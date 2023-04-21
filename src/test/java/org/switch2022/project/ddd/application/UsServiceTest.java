package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.FactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.IFactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
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

class UsServiceTest {


    UserStoryCreationDto userStoryCreationDtoOne, userStoryCreationDtoTwo,userStoryCreationDtoThree;
    UserStoryDto userStoryDtoOne;
    UsId usIdOne, usIdTwo, usIdThree;
    UsService usService, usServiceOne;

    UsRepository usRepository, usRepositoryOne, usRepositoryTwo;

    IFactoryUserStory factoryUserStory;

    UserStoryMapper userStoryMapper;

    List<UsId> usIds, usIdsOne;

    List<UserStoryDto> userStoriesDto;



    /**
     * BeforeEach executes common code before running the tests below.
     */
    @BeforeEach

    void setUp() {

        //Us ID
        usIdOne = new UsId("P001", "US001");
        usIdTwo = new UsId("P001","US002");
        usIdThree = new UsId("P001", "US003");

        //US Repository
        usRepository = new UsRepository();
        usRepositoryOne = new UsRepository();

        // IFactoryUserStory
        factoryUserStory = new FactoryUserStory();

        //US Mapper
        userStoryMapper = new UserStoryMapper();

        //US Service
        usService = new UsService(usRepository, factoryUserStory, userStoryMapper);
        usServiceOne = new UsService(usRepositoryOne, factoryUserStory, userStoryMapper);

        //User Stories ID
        usIds= new ArrayList<>();
        usIds.add(usIdOne);
        usIds.add(usIdTwo);
        usIds.add(usIdThree);

        usIdsOne= new ArrayList<>();

        //User Story Dto
        userStoryDtoOne= new UserStoryDto("us001","text","planned");


        //User Stories Dto
        userStoriesDto= new ArrayList<>();
        userStoriesDto.add(userStoryDtoOne);



        //User Stories Creation Dto
        userStoryCreationDtoOne = new UserStoryCreationDto("US001", "text", "actor", 0);
        userStoryCreationDtoTwo= new UserStoryCreationDto("US002", "textTwo","actorTwo", 2);
        userStoryCreationDtoThree= new UserStoryCreationDto("US003", "textThree", "actorThree", 3);


        // US Repository
        usRepositoryTwo = new UsRepository();


        //User Stories Dto

    }

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
        IUsRepository usRepositoryDouble = mock(IUsRepository.class);
        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        UserStoryMapper userStoryMapperDouble = mock(UserStoryMapper.class);

        UsService usService = new UsService(usRepositoryDouble, factoryUserStoryDouble, userStoryMapperDouble);

        UserStoryCreationDto userStoryCreationDtoDouble = mock(UserStoryCreationDto.class);
        UserStory userStoryDouble = mock(UserStory.class);

        // Act
        when(factoryUserStoryDouble.createUserStory(userStoryCreationDtoDouble, "P001"))
                .thenReturn(userStoryDouble);

        usRepositoryDouble.add(userStoryDouble);

        UsId expected = userStoryDouble.getUsId();
        UsId result = usService.createUs(userStoryCreationDtoDouble, "P001");

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
        IUsRepository usRepositoryDouble = mock(IUsRepository.class);
        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        UserStoryMapper userStoryMapperDouble = mock(UserStoryMapper.class);

        UsService usService = new UsService(usRepositoryDouble, factoryUserStoryDouble, userStoryMapperDouble);

        UserStoryCreationDto userStoryCreationDtoDouble = mock(UserStoryCreationDto.class);
        UserStory userStoryDouble = mock(UserStory.class);

        // Act
        when(factoryUserStoryDouble.createUserStory(userStoryCreationDtoDouble, "P001"))
                .thenReturn(userStoryDouble);

        usRepositoryDouble.add(userStoryDouble);

        doThrow(new IllegalStateException("User Story ID already exists")).when(usRepositoryDouble).
                add(userStoryDouble);

