package org.switch2022.project.ddd.dto.mapper;


import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.dto.UserStoryDto;
import java.util.ArrayList;
import java.util.List;

public class UserStoryMapper {

    /**
     * This method converts a user story into a Dto.
     *
     * @param userStory one must convert.
     * @return Dto carrying data.
     */
    
    public UserStoryDto userStoryToDto(UserStory userStory) {
        return new UserStoryDto(userStory.getUsNumber().toString(), userStory.getUsText().toString(),
                userStory.getStatus().toString());
    }
    /**
     * This method converts a list of user stories into a list
     * of user story DTOs.
     *
     * @param userStories to convert into User Story DTOs.
     * @return a list of User Story DTOs.
     */
   
    public List<UserStoryDto> userStoryToDtoList(List<UserStory> userStories) {
        List<UserStoryDto> userStoryDtos = new ArrayList<>();

        for (UserStory userStory : userStories) {
            userStoryDtos.add(userStoryToDto(userStory));
        }
        return userStoryDtos;
    }
}

