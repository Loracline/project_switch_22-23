package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.*;
import org.switch2022.project.dto.ProjectCreationDto;
import org.switch2022.project.dto.ProjectDto;
import org.switch2022.project.dto.UserStoryCreationDto;
import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.factories.*;
import org.switch2022.project.model.ProductBacklog;
import org.switch2022.project.model.UserStory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetProductBacklogControllerTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running
     * the tests below.
     */

    ProjectCreationDto projectCreationDto;
    ProjectDto projectDto, wrongCodeProjectDto;
    UserStoryCreationDto userStoryCreationDtoOne, userStoryCreationDtoTwo;
    UserStoryDto userStoryDtoOne, userStoryDtoTwo;

    BusinessSectorContainer businessSectorContainer;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectContainer projectContainer;
    CustomerContainer customerContainer;
    Company company;
    GetProductBacklogController getProductBacklogController;
    IFactoryProject factoryProject;
    IFactoryUserStory factoryUserStory;
    IFactoryProductBacklog factoryProductBacklog;

    IFactoryPeriod factoryPeriod;
    IFactorySprintBacklog factorySprintBacklog;
    IFactorySprint factorySprint;

    @BeforeEach
    void setUp() {
        //Interfaces implemented
        factoryProductBacklog = new FactoryProductBacklog();
        factoryProject = new FactoryProject();
        factoryUserStory = new FactoryUserStory();
        factoryPeriod = new FactoryPeriod();
        factorySprintBacklog = new FactorySprintBacklog();
        factorySprint = new FactorySprint();

        //Dtos
        userStoryCreationDtoOne = new UserStoryCreationDto("US001",
                "I want to create a project", "Manager", 0);
        userStoryCreationDtoTwo = new UserStoryCreationDto("US002",
                "I want to create a project", "Manager", 1);

        userStoryDtoOne = new UserStoryDto("US001",
                "I want to create a project", "Planned");
        userStoryDtoTwo = new UserStoryDto("US002",
                "I want to create a project", "Planned");

        projectDto = new ProjectDto("AA001", "software development " +
                "management", "Peter", "228674498",
                "Fixed cost", "Fishing");

        wrongCodeProjectDto = new ProjectDto("AA020", "software development " +
                "management", "Peter", "228674498",
                "Fixed cost", "Fishing");

        projectCreationDto = new ProjectCreationDto("AA001", "software development " +
                "management", "Peter", "228674498",
                "Fixed cost", "Fishing");

        //Containers
        projectTypologyContainer = new ProjectTypologyContainer();
        customerContainer = new CustomerContainer();
        businessSectorContainer = new BusinessSectorContainer();
        projectContainer = new ProjectContainer();

        customerContainer.addCustomer(projectCreationDto.customerName,
                projectCreationDto.customerNif);
        projectTypologyContainer.createProjectTypology(
                projectCreationDto.projectTypology);
        businessSectorContainer.createBusinessSector(projectCreationDto.projectTypology);

        projectContainer.registerProject(projectCreationDto, projectTypologyContainer,
                customerContainer,
                businessSectorContainer, factoryProductBacklog, factoryUserStory,
                factoryProject,
                factoryPeriod, factorySprintBacklog, factorySprint);

        projectContainer.createUserStory(projectDto, userStoryCreationDtoOne);
        projectContainer.createUserStory(projectDto, userStoryCreationDtoTwo);

        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                projectContainer, projectTypologyContainer, null, customerContainer);

        getProductBacklogController = new GetProductBacklogController(company);
    }

    @AfterEach
    void tearDown() {
        factoryProductBacklog = null;
        factoryProject = null;
        factoryUserStory = null;
        factoryPeriod = null;
        factorySprintBacklog = null;
        factorySprint = null;
        userStoryCreationDtoOne = null;
        projectDto = null;
        wrongCodeProjectDto = null;
        accountContainer = null;
        profileContainer = null;
        projectTypologyContainer = null;
        customerContainer = null;
        businessSectorContainer = null;
        projectContainer = null;
        projectCreationDto = null;
        company = null;
        getProductBacklogController = null;
    }

    //UNIT TESTS

    /**
     * METHOD getProductBacklog - with isolation
     * Scenario 1: This test ensures that a list of User Stories Dto extracted from a
     * Product Backlog of a Project is returned when a given projectCode match with an
     * existent Project.
     */

    @Test
    void ensureThatProductBacklogIsSuccessfullyReturnedIfProjectCodeExists_WithIsolation() {
        //ARRANGE
        ProjectDto projectDto = mock(ProjectDto.class);
        when(projectDto.getProjectCode()).thenReturn("AA001");

        Optional<ProductBacklog> productBacklogOptional = mock(Optional.class);
        Company company = mock(Company.class);
        when(company.getProductBacklog("AA001")).thenReturn(productBacklogOptional);

        when(productBacklogOptional.isPresent()).thenReturn(true);

        ProductBacklog productBacklog = mock(ProductBacklog.class);

        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);

        when(userStoryOne.getUserStoryNumber()).thenReturn("US001");
        when(userStoryOne.getUserStoryText()).thenReturn("I want to create a project");
        when(userStoryOne.getStatus()).thenReturn("planned");

        when(userStoryTwo.getUserStoryNumber()).thenReturn("US002");
        when(userStoryTwo.getUserStoryText()).thenReturn("I want to create a user " +
                "story");
        when(userStoryTwo.getStatus()).thenReturn("planned");

        List<UserStory> userStories = new ArrayList<>();
        userStories.add(userStoryOne);
        userStories.add(userStoryTwo);

        when(productBacklog.getUserStoriesCopy()).thenReturn(userStories);

        when(productBacklogOptional.get()).thenReturn(productBacklog);

        List<UserStoryDto> expected = new ArrayList<>();
        expected.add(userStoryDtoOne);
        expected.add(userStoryDtoTwo);

        //ACT
        List<UserStoryDto> result = getProductBacklogController.getProductBacklog(
                projectDto);

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * METHOD getProductBacklog - with isolation
     * Scenario 2: This test ensures that an empty list of User Stories Dto extracted
     * from a Product Backlog of a Project is returned when a given projectCode doesn't
     * match any existent Project.
     */

    @Test
    void ensureThatProductBacklogIsNotSuccessfullyReturnedIfProjectCodeDoesNotExist_WithIsolation() {
        //ARRANGE
        ProjectDto projectDto = mock(ProjectDto.class);
        when(projectDto.getProjectCode()).thenReturn("AA002");

        Optional<ProductBacklog> productBacklogOptional = mock(Optional.class);
        Company company = mock(Company.class);
        when(company.getProductBacklog("AA002")).thenReturn(productBacklogOptional);

        when(productBacklogOptional.isPresent()).thenReturn(false);

        ProductBacklog productBacklog = mock(ProductBacklog.class);

        List<UserStory> userStories = new ArrayList<>();

        when(productBacklog.getUserStoriesCopy()).thenReturn(userStories);

        when(productBacklogOptional.get()).thenReturn(productBacklog);

        List<UserStoryDto> expected = new ArrayList<>();

        //ACT
        List<UserStoryDto> result = getProductBacklogController.getProductBacklog(
                projectDto);

        //ASSERT
        assertEquals(expected, result);
    }

    //INTEGRATION TESTS

    /**
     * METHOD getProductBacklog
     * Scenario 1: This test ensures that a list of User Stories Dto extracted from a
     * Product Backlog of a Project is returned when a given projectCode match with an
     * existent Project.
     */

    @Test
    void ensureThatProductBacklogIsSuccessfullyReturnedIfProjectCodeExists() {
        //ARRANGE
        List<UserStoryDto> expected = new ArrayList<>();
        expected.add(userStoryDtoOne);
        expected.add(userStoryDtoTwo);

        //ACT
        List<UserStoryDto> result = getProductBacklogController.getProductBacklog(
                projectDto);

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * METHOD getProductBacklog
     * Scenario 2: This test ensures that an empty list of User Stories Dto extracted
     * from a Product Backlog of a Project is returned when a given projectCode doesn't
     * match any existent Project.
     */

    @Test
    void ensureThatProductBacklogIsNotReturnedIfProjectCodeDoesNotMatchAnyProject() {
        //ARRANGE
        List<UserStoryDto> expected = new ArrayList<>();

        //ACT
        List<UserStoryDto> result =
                getProductBacklogController.getProductBacklog(wrongCodeProjectDto);

        //ASSERT
        assertEquals(expected, result);
    }
}