package org.switch2022.project.ddd.domain.model.user_story;

import org.switch2022.project.ddd.domain.value_object.UsId;

import java.util.List;

/**
 * User Story Repository interface.
 */

public interface IUsRepository {

    /**
     * This method adds a new userStory to the repository of userStories if it does not exist.
     *
     * @param userStory to be added to the repository.
     * @return true if the user story is added and an exception otherwise.
     */

    public boolean save(UserStory userStory);

    /**
     * This method deletes a userStory from the repository of userStories if it exists.
     *
     * @param usId of the userStory to be deleted from the repository.
     * @return true if the user story is deleted or throws an exception otherwise.
     */
    public boolean delete(UsId usId);

    /**
     * Lists all userStories with a matching ID.
     *
     * @param usId will filter the userStories present in the repository by ID.
     * @return all planned userStories with a corresponding ID.
     */

    List<UserStory> getListOfUsWithMatchingIds(List<UsId> usId);

}