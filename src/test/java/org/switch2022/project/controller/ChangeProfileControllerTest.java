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

class ChangeProfileControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Account accountOne;
    Profile profileOne;
    BusinessSector businessSector;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    BusinessSectorContainer businessSectorContainer;
    Company company;
    ChangeProfileController controller;
    List<BusinessSector> businessSectors;
    List<Account> accounts;
    List<Profile> profiles;

    @BeforeEach
    void setUp() {

        accounts = new ArrayList<>();
        accountContainer = new AccountContainer(accounts);
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accounts.add(accountOne);

        profiles = new ArrayList<>();
        profileContainer = new ProfileContainer(profiles);
        profileOne = new Profile("Administrator");
        profiles.add(profileOne);

        businessSector = new BusinessSector("fishing");

        businessSectors = new ArrayList<>();
        businessSectorContainer= new BusinessSectorContainer(businessSectors);
        businessSectors.add(businessSector);

        company = new Company(accountContainer, profileContainer,businessSectorContainer);
        controller = new ChangeProfileController(company);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        profileOne = null;
        accountContainer = null;
        profileContainer = null;
        company = null;
        controller = null;
        accounts.clear();
        profiles.clear();
        businessSector=null;
        businessSectors.clear();
        businessSectorContainer=null;
    }
    @Test
    void ensureAccountProfileIsChangedSuccessfully() {
        //Arrange
        boolean expected = true;
        //Act
        boolean result = controller.changeProfile("mike@isep.ipp.pt", "Administrator");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureAccountProfileIsNotChangedSuccessfully_ProfileNotFound() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = controller.changeProfile("mike@isep.ipp.pt", "Manager");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureAccountProfileIsNotChangedSuccessfully_AccountNotFound() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = controller.changeProfile("mikke@isep.ipp.pt", "Administrator");
        //Assert
        assertEquals(expected, result);
    }
}