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
    public long phoneNumber;
    public BufferedImage photo;
    public boolean status;
    public Profile profile;

    /**
     * This method determines whether two accountDTOs are equal in terms of
     * their content.
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
        return phoneNumber == that.phoneNumber && status == that.status && name.equals(that.name) && email.equals(that.email) && Objects.equals(photo, that.photo) && profile.equals(that.profile);
    }
}
