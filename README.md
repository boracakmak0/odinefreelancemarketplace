# Odine Freelance Marketplace

A Spring Boot RESTful API for managing freelancers, jobs, and job comments.

## Features

-  Create and read freelancers
-  Create, update, view jobs linked to freelancers
-  Comment on jobs, and update/read those comments

## Tools

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

## Endpoints Overview (All in the Postman Collection)

> Freelancers
- 'POST /freelancers' - Add new freelancer
- 'GET /freelancers' - Get all freelancers
- 'GET /freelancers/"id"' - Get freelancer by ID

> Jobs
- 'POST /jobs' - Create a job for a freelancer
- 'GET /jobs' - List all jobs
- 'GET /jobs/"id"' - Get job by ID
- 'GET /jobs/freelancer/"freelancerid"' - Get all jobs for a freelancer
- 'PUT /jobs/"id"' - Update job status/description

> Comments
- 'POST /comments' - Add comment to a job
- 'GET /comments/job/jobid' - Get all comments for a job
- 'PUT /comments/id' - Update comment

## Build & Run

> Must Have Tools
- Java 17 or later
- Maven 3.6+
- An IDE that supports Java language

> Steps
- Clone the repository.
- Build the project using Maven.
- Before running the program, update the src/main/resources/application.properties file and provide your database credentials:
    - spring.datasource.username= [your username here]
    - spring.datasource.password= [your password here]
- Run the main method in src/main/java/org/example/Main.java.
- The backend will start on http://localhost:8080

## Postman Collection

- Included in this repo as Odine_Postman_Collection.json
- You can import this to Postman to test all endpoints.


## Author
- Bora Çakmak
