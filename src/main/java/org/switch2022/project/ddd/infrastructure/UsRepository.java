package org.switch2022.project.ddd.infrastructure;

import org.springframework.stereotype.Repository;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class UsRepository allows to manage userStories records.
 */
@Repository("us_memory")
public class UsRepository implements IUsRepository {

    private final List<UserStory> userStories = new ArrayList<>();


    /**
     * This method checks if an instance of UsRepository is equal to another object.
     *
     * @param o object to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        UsRepository that = (UsRepository) o;
        return Objects.equals(userStories, that.userStories);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return Objects.hash(userStories);
    }

    /**
     * This method adds a new userStory to the repository of userStories if it does not exist.
     *
     * @param userStory to be added to the repository.
     * @return true if the user story is added and an UserStoryAlreadyExistException otherwise.
     */

    @Override
    public boolean save(UserStory userStory) {
        if (!userStories.contains(userStory)) {
            userStories.add(userStory);
            return true;
        } else {
            throw new AlreadyExistsInRepoException("User story ID already exists");
        }
    }

    /**
     * This method deletes a userStory from the repository of userStories if it exists.
     *
     * @param usId of the userStory to be deleted from the repository.
     * @return true if the user story is deleted or throws an exception otherwise.
     */
    public boolean delete(UsId usId) {
        boolean usFound = false;
        for (int i = 0; i < userStories.size(); i++) {
            if (userStories.get(i).getUsId().equals(usId.getUserStoryId()) && !usFound) {
                userStories.remove(i);
                usFound = true;
            }
        }
        if (!usFound) {
            throw new AlreadyExistsInRepoException("The User Story is already in the Product Backlog");
        }
        return true;
    }

    /**
     * Lists all userStories with a matching ID.
     *
     * @param usId will filter the userStories present in the repository by ID.
     * @return all planned userStories with a corresponding ID.
     */
    public List<UserStory> getListOfUsWithMatchingIds(List<UsId> usId) {
        List<UserStory> userStoriesWithMatchingIds = new ArrayList<>();
        for (UserStory userStory : userStories) {
            for (UsId id : usId) {
                if (userStory.getUsId().equals(id.getUserStoryId())) {
                    userStoriesWithMatchingIds.add(userStory);
                }
            }
        }
        return userStoriesWithMatchingIds;
    }

    /**
     * Checks if a UserStory with the given UsId exists in the list of UserStories.
     *
     * @param usId the UsId to search for
     * @return true if a UserStory with the given UsId is found, false otherwise
     */
    @Override
    public boolean existsByUsId(UsId usId) {
        boolean usFound = false;
        for (UserStory userStory : userStories) {
            if (userStory.getUsId().equals(usId.getUserStoryId()) && !usFound) {
                usFound = true;
            }
    }
        return usFound;
    }
}
