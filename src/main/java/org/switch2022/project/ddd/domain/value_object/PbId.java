package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

public class PbId implements ValueObject<PbId> {

    private final String productBacklogId;

    /**
     * Constructor.
     *
     * @param projectCode project code.
     */
    public PbId(final String projectCode) {
        Validate.notNull(projectCode, "Project code can't be null.");
        Validate.notEmpty(projectCode, "Project code can't be empty.");
        Validate.notBlank(projectCode, "Project code can't be blank.");

        this.productBacklogId = (projectCode + "_pb").toLowerCase();
    }

    /**
     * This method checks if two instances of PbId are equal by comparing the value of the attribute product backlog id.
     *
     * @param other PbId instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(PbId other) {
        return other != null && this.productBacklogId.equals(other.productBacklogId);
    }

    /**
     * This method checks if an instance of PbId is equal to another object.
     *
     * @param other object to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        PbId pbId = (PbId) other;

        return sameValueAs(pbId);
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
     * This method returns a string representation of this product backlog. The string representation consists of the
     * product backlog ID as a string.
     *
     * @return a string representation of this product backlog.
     */
    @Override
    public String toString() {
        return productBacklogId;
    }
}
