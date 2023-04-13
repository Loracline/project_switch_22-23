package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.interfaces.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.FactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.IFactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.Status;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;
import org.switch2022.project.ddd.dto.UserStoryDto;
import org.switch2022.project.ddd.dto.mapper.UserStoryMapper;
import org.switch2022.project.ddd.infrastructure.UsRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UsServiceTest {


    UserStoryCreationDto userStoryCreationDto;
    UsId usIdOne;
    UsService usService, usServiceOne;

    UsRepository usRepository, usRepositoryOne;

    IFactoryUserStory factoryUserStory;

    UserStoryMapper userStoryMapper;

    /**
     * BeforeEach executes common code before running the tests below.
     */
    @BeforeEach

    void setUp() {
        userStoryCreationDto = new UserStoryCreationDto("US001", "text", "actor", 0);
        usIdOne = new UsId("P001", "US001");
        usRepository = new UsRepository();
        usRepositoryOne = new UsRepository();
        factoryUserStory = new FactoryUserStory();
        userStoryMapper = new UserStoryMapper();
        usService = new UsService(usRepository, factoryUserStory, userStoryMapper);
        usServiceOne = new UsService(usRepositoryOne, factoryUserStory, userStoryMapper);
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
     * Method: requestAllPlannedUs(usId).
     * Returns a list of all userStories with the Status "PLANNED" through the userStory ID.
     * <br>
     * Scenario 01: verify if a list of userStories is retrieved.
     * <p>
     * Expected result: userStories list is retrieved.
     */


    @Test
    void ensureRequestOfAllPlannedUsIsSuccessful() throws Exception {
        // Arrange
        IUsRepository usRepositoryDouble = mock(IUsRepository.class);
        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        UserStoryMapper userStoryMapperDouble = mock(UserStoryMapper.class);
        UsService usServiceDouble = new UsService(usRepositoryDouble, factoryUserStoryDouble, userStoryMapperDouble);

        UserStory userStoryDoubleOne = mock(UserStory.class);
        UserStoryDto userStoryDtoDoubleOne = mock(UserStoryDto.class);

        List<UsId> usIdDoubleList = new ArrayList<>();
        List<UserStoryDto> expected = new ArrayList<>();
        expected.add(userStoryDtoDoubleOne);

        when(usRepositoryDouble.getListOfUsWithMatchingIds(usIdDoubleList)).
                thenReturn(Collections.singletonList(userStoryDoubleOne));
        when(userStoryDoubleOne.hasStatus(Status.PLANNED)).thenReturn(true);
        when(userStoryMapperDouble.userStoryToDto(userStoryDoubleOne)).thenReturn(userStoryDtoDoubleOne);

        // Act
        List<UserStoryDto> result = usServiceDouble.requestAllPlannedUs(usIdDoubleList);

        // Assert
        assertEquals(expected, result);
    }


    /**
     * Scenario 02: verify if a list of empty userStories is retrieved, a exception is thrown.
     * <p>
     * Expected result: exception is thrown.
     */

    @Test
    void ensureRequestOfAllPlannedUsIsSuccessfulEmptyList() throws Exception {
        // Arrange
        IUsRepository usRepositoryDouble = mock(IUsRepository.class);
        List<UsId> usIdDoubleList = new ArrayList<>();
        List<UserStory> userStoriesList = new ArrayList<>();

        when(usRepositoryDouble.getListOfUsWithMatchingIds(usIdDoubleList)).
                thenReturn(userStoriesList);

        List<UserStoryDto> emptyList = new ArrayList<>();

        // Assert
        assertEquals(userStoriesList, emptyList);
    }

    @Test
    void ensureRequestOfAllPlannedUsIsSuccessfulEmptyListTestTwo() throws Exception {
        // Arrange
        IUsRepository usRepositoryDouble = mock(IUsRepository.class);
        List<UsId> usIdDoubleList = new ArrayList<>();

        when(usRepositoryDouble.getListOfUsWithMatchingIds(usIdDoubleList)).
                thenReturn(Collections.emptyList());

        List<UserStoryDto> emptyList = new ArrayList<>();

        // Assert
        assertEquals(Collections.emptyList(), emptyList);
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
        UsId result = usService.createUs(userStoryCreationDto, "P001");

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
        usService.createUs(userStoryCreationDto, "P001");


        //ACT and ASSERT
        assertThrows(IllegalStateException.class, () ->
                usService.createUs(userStoryCreationDto, "P001"));

    }
}
