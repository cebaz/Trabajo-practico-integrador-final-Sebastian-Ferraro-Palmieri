package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
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

    public static CustomerDTO fromEntity(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerDTO(
                customer.getId(),
                customer.getNombre(),
                customer.getApellidgORazonSocial(),
                customer.getDocumento0Cuit(),
                customer.getDireccion(),
                customer.getTelefeng(),
                customer.getEmail(),
                customer.getTipoCliente(),
                customer.isActivo(),
                customer.getSaldoPendiente(),
                customer.getFechaAlta()
        );
    }

    public Customer toEntity() {
        return new Customer(
                id,
                nombre,
                apellidgORazonSocial,
                documento0Cuit,
                direccion,
                telefeng,
                email,
                tipoCliente,
                activo,
                saldoPendiente,
                fechaAlta
        );
    }
}
