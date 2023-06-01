# BusinessSectorWebController

Controller class for handling requests related to resources.

## Constructor

### BusinessSectorWebController

Constructs a new BusinessSectorController used to create a new business sector.

#### Parameters

- `service` (BusinessSectorService): The service responsible for creating a new business sector.

## Endpoints

### POST /business_sectors

Creates and saves a business sector based on the provided BusinessSectorCreationDto.

#### Request

- HTTP Method: POST
- Path: `/business_sectors`
- Request Body: BusinessSectorCreationDto (JSON)

> `name` (string, required): The name of the business sector. <br>

#### Responses

- Status Code: 201 (Created)
    - Response Body: BusinessSectorCreationDto (JSON)
    -
> `name` (string, required): The name of the business sector. <br>

- Status Code: 409 (Conflict)
    - Response Body: Error message (string)


| Status Code | Response Body                    | Description                                   |
|-------------|----------------------------------|-----------------------------------------------|
| 201         | BusinessSectorCreationDto (JSON) | The business sector was created successfully. |
| 415         | Error message (string)           | Name cannot be empty.                         |
| 415         | Error message (string)           | Name cannot be blank.                         |

### GET /business_sectors

Retrieves a list of all business sectors.

#### Request

- HTTP Method: GET
- Path: `/business_sectors`
- Request Body: String name (JSON)

> `name` (string, required): The name of the business sector. <br>


#### Responses

- Status Code: 200 (OK)
  - Response Body: {}

> [ <br>
> &emsp; `name` (string): The name of the business sector. <br>
> &emsp; ... <br>
> ]

- Status Code: 415 (Unsupported Media Type)
  - Response Body: Error message (string)

| Status Code | Response Body                           | Description |
|-------------|-----------------------------------------|-------------|
| 200         | List\<BusinessSectorCreationDto> (JSON) |             |

## Dependencies

- `service` (BusinessSectorService): The service responsible for creating a new business sector.