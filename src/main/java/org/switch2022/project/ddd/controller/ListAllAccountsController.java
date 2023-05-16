package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.AccountService;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.dto.AccountDto;
import org.switch2022.project.ddd.dto.mapper.AccountMapper;

import java.util.List;

/**
 * Class ListAllAccountsController is a class between the UI and application service,
 * responsible to list all the accounts registered.
 */
@Controller
public class ListAllAccountsController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountMapper accountMapper;

    /**
     * This method requests a list of all accounts
     * @return an accountDto list
     */

    public List<AccountDto> listAllAccounts() {
        List<Account> accounts = accountService.listAllAccounts();
        return accountMapper.listAccountsToDto(accounts);
    }
}
