package org.switch2022.project.ddd.domain.model.project;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.PbId;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.utils.Validate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductBacklog implements Entity<ProductBacklog> {

    private final PbId productBacklogId;
    private final List<UsId> userStories = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param projectCode the ID of the product backlog to be created.
     */
    protected ProductBacklog(final String projectCode) {
        Validate.notNull(projectCode, "Product Backlog's ID can't be null.");
        this.productBacklogId = new PbId(projectCode);
    }

    /**
     * This method checks if two instances of ProductBacklog are equal by comparing the value of
     * the attribute product
     * backlog id.
     *
     * @param other ProductBacklog instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code>
     * otherwise.
     */
    @Override
    public boolean sameIdentityAs(ProductBacklog other) {
        return other != null && this.productBacklogId.equals(other.productBacklogId);
    }

    /**
     * This method checks if an instance of ProductBacklog is equal to another object.
     *
     * @param toCompare object to compare with.
     * @return <code>true</code> if the two have the same ID, and <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) {
            return true;
        }
        if (toCompare == null) {
            return false;
        }
        if (this.getClass() != toCompare.getClass()) {
            return false;
        }
        ProductBacklog productBacklog = (ProductBacklog) toCompare;

        return sameIdentityAs(productBacklog);
    }

    /**
     * This method is used to generate a unique hash for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return productBacklogId.hashCode();
    }

    /**
     * This method returns an unmodifiable view of the user stories in the product backlog. The
     * returned list is a
     * read-only view of the user stories stored in the product backlog.
     *
     * @return an unmodifiable view of the user stories in the product backlog.
     */
    protected List<UsId> getUserStories() {
        return Collections.unmodifiableList(userStories);
    }

    /**
     * This method adds a new User Story to the userStories list if the User Story
     * doesn't already exist.
     * The priority is defined being 0 as the most important.
     *
     * @param usId     is the id of the User Story to be added to the product backlog.
     * @param priority is the priority of the user story, which means the position in
     *                 which it should be added. If the user doesn't specify it the UI
     *                 will send the parameter as -1, so that the User Story should be
     *                 added by the end of the list.
     * @return TRUE if the User Story was successfully added to the list and FALSE
     * otherwise. If the id is already in the Product backlog it returns and exception.
     */
    protected boolean addUserStory(int priority, UsId usId) {
        boolean result = true;

        if (priority > userStories.size()) {
            throw new IndexOutOfBoundsException("This position doesn't exist, since it's" +
                    " bigger than the list of User Stories.");
        }

        if (userStories.contains(usId)) {
            result = false;
        } else if (priority == -1) {
            userStories.add(usId);
        } else {
            userStories.add(priority, usId);
        }
        return result;
    }

    /**
     * Retrieves the identifier of the product backlog.
     *
     * @return The identifier of the product backlog as a string representation.
     */

    public String getProductBacklogId() {
        return this.productBacklogId.toString();
    }
}


