package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

/**
 * Enum Role, that contains the diferente types of roles that an account could have in a specific project.
 */
public enum Role implements ValueObject<Role> {
    PRODUCT_OWNER, SCRUM_MASTER, PROJECT_MANAGER, TEAM_MEMBER;

    /**
     * This method checks if two Roles are the same.
     *
     * @param other Role Enum to compare with.
     * @return <code>true</code> if the two enums are the same, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(Role other) {
        Validate.notNull(other, "Role to compare can not be null");
        return this.equals(other);
    }
}

