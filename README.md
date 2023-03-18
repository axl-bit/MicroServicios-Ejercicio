# MicroServicios-Ejercicio

Esta es la rama "config-server" continuacion del la rama "main"

En esta rama se da un paso mas adelante en los microservicios configurandolos usando git

## cambios realizados

- proyecto config-servicio (agregado):
	- dependencias:
		- Spring Boot DevTools
		- Config Server
	- se cambio application.properties => application.yaml

- cambios en los servicios usuario, carro y moto:
	- dependencias:
		- Config Client
		- Bootstrap //de spring cloud, puedes encontrarla en maven repositorio
	- se cambio application.properties => bootstrap.yaml

actualmente se encuentra en la version 4.0 "configuracion de servicios con Git"

## configuracion de los servicios 

El proyecto config-servicio es el encargado de dar los puertos a cada uno de los servicios. Estos puertos se pueden configurar en los archivos *.yaml de la carpeta config-data

| nombre microservicio   |  server.port |
| ---------------------- | ------------ |
| usuario-servicio       |    8001      |
| carro-servicio         |    8002      |
| moto-servicio          |    8003      |

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

|Metodo| servicio | que hace | url |
| -----| -------- | -------- | --- |
| GET  | moto-servicio | lista todas las motos | http://localhost:8003/moto |
| GET  | moto-servicio | listar moto por id | http://localhost:8003/moto/(motoId) |
| POST | moto-servicio | guardar moto nueva | http://localhost:8003/moto |

