# AccountWebController

Controller class for registering and managing user accounts.

## Constructor

### AccountWebController

Constructs a new AccountController with the specified services.

#### Parameters

- `accountCreationService` (AccountCreationService): The service responsible for creating accounts.
- `accountListService` (AccountListService): The service responsible to retrieve a list of all accounts.
- `accountChangeStatusService` (AccountChangeStatusService): The service responsible to change the account's status.

## Endpoints

### POST /accounts

Registers and saves an account based on the provided AccountCreationDto.

#### Request

- HTTP Method: POST
- Path: `/accounts`
- Request Body: AccountCreationDTO (JSON)

> `name` (string, required): The name of the account holder. <br>
> `email` (string, required): The email address of the account. <br>
> `phoneNumber` (int, required): The phone number of the account holder. <br>
> `photo` (bufferedImage): The photo of the account holder.

#### Responses

- Status Code: 201 (Created)
  - Response Body: AccountCreationDTO (JSON)
  - 
> `name` (string, required): The name of the account holder. <br>
> `email` (string, required): The email address of the account. <br>
> `phoneNumber` (string, required): The phone number of the account holder. <br>
> `photo` (bufferedImage): The photo of the account holder.

- Status Code: 409 (Conflict)
    - Response Body: Error message (string)


| Status Code | Response Body          | Description                                         |
|-------------|------------------------|-----------------------------------------------------|
| 201         | AccountDTO (JSON)      | The account was created successfully.               |
| 409         | Error message (string) | Account already exists.                             |
| 400         | Error message (string) | Email cannot be null.                               |
| 400         | Error message (string) | Email cannot be empty.                              |
| 400         | Error message (string) | Email cannot be blank.                              |
| 400         | Error message (string) | Email is not valid.                                 |
| 400         | Error message (string) | Name cannot be null.                                |
| 400         | Error message (string) | Name cannot be empty.                               |
| 400         | Error message (string) | Name cannot be blank.                               |
| 400         | Error message (string) | Phone Number cannot be null.                        |
| 400         | Error message (string) | Phone Number cannot be empty.                       |
| 400         | Error message (string) | Phone Number cannot be blank.                       |
| 400         | Error message (string) | Phone number should have nine digits.               |
| 400         | Error message (string) | Phone number can only contain numbers.              |
| 400         | Error message (string) | Phone number can only start with a 2, 6 , 7 or a 9. |


### PATCH /account/email

Sets the status of an account.

#### Request

- HTTP Method: PATCH
- Path: `/account/email`
- Request Body: String email, String status (JSON)

> `email` (string, required): The email address of the account. <br>
> `status` (string, required): The status to set the account. <br>

#### Responses

- Status Code: 200 (OK)
  - Response Body: {}

- Status Code: 400 (Bad Request)
  - Response Body: Error message (string)

- Status Code: 404 (Not Found)
  - Response Body: Error message (string)

| Status Code | Response Body          | Description                              |
|-------------|------------------------|------------------------------------------|
| 200         | {}                     |                                          |
| 400         | Error message (string) | Email cannot be null.                    |
| 400         | Error message (string) | Email cannot be empty.                   |
| 400         | Error message (string) | Email cannot be blank                    |
| 400         | Error message (string) | Email is not valid.                      |
| 400         | Error message (string) | Account status cannot be null.           |
| 400         | Error message (string) | Account status cannot be empty.          |
| 400         | Error message (string) | Account status cannot be blank.          |
| 400         | Error message (string) | Invalid Account Status.                  |
| 404         | Error message (string) | Account with that email does not exists. |

### GET /accounts/

Retrieves a list of all accounts.

#### Request

- HTTP Method: GET
- Path: `/accounts`
- Request Body: 
  -  {}

#### Responses

- Status Code: 200 (OK)
  - Response Body: List\<AccountDTO> (JSON)
> [ <br>
> &emsp; `email` (string): The email of the account. <br>
> &emsp; `name` (string): The description of the account. <br>
> &emsp; `status` (string): The status of the account.,
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

- `accountCreationService` (AccountCreationService): The service responsible for creating accounts.
- `accountListService` (AccountListService): The service responsible to retrieve a list of all accounts.
- `accountChangeStatusService` (AccountChangeStatusService): The service responsible to change the account's status.