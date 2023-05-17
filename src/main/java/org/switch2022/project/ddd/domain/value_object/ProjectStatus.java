package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;

public enum ProjectStatus implements ValueObject<ProjectStatus> {
    PLANNED("planned"), INCEPTION("inception"), ELABORATION("elaboration"),
    CONSTRUCTION("construction"), TRANSITION("transition"), WARRANTY("warranty"),
    CLOSED("closed");

    private final String value;

    ProjectStatus(String value) {
        this.value = value.toLowerCase().trim();
    }

    /**
     * This getter method returns a String with the Project status.
     */
    public String getStatus() {
        return value;
    }

    @Override
    public boolean sameValueAs(final ProjectStatus other) {
        return this.equals(other);
    }
}
