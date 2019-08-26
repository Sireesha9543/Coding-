
#Business Performance Group – Java coding exercise

### RESTful API to simulate simple banking operations.  Some   functionality follows:

* CRUD operations for  accounts.
* 	Support deposits and withdrawals on accounts.
* 	Internal transfer support (i.e. a customer may transfer funds from one account to another).



Technologies used:

* 	 Spring Boot (https://spring.io/projects/spring-boot)
* 	 Spring Security configuration
* 	 RESTful API with Swagger (i.e. https://springfox.github.io/springfox/)
* 	 H2 Database repo
* 	 Docker  
* 	 Maven 

### Run the app in docker container
Build jar 
* 	  mvn clean package 
Run jar 
* 	  mvn spring-boot:run

Test API using Swagger UI (Use admin/admin for login )
* 	  http://localhost:8080/swagger-ui.html
Check H2 Database (For application login use admin/admin )
-  JDBC Url : jdbc:h2:~/test
-  H2 User name: sa
-  H2 password:
* 	  http://localhost:8080/h2


### Run using Docker
Create docker image
* 	  mvn  clean package docker:build
Show all  docker images
* 	  docker images --all
Run docker image
* 	  docker run -p 8080:8080 -t bank-of-users
 
Test API using Swagger UI (For application login use admin/admin )
* 	  http://localhost:8080/swagger-ui.html

Check H2 Database (For application login use admin/admin )
JDBC Url : jdbc:h2:~/test
H2 User name: sa
H2 password:

* 	  http://localhost:8080/h2
