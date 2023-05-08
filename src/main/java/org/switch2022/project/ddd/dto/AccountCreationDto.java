package org.switch2022.project.ddd.dto;

import java.awt.image.BufferedImage;

/**
 * Represents an Account creation data transfer object.
 * This class contains information about the account such as its email, name, phone number
 * and, optionally, photo.
 */

public class AccountCreationDto {
    /**
     * Attributes
     */
    public final String name;
    public final String email;
    public final int phoneNumber;
    public final BufferedImage photo;

    /**
     * Constructs a new AccountCreationDto object with the specified name, email, phone
     * number and image.
     * @param name of the user account
     * @param email of the user account
     * @param phoneNumber of the user account
     * @param photo of the user account
     */
    public AccountCreationDto(String name, String email, int phoneNumber,
                              BufferedImage photo) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.photo = photo;
    }
}
