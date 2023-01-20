package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.AccountContainer;
import org.switch2022.project.container.BusinessSectorContainer;
import org.switch2022.project.container.ProfileContainer;
import org.switch2022.project.container.ProjectTypologyContainer;
import org.switch2022.project.model.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ProjectTypologyControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Account accountOne, accountTwo, accountThree;
    Profile profileOne, profileTwo, profileThree;
    ProjectTypology projectTypologyOne, projectTypologyTwo;
    List<Account> accounts;
    List<Profile> profiles;
    BusinessSector businessSector;
    List<BusinessSector> businessSectors;
    BusinessSectorContainer businessSectorContainer;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    ProjectTypologyContainer projectTypologyContainer;
    List<ProjectTypology> typologies;
    Company company;

    ProjectTypologyController projectTypologyController;


    @BeforeEach
    void setUp() {
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Emma", "emma@isep.ipp.pt", 932755688, null);
        accountThree = new Account("Jane", "jane@isep.ipp.pt", 932755687, null);

        accounts = new ArrayList<>();
        accountContainer = new AccountContainer(accounts);
        accounts.add(accountOne);
        accounts.add(accountTwo);
        accounts.add(accountThree);

        profileOne = new Profile("Administrator");
        profileTwo = new Profile("User");
        profileThree= new Profile ("Manager");


        profiles = new ArrayList<>();
        profileContainer = new ProfileContainer(profiles);
        profiles.add(profileOne);
        profiles.add(profileTwo);

        businessSector = new BusinessSector("fishing");

        businessSectors = new ArrayList<>();
        businessSectorContainer= new BusinessSectorContainer(businessSectors);
        businessSectors.add(businessSector);

        projectTypologyOne = new ProjectTypology("Fixed Cost");
        projectTypologyTwo= new ProjectTypology("Fixed time and materials");

        typologies = new ArrayList<>();
        projectTypologyContainer= new ProjectTypologyContainer(typologies);
        typologies.add(projectTypologyOne);
        typologies.add(projectTypologyTwo);
        company = new Company(accountContainer, profileContainer,businessSectorContainer);
        projectTypologyController = new ProjectTypologyController(company);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        accountThree= null;
        profileOne = null;
        profileTwo = null;
        profileThree=null;
        accounts.clear();
        profiles.clear();
        accountContainer = null;
        profileContainer = null;
        businessSector=null;
        businessSectors.clear();
        businessSectorContainer=null;
        projectTypologyOne= null;
        projectTypologyTwo= null;
        typologies.clear();
        projectTypologyContainer=null;
        projectTypologyController=null;

        company = null;
    }
    /*@Test
    void addNewprojectTypologySuccessfully(){
        //Arrange
        boolean expected = true;
        accountOne.setProfile(profileOne);
        //Act
        boolean result = projectTypologyController.createProjectTypology("mike@isep.ipp.pt","Fixed new typology");
        //Assert
        assertEquals(expected,result);
    }*/
}