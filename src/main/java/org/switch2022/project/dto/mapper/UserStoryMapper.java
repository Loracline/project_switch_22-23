package org.switch2022.project.dto.mapper;

import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.model.UserStory;

public final class UserStoryMapper {
    /**
     * Constructor of the class UserStoryMapper.
     */
    private UserStoryMapper() {
    }

    /**
     * This method converts a user story into a Dto.
     *
     * @param userStory one must convert.
     * @return Dto carrying data.
     */
    public static UserStoryDto userStoryToDto(UserStory userStory) {
        return new UserStoryDto(userStory.getUserStoryNumber(),
                userStory.getUserStoryText(), userStory.getStatus().toString());
    }
}
