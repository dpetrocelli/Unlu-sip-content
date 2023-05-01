# tomar como base una imagen de Docker existente (ISO)
FROM openjdk:latest

# Definir el directorio base de trabajo
WORKDIR /usr/src/

# Copiar mi archivo .jar (de mi maq.) al contenedor 
COPY target/my-app-1.0-SNAPSHOT.jar /usr/src/app.jar

# Exponer el puerto que escucha la app (Servidor socket)
EXPOSE 8080

# Comando que corro al iniciar el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]

# COMPILACION ->  docker build . -t miprimerappjava

# CORRIDA -> 
# docker run --rm --name miprimercontenedorcorriendo -p 8080:8080 miprimerappjava   
# docker run --rm --name misegundocontenedorcorriendo -p 8081:8080 miprimerappjava  