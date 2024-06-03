- La app se encuentra desplegada en https://render.com/
 

- La base de datos se almacena en https://aiven.io/


- Utiliza https://keepalive.dashdashhard.com/ para mantener la instancia del servidor activa


- Si se realizan cambios en el código, ejecutar ``./mvnw clean install -DskipTests`` antes de subirlo al repositorio, y comprobar el estado de la linea ``spring.profiles.active=local`` en el archivo ``application.properties`` 


- En la ruta del proyecto se adjuntan los archivos ``LOCAL_carreras_estudiantes.postman_collection.json`` y ``PRODUCTION_carreras_estudiantes_render.postman_collection.json`` para probar los servicios localmente o utilizando la base de datos de producción


- Para probar el codigo localmente es necesario montar una base de datos y descomentar la linea ``spring.profiles.active=local`` en el archivo ``application.properties`` 