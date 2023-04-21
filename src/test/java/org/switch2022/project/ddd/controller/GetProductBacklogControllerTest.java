package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.application.ProjectService;
import org.switch2022.project.ddd.application.UsService;
import org.switch2022.project.ddd.domain.model.project.*;
import org.switch2022.project.ddd.domain.model.user_story.FactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.IFactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.ProjectCreationDto;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;
import org.switch2022.project.ddd.dto.UserStoryDto;
import org.switch2022.project.ddd.dto.mapper.UserStoryMapper;
import org.switch2022.project.ddd.infrastructure.ProjectRepository;
import org.switch2022.project.ddd.infrastructure.UsRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetProductBacklogControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running
     * the tests below.
     */

    GetProductBacklogController getProductBacklogController;
    UsService usService;
    ProjectService projectService;
    String projectCode;
    GetProductBacklogController getProductBacklogControllerOne;
    UsService usServiceOne;
    ProjectService projectServiceOne;
    IFactoryProject factoryProjectOne;
    ProjectRepository projectRepositoryOne;
    IFactoryProductBacklog factoryProductBacklogOne;
    UsRepository usRepositoryOne;
    IFactoryUserStory factoryUserStoryOne;
    UserStoryMapper userStoryMapperOne;
    Project projectOne;
    Project projectTwo;
    UserStory userStoryOne;
    UserStory userStoryTwo;
    UserStory userStoryThree;
    UserStory userStoryFour;
    UserStoryDto userStoryToDtoOne;
    UserStoryDto userStoryToDtoFour;


    @BeforeEach
    void setUp() throws Exception {
        //Services implemented
        usService = mock(UsService.class);
        projectService = mock(ProjectService.class);

        //Input
        projectCode = "P001";

        //UserStory Service
        usRepositoryOne = new UsRepository();
        factoryUserStoryOne = new FactoryUserStory();
        userStoryMapperOne = new UserStoryMapper();
        usServiceOne = new UsService(usRepositoryOne, factoryUserStoryOne, userStoryMapperOne);

        //Controller
        getProductBacklogController = new GetProductBacklogController(projectService, userStoryMapperOne);

        //Project Service
        factoryProjectOne = new FactoryProject();
        projectRepositoryOne = new ProjectRepository();
        factoryProductBacklogOne = new FactoryProductBacklog();
        projectServiceOne = new ProjectService(factoryProjectOne, projectRepositoryOne,
                factoryProductBacklogOne, usRepositoryOne);

        //Controller
        getProductBacklogControllerOne =
                new GetProductBacklogController(projectServiceOne, userStoryMapperOne);

        //UserStory
        userStoryOne = factoryUserStoryOne.createUserStory(new UserStoryCreationDto("US01",
                "userStoryText", "actor", 0), "P001");
        userStoryTwo = factoryUserStoryOne.createUserStory(new UserStoryCreationDto("US02",
                "userStoryText", "actor", 1), "P001");
        userStoryThree = factoryUserStoryOne.createUserStory(new UserStoryCreationDto("US03",
                "userStoryText", "actor", 2), "P001");
        userStoryFour = factoryUserStoryOne.createUserStory(new UserStoryCreationDto("US04",
                "userStoryText", "actor", 3), "P001");

        userStoryOne.setStatus(Status.PLANNED);
        userStoryTwo.setStatus(Status.FINISHED);
        userStoryThree.setStatus(Status.BLOCKED);
        userStoryFour.setStatus(Status.PLANNED);

        usRepositoryOne.add(userStoryOne);
        usRepositoryOne.add(userStoryTwo);
        usRepositoryOne.add(userStoryThree);
        usRepositoryOne.add(userStoryFour);

        //Project
        projectOne = factoryProjectOne.createProject(new Code(1), new ProjectCreationDto("projectName",
                        "projectDescription", "businessSectorName", "customerName",
                        "typologyName", 2), new BusinessSectorId(1), new CustomerId(1),
                new ProjectTypologyId(1), factoryProductBacklogOne);

        projectTwo = factoryProjectOne.createProject(new Code(2), new ProjectCreationDto("projectName",
                        "projectDescription", "businessSectorName", "customerName",
                        "typologyName", 2), new BusinessSectorId(1), new CustomerId(1),
                new ProjectTypologyId(1), factoryProductBacklogOne);

        projectRepositoryOne.addProjectToProjectRepository(projectOne);
        projectRepositoryOne.addProjectToProjectRepository(projectTwo);

        projectOne.addUserStory(0, new UsId("P001", "US01"));
        projectOne.addUserStory(1, new UsId("P001", "US02"));
        projectOne.addUserStory(2, new UsId("P001", "US03"));
        projectOne.addUserStory(3, new UsId("P001", "US04"));

    }

    @AfterEach
    void tearDown() {
        getProductBacklogController = null;
        usService = null;
        projectService = null;
        projectCode = null;
    }

    /**
     * Method: getProductBacklog()
     * Scenario 1: Tests the behavior of the getProductBacklog method in the
     * getProductBacklogController class when a null ProjectCode String is passed as input.
     * The method should throw an IllegalArgumentException with a message indicating that the
     * input parameter cannot be null.
     * The test first arranges the input by initializing the expected error message. Then it acts
     * by invoking the method with a null ProjectCode String and verifying that an
     * IllegalArgumentException with the
     * expected message is thrown. Finally, it asserts that the exception message matches the
     * expected message.
     *
     * Should throw an exception if the input parameters are null.
     */
    @Test
    void ensureThatIsReturnedAndThrowsAnExceptionIfProjectCodeIsNull() {
        // ARRANGE
        String expectedMessage = "Input parameter cannot be null.";

        // ACT
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                getProductBacklogController.getProductBacklog(null));

        // ASSERT
        assertEquals(expectedMessage, exception.getMessage());

    }

    /**
     * Scenario 2: This test verifies that none IllegalArgumentException is thrown with a not
     * null input parameter.
     */
    @Test
    void ensureThatIsNotReturnedAndThrowsAnExceptionIfProjectCodeIsNotNull() {
        // ACT
        try {
            getProductBacklogController.getProductBacklog("P001");
        } catch (Exception e) {
            // ASSERT
            fail("No exception should be thrown when the Project Code parameter was not null.");
        }

        // ASSERT
        assertTrue(true);
    }

    /**
     * Scenario 3: This test ensure that an empty UserStoryDto list is returned when the Product
     * Backlog of a give Project Code is also empty.
     *
     * Should return a UserStoryDto list
     */
    @Test
    void ensureThatAnEmptyUserStoryDtoListIsReturned() throws Exception {
        // ARRANGE
        String projectCode = "P001";
        List<UsId> emptyProductBacklog = new ArrayList<>();
        when(projectService.getProductBacklog(projectCode)).thenReturn(emptyProductBacklog);

        // ACT
        List<UserStoryDto> result = getProductBacklogController.getProductBacklog(projectCode);

        // ASSERT
        assertTrue(result.isEmpty());
    }

    /**
     * Scenario 4: This test ensure that an UserStoryDto list is returned when the Product
     * Backlog of a given Project Code has a list of planned User Stories.
     *
     * @UserStoryDto list
     */

    /*
    @Test
    void testGetProductBacklog() throws Exception {
        // ARRANGE
        String projectCode = "P001";
        List<UsId> productBacklog = Arrays.asList(new UsId("P001", "US001"), new UsId("P001",
                "US002"));
        List<UserStoryDto> expectedUserStoryList = Arrays.asList(new UserStoryDto("US001",
                        "create an User Story", "planned"),
                new UserStoryDto("US002", "create Product Backlog", "planned"));

        // Mock the getProductBacklog method of the projectService to return a non-empty list
        when(projectService.getProductBacklog(projectCode)).thenReturn(productBacklog);

        // Mock the requestAllPlannedUs method of the usService to return a list of UserStoryDto
        when(projectService.requestAllUserStories(productBacklog)).thenReturn(expectedUserStoryList);

        // ACT
        List<UserStoryDto> actualUserStoryDtoList =
                getProductBacklogController.getProductBacklog(projectCode);

        // ASSERT
        assertEquals(expectedUserStoryDtoList.size(), actualUserStoryDtoList.size());
        assertTrue(expectedUserStoryDtoList.containsAll(actualUserStoryDtoList));
        assertTrue(actualUserStoryDtoList.containsAll(expectedUserStoryDtoList));
    }*/

    /**
     * Method: getProductBacklog()
     * <p>
     * Scenario 1: This test ensure that is returned a list containing only User Stories with the
     * status Planned when the Product Backlog contains user stories from multiple status.
     */
    @Test
    void ensureThatIsReturnedAnOrderListOfPlannedUserStories() throws Exception {
        //ARRANGE
        userStoryOne.setStatus(Status.PLANNED);
        userStoryTwo.setStatus(Status.FINISHED);
        userStoryThree.setStatus(Status.BLOCKED);
        userStoryFour.setStatus(Status.PLANNED);

        userStoryToDtoOne = userStoryMapperOne.userStoryToDto(userStoryOne);
        userStoryToDtoFour = userStoryMapperOne.userStoryToDto(userStoryFour);

        List<UserStoryDto> expected = Arrays.asList(userStoryToDtoOne, userStoryToDtoFour);

        //ACT
        List<UserStoryDto> result = getProductBacklogControllerOne.getProductBacklog("P001");

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: This test ensure that an empty List is returned when the Product
     * Backlog of a give Project has no User Stories with Planned status.
     */
    @Test
    void ensureThatIsReturnedAnEmptyListOfUserStories_NoUserStoryWithPlannedStatus() throws Exception {
        //ARRANGE
        userStoryOne.setStatus(Status.RUNNING);
        userStoryTwo.setStatus(Status.FINISHED);
        userStoryThree.setStatus(Status.BLOCKED);
        userStoryFour.setStatus(Status.FINISHED);

        //ACT
        List<UserStoryDto> result = getProductBacklogControllerOne.getProductBacklog("P001");

        // ASSERT
        assertTrue(result.isEmpty());
    }

    /**
     * Scenario 3: This test ensure that an empty List is returned when the Product
     * Backlog of a give Project has no User Stories.
     */
    @Test
    void ensureThatIsReturnedAnEmptyListOfUserStories_ProductBacklogIsEmpty() throws Exception {

        //ACT
        List<UserStoryDto> result = getProductBacklogControllerOne.getProductBacklog("P002");

        // ASSERT
        assertTrue(result.isEmpty());
    }
}