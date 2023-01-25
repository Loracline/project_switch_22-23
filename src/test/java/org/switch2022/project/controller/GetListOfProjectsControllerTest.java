package org.switch2022.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.AccountContainer;
import org.switch2022.project.model.container.ProjectContainer;
import org.switch2022.project.utils.dto.GetProjectDTO;
import org.switch2022.project.utils.mapper.ListOfProjectsMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetListOfProjectsControllerTest {

    private GetListOfProjectsController controller;

    @BeforeEach
    void setUp() {
        Account userAccount = new Account("Paul", "paul@isep.ipp.pt", 939855689, null);

        Account managerAccount = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        managerAccount.setProfile(new Profile("Manager"));

        List<Account> accounts = new ArrayList<>();
        accounts.add(managerAccount);
        accounts.add(userAccount);
        AccountContainer accountContainer = new AccountContainer(accounts);

        Customer customer = new Customer("ISEP", "222333444");
        ProjectTypology projectTypology = new ProjectTypology("Fixed cost");
        BusinessSector businessSector = new BusinessSector("fishing");

        Project projectOne = new Project("AA001", "software development management", new Customer("ISEP"),
                new ProjectTypology("Fixed Cost"), new BusinessSector("fishing"));
        Project projectTwo = new Project("AA002", "project software", new Customer("ISEP"),
                new ProjectTypology("Fixed Cost"), new BusinessSector("fishing"));

        List<Project> projects = new ArrayList<>();
        projects.add(projectOne);
        projects.add(projectTwo);
        ProjectContainer projectContainer = new ProjectContainer(projects);

        Company company = new Company(accountContainer, null, null,
                projectContainer, null, null, null);

        ListOfProjectsMapper mapper = new ListOfProjectsMapper();

        controller = new GetListOfProjectsController(company, mapper);
    }

   @Test
    void ensureAllProjectsAreListedWhenRequestedByManager() {
        // Arrange
        GetProjectDTO projectDTOOne = new GetProjectDTO("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        GetProjectDTO projectDTOTwo = new GetProjectDTO("AA002", "project software", "isep", "planned",
            "fixed cost", "fishing");

        List<GetProjectDTO> expectDTOs = new ArrayList<>();
        expectDTOs.add(projectDTOOne);
        expectDTOs.add(projectDTOTwo);

        // Act
        List<GetProjectDTO> result = controller.getListOfProjects("mike@isep.ipp.pt");

        // Assert
        assertEquals(expectDTOs, result);
    }

    @Test
    void ensureNoProjectsAreListedWhenRequestedByAnotherProfile() {
        // Arrange
        List<GetProjectDTO> expectDTOs = new ArrayList<>();

        // Act
        List<GetProjectDTO> result = controller.getListOfProjects("paul@isep.ipp.pt");

        // Assert
        assertEquals(expectDTOs, result);
    }

}