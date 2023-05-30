package org.switch2022.project.ddd.datamodel_jpa;

import lombok.Data;
import org.switch2022.project.ddd.utils.Utils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.awt.image.BufferedImage;

/**
 * The class Account was built to create and manage new accounts.
 * An account is defined by: name, e-mail, phone number, profile, status and
 * photo (this last attribute is optional).
 */

@Data
@Entity
@Table(name = "accounts")
public class AccountJpa {

    /**
     * Attributes
     */
    @Id
    private String email;
    private String name;
    private String phoneNumber;
    private String accountStatus;
    //There is no primitive data type in Java that is suitable for storing photos. We can use
    //non-primitive data types like byte[].This is an array of bytes that represents the binary
    //content of an image. It is commonly used to store images in databases.
    private byte[] photo;
    private String profileId;

    /**
     * Constructor
     * Constructs a new instance of AccountJpa with the provided parameters.
     *
     * @param email         the identifier of the account.
     * @param name          the name of the account.
     * @param phoneNumber   the phoneNumber of the account.
     * @param accountStatus the status of the account.
     * @param photo         the photo (optional) of the account.
     */

    public AccountJpa(String email, String name, int phoneNumber,
                      String accountStatus, String profileId, BufferedImage photo) {
        this.email = email;
        this.name = name;
        this.phoneNumber = Integer.toString(phoneNumber);
        this.accountStatus = accountStatus;
        this.profileId = profileId;

        if (photo != null) {
            this.photo = Utils.convertBufferedImageToByteArray(photo);
        }
    }

    protected AccountJpa() {
    }

    /**
     * Getter method that returns a BufferedImage with the photo
     *
     * @return photo.
     */
    public byte[] getPhoto() {
        byte[] bufferedImage = null;
        if (photo != null) {
            bufferedImage = this.photo;
        }
        return bufferedImage;
    }
}
