package org.example.DTOs;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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

    @NotNull(message = "El monto invertido es obligatorio")
    @Positive(message = "El monto invertido debe ser positivo")
    private Long investedAmount;

    @NotNull(message = "La tasa anual es obligatoria")
    @PositiveOrZero(message = "La tasa anual no puede ser negativa")
    private Double annualRate;

    @NotNull(message = "La fecha de vencimiento es obligatoria")
    private LocalDate expirationDate;
}
