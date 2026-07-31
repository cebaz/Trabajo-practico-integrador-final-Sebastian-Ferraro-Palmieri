package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accont_id")
    public long accontId;

    @Column(name = "account_nur", nullable = false)
    public long accountNur;

    @Column(nullable = false)
    public String type;

    @Enumerated(EnumType.STRING)
    public Currency currency;

    @Column(nullable = false)
    public long balance;

    public String status;

    @Column(name = "created_at")
    public LocalDate createdAt;

    @Column(name = "last_modification_date")
    public LocalDate lastModificationDate;

    @Column(name = "client_id", nullable = false)
    public long clientId;


}
