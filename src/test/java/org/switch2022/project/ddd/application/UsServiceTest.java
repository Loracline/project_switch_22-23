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
import org.switch2022.project.ddd.dto.UserStoryCreationDto;
import org.switch2022.project.ddd.dto.UserStoryDto;
import org.switch2022.project.ddd.dto.mapper.UserStoryMapper;
import org.switch2022.project.ddd.infrastructure.UsRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
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

        UserStory userStoryDouble = mock(UserStory.class);
        Code projectCode = new Code(1);
        when(factoryUserStory.createUserStory(userStoryNumberDouble, userStoryTextDouble, actorDouble, priority ,
                projectCode)).thenReturn(userStoryDouble);
        when(userStoryDouble.getUsId()).thenReturn("P001_US003");
        when(userStoryDouble.getUsNumber()).thenReturn("US003");

        usRepository.add(userStoryDouble);

        UsId expected = new UsId("P001","US003");

        // Act
        UsId result = usService.createUs(userStoryNumberDouble, userStoryTextDouble, actorDouble, priority, projectCode);

        // Assert
        assertEquals(expected, result);
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

        UserStory userStoryDouble = mock(UserStory.class);
        Code projectCode = new Code(1);

        // Act
        when(factoryUserStory.createUserStory(userStoryNumberDouble, userStoryTextDouble, actorDouble, priority, projectCode))
                .thenReturn(userStoryDouble);

        usRepository.add(userStoryDouble);

        doThrow(new IllegalStateException("User Story ID already exists")).when(usRepository).
                add(userStoryDouble);

        // Assert
        assertThrows(IllegalStateException.class, () -> usService.createUs(userStoryNumberDouble, userStoryTextDouble, actorDouble, priority,
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
     * Method: requestAllPlannedUs(usId).
     * Returns a list of all userStories with the Status "PLANNED" through the userStory ID.
     * <br>
     * Scenario 01: verify if a list of userStories is retrieved.
     * <p>
     * Expected result: userStories list is retrieved.
     */


    @Test
    void ensureRequestOfAllPlannedUsIsSuccessful() {
        // Arrange
        UserStory userStoryDoubleOne = mock(UserStory.class);
        UserStoryDto userStoryDtoDoubleOne = mock(UserStoryDto.class);

        List<UsId> usIdDoubleList = new ArrayList<>();
        List<UserStoryDto> expected = new ArrayList<>();
        expected.add(userStoryDtoDoubleOne);

        when(usRepository.getListOfUsWithMatchingIds(usIdDoubleList)).
                thenReturn(Collections.singletonList(userStoryDoubleOne));
        when(userStoryDoubleOne.hasStatus(Status.PLANNED)).thenReturn(true);
        when(userStoryMapper.userStoryToDto(userStoryDoubleOne)).thenReturn(userStoryDtoDoubleOne);

        // Act
        List<UserStoryDto> result = usService.requestAllPlannedUs(usIdDoubleList);

        // Assert
        assertEquals(expected, result);
    }


    /**
     * Scenario 02: verify if a list of empty userStories is retrieved, an exception is thrown.
     * <p>
     * Expected result: exception is thrown.
     */

    @Test
    void ensureRequestOfAllPlannedUsIsSuccessfulEmptyList(){
        // Arrange

        List<UsId> usIdDoubleList = new ArrayList<>();
        List<UserStory> userStoriesList = new ArrayList<>();

        when(usRepository.getListOfUsWithMatchingIds(usIdDoubleList)).
                thenReturn(userStoriesList);

        List<UserStoryDto> emptyList = new ArrayList<>();

        // Assert
        assertEquals(userStoriesList, emptyList);
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
    *//**
     * Method: requestAllPlannedUs(List<UsId> usId).
     * Requests All Planned UserStories and resturn a list of User Stories Dto.
     * <br>
     * Scenario 01: checks that the list of all planned user stories and the list of Dto user stories are identical
     *//*
    @Test
    void ensureThatReturnsAListOfPlannedUserStories() throws Exception{
        //Arrange
        usService.createUs(userStoryCreationDtoOne,"P001");
        usService.createUs(userStoryCreationDtoTwo, "P001");
        usService.createUs(userStoryCreationDtoThree, "P001");
        UserStory userStoryTwo =(usRepository.getUserStory(usIdTwo)).get();
        UserStory userStoryThree =(usRepository.getUserStory(usIdThree)).get();

        userStoryTwo.setStatus(Status.FINISHED);
        userStoryThree.setStatus(Status.BLOCKED);

        List<UserStoryDto> expected = userStoriesDto;

        //Act
        List<UserStoryDto> result = usService.requestAllPlannedUs(usIds);

        //Assert
        assertEquals(expected, result);

    }

    *//**
     * Scenario 02: check if returns an empty list when there are no UserStories.
     *//*
    @Test
    void ensureThatReturnsAnEmptyListIfThereAreNoUserStories()  {

        //Act
        List<UserStoryDto> result = usService.requestAllPlannedUs(usIdsOne);

        //Assert
        assertTrue(result.isEmpty());

    }

    *//**
     * Scenario 03: check if returns an empty list when there are no UserStories with planned status.
     *//*
    @Test
    void ensureThatReturnsAnEmptyListBecauseThereAreNoPlannedUserStories() throws Exception {
        //Arrange
        usService.createUs(userStoryCreationDtoOne,"P001");
        usService.createUs(userStoryCreationDtoTwo, "P001");
        usService.createUs(userStoryCreationDtoThree, "P001");
        UserStory userStoryOne =(usRepository.getUserStory(usIdOne)).get();
        UserStory userStoryTwo =(usRepository.getUserStory(usIdTwo)).get();
        UserStory userStoryThree =(usRepository.getUserStory(usIdThree)).get();

        userStoryOne.setStatus(Status.RUNNING);
        userStoryTwo.setStatus(Status.FINISHED);
        userStoryThree.setStatus(Status.BLOCKED);

        //Act
        List<UserStoryDto> result = usService.requestAllPlannedUs(usIds);

        //Assert
        assertTrue(result.isEmpty());

    }


*/
}