package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;

public enum ProjectStatus implements ValueObject<ProjectStatus> {
    PLANNED, INCEPTION, ELABORATION, CONSTRUCTION, TRANSITION, WARRANTY, CLOSED;

    @Override
    public boolean sameValueAs(final ProjectStatus other) {
        return this.equals(other);
    }
}
