package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class SprintBacklog, holds a set of userStories.
 */

public class SprintBacklog {

    /**
     * Attributes of the class SprintBacklog, according to the Class Diagram.
     */

    private final List<UserStory> userStories = new ArrayList<>();

    /** This method adds a new User Story to the userStories list is the User Story
     * doesn't already exist.
     *
     * @param userStory the new User Story to be added
     * @return TRUE if the User Story was successfully added to the list and FALSE
     * otherwise.
     */
    public boolean addUserStory(UserStory userStory) {
        boolean result = true;
        if (userStories.contains(userStory)) {
            result = false;
        } else {
            userStories.add(userStory);
        }
        return result;
    }

    /** This method verifies if the User Story is already in the list.
     *
     * @param userStoryNumber to check the presence in the list through the
     *                        hasStoryNumber method.
     * @return TRUE if the User Story is present in the list and FALSE otherwise.
     */
    public boolean hasUserStory(String userStoryNumber) {
        boolean result = false;
        int i = 0;
        while (i < userStories.size() && result == false) {
            if (userStories.get(i).hasUserStoryNumber(userStoryNumber)) {
                result = true;
            }
            i++;
        }
        return result;
    }

}
