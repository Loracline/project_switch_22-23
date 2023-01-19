package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.AccountContainer;
import org.switch2022.project.container.BusinessSectorContainer;
import org.switch2022.project.container.ProfileContainer;
import org.switch2022.project.model.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegisterAccountControllerTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Account accountOne, accountTwo, accountThree;
    Profile profileOne, profileTwo, profileThree;
    List<Account> accounts;
    List<Profile> profiles;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    BusinessSector businessSector;
    List<BusinessSector> businessSectors;
    BusinessSectorContainer businessSectorContainer;

    Company company;

    RegisterAccountController controller;

    @BeforeEach
    void setUp() {
        //create users
        accountOne = new Account("Claire", "claire@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Emma", "emma@isep.ipp.pt", 932755688, null);
        accountThree = new Account("Jane", "jane@isep.ipp.pt", 932755687, null);

        //add users to accounts list
        accounts = new ArrayList<>();
        accounts.add(accountOne);
        accounts.add(accountTwo);
        accounts.add(accountThree);

        //initialize account container with filled account list
        accountContainer = new AccountContainer(accounts);

        //create profiles
        profileOne = new Profile("Manager");
        profileTwo = new Profile("Administrator");
        profileThree = new Profile("User");

        //add profiles to profiles list
        profiles = new ArrayList<>();
        profiles.add(profileOne);
        profiles.add(profileTwo);
        profiles.add(profileThree);

        //initialize profile container with filled profiles list
        profileContainer = new ProfileContainer(profiles);


        businessSector = new BusinessSector("fishing");

        businessSectors = new ArrayList<>();
        businessSectorContainer= new BusinessSectorContainer(businessSectors);
        businessSectors.add(businessSector);

        //initialize company with filled containers
        company = new Company(accountContainer, profileContainer,businessSectorContainer);

        //initialize controller with company
        controller = new RegisterAccountController(company);

    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        accountThree = null;
        profileOne = null;
        profileTwo = null;
        profileThree = null;

        accounts.clear();
        profiles.clear();

        accountContainer = null;
        profileContainer = null;
        businessSector=null;
        businessSectors.clear();
        businessSectorContainer=null;
        company = null;
        controller = null;
    }
    @Test
    void ensureThatNewAccountIsRegisteredIfEmailIsUnique() {
        //ARRANGE
        boolean expected = true;

        //ACT
        boolean result = controller.registerAccount("Charlotte", "charlotte@isep.ipp.pt", 932755677, null);

        //ASSERT
        assertEquals(expected, result);
    }


    @Test
    void ensureThatNewAccountIsNotRegisteredIfEmailIsDuplicated() {
        //ARRANGE
        boolean expected = false;

        //ACT
        boolean result = controller.registerAccount("Emma", "emma@isep.ipp.pt", 932755688, null);

        //ASSERT
        assertEquals(expected, result);
    }
}