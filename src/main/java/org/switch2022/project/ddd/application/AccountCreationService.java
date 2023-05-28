package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountFactory;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.domain.value_object.PhoneNumber;
import org.switch2022.project.ddd.domain.value_object.Photo;
import org.switch2022.project.ddd.dto.AccountCreationDto;

import java.awt.image.BufferedImage;

/**
 * Class AccountService allows to create an account.
 */
@Service
public class AccountCreationService {
    @Autowired
    private IAccountFactory accountFactory;
    @Qualifier("account_jpa")
    @Autowired
    private IAccountRepository accountRepository;

    /**
     * This method receives a name, an email, a phoneNumber and photo and creates an
     * account and adds it to the repository.
     *
     * @param accountCreationDto the AccountCreationDto that represents the data for creating a
     *                           new account
     * @return TRUE if the Account is created and added to the account repository
     * successfully, and throws an AlreadyExistsInRepoException otherwise.
     */

    public boolean registerAccount(AccountCreationDto accountCreationDto) {
        String emailString = accountCreationDto.email;
        String nameString = accountCreationDto.name;
        int phoneNumberInt = accountCreationDto.phoneNumber;
        BufferedImage photoBuf = accountCreationDto.photo;

        Name name = new Name(nameString);
        Email email = new Email(emailString);
        PhoneNumber phoneNumber = new PhoneNumber(phoneNumberInt);
        Photo photo = new Photo(photoBuf);

        Account account = accountFactory.create(name, email, phoneNumber, photo);
        return accountRepository.save(account);
    }
}
