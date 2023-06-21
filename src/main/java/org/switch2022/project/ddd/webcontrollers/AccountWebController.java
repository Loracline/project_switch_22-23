package org.switch2022.project.ddd.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.ddd.application.AccountChangeStatusService;
import org.switch2022.project.ddd.application.AccountCreationService;
import org.switch2022.project.ddd.application.AccountListService;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.dto.AccountCreationDto;
import org.switch2022.project.ddd.dto.AccountDto;

import java.util.List;
import java.util.Optional;

/**
 * The AccountWebController class is a REST controller for handling requests related to
 * accounts.
 */

@CrossOrigin(origins = {"http://localhost:3000"}, maxAge = 3600)
@RestController
@RequestMapping("/accounts")
public class AccountWebController {
    /**
     * The AccountCreationService used to create a new account.
     */
    @Autowired
    private AccountCreationService accountCreationService;

    /**
     * The AccountListService used to retrieve a list of all accounts.
     */
    @Autowired
    private AccountListService accountListService;

    /**
     * The AccountChangeStatusService used to change the status of the account.
     */
    @Autowired
    private AccountChangeStatusService accountChangeStatusService;

    /**
     * Registers a new account based on the provided AccountCreationDto.
     *
     * @param accountCreationDto the AccountCreationDto object containing the
     *                           information for
     *                           creating the new account.
     * @return ResponseEntity representing the result of the account registration: if the
     * account registration is successful, returns a ResponseEntity with HTTP status
     * code 201
     * (CREATED); if the account registration encounters a conflict or error, returns a
     * ResponseEntity with HTTP status code 409 (CONFLICT).
     */
    @PostMapping
    public ResponseEntity<Object> registerAccount(@RequestBody AccountCreationDto accountCreationDto) {
        if (accountCreationService.registerAccount(accountCreationDto)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    /**
     * Handles a GET request to retrieve a list of all accounts.
     *
     * @return ResponseEntity representing the result of the retrieval. If the
     * account list is successfully retrieved, returns a ResponseEntity with HTTP status
     * code 200 (OK) and the list of AccountDto objects.
     */
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<AccountDto>> listAllAccounts() {
        List<AccountDto> accounts = accountListService.listAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    /**
     * Handles a PATCH request to change the status of an account with the specified
     * email.
     *
     * @param email  the email of the account we want to change the status.
     * @param status the new status to set for the account.
     * @return ResponseEntity representing the result of the status change operation: if
     * the status change is successful, returns a ResponseEntity with HTTP status
     * code 200 (OK); if the account with the specified email is not found, returns a
     * ResponseEntity with HTTP status code 404 (NOT FOUND).
     */

    @PatchMapping("/account/{email}")
    @ResponseBody
    public ResponseEntity<Object> changeStatus(@PathVariable("email") String email,
                                               @RequestParam String status) {
        if (accountChangeStatusService.changeStatus(email, status)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Retrieves an account by their email and returns it as a response entity.
     *
     * @param email The email of the account to be retrieved.
     * @return A response entity containing the account DTO if found, or a not found
     * response if the account is not
     * found.
     */
    @GetMapping("/{email}")
    public ResponseEntity<AccountDto> getByEmail(@PathVariable String email) {
        Email emailId = new Email(email);
        Optional<AccountDto> opAccountDto = accountListService.getAccountByEmail(emailId);
        if (opAccountDto.isPresent()) {
            AccountDto accountDto = opAccountDto.get();
            return new ResponseEntity<>(accountDto, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

