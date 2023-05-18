package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountFactory;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.domain.value_object.*;

import java.awt.image.BufferedImage;

/**
 * Class AccountService allows to create an account.
 */
@Service
public class AccountCreationService {
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
}
