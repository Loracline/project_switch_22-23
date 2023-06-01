# CustomerWebController

Controller class that handles HTTP requests related to customers.

## Constructor

### CustomerWebController

Constructs a new CustomerController with the specified services.

#### Parameters

- `createService` (CustomerService): The service responsible for creating a customer.
- `listService` (CustomerListService): The service responsible to retrieve a list of all customers.

## Endpoints

### POST /customers

Creates and saves a customer based on the provided CustomerCreationDto.

#### Request

- HTTP Method: POST
- Path: `/customers`
- Request Body: CustomerCreationDto (JSON)

> `customerTaxId` (int, required): The tax ID of the customer. <br>
> `customerName` (string, required): The name of the customer. <br>

#### Responses

- Status Code: 201 (Created)
    - Response Body: CustomerCreationDto (JSON)

> `customerTaxId` (int, required): The tax ID of the customer. <br>
> `customerName` (string, required): The name of the customer. <br>

- Status Code: 409 (Conflict)
    - Response Body: Error message (string)

| Status Code | Response Body             | Description                                           |
|-------------|---------------------------|-------------------------------------------------------|
| 201         | AccountCreationDTO (JSON) | The customer was created successfully.                |
| 409         | Error message (string)    | Customer already exists.                              |
| 415         | Error message (string)    | Name cannot be empty.                                 |
| 415         | Error message (string)    | Name cannot be blank.                                 |
| 415         | Error message (string)    | Name cannot be null.                                  |
| 400         | Error message (string)    | Invalid or unsupported country for tax ID validation. |


### GET /accounts/

Retrieves a list of all accounts.

#### Request

- HTTP Method: GET
- Path: `customers`
- Request Body:
    -  {}

#### Responses

- Status Code: 200 (OK)
    - Response Body: List<CustomerDto> (JSON)
> [ <br>
> &emsp; `name` (string): The name of the customer. <br>
> &emsp; `taxIdNumber` (int): The tax ID of the customer.,
> &emsp; ... <br>
> ]

- Status Code: 400 (Bad Request)
    - Response Body: Error message (string)

- Status Code: 404 (Not Found)
    - Response Body: Error message (string)

| Status Code | Response Body            | Description                              |
|-------------|--------------------------|------------------------------------------|
| 200         | List\<AccountDTO> (JSON) |                                          |


## Dependencies

- `createService` (CustomerService): The service responsible for creating a customer.
- `listService` (CustomerListService): The service responsible to retrieve a list of all customers.