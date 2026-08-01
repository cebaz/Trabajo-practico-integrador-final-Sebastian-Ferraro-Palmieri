package org.example.DTOs;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class CreditCardDTO extends ProductDTO {

    private String cardNumber;
    private Long creditLimit;
    private Long availableLimit;
    private Integer closingDay;
}
