package org.switch2022.project.dto.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.UserStory;
import static org.mockito.Mockito.mock;


class UserStoryMapperTest {
    /**
     * Scenario 1: Verify if a UserStory object is successfully converted
     * into a UserStoryDto.
     */
    @Test
    void ensureThatAValidUserStoryDtoIsCreated() {
        // ARRANGE
        UserStory userStoryDouble = mock(UserStory.class);

        // ACT
        UserStoryMapper.userStoryToDto(userStoryDouble);
    }

}