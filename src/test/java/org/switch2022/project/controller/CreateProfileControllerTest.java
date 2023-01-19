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



class CreateProfileControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Account accountOne, accountTwo, accountThree, accountFour;
    Profile profileOne, profileTwo;
    List<Account> accounts;
    List<Profile> profiles;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    BusinessSector businessSector;
    List<BusinessSector> businessSectors;
    BusinessSectorContainer businessSectorContainer;
    Company company;

    CreateProfileController createProfileController;


    @BeforeEach
    void setUp() {
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Paul", "paul@isep.ipp.pt", 939855689, null);
        accountThree = new Account("Anna", "anna@isep.ipp.pt", 932755689, null);
        accountFour = new Account("Mary", "mary@isep.ipp.pt", 939855689, null);

        accounts = new ArrayList<>();
        accountContainer = new AccountContainer(accounts);
        accounts.add(accountOne);
        accounts.add(accountTwo);

        profileOne = new Profile("Administrator");
        profileTwo = new Profile("User");


        profiles = new ArrayList<>();
        profileContainer = new ProfileContainer(profiles);
        profiles.add(profileOne);
        profiles.add(profileTwo);

        businessSector = new BusinessSector("fishing");

        businessSectors = new ArrayList<>();
        businessSectorContainer= new BusinessSectorContainer(businessSectors);
        businessSectors.add(businessSector);

        company = new Company(accountContainer, profileContainer,businessSectorContainer);

        createProfileController = new CreateProfileController(company);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        profileOne = null;
        profileTwo = null;
        accounts.clear();
        profiles.clear();
        accountContainer = null;
        profileContainer = null;
        businessSector=null;
        businessSectors.clear();
        businessSectorContainer=null;
        company = null;
        createProfileController = null;
    }
    @Test
    void addNewProfileSuccessfully(){
        //Arrange
        boolean expected = true;
        //Act
        boolean result = createProfileController.createProfile("Manager");
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void addNewProfileUnsuccessfullyInvalidName(){
        //Arrange
        boolean expected = false;
        //Act
        boolean result = createProfileController.createProfile("User");
        //Assert
        assertEquals(expected,result);
    }
}