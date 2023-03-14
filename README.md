# MicroServicios-Ejercicio

Este es un ejercicio que estoy realizando para usar lo aprendido en la implementacion de microservicios

En este ejercicio encontrara las practicas:
- Manejo de DTO, para las transferencias de datos.
- CamelCase, para el nombramiento de variables, metodos, etc.
- Comentarios entendibles, estos explican el funcionamiento de los metodos y parte poco usuales

este proyectos cuenta con las dependecias:

- spring boot devtools
- spring data jpa
- h2 database //es una base de datos en memoria
- spring web

actualmente se encuentra en la version 2.0 "Comunincacion Por Rest Template"

## Tipos de comunicacion

- Rest Template (implementado): Implementacion de los servicios getCars y getMotos | Implementacion de controladores getUsersCars y getUsersMotos

- Feign Client (por Implementar):

## configuracion de los servicios "application.properties"

| nombre microservicio   |  server.port | spring.application.name |
| ---------------------- | ------------ | ----------------------- |
| usuario-servicio       |    8001      |  usuario-servicio       |
| carro-servicio         |    8002      |  carro-servicio         |
| moto-servicio          |    8003      |  moto-servicio          |

