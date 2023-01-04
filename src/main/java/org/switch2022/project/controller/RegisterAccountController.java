package org.switch2022.project.controller;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.Profile;
import org.switch2022.project.model.Company;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Class RegisterAccountController is built to allow access to register account methods
 * in repository Class.
 */

public class RegisterAccountController {

    /**
     * Attributes of the class RegisterAccountController, according to the Class Diagram.
     */

    private Company company;

    /**
     * RegisterAccountController constructor
     */
    public RegisterAccountController(List<Account> accountList, List<Profile> profileList) {
        this.company = new Company(accountList, profileList);

    }

    public RegisterAccountController() {
        this.company = new Company();

    }

    /**
     * Register new account method.
     *
     * @param name        of the new account
     * @param email       of the new account
     * @param phoneNumber of the new account
     * @param photo       of the new account
     * @param status      of the new account
     * @return instance of Account with given parameters if email is unique;
     * @return null if email already exists
     */


    public boolean registerAccount(String name, String email, long phoneNumber, BufferedImage photo, boolean status) {

        return this.company.registerAccount(name, email, phoneNumber, photo, status);

    }

}