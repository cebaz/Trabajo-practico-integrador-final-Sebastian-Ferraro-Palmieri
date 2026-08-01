package org.example.DTOs;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Currency;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class AccountDTO extends ProductDTO {

    @PositiveOrZero(message = "El saldo no puede ser negativo")
    private Long balance;

    @NotNull(message = "La moneda es obligatoria")
    private Currency currency;
}
