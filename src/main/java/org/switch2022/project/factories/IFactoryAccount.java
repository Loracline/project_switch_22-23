package org.switch2022.project.factories;

import org.switch2022.project.model.Account;

import java.awt.image.BufferedImage;

/**
 * Interface for an Account factory.
 */
public interface IFactoryAccount {

    /**
     * This method creates a new Account object WITH a photo.
     *
     * @param accountName the name of the Account
     * @param email       of the Account
     * @param phoneNumber of the owner Account
     * @param photo       of the owner of Account
     * @return a new Account object with the specified attributes.
     */
    Account createAccount(String accountName, String email, long phoneNumber,
                                 BufferedImage photo);

    /**
     * This method creates a new Account object WITHOUT a photo.
     *
     * @param accountName the name of the Account
     * @param email       of the Account
     * @param phoneNumber of the owner Account
     * @return a new Account object with the specified attributes.
     */
    Account createAccount(String accountName, String email, long phoneNumber);
}

