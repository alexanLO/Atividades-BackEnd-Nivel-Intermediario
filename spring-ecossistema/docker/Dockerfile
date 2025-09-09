# Usar uma imagem base Java 17 (ex: eclipse-temurin:21-jdk).
FROM eclipse-temurin:21-jdk-alpine

#Cria um diretorio dentro do container
WORKDIR /app

# Copiar o target/*.jar gerado pelo Maven/Gradle.
COPY ./target/spring-ecossistema-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta 9090 (já que seu projeto roda nela).
EXPOSE 9090

# Rodar o .jar.
CMD ["java", "-jar", "app.jar"]