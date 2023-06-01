package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.exceptions.InvalidInputException;
import org.switch2022.project.ddd.utils.Validate;

/**
 * Enum Role, that contains the different types of roles that an account could have in a specific project.
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
        return this == other;
    }

    /**
     * This method generate a Role from a String .
     *
     * @param role to check.
     * @return a Role if the given String role is valid.
     * @throws InvalidInputException if the role is not valid.
     */
    public static Role generateRole(String role) {
        String roleInUpperCase = role.toUpperCase();
        String finalRole = convertBlankSpacesInUnderscore(roleInUpperCase);
        Role result;
        if (isRoleValid(finalRole)) {
            result = Role.valueOf(finalRole);
        } else {
            throw new InvalidInputException("Role is not valid");
        }
        return result;
    }

    /**
     * This method convert all blank spaces from a String into underscore.
     *
     * @param string to convert.
     * @return a String with no blank spaces and with underscore instead.
     */
    private static String convertBlankSpacesInUnderscore(String string) {
        char[] name = string.toCharArray();
        for (int i = 0; i < name.length; i++) {
            if (name[i] == ' ') {
                name[i] = '_';
            }
        }
        return new String(name);
    }

    /**
     * This method checks if a given role is valid.
     *
     * @param role to ckeck.
     * @return <code>true</code> if the role is valid, and <code>false</code> otherwise.
     */
    private static boolean isRoleValid(final String role) {
        boolean result = false;
        Role[] roles = Role.values();
        int i = 0;
        while (i < roles.length) {
            if (roles[i].toString().equals(role)) {
                result = true;
                i = roles.length;
            }
            i++;
        }
        return result;
    }
}

