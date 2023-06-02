# ProjectWebController

Controller class for handling requests related to projects.

## Constructor

### ProjectWebController

Constructs a new ProjectController with the specified services.

#### Parameters

- `projectCreationService` (ProjectCreationService): The service responsible to create a new project.
- `projectService` (ProjectService): The service responsible to manipulate a project.
- `projectListService` (ProjectListService): The service responsible to retrieve a list of all projects.

## Endpoints

### POST /projects

Registers and saves a project based on the provided ProjectCreationDto.

#### Request

- HTTP Method: POST
- Path: `/projects`
- Request Body: ProjectCreationDto (JSON)

> `projectName` (string, required): The name of the project. <br>
> `projectDescription` (string, required): A brief description of the project. <br>
> `businessSectorId` (string, required): The name of the business sector to which the project belongs.
> `customerId` (string, required): The name of the customer for whom the project is being created. <br>
> `typologyId` (string, required): The name of the project typology.

#### Responses

- Status Code: 201 (Created)
    - Response Body: ProjectCreationDto (JSON)
  
> `projectName` (string, required): The name of the project. <br>
> `projectDescription` (string, required): A brief description of the project. <br>
> `businessSectorId` (string, required): The name of the business sector to which the project belongs.
> `customerId` (string, required): The name of the customer for whom the project is being created. <br>
> `typologyId` (string, required): The name of the project typology.

- Status Code: 409 (Conflict)
    - Response Body: Error message (string)


| Status Code | Response Body             | Description                                           |
|-------------|---------------------------|-------------------------------------------------------|
| 201         | ProjectCreationDto (JSON) | The project was created successfully.                 |
| 400         | Error message (string)    | Project name cannot be null.                          |
| 400         | Error message (string)    | Project name cannot be empty.                         |
| 400         | Error message (string)    | Project name cannot be blank.                         |
| 400         | Error message (string)    | Business sector id Index 1 out of bounds for length 1 |
| 400         | Error message (string)    | Typology id Index 1 out of bounds for length 1        |
| 400         | Error message (string)    | The project description must not be empty             |
| 400         | Error message (string)    | The project description must not be blank             |
| 400         | Error message (string)    | The project description must not be null              |
| 400         | Error message (string)    | Customer id Index 1 out of bounds for length 1        |

### GET /projects

Retrieves a list of all projects.

#### Request

- HTTP Method: GET
- Path: `/projects`
- Request Body:
    -  {}

#### Responses

- Status Code: 200 (OK)
    - Response Body: List<ProjectDto> (JSON)
> [ <br>
> &emsp; `code` (string): The project code. <br>
> &emsp; `projectName` (string): The name of the project. <br>
> &emsp; `customerName` (string): The name of the customer.,
> &emsp; `status` (string): The status of the account., 
> &emsp; `startDate` (string): The start date of the project.,
> &emsp; `endDate` (string): The end date of the project.,
> &emsp; ... <br>
> ]

- Status Code: 400 (Bad Request)
    - Response Body: Error message (string)

- Status Code: 404 (Not Found)
    - Response Body: Error message (string)

| Status Code | Response Body            | Description                              |
|-------------|--------------------------|------------------------------------------|
| 200         | List\<ProjectDto> (JSON) |                                          |


### GET /{code}/productBacklog

Retrieves a list of all user stories.

#### Request

- HTTP Method: GET
- Path: `/{code}/productBacklog`
- Request Body:
    -  {}

#### Responses

- Status Code: 200 (OK)
    - Response Body: List\<UserStoryDto> (JSON)
> [ <br>
> &emsp; `userStoryNumber` (string): The user story number. <br>
> &emsp; `userStoryText` (string): The description of the user story. <br>
> &emsp; `status` (string): The status of the user story.,
> &emsp; ... <br>
> ]

- Status Code: 400 (Bad Request)
    - Response Body: Error message (string)

- Status Code: 404 (Not Found)
    - Response Body: Error message (string)

| Status Code | Response Body                | Description |
|-------------|------------------------------|-------------|
| 200         | List\<UserStoryDto> (JSON)   |             |



### GET /{code}

Retrieves a project.

#### Request

- HTTP Method: GET
- Path: `/{code}`
- Request Body:
  -  {}

#### Responses

- Status Code: 200 (OK)
  - Response Body: List\<ProjectDto> (JSON)
  
> [ <br>
> &emsp; `code` (string): The project code. <br>
> &emsp; `projectName` (string): The name of the project. <br>
> &emsp; `customerName` (string): The name of the customer.,
> &emsp; `status` (string): The status of the account.,
> &emsp; `startDate` (string): The start date of the project.,
> &emsp; `endDate` (string): The end date of the project.,
> &emsp; ... <br>
> ]

- Status Code: 400 (Bad Request)
  - Response Body: Error message (string)

- Status Code: 404 (Not Found)
  - Response Body: Error message (string)

| Status Code | Response Body               | Description |
|-------------|-----------------------------|-------------|
| 200         | List\<ProjectDto> (JSON)    |             |


