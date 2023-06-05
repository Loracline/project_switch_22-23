package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;

import java.awt.image.BufferedImage;
import java.util.Objects;

public class Photo implements ValueObject<Photo> {
    private final BufferedImage picture;

    /**
     * Constructor.
     *
     * @param picture of the account.
     */
    public Photo(final BufferedImage picture) {
        this.picture = picture;
    }

    /**
     * Getter method for the attribute: photo.
     *
     * @return photo of the account.
     */
    public BufferedImage getPicture() {
        return picture;
    }

    /**
     * This method checks if two instances of Photo are equal by comparing the value of the attribute photo.
     *
     * @param other Photo instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(Photo other) {
        return other != null && this.picture == (other.picture);
    }

    /**
     * This method checks if an instance of Budget is equal to another object.
     *
     * @param o object to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Photo other = (Photo) o;

        return sameValueAs(other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return Objects.hash(picture);
    }
}
