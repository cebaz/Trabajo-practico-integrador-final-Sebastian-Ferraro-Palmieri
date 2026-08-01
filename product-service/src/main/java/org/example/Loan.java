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
@DiscriminatorValue("LOAN")
public class Loan extends Product {

    @Column(name = "amount")
    private Long amount;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "installments")
    private Integer installments;
}
