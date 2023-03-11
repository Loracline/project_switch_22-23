package org.switch2022.project.model;

import org.switch2022.project.dto.UserStoryCreationDto;
import org.switch2022.project.factories.IFactoryUserStory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Class ProductBacklog is built to access and manipulate the set of User Stories
 * of a given Project.
 */

public class ProductBacklog {
    /**
     * Attributes
     */
    private final List<UserStory> userStories = new ArrayList<>();
    private final IFactoryUserStory iFactoryUserStory;

    /**
     * Constructor to assign class that implements FactoryUserStory
     */
    public ProductBacklog(IFactoryUserStory ifactoryUserStory) {
        this.iFactoryUserStory = ifactoryUserStory;
    }

    /**
     * This method checks if two instances of ProductBacklog are equal by comparing
     * the userStories attribute (the list of user stories).
     *
     * @param o ProductBacklog instance to compare with.
     * @return TRUE if the two have the same attributes, and FALSE otherwise.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductBacklog that = (ProductBacklog) o;
        return userStories.equals(that.userStories);
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
     * This method creates an UserStory from a Dto, and checks if the userStory is unique
     * and had it to the ProductBacklog according to the priority defined.
     *
     * @param userStoryCreationDto and factoryUserStory
     * @return true if the userStory is created and added to the ProductBacklog.
     */

    public boolean createUserStory(UserStoryCreationDto userStoryCreationDto) {
        boolean isUserStoryCreated = false;
        int priority = userStoryCreationDto.priority;
        UserStory userStory = iFactoryUserStory.createUserStory(userStoryCreationDto);
        if (addUserStory(userStory, priority)) {
            isUserStoryCreated = true;
        }
        return isUserStoryCreated;
    }


    /**
     * This method adds a new User Story to the userStories list if the User Story
     * doesn't already exist.
     * The priority is defined being 0 as the most important.
     *
     * @param userStory the new User Story to be added, priority
     * @return TRUE if the User Story was successfully added to the list and FALSE
     * otherwise.
     */
    public boolean addUserStory(UserStory userStory, int priority) {
        boolean result = true;

        if (userStories.contains(userStory) || priority > userStories.size()) {
            result = false;
        } else {
            userStories.add(priority, userStory);
        }
        return result;
    }

    /**
     * This method returns a User Story from the Product Backlog with a given User Story number.
     *
     * @param userStoryNumber of the User Story one searches for.
     * @return an Optional with a User Story.
     */


    public Optional<UserStory> getUserStoryByNumber(String userStoryNumber) {
        int i = 0;
        UserStory userStory = null;
        while (i < userStories.size() && userStory == null) {
            if (userStories.get(i).hasUserStoryNumber(userStoryNumber)) {
                userStory = userStories.get(i);
            }
            i++;
        }
        return Optional.ofNullable(userStory);
    }

    /**
     * This method removes a User Story from the userStories list if the User Story exists.
     *
     * @param userStory to be deleted
     * @return TRUE if the User Story is removed from UserStories list and FALSE otherwise.
     */
    public boolean removeUserStory(UserStory userStory) {
        int i = 0;
        boolean wasRemoved = false;
        while (i < userStories.size() && !wasRemoved) {
            if (userStories.contains(userStory)) {
                userStories.remove(i);
                wasRemoved = true;
            }
            i++;
        }
        return wasRemoved;
    }

    /**
     * This method makes a deep copy of the User Stories that are in the Product Backlog,
     * and the copy in a new list.
     *
     * @return a list of the copied User Stories.
     */

    public List<UserStory> getUserStoriesCopy() {
        List<UserStory> listOfCopies = new ArrayList<>();

        for (UserStory userStory : this.userStories) {
            UserStory userStoryCopy = this.iFactoryUserStory.createUserStory(
                    userStory.getUserStoryNumber(),
                    userStory.getActor(),
                    userStory.getUserStoryText());
            userStoryCopy.setStatus(userStory.getStatus());
            userStoryCopy.setEffort(userStory.getEffort());
            listOfCopies.add(userStoryCopy);
        }
        return listOfCopies;
    }

    /**
     * This method makes a copy the list of user stories contained in the product backlog and
     * stores them in another instance of product backlog.
     *
     * @return a product backlog with list of copies of user stories.
     */

    public ProductBacklog getProductBacklogCopy() {
        ProductBacklog productBacklogCopy = new ProductBacklog(this.iFactoryUserStory);

        List<UserStory> userStoriesCopy =
                getUserStoriesCopy();

        for (int i = 0; i < userStoriesCopy.size(); i++) {
            productBacklogCopy.addUserStory(userStoriesCopy.get(i), i);
            i++;
        }
        return productBacklogCopy;
    }
}
