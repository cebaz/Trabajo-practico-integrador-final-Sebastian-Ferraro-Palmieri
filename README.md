# Trabajo Práctico Integrador Final

## Diplomatura en Desarrollo de Software FinTech: IA y Microservicios

## Descripción

Este proyecto implementa un ecosistema de microservicios desarrollado con Spring Boot y Spring Cloud, siguiendo la arquitectura propuesta en la consigna del trabajo integrador.

El sistema está compuesto por cuatro proyectos independientes que trabajan de forma integrada mediante descubrimiento de servicios y configuración centralizada.

## Arquitectura

El proyecto está conformado por los siguientes módulos:

## Componentes y Puertos
|    Componente    | Puerto |
|------------------|--------|
| Config Server    |  8888  |
| Eureka Server    |  8761  |
| Customer Service |  8081  |
| Product Service  |  8082  |

### customer-service

Servicio principal encargado de la gestión de clientes.

Funciones principales:

* Registrar clientes.
* Consultar clientes.
* Actualizar la información de los clientes.
* Obtener los productos asociados a un cliente mediante comunicación con `product-service`.

### product-service

Servicio encargado de administrar los productos financieros disponibles para los clientes.

Entre los productos gestionados se encuentran:

* Cuentas.
* Tarjetas.
* Préstamos.
* Inversiones.

Expone endpoints REST que permiten consultar los productos asociados a un cliente según su identificador.

### eureka-server

Servidor de descubrimiento de servicios (Service Discovery).

Su función es registrar los microservicios para que puedan encontrarse entre sí sin depender de direcciones IP o puertos configurados manualmente.

Los servicios registrados son:

* customer-service
* product-service

### config.server

Servidor de configuración centralizada utilizando Spring Cloud Config Server.

Las configuraciones de los distintos microservicios se obtienen desde un repositorio Git remoto, evitando mantener propiedades duplicadas en cada servicio.

## Comunicación entre servicios

La comunicación entre los microservicios se realiza mediante **OpenFeign**.

Flujo de funcionamiento:

1. El cliente realiza una solicitud a `customer-service`.
2. `customer-service` procesa la solicitud.
3. Cuando necesita obtener los productos del cliente, realiza una llamada mediante Feign Client a `product-service`.
4. `product-service` responde con la información solicitada.
5. `customer-service` devuelve la respuesta al consumidor de la API.

## Endpoints principales
Customer Service — http://localhost:8081
Obtener todos los clientes

GET /customers

Response:

[
  {
    "id": 1,
    "nombre": "Juan",
    "apellidoORazonSocial": "Pérez",
    "documentoCuit": "20123456789",
    "direccion": "Av. Corrientes 123",
    "telefono": "1123456789",
    "email": "juan.perez@email.com",
    "tipoCliente": "PERSONA",
    "activo": true,
    "saldoPendiente": 0.0,
    "fechaAlta": "2026-08-01"
  }
]
Obtener un cliente por ID

GET /customers/{id}

Ejemplo: /customers/1

Response:

{
  "id": 1,
  "nombre": "Juan",
  "apellidoORazonSocial": "Pérez",
  "documentoCuit": "20123456789",
  "direccion": "Av. Corrientes 123",
  "telefono": "1123456789",
  "email": "juan.perez@email.com",
  "tipoCliente": "PERSONA",
  "activo": true,
  "saldoPendiente": 0.0,
  "fechaAlta": "2026-08-01"
}
Crear un cliente

POST /customers/agregar

Request:

{
  "nombre": "Juan",
  "apellidoORazonSocial": "Pérez",
  "documentoCuit": "20123456789",
  "direccion": "Av. Corrientes 123",
  "telefono": "1123456789",
  "email": "juan.perez@email.com",
  "tipoCliente": "PERSONA",
  "activo": true,
  "saldoPendiente": 0.0,
  "fechaAlta": "2026-08-01"
}

Response:

{
  "id": 1,
  "nombre": "Juan",
  "apellidoORazonSocial": "Pérez",
  "documentoCuit": "20123456789",
  "direccion": "Av. Corrientes 123",
  "telefono": "1123456789",
  "email": "juan.perez@email.com",
  "tipoCliente": "PERSONA",
  "activo": true,
  "saldoPendiente": 0.0,
  "fechaAlta": "2026-08-01"
}
Actualizar un cliente

PUT /customers/editar/{id}

Ejemplo: /customers/editar/1

Request:

{
  "nombre": "Juan",
  "apellidoORazonSocial": "Pérez Gómez",
  "documentoCuit": "20123456789",
  "direccion": "Av. Corrientes 456",
  "telefono": "1198765432",
  "email": "juan.gomez@email.com",
  "tipoCliente": "PERSONA",
  "activo": true,
  "saldoPendiente": 0.0,
  "fechaAlta": "2026-08-01"
}

Response:

{
  "id": 1,
  "nombre": "Juan",
  "apellidoORazonSocial": "Pérez Gómez",
  "documentoCuit": "20123456789",
  "direccion": "Av. Corrientes 456",
  "telefono": "1198765432",
  "email": "juan.gomez@email.com",
  "tipoCliente": "PERSONA",
  "activo": true,
  "saldoPendiente": 0.0,
  "fechaAlta": "2026-08-01"
}
Eliminar un cliente

