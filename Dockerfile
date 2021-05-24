
#spring boot layer TODO

# Alpine Linux with OpenJDK JRE
FROM openjdk:11.0.11-oraclelinux7

COPY target/dbexo-0.0.1-SNAPSHOT.jar /dbexo.jar

#docker build -t testrestjpa:latest .
#docker run -p 8080:8080 --add-host=database:<host-ip>
#docker run -d --net=host

#spring system env key has to be uppercase with _ instead of .
#so  SPRING_DATASOURCE_URL here is equivalent to spring.datasource.url in the application.properties

#ENV SPRING_DATASOURCE_URL="jdbc:mysql://database:3306/test_db?useLegacyDatetimeCode=FALSE&serverTimezone=Europe/Paris&autoReconnect=true&useUnicode=true&useCompression=true&characterEncoding=utf8&jdbcCompliantTruncation=false&autoCommit=true"
#ENV SPRING_DATASOURCE_USERNAME="java"
#ENV SPRING_DATASOURCE_PASSWORD="java789"
#ENV SPRING_DATASOURCE_DRIVER-CLASS-NAME="com.mysql.cj.jdbc.Driver"
#issue with mysqldialect
#ENV SPRING_JPA_DATABASE-PLATFORM="org.hibernate.dialect.MySQLDialect"

ENV SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/test_db"
ENV SPRING_DATASOURCE_USERNAME="postgres"
ENV SPRING_DATASOURCE_PASSWORD="postgres"
ENV SPRING_DATASOURCE_DRIVER-CLASS-NAME="org.postgresql.Driver"
ENV SPRING_JPA_DATABASE-PLATFORM="org.hibernate.dialect.PostgreSQLDialect"



# run application with this command line
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=default", "/dbexo.jar"]