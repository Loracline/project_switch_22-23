package org.switch2022.project.factories;

import org.switch2022.project.model.Account;

import java.awt.image.BufferedImage;

/**
 * Implementation of the FactoryAccount interface that creates instances of the Account
 * class.
 */
public class FactoryAccount implements IFactoryAccount {

    /**
     * This method creates a new Account object WITH a photo.
     *
     * @param accountName the name of the Account
     * @param email       of the Account
     * @param phoneNumber of the owner Account
     * @param photo       of the owner of Account
     * @return a new Account object with the specified attributes.
     */
    public Account createAccount(String accountName, String email, long phoneNumber,
                                 BufferedImage photo) {
        return new Account.AccountBuilder(email)
                .setAccountName(accountName)
                .setPhoneNumber(phoneNumber)
                .setPhoto(photo)
                .build();
    }

    /**
     * This method creates a new Account object WITHOUT a photo.
     *
     * @param accountName the name of the Account
     * @param email       of the Account
     * @param phoneNumber of the owner Account
     * @return a new Account object with the specified attributes.
     */
    public Account createAccount(String accountName, String email, long phoneNumber) {
        return new Account.AccountBuilder(email)
                .setAccountName(accountName)
                .setPhoneNumber(phoneNumber)
                .build();
    }


}
