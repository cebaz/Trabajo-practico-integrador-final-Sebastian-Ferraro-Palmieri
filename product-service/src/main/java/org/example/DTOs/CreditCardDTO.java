package org.example.DTOs;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class CreditCardDTO extends ProductDTO {

    @NotBlank(message = "El número de tarjeta es obligatorio")
    @Size(min = 12, max = 19, message = "El número de tarjeta debe tener entre 12 y 19 caracteres")
    private String cardNumber;

    @NotNull(message = "El límite de crédito es obligatorio")
    @PositiveOrZero(message = "El límite de crédito no puede ser negativo")
    private Long creditLimit;

    @NotNull(message = "El límite disponible es obligatorio")
    @PositiveOrZero(message = "El límite disponible no puede ser negativo")
    private Long availableLimit;

    @NotNull(message = "El día de cierre es obligatorio")
    @Positive(message = "El día de cierre debe ser positivo")
    private Integer closingDay;
}
