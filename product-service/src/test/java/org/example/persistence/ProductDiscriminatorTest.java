package org.example.persistence;

import org.example.Currency;
import org.example.Product;
import org.example.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductDiscriminatorTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void cadaSubtipoSeGuardaYSeVuelveALeerConSuClase() {
        productRepository.save(Product.builder()
                .type("ACCOUNT").accountNur(1L).clientId(10L)
                .balance(500L).currency(Currency.ARS).build());
        productRepository.save(Product.builder()
                .type("CREDIT_CARD").accountNur(2L).clientId(10L).build());
        productRepository.save(Product.builder()
                .type("LOAN").accountNur(3L).clientId(10L).build());
        productRepository.save(Product.builder()
                .type("INVESTMENT").accountNur(4L).clientId(10L).build());
        entityManager.flush();
        entityManager.clear();

        List<Product> products = productRepository.findAll();

        assertThat(products)
                .extracting(product -> product.getClass().getSimpleName())
                .containsExactlyInAnyOrder("Account", "CreditCard", "Loan", "Investment");
    }

    @Test
    void unTipoDeNegocioPropioNoRompeLaLectura() {
        productRepository.save(Product.builder()
                .type("CAJA_AHORRO").accountNur(5L).clientId(10L)
                .balance(1000L).currency(Currency.ARS).build());
        entityManager.flush();
        entityManager.clear();

        List<Product> products = productRepository.findAll();

        assertThat(products).singleElement().satisfies(product -> {
            assertThat(product.getClass().getSimpleName()).isEqualTo("Account");
            assertThat(product.getType()).isEqualTo("CAJA_AHORRO");
        });
    }

    @Test
    void elDiscriminadorViajaEnProductTypeYNoEnType() {
        productRepository.save(Product.builder()
                .type("CAJA_AHORRO").accountNur(99L).clientId(10L).build());
        entityManager.flush();

        Object[] stored = (Object[]) entityManager.getEntityManager()
                .createNativeQuery("select product_type, type from account where account_nur = 99")
                .getSingleResult();

        assertThat(stored).containsExactly("ACCOUNT", "CAJA_AHORRO");
    }

    @Test
    void findByCustomerIdDevuelveSoloLosProductosDeEseCliente() {
        productRepository.save(Product.builder().type("ACCOUNT").accountNur(11L).clientId(1L).build());
        productRepository.save(Product.builder().type("LOAN").accountNur(12L).clientId(1L).build());
        productRepository.save(Product.builder().type("INVESTMENT").accountNur(13L).clientId(2L).build());
        entityManager.flush();
        entityManager.clear();

        List<Product> products = productRepository.findByCustomerId(1L);

        assertThat(products)
                .extracting(Product::getClientId)
                .containsOnly(1L);
    }
}
