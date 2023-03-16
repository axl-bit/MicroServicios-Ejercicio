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

- Rest Template (implementado):
	- se agrego ResTemplateConfig, donde se creo la intancia para la comunicacion
	- Se implementaron getCars y get Motos en UsuarioServicio en UsuarioServicio
	- Se implementaron getUsersCars y getUsersMotos en UsuarioController

- Feign Client (implementado):
	- se agrego el marcado @EnableFeignClient en UsuarioSerivicioApplication.java 
	- se agregaron las interfaces CarroFeignClient y MotoFeignClient
	- se agregaron saveCar, saveMoto y getUserVehicles en UsuarioServicio
	- se agregaron saveCar, saveMoto y getUserVehicles en UsuarioController

## configuracion de los servicios "application.properties"

| nombre microservicio   |  server.port | spring.application.name |
| ---------------------- | ------------ | ----------------------- |
| usuario-servicio       |    8001      |  usuario-servicio       |
| carro-servicio         |    8002      |  carro-servicio         |
| moto-servicio          |    8003      |  moto-servicio          |

## rutas del programa

### Servicio de Usuario

|Metodo| servicio | que hace | url |
| -----| -------- | -------- | --- |
| GET  | usuario-servicio | lista todos los usuario | http://localhost:8001/usuario |
| GET  | usuario-servicio | lista usuario por id    | http://localhost:8001/usurio/(usuarioId) |
| GET  | usuario-servicio | lista los carros del usuario | http://localhost:8001/usuario/carros/(usuarioId) |
| GET  | usuario-servicio | lista las motos del usuario | http://localhost:8001/usuario/motos/(usuarioId) |
| GET  | usuario-servicio | listar todos los vehiculos de un usuario | http://localhost:8001/usuario/todos/(usuarioId) |
| POST | usuario-servicio | guardar usuario nuevo | http://localhost:8001/usuario |
| POST | usuario-servicio | guarda un carro y lo asocia con el usuario | http://localhost:8001/usuario/carro/(usuarioId) |
| POST | usuario-servicio | guardar una moto y lo asocia con el usuario  | http://localhost:8001/usuario/moto/(usuarioId) |

### Servicio de Carros

|Metodo| servicio | que hace | url |
| -----| -------- | -------- | --- |
| GET  | carro-servicio | lista todos los carros | http://localhost:8002/carro |
| GET  | carro-servicio | listar carro por id | http://localhost:8002/carro/(carroId) |
| POST | carro-servicio | guardar carro nuevo | http://localhost:8002/carro |

### Servicio de Motos

| GET  | moto-servicio | lista todas las motos | http://localhost:8003/moto |
| GET  | moto-servicio | listar moto por id | http://localhost:8003/moto/(motoId) |
| POST | moto-servicio | guardar moto nueva | http://localhost:8003/moto |

