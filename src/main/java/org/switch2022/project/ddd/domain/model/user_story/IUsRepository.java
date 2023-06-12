package org.switch2022.project.ddd.domain.model.user_story;

import org.switch2022.project.ddd.domain.value_object.UsId;

import java.util.List;
import java.util.Optional;

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

    boolean save(UserStory userStory);

    /**
     * This method deletes a userStory from the repository of userStories if it exists.
     *
     * @param usId of the userStory to be deleted from the repository.
     * @return true if the user story is deleted or throws an exception otherwise.
     */
    boolean delete(UsId usId);

    /**
     * Lists all userStories with a matching ID.
     *
     * @param usId will filter the userStories present in the repository by ID.
     * @return all planned userStories with a corresponding ID.
     */

    List<UserStory> getListOfUsWithMatchingIds(List<UsId> usId);

    /**
     * Checks if a User Story with the specified ID exists.
     *
     * @param usId The ID of the User Story to check.
     * @return true if a User Story with the specified ID exists, false otherwise.
     */
    boolean existsByUsId (UsId usId);

    /**
     * Returns a USer Story based on its Id.
     * @param usId from the User Story one searches for.
     * @return an Optional containing the desired User Story.
     */
    Optional<UserStory> findByUsId(UsId usId);
}

