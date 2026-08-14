package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "client")
public class Customer {
    private static final int DIAS_UMBRAL = 30;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido_o_razon_social")
    private String apellidoORazonSocial;

    @Column(name = "documento_cuit")
    private String documentoCuit;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

    @Column(name = "tipo_cliente")
    private String tipoCliente;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    @Column(name = "saldo_pendiente", nullable = false)
    private double saldoPendiente;

    @Column(name = "fecha_alta")
    private LocalDate fechaAlta;

    public boolean esClienteReciente() {
        if (fechaAlta == null) return false;
        return !fechaAlta.isBefore(LocalDate.now().minusDays(DIAS_UMBRAL));
    }
}
