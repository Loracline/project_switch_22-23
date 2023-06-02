# UserStoryWebController

Controller class for registering and managing a user story.

## Constructor

### UserStoryWebController

Constructs a new UserStoryController with the specified services.

#### Parameters

- `usService` (UsService): The service responsible for creating user stories.

## Endpoints

### POST /userStories

Registers and saves a user story based on the provided UserStoryCreationDto.

#### Request

- HTTP Method: POST
- Path: `/userStories`
- Request Body: UserStoryCreationDto (JSON)

> `projectCode` (string, required): The identifier of the project where the user story should be created. <br>
> `userStoryNumber` (string, required): The identifier of the user story. <br>
> `userStoryText` (string, required): The description of the user story. <br>
> `actor` (string, required): The actor who will use or benefit from the user story. <br>
> `acceptanceCriteria` (List<String>, required): The list of acceptance criteria. <br>
> `priority` (string, required): The priority of the user story. <br>


#### Responses

- Status Code: 201 (Created)
    - Response Body: UserStoryCreationDto (JSON)
    -
> `userStoryId` (string, required): The id of the user story created. <br>

- Status Code: 409 (Conflict)
    - Response Body: Error message (string)


| Status Code | Response Body                | Description                                            |
|-------------|------------------------------|--------------------------------------------------------|
| 201         | UserStoryCreationDto  (JSON) | The user story was created successfully.               |
| 409         | Error message (string)       | User Story ID already exists.                          |
| 400         | Error message (string)       | ProjectCode Index 1 out of bounds for length 1 .       |
| 400         | Error message (string)       | ProjectCode The initial expression must not be null.   |
| 400         | Error message (string)       | ProjectCode blank ,Index 1 out of bounds for length 1  |
| 400         | Error message (string)       | The user story number must not be null.                |
| 400         | Error message (string)       | The user story number must not be empty.               |
| 400         | Error message (string)       | The user story number must not be blank.               |
| 400         | Error message (string)       | The user story text must not be null.                  |
| 400         | Error message (string)       | The user story text must not be empty.                 |
| 400         | Error message (string)       | The user story text must not be blank.                 |
| 400         | Error message (string)       | The actor must not be null.                            |
| 400         | Error message (string)       | The actor must not be empty.                           |
| 400         | Error message (string)       | The actor must not be blank.                           |
|             |                              |                                                        |

## Dependencies

- `usService` (UsService): The service responsible for creating user stories.