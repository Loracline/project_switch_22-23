package org.switch2022.project.factories;

import org.switch2022.project.model.Account;

import java.awt.image.BufferedImage;

/**
 * Interface for an Account factory.
 */

public interface IFactoryAccount {
    /**
     * This method creates a new Account object with the specified attributes with no return.
     *
     * @param name        the name of the Account
     * @param email       of the Account
     * @param phoneNumber of the owner Account
     * @param photo       of the owner of Account
     */

    public Account createAccount(String name, String email, long phoneNumber, BufferedImage photo);
}

