package org.example.DTOs;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.DTOs.ProductDTO;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class LoanDTO extends ProductDTO {

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser positivo")
    private Long amount;

    @NotNull(message = "La tasa de interés es obligatoria")
    @PositiveOrZero(message = "La tasa de interés no puede ser negativa")
    private Double interestRate;

    @NotNull(message = "La cantidad de cuotas es obligatoria")
    @Positive(message = "La cantidad de cuotas debe ser positiva")
    private Integer installments;
}
