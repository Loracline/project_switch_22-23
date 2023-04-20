package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;

/**
 * Represents the different statuses for a user story.
 */
public enum Status implements ValueObject<Status> {
    PLANNED("Planned"), RUNNING("Running"), FINISHED("Finished"), BLOCKED("Blocked");
    private final String value;


    Status(String value) {
        this.value = value;
    }

    /**
     * This getter method returns a String with User Story status.
     */
    public String getStatus() {
        return value;
    }



    @Override
    public boolean sameValueAs(final Status other) {
        return this.equals(other);
    }
}

