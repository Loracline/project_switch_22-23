package org.switch2022.project.dto;

import java.util.Objects;

public class AccountDto {
    /**
     * Attributes
     */
    public final String name;
    public final String email;
    public final boolean status;

    /**
     * Constructor
     */
    public AccountDto(String name, String email, boolean status) {
        this.name = name;
        this.email = email;
        this.status = status;
    }

    /**
     * The equals() method is used to determine whether two objects are equal in
     * terms of their content.
     * The default implementation of the equals() method compares the memory
     * addresses of the two objects, which means that two objects are considered
     * equal only if they are the same object in memory.
     *
     * @param toCompare accountDto to compare to.
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
        AccountDto that = (AccountDto) toCompare;
        return status == that.status && name.equals(that.name) && email.equals(that.email);
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
