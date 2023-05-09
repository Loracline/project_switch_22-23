package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.AccountService;
import org.switch2022.project.ddd.utils.Validate;

/**
 * Class ChangeStatusController is built to change account status.
 */
@Controller
public class ChangeAccountStatusController {
    @Autowired
    private AccountService accountService;

    /**
     * Changes the status of the account with the given email.
     *
     * @param email  the email of the account to change status
     * @param status the new status of the account
     * @return true if the status was successfully changed, false otherwise
     */
    public boolean changeStatus(String email, boolean status) {
        Validate.notNullOrEmptyOrBlank(email, "email");
        return accountService.changeStatus(email, status);

    }
}
