package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.AccountService;
import org.switch2022.project.ddd.dto.AccountCreationDto;
import org.switch2022.project.ddd.utils.Validate;

import java.awt.image.BufferedImage;

/**
 * Class RegisterAccountController is built to create business sectors.
 */

@Controller
public class RegisterAccountController {
    @Autowired
    private AccountService accountService;

    public boolean registerAccount(AccountCreationDto accountCreationDto) {
        Validate.notNull(accountCreationDto, "The account Dto can't be null");

        String name = accountCreationDto.name;
        String email = accountCreationDto.email;
        int phoneNumber = accountCreationDto.phoneNumber;
        BufferedImage photo = accountCreationDto.photo;

        return accountService.registerAccount(name, email, phoneNumber, photo);
    }
}
