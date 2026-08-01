package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.time.LocalDate;
import java.util.Locale;

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING, length = 32)
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accont_id")
    public Long accontId;

    @Column(name = "account_nur", nullable = false)
    public Long accountNur;
    
    @Column(name = "type", nullable = false)
    public String type;

    public String status;

    @Column(name = "created_at")
    public LocalDate createdAt;

    @Column(name = "last_modification_date")
    public LocalDate lastModificationDate;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Transient
    private Long transientBalance;

    @Transient
    private Currency transientCurrency;

    protected Product() {}

    public Long getAccontId() {
        return accontId;
    }

    public void setAccontId(Long accontId) {
        this.accontId = accontId;
    }

    public Long getAccountNur() {
        return accountNur;
    }

    public void setAccountNur(Long accountNur) {
        this.accountNur = accountNur;
    }

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(LocalDate lastModificationDate) {this.lastModificationDate = lastModificationDate;}

    public Long getClientId() {
        return customerId;
    }

    public void setClientId(Long clientId) {
        this.customerId = clientId;
    }

    public Long getBalance() {
        return transientBalance;
    }

    public void setBalance(Long balance) {
        this.transientBalance = balance;
    }

    public Currency getCurrency() {
        return transientCurrency;
    }

    public void setCurrency(Currency currency) {
        this.transientCurrency = currency;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Long accontId;
        private Long accountNur;
        private String type;
        private String status;
        private LocalDate createdAt;
        private LocalDate lastModificationDate;
        private Long clientId;
        private Long balance;
        private Currency currency;

        public Builder accontId(Long accontId) {
            this.accontId = accontId;
            return this;
        }

        public Builder accountNur(Long accountNur) {
            this.accountNur = accountNur;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder createdAt(LocalDate createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder lastModificationDate(LocalDate lastModificationDate) {
            this.lastModificationDate = lastModificationDate;
            return this;
        }

        public Builder clientId(Long clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder balance(Long balance) {
            this.balance = balance;
            return this;
        }

        public Builder currency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Product build() {
            Product product = createProduct();
            product.setAccontId(accontId);
            product.setAccountNur(accountNur);
            product.setType(type == null || type.isBlank() ? discriminatorOf(product) : type);
            product.setStatus(status);
            product.setCreatedAt(createdAt);
            product.setLastModificationDate(lastModificationDate);
            product.setClientId(clientId);
            product.setBalance(balance);
            product.setCurrency(currency);
            return product;
        }

        private Product createProduct() {
            String normalizedType = type == null ? "" : type.trim().toUpperCase(Locale.ROOT);
            return switch (normalizedType) {
                case "CREDIT_CARD", "CREDITCARD", "CREDIT CARD" -> new CreditCard();
                case "LOAN" -> new Loan();
                case "INVESTMENT" -> new Investment();
                default -> new Account();
            };
        }

        /** Tipo de respaldo cuando no llega ninguno: el del subtipo instanciado. */
        private static String discriminatorOf(Product product) {
            DiscriminatorValue discriminatorValue =
                    product.getClass().getAnnotation(DiscriminatorValue.class);
            return discriminatorValue == null ? null : discriminatorValue.value();
        }
    }
}
