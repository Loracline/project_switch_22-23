package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.user_story.IFactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.AcceptanceCriteria;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;
import org.switch2022.project.ddd.dto.mapper.UserStoryMapper;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = UsServiceTest.class
)
class UsServiceTest {

    @MockBean
    IFactoryUserStory factoryUserStory;
    @MockBean
    @Qualifier("us_jpa")
    IUsRepository usRepository;
    @MockBean
    IProjectRepository projectRepository;
    @MockBean
    UserStoryMapper userStoryMapper;
    @InjectMocks
    UsService usService;

    /**
     * BeforeEach executes common code before running the tests below.
     */

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

    }

    //UNIT TESTS

    /**
     * Method: createUs(userStoryCreationDto).
     * Creates a userStory and return the userStoryId.
     * <br>
     * Scenario 01: verify if a userStory is created and its ID returned.
     * <p>
     * Expected result: UsId is returned.
     */

    @Test
    void ensureUsIsCreated() {
        // Arrange
        int priority = 1;
        UserStory userStoryDouble = mock(UserStory.class);
        Code code = new Code(1);
        when(factoryUserStory.createUserStory(any(), any(), any(), any(), any())).thenReturn(userStoryDouble);
        when(userStoryDouble.getUsNumber()).thenReturn("US003");

        Project projectDouble = mock(Project.class);
        when(projectRepository.findByCode(code)).thenReturn(Optional.of(projectDouble));
        when(projectDouble.addUserStory(priority, new UsId("P001", "US003"))).thenReturn(true);

        List<String> acceptanceCriteria = new ArrayList<>();

        UserStoryCreationDto userStoryCreationDto =
                new UserStoryCreationDto("P001", "1", "text", "manager", acceptanceCriteria, 1);

        // Act
        UsId result = usService.createUs(userStoryCreationDto);

        // Assert
        assertNotNull(result);
        assertTrue(true);
    }

    /**
     * Scenario 02: verify if a userStory is not created and its ID not returned.
     * <p>
     * Expected result: exception is thrown.
     */
    @Test
    void ensureUsIsNotCreated() {
        // Arrange
        AcceptanceCriteria acceptanceCriteriaElementDouble = mock(AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);

        UserStory userStoryDouble = mock(UserStory.class);

        List<String> acceptanceCriteria = new ArrayList<>();

        UserStoryCreationDto userStoryCreationDto =
                new UserStoryCreationDto("P001", "1", "text", "manager", acceptanceCriteria, 1);

        // Act
        when(factoryUserStory.createUserStory(any(), any(), any(), any(), any()))
                .thenReturn(userStoryDouble);
        when(userStoryDouble.getUsNumber()).thenReturn("us001");

        usRepository.save(userStoryDouble);
        when(usRepository.existsByUsId(any())).thenReturn(true);

        // Assert
        assertThrows(AlreadyExistsInRepoException.class, () -> usService.createUs(
                userStoryCreationDto));

    }

    /**
     * Scenario 03: verify if a userStory is not created and its ID not returned, due to being present in the
     * productBacklog
     * <p>
     * Expected result: exception is thrown.
     */
    @Test
    void ensureUsIsNotCreated_UsInProductBacklog() {
        // Arrange
        AcceptanceCriteria acceptanceCriteriaElementDouble = mock(AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);

        UserStory userStoryDouble = mock(UserStory.class);

        List<String> acceptanceCriteria = new ArrayList<>();

        UserStoryCreationDto userStoryCreationDto =
                new UserStoryCreationDto("P001", "1", "text", "manager",
                        acceptanceCriteria, 1);

        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);

        // Act
        when(factoryUserStory.createUserStory(any(), any(), any(), any(), any()))
                .thenReturn(userStoryDouble);
        when(userStoryDouble.getUsNumber()).thenReturn("us001");

        usRepository.save(userStoryDouble);
        when(usRepository.existsByUsId(any())).thenReturn(false);
        when(projectRepository.findByCode(any())).thenReturn(optionalProject);
        when(projectDouble.addUserStory(anyInt(), any())).thenReturn(false);


        // Assert
        assertThrows(AlreadyExistsInRepoException.class, () -> usService.createUs(
                userStoryCreationDto));

    }

    /**
     * Method: createUs(userStoryCreationDto).
     * Creates a userStory and return the userStoryId.
     * <br>
     * Scenario 4: verify if a userStory is created and its ID returned.
     * <p>
     * Expected result: UsId is returned.
     */

    @Test
    void ensureUsIsCreated_withAcceptanceCriteria() {
        // Arrange
        int priority = 1;
        UserStory userStoryDouble = mock(UserStory.class);
        Code code = new Code(1);


        when(factoryUserStory.createUserStory(any(), any(), any(), any(), any())).thenReturn(userStoryDouble);
        when(userStoryDouble.getUsNumber()).thenReturn("US003");

        Project projectDouble = mock(Project.class);
        when(projectRepository.findByCode(code)).thenReturn(Optional.of(projectDouble));
        when(projectDouble.addUserStory(priority, new UsId("P001", "US003"))).thenReturn(true);

        List<String> acceptanceCriteria = new ArrayList<>();
        acceptanceCriteria.add("Hoje eu comi pipoca com sal");
        acceptanceCriteria.add("Fiquei todo empanzinado");
        acceptanceCriteria.add("Estou mau mau mau mau");

        UserStoryCreationDto userStoryCreationDto =
                new UserStoryCreationDto("P001", "1", "text", "manager", acceptanceCriteria, 1);

        // Act
        UsId result = usService.createUs(userStoryCreationDto);

        // Assert
        assertNotNull(result);
        assertTrue(true);
    }


    /**
     * Method: delete(userStoryDto).
     * Deletes a userStory.
     * <br>
     * Scenario 01: verify if a userStory is not deleted.
     * <p>
     * Expected result: userStory is not deleted.
     */

    @Test
    void ensureUsIsNotDeleted() {
        // Arrange
        UsService usServiceDouble = mock(UsService.class);
        UsId usIdDouble = mock(UsId.class);

        // Act
        doThrow(new IllegalStateException("User story does not exist")).when(usServiceDouble).
                deleteUs(usIdDouble);

        // Assert
        assertThrows(IllegalStateException.class, () -> usServiceDouble.deleteUs(usIdDouble));
    }


    /**
     * Scenario 02: verify if a userStory is deleted.
     * <p>
     * Expected result: userStory is deleted.
     */

    @Test
    void ensureUsIsDeleted() {
        // Arrange
        UsId usIdDouble = mock(UsId.class);

        // Act
        when(usRepository.getListOfUsWithMatchingIds(anyList())).thenReturn(Collections.singletonList(mock(UserStory.class)));
        boolean result = usService.deleteUs(usIdDouble);

        // Assert
        assertTrue(result);
    }

    /**
     * Method: addToProductBacklog
     * scenario 1: it adds an usId to the ProductBacklog
     */
    @Test
    void ensureUsIdIsAddedSuccessfullyToProductBacklog() {
        //Arrange
        boolean expected = true;
        UsId usIdDouble = mock(UsId.class);
        Code projectCode = new Code(1);
        int priority = 1;
        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);
        when(projectRepository.findByCode(any())).thenReturn(optionalProject);
        when(projectDouble.addUserStory(priority, usIdDouble)).thenReturn(true);

        //Act
        boolean result = usService.addUsToProductBacklog(usIdDouble, projectCode, priority);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: doesn't add an usID to ProductBacklog, because the id is already in the
     * product backlog
     */
    @Test
    void ensureUsIdIsNotAddedSuccessfullyToProductBacklog() {
        //Arrange
        UsId usIdDouble = mock(UsId.class);
        Code projectCode = new Code(1);
        int priority = 1;
        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);
        when(projectRepository.findByCode(any())).thenReturn(optionalProject);
        when(projectDouble.addUserStory(priority, usIdDouble)).thenReturn(false);
        Exception exception = assertThrows(Exception.class, () ->
                usService.addUsToProductBacklog(usIdDouble, projectCode, priority));
        String expected = "The User Story is already in the Product Backlog";
        //Act
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: doesn't add an usID to ProductBacklog, because the projectCode doesn't match
     * any project
     */
    @Test
    void ensureUsIdIsNotAddedSuccessfullyToProductBacklog_NoProject() {
        //Arrange
        UsId usIdDouble = mock(UsId.class);
        Code projectCode = new Code(1);
        int priority = 1;
        Optional<Project> optionalProject = Optional.empty();
        when(projectRepository.findByCode(any())).thenReturn(optionalProject);
        Exception exception = assertThrows(Exception.class, () ->
                usService.addUsToProductBacklog(usIdDouble, projectCode, priority));
        String expected = "No project with that code";
        //Act
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

}