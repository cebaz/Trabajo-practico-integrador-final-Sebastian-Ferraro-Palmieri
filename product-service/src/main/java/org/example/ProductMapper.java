package org.example;

import org.example.DTOs.AccountDTO;
import org.example.DTOs.CreditCardDTO;
import org.example.DTOs.InvestmentDTO;
import org.example.DTOs.LoanDTO;
import org.example.DTOs.ProductDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapea la jerarquía Product/ProductDTO. Cada subtipo tiene su propio par de
 * métodos; toDto/toEntity solo despachan al método concreto según el tipo real.
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProductMapper {

    // ---------- Despacho polimórfico ----------

    default ProductDTO toDto(Product product) {
        if (product == null) return null;
        return switch (product) {
            case Account account -> toAccountDto(account);
            case CreditCard creditCard -> toCreditCardDto(creditCard);
            case Loan loan -> toLoanDto(loan);
            case Investment investment -> toInvestmentDto(investment);
            default -> throw new IllegalArgumentException(
                    "Tipo de producto no soportado: " + product.getClass().getName());
        };
    }

    default Product toEntity(ProductDTO dto) {
        if (dto == null) return null;
        return switch (dto) {
            case AccountDTO account -> toAccountEntity(account);
            case CreditCardDTO creditCard -> toCreditCardEntity(creditCard);
            case LoanDTO loan -> toLoanEntity(loan);
            case InvestmentDTO investment -> toInvestmentEntity(investment);
            default -> throw new IllegalArgumentException(
                    "Tipo de producto no soportado: " + dto.getClass().getName());
        };
    }

    // ---------- Account ----------

    @Mapping(source = "accontId", target = "accountId")
    @Mapping(source = "accountNur", target = "accountNumber")
    @Mapping(source = "clientId", target = "customerId")
    AccountDTO toAccountDto(Account account);

    @Mapping(source = "accountId", target = "accontId")
    @Mapping(source = "accountNumber", target = "accountNur")
    @Mapping(source = "customerId", target = "clientId")
    Account toAccountEntity(AccountDTO dto);

    // ---------- CreditCard ----------

    @Mapping(source = "accontId", target = "accountId")
    @Mapping(source = "accountNur", target = "accountNumber")
    @Mapping(source = "clientId", target = "customerId")
    CreditCardDTO toCreditCardDto(CreditCard creditCard);

    @Mapping(source = "accountId", target = "accontId")
    @Mapping(source = "accountNumber", target = "accountNur")
    @Mapping(source = "customerId", target = "clientId")
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "currency", ignore = true)
    CreditCard toCreditCardEntity(CreditCardDTO dto);

    // ---------- Loan ----------

    @Mapping(source = "accontId", target = "accountId")
    @Mapping(source = "accountNur", target = "accountNumber")
    @Mapping(source = "clientId", target = "customerId")
    LoanDTO toLoanDto(Loan loan);

    @Mapping(source = "accountId", target = "accontId")
    @Mapping(source = "accountNumber", target = "accountNur")
    @Mapping(source = "customerId", target = "clientId")
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "currency", ignore = true)
    Loan toLoanEntity(LoanDTO dto);

    // ---------- Investment ----------

    @Mapping(source = "accontId", target = "accountId")
    @Mapping(source = "accountNur", target = "accountNumber")
    @Mapping(source = "clientId", target = "customerId")
    InvestmentDTO toInvestmentDto(Investment investment);

    @Mapping(source = "accountId", target = "accontId")
    @Mapping(source = "accountNumber", target = "accountNur")
    @Mapping(source = "customerId", target = "clientId")
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "currency", ignore = true)
    Investment toInvestmentEntity(InvestmentDTO dto);

    // ---------- Métodos previos (map/…): delegan en los nuevos ----------
    // Se conservan para no romper código existente; se pueden borrar cuando
    // ya nadie los use.

    @Deprecated
    default AccountDTO map(Account account) {
        return toAccountDto(account);
    }

    @Deprecated
    default CreditCardDTO map(CreditCard creditCard) {
        return toCreditCardDto(creditCard);
    }

    @Deprecated
    default LoanDTO map(Loan loan) {
        return toLoanDto(loan);
    }

    @Deprecated
    default InvestmentDTO map(Investment investment) {
        return toInvestmentDto(investment);
    }

    @Deprecated
    default Account map(AccountDTO dto) {
        return toAccountEntity(dto);
    }

    @Deprecated
    default CreditCard map(CreditCardDTO dto) {
        return toCreditCardEntity(dto);
    }

    @Deprecated
    default Loan map(LoanDTO dto) {
        return toLoanEntity(dto);
    }

    @Deprecated
    default Investment map(InvestmentDTO dto) {
        return toInvestmentEntity(dto);
    }
}
