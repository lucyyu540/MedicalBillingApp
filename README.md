

DATABASE SETUP

mysql -u root
mysql> create database refundApp; -- Creates the new database
mysql> create user 'user'@'%' identified by 'password'; -- Creates the user
mysql> grant all on refundApp.* to 'user'@'%'; -- Gives all privileges to the new user on the newly created database

OR
override settings in src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/refundApp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=<your username>
spring.datasource.password=<your password>

====================================================

mvn install -- build both front and back-end
java -jar target/demo-0.0.1-SNAPSHOT.jar -- start bundled application

BACKEND
mvn spring-boot:run -- run back-end

FRONTEND
cd client
yarn run serve
