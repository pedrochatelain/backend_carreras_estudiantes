## Hosting & keep-alive

- La app se encuentra desplegada en https://render.com/
 

- La base de datos se almacena en https://aiven.io/


- Utiliza https://keepalive.dashdashhard.com/ para mantener la instancia del servidor activa

## Servicios
- Para probar los servicios utilizar el siguiente postman workspace https://www.postman.com/dark-star-644492/workspace/carreras-estudiante-worskpace

## Tests
- Para correr los tests, ejecutar el siguiente comando

    `./mvnw clean test -Dspring.profiles.active=test`

## A tener en cuenta

⚠️ Si se van a realizar cambios en el código, tener en cuenta los dos siguientes puntos antes de subir las modificaciones al repositorio remoto (GitHub)

- Desactivar la linea ``spring.profiles.active=local`` en el archivo ``application.properties`` (o sea, dejarla comentada con el simbolo # al principio de la linea)


- Luego ejecutar el comando

  ``./mvnw clean install -DskipTests``

  (no olvidar el punto al principio)

## Correr aplicación localmente

Si se quiere utilizar la applicación, es necesario apuntar a una base de datos local.

  Para ello hay que descomentar la linea ``spring.profiles.active=local`` en el archivo ``application.properties``, haciendo que de esta manera se apunte a una base de datos embebida (*H2 Database Engine*).

Una vez hecho esto, es posible correr la aplicación.

### GUI para interactuar con H2 Database Engine
Es posible acceder a la base de datos local desde un navegador web http://localhost:8080/h2-console (configurando el login como se muestra en la siguiente imagen)

![configuracion h2](src/main/resources/configuracion_database_h2.png)


## TO DO

- Allow attributes "edad", "dni", "fecha_nacimiento" to be null in database
- Allow multiple constructors to create student
- Store "edad" when saving student (calculate it from birthdate)  
- Display custom error responses when DateTimeParseException occurs