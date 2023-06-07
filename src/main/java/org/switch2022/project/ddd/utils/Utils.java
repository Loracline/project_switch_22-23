package org.switch2022.project.ddd.utils;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.value_object.ProjectStatus;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static java.lang.Integer.parseInt;
@Component
public final class Utils {
    private Utils(){}

    /**
     * This method extracts the number of an alphanumeric string by removing a specified expression and converts them
     * into an integer value.
     *
     * @param initialExpression  initial alphanumeric string
     * @param expressionToRemove string to remove
     * @return the string number as an integer value.
     */
    public static int getIntFromAlphanumericString(String initialExpression, String expressionToRemove) {
        Validate.notNull(initialExpression, "The initial expression must not be null");
        Validate.notNull(expressionToRemove, "The expression to remove must not be null");

        final int SPLIT_LIMIT = -2;

        String[] array = initialExpression.toLowerCase().split(expressionToRemove.toLowerCase(), SPLIT_LIMIT);
        return parseInt(array[1]);
    }

    /**
     * This method verifies if two project status are the same.
     *
     * @param actualProjectStatus  current project status.
     * @param desiredProjectStatus pretended project status.
     * @return TRUE if the project status is the same, and an illegal argument exception otherwise.
     */
    public static boolean hasStatus(ProjectStatus actualProjectStatus, ProjectStatus desiredProjectStatus) {
        if (actualProjectStatus.equals(desiredProjectStatus)) {
            return true;
        } else {
            throw new IllegalArgumentException(String.format("The project must be in %s phase.",
                    desiredProjectStatus));
        }
    }

    /**
     * This method converts a byte[] to a BufferedImage.
     *
     * @param bytes Array of bytes representing the binary content of the image
     * @return BufferedImage object converted from the array of bytes
     */
    public static BufferedImage convertByteArrayToBufferedImage(byte[] bytes) {
        BufferedImage image;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            image = ImageIO.read(byteArrayInputStream);
        } catch (Exception e) {
            image = null;
        }
        return image;
    }

    /**
     * This method converts a BufferedImage to a byte[], which is commonly used to
     * store images in databases.
     *
     * @param image BufferedImage object to be converted
     * @return Array of bytes representing the binary content of the image
     */
    public static byte[] convertBufferedImageToByteArray(BufferedImage image) {
        byte[] bytes;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", byteArrayOutputStream);
            bytes = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            bytes = null;
        }
        return bytes;
    }
}