        // Assert
        assertThrows(IllegalStateException.class, () -> usService.createUs(userStoryCreationDtoDouble,
                "P001"));

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
        UsService usServiceDouble = mock(UsService.class);
        UsRepository usRepositoryDouble = mock(UsRepository.class);
        UsId usIdDouble = mock(UsId.class);

        // Act
        when(usRepositoryDouble.getListOfUsWithMatchingIds(anyList())).thenReturn(Collections.singletonList(mock(UserStory.class)));
        usServiceDouble.deleteUs(usIdDouble);

        // Assert
        verify(usServiceDouble).deleteUs(usIdDouble);
    }

    /**
     * Constructor
     * <p>
     * Scenario 1: usRepository is null.
     */

    @Test
    void ensureUsRepositoryIsNull() {
        // Arrange
        UsRepository usRepository = null;
        FactoryUserStory factoryUserStory = new FactoryUserStory();
        UserStoryMapper userStoryMapper = new UserStoryMapper();

        // Act
        Exception exception = assertThrows(Exception.class, () ->
                new UsService(usRepository, factoryUserStory, userStoryMapper));

        String expected = "User Story Repository can't be null";
        String result = exception.getMessage();

        // Act
        assertEquals(expected, result);
    }

    /**
     * Constructor
     * <p>
     * Scenario 2: factoryRepository is null.
     */

    @Test
    void ensureFactoryUserStoryIsNull() {
        // Arrange
        UsRepository usRepository = new UsRepository();
        FactoryUserStory factoryUserStory = null;
        UserStoryMapper userStoryMapper = new UserStoryMapper();

        // Act
        Exception exception = assertThrows(Exception.class, () ->
                new UsService(usRepository, factoryUserStory, userStoryMapper));

        String expected = "Factory User Story can't be null";
        String result = exception.getMessage();

        // Act
        assertEquals(expected, result);
    }

    /**
     * Constructor
     * <p>
     * Scenario 3: userStoryMapper is null.
     */

    @Test
    void ensureUserStoryMapperIsNull() {
        // Arrange
        UsRepository usRepository = new UsRepository();
        FactoryUserStory factoryUserStory = new FactoryUserStory();
        UserStoryMapper userStoryMapper = null;

        // Act
        Exception exception = assertThrows(Exception.class, () ->
                new UsService(usRepository, factoryUserStory, userStoryMapper));

        String expected = "User Story Mapper can't be null";
        String result = exception.getMessage();

        // Act
        assertEquals(expected, result);
    }

    /**
     * Method: createUs(userStoryCreationDto, projectCode).
     * Creates a userStory and return the userStoryId.
     * <p>
     * Scenario 01: verify if a userStory is created and added to a list of User Stories.
     * <p>
     * Expected result: userStoryId is returned.
     */
    @Test
    void ensureThatUserStoryIsCreatedAndAddedToRepository() throws Exception {
        //ARRANGE
        UsId expected = usIdOne;

        //ACT
        UsId result = usService.createUs(userStoryCreationDtoOne, "P001");

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 02: verify if a userStory is not created when already exist.
     * <p>
     * Expected result: exception is thrown.
     */
    @Test
    void ensureThatUserStoryIsNotCreatedAndAddedToUserStoryRepository() throws Exception {
        //ARRANGE
        usService.createUs(userStoryCreationDtoOne, "P001");


        //ACT and ASSERT
        assertThrows(IllegalArgumentException.class, () ->
                usService.createUs(userStoryCreationDtoOne, "P001"));
    }

    /**
     * Method: deleteUs(UsId).
     * Deletes a userStory.
     * <br>
     * Scenario 01: verify if a userStory is not deleted because it is not there.
     * <p>
     * Expected result: tHrow an IllegalArgumentException.
     */
    @Test
    void ensureThatUserStoryIsNotDeletedFromRepositoryBecauseIsNotThere() {
        //ARRANGE
        UsId expected = usIdOne;

        //ACT and ASSERT
        assertThrows(IllegalArgumentException.class, () -> usService.deleteUs(expected));

    }

    /**
     * Scenario 02: verify if a userStory is deleted.
     * <p>
     * Expected result: true.
     */
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
}
