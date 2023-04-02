package org.switch2022.project.ddd.domain.model.project;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.PbId;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.utils.Validate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductBacklog implements Entity<ProductBacklog> {

    private final String productBacklogId;
    private final List<UsId> userStories = new ArrayList<>();

    public ProductBacklog(final PbId productBacklogId) {
        Validate.notNull(productBacklogId, "Product Backlog's ID can't be null.");

        this.productBacklogId = productBacklogId.toString();
    }

    /**
     * This method checks if two instances of ProductBacklog are equal by comparing the value of the attribute product
     * backlog id.
     *
     * @param other ProductBacklog instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
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
     * This method returns an unmodifiable view of the user stories in the product backlog. The returned list is a
     * read-only view of the user stories stored in the product backlog.
     *
     * @return an unmodifiable view of the user stories in the product backlog.
     */
    public List<UsId> getUserStories() {
        return Collections.unmodifiableList(userStories);
    }
}
