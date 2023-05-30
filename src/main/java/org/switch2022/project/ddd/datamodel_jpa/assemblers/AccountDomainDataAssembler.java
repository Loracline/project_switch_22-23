package org.switch2022.project.ddd.datamodel_jpa.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.datamodel_jpa.AccountJpa;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountFactory;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.utils.Utils;

import java.awt.image.BufferedImage;

@Service
public class AccountDomainDataAssembler {

    @Autowired
    private IAccountFactory accountFactory;

    /**
     * Converts an Account domain instance to an AccountJpa object for persistence.
     *
     * @param account the Account instance to be converted.
     * @return the converted AccountJpa instance.
     */
    public AccountJpa toData(Account account) {
        return new AccountJpa(account.getAccountEmail(), account.getAccountName(),
                account.getPhoneNumber(),
                account.getAccountStatus(), account.getProfileId(), account.getPhoto()
        );
    }

    /**
     * Converts an AccountJpa data model instance to an Account domain instance.
     *
     * @param accountJpa the AccountJpa instance to be converted.
     * @return The converted Account instance.
     */
    public Account toDomain(AccountJpa accountJpa) {
        Name accountName = new Name(accountJpa.getName());
        Email email = new Email(accountJpa.getEmail());
        PhoneNumber phoneNumber = new PhoneNumber(Integer.parseInt(accountJpa.getPhoneNumber()));
        BufferedImage bufferedImage = Utils.convertByteArrayToBufferedImage(accountJpa.getPhoto());
        Photo photo = new Photo(bufferedImage);
        AccountStatus accountStatus =
                AccountStatus.valueOf(accountJpa.getAccountStatus().toUpperCase());
        ProfileId profileId =
                new ProfileId(Utils.getIntFromAlphanumericString(accountJpa.getProfileId(), "prf"));
        Account account = accountFactory.create(accountName, email, phoneNumber, photo);
        account.changeStatus(accountStatus);
        account.changeProfile(profileId);
        return account;
    }
}
