FROM openjdk:17-alpine

COPY target/TelegramZateyBot*.jar /zateybot.jar

CMD ["java", "-jar", "zateybot.jar"]