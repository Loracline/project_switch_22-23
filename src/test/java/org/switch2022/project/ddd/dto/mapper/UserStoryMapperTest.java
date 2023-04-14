package org.switch2022.project.ddd.dto.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.Status;
import org.switch2022.project.ddd.domain.value_object.UsNumber;
import org.switch2022.project.ddd.domain.value_object.UsText;
import org.switch2022.project.ddd.dto.UserStoryDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryMapperTest {

    /**
     * Method userStoryToDto(userStory).
     * Scenario 1: it should return a dto from the UserStory given
     */

    @Test
    void ensureThatItReturnsADto() {
        //Arrange
        UserStoryMapper mapper = new UserStoryMapper();
        UserStory userStoryDouble = mock(UserStory.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Status statusDouble = mock(Status.class);
        UsText usTextDouble = mock(UsText.class);
        String usNumber = "US001";
        String status = "Planned";
        String text = "I want to create an issue";
        when(usNumberDouble.getUserStoryNumber()).thenReturn(usNumber);
        when(statusDouble.toString()).thenReturn(status);
        when(usTextDouble.getUserStoryText()).thenReturn(text);
        when(userStoryDouble.getUsNumber()).thenReturn(usNumberDouble);
        when(userStoryDouble.getStatus()).thenReturn(statusDouble);
        when(userStoryDouble.getUsText()).thenReturn(usTextDouble);

        UserStoryDto expected = new UserStoryDto(usNumber, text, status);

        //Act
        UserStoryDto result = mapper.userStoryToDto(userStoryDouble);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method userStoryToDtolist(userStory).
     * Scenario 1: it should return a dto from the UserStory given
     */
    @Test
    void ensureThatItReturnsADtoLIst() {
        //Arrange
        UserStoryMapper mapper = new UserStoryMapper();
        UserStory userStoryDouble = mock(UserStory.class);
        UserStory userStoryDoubleTwo = mock(UserStory.class);
        List<UserStory> userStories = new ArrayList<>();
        userStories.add(userStoryDouble);
        userStories.add(userStoryDoubleTwo);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Status statusDouble = mock(Status.class);
        UsText usTextDouble = mock(UsText.class);
        String usNumber = "US001";
        String status = "Planned";
        String text = "I want to create an issue";
        when(usNumberDouble.getUserStoryNumber()).thenReturn(usNumber);
        when(statusDouble.toString()).thenReturn(status);
        when(usTextDouble.getUserStoryText()).thenReturn(text);
        when(userStoryDouble.getUsNumber()).thenReturn(usNumberDouble);
        when(userStoryDoubleTwo.getUsNumber()).thenReturn(usNumberDouble);
        when(userStoryDouble.getStatus()).thenReturn(statusDouble);
        when(userStoryDoubleTwo.getStatus()).thenReturn(statusDouble);
        when(userStoryDouble.getUsText()).thenReturn(usTextDouble);
        when(userStoryDoubleTwo.getUsText()).thenReturn(usTextDouble);
        UserStoryDto userStoryDtoDouble = new UserStoryDto(usNumber, text, status);
        UserStoryDto userStoryDtoDoubleTwo = new UserStoryDto(usNumber, text, status);


        List<UserStoryDto> expected = new ArrayList<>();
        expected.add(userStoryDtoDouble);
        expected.add(userStoryDtoDoubleTwo);

        //Act
        List<UserStoryDto> result = mapper.userStoryToDtoList(userStories);
        //Assert
        assertEquals(expected, result);
    }
}