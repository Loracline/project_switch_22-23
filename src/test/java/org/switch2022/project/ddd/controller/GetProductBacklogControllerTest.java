package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.application.ProjectService;
import org.switch2022.project.ddd.application.UsService;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.UserStoryDto;

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

    UserStoryDto userStoryDto;
    String projectCode;


    @BeforeEach
    void setUp() {
        //Services implemented
        usService = mock(UsService.class);
        projectService = mock(ProjectService.class);

        //Input
        projectCode = "P001";

        //Controller
        getProductBacklogController = new GetProductBacklogController(projectService, usService);

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
     * @throwsIllegalArgumentException if the input parameters are null.
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
     * @empty UserStoryDto list
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
     * Backlog of a give Project Code has a lis of planned User Stories.
     *
     * @UserStoryDto list
     */
    @Test
    void testGetProductBacklog() throws Exception {
        // ARRANGE
        String projectCode = "P001";
        List<UsId> productBacklog = Arrays.asList(new UsId("P001", "US001"), new UsId("P001",
                "US002"));
        List<UserStoryDto> expectedUserStoryDtoList = Arrays.asList(new UserStoryDto("US001",
                        "create an User Story", "planned"),
                new UserStoryDto("US002", "create Product Backlog", "planned"));

        // Mock the getProductBacklog method of the projectService to return a non-empty list
        when(projectService.getProductBacklog(projectCode)).thenReturn(productBacklog);

        // Mock the requestAllPlannedUs method of the usService to return a list of UserStoryDto
        when(usService.requestAllPlannedUs(productBacklog)).thenReturn(expectedUserStoryDtoList);

        // ACT
        List<UserStoryDto> actualUserStoryDtoList =
                getProductBacklogController.getProductBacklog(projectCode);

        // ASSERT
        assertEquals(expectedUserStoryDtoList.size(), actualUserStoryDtoList.size());
        assertTrue(expectedUserStoryDtoList.containsAll(actualUserStoryDtoList));
        assertTrue(actualUserStoryDtoList.containsAll(expectedUserStoryDtoList));
    }
}