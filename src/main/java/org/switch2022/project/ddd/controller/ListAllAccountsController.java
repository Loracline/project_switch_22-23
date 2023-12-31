package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.AccountListService;
import org.switch2022.project.ddd.dto.AccountDto;

import java.util.List;

/**
 * Class ListAllAccountsController is a class between the UI and application service,
 * responsible to list all the accounts registered.
 */
@Controller
public class ListAllAccountsController {
    @Autowired
    private AccountListService accountService;

    /**
     * This method requests a list of all accounts
     * @return an accountDto list
     */

    public List<AccountDto> listAllAccounts() {
        return accountService.listAllAccounts();
    }
}
