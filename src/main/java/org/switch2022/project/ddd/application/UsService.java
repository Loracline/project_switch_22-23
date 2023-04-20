package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.IFactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.Status;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;
import org.switch2022.project.ddd.dto.UserStoryDto;
import org.switch2022.project.ddd.dto.mapper.UserStoryMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Class UsService allows to create a new userStory.
 */

public class UsService {
    @Autowired
    private IUsRepository usRepository;
    @Autowired
     private IFactoryUserStory factoryUserStory;
    @Autowired
    private UserStoryMapper userStoryMapper;

    public UsService() {
    }

    /**
     * This method returns the ID of the userStory newly created or throws an exception if userStory
     * not created.
     *
     * @param userStoryCreationDto will be the base for the creation of a new userStory.
     * @param projectCode          will be associated with the newly created userStory.
     * @return userStoryId from the newly created userStory.
     */

    public UsId createUs(UserStoryCreationDto userStoryCreationDto, String projectCode) throws Exception {
        final UserStory userStory = factoryUserStory.createUserStory(userStoryCreationDto, projectCode);
        usRepository.add(userStory);
        UsId usId = new UsId(projectCode, userStory.getUsNumber());
        return usId;
    }

    /**
     * This method deletes the userStory or throws an exception if the userStory does not exist.
     *
     * @param usId of userStory to be deleted from the repository.
     */

    public boolean deleteUs(UsId usId) throws Exception {
        usRepository.delete(usId);
        return true;
    }

    /**
     * Requests a list of userStories with the status planned.
     *
     * @param usId ID of the userStory.
     * @return a list of all userStoriesDto with the status planned.
     */

    public List<UserStoryDto> requestAllPlannedUs(List<UsId> usId) {
        List<UserStory> userStories = usRepository.getListOfUsWithMatchingIds(usId);
        List<UserStoryDto> userStoriesDto = new ArrayList<>();
        if (!userStories.isEmpty()) {
            for (UserStory userStory : userStories) {
                if (userStory.hasStatus(Status.PLANNED)) {
                    userStoriesDto.add(userStoryMapper.userStoryToDto(userStory));
                }
            }
        }
        return userStoriesDto;
    }
}