# Microservices-config-extern-pattern
Microservices achritecture with the configuration externalisation pattern

Schema of the architecture 

![Schema](https://github.com/achrafsq/Microservices-config-extern-pattern/assets/137192466/ed2d285f-a337-43bc-b460-f6ebbb2a8a98)


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
![image](https://github.com/achrafsq/Microservices-config-extern-pattern/assets/137192466/f72af5bc-8c51-4de7-826f-7d3647979d18)



Externalisation du ficher de config
Name of the application that will display on the registry service
Configserver : http://localhost:8888 Externalisation du ficher de config
Managment.endpoints.web.exposure.include=*  actuator  live binding param 
application.proprerties
		spring.application.name=payment-service
		spring.cloud.config.enabled=true
		spring.config.import=configserver:http://localhost:8888 
		management.endpoints.web.exposure.include=*

config-Service
	Dependencies 
 		pom.xml
		<dependency>
		    <groupId>org.springframework.cloud</groupId>
		    <artifactId>spring-cloud-config-server</artifactId>
		</dependency>
		
		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-test</artifactId>
		    <scope>test</scope>
		</dependency>

creating new folder “cloud-config” contains our configuration files / we use git repository directly.
application.proprerties
		server.port=8888
		spring.cloud.config.server.git.uri=file://${user.home}/cloud-config

create the folder on the local desktop
![image](https://github.com/achrafsq/Microservices-config-extern-pattern/assets/137192466/aa52270f-d8a4-4191-90ab-f5f2718a84ed)

![image](https://github.com/achrafsq/Microservices-config-extern-pattern/assets/137192466/eaa65d1b-83c5-4206-95d0-6c4d1534d68e)

Git status   git add .
![image](https://github.com/achrafsq/Microservices-config-extern-pattern/assets/137192466/7c217337-de44-4e00-bd7e-ee2a83234891)

Git commit -m “config1”
That will help us know the changes of files
![image](https://github.com/achrafsq/Microservices-config-extern-pattern/assets/137192466/7c8a2e36-2393-4b4f-93dc-46e31d9a04f4)

http://localhost:8888/application /master
![image](https://github.com/achrafsq/Microservices-config-extern-pattern/assets/137192466/4527704d-7c3b-4ae8-85a5-28f4c63a1386)

http://localhost:8888/payment-service/master    to check config on the endpoint payment-service
![image](https://github.com/achrafsq/Microservices-config-extern-pattern/assets/137192466/e5956bf3-d08e-42e4-8e27-a4b2523b4087)

![image](https://github.com/achrafsq/Microservices-config-extern-pattern/assets/137192466/93881ee9-403c-4720-b07c-a7ea6b6d2003)

registry-Service
		Dependencies
  			pom.xml
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

![image](https://github.com/achrafsq/Microservices-config-extern-pattern/assets/137192466/288dae53-0a47-4559-ad86-1c4b568fc572)

api-getway
		Dependencies
  			pom.xml
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

![image](https://github.com/achrafsq/Microservices-config-extern-pattern/assets/137192466/ac15d270-c02d-4606-8c27-c5de3da662a0)

Discovery : true    will make service discovred by eureka service-registry

![image](https://github.com/achrafsq/Microservices-config-extern-pattern/assets/137192466/263ba86a-7504-4c66-ba45-2997836f427e)
