package org.switch2022.project.dto;

import java.util.Objects;

public class UserStoryDto {
    /**
     * Attributes
     */
    public final String userStoryNumber;
    public final String userStoryText;
    public final String status;

    /**
     * Constructor
     */
    public UserStoryDto(String userStoryNumber, String userStoryText, String status) {
        this.userStoryNumber = userStoryNumber.toLowerCase().trim();
        this.userStoryText = userStoryText.toLowerCase().trim();
        this.status = status.toLowerCase().trim();
    }

    /**
     * The equals() method is used to determine whether two objects are equal in
     * terms of their content.
     * The default implementation of the equals() method compares the memory
     * addresses of the two objects, which means that two objects are considered
     * equal only if they are the same object in memory.
     *
     * @param toCompare userStoryDto to compare to.
     * @return TRUE if equal, and FALSE otherwise.
     */
    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) {
            return true;
        }
        if (toCompare == null || getClass() != toCompare.getClass()) {
            return false;
        }
        UserStoryDto that = (UserStoryDto) toCompare;
        return status.equals(that.status) && userStoryNumber.equals(
                that.userStoryNumber) && userStoryText.equals(that.userStoryText);
    }

    /**
     * The hashCode() method is used to generate a unique hash code for an
     * object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(userStoryNumber, userStoryText, status);
    }
}
