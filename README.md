# Trabajo Práctico Integrador Final

## Diplomatura en Desarrollo de Software FinTech: IA y Microservicios

## Descripción

Este proyecto implementa un ecosistema de microservicios desarrollado con Spring Boot y Spring Cloud, siguiendo la arquitectura propuesta en la consigna del trabajo integrador.

El sistema está compuesto por cuatro proyectos independientes que trabajan de forma integrada mediante descubrimiento de servicios y configuración centralizada.

## Arquitectura

El proyecto está conformado por los siguientes módulos:

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

## Tecnologías utilizadas

* Java
* Spring Boot
* Spring Cloud
* Spring Cloud Config Server
* Spring Cloud Netflix Eureka
* OpenFeign
* Maven

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

## Autor

Trabajo práctico integrador correspondiente a la Diplomatura en Desarrollo de Software FinTech: IA y Microservicios.
