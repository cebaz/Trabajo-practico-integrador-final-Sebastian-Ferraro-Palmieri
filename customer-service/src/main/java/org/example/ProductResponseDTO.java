package org.example;

import java.time.LocalDate;

public class ProductResponseDTO {
    private Long accountId;
    private Long accountNumber;
    private String type;
    private String status;
    private LocalDate createdAt;
    private LocalDate lastModificationDate;
    private Long customerId;

    public ProductResponseDTO() {}

    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }

    public Long getAccountNumber() { return accountNumber; }
    public void setAccountNumber(Long accountNumber) { this.accountNumber = accountNumber; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }

    public LocalDate getLastModificationDate() { return lastModificationDate; }
    public void setLastModificationDate(LocalDate lastModificationDate) { this.lastModificationDate = lastModificationDate; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
}
