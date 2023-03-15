package org.switch2022.project.dto.assembler;

import org.junit.jupiter.api.Test;
import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.model.ProductBacklog;
import org.switch2022.project.model.UserStory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryDtoListAssemblerTest {


    /**
     * METHOD backlogToDto(productBacklog)
     * Scenario 1: it should return a list of user story DTOs that is equal to the
     * expected.
     */
    @Test
    void ensureThatItReturnsAListOfUserStoryDtosThatIsEqualToTheExpected() {
       //ARRANGE

        //user stories in product backlog
        ProductBacklog productBacklog = mock(ProductBacklog.class);

        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);

        when(userStoryOne.getUserStoryNumber()).thenReturn("US001");
        when(userStoryOne.getUserStoryText()).thenReturn("I want to create a project");
        when(userStoryOne.getStatus()).thenReturn("planned");

        when(userStoryTwo.getUserStoryNumber()).thenReturn("US002");
        when(userStoryTwo.getUserStoryText()).thenReturn("I want to create a user " +
                "story");
        when(userStoryTwo.getStatus()).thenReturn("planned");

        //behavior of product backlog
        List<UserStory> userStories = new ArrayList<>();
        userStories.add(userStoryOne);
        userStories.add(userStoryTwo);
        when(productBacklog.getUserStoriesCopy()).thenReturn(userStories);

        //user stories dtos from mapper
        UserStoryDto userStoryDtoOne = new UserStoryDto("US001", "I want to create a " +
                "project", "planned");
        UserStoryDto userStoryDtoTwo = new UserStoryDto("US002", "I want to create a " +
                "user story", "planned");

        List<UserStoryDto> expected = new ArrayList<>();
        expected.add(userStoryDtoOne);
        expected.add(userStoryDtoTwo);

        //ACT
        List<UserStoryDto> result =
                UserStoryDtoListAssembler.backlogToDto(productBacklog);
        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * METHOD backlogToDto(productBacklog)
     * Scenario 2: it should return an empty list of user story DTOs if no product
     * backlog is retrieved
     */
    @Test
    void ensureThatItReturnsAnEmptyListOfUserStoryDtosIfProductBacklogIsNull() {
        //ARRANGE

        ProductBacklog productBacklog = null;

        List<UserStoryDto> expected = new ArrayList<>();

        //ACT
        List<UserStoryDto> result =
                UserStoryDtoListAssembler.backlogToDto(productBacklog);
        //ASSERT
        assertEquals(expected, result);
    }
}