# Starbucks-Backend :copyright: [![Build Status] https://travis-ci.org/CoderCouple/starbucks-backend.svg?branch=master
>This repository provides a sample backend code for creating a :coffee: ordering REST application like Starbucks. 

------------------------------------------------------------------------------------------------------------------

## Local Setup: :computer:

### Pre-requisites: 👀
* Java-8
* mysql 5.7 & above
* IntelliJ IDE (Optional)

### How to Compile: :hammer:
* Go to the parent project directory `cd starbucks-backend`
* Run `mvn clean install`

### How to Run: :fire:
* Go to API directory `cd /starbucks-backend/api`
* Run `mvn jetty:run` 

### How to Tets: :wrench:
* Go to parent project directory `cd /starbucks-backend`
* Run `mvn clean test` 

### Database Setup: :white_check_mark:
* Install MySql on your local
* Create a database with name `starbucks`
* Go to the directory `starbucks-backend/common/src/main/resources`
* Change the following parameters in the `config.json` file
    * `"username": "Your Username Here"`
    * `"access": "Your Password Here"`
    

## Docker Setup: :cloud:
* Run following commands to do a docker setup`
    * `docker-compose up -d`
    * Database  Setup (Use Separate terminal)
        * `docker cp ./initDB.sql mysql-server:/`
        * `docker exec -it mysql-server /bin/bash`
        * `mysql < initDB.sql`
    * API  Setup (Use Separate terminal)
        * `docker exec -it starbucks-backend /bin/bash`
        * `mvn clean insall`
        * `cd api && mvn jetty:run`
        
* Run following commands to do a docker tear down
    * `docker compose down`

* For a fresh start
    * `docker compose stop`
    * `docker system prune`

### List of available API:

![SwaggerApiList.png](common/src/main/resources/images/SwaggerApiList.png)

### Database Schema: 

![DBSchemas.png](common/src/main/resources/images/DBSchemas.png)

### How to generate Swagger documentation:
* Run the project
* Install the chrome plugin : https://bit.ly/2wIiKOw
* Go to the url : http://localhost:8080/api/swagger.json
* Click on the plugin icon in the browser  
![PluginIcon.png](common/src/main/resources/images/PluginIcon.png)


