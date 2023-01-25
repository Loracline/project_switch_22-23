package org.switch2022.project.model.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.dto.AllocationDTO;
import org.switch2022.project.utils.dto.ProjectDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountInProjectContainerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */
    AccountDTO accountDTO, accountDTO2, accountDTO3;
    ProjectDTO projectDTO;
    Project project;
    AllocationDTO allocationDTOPO, allocationDTOTM,
            allocationDTOInvalid, allocationDTOAllocationValid,
            allocationDTOAllocationInvalid;
    List<AccountInProject> accountsInProject;
    AccountInProjectContainer accountInProjectContainer;

    @BeforeEach
    void setUp() {
        // AccountDTOs created.
        accountDTO = new AccountDTO();
        accountDTO.name = "John";
        accountDTO.email = "john@isep.ipp.pt";
        accountDTO.phoneNumber = 912345678;
        accountDTO.photo = null;

        accountDTO2 = new AccountDTO();
        accountDTO2.name = "Anna";
        accountDTO2.email = "anna@isep.ipp.pt";
        accountDTO2.phoneNumber = 912345679;
        accountDTO2.photo = null;

        accountDTO3 = new AccountDTO();
        accountDTO3.name = "Anna";
        accountDTO3.email = "anna@isep.ipp.pt";
        accountDTO3.phoneNumber = 912345679;
        accountDTO3.photo = null;

        // ProjectDTO created.
        Customer customer = new Customer("IT Customer");
        ProjectTypology projectTypology = new ProjectTypology("fixed cost");
        BusinessSector businessSector = new BusinessSector("IT Sector");
        projectDTO = new ProjectDTO("id001", "Test", customer, projectTypology, businessSector);

        //Project created
        project = new Project("proj001", "software development management", customer,
                projectTypology, businessSector);

        // Container of accounts in project created.
        accountsInProject = new ArrayList<>();
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);
    }

    @AfterEach
    void tearDown() {
        accountDTO = null;
        accountDTO2 = null;
        projectDTO = null;
        allocationDTOPO = null;
        accountsInProject.clear();
        accountInProjectContainer = null;
    }

    /**
     * Testing if one is able to add account to project with valid role.
     */
    @Test
    void ensureThatProductOwnerIsAddedToAccountsInProjects() {
        //Assert
        Account account =  new Account("Claire", "claire@isep.ipp.pt", 932755689,null);
        allocationDTOPO = new AllocationDTO();
        allocationDTOPO.role = "Product Owner";
        allocationDTOPO.costPerHour = 7.5f;
        allocationDTOPO.percentageAllocation = 45.0f;
        allocationDTOPO.startDate = LocalDate.of(2023, 1, 19);

        //Act
        boolean result = accountInProjectContainer.addUserToProject(account, project,
                allocationDTOPO);
        //Assert
        assertTrue(result);
    }

    @Test
    void ensureThatAccountWithInvalidRoleIsNotAddedToAccountsInProjects() {
        //Assert
        allocationDTOInvalid = new AllocationDTO();
        allocationDTOInvalid.accountDTO = accountDTO;
        allocationDTOInvalid.projectDTO = projectDTO;
        allocationDTOInvalid.role = "Product Visionary";
        allocationDTOInvalid.costPerHour = 7.5f;
        allocationDTOInvalid.percentageAllocation = 45.0f;
        allocationDTOInvalid.startDate = LocalDate.of(2023, 1, 15);
        allocationDTOInvalid.endDate = LocalDate.of(2023, 1, 22);
        //Act
        boolean result = accountInProjectContainer.addUserToProject(allocationDTOInvalid);
        //Assert
        assertFalse(result);
    }

    @Test
    void ensureThatTeamMemberIsAddedToAccountsInProjects() {
        //Assert
        allocationDTOTM = new AllocationDTO();
        allocationDTOTM.accountDTO = accountDTO2;
        allocationDTOTM.projectDTO = projectDTO;
        allocationDTOTM.role = "Team Member";
        allocationDTOTM.costPerHour = 6.5f;
        allocationDTOTM.percentageAllocation = 45.0f;
        allocationDTOTM.startDate = LocalDate.of(2023, 1, 19);
        allocationDTOTM.endDate = LocalDate.of(2023, 1, 22);
        //Act
        boolean result = accountInProjectContainer.addUserToProject(allocationDTOTM);
        //Assert
        assertTrue(result);
    }

    /**
     * Testing if the sum of the current percentages of allocation of a user is returned.
     */
    @Test
    void ensureThatTheSumOfTheCurrentPercentageOfAllocationIsReturned() {
        //Arrange
        Account account = new Account("Claire", "claire@isep.ipp.pt", 932755689, null);
        LocalDate startDateOne = LocalDate.of(2023, 1, 19);
        LocalDate startDateTwo = LocalDate.of(2023, 1, 25);
        LocalDate startDateThree = LocalDate.of(2023, 1, 30);
        AccountInProject accountInProjectOne = new AccountInProject(account, project,
                "Team Member",7.5f, 45.0f, startDateOne);
        AccountInProject accountInProjectTwo = new AccountInProject(account, project,
                "Scrum Master",7.5f, 45.0f, startDateTwo);
        AccountInProject accountInProjectThree = new AccountInProject(account, project,
                "Team Member",7.5f, 2.5f, startDateThree);

        accountsInProject.add(accountInProjectOne);
        accountsInProject.add(accountInProjectTwo);
        accountsInProject.add(accountInProjectThree);

        float expected = 92.5f;
        //Act
        float result = accountInProjectContainer.currentPercentageOfAllocation();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Testing if the total percentage of allocation (current percentages in all
     * project  + new percentage to be added) is valid or not (i.e., does not exceed
     * 100%).
     */

    @Test
    void ensureThatTotalPercentageOfAllocationIsValid() {
        //Arrange
        Account account = new Account("Claire", "claire@isep.ipp.pt", 932755689, null);
        LocalDate startDateOne = LocalDate.of(2023, 1, 19);
        LocalDate startDateTwo = LocalDate.of(2023, 1, 25);
        LocalDate startDateThree = LocalDate.of(2023, 1, 30);
        AccountInProject accountInProjectOne = new AccountInProject(account, project,
                "Team Member",7.5f, 45.0f, startDateOne);
        AccountInProject accountInProjectTwo = new AccountInProject(account, project,
                "Scrum Master",7.5f, 45.0f, startDateTwo);
        AccountInProject accountInProjectThree = new AccountInProject(account, project,
                "Team Member",7.5f, 2.5f, startDateThree);

        accountsInProject.add(accountInProjectOne);
        accountsInProject.add(accountInProjectTwo);
        accountsInProject.add(accountInProjectThree);

        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);
        AccountInProject newAccountInProject = new AccountInProject(account, project,
                "Team Member",7.5f, 2.5f, startDateOne);
        float newPercentageOfAllocation =  newAccountInProject.getPercentageOfAllocation();

        //Act
        boolean result =
                accountInProjectContainer.isPercentageOfAllocationValid(newPercentageOfAllocation);
        //Assert
        assertTrue(result);
    }

    @Test
    void ensureThatTotalPercentageOfAllocationIsInvalid() {
        //Arrange
        Account account = new Account("Claire", "claire@isep.ipp.pt", 932755689, null);
        LocalDate startDateOne = LocalDate.of(2023, 1, 19);
        LocalDate startDateTwo = LocalDate.of(2023, 1, 25);
        LocalDate startDateThree = LocalDate.of(2023, 1, 30);
        AccountInProject accountInProjectOne = new AccountInProject(account, project,
                "Team Member",7.5f, 45.0f, startDateOne);
        AccountInProject accountInProjectTwo = new AccountInProject(account, project,
                "Scrum Master",7.5f, 45.0f, startDateTwo);
        AccountInProject accountInProjectThree = new AccountInProject(account, project,
                "Team Member",7.5f, 2.5f, startDateThree);

        accountsInProject.add(accountInProjectOne);
        accountsInProject.add(accountInProjectTwo);
        accountsInProject.add(accountInProjectThree);

        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);
        AccountInProject newAccountInProject = new AccountInProject(account, project,
                "Team Member",7.5f, 15f, startDateOne);
        float newPercentageOfAllocation =  newAccountInProject.getPercentageOfAllocation();

        //Act
        boolean result =
                accountInProjectContainer.isPercentageOfAllocationValid(newPercentageOfAllocation);
        //Assert
        assertFalse(result);
    }

    /**
     * Testing if an account is allocated to a project if the total percentage of
     * allocation (current percentages in all project + new percentage to be added)
     * is valid (i.e., does not exceed 100%).
     */

    @Test
    void ensureThatAccountIsAllocatedToProjectIfTotalPercentageOfAllocationIsValid() {
        //Arrange
        Account account = new Account("Claire", "claire@isep.ipp.pt", 932755689, null);
        LocalDate startDateOne = LocalDate.of(2023, 1, 19);
        LocalDate startDateTwo = LocalDate.of(2023, 1, 25);
        LocalDate startDateThree = LocalDate.of(2023, 1, 30);
        AccountInProject accountInProjectOne = new AccountInProject(account, project,
                "Team Member",7.5f, 45.0f, startDateOne);
        AccountInProject accountInProjectTwo = new AccountInProject(account, project,
                "Scrum Master",7.5f, 45.0f, startDateTwo);
        AccountInProject accountInProjectThree = new AccountInProject(account, project,
                "Team Member",7.5f, 2.5f, startDateThree);

        accountsInProject.add(accountInProjectOne);
        accountsInProject.add(accountInProjectTwo);
        accountsInProject.add(accountInProjectThree);

        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

        allocationDTOAllocationValid = new AllocationDTO();
        allocationDTOAllocationValid.accountDTO = accountDTO3;
        allocationDTOAllocationValid.projectDTO = projectDTO;
        allocationDTOAllocationValid.role = "Team Member";
        allocationDTOAllocationValid.costPerHour = 6.5f;
        allocationDTOAllocationValid.percentageAllocation = 2.5f;
        allocationDTOAllocationValid.startDate = LocalDate.of(2023, 1, 29);
        allocationDTOAllocationValid.endDate = LocalDate.of(2023, 1, 31);

        //Act
        boolean result =
                accountInProjectContainer.addUserToProject(allocationDTOAllocationValid);
        //Assert
        assertTrue(result);
    }

    /**
     * Testing if an account is NOT allocated to a project if the total percentage of
     * allocation (current percentages in all project + new percentage to be added)
     * is NOT valid (i.e., does not exceed 100%).
     */

    @Test
    void ensureThatAccountIsNotAllocatedToProjectIfTotalPercentageOfAllocationIsInvalid() {
        //Arrange
        Account account = new Account("Claire", "claire@isep.ipp.pt", 932755689, null);
        LocalDate startDateOne = LocalDate.of(2023, 1, 19);
        LocalDate startDateTwo = LocalDate.of(2023, 1, 25);
        LocalDate startDateThree = LocalDate.of(2023, 1, 30);
        AccountInProject accountInProjectOne = new AccountInProject(account, project,
                "Team Member",7.5f, 45.0f, startDateOne);
        AccountInProject accountInProjectTwo = new AccountInProject(account, project,
                "Scrum Master",7.5f, 45.0f, startDateTwo);
        AccountInProject accountInProjectThree = new AccountInProject(account, project,
                "Team Member",7.5f, 2.5f, startDateThree);

        accountsInProject.add(accountInProjectOne);
        accountsInProject.add(accountInProjectTwo);
        accountsInProject.add(accountInProjectThree);

        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);


        allocationDTOAllocationInvalid = new AllocationDTO();
        allocationDTOAllocationInvalid.accountDTO = accountDTO3;
        allocationDTOAllocationInvalid.projectDTO = projectDTO;
        allocationDTOAllocationInvalid.role = "Team Member";
        allocationDTOAllocationInvalid.costPerHour = 6.5f;
        allocationDTOAllocationInvalid.percentageAllocation = 15f;
        allocationDTOAllocationInvalid.startDate = LocalDate.of(2023, 1, 29);
        allocationDTOAllocationInvalid.endDate = LocalDate.of(2023, 1, 31);

        //Act
        boolean result =
                accountInProjectContainer.addUserToProject(allocationDTOAllocationInvalid);
        //Assert
        assertFalse(result);
    }

}