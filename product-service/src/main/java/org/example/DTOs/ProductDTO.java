package org.example.DTOs;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "type",
    visible = true
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = AccountDTO.class, name = "ACCOUNT"),
    @JsonSubTypes.Type(value = CreditCardDTO.class, name = "CREDIT_CARD"),
    @JsonSubTypes.Type(value = LoanDTO.class, name = "LOAN"),
    @JsonSubTypes.Type(value = InvestmentDTO.class, name = "INVESTMENT")
})
public abstract class ProductDTO {

    private Long accountId;

    @NotNull(message = "El número de cuenta es obligatorio")
    @Positive(message = "El número de cuenta debe ser positivo")
    private Long accountNumber;

    @NotBlank(message = "El tipo de producto es obligatorio")
    private String type;

    @NotBlank(message = "El estado es obligatorio")
    private String status;

    private LocalDate createdAt;
    private LocalDate lastModificationDate;

    @NotNull(message = "El identificador del cliente es obligatorio")
    @Positive(message = "El identificador del cliente debe ser positivo")
    private Long customerId;
}
