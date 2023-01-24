package org.switch2022.project.DTO;

import org.switch2022.project.model.Profile;

import java.awt.image.BufferedImage;
import java.util.Objects;

public class AccountDTO {

    public String name;
    public String email;
    public long phoneNumber;
    public BufferedImage photo;
    public boolean status;
    public String profile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountDTO)) return false;
        AccountDTO that = (AccountDTO) o;
        return phoneNumber == that.phoneNumber && status == that.status && name.equals(that.name) && email.equals(that.email) && Objects.equals(photo, that.photo) && profile.equals(that.profile);
    }
}
