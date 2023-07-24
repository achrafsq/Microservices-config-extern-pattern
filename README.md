# Microservices-config-extern-pattern
Microservices achritecture with the configuration externalisation pattern

Schemas of the architecture 																																																																																																																																																																																																																																																																					
Payment-Service / admin service / product-service / transfer-service
		Dependencies
				JPA 
				H2
				SPRING WEB
				CLIENT CONFIG 
				LOMBOK
				EUREKA SERVICE
				ACTUATOR
				REST REPOSITORY
 

application.proprerties

Externalisation du ficher de config
Name of the application that will display on the registry service
Configserver : http://localhost:8888 Externalisation du ficher de config
Managment.endpoints.web.exposure.include=*  actuator  live binding param 
spring.application.name=payment-service
spring.cloud.config.enabled=true
spring.config.import=configserver:http://localhost:8888 
management.endpoints.web.exposure.include=*


config-Service
		Dependencies
	
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
application.proprerties
creating new folder “cloud-config” contains our configuration files / we use git repository directly.
server.port=8888
spring.cloud.config.server.git.uri=file://${user.home}/cloud-config
create the folder on the local desktop
  





Git status   git add .
 
Git commit -m “config1”
That will help us know the changes of files
 







http://localhost:8888/application /master
 

http://localhost:8888/payment-service/master    to check config on the endpoint payment-service 
 

registry-Service
		Dependencies
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
application.proprerties
bootstrap the configuration
spring.application.name=eureka-service
spring.cloud.config.enabled=true
spring.config.import=configserver:http://localhost:8888

 

config-Service
		Dependencies
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

<!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-netflix-hystrix -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    <version>2.2.10.RELEASE</version>
</dependency>

application.proprerties
spring.application.name=api-getway

application.yml
spring:
  cloud:
    gateway:
      routes:
        - id: r1
          uri: http://localhost:8062/
          predicates:
            - Path=/customers/**
        - id: r2
          uri: http://localhost:8063/
          predicates:
            - Path=/products/**
        - id: r3
          uri: http://localhost:8064/
          predicates:
            - Path=/admins/**
    discovery:
      enabled: false
server:
  port: 8899
route r1 , r2 , r3 specified for  services  each services have some paths
 

Discovery : true    will make service discovred by eureka service-registry

