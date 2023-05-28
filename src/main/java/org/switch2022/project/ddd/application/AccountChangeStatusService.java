package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.domain.value_object.*;

/**
 * Class AccountService allows to create and manipulate the Account aggregate.
 */
@Service
public class AccountChangeStatusService {
    @Qualifier("account_jpa")
    @Autowired
    private IAccountRepository accountRepository;

    /**
     * This method changes the status of the account with the specified email address.
     * @param email  the email address of the account to update.
     * @param status the new status for the account.
     * @return updates the account.
     */

    public boolean changeStatus(String email, String status) {
        boolean result = false;
        for (AccountStatus accountStatus : AccountStatus.values()) {
            if (accountStatus.getAccountStatus().equals(status)) {
                Account account = accountRepository.findAccountByEmail(email);
                result = account.changeStatus(accountStatus);
            }
        }
        return result;
    }
}
