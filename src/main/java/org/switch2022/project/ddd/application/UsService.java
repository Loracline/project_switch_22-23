package org.switch2022.project.ddd.application;

import org.switch2022.project.ddd.domain.interfaces.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.IFactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
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

    private final IUsRepository usRepository;
    private final IFactoryUserStory factoryUserStory;
    private final UserStoryMapper userStoryMapper;

    /**
     * Constructor.
     *
     * @param usRepository     where the userStory will be saved.
     * @param factoryUserStory allows to create a userStory.
     * @param userStoryMapper  converts userStory to userStoryDto.
     */

    public UsService(IUsRepository usRepository, IFactoryUserStory factoryUserStory,
                     UserStoryMapper userStoryMapper) {
        this.usRepository = usRepository;
        this.factoryUserStory = factoryUserStory;
        this.userStoryMapper = userStoryMapper;
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
        return userStory.getUsId();
    }

    /**
     * This method deletes the userStory or throws an exception if the userStory does not exist.
     *
     * @param usId of userStory to be deleted from the repository.
     * @return userStory deletion.
     */

    public void deleteUs(UsId usId) throws Exception {
        List<UsId> usIdList = new ArrayList<>();
        List<UserStory> userStories = usRepository.getListOfUsWithMatchingIds(usIdList);
        if (userStories.isEmpty()) {
            throw new IllegalStateException("User story does not exist");
        } else {
            usRepository.delete(usId);
        }
    }

    /**
     * Requests a list of userStories with the status planned.
     *
     * @param usId ID of the userStory.
     * @return a list of all userStoriesDto with the status planned.
     */

    public List<UserStoryDto> requestAllPlannedUs(List<UsId> usId) throws Exception {
        List<UserStory> userStories = usRepository.getListOfUsWithMatchingIds(usId);
        if (userStories.isEmpty()) {
            throw new IllegalStateException("User story list does not contain userStories matching given IDs");
        } else {
            List<UserStoryDto> userStoriesDto = new ArrayList<>();
            for (UserStory userStory : userStories) {
                if (userStory.getStatus().toString().equals("PLANNED")) {
                    userStoriesDto.add(userStoryMapper.userStoryToDto(userStory));
                }
            }
            return userStoriesDto;
        }
    }
}