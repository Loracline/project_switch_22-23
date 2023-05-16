package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountFactory;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.domain.value_object.*;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Class AccountService allows to create and manipulate the Account aggregate.
 */
@Service
public class AccountService {
    @Autowired
    private IAccountFactory accountFactory;
    @Autowired
    private IAccountRepository accountRepository;

    /**
     * This method receives a name, an email, a phoneNumber and photo and creates an
     * account and adds it to the repository.
     *
     * @param nameString from the user account.
     * @param emailString from the user account.
     * @param phoneNumberInt from the user account.
     * @param photoBuf from the user account.
     * @return TRUE if the Account is created and added to the account repository
     * successfully, and throws an
     * AlreadyExistsInRepoException otherwise.
     */

    public boolean registerAccount(String nameString, String emailString,
                                   int phoneNumberInt,
                                   BufferedImage photoBuf) {
        Name name = new Name(nameString);
        Email email = new Email(emailString);
        PhoneNumber phoneNumber = new PhoneNumber(phoneNumberInt);
        Photo photo = new Photo(photoBuf);

        Account account = accountFactory.create(name, email, phoneNumber, photo);
        return accountRepository.add(account);
    }

    /**
     * This method asks the repository for the list of all Accounts and returns it.
     * @return list with all accounts.
     */
    public List<Account> listAllAccounts() {
        return accountRepository.getAccounts();
    }

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
                Account account = accountRepository.getAccountByEmail(email);
                result = account.changeStatus(accountStatus);
            }
        }
        return result;
    }



}
