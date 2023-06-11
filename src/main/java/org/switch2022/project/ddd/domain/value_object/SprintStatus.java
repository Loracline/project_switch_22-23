package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.exceptions.InvalidInputException;
import org.switch2022.project.ddd.utils.Validate;

/**
 * Enum SprintStatus that contains the different statuses that a sprint could have in a specific project.
 */
public enum SprintStatus implements ValueObject<SprintStatus> {
    OPEN, PLANNED, CLOSED;

    /**
     * This method checks if two Status are the same.
     *
     * @param other Enum SprintStatus to compare with.
     * @return <code>true</code> if the two status are the same, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(SprintStatus other) {
        Validate.notNull(other, "Sprint status to compare can not be null");
        return this == other;
    }

    /**
     * This method generates a SprintStatus from a String.
     *
     * @param status to check.
     * @return a SprintStatus if the given String status is valid.
     * @throws InvalidInputException if the status is not valid.
     */
    public static SprintStatus generateSprintStatus(String status) {
        String statusInUpperCase = status.toUpperCase();
        SprintStatus result;
        if (isStatusValid(statusInUpperCase)) {
            result = SprintStatus.valueOf(statusInUpperCase);
        } else {
            throw new InvalidInputException("Sprint status must be OPEN, PLANNED or CLOSED");
        }
        return result;
    }


    /**
     * This method checks if a given status is valid.
     *
     * @param status to ckeck.
     * @return <code>true</code> if the status is valid, and <code>false</code> otherwise.
     */
    private static boolean isStatusValid(final String status) {
        boolean result = false;
        SprintStatus[] validStatuses = SprintStatus.values();
        int i = 0;
        while (i < validStatuses.length) {
            if (validStatuses[i].toString().equals(status)) {
                result = true;
                i = validStatuses.length;
            }
            i++;
        }
        return result;
    }

    /**
     * This method returns a string representation of the SprintStatus enum.
     *
     * @return a string representing the sprint status value.
     */
    public String getStatus() {
       return this.name();
    }
}