DELETE /customers/eliminar/{id}

Ejemplo: /customers/eliminar/1

Response:

HTTP 200 OK sin contenido.

Obtener productos de un cliente

GET /customers/{id}/products

Ejemplo: /customers/1/products

Este endpoint utiliza OpenFeign para consultar product-service.

Response:

[
  {
    "accountId": 1,
    "accountNumber": 123456,
    "type": "ACCOUNT",
    "status": "ACTIVE",
    "createdAt": "2026-08-01",
    "lastModificationDate": "2026-08-01",
    "customerId": 1
  }
]
Product Service — http://localhost:8082
Obtener todos los productos

GET /products

Response:

[
  {
    "accountId": 1,
    "accountNumber": 123456,
    "type": "ACCOUNT",
    "status": "ACTIVE",
    "createdAt": "2026-08-01",
    "lastModificationDate": "2026-08-01",
    "customerId": 1
  }
]
Obtener un producto por ID

GET /products/{id}

Ejemplo: /products/1

Response:

{
  "accountId": 1,
  "accountNumber": 123456,
  "type": "ACCOUNT",
  "status": "ACTIVE",
  "createdAt": "2026-08-01",
  "lastModificationDate": "2026-08-01",
  "customerId": 1
}
Obtener productos de un cliente

GET /products/customer/{customerId}

Ejemplo: /products/customer/1

Response:

[
  {
    "accountId": 1,
    "accountNumber": 123456,
    "type": "ACCOUNT",
    "status": "ACTIVE",
    "createdAt": "2026-08-01",
    "lastModificationDate": "2026-08-01",
    "customerId": 1
  }
]
Crear una cuenta

POST /products/accounts

Request:

{
  "accountNumber": 123456,
  "type": "ACCOUNT",
  "status": "ACTIVE",
  "customerId": 1,
  "balance": 50000,
  "currency": "ARS"
}

Response:

{
  "accountId": 1,
  "accountNumber": 123456,
  "type": "ACCOUNT",
  "status": "ACTIVE",
  "createdAt": "2026-08-01",
  "lastModificationDate": "2026-08-01",
  "customerId": 1,
  "balance": 50000,
  "currency": "ARS"
}
Crear una tarjeta de crédito

POST /products/credit-cards

Request:

{
  "accountNumber": 123457,
  "type": "CREDIT_CARD",
  "status": "ACTIVE",
  "customerId": 1,
  "cardNumber": "4500123456789012",
  "creditLimit": 500000,
  "availableLimit": 500000,
  "closingDay": 10
}
Crear un préstamo

POST /products/loans

Request:

{
  "accountNumber": 123458,
  "type": "LOAN",
  "status": "ACTIVE",
  "customerId": 1,
  "amount": 1000000,
  "interestRate": 50.0,
  "installments": 12
}
Crear una inversión

POST /products/investments

Request:

{
  "accountNumber": 123459,
  "type": "INVESTMENT",
  "status": "ACTIVE",
  "customerId": 1,
  "investedAmount": 500000,
  "annualRate": 40.0,
  "expirationDate": "2027-08-01"
}
Eliminar un producto

DELETE /products/eliminar/{id}

Ejemplo: /products/eliminar/1

Response:

HTTP 200 OK sin contenido.

## Tecnologías utilizadas

* Java
* Spring Boot
* Spring Cloud
* Spring Cloud Config Server
* Spring Cloud Netflix Eureka
* OpenFeign
* Maven

## Documentación de API
Customer Service: http://localhost:8081/swagger-ui/index.html
Product Service: http://localhost:8082/swagger-ui/index.html
Eureka Server: http://localhost:8761/swagger-ui/index.html

## Estructura del proyecto

```
Trabajo práctico integrador final
│
├── customer-service
├── product-service
├── eureka-server
└── config.server
```

## Instrucciones de ejecución

### Requisitos

* Java JDK 17 o superior.
* Maven 3.9 o superior.
* Acceso al repositorio remoto de configuración utilizado por Config Server.

### Orden de inicio

Para el correcto funcionamiento del sistema, los servicios deben iniciarse en el siguiente orden:

1. Config Server (`config.server`)
2. Eureka Server (`eureka-server`)
3. Product Service (`product-service`)
4. Customer Service (`customer-service`)

Una vez iniciados, ambos microservicios se registrarán automáticamente en Eureka y obtendrán su configuración desde el Config Server.

## Funcionalidades implementadas

* Gestión de clientes.
* Gestión de productos.
* Comunicación entre microservicios mediante Feign Client.
* Registro automático de servicios en Eureka.
* Configuración centralizada mediante Spring Cloud Config Server.
* Uso de DTOs para la comunicación.
* Uso de mapeadores entre entidades y DTOs.
* Manejo de excepciones.

### Repositorio de configuración
El Config Server obtiene la configuración desde:
https://github.com/cebaz/config-repository.git

## Autor

Trabajo práctico integrador correspondiente a la Diplomatura en Desarrollo de Software FinTech: IA y Microservicios.
