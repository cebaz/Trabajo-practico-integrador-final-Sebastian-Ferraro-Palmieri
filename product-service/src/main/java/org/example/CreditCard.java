package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("CREDIT_CARD")
public class CreditCard extends Product {

    @Column(name = "card_number", length = 32)
    private String cardNumber;

    @Column(name = "credit_limit")
    private Long creditLimit;

    @Column(name = "available_limit")
    private Long availableLimit;

    @Column(name = "closing_day")
    private Integer closingDay;
}
