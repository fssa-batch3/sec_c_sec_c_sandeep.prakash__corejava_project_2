# sec_c_sec_c_sandeep.prakash__corejava_project_2
# BookandPlay Backend Core Java Project
# Milestone -1
# In Milestone-1, we will focus on implementing the backend logic and functionalities to support the Ground CRUD operations.
# Ground Model:
# Design a Ground model to represent the properties of a product, such as groundname, Address, price, district,sportsAvailable,timings,courtsAvailable images list and Ground type relevant attributes.

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


![Screenshot (212)](https://github.com/fssa-batch3/sec_c_sec_c_sandeep.prakash__corejava_project_2/assets/116252886/ec0ecbb8-e8a6-47e4-a097-b5ca66b0accb)
