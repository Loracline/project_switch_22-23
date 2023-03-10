package org.switch2022.project.model;

import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.factories.IFactoryUserStory;
import org.switch2022.project.utils.Effort;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class SprintBacklog, holds a set of userStories.
 */

public class SprintBacklog {

    /**
     * Attributes of the class SprintBacklog, according to the Class Diagram.
     */

    private final List<UserStory> userStories = new ArrayList<>();

    /**
     * This method checks if two instances of SprintBacklog are equal by comparing
     * the userStories.
     *
     * @param o SprintBacklog instance to compare with.
     * @return TRUE if the two have the same attributes, and FALSE otherwise.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SprintBacklog)) {
            return false;
        }
        SprintBacklog that = (SprintBacklog) o;
        return Objects.equals(userStories, that.userStories);
    }

    /**
     * The hashCode() method is used to generate a unique hash code for an
     * object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return Objects.hash(userStories);
    }

    /**
     * This method adds a new User Story to the userStories list is the User Story
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

    /**
     * This method verifies if the User Story is already in the list.
     *
     * @param userStoryNumber to check the presence in the list through the
     *                        hasStoryNumber method.
     * @return TRUE if the User Story is present in the list and FALSE otherwise.
     */
    public boolean hasUserStory(String userStoryNumber) {
        boolean result = false;
        int i = 0;
        while (i < userStories.size() && !result) {
            if (userStories.get(i).hasUserStoryNumber(userStoryNumber)) {
                result = true;
            }
            i++;
        }
        return result;
    }

    /**
     * This method makes a deep copy of the User Stories that are in the Sprint Backlog,
     * and the copy in a new list.
     *
     * @param factoryUserStory used to create the copy User Stories.
     * @return a list of the copied User Stories.
     */
    public List<UserStory> getUserStoriesCopy(IFactoryUserStory factoryUserStory) {
        List<UserStory> copyList = new ArrayList<>();
        int i = 0;
        while (i < this.userStories.size()) {
            UserStory copyUserStory =
                    factoryUserStory.createUserStory(
                            this.userStories.get(i).getUserStoryNumber(),
                            this.userStories.get(i).getActor(),
                            this.userStories.get(i).getUserStoryText());
            copyUserStory.setStatus(this.userStories.get(i).getStatus());
            copyList.add(copyUserStory);
            i++;
        }
        return copyList;
    }


    /**
     * This method checks if the userStory number exists in the SprintBacklog,
     * if so it'll then set the effort for the userStory.
     *
     * @param userStoryDto
     * @param effort
     * @return true if the userStory number exists and false otherwise.
     */

    public boolean estimateEffortUserStory(UserStoryDto userStoryDto, Effort effort) {
        int i = 0;
        boolean isEffortSet = false;
        while (i < userStories.size()) {
            if (userStories.get(i).hasUserStoryNumber(userStoryDto.userStoryNumber)) {
                userStories.get(i).setEffort(effort);
                isEffortSet = true;
            }
            i++;
        }
        return isEffortSet;
    }
}
