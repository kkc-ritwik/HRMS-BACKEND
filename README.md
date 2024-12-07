# Expense Management Module - Backend

This is the backend for the Expense Management module, built with Spring Boot. It includes user authentication, client management, expense tracking, and project management functionalities. Below are the details about setting up and using the module.

## Features
- **User Authentication**: Register, login, password change, profile management, and user deletion.
- **Client Management**: Add, update, delete clients, and send/submit client forms.
- **Expense Management**: Add, update, delete expenses, manage expense attachments, and update expense status.
- **Project Management**: Add, update projects, toggle project status.
- **Cross-Origin Resource Sharing (CORS)**: Configured to allow requests from any origin.

## Technologies Used
- Spring Boot
- Spring Security (for authentication)
- Hibernate (for database interaction)
- MongoDB (or any other DB as required)
- Jackson (for JSON parsing)
- SLF4J (for logging)

## Prerequisites
- Java 17 or above
- Maven 3.6.3 or above
- MongoDB (for database)

## Setting Up the Project

### 1. Clone the Repository
```bash
git clone https://github.com/your-repo/expense-management-backend.git
cd expense-management-backend

2. Build the Project
Use Maven to build the project:
mvn clean install

3. Configure the Application
Before running the project, configure your MongoDB connection details in the application.properties or application.yml file:

4. Running the Application
You can run the application by using:

API Documentation
Authentication Endpoints
Method	Endpoint	Description
POST	/v1/auth/register	Register a new user
POST	/v1/auth/login	Login user and generate JWT token
GET	/v1/auth/profile	Get logged-in user's profile
POST	/v1/auth/change-password	Change password for the logged-in user
GET	/v1/auth/users	Get list of all users
POST	/v1/auth/change-password/{userId}	Change password for specific user
DELETE	/v1/auth/delete-user/{userId}	Delete a user
Client Endpoints
Method	Endpoint	Description
POST	/v1/clients	Add a new client
GET	/v1/clients	Get all clients
PUT	/v1/clients/{id}	Update a client
DELETE	/v1/clients/{id}	Delete a client
POST	/v1/clients/send-form	Send client form via email
POST	/v1/clients/submit-form	Submit client form
Expense Endpoints
Method	Endpoint	Description
POST	/v1/expenses	Add a new expense (supports attachments)
GET	/v1/expenses	Get all expenses
GET	/v1/expenses/{id}	Get expense by ID
GET	/v1/expenses/{id}/attachment	Get expense attachment
PUT	/v1/expenses/{id}	Update an expense
PUT	/v1/expenses/{id}/status	Update the status of an expense
DELETE	/v1/expenses/{id}	Delete an expense
Project Endpoints
Method	Endpoint	Description
POST	/v1/projects	Add a new project
GET	/v1/projects	Get all projects
PUT	/v1/projects/{id}	Update a project
PATCH	/v1/projects/{id}/toggle-status	Toggle the project status (active/inactive)
DTOs (Data Transfer Objects)
ReqRes: Handles request and response data for authentication services.
PasswordChangeDto: Handles password change operations.
ClientDto: Represents client data for add and update operations.
ExpenseDto: Represents expense data, including optional attachments.
ProjectDto: Represents project data for add and update operations.
Services
AuthenticationService
Handles user registration, login, profile retrieval, and password change functionalities.

UserService
Responsible for user-related operations such as retrieving, updating, and deleting users.

ClientService
Handles all operations related to client management, including sending and submitting client forms.

ExpenseService
Handles expense-related operations, including adding expenses, managing attachments, updating statuses, and deleting expenses.

ProjectService
Manages project-related operations, including adding, updating, and toggling project statuses.

Error Handling
The controllers have exception handlers to catch and respond with appropriate HTTP status codes and messages in case of errors.

Logging
Logging is implemented using SLF4J and can be found throughout the application to track errors and important flow checkpoints.

License
This project is licensed under the MIT License. See the LICENSE file for details.

This README provides detailed instructions on setting up, running, and understanding the Expense Management module, covering all controllers and their respective endpoints. You can copy the entire content in one go.

