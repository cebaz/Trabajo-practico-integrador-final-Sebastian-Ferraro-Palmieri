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

## Por corejir
==========================================================================
DEVOLUCION - ACTIVIDAD DE CIERRE
SEGUNDA REVISION
Diplomatura en Desarrollo de Software FinTech: IA y Microservicios
Clase 26 - Ecosistema de microservicios
==========================================================================

Estudiante: Sebastian Ferraro Palmieri (cebaz)

--------------------------------------------------------------------------
RESULTADO POR CRITERIO
--------------------------------------------------------------------------
Integracion tecnica (Eureka + Feign) ....... LOGRADO
Gestion de configuracion (Config Server) ... LOGRADO
Diseno y buenas practicas .................. LOGRADO
Funcionalidad .............................. LOGRADO
Documentacion .............................. LOGRADO

--------------------------------------------------------------------------
QUE CAMBIO DESDE LA ENTREGA ANTERIOR
--------------------------------------------------------------------------
Las seis observaciones de la devolucion anterior:

1. Faltaba la actualizacion de clientes
RESUELTO. PUT /customers/editar/{id} en CustomerController y
CustomerService.updateClient, que verifica que el cliente exista, lanza
CustomerNotFoundException si no, y devuelve el DTO actualizado. Exacto
lo que estaba pedido, y con @Valid sobre el cuerpo.

2. Dos clases distintas llamadas CustomerNotFoundException
RESUELTO. Borraste la de org.example, con su @ResponseStatus, y quedo
solo la de org.example.exception, que es la que lanza el service y la
que maneja el GlobalExceptionHandler. Ahora el 404 sale por el handler,
que era el problema de fondo.

3. Las excepciones propias estaban definidas y no se lanzaban nunca
RESUELTO en lo importante. getClientById y getAccountById dejaron de
devolver Optional y lanzan la excepcion de "no encontrado" con
orElseThrow; los dos deleteById chequean existsById antes de borrar, con
lo cual un id inexistente ahora devuelve 404 y no un 500 por
EmptyResultDataAccessException. Quedan dos clases sin usar: ver mejora 5.

4. CustomerController se salteaba la capa de servicio
RESUELTO. El controller ya no recibe el repositorio ni el ProductClient:
inyecta solo CustomerService, y la verificacion del cliente y la llamada
Feign quedaron en getCustomerProducts del service. De paso desaparecio
el @Autowired en campo que convivia con la inyeccion por constructor en
la misma clase.

5. Customer sin @GeneratedValue
RESUELTO, y ademas anotaste todos los campos con @Column y nombres
explicitos en snake_case.

6. Al README le faltaban puertos, URL del repositorio de configuracion y
endpoints
RESUELTO. Estan la tabla de puertos, la URL del repositorio de
configuracion y los endpoints de los dos servicios con ejemplos de
request y response, incluidos los cuatro POST por subtipo de producto.

Ademas aplicaste once de las dieciseis mejoras sugeridas: tipaste el
contrato Feign, corregiste "Server:" por "server:", renombraste
apellidgORazonSocial, documento0Cuit, telefeng y DISA_UMBRAS, sacaste el
Main.java de ejemplo y las tres carpetas .idea/ con .gitignore por
proyecto, y corregiste el test del discriminador para que consulte la tabla
product.

--------------------------------------------------------------------------
LO QUE ESTA BIEN RESUELTO
--------------------------------------------------------------------------
- Atendiste las seis observaciones. Ninguna quedo sin tratar, y las cuatro
de codigo estan resueltas tal cual estaban planteadas.
- Los mensajes de commit citan por numero las mejoras que resuelven. Es una
practica poco frecuente y hace que revisar tu trabajo sea directo.
- El tipado del contrato Feign esta bien hecho, y no era automatico.
Cambiar List> por List podia
romperse contra la serializacion polimorfica de ProductDTO; como tu
@JsonTypeInfo usa EXISTING_PROPERTY sobre el campo type, el JSON sale
plano y el DTO plano del consumidor deserializa bien. Los siete campos
coinciden uno a uno.
- La reorganizacion de CustomerController y CustomerService es la
correccion mejor hecha del conjunto: no moviste codigo de lugar,
reordenaste responsabilidades, y el controller quedo en una linea por
endpoint.
- El README paso de superficial a completo, y los ejemplos estan
sincronizados con el codigo real: usan los nombres de campo ya corregidos
y las rutas efectivas, incluidas las que no son adivinables
(/customers/agregar, /customers/editar/{id}, /products/eliminar/{id}).
- Se mantiene intacto lo que ya era fuerte: el ProductMapper con MapStruct
sobre la jerarquia de productos, el modelo SINGLE_TABLE con discriminador,
las validaciones Bean Validation por subtipo, los tests de persistencia
con @DataJpaTest y la configuracion centralizada real, con los
application.yml locales reducidos al nombre y al import.

