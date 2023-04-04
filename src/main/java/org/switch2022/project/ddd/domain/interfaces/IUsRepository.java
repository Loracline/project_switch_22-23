package org.switch2022.project.ddd.domain.interfaces;

import org.switch2022.project.ddd.domain.model.user_story.UserStory;

import java.util.List;

/**
 * User Story Repository interface.
 */

public interface IUsRepository {

    /**
     * This method adds a new userStory to the repository of userStories if it does not exist.
     *
     * @param userStory to be added to the repository.
     * @return the addition of the userStory to the repository or an exception otherwise.
     */
    public void add(UserStory userStory) throws Exception;

    /**
     * This method deletes a userStory from the repository of userStories if it exists.
     *
     * @param userStory to be deleted from the repository.
     * @return the elimination of the userStory of the repository or an exception otherwise.
     */
    public void delete(UserStory userStory) throws Exception;

    /**
     * Lists all userStories with the status planned.
     *
     * @param usId will filter the userStories present in the repository by ID.
     * @return all planned userStories with a corresponding ID.
     */
    List<UserStory> getAllPlannedUs(List usId) throws Exception;
}