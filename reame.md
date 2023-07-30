
# Project Title

MCB Assessment


## Acknowledgements

 - [Swagger API Documentation](http://localhost:8080/swagger-ui/index.html#/)

## Testing
 - You fine an exported Postman collection with the project, import it in Postman application and run it in the below sequence
  - Sign up
  - Signin
  - Insert of each Model folder
  - then any API from the App folder

## Running

Clone the project

```bash
  git clone https://link-to-project
```

Go to the project directory

```bash
  cd my-project
```

 - Docker (Run the following commands)
 
  ```bash
  docker build -t assessment/arafat .
  ```
 
  ```bash
  docker run -p 8080:8080 --name assessment assessment/arafat:latest
  ```
 - Maven
  
  ```bash 
  mvn clean spring-boot:run 
  ```
  
  
  
