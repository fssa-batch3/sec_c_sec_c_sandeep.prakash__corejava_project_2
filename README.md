# sec_c_sec_c_sandeep.prakash__corejava_project_2
# BookandPlay Backend CoreJava Project
## Milestone-1
## In  Milestone-1, we will  focusing on implementing the backend logic and functionalities to support the Ground crud operations
## Ground Model:
## Design a Ground model to represent the properties of ground, such as groundname, Address, price, district,sportsAvailable,timings,courtsAvailable,Ground Images and Ground type relevent attributes.

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

![image](https://github.com/fssa-batch3/sec_c_sec_c_sandeep.prakash__corejava_project_2/assets/116252886/55e32314-2dde-48ec-910e-a2f64ed81352)

