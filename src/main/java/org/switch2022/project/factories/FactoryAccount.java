package org.switch2022.project.factories;

import org.switch2022.project.model.Account;

import java.awt.image.BufferedImage;

/**
 * Implementation of the FactoryAccount interface that creates instances of the
 * Account class.
 */
public class FactoryAccount implements IFactoryAccount {
    /**
     * This method creates a new Account object.
     *
     * @param name        the name of the Account
     * @param email       of the Account
     * @param phoneNumber of the owner Account
     * @param photo       of the owner of Account
     * @return a new Account object with the specified attributes.
     */

    public Account createAccount(String name, String email, long phoneNumber,
                                 BufferedImage photo) {
        return new Account(name, email, phoneNumber, photo);
    }
}
