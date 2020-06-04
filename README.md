# techhunt2020_employees
## TechHunt 2020 Take Home Assessment

This is a simple introduction to compiling Spring Boot Application and MySQL's script in your local machine: 

#### <ins>Things you'll need:</ins> 
* Apache Maven 3.6 or later
* JDK 1.8 or later
* MySQL 5.6 or later

#### <ins>Folder structure:</ins>
| Folder | Description |
|--------|-------------|
| employees | Contains source codes of the Spring Boot application for APIs call |
| sql_scripts | Contains SQL Scripts to run to import schemas |
| sample_CSV | Contains sample CSV files for testing purposes | 

#### <ins>Compiling Spring Boot Application:</ins> 
* Download and unzip the source repository, or clone it using Git: 
```
git clone https://github.com/jinpeiw/techhunt2020_employees.git
```
* Navigate to /employees folder and run mvn clean and package: 
```
mvn clean 
mvn package
```
* Run jar package: 
```
java -jar employees-0.0.1-SNAPSHOT.jar
``` 

#### <ins>Executing SQL Scripts:</ins> 
* Import the script in /sql_scripts folder and execute in your preferred SQL developer tool 

## Scope
This repository contains source codes that should be assessed for TechHunt 2020 Take Home Assessment, attempting on User Story #1 and User Story #2 requirements. 
What is included in this repository: 
1. 2 APIs (Using Spring Boot framework), connecting to
2. MySQL database (Open sourced)

## APIs Catalogue
| S/N | API | Functionality |
|--------|-------------|-----------|
| 1 | POST /users/upload | To upload a CSV file for employees's details |
| 2 | GET /users | To get a list of employees' details |

### <ins>User Story #1 HTTP POST /users/upload</ins>
#### Request

Headers - Content-Type: multipart/form-data
Parameters - 
| Field name | Data type | Description |
|--------|-------------|-----------|
| file | multipart/form-data |  CSV file upload |
#### Response
| Field name | Data type | Description |
|--------|-------------|-----------|
| noOfSuccessRows | number |  indicate number of rows created/updated in CSV |
| successIds | list | show list of IDs successfully created/updated |
| failIds | list | show list of IDs with error in creation/update |

#### Sample Request and Response 
Response 
```
#1 HTTP STATUS 200 OK

{
    "noOfSuccessRows": 9,
    "successIds": [
        "e0001",
        "e0002",
        "e0003",
        "e0004",
        "e0005",
        "e0006",
        "e0007",
        "e0008",
        "e0009"
    ],
    "failIds": []
}

#2 HTTP STATUS 207 Multi-Status
{
    "noOfSuccessRows": 9,
    "successIds": [
        "e0001",
        "e0002",
        "e0003",
        "e0004",
        "e0005",
        "e0006",
        "e0007",
        "e0008",
        "e0009"
    ],
    "failIds": [
        {
            "id": "e0010",
            "message": "Salary entered for e0010 is below $0."
        }
    ]
}

#3 HTTP STATUS 500 
{
    "timestamp": "2020-06-04T17:53:15.213+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "File is empty.",
    "path": "/users/upload"
}
```

### <ins>User Story #2 HTTP GET /users</ins>
#### Request

Headers - Content-Type: multipart/form-data
Parameters - 
| Field name | Data type | Description |
|--------|-------------|-----------|
| minSalary | number |  Filter for minimum salary |
| maxSalary | number | Filter for maximum salary |
| offset | number | Position of the starting point to retrieve in the SQL query|
| limit | number | Total number of items retrieve |
| sort | String | Sort order + sort columns: e.g. +login. Sort order columns include [id, name, login, salary] and sort order include [+. -]|  

#### Response
| Field name | Data type | Description |
|--------|-------------|-----------|
| results | object |  contains list of employees details |
| results.id | String | Id of the employee |
| results.name | String | Name of the employee |
| results.login | String  | Login of the employee |
| results.salary | number | Salary of the employee | 

#### Sample Request and Response 
Response 
```
#1 HTTP STATUS 200 OK
{
    "results": [
        {
            "id": "e0005",
            "login": "voldemort",
            "name": "Lord Voldemort",
            "salary": 523.4
        },
        {
            "id": "e0007",
            "login": "hgranger",
            "name": "Hermione Granger",
            "salary": 0.0
        },
        {
            "id": "e0008",
            "login": "adumbledore",
            "name": "Albus Dumbledore",
            "salary": 34.23
        }
    ]
}

#2 HTTP STATUS 400 Bad request 
{
    "timestamp": "2020-06-04T17:55:36.844+0000",
    "status": 400,
    "error": "Invalid Input Exception",
    "message": "Invalid sorting criteria entered.",
    "path": "/users"
}

```

### Custom Exception Handling w/ Spring's Controller Advice 
#### InvalidInputException - extends Runtime Exception 
* Returning HTTP Status code 400 Bad request 
Example of exception's response: 
``` 
{
    "timestamp": "2020-06-04T17:55:36.844+0000",
    "status": 400,
    "error": "Invalid Input Exception",
    "message": "Invalid sorting criteria entered.",
    "path": "/users"
}
```

### Database Schema
As the requirement provided in the scope is simple and straightforward, the following basic schema is created to fulfil the requirement 

| column | type | nullable | 
|----|----|----|
|id|nvarchar(255) | NO|
|login|nvarchar(255) | NO|
|name|nvarchar(255) | NO|
|salary|double | NO|