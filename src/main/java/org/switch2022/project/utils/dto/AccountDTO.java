package org.switch2022.project.utils.dto;

import org.switch2022.project.model.Profile;

import java.awt.image.BufferedImage;
import java.util.Objects;

public class AccountDTO {
    /**
     * Attributes
     */
    public String name;
    public String email;
    public static boolean status;


    /**
     * The equals() method is used to determine whether two objects are equal in
     * terms of their content.
     * The default implementation of the equals() method compares the memory
     * addresses of the two objects, which means that two objects are considered
     * equal only if they are the same object in memory.
     *
     * @param toCompare accountDTO to compare to.
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
        AccountDTO that = (AccountDTO) toCompare;
        return  status == that.status && name.equals(that.name) && email.equals(that.email);
    }

    /**
     * The hashCode() method is used to generate a unique hash code for an
     * object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, email, status);
    }
}
