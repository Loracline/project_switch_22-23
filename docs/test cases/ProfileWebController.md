# ProfileWebController

Controller class for handling requests related to profiles.

## Constructor

### ProfileWebController

Constructs a new ProfileController with the specified services.

#### Parameters

- `service` (ProfileService): The service responsible for creating profiles.

## Endpoints

### POST /profiles

Registers and saves a profile based on the provided ProfileCreationDto.

#### Request

- HTTP Method: POST
- Path: `/profiles`
- Request Body: ProfileCreationDto (JSON)

> `profileName` (string, required): The name of the profile. <br>

#### Responses

- Status Code: 201 (Created)
    - Response Body: ProfileCreationDto (JSON)
    -
> `profileName` (string, required): The name of the profile. <br>

- Status Code: 409 (Conflict)
    - Response Body: Error message (string)

| Status Code | Response Body             | Description                           |
|-------------|---------------------------|---------------------------------------|
| 201         | ProfileCreationDto (JSON) | The profile was created successfully. |
| 409         | Error message (string)    | Profile name cannot be blank.         |
| 409         | Error message (string)    | Profile name cannot be empty.         |
| 415         | Error message (string)    | Unsupported media type.               |

## Dependencies

- `service` (ProfileService): The service responsible for creating profiles.