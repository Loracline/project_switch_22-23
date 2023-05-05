package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.UsService;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CreateUserStoryController.class)
class CreateUserStoryControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running
     * the tests below.
     */

    @MockBean
    UsService usService;

    @InjectMocks
    CreateUserStoryController controller;

    UserStoryCreationDto userStoryCreationDto;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Method: createUs()
     * Scenario 1: Tests the behavior of the createUs method in the CreateUsController class
     * when a null projectCodeOfInterest string is passed as input.
     * The method should throw an IllegalArgumentException with a message indicating that the
     * input parameters cannot be null.
     * The test first arranges the input by initializing the expected error message. Then it acts
     * by invoking the method with a null projectCodeOfInterest string and verifying that an
     * IllegalArgumentException with the expected message is thrown. Finally, it asserts that the
     * exception message matches the expected message.
     *
     * @throws IllegalArgumentException if the input parameters are null.
     */

    @Test
    void ensureCreateUsCopyThrowsExceptionWithNullProjectDto() {
        // Arrange
        String expectedMessage = "The Project Code Of Interest must not be null";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controller.createUs(null, userStoryCreationDto));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());

    }

    /**
     * Scenario 2: Tests the behavior of the createUs method in the CreateUsController class
     * when
     * a null
     * UserStoryCreationDto object is passed as input. The method should throw an
     * IllegalArgumentException with
     * a message indicating that the input parameters cannot be null.
     *
     * @throws IllegalArgumentException if the input parameters are null.
     */
    @Test
    void ensureCreateUsThrowsExceptionWithNullUserStoryCreationDto() {
        // Arrange
        CreateUserStoryController controller = new CreateUserStoryController();
        String projectCodeOfInterest = "P001";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controller.createUs(projectCodeOfInterest, null));

        // Assert
        assertEquals("User Story Creation Dto", exception.getMessage());
    }

    /**
     * Scenario 3: This test verifies the behavior of the createUs method in the
     * CreateUsController class when a valid UserStoryCreationDto object is passed as input.
     * The method should successfully create a User Story and return true. The test arranges the
     * input by creating mock objects for the UsService, and setting up
     * the appropriate
     * mock method behavior to return a UsId and true when called.
     * Then it acts by invoking the method and verifying that it returns true. Finally, it
     * asserts that the
     * result is true.
     */
    @Test
    void ensureUserStoryIsCreatedSuccessfully() throws Exception {
        // Arrange
        String projectCode = "P001";
        String acceptanceCriteria = "E-mail must have an @ and a dot";
        userStoryCreationDto = new UserStoryCreationDto("US001",
                "As a user, I want to be able to log in to the system.", "User",
                singletonList(acceptanceCriteria), 1);

        UsNumber userStoryNumber = new UsNumber(userStoryCreationDto.userStoryNumber);
        UsText userStoryText = new UsText(userStoryCreationDto.userStoryText);
        Actor actor = new Actor(userStoryCreationDto.actor);
        int priority = userStoryCreationDto.priority;
        List<AcceptanceCriteria> acceptanceCriteriaList = singletonList(new AcceptanceCriteria(acceptanceCriteria));
        Code projectCodeObj = new Code(1);

        when(usService.createUs(userStoryNumber, userStoryText, actor, priority,
                acceptanceCriteriaList, projectCodeObj)).thenReturn(true);

        // Act
        boolean result = controller.createUs(projectCode, userStoryCreationDto);

        // Assert
        assertTrue(result);
    }

//
//        // Acceptance Criteria Tests
//
//        /**
//         * Method: createUs()
//         * Scenario 1: Fails to create a US due to insufficient/invalid information, like
//         * nonexistent
//         * project.
//         * It should return true.
//         */
//        @Test
//        void ensureUserStoryIsNotCreatedDueToInvalidInformation () throws Exception {
//            //ARRANGE
//            ProjectDto projectDtoOne = new ProjectDto("AA001", "software development management",
//                    "isep", "planned", "fixed cost", "fishing");
//
//            //ACT
//            boolean result = mockCreateUsController.createUs(projectDtoOne, userStoryCreationDto);
//
//            //ASSERT
//            assertTrue(result);
//        }
//
//        /**
//         * Scenario 2: Fails to create a US because it has the same number of another in a
//         non-empty
//         * backlog.
//         * It should throw an exception with the message "The User Story is already in the Product
//         * Backlog".
//         */
//        @Test
//        void ensureUserStoryIsNotCreated_UserStoryRepeated () throws Exception {
//            //ARRANGE
//            ProjectCreationDto projectCreationDto = new ProjectCreationDto("projectName",
//                    "projectDescription", "businessSectorName",
//                    "customerName", "typologyName", 2);
//
//            Project project = factoryProject.createProject(new Code(1), projectCreationDto,
//                    new BusinessSectorId(1), new CustomerId(1),
//                    new ProjectTypologyId(1), new FactoryProductBacklog());
//
//            projectRepository.addProjectToProjectRepository(project);
//
//            UsId usId = new UsId("P001", "US001");
//            projectService.addUsToProductBacklog(usId, "P001", 0);
//
//            ProjectDto projectDto = new ProjectDto("P001", null, null, null,
//                    null, null);
//
//            String expectedMessage = "The User Story is already in the Product Backlog";
//
//            //ACT
//            Exception exception = assertThrows(Exception.class, () ->
//                    createUsController.createUs(projectDto, userStoryCreationDtoOne));
//
//            //ASSERT
//            assertEquals(expectedMessage, exception.getMessage());
//        }
//
//        /**
//         * Scenario 3: Create US and add it to an empty backlog.
//         * It should return true.
//         */
//        @Test
//        void ensureUserStoryIsCreatedAndAddedToEmptyBacklog () throws Exception {
//            //ARRANGE
//            ProjectCreationDto projectCreationDto = new ProjectCreationDto("projectName",
//                    "projectDescription", "businessSectorName",
//                    "customerName", "typologyName", 2);
//
//            Project project = factoryProject.createProject(new Code(1), projectCreationDto,
//                    new BusinessSectorId(1), new CustomerId(1),
//                    new ProjectTypologyId(1), new FactoryProductBacklog());
//
//            projectRepository.addProjectToProjectRepository(project);
//
//            ProjectDto projectDto = new ProjectDto("P001", null, null, null,
//                    null, null);
//
//            //ACT
//            boolean result = createUsController.createUs(projectDto, userStoryCreationDtoOne);
//
//            //ASSERT
//            assertTrue(result);
//        }
}
