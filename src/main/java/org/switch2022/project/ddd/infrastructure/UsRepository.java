package org.switch2022.project.ddd.infrastructure;


import org.switch2022.project.ddd.domain.interfaces.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.UsId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class UsRepository allows to manage userStories records.
 */

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
        if (this == o) return true;
        if (!(o instanceof UsRepository)) return false;
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
     * @return the addition of the userStory to the repository or an exception otherwise.
     */

    public void add(UserStory userStory) {
        if (!userStories.contains(userStory)) {
            userStories.add(userStory);
        } else {
            throw new IllegalStateException("User story ID already exists");
        }
    }

    /**
     * This method deletes a userStory from the repository of userStories if it exists.
     *
     * @param usId of the userStory to be deleted from the repository.
     * @return the elimination of the userStory of the repository or an exception otherwise.
     */

    public void delete(UsId usId) {
        boolean usFound = false;
        for (int i = 0; i < userStories.size(); i++) {
            if (userStories.get(i).getUsId().equals(usId)) {
                userStories.remove(i);
                usFound = true;
                break;
            }
        }
        if (!usFound) {
            throw new IllegalStateException("User story does not exist");
        }
    }


    /**
     * Lists all userStories with a matching ID.
     *
     * @param usId will filter the userStories present in the repository by ID.
     * @return all planned userStories with a corresponding ID.
     */

    @Override
    public List<UserStory> getListOfUsWithMatchingIds(List<UsId> usId) {
        if (userStories.isEmpty()) {
            throw new IllegalStateException("User story list does not contain userStories matching given IDs");
        } else {
            List<UserStory> userStoriesWithMatchingIds = new ArrayList<>();
            for (UserStory userStory : userStories) {
                for (UsId id : usId) {
                    if (userStory.getUsId().equals(id)) {
                        userStoriesWithMatchingIds.add(userStory);
                    }
                }
            }
            return userStoriesWithMatchingIds;
        }
    }
}
