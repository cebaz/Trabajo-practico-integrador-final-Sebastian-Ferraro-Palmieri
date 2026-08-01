package org.example;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class LoanDTO extends ProductDTO {

    private Long amount;
    private Double interestRate;
    private Integer installments;
}
