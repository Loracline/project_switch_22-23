package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.AccountCreationService;
import org.switch2022.project.ddd.dto.AccountCreationDto;
import org.switch2022.project.ddd.utils.Validate;

/**
 * Class RegisterAccountController is built to create business sectors.
 */

@Controller
public class RegisterAccountController {
    @Autowired
    private AccountCreationService accountService;

    /**
     * This method receives a dto with Account information and registers a new account.
     *
     * @param accountCreationDto that contains all Account information.
     * @return TRUE if the account is registered, and throws an
     * AlreadyExistsInRepoException exception otherwise.
     */

    public boolean registerAccount(AccountCreationDto accountCreationDto) {
        Validate.notNull(accountCreationDto, "The account Dto can't be null");
        return accountService.registerAccount(accountCreationDto);
    }
}
