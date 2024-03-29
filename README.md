# Casestudy:

myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 
The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 
Your goal is to create a RESTful service that can retrieve product and price details by ID. The URL structure is up to you to define, but try to follow some sort of logical convention.

## Usecases:
Build an application that performs the following actions: 
•	Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 

•	Example product IDs: 15117729, 16483589, 16696652, 16752456, 15643793) 

•	Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}

•	Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com, but let’s just pretend this is an internal resource hosted by myRetail)  

•	Example: http://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics

•	Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response.  

•	BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store.  

# Build:
Make sure JAVA_HOME points to JDK 8 before run gradle build.

## Linux or macOS:
```./gradlew clean build```
## Windows:
```gradlew clean build```

## Install Docker:
Follow below link to install docker.

```https://docs.docker.com/install/```

## Install Docker Compose:
```https://docs.docker.com/compose/install/```

## Docker Build:
After gradle build completed run below Docker command to make image of the artifacts.

```docker build .```

# Run:
## Standalone:
Use below command to run MongoDB Docker container at port 27017.

```docker run -d -p 27017:27017 --name mongodb mongo:latest```

Use below command to run myRetail application as standalone using above mongo container.

```java -jar build/libs/myRetail.jar```

## Docker Compose:
Below command will run application using docker compose. This will run both MongoDB image and the MyRetail application image together.

```docker-compose up```

```docker-compose up --build``` (perform docker build also)

# Test:
To test API using Swagger UI access below url when application is successfully started.

```http://localhost:8082/swagger-ui.html```


