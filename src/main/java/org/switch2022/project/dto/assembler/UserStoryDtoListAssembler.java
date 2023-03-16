package org.switch2022.project.dto.assembler;

import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.dto.mapper.UserStoryMapper;
import org.switch2022.project.factories.IFactoryUserStory;
import org.switch2022.project.model.ProductBacklog;
import org.switch2022.project.model.SprintBacklog;
import org.switch2022.project.model.UserStory;

import java.util.ArrayList;
import java.util.List;

public class UserStoryDtoListAssembler {
    /**
     * Constructor of the class UserStoryDtoListAssembler.
     */
    private UserStoryDtoListAssembler() {
    }

    /**
     * This method converts a list of user stories within a Product Backlog into a list
     * of user story DTOs.
     *
     * @return a list of User Story DTOs.
     */


    public static List<UserStoryDto> backlogToDto(ProductBacklog productBacklog) {
        List<UserStoryDto> userStoryDto = new ArrayList<>();
        if (productBacklog != null) {
            List<UserStory> userStories = productBacklog.getUserStoriesCopy();
            userStoryDto.addAll(getUserStoryDtos(userStories));
        }
        return userStoryDto;
    }

    /**
     * This method converts a list of user stories within a Sprint Backlog into a list
     * of user story DTOs.
     *
     * @param factoryUserStory used to create the copy User Stories.
     * @return a list of User Story DTOs.
     */
    public static List<UserStoryDto> backlogToDto(SprintBacklog sprintBacklog,
                                                  IFactoryUserStory factoryUserStory) {
        List<UserStory> userStories = sprintBacklog.getUserStoriesCopy(factoryUserStory);
        List<UserStoryDto> userStoryDtos = getUserStoryDtos(userStories);
        return userStoryDtos;
    }

    /**
     * This method converts a list of user stories into a list
     * of user story DTOs.
     *
     * @param userStories to convert into User Story DTOs.
     * @return a list of User Story DTOs.
     */
    private static List<UserStoryDto> getUserStoryDtos(List<UserStory> userStories) {
        List<UserStoryDto> userStoryDtos = new ArrayList<>();

        for (UserStory userStory : userStories) {
            userStoryDtos.add(UserStoryMapper.userStoryToDto(userStory));
        }
        return userStoryDtos;
    }


}
