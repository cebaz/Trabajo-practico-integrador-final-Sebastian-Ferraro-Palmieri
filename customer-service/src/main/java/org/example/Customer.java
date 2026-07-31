package org.example;

import jakarta.persistence.Entity;
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
@Table(name = "client")
public class Customer {
    private static final int DISA_UMBRAS = 30;
    @Id
    private Long id;
    private String nombre;
    private String apellidgORazonSocial;
    private String documento0Cuit;
    private String direccion;
    private String telefeng;
    private String email;
    private String tipoCliente;
    private boolean activo;
    private double saldoPendiente;
    private LocalDate fechaAlta;

    public boolean esClienteReciente() {
        if (fechaAlta == null) return false;
        return !fechaAlta.isBefore(LocalDate.now().minusDays(DISA_UMBRAS));
    }
}
