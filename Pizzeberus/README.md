# Pizzeberus

## Installation guide

### Install dependencies
`mvn clean install`

### Containers
Be sure to use the command line in the proper directory
`docker compose up --build -d`

This will initialize the databases, Zipkin and Rabbitmq

## Config server
Here we have storage all the application.yml of our project.
For the same reason this will be the first to be initialized.
1. Run the config-server. Go to [ApplicationConfigServer.java](config-server/src/main/java/com/hiberus/ApplicationConfigServer.java) and press the run button.


## Eureka server
Its primary function is to allow microservices to register with it when they are started and,
in turn, provides a way for other microservices to find and communicate with them.
2. Run the eureka-server. Go to [ApplicationEurekaServer.java](eureka-server/src/main/java/com/hiberus/ApplicationEurekaServer.java) and press the run button.

## Gateway server
Spring Cloud Gateway manages request routing in a microservices' environment.
He is available to route using the eureka server the requests for the services instead of changing the base url.
3. Run the gateway-server. Go to [ApplicationGatewayServer.java](gateway-server/src/main/java/com/hiberus/ApplicationGatewayServer.java) and press the run button.

## Services
Now we can initialize [one by one](#one-by-one) the services **or** use a [*Compound*](#compound) we can edit the
settings

#### One by One
4. Run the pizzaRead. Go to [ApplicationPizzaRead.java](pizzaRead/src/main/java/com/hiberus/ApplicationPizzaRead.java) and press the run button.
5. Run the pizzaWrite. Go to [ApplicationPizzasWrite.java](pizzaWrite/src/main/java/com/hiberus/ApplicationPizzasWrite.java) and press the run button.
6. Run the user-crud. Go to [ApplicationUsers.java](user-crud/src/main/java/com/hiberus/ApplicationUsers.java) and press the run button.

### Compound
Go to Run/Debug then add a new configuration select Compound name it as [Pizzeberus](#pizzeberus) :) and add the services in order.
* We can also add here the Config server, eureka server and the gateway server, so we can initialize all in on click.

## Documentation
Users: http://localhost:8083/swagger-ui.html        
PizzaRead: http://localhost:8082/swagger-ui.html    
PizzaWrite: http://localhost:8081/swagger-ui.html   

Author
[Lisardo Carretero Colmenar](https://github.com/LCarretero/LCarretero)

