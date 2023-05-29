package org.switch2022.project.ddd.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.ProjectStatus;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    /**
     * METHOD getIntFromAlphanumericString(String fullExpression, String expressionToRemove)
     * Scenario 1: verifies that the initial expression is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatThrowsExceptionWhenInitialExpressionIsNull() {
        //Arrange
        String initialExpression = null;
        String expressionToRemove = "P";

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () ->
                Utils.getIntFromAlphanumericString(initialExpression, expressionToRemove));

    }

    /**
     * Scenario 2: verifies that the expression to remove is null.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatThrowsExceptionWhenExpressionToRemoveIsNull() {
        //Arrange
        String initialExpression = "P001";
        String expressionToRemove = null;

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () ->
                Utils.getIntFromAlphanumericString(initialExpression, expressionToRemove));

    }

    /**
     * Scenario 3: verifies that the number is successfully retrieved from the expression.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void ensureThatReturnsTheNumberWithinTheAlphanumericExpression() {
        //Arrange
        String initialExpression = "P001";
        String expressionToRemove = "P";
        int expected = 1;

        //Act
        int result = Utils.getIntFromAlphanumericString(initialExpression, expressionToRemove);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD hasStatus()
     * Scenario 1: The status is the same. Should return true.
     */
    @DisplayName("Project has desired status")
    @Test
    void ensureThatReturnsTrueIfProjectStatusIsTheDesiredOne() {
        // ARRANGE
        ProjectStatus actualProjectStatus = ProjectStatus.PLANNED;
        ProjectStatus desiredProjectStatus = ProjectStatus.PLANNED;

        // ACT
        boolean result = Utils.hasStatus(actualProjectStatus, desiredProjectStatus);

        // ASSERT
        assertTrue(result);
    }

    /**
     * Scenario 2: The status is not the same. Should return an exception.
     */
    @DisplayName("Project hasn't the desired status")
    @Test
    void ensureThatReturnsExceptionIfProjectStatusIsNotTheDesiredOne() {
        // ARRANGE
        ProjectStatus actualProjectStatus = ProjectStatus.PLANNED;
        ProjectStatus desiredProjectStatus = ProjectStatus.INCEPTION;

        // ACT & ASSERT
        assertThrows(IllegalArgumentException.class, () ->
                Utils.hasStatus(actualProjectStatus, desiredProjectStatus));
    }

    /**
     * METHOD convertBufferedImageToByteArray()
     * Scenario 1: this test verifies that the conversion between a BufferedImage and a Byte
     * Array representation of the image produces the expected results.
     * It uses the Utils class to perform the conversions.
     * <p>
     * Should return an assertion error if the converters don't work as pretended.
     */
    @Test
    void ensureBufferedImageAndByteArrayConvertersAreWorkingCorrectly() {
        //ARRANGE
        BufferedImage defaultImage = new BufferedImage(5, 5, BufferedImage.TYPE_3BYTE_BGR);

        //ACT
        byte[] bytes = Utils.convertBufferedImageToByteArray(defaultImage);

        //ASSERT
        BufferedImage resultImage = Utils.convertByteArrayToBufferedImage(bytes);
        assertImagesAreEqual(defaultImage, resultImage);
    }

    /**
     * Scenario 2: this test verifies the behavior of the Utils.convertBufferedImageToByteArray
     * method when it is called with a null BufferedImage.
     * The method is expected to throw an exception in this case. The test asserts that the
     * returned byte array is null.
     * <p>
     * If the byte array is not null, an assertion error should be thrown.
     */
    @Test
    void ensureConvertBufferedImageToByteArrayReturnsNullWhenThrowsException() {
        //ACT
        byte[] bytes = Utils.convertBufferedImageToByteArray(null);

        //ASSERT
        assertNull(bytes);
    }

    /**
     * Scenario 3: this test verifies the behavior of the Utils.convertByteArrayToBufferedImage
     * method when it is called with a null Byte Array.
     * The method is expected to throw an exception in this case. The test asserts that the
     * returned BufferedImage is null.
     * <p>
     * If the BufferedImage is not null, an assertion error should be thrown.
     */
    @Test
    void ensureConvertByteArrayToBufferedImageReturnsNullWhenThrowsException() {
        //ACT
        BufferedImage bufferedImage = Utils.convertByteArrayToBufferedImage(null);

        //ASSERT
        assertNull(bufferedImage);
    }

    /**
     * Asserts that two BufferedImages are equal, pixel by pixel.
     *
     * @param expected the expected BufferedImage
     * @param actual   the actual BufferedImage
     * @throws AssertionError if the BufferedImages are not equal
     */
    private void assertImagesAreEqual(BufferedImage expected, BufferedImage actual) {
        assertEquals(expected.getWidth(), actual.getWidth());
        assertEquals(expected.getHeight(), actual.getHeight());

        for (int x = 0; x < actual.getWidth(); x++) {
            for (int y = 0; y < actual.getHeight(); y++) {
                assertEquals(expected.getRGB(x, y), actual.getRGB(x, y));
            }
        }
    }
}