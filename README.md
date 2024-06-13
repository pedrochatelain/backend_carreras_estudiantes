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


- Luego ejecutar el comando ``./mvnw clean install -DskipTests`` (no olvidar el punto al principio)


Para probar el codigo localmente es necesario montar una base de datos y descomentar la linea ``spring.profiles.active=local`` en el archivo ``application.properties``