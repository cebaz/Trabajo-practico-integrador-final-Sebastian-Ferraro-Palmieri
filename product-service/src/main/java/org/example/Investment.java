package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("INVESTMENT")
public class Investment extends Product {

    @Column(name = "invested_amount")
    private Long investedAmount;

    @Column(name = "annual_rate")
    private Double annualRate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;
}
