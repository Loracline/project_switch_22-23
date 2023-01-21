package org.switch2022.project.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.dto.AccountInProjectDTO;
import org.switch2022.project.utils.dto.ProjectDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountInProjectDTOMapperTest {

    AccountInProjectDTOMapper mapper;
    AccountDTO accountDTO;
    ProjectDTO projectDTO;
    AccountInProjectDTO accountInProjectDTOPO;
    AccountInProject accountInProject;
    Project project;
    Account account;

    @BeforeEach
    void setUp() {
        //set up account dto
        accountDTO = new AccountDTO();
        accountDTO.name = "John";
        accountDTO.email = "john@isep.ipp.pt";
        accountDTO.phoneNumber = 912345678;
        accountDTO.photo = null;
        accountDTO.profile = new Profile("User");
        accountDTO.status = true;

        //set up project dto
        Customer customer = new Customer("IT Customer");
        ProjectTypology projectTypology = new ProjectTypology("fixed cost");
        BusinessSector businessSector = new BusinessSector("IT Sector");
        projectDTO = new ProjectDTO("id001", "Test", customer, projectTypology, businessSector);
        //set up account in project dto
        accountInProjectDTOPO = new AccountInProjectDTO();
        accountInProjectDTOPO.accountDTO = accountDTO;
        accountInProjectDTOPO.projectDTO = projectDTO;
        accountInProjectDTOPO.role = "Product Owner";
        accountInProjectDTOPO.costPerHour = 7.5f;
        accountInProjectDTOPO.percentageAllocation = 45.0f;
        accountInProjectDTOPO.startDate = LocalDate.of(2023, 01, 19);
        accountInProjectDTOPO.endDate = LocalDate.of(2023, 01, 22);
    }

    @AfterEach
    void tearDown() {
        accountDTO = null;
        projectDTO = null;
        accountInProjectDTOPO = null;
    }

    /*@Test
    void ensuresAccountInProjectTeamMemberIsSuccessfullyCreated() {
        //Arrange
        AccountInProjectDTO dto = new AccountInProjectDTO();
        dto.account = account;
        dto.project = project;
        dto.costPerHour = costPerHour;
        dto.percentageAllocation = percentageAllocation;
        dto.startDate = startDate;
        AccountInProjectDTOMapper mapper = new AccountInProjectDTOMapper();
        AccountInProject expected = accountInProject;

        //Act
        AccountInProject result = mapper.accountInProjectFromDTO(dto);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensuresAccountInProjectTeamMemberIsNotCreated() {
        //Arrange
        AccountInProjectDTO dto = new AccountInProjectDTO();
        dto.account = account;
        dto.project = project;
        dto.costPerHour = costPerHour;
        dto.percentageAllocation = percentageAllocation;
        dto.startDate = LocalDate.of(2023,02,19);
        AccountInProjectDTOMapper mapper = new AccountInProjectDTOMapper();
        AccountInProject expected = accountInProject;

        //Act
        AccountInProject result = mapper.accountInProjectFromDTO(dto);

        //Assert
        assertNotEquals(expected, result);
    }*/

    @Test
    void ensureAccountIsSuccessfullyTransformedFromDTO() {
        //Arrange
        account = new Account(accountDTO.name, accountDTO.email, accountDTO.phoneNumber
                , accountDTO.photo);

        project = new Project(projectDTO.code, projectDTO.name, projectDTO.customer,
                projectDTO.projectTypology, projectDTO.businessSector);

        AccountInProject expected = new AccountInProject(account, project, "Product " +
                "Owner", 7.5f, 45.0f, LocalDate.of(2023, 01, 19));

        mapper = new AccountInProjectDTOMapper();

        //Act
        AccountInProject result =
                mapper.accountInProjectFromDTO(accountInProjectDTOPO);
        //Assert
        assertEquals(expected, result);
    }
}