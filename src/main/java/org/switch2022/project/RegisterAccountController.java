package org.switch2022.project;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Class RegisterAccountController is built to allow access to register account methods
 * in repository Class.
 */

public class RegisterAccountController {

    /**
     * Attributes of the class RegisterAccountController, according to the Class Diagram.
     */

    private Repository repository;

    /**
     * RegisterAccountController constructor
     */
    public RegisterAccountController(List<Account> accountList, List<Profile> profileList) {
        this.repository = new Repository(accountList, profileList);

    }

    public RegisterAccountController() {
        this.repository = new Repository();

    }

    /**
     * Register new account method.
     *
     * @param name        of the new account
     * @param email       of the new account
     * @param phoneNumber of the new account
     * @param photo       of the new account
     * @return instance of Account with given parameters if email is unique;
     * @return null if email already exists
     */


    public Account registerAccount(String name, String email, long phoneNumber, BufferedImage photo) {

        return this.repository.registerAccount(name, email, phoneNumber, photo);

    }

}
