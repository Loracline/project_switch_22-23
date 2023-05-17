package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;

import java.util.List;

/**
 * Class AccountListService allows to list all accounts registered.
 */
@Service
public class AccountListService {
    @Autowired
    private IAccountRepository accountRepository;

    /**
     * This method asks the repository for the list of all Accounts and returns it.
     * @return list with all accounts.
     */
    public List<Account> listAllAccounts() {
        return accountRepository.getAccounts();
    }
}
