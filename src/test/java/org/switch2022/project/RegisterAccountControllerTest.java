package org.switch2022.project;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegisterAccountControllerTest {

    @Test
    void ensureThatNewAccountIsRegisteredWithEmptyConstructor() {
        //ARRANGE
        //create empty controller
        RegisterAccountController test = new RegisterAccountController();
        boolean expected= true;

        //ACT  register user with same information as expected
        boolean result = test.registerAccount("Ana", "ana@mail.com", 12345678, null, true);

        //ASSERT compare expected user with the registered user
        assertEquals(expected, result);
    }


    @Test
    void ensureThatNewAccountIsRegisteredWithInitializedConstructor() {

        //ARRANGE
        //create initialized controller
        List<Account> accountList = new ArrayList<>();
        List<Profile> profileList = new ArrayList<>();

        RegisterAccountController test = new RegisterAccountController(accountList, profileList);
        boolean expected= true;

        //ACT  register user with same information as expected
        boolean result = test.registerAccount("Ana", "ana@mail.com", 12345678, null, true);

        //ASSERT compare expected user with the registered user
        assertEquals(expected, result);
    }


    @Test
    void ensureThatNewAccountIsRegisteredWithInitializedConstructorWithPhoto() throws IOException {

        //ARRANGE
        //create initialized controller
        List<Account> accountList = new ArrayList<>();
        List<Profile> profileList = new ArrayList<>();

        BufferedImage photo = ImageIO.read(new File("docs/domain_analysis/Domain_Model_v01_Dec14_2022.png"));

        RegisterAccountController test = new RegisterAccountController(accountList, profileList);
        boolean expected= true;

        //ACT  register user with same information as expected
        boolean result = test.registerAccount("Ana", "ana@mail.com", 12345678, photo, true);

        //ASSERT compare expected user with the registered user
        assertEquals(expected, result);
    }
}