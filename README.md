- La app se encuentra desplegada en https://render.com/
 

- La base de datos se almacena en https://aiven.io/


- Utiliza https://keepalive.dashdashhard.com/ para mantener la instancia del servidor activa


- Si se realizan cambios en el código, ejecutar ``./mvnw clean install -DskipTests`` antes de subirlo al repositorio 


- En la ruta del proyecto se adjunta el archivo ``carrera_estudiante_local.postman_collection``
para probar los servicios


- Para probar el codigo localmente es necesario montar una base de datos y descomentar la linea ``spring.profiles.active=local`` en el archivo ``application.properties`` 