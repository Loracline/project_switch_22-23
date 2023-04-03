package org.switch2022.project.ddd.domain.infrastructure;


import org.switch2022.project.ddd.domain.interfaces.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;

import java.util.ArrayList;
import java.util.List;

/**
 * Class UsRepository allows to manage userStories records.
 */

public class UsRepository implements IUsRepository {

    private final List<UserStory> userStories = new ArrayList<>();

    /**
     * This method adds a new userStory to the repository of userStories if it does not exist.
     *
     * @param userStory to be added to the repository.
     * @return the addition of the userStory to the repository or an exception otherwise.
     */

    public void add(UserStory userStory) {
        if (userStory.getUsId() == null) {
            userStories.add(userStory);
        }
        throw new IllegalStateException("User story ID already exists.");
    }

    /**
     * This method deletes a userStory from the repository of userStories if it exists.
     *
     * @param userStory to be deleted from the repository.
     * @return the elimination of the userStory of the repository or an exception otherwise.
     */

    public void delete(UserStory userStory) {
        if (userStories.isEmpty() || !userStories.contains(userStory)) {
            throw new IllegalStateException("User story not found");
        }
        for (int i = 0; i < userStories.size(); i++) {
            if (userStories.get(i).getUsId().sameValueAs(userStory.getUsId())) {
                userStories.remove(userStory);
            }
        }
    }

    /**
     * Lists all userStories with the status planned.
     *
     * @param usId will filter the userStories present in the repository by ID.
     * @return all planned userStories with a corresponding ID.
     */
    public List<UserStory> getAllPlannedUs(List usId) {
        if (userStories.isEmpty()) {
            throw new IllegalStateException("User story list is empty");
        }
        List<UserStory> userStoriesWithMatchingIds = new ArrayList<>();
        for (int i = 0; i < userStories.size(); i++) {
            for (int j = 0; j < usId.size(); j++) {
                if (userStories.get(i).getUsId().equals(usId.get(j)) &&
                        userStories.get(i).getStatus().toString().equals("PLANNED")) {
                    userStoriesWithMatchingIds.add(userStories.get(i));
                }
            }
        }
        return userStoriesWithMatchingIds;
    }
}
