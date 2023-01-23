package org.switch2022.project.controller;

import org.switch2022.project.model.*;

import java.awt.image.BufferedImage;

/**
 * Class RegisterAccountController is built to allow access to AccountContainer
 * in Company Class.
 */

public class RegisterAccountController {

    /**
     * Attributes of the class RegisterAccountController, according to the Class Diagram.
     */

    private Company company;

    /**
     * RegisterAccountController constructor
     */

    public RegisterAccountController(Company company) {
        this.company = company;
    }

    /**
     * Register new account method
     *
     * @param name        of the new account
     * @param email       of the new account
     * @param phoneNumber of the new account
     * @param photo       of the new account
     *
     * @return true if Account is added
     * @return false if Account is not added
     */
    public boolean registerAccount(String name, String email, long phoneNumber, BufferedImage photo) {
         return this.company.registerAccount(name, email, phoneNumber, photo);
    }
}
