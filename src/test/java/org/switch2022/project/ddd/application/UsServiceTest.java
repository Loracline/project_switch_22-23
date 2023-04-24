package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.IFactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.mapper.UserStoryMapper;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = UsServiceTest.class
)
class UsServiceTest {

    @MockBean
    IFactoryUserStory factoryUserStory;
    @MockBean
    IUsRepository usRepository;
    @MockBean
    IProjectRepository projectRepository;
    @MockBean
    UserStoryMapper userStoryMapper;

    @InjectMocks
    UsService usService;


//    UserStoryCreationDto userStoryCreationDtoOne, userStoryCreationDtoTwo,
//            userStoryCreationDtoThree;
//    UserStoryDto userStoryDtoOne;
//    UsId usIdOne, usIdTwo, usIdThree;
//    UsService usServiceOne;

//    UsRepository usRepositoryOne,usRepositoryTwo;

//    List<UsId> usIds, usIdsOne;

//    List<UserStoryDto> userStoriesDto;


    /**
     * BeforeEach executes common code before running the tests below.
     */

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);


//        //Us ID
//        usIdOne = new UsId("P001", "US001");
//        usIdTwo = new UsId("P001","US002");
//        usIdThree = new UsId("P001", "US003");
//
//        //US Service
//        usServiceOne = new UsService(usRepositoryOne, factoryUserStory, userStoryMapper);
//
//        //User Stories ID
//        usIds= new ArrayList<>();
//        usIds.add(usIdOne);
//        usIds.add(usIdTwo);
//        usIds.add(usIdThree);
//
//        usIdsOne= new ArrayList<>();
//
//        //User Story Dto
//        userStoryDtoOne= new UserStoryDto("us001","text","planned");
//
//
//        //User Stories Dto
//        userStoriesDto= new ArrayList<>();
//        userStoriesDto.add(userStoryDtoOne);
//
//
//
//        //User Stories Creation Dto
//        userStoryCreationDtoOne = new UserStoryCreationDto("US001", "text", "actor", 0);
//        userStoryCreationDtoTwo= new UserStoryCreationDto("US002", "textTwo","actorTwo", 2);
//        userStoryCreationDtoThree= new UserStoryCreationDto("US003", "textThree", "actorThree", 3);
//
//
//        // US Repository
//        usRepositoryTwo = new UsRepository();
//
//
//        //User Stories Dto
    }

    //UNIT TESTS

    /**
     * Method: createUs(userStoryCreationDto, projectCode).
     * Creates a userStory and return the userStoryId.
     * <br>
     * Scenario 01: verify if a userStory is created and its ID returned.
     * <p>
     * Expected result: userStoryId is returned.
     */


    @Test
    void ensureUsIsCreated() throws Exception {
        // Arrange
        UsNumber userStoryNumberDouble = mock(UsNumber.class);
        UsText userStoryTextDouble = mock(UsText.class);
        Actor actorDouble = mock(Actor.class);
        int priority = 1;
        AcceptanceCriteria acceptanceCriteriaElementDouble = mock(AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);


        UserStory userStoryDouble = mock(UserStory.class);
        Code projectCode = new Code(1);
        when(factoryUserStory.createUserStory(userStoryNumberDouble, userStoryTextDouble, actorDouble, priority, acceptanceCriteriaDouble,
                projectCode)).thenReturn(userStoryDouble);
        when(userStoryDouble.getUsId()).thenReturn("P001_US003");
        when(userStoryDouble.getUsNumber()).thenReturn("US003");

        usRepository.add(userStoryDouble);

        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);
        when(projectRepository.getProjectByCode(projectCode)).thenReturn(optionalProject);
        UsId usId = new UsId("P001","US003");
        when(projectDouble.addUserStory(priority, usId)).thenReturn(true);

        usRepository.add(userStoryDouble);

        // Act
        boolean result = usService.createUs(userStoryNumberDouble, userStoryTextDouble, actorDouble, priority,
                acceptanceCriteriaDouble, projectCode);

        // Assert
        assertTrue(result);
    }


    /**
     * Scenario 02: verify if a userStory is not created and its ID not returned.
     * <p>
     * Expected result: exception is thrown.
     */


    @Test
    void ensureUsIsNotCreated() throws Exception {
        // Arrange
        UsNumber userStoryNumberDouble = mock(UsNumber.class);
        UsText userStoryTextDouble = mock(UsText.class);
        Actor actorDouble = mock(Actor.class);
        int priority = 1;
        AcceptanceCriteria acceptanceCriteriaElementDouble = mock(AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);

        UserStory userStoryDouble = mock(UserStory.class);
        Code projectCode = new Code(1);

        // Act
        when(factoryUserStory.createUserStory(userStoryNumberDouble, userStoryTextDouble, actorDouble, priority, acceptanceCriteriaDouble, projectCode))
                .thenReturn(userStoryDouble);

        usRepository.add(userStoryDouble);

        doThrow(new IllegalStateException("User Story ID already exists")).when(usRepository).
                add(userStoryDouble);

        // Assert
        assertThrows(IllegalStateException.class, () -> usService.createUs(userStoryNumberDouble, userStoryTextDouble, actorDouble, priority, acceptanceCriteriaDouble,
                projectCode));

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
    void ensureUsIsNotDeleted() throws Exception {
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
    void ensureUsIsDeleted() throws Exception {
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
    void ensureUsIdIsAddedSuccessfullyToProductBacklog() throws Exception {
        //Arrange
        boolean expected = true;
        UsId usIdDouble = mock(UsId.class);
        Code projectCode = new Code(1);
        int priority = 1;
        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);
        when(projectRepository.getProjectByCode(any())).thenReturn(optionalProject);
        when(projectDouble.addUserStory(priority, usIdDouble)).thenReturn(true);

        //Act
        boolean result = usService.addUsToProductBacklog(usIdDouble, projectCode, priority);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: doesn't add an usID to ProductBacklog, because the id is already in the product backlog
     */
    @Test
    void ensureUsIdIsNotAddedSuccessfullyToProductBacklog() {
        //Arrange
        UsId usIdDouble = mock(UsId.class);
        Code projectCode = new Code(1);
        int priority = 1;
        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);
        when(projectRepository.getProjectByCode(any())).thenReturn(optionalProject);
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
     * Scenario 3: doesn't add an usID to ProductBacklog, because the projectCode doesn't match any project
     */
    @Test
    void ensureUsIdIsNotAddedSuccessfullyToProductBacklog_NoProject() {
        //Arrange
        UsId usIdDouble = mock(UsId.class);
        Code projectCode = new Code(1);
        int priority = 1;
        Optional<Project> optionalProject = Optional.empty();
        when(projectRepository.getProjectByCode(any())).thenReturn(optionalProject);
        Exception exception = assertThrows(Exception.class, () ->
                usService.addUsToProductBacklog(usIdDouble, projectCode, priority));
        String expected = "No project with that code";
        //Act
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    //INTEGRATION TESTS

    /* *//**
     * Method: createUs(userStoryCreationDto, projectCode).
     * Creates a userStory and return the userStoryId.
     * <p>
     * Scenario 01: verify if a userStory is created and added to a list of User Stories.
     * <p>
     * Expected result: userStoryId is returned.
     *//*
    @Test
    void ensureThatUserStoryIsCreatedAndAddedToRepository() throws Exception {
        //ARRANGE
        UsId expected = new UsId("P001", "US001");;

        //ACT

        userStoryCreationDtoOne = new UserStoryCreationDto("US001", "text", "actor",
                0);
        UsId result = usService.createUs(userStoryCreationDtoOne, "P001");

        //ASSERT
        assertEquals(expected, result);
    }*/
    /*

     *//**
     * Scenario 02: verify if a userStory is not created when already exist.
     * <p>
     * Expected result: exception is thrown.
     *//*
    @Test
    void ensureThatUserStoryIsNotCreatedAndAddedToUserStoryRepository() throws Exception {
        //ARRANGE
        usService.createUs(userStoryCreationDtoOne, "P001");


        //ACT and ASSERT
        assertThrows(IllegalArgumentException.class, () ->
                usService.createUs(userStoryCreationDtoOne, "P001"));
    }

    *//**
     * Method: deleteUs(UsId).
     * Deletes a userStory.
     * <br>
     * Scenario 01: verify if a userStory is not deleted because it is not there.
     * <p>
     * Expected result: tHrow an IllegalArgumentException.
     *//*
    @Test
    void ensureThatUserStoryIsNotDeletedFromRepositoryBecauseIsNotThere() {
        //ARRANGE
        UsId expected = usIdOne;

        //ACT and ASSERT
        assertThrows(IllegalArgumentException.class, () -> usService.deleteUs(expected));

    }

    *//**
     * Scenario 02: verify if a userStory is deleted.
     * <p>
     * Expected result: true.
     *//*
    @Test
    void ensureThatUserStoryIsDeletedFromRepository() throws Exception {
        //ARRANGE
        UsId expected = usIdOne;
        usService.createUs(userStoryCreationDtoOne, "P001");

        //ACT
        boolean result = usService.deleteUs(expected);

        //ASSERT
        assertTrue(result);
    }
    */
}