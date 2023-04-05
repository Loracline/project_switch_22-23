package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.interfaces.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.IFactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.Status;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;
import org.switch2022.project.ddd.dto.UserStoryDto;
import org.switch2022.project.ddd.dto.mapper.UserStoryMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsServiceTest {


    /**
     * Method: create(userStoryCreationDto, projectCode).
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
     * Scenario 01: verify if a userStory is deleted.
     * <p>
     * Expected result: userStory is deleted.
     */


    @Test
    void ensureUsIsDeleted() throws Exception {
        // Arrange
        IUsRepository usRepositoryDouble = mock(IUsRepository.class);
        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        UserStoryMapper userStoryMapperDouble = mock(UserStoryMapper.class);
        UsId usIdDouble = mock(UsId.class);

        UsService usServiceOneDouble = new UsService(usRepositoryDouble, factoryUserStoryDouble, userStoryMapperDouble);
        UsService usServiceTwoDouble = new UsService(usRepositoryDouble, factoryUserStoryDouble, userStoryMapperDouble);

        // Act
        usServiceOneDouble.deleteUs(usIdDouble);
        usServiceTwoDouble.deleteUs(usIdDouble);

        // Assert
        assertEquals(usServiceOneDouble, usServiceTwoDouble);
    }


    /**
     * Scenario 02: verify if a userStory is not deleted.
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
        when(userStoryDoubleOne.getStatus()).thenReturn(Status.valueOf("PLANNED"));
        when(userStoryMapperDouble.userStoryToDto(userStoryDoubleOne)).thenReturn(userStoryDtoDoubleOne);

        // Act
        List<UserStoryDto> result = usServiceDouble.requestAllPlannedUs(usIdDoubleList);

        // Assert
        assertEquals(expected, result);
    }


    /**
     * Scenario 02: verify if a list of empty userStories is retrieved.
     * <p>
     * Expected result: userStories list is retrieved.
     */


    @Test
    void ensureRequestOfAllPlannedUsIsSuccessfulEmptyList() throws Exception {
        // Arrange
        IUsRepository usRepositoryDouble = mock(IUsRepository.class);
        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        UserStoryMapper userStoryMapperDouble = mock(UserStoryMapper.class);
        UsService usServiceDouble = new UsService(usRepositoryDouble, factoryUserStoryDouble, userStoryMapperDouble);

        UserStoryDto userStoryDtoDoubleOne = mock(UserStoryDto.class);

        List<UsId> usIdDoubleList = new ArrayList<>();
        List<UserStoryDto> expected = new ArrayList<>();
        expected.add(userStoryDtoDoubleOne);

        when(usRepositoryDouble.getListOfUsWithMatchingIds(usIdDoubleList)).
                thenReturn(Collections.emptyList());

        // Act
        List<UserStoryDto> result = usServiceDouble.requestAllPlannedUs(usIdDoubleList);

        // Assert
        assertNotEquals(expected, result);
    }
}
