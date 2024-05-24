# Используем официальный образ OpenJDK в качестве базового
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файл pom.xml и папку src в контейнер
COPY pom.xml ./
COPY src ./src

# Копируем Maven Wrapper (если он используется)
COPY .mvn/ .mvn
COPY mvnw ./

# Выполняем команду Maven для скачивания зависимостей и сборки проекта
RUN ./mvnw dependency:go-offline
RUN ./mvnw clean package -DskipTests

# Копируем скомпилированный JAR-файл в контейнер
COPY target/PredictAiSpringRestAPI-0.0.1-SNAPSHOT.jar /app/app.jar

# Открываем порт 8080 для доступа к приложению
EXPOSE 8080

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
