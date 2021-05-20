# Prueba Merqueo

Prueba realizada para ingreso a merqueo

## Architecture overview
![alt text](https://github.com/jorelk19/merqueoTest/blob/main/docs/images/component_model.PNG?raw=true)

## Descripción técnica para el funcionamiento del deep link.

Para el acceso dinamico del detalle, se construlle la url a partir de la pelicula seleccionada y se consume el correspondiente servicio que permite obtener la informacion del detalle; este consumo se hace en la capa de dominio y se obtiene dicha informacion para que sea visualizada en la vista de detalle, adicional si el dispositivo no tiene internet, se consume la base de datos local y se obtiene la informacion que se necesita para mostrar el detalle.

## Breve descripción de la responsabilidad de cada capa propuesta.

Para el desarrollo de esta prrueba se realizo una aplicacion multimodular que consta de las siguientes capas:

* APP
- Capa de aplicacion la cual se encarga de manejar todo lo referente a despliegue de interface de usuario y los respectivos viewmodels que usan las vistas
* DI
- En esta capa se implemento el inyector de dependencias KOIN para permitir realizar funcionalidades mas testeables y desacopladas 
* DOMINIO
- En esta capa se encuentra toda la logica de negocio necesaria para el funcionamiento de la aplicacion, teniendo en cuenta el consumo de repositorios remotos y locales
* REPOSITORIO
- Capa en la cual se relizan los cruds respectivos para el almacenamiento y consulta de informacion que la aplicacion necesita para modo offline
* UTILIDADES
- Capa de utilidades transversales que usa la aplicacion para resar codigo
* ENTIDADES DE NEGOCIO
- Capa encargada de almacenar entidades y contratos de servicios que seran usadas en el consumo de servicios y tratamiento de informacion en la aplicacion

## Application Overview
![alt text](https://github.com/jorelk19/merqueoTest/blob/main/docs/images/listapeliculas.png?raw=true)
![alt text](https://github.com/jorelk19/merqueoTest/blob/main/docs/images/detallepelicula1.png?raw=true)
![alt text](https://github.com/jorelk19/merqueoTest/blob/main/docs/images/detallepelicula2.png?raw=true)
![alt text](https://github.com/jorelk19/merqueoTest/blob/main/docs/images/carritoconproductos.png?raw=true)
![alt text](https://github.com/jorelk19/merqueoTest/blob/main/docs/images/carritoincrementandopelicula.png?raw=true)
![alt text](https://github.com/jorelk19/merqueoTest/blob/main/docs/images/carritoconvariosproductos.png?raw=true)
![alt text](https://github.com/jorelk19/merqueoTest/blob/main/docs/images/carritovacio.png?raw=true)
