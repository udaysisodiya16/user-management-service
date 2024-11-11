
# User Management Service

A Spring Boot-based service to manage user accounts, including features for registration, login, profile management, and password reset. The service is secure and scalable, using MySQL as the database and Spring Security for authentication.

---

## Features

1. **User Management**:
  - **Registration**: Allow users to register using email or social media profiles.
  - **Login**: Secure login using credentials with Spring Security.
  - **Profile Management**: View and modify user profile details.
  - **Password Reset**: Reset passwords securely using a token-based system.

2. **Event-Driven Architecture**:
  - Publishes Kafka events for key user actions (e.g., user registration) to notify other services.

3. **Database Integration**:
  - Uses MySQL for storing structured user data.

4. **Security**:
  - Implements password hashing using BCrypt.
  - Secures endpoints using Spring Security.
---

## Technologies Used

- **Spring Boot**: Framework for building the application.
- **Spring Security**: Secures endpoints and handles authentication.
- **Spring Data JPA**: For database interaction.
- **MySQL**: Relational database for storing user data.
- **Spring Kafka**: Publishes and consumes user activity events.
- **Spring Validation**: For validating user input.

---

## Prerequisites

- **Java**: JDK 17.
- **MySQL**: A running MySQL instance.
- **Kafka**: A running Kafka broker.

---

## Setup and Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/udaysisodiya16/user-management-service.git
   cd user-management-service
   ```

2. **Configure MySQL**:
  - Create a database named `user_management_service`.
  - Update the `application.properties` file with your MySQL credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/user_management_service
    spring.datasource.username=root
    spring.datasource.password=root
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    ```

3. **Configure Kafka**:
  - Ensure Kafka is running locally or update the `spring.kafka.bootstrap-servers` property with your Kafka broker address:
    ```properties
    spring.kafka.bootstrap-servers=localhost:9092
    ```

4. **Build and Run**:
  - Build the project:
    ```bash
    mvn clean install
    ```
  - Run the application:
    ```bash
    mvn spring-boot:run
    ```

The service will start on `http://localhost:8081`.

---

## API Endpoints

### **1. Registration**
Allows users to register using their email and password.

- **URL**: `/signup`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "email": "udaysisodiya@gmail.com",
    "password": "Uday123!"
  }
  ```
- **Response**:
  ```json
  {
    "email": "udaysisodiya@gmail.com"
  }
  ```

---

### **2. Login**
Allows users to log in using their credentials.

- **URL**: `/login`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "email": "udaysisodiya@gmail.com",
    "password": "Uday123!"
  }
  ```
- **Response**:
  ```json
  {
    "email": "udaysisodiya@gmail.com"
  }

---

### **3. Profile Management**

#### **Get Profile**
Fetch the user's profile by ID.

- **URL**: `/api/users/profile/{userId}`
- **Method**: `GET`
- **Response**:
  ```json
  {
    "id": 1,
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe"
  }
  ```

#### **Update Profile**
Update user profile details.

- **URL**: `/api/users/profile/{userId}`
- **Method**: `PUT`
- **Request Body**:
  ```json
  {
    "firstName": "Jane",
    "lastName": "Smith"
  }
  ```
- **Response**:
  ```json
  {
    "message": "Profile updated successfully"
  }
  ```

---

### **4. Password Reset**

#### **Request Password Reset**
Initiate a password reset by generating a token.

- **URL**: `/api/users/reset-password-request`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "email": "user@example.com"
  }
  ```
- **Response**:
  ```json
  {
    "message": "Password reset token sent to your email."
  }
  ```

#### **Reset Password**
Reset the password using the token.

- **URL**: `/api/users/reset-password`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "token": "secure-reset-token",
    "newPassword": "newsecurepassword"
  }
  ```
- **Response**:
  ```json
  {
    "message": "Password reset successfully"
  }
  ```

---