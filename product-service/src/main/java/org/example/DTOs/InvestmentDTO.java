package org.example.DTOs;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.DTOs.ProductDTO;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class InvestmentDTO extends ProductDTO {

    private Long investedAmount;
    private Double annualRate;
    private LocalDate expirationDate;
}
