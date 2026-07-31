package org.example;

import java.time.LocalDate;

public record ProductDto(
        Long accountId,
        Long accountNumber,
        String type,
        Currency currency,
        Long balance,
        String status,
        LocalDate createdAt,
        LocalDate lastModificationDate,
        Long clientId
) {
}
