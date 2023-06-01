# ResourceWebController

Controller class for handling requests related to resources.

## Constructor

### ResourceWebController

Constructs a new ResourceController with the specified services.

#### Parameters

- `service` (ResourceAllocationService): The service responsible to create a new resource.

## Endpoints


### POST /resources

Registers and saves a resource based on the provided AllocationDto.

#### Request

- HTTP Method: POST
- Path: `/resources`
- Request Body: AllocationDto (JSON)

> `projectCode` (string, required): The code of the project. <br>
> `accountEmail` (string, required): The email address of the account. <br>
> `accountRole` (string, required): The role of the account holder. <br>
> `accountCostPerHour` (float, required): The cost per hour of the account holder.
> `accountPercentageOfAllocation` (float, required): The percentage of allocation of the account holder.
> `startDate` (LocalDate, required): The start date.
> `endDate` (LocalDate, required): The end date.

#### Responses

- Status Code: 201 (Created)
    - Response Body: AllocationDto (JSON)
    -
> `projectCode` (string, required): The code of the project. <br>
> `accountEmail` (string, required): The email address of the account. <br>
> `accountRole` (string, required): The role of the account holder. <br>
> `accountCostPerHour` (float, required): The cost per hour of the account holder.
> `accountPercentageOfAllocation` (float, required): The percentage of allocation of the account holder.
> `startDate` (LocalDate, required): The start date.
> `endDate` (LocalDate, required): The end date.

- Status Code: 409 (Conflict)
    - Response Body: Error message (string)


| Status Code | Response Body           | Description                                         |
|-------------|-------------------------|-----------------------------------------------------|
| 201         | AllocationDto (JSON)    | The resource was created successfully.              |
| 409         | Error message (string)  | Account already exists.                             |
| 400         | Error message (string)  | Email cannot be null.                               |
| 400         | Error message (string)  | Email cannot be empty.                              |
| 400         | Error message (string)  | Email cannot be blank.                              |
| 400         | Error message (string)  | Email is not valid.                                 |
| 400         | Error message (string)  | Name cannot be null.                                |
| 400         | Error message (string)  | Name cannot be empty.                               |
| 400         | Error message (string)  | Name cannot be blank.                               |
| 400         | Error message (string)  | Phone Number cannot be null.                        |
| 400         | Error message (string)  | Phone Number cannot be empty.                       |
| 400         | Error message (string)  | Phone Number cannot be blank.                       |
| 400         | Error message (string)  | Phone number should have nine digits.               |
| 400         | Error message (string)  | Phone number can only contain numbers.              |
| 400         | Error message (string)  | Phone number can only start with a 2, 6 , 7 or a 9. |


## Dependencies

- `service` (ResourceAllocationService): The service responsible to create a new resource.