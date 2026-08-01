package org.example;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La razón social es obligatoria")
    private String apellidgORazonSocial;

    @NotBlank(message = "El documento es obligatorio")
    private String documento0Cuit;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio")
    private String telefeng;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @NotBlank(message = "El tipo de cliente es obligatorio")
    private String tipoCliente;

    private boolean activo;

    @PositiveOrZero(message = "El saldo pendiente no puede ser negativo")
    private double saldoPendiente;

    @NotNull(message = "La fecha de alta es obligatoria")
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
