# MicroServicios-Ejercicio

Este es un ejercicio que estoy realizando para usar lo aprendido en la implementacion de microservicios

En este ejercicio encontrara las practicas:
- Manejo de DTO, para las transferencias de datos.
- CamelCase, para el nombramiento de variables, metodos, etc.
- Comentarios entendibles, estos explican el funcionamiento de los metodos y parte poco usuales

## Informacion del Proyecto

versiones:

- java 1.8
- spring 2.5.14

dependencias

- spring boot devtools
- spring data jpa
- h2 database //es una base de datos en memoria
- spring web
- OpenFeign //puede ser implementada cuando se requiera

actualmente se encuentra en la version 3.0 "Comunincacion Por RestTemplate y FeignClient"

## Tipos de comunicacion

- Rest Template (implementado): Implementacion de los servicios getCars y getMotos | Implementacion de controladores getUsersCars y getUsersMotos

- Feign Client (por Implementar):

## configuracion de los servicios "application.properties"

| nombre microservicio   |  server.port | spring.application.name |
| ---------------------- | ------------ | ----------------------- |
| usuario-servicio       |    8001      |  usuario-servicio       |
| carro-servicio         |    8002      |  carro-servicio         |
| moto-servicio          |    8003      |  moto-servicio          |

## rutas del programa

|Metodo| servicio | que hace | url |
| -----| -------- | -------- | --- |
| GET  | usuario-servicio | lista todos los usuario | http:localhost:8001/usuario |
| GET  | usuario-servicio | lista usuario por id    | http:localhost:8001/usurio/(id) |
| GET  | usuario-servicio | lista los carros del usuario | http://localhost:8001/usuario/carros/(id) |
| GET  | usuario-servicio | lista las motos del usuario | http://localhost:8001/usuario/motos/(id) |
| POST | usuario-servicio | guardar usuario nuevo | http://localhost:8001/usuario |
| POST | usuario-servicio | guardar carro con usuarioId | http://localhost:8001/usuario/carro/(id) |
| POST | usuario-servicio | guardar moto con usuarioId  | http://localhost:8001/usuario/moto/(id) |
| GET  | carro-servicio | lista todos los carros | http://localhost:8002/carro |
| GET  | carro-servicio | listar carro por id | http://localhost:8002/carro/(id) |
| POST | carro-servicio | guardar carro nuevo | http://localhost:8002/carro |
| GET  | moto-servicio | lista todas las motos | http://localhost:8003/moto |
| GET  | moto-servicio | listar moto por id | http://localhost:8003/moto/(id) |
| POST | moto-servicio | guardar moto nueva | http://localhost:8003/moto |

