package org.switch2022.project.ddd.domain.interfaces;


import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;

import java.util.List;

/**
 * User Story creation interface.
 */

public interface IUsService {

    /**
     * This method returns the ID of the userStory newly created or throws an exception if userStory
     * not created.
     *
     * @param userStoryCreationDto will be the base for the creation of a new userStory.
     * @param projectCode will be associated with the newly created userStory.
     * @return userStoryId from the newly created userStory.
     */
    UsId createUs(UserStoryCreationDto userStoryCreationDto, String projectCode) throws Exception;

    /**
     * This method deletes the userStory or throws an exception if the userStory does not exist.
     *
     * @param userStory to be deleted from the repository.
     * @return userStory deletion.
     */
    void deleteUs(UserStory userStory) throws Exception;

    /**
     * Requests a list of userStories with the status planned or throws an exception if empty.
     *
     * @param usId ID of the userStory.
     * @return a list of all userStories with the status planned.
     */
    List<UserStory> requestAllPlannedUs(List usId) throws Exception;
}