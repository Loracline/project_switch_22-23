package org.switch2022.project.ddd.domain.model.account;

import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.domain.value_object.PhoneNumber;
import org.switch2022.project.ddd.domain.value_object.Photo;

/**
 * Factory of Account class.
 */
public interface IAccountFactory {

    /**
     * This method creates an instance of Account.
     *
     * @param name        of the account.
     * @param email       of the account, unique for each account.
     * @param phoneNumber of the account.
     * @param photo       is option.
     * @return a new object Account.
     */
    Account create(Name name, Email email, PhoneNumber phoneNumber, Photo photo);
}
