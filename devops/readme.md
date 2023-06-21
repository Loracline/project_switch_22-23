# Project Management System - DevOps #

## Introduction

Applying DevOps fundamental concepts to our Project Management System project is crucial for its success, as it will 
have a profound impact on our development and delivery processes. By leveraging Infrastructure as Code, Git for 
collaboration, and utilizing multiple hosts in Jenkins and Docker, we can achieve greater scalability, reliability, and 
flexibility in our project. The Jenkins pipeline automates crucial stages such as application assembly, testing, and 
artifact publishing, resulting in faster feedback loops and shorter release cycles. This automation enhances our ability 
to deliver high-quality software consistently. Additionally, integrating Docker ensures portability and consistency 
across different environments, further facilitating efficient and reliable deployments. Ultimately, implementing a 
Jenkins pipeline in our Project Management System project enables us to optimize our development workflow, enhance 
collaboration, and streamline the delivery process, ultimately resulting in a more efficient and successful project.


## Prerequisites

The assignment focuses on implementing a Jenkins pipeline for our Project Management System project, incorporating key 
DevOps principles.

### General Requirements

#### **Infrastructure as Code**
- Our solution must be reproduced in any system by cloning our repository.
- Images uploaded to Docker Hub must be used to facilitate easy setup and deployment.

#### **Git / Bitbucket**
- As it has been done so far, tasks and issues must be created to track the work of all team members.

### Jenkins Requirements

For the Jenkins solution, two hosts must be set up in a "docker-compose.yml" file: 
- **Control host** This host must have Jenkins installed, acting as the central control point for our pipeline.
- **Dind host** This host should have Docker-in-Docker installed and will be used by Jenkins for container operations. 

The Jenkins pipeline must have the following stages:
- **Assemble** This stage involves compiling and building our application, ensuring that it is ready for testing and 
deployment.
- **Generate and Publish Javadoc** In this stage, Javadoc documentation must be generated for our code and published 
within Jenkins. This documentation helps in understanding the codebase and its usage. 
- **Execute Tests (Unit / Integration) and Publish Results** This stage focuses on running both unit and integration 
tests for our application. The test results, including any code coverage metrics, should be published in Jenkins. This 
helps assess the quality and reliability of our code. 
- **Publish Distributable Artifacts** In this stage, distributable artifacts such as .war files, must be published. 
These artifacts are the final outputs of our build and can be used for deployment. 
- **Publish Image** Our pipeline must integrate with Docker, which means that an image of our application must be built 
and published in Docker Hub. The Docker image must be appropriately tagged to reflect the build version, ensuring 
version control. Additionally, it must also be tagged as "latest" to facilitate easy access to the most recent version
of the image. 

### Docker Requirements

Regarding the deployment solution, three hosts must be defined and managed in another "docker-compose.yml" file:
#### **Web host** 
This host must be responsible for presenting the Project Management System web application to users. The 
App container is deployed on the Web host.

**App container** is deployed on the Web host:
- Uses a Tomcat server
- Connects to the Database container to persist the information
- Uses the image published in Docker Hub from Jenkins (see Jenkins Requirements)

#### **DB host** 
This host will store the application's database, using MariaDB or MySQL. The database should be stored in a host volume 
for data persistence.

**Database container** is deployed on the DB host:
- Uses MariaDB or MySQL
- Database information stored in a host volume, ensuring that data persists even if the container is stopped or removed

#### **PHPMyAdmin host** 
This host must enable view and manage the project database. It connects to the DB host for accessing the database. 

**PhpMyAdmin container** is deployed on the PHPMyAdmin host:
- Separate service in the "docker-compose.yml" file
- Connects to the Database container and provides an interface for database management

System parameters must be stored in a unique ".env" file. 


## Tutorial

### Step 1: 

### Step 2:

## Conclusion