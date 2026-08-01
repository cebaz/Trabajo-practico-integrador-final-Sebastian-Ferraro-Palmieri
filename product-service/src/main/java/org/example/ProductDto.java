package org.example;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
    private Long accountNumber;
    private String type;
    private String status;
    private LocalDate createdAt;
    private LocalDate lastModificationDate;
    private Long customerId;
}
