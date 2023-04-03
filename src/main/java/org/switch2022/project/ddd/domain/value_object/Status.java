package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;

/**
 * Represents the different statuses for a user story.
 */
public enum Status implements ValueObject<Status> {
    PLANNED, RUNNING, FINISHED, BLOCKED;

    @Override
    public boolean sameValueAs(final Status other) {
        return this.equals(other);
    }
}

