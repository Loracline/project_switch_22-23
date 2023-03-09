package org.switch2022.project.dto.mapper;

import org.switch2022.project.dto.AccountEmailStatusDto;
import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.UserStory;

public class UserStoryMapper {
    /**
     * Constructor of the class UserStoryMapper.
     */
    private UserStoryMapper() {
    }

    //  UserStory -> UserStoryDto

    /**
     * This method converts a user story into a Dto.
     *
     * @param userStory one must convert.
     * @return Dto carrying data.
     */
    public static UserStoryDto userStoryToDto(UserStory userStory) {
        return new UserStoryDto(userStory.getUserStoryNumber(),
                userStory.getUserStoryText(),userStory.getStatus());
    }

}
