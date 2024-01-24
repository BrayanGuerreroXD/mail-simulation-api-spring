# Mail Simulation API for Mail Simulation

This API is designed to simulate the sending of emails. It only handles one POST endpoint to perform the simulation flow. The application is built in Spring Boot with Java 17.

## Configuration

Make sure you have Java 17 installed on your system before running the application.

## Running

1. Clone the repository:

    ```bash
    git clone https://github.com/BrayanGuerreroXD/mail-simulation-api-spring.git
    ```

2. Navigate to the project directory:

    ```bash
    cd mail
    ```

3. Run the application:

    ```bash
    ./mvnw spring-boot:run
    ```

The application will be available at `http://localhost:8080`.

## Usage

The API exposes a single endpoint to simulate sending mail:

- **Endpoint**: `POST /mail/send`.
  
  JSON request example:

  ```json
  {
    "email": "user@mail.com",
    "plate": "Vehicle's plate",
    "message": "Mail message",
    "parkingName": "Parking's name"
  }
