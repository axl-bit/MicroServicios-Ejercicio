# MicroServicios-Ejercicio

Esta es la rama "eureka" continuacion del la rama "multiple-instances"

En esta rama se da un paso mas adelante en los microservicios implmentando el instanciamiento multiple de un servicio.

## cambios realizados


- Implementación de instanciamiento multiple:

- cambios realizados en eureka-servicio (config-data):
	- se añadio service-url => default-zone "http://${eureka.instance.hostname}:${server.port}/eureka/ "

- cambios realizados en usuario, carro y moto (config-data):
	- se cambiaron los puertos de 8001, 8002 y 8003. A unos que se actaulizan de forma random en el instance-id  "${PORT:${SERVER_PORT:0}}"
	- se agrego instance => instance-id "${spring.application.name}:${spring.application.instance_id:${random.value}}"

- cambios realizados en config-servicio:
	- se cambio defaul-label: eureka => multiple-instances

actualmente se encuentra en la version 6.0 "Implementacion de instanciamiento multiple"

## configuracion de los servicios 

El proyecto config-servicio es el encargado de dar los puertos a cada uno de los servicios. Estos puertos se pueden configurar en los archivos *.yaml de la carpeta config-data

#### NOTA: la configuracion de los puerto se realizara en la proxima actualizacion en donde se usara gateway

| nombre microservicio   |  server.port |
| ---------------------- | ------------ |
| usuario-servicio       |    random    |
| carro-servicio         |    random    |
| moto-servicio          |    random    |
| config-servicio        |    8081      |
| eureka-servicio        |    8761      |


