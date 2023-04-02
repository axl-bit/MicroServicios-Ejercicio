# MicroServicios-Ejercicio

Esta es la rama "circuit-breaker" continuacion del la rama "gateway"

En esta rama se da un paso mas adelante en los microservicios implmentando zipkin y sleuth, esto para tener seguimiento de logs y ver los el rendimiento de los servicios, y si hay algun problema o se puede mejorar algo, tenerlo mas claro.

## cambios realizados

### Implementación de zipkin-sleuth:

- cambios en usuario-servicio:
	- añadimos dependencias Zipkin Client | Sleuth
- cambios en carro-servicio:
	- añadimos dependencias Zipkin Client | Sleuth
- cambios en moto-servicio:
	- añadimos dependencias Zipkin Client | Sleuth
- descargamos y añadimos al proyecto el jar de zipkin (Link de descarga: https://zipkin.io/pages/quickstart.html  )
- cambios en config-data (usuario - moto - carro):
	- añadimos zipkin al final
- comando para usar zipkin = java -jar zipkin-server-2.24.0-exec.jar (usando el bash de git iniciado "git init")


actualmente se encuentra en la version 9.0 "Implementacion de zipkin y sleuth"

## configuracion de los servicios 

El proyecto config-servicio es el encargado de dar los puertos a cada uno de los servicios. Estos puertos se pueden configurar en los archivos *.yaml de la carpeta config-data

#### NOTA: los puertos de usuario, carro y moto, son ramdom, pero implementando el gateway, todos los puertos se normalizan en el 8080.

| nombre microservicio   |  server.port |
| ---------------------- | ------------ |
| usuario-servicio       |    random    |
| carro-servicio         |    random    |
| moto-servicio          |    random    |
| config-servicio        |    8081      |
| eureka-servicio        |    8761      |
| gateway-servicio		 |	  8080		|
| zipkin				 | 	  9411		|


## rutas del programa

### Servicio de Usuario

|Metodo| servicio | que hace | url |
| -----| -------- | -------- | --- |
| GET  | usuario-servicio | lista todos los usuario | http://localhost:8080/usuario |
| GET  | usuario-servicio | lista usuario por id    | http://localhost:8080/usurio/(usuarioId) |
| GET  | usuario-servicio | lista los carros del usuario | http://localhost:8080/usuario/carros/(usuarioId) |
| GET  | usuario-servicio | lista las motos del usuario | http://localhost:8080/usuario/motos/(usuarioId) |
| GET  | usuario-servicio | listar todos los vehiculos de un usuario | http://localhost:8080/usuario/todos/(usuarioId) |
| POST | usuario-servicio | guardar usuario nuevo | http://localhost:8080/usuario |
| POST | usuario-servicio | guarda un carro y lo asocia con el usuario | http://localhost:8080/usuario/carro/(usuarioId) |
| POST | usuario-servicio | guardar una moto y lo asocia con el usuario  | http://localhost:8080/usuario/moto/(usuarioId) |

### Servicio de Carros

|Metodo| servicio | que hace | url |
| -----| -------- | -------- | --- |
| GET  | carro-servicio | lista todos los carros | http://localhost:8080/carro |
| GET  | carro-servicio | listar carro por id | http://localhost:8080/carro/(carroId) |
| POST | carro-servicio | guardar carro nuevo | http://localhost:8080/carro |

### Servicio de Motos

|Metodo| servicio | que hace | url |
| -----| -------- | -------- | --- |
| GET  | moto-servicio | lista todas las motos | http://localhost:8080/moto |
| GET  | moto-servicio | listar moto por id | http://localhost:8080/moto/(motoId) |
| POST | moto-servicio | guardar moto nueva | http://localhost:8080/moto |
