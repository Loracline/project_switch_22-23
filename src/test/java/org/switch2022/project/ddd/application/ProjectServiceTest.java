package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.customer.ICustomerRepository;
import org.switch2022.project.ddd.domain.model.project.IFactoryProject;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.ProjectDto;
import org.switch2022.project.ddd.dto.UserStoryDto;
import org.switch2022.project.ddd.dto.mapper.ProjectMapper;
import org.switch2022.project.ddd.dto.mapper.UserStoryMapper;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ProjectService.class
)
class ProjectServiceTest {
    /**
     * BeforeEach execute common code before running the
     * tests below.
     */
    @InjectMocks
    ProjectService projectService;
    @MockBean
    IFactoryProject factoryProject;
    @MockBean
    @Qualifier("project_jpa")
    IProjectRepository projectRepository;
    @MockBean
    @Qualifier("us_jpa")
    IUsRepository usRepository;
    @MockBean
    ProjectMapper projectMapper;
    @MockBean
    @Qualifier("customer_jpa")
    ICustomerRepository customerRepository;
    @MockBean
    UserStoryMapper userStoryMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); }

    /**
     * Method: getProjectByCode
     * scenario 1: returns an optional
     */
    @Test
    void ensureProjectIsRetrieved() {
        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);
        when(projectRepository.findByCode(any())).thenReturn(optionalProject);
        //Act
        Optional<Project> result = projectService.getProjectByCode("P001");
        //Assert
        assertEquals(optionalProject, result);
    }

    /**
     * Method: getProductBacklog
     * scenario 1: returns a list of usId
     */
    @Test
    void ensureProductBacklogIsRetrievedSuccessfully() {
        //Arrange
        UsId usIdDouble = mock(UsId.class);
        UsId usIdDoubleTwo = mock(UsId.class);
        List<UsId> usIds = Arrays.asList(usIdDoubleTwo, usIdDouble);

        UserStory userStoryOne = mock(UserStory.class);
        List<UserStory> userStories = Arrays.asList(userStoryOne);

        UserStoryDto userStoryDto = mock(UserStoryDto.class);
        List<UserStoryDto> expected = Arrays.asList(userStoryDto);
        List<UserStoryDto> userStoryDtos = Arrays.asList(userStoryDto);

        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);

        when(projectRepository.findByCode(any())).thenReturn(optionalProject);
        when(projectDouble.getProductBacklog()).thenReturn(usIds);
        when(usRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStories);
        when(userStoryOne.hasStatus(any())).thenReturn(true);
        when(userStoryMapper.userStoryToDtoList(userStories)).thenReturn(userStoryDtos);

        //Act
        List<UserStoryDto> result = projectService.getProductBacklog("P001");
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 2: returns an empty list
     */
    @Test
    void ensureProductBacklogIsRetrievedSuccessfully_EmptyList() {
        //Arrange
        List<UsId> usIds = Arrays.asList(mock(UsId.class));
        List<UserStory> expected = new ArrayList<>();
        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);
        when(projectRepository.findByCode(any())).thenReturn(optionalProject);
        when(projectDouble.getProductBacklog()).thenReturn(usIds);
        when(userStoryMapper.userStoryToDtoList(expected)).thenReturn(Collections.emptyList());

        //Act
        List<UserStoryDto> result = projectService.getProductBacklog("P001");

        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 3: productBacklog is not retrieved because code doesn't match any project
     */
    @Test
    void ensureThatProductBacklogIsNotRetrievedBecauseCodeDoesNotMatchAnyProject() {
        //Arrange
        Optional<Project> optionalProject = Optional.empty();
        when(projectRepository.findByCode(any())).thenReturn(optionalProject);
        NotFoundInRepoException exception = assertThrows(NotFoundInRepoException.class, () ->
                projectService.getProductBacklog("P001"));
        String expected = "No project with that code";
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: requestAllPlannedUserStories(List<UsId> usId).
     * Requests a list of User Stories with matching usIds and "planned" status.
     * <br>
     * Scenario 01: returns a list of User Stories that own the corresponding UsIds and
     * "planned" status.
     */

    @Test
    void ensureThatAllUserStoriesWithPlannedStatusAreReturned() {
        //Arrange
        List<UsId> usIds = new ArrayList<>();
        UserStory userStory = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);

        List<UserStory> expected = new ArrayList<>();
        expected.add(userStory);
        expected.add(userStoryTwo);

        when(usRepository.getListOfUsWithMatchingIds(usIds)).thenReturn(expected);
        when(userStory.hasStatus(any())).thenReturn(true);
        when(userStoryTwo.hasStatus(any())).thenReturn(true);

        //Act
        List<UserStory> result = projectService.requestAllPlannedUserStories(usIds);

        //Assert
        assertEquals(expected, result);

    }

    /**
     * Scenario 02: returns an empty list when there are no UserStories.
     */

    @Test
    void ensureThatReturnsAnEmptyListIfThereAreNoUserStories() {
        //Arrange
        List<UsId> usIds = new ArrayList<>();
        List<UserStory> expected = new ArrayList<>();
        when(usRepository.getListOfUsWithMatchingIds(usIds)).thenReturn(expected);

        //Act
        List<UserStory> result = projectService.requestAllPlannedUserStories(usIds);

        //Assert
        assertTrue(result.isEmpty());

    }

    /**
     * Scenario 03: check if returns an empty list when there are no UserStories with planned
     * status.
     */
    @Test
    void ensureThatReturnsAnEmptyListBecauseThereAreNoUserStoriesWithPlannedStatus() {
        //Arrange
        List<UsId> usIds = new ArrayList<>();
        UserStory userStory = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);

        List<UserStory> expected = new ArrayList<>();

        when(usRepository.getListOfUsWithMatchingIds(usIds)).thenReturn(expected);
        when(userStory.hasStatus(any())).thenReturn(false);
        when(userStoryTwo.hasStatus(any())).thenReturn(false);

        //Act
        List<UserStory> result = projectService.requestAllPlannedUserStories(usIds);

        //Assert
        assertEquals(expected, result);

    }

    @Test
    void ensureProductBacklogIsRetrievedUnsuccessfully() {
        //Arrange
        UsId usIdDouble = mock(UsId.class);
        UsId usIdDoubleTwo = mock(UsId.class);
        List<UsId> usIds = Arrays.asList(usIdDoubleTwo, usIdDouble);

        UserStory userStoryOne = mock(UserStory.class);
        List<UserStory> userStories = Arrays.asList(userStoryOne);

        UserStoryDto userStoryDto = mock(UserStoryDto.class);
        List<UserStoryDto> expected = new ArrayList<>();
        List<UserStoryDto> userStoryDtos = Arrays.asList(userStoryDto);

        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);

        when(projectRepository.findByCode(any())).thenReturn(optionalProject);
        when(projectDouble.getProductBacklog()).thenReturn(usIds);
        when(usRepository.getListOfUsWithMatchingIds(any())).thenReturn(userStories);
        when(userStoryOne.hasStatus(any())).thenReturn(false);
        when(userStoryMapper.userStoryToDtoList(userStories)).thenReturn(userStoryDtos);

        //Act
        List<UserStoryDto> result = projectService.getProductBacklog("P001");
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method getProjectDto(code)
     * <p>
     * Scenario 1: Project dto is retrieved.
     */
    @Test
    void ensureThatProjectDtoISRetrievedSuccessfully() {
        // Arrange
        Project project = mock(Project.class);
        ProjectDto projectDto = mock(ProjectDto.class);
        Optional<Project> projectOptional = Optional.ofNullable(project);
        when(projectRepository.findByCode(any())).thenReturn(projectOptional);
        when(customerRepository.findCustomerNameByTaxId(any())).thenReturn("Isep");
        when(projectMapper.projectToDto(project, "Isep")).thenReturn(projectDto);

        // Act
        ProjectDto result = projectService.getProjectDto("P001");

        // Assert
        assertEquals(projectDto, result);
    }

    /**
     * Scenario 2: an exception is thrown because the project doesn't exist.
     */
    @Test
    void ensureThatAnExceptionIsThrownBecauseProjectDoesNotEXist() {
        // Arrange
        String message = "This project doesn't exist";
        Optional<Project> projectOptional = Optional.ofNullable(null);
        when(projectRepository.findByCode(any())).thenReturn(projectOptional);

        // Act
        NotFoundInRepoException exception = assertThrows(NotFoundInRepoException.class, () ->
                projectService.getProjectDto("P001"));

        // Assert
        assertEquals(message, exception.getMessage());
    }


}