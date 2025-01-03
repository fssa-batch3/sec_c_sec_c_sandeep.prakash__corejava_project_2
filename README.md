# BookandPlay Backend CoreJava Project

# BookandPlay Backend
 This repository includes backendworks core java
# Book and Play 
Hi This project was developed using HTML, CSS,JavaScript,Java ,MYSql,Servlet,JDBC and JSP
### Good Practices
- Writing TestCases
- Maintained Folder Structure
- Used Prepared Statement
- Both Frontend and backend validation
- Sending data using post method 
- Hashed the password in the database

### Code Quality
SonarCloud
Code Smells, Bugs

### Tools
GIT, GitHub, GitHub Issues
Eclipse, Tomcat, Maven, VSCode


### BookandPlay Project Hosting on AWS
Deployed the BookandPlay project on AWS to explore cloud hosting.
Unhosted it due to high costs, gaining insights into cloud resource management and cost optimization.



Welcome to the **Book and Play** platform!

## Overview

**Book and Play** is a platform designed to assist individuals in reserving sports facilities and connecting with fellow players. The website offers the following key functionalities:

### Website Functionality

Users can easily reserve sports grounds through an intuitive and user-friendly interface. The booking process is designed to be straightforward and efficient.

### Community Building

**Book and Play** aims to foster a sense of community among sports enthusiasts. Users can find fellow players who share similar interests and connect with them for sports activities.

### Communication Tool

To enhance user interaction, the platform provides a chat feature. Users can communicate with each other.





| Table: Ground          |             |                   |               |                 |              |                  |               |             |                     |                               |
|------------------------|-------------|-------------------|---------------|-----------------|--------------|------------------|---------------|-------------|---------------------|-------------------------------|
| Column                 | Data Type   | Nullable          | Auto Increment| Primary Key     | Foreign Key  | References       |               |             |                     |                               |
| id                     | int         | NOT NULL          | YES           | YES             | NO           |                  |               |             |                     |                               |
| groundName             | varchar(255)| NOT NULL          | NO            | NO              | NO           |                  |               |             |                     |                               |
| groundMainArea         | varchar(255)| NOT NULL          | NO            | NO              | NO           |                  |               |             |                     |                               |
| groundAddress          | varchar(255)| NOT NULL          | NO            | NO              | NO           |                  |               |             |                     |                               |
| groundLocationLink     | TEXT        | NOT NULL          | NO            | NO              | NO           |                  |               |             |                     |                               |
| district               | varchar(100)| NOT NULL          | NO            | NO              | NO           |                  |               |             |                     |                               |
| startTime              | TIME        | NOT NULL          | NO            | NO              | NO           |                  |               |             |                     |                               |
| endTime                | TIME        | NOT NULL          | NO            | NO              | NO           |                  |               |             |                     |                               |
| groundRules            | text        | YES               | NO            | NO              | NO           |                  |               |             |                     |                               |
| price                  | decimal(10,2)| NOT NULL          | NO            | NO              | NO           |                  |               |             |                     |                               |
| increasingPriceForExtra| decimal(10,2)| NOT NULL          | NO            | NO              | NO           |                  |               |             |                     |                               |
| courtsAvailable        | int         | NOT NULL          | NO            | NO              | NO           |                  |               |             |                     |                               |

| Table: GroundImages    |             |                   |               |                 |              |                  |               |             |                     |                               |
|------------------------|-------------|-------------------|---------------|-----------------|--------------|------------------|---------------|-------------|---------------------|-------------------------------|
| Column                 | Data Type   | Nullable          | Auto Increment| Primary Key     | Foreign Key  | References       |               |             |                     |                               |
| id                     | int         | NOT NULL          | YES           | YES             | NO           |                  |               |             |                     |                               |
| groundId               | INT         | NOT NULL          | NO            | NO              | YES          | Ground(id)       |               |             |                     |                               |
| imageUrl               | TEXT        | NOT NULL          | NO            | NO              | NO           |                  |               |             |                     |                               |

| Table: SportsAvailable |             |                   |               |                 |              |                  |               |             |                     |                               |
|------------------------|-------------|-------------------|---------------|-----------------|--------------|------------------|---------------|-------------|---------------------|-------------------------------|
| Column                 | Data Type   | Nullable          | Auto Increment| Primary Key     | Foreign Key  | References       |               |             |                     |                               |
| id                     | int         | NOT NULL          | YES           | YES             | NO           |                  |               |             |                     |                               |
| groundId               | INT         | NOT NULL          | NO            | NO              | YES          | Ground(id)       |               |             |                     |                               |
| sportName              | VARCHAR(100)| NOT NULL          | NO            | NO              | NO           |                  |               |             |                     |                               |





### ER Diagram Data Base of Book and play

<img width="988" alt="booandplayer" src="https://github.com/user-attachments/assets/2b8b59d4-f59a-437b-93c7-d85734e4a2db">

