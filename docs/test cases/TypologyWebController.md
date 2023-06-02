# TypologyWebController

Controller class for registering and managing a typology.

## Constructor

### TypologyWebController

Constructs a new TypologyController with the specified services.

#### Parameters

- `service` (TypologyService): The service responsible for creating typology's.

## Endpoints

### POST /typologies

Registers and saves a typology based on the provided TypologyCreationDto.

#### Request

- HTTP Method: POST
- Path: `/typologies`
- Request Body: TypologyCreationDto (JSON)

> `typologyName` (string, required): The name of the typology. <br>

#### Responses

- Status Code: 201 (Created)
  - Response Body: TypologyCreationDto (JSON)
  - 
> `typologyId` (string, required): The id of the typology. <br>
> `typologyName` (string, required): The name of the typology. <br>


- Status Code: 409 (Conflict)
    - Response Body: Error message (string)


| Status Code | Response Body               | Description                           |
|-------------|-----------------------------|---------------------------------------|
| 201         | TypologyCreationDto  (JSON) | The account was created successfully. |
| 409         | Error message (string)      | Typology already exists.              |
| 400         | Error message (string)      | Name cannot be null.                  |
| 400         | Error message (string)      | Name cannot be empty.                 |
| 400         | Error message (string)      | Name cannot be blank.                 |


### GET /typologies/

Retrieves a list of all typologies.

#### Request

- HTTP Method: GET
- Path: `/typologies`
- Request Body:
  -  {}

#### Responses

- Status Code: 200 (OK)
  - Response Body: List\<TypologyDto> (JSON)
> [ <br>
> &emsp; `typologyId` (string): The id of the typology. <br>
> &emsp; `typologyName` (string): The name of the typology. <br>
> &emsp; ... <br>
> ]

- Status Code: 400 (Bad Request)
  - Response Body: Error message (string)

- Status Code: 404 (Not Found)
  - Response Body: Error message (string)

| Status Code | Response Body               | Description                              |
|-------------|-----------------------------|------------------------------------------|
| 200         | List\<TypologyDto> (JSON)   |                                          |


## Dependencies

- `service` (TypologyService): The service responsible for creating typology's.
