package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.UsService;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
     * Scenario 1: Tests the behavior of the createUs method in the CreateUserStoryController class
     * when a null UserStoryCreationDto object is passed as input. The method should throw an
     * IllegalArgumentException with a message indicating that the input parameters cannot be null.
     *
     * @throws IllegalArgumentException if the input parameters are null.
     */
    @Test
    void ensureCreateUsThrowsExceptionWithNullUserStoryCreationDto() {
        // Arrange
        CreateUserStoryController controller = new CreateUserStoryController();

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controller.createUs( null));

        // Assert
        assertEquals("User Story Creation Dto", exception.getMessage());
    }

    /**
     * Scenario 2: This test verifies the behavior of the createUs method in the
     * CreateUserStoryController class when a valid UserStoryCreationDto object is passed as input.
     * The method should successfully create a User Story and return a User Story Id. It verifies
     * that a User Story is created successfully by mocking the necessary dependencies,
     * setting up the required test data, and asserting that the result is not null.
     */
    @Test
    void ensureUserStoryIsCreatedSuccessfully() throws Exception {
        // Arrange
        UsId usId = mock(UsId.class);
        String projectCode = "P001";
        String acceptanceCriteria = "E-mail must have an @ and a dot";
        userStoryCreationDto = new UserStoryCreationDto("P001","US001",
                "As a user, I want to be able to log in to the system.", "User",
                singletonList(acceptanceCriteria), 1);

        when(usService.createUs(userStoryCreationDto)).thenReturn(usId);

        // Act
        UsId result = controller.createUs( userStoryCreationDto);

        // Assert
        assertNotNull(result);
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
