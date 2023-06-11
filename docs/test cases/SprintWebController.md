# SprintWebController

Controller class for registering and managing user sprints.

## Constructor

### SprintWebController

Constructs a new SprintController with the specified services.

#### Parameters

- `createSprintService` (CreateSprintService): The service responsible for creating sprints.
- `userStoriesInSprintService` (UserStoriesInSprintService): The service responsible for retrieving the sprint backlog.
## Endpoints

### POST /sprints

Registers and saves an sprint based on the provided SprintCreationDto.

#### Request

- HTTP Method: POST
- Path: `/sprints`
- Request Body: SprintCreationDto (JSON)

> `projectCode` (string, required): To add a sprint to. <br>
> `startDate` (string, required): Start date of the sprint. <br>

#### Responses

- Status Code: 201 (Created)
    - Response Body: SprintCreationDto (JSON)
    -
> `sprintCode` (string, required): The code of the sprint created. <br>

- Status Code: 409 (Conflict)
    - Response Body: Error message (string)


| Status Code | Response Body               | Description                                             |
|-------------|-----------------------------|---------------------------------------------------------|
| 201         | SprintCreationDto (JSON)    | The sprint was created successfully.                    |
| 409         | Error message (string)      | Account already exists.                                 |
| 400         | Error message (string)      | The sprint period is overlapping with other sprint      |
| 400         | Error message (string)      | The sprint end date is after the project end date       |
| 400         | Error message (string)      | The sprint start date is before the project start date  |


## Dependencies

- `createSprintService` (CreateSprintService): The service responsible for creating sprints.

### GET /{sprintId}/userStoriesInSprint

Retrieves the sprint backlog of a sprint based on the provided sprint ID.

#### Request

- HTTP Method: GET
- Path: `/sprints/{sprintId}/userStoriesInSprint`
- Path Variable: String (JSON)

> `sprintId` (string, required): To consult the sprint backlog. <br>

#### Responses

- Status Code: 200 (OK)
  - Response Body: List<UserStoryDto> (JSON)

> [ <br>
> &emsp; `userStoryNumber` (string): The user story number. <br>
> &emsp; `userStoryText` (string): The description of the user story. <br>
> &emsp; `status` (string): The status of the user story.,
> &emsp; <br>
> ]

- Status Code: 404 (Not found)
  - Response Body: Error message (string)


| Status Code | Response Body             | Description             |
|-------------|---------------------------|-------------------------|
| 200         | List<UserStoryDto> (JSON) |                         |
| 404         | Error message (string)    | No sprint with that id. |


## Dependencies

- `userStoriesInSprintService` (UserStoriesInSprintService): The service responsible for retrieving the sprint backlog.