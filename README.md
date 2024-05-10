# Shop Management System

Shop Management System is a Java Spring Boot application for managing a shop's inventory, bills, categories, and users.

## Features

- Add, update, and delete products
- Manage categories
- Generate bills
- View user information
- Authentication and authorization (roles: admin, user)
- Search products by name or category
- Calculate day and month balances for users
- Retrieve products by category
- Retrieve categories by name
- ...

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- H2 Database (for development)
- Maven (for dependency management)

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven

### Installation

1. Clone the repository:

git clone <repository-url>


2. Navigate to the project directory:

cd shop-management-system


3. Build the project using Maven:

mvn clean install

4. Run the application:

mvn spring-boot:run


The application will start running at `http://localhost:8080`.

## Usage

### API Endpoints

- `/product`: CRUD operations for products
- `/category`: CRUD operations for categories
- `/bill`: Generate bills
- `/user`: Manage users
- ...

### Documentation

API documentation can be found at `http://localhost:8080/swagger-ui.html`.

## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue if you encounter any problems or have suggestions for improvements.