--------------------------------------------------------------------------
LO QUE HAY QUE REVISAR
--------------------------------------------------------------------------
Sin observaciones: los cinco criterios quedaron en LOGRADO.

Nota: Un punto de la consigna aparte de la rubrica sigue pendiente: la revision de
codigo solicitada al asistente IA de la plataforma Git. Revise los dos
repositorios y no hay pull requests, issues ni menciones en los commits o en
el README. Es lo unico del enunciado que quedó sin atender en las dos
entregas, y es de lo mas rapido de hacer: abrí una pull request sobre este
proyecto y pedile la revision.

--------------------------------------------------------------------------
MEJORAS SUGERIDAS
--------------------------------------------------------------------------
Ordenadas por impacto. Ninguna es una falta solo un pulido para mostrar.

1. Subi al repositorio de configuracion el cambio del eureka-server.yaml
El commit del 8 de agosto dice que aplicaste la mejora 8, pero
config-repository no tiene commits nuevos y el archivo sigue sin
register-with-eureka: false ni fetch-registry: false. Parece que el
cambio quedo hecho en local sin pushear. Sin eso, el servidor de
descubrimiento intenta registrarse en si mismo y te llena el log de
errores de conexion que cuesta distinguir de un problema real.

2. Saca el datasource y las contrasenas del repositorio de configuracion
Los tres archivos siguen con usuario root y contrasena root en un
repositorio publico, y eureka-server.yaml ademas define un datasource
MySQL completo que un servidor de descubrimiento no usa. Como ya estan
en el historial, borrarlas del archivo no alcanza: hay que rotarlas y
despues externalizarlas por variables de entorno, o cifrarlas con la
funcionalidad de encriptacion del propio Config Server.

3. Borra la clase vacia ProductResponseDTO de product-service
product-service/.../DTOs/ProductResponseDTO.java quedo sin campos ni
uso. El DTO que hacia falta ya lo tenes, del lado de customer-service.

4. Termina de renombrar los campos de Product
accontId y accountNur siguen igual en la entidad. Al JSON no se filtran
porque el ProductMapper los traduce a accountId y accountNumber, pero si
llegan a los nombres de columna de la base y al codigo de los tests.
Ahora es mas barato que cuando el esquema se consolide.

5. Usa InvalidProductException y CustomerAlreadyExistsException, o quitalas
Tienen clase y handler propios y ningun throw. Los tres
IllegalArgumentException que lanzan ProductMapper y ProductService ante
un subtipo de DTO desconocido son justamente el caso de
InvalidProductException. Y CustomerAlreadyExistsException encaja en un
chequeo de documento o email duplicado antes del alta.

6. Restitui los 201 en las altas de product-service
Se fueron los @ResponseStatus(HttpStatus.CREATED) de los cuatro POST y
ahora responden 200. No estaba entre lo pedido y estaba bien como
estaba: un alta que devuelve 201 con el recurso creado es lo correcto.

7. Limpia los imports innecesarios y la carpeta que quedo vacia
ProductClient, CustomerController y CustomerService importan
org.example.ProductResponseDTO estando en el mismo paquete. Y de
config.server/config.server/ solo quedo un .gitignore, resto del
proyecto de ejemplo que borraste.

8. Pone la barra inicial en @PutMapping("editar/{id}")
Funciona igual porque Spring la agrega al combinar con el
@RequestMapping de la clase, pero es la unica ruta del proyecto
declarada asi: al lado tenes "/eliminar/{id}".

9. Corregi el link de Swagger del Eureka Server en el README
Un Eureka Server no expone Swagger UI. Su dashboard es
http://localhost:8761 a secas.

10. Unifica el estilo de inyeccion en product-service
ProductController y ProductService siguen con @Autowired en campo,
mientras que customer-service ya paso a constructor. Con constructor,
las dependencias quedan explicitas y la clase se puede testear sin
contexto de Spring.

11. (Opcional) Usa BigDecimal para los importes
saldoPendiente sigue siendo double, y balance, creditLimit, amount e
investedAmount siguen siendo Long. Para dinero corresponde BigDecimal:
double arrastra error de redondeo y Long te obliga a decidir la unidad
minima fuera del tipo.

12. (Opcional) Maneja la caida de product-service
Si ese servicio no esta levantado, Feign lanza FeignException y hoy eso
cae en tu handler generico como un 500 poco informativo. Un
@ExceptionHandler especifico, o un fallback con Resilience4j, devolveria
algo mas util a quien consume la API.

--------------------------------------------------------------------------

==========================================================================
