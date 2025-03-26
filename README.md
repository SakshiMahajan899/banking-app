# Bank Assignment

## Assignment

The assignment implement a Bank application using Spring boot that contains the operations for opening a new “current account” of already existing customers.

## Tools and Technology

- Kotlin
- Java 21
- Maven 3.5.1
- Git
- Docker
- Spring Framework
- Mockito
- Spring Security
- SLF4J (For logging)
- OpenApi Documentation
- Postman

## Steps to run the Bank Application

There are several ways to run a Spring Boot application on your local machine.

### Method 1: Using Docker

- Open cmd prompt and run
  docker pull sakshi899/banking-app:v1.0.0
- After image pull run the application by using below commad
  docker run -p 8080:8080 sakshi899/banking-app:v1.0.0

### Method 2: Using Git and Docker

- Clone the repository:
    - git clone https://github.com/SakshiMahajan899/banking-app.git
    - cd banking-app
- Docker compose file that can be run with **docker-compose up** which should start up a application
  at port 8080 (including dependencies like a database)

### Access the application:

- Application is up and running on localhost at port 8080 you can execute below metioned endpoints using
  POSTMAN.

- Also at the browser enter - http://localhost:8082/ which shows the beautiful screen and by using customerId - **123e4567-e89b-12d3-a456-426614174000** you can continue operations

## Spring Security

- This application uses the Spring Security for authentication
- Use **Basic Auth** inside Authorization and pass **Username** - user and **Password** - password

## Endpoints

<table>
<tr>
   <td>Endpoint</td><td>Description</td><td>Request body example</td><td>Response body example</td>
</tr>

<tr>
   <td> GET /api/v1/customer/123e4567-e89b-12d3-a456-426614174000 </td>
   <td>

      get the user information showing Name, Surname, balance, and
transactions of the accounts.

   </td>
   <td>

   ```json
   
   ```

   </td>
   <td>

   ```json
        {
    "firstName": "John",
    "lastName": "Doe",
    "accounts": [
        {
            "accountID": "223e4567-e89b-12d3-a456-426614174000",
            "balance": 1000,
            "transactions": [
                {
                    "id": "323e4567-e89b-12d3-a456-426614174000",
                    "accountID": "223e4567-e89b-12d3-a456-426614174000",
                    "amount": 1000,
                    "timestamp": "2025-03-25T23:34:44.775",
                    "status": "SUCCESS"
                }
            ]
        }
    ]
}
   ```

   </td>
</tr>


<tr>
   <td>POST /api/v1/open-account?customerID=123e4567-e89b-12d3-a456-426614174000&initialCredit=500 </td>
   <td>

       POST endpoint which accepts the user information (customerID,
initialCredit).
• Once the endpoint is called, a new account will be opened connected to the user whose ID is
customerID.

   </td>
   <td>

   ```json
        
   ```

   </td>
   <td>

   ```json
      {
    "id": "67e33a296bf91d1ed95f93a1",
    "customerID": "123e4567-e89b-12d3-a456-426614174000",
    "balance": 500
}
   ```

   </td>
</tr>


</table>

## Testing and Validation

### Unit Tests (UT) 

    - As part of testing strategy, i have ensured that all primary use cases are covered by unit test. 
    - This approach guarantees comprehensive validation and reliability of the system.

## Logging and Monitoring

- Proper logging is done to log requests, responses, and errors.
- Used Spring Boot Actuator for monitoring the health of application in production , exposed **/actuator/health** and *
  */actuator/metrics** endpoints for real-time monitoring

## Continuous Integration and Deployment

- Within the banking-app/.github/workflows/ directory, you'll find the ci-cd.yml file, which is used to
  build the code & deploy the application image to dockerHub.
- All the Unit and Integration Test Cases are automatically triggered once pipeline execute.
- Pipeline Link for reference https://github.com/SakshiMahajan899/banking-app/actions/runs/14072438057

    
## Custom Exceptions for Better Error Handling

 - Instead of generic exceptions, I have specific exceptions for better debugging.





