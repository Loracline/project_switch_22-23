package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.exceptions.InvalidTaxIdException;

import static org.junit.jupiter.api.Assertions.*;

class TaxIdTest {

    /**
     * Constructor
     */
    @SuppressWarnings("all")
    @DisplayName("Tax ID's number is null")
    @Test
    void ensureATaxIdIsNotCreatedBecauseNumberIsNull() {
        // ARRANGE
        String number = null;

        String expected = "The number must not be null";

        // ACT
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new TaxId(number));

        // ASSERT
        assertEquals(expected, result.getMessage());
    }

    @SuppressWarnings("all")
    @DisplayName("Tax ID's number is empty")
    @Test
    void ensureATaxIdIsNotCreatedBecauseNumberIsEmpty() {
        // ARRANGE
        String number = "";

        String expected = "The number must not be empty";

        // ACT
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new TaxId(number));

        // ASSERT
        assertEquals(expected, result.getMessage());
    }

    @SuppressWarnings("all")
    @DisplayName("Tax ID's number is blank")
    @Test
    void ensureATaxIdIsNotCreatedBecauseNumberIsBlank() {
        // ARRANGE
        String number = "   ";

        String expected = "The number must not be blank";

        // ACT
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new TaxId(number));

        // ASSERT
        assertEquals(expected, result.getMessage());
    }

    /**
     * METHOD sameValueAs()
     */
    @DisplayName("Two tax IDs are the same")
    @Test
    void ensureReturnsTrueWhenTwoTaxIdsHaveSameAttributes() {
        // ARRANGE
        String number = "514024054";
        TaxId taxIdOne = new TaxId(number);
        TaxId taxIdTwo = new TaxId(number);

        boolean expected = true;

        // ACT
        boolean result = taxIdOne.sameValueAs(taxIdTwo);

        // ASSERT
        assertEquals(expected, result);
    }

    @DisplayName("Number is not the same")
    @Test
    void ensureReturnsFalseWhenTwoTaxIdsHaveDifferentNumbers() {
        // ARRANGE
        String numberOne = "514024054";
        String numberTwo = "228019885";
        TaxId taxIdOne = new TaxId(numberOne);
        TaxId taxIdTwo = new TaxId(numberTwo);

        boolean expected = false;

        // ACT
        boolean result = taxIdOne.sameValueAs(taxIdTwo);

        // ASSERT
        assertEquals(expected, result);
    }

    @SuppressWarnings("all")
    @DisplayName("One of the tax ID is null")
    @Test
    void ensureReturnsFalseWhenOneOfTheTaxIdIsNull() {
        // ARRANGE
        String numberOne = "514024054";
        TaxId taxIdOne = new TaxId(numberOne);
        TaxId taxIdTwo = null;

        boolean expected = false;

        // ACT
        boolean result = taxIdOne.sameValueAs(taxIdTwo);

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * METHOD equals()
     */
    @SuppressWarnings("all")
    @DisplayName("Same object equals itself")
    @Test
    void ensureSameTaxIdEqualsItself() {
        // Arrange
        String number = "514024054";

        TaxId reference = new TaxId(number);
        TaxId other = reference;

        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Two taxId are equal - one with spaces")
    @Test
    void ensureTwoTaxIdAreEqualIfNumberIsTheSameWithSpaces() {
        // Arrange
        String numberOne = "514 024 054";
        String numberTwo = "514024054";

        TaxId reference = new TaxId(numberOne);
        TaxId other = new TaxId(numberTwo);

        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Two taxId are equal - one with hyphens")
    @Test
    void ensureTwoTaxIdAreEqualIfNumberIsTheSameWithHyphens() {
        // Arrange
        String numberOne = "514 024 054";
        String numberTwo = "514-024-054";

        TaxId reference = new TaxId(numberOne);
        TaxId other = new TaxId(numberTwo);

        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Two taxId are different")
    @Test
    void ensureTwoTaxIdAreDifferentIfNumberIsNotTheSame() {
        // Arrange
        String numberOne = "514024054";
        String numberTwo = "514024053";

        TaxId reference = new TaxId(numberOne);
        TaxId other = new TaxId(numberTwo);

        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    @SuppressWarnings("all")
    @DisplayName("TaxId does not equal null")
    @Test
    void ensureTaxIdDoesNotEqualNullObject() {
        // Arrange
        String numberOne = "514024054";

        TaxId reference = new TaxId(numberOne);
        TaxId other = null;

        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    @SuppressWarnings("all")
    @DisplayName("TaxId does not equal other type of object")
    @Test
    void ensureTaxIdDoesNotEqualOtherTypeOfObject() {
        // Arrange
        String numberOne = "514024054";
        Number numberTwo = 514024054;

        TaxId reference = new TaxId(numberOne);
        Code other = new Code(numberTwo);

        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD hashCode()
     */
    @DisplayName("Two taxId have same hashcode - one with spaces")
    @Test
    void ensureTwoTaxIdHaveSameHashCodeIfEqualWithSpaces() {
        // Arrange
        String numberOne = "514 024 054";
        String numberTwo = "514024054";

        TaxId reference = new TaxId(numberOne);
        TaxId other = new TaxId(numberTwo);

        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Two taxId have same hashcode - one with hyphens")
    @Test
    void ensureTwoTaxIdHaveSameHashCodeIfEqualWithHyphens() {
        // Arrange
        String numberOne = "514 024 054";
        String numberTwo = "514-024-054";

        TaxId reference = new TaxId(numberOne);
        TaxId other = new TaxId(numberTwo);

        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Two taxId have different hashcode")
    @Test
    void ensureTwoTaxIdHaveDifferentHashCodeIfNotTheSame() {
        // Arrange
        String numberOne = "514024054";
        String numberTwo = "514024053";

        TaxId reference = new TaxId(numberOne);
        TaxId other = new TaxId(numberTwo);

        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertNotEquals(expected, result);
    }

    /**
     * METHOD getNumber()
     */
    @DisplayName("Tax ID's number is retrieved successfully")
    @Test
    void ensureNumberIsRetrievedSuccessfully() {
        // Arrange
        String expected = "514024054";
        TaxId taxId = new TaxId(expected);

        // Act
        String result = taxId.getNumber();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD isValid()
     */
    @DisplayName("Valid Portugal tax ID - legal entity")
    @Test
    void ensureReturnsTrueWhenItsValidPortugalIdEntity() {
        // Arrange
        boolean expected = true;

        TaxId taxId = new TaxId("514 024 054");

        // Act
        boolean result = taxId.isValid();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Valid Portugal tax ID - individual")
    @Test
    void ensureReturnsTrueWhenItsValidPortugalIdIndividual() {
        // Arrange
        boolean expected = true;

        TaxId taxId = new TaxId("228-019-885");

        // Act
        boolean result = taxId.isValid();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Valid Spain tax ID - individual")
    @Test
    void ensureReturnsTrueWhenItsValidSpainIdIndividual() {
        // Arrange
        boolean expected = true;

        TaxId taxId = new TaxId("12345678Z");

        // Act
        boolean result = taxId.isValid();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Invalid tax ID")
    @Test
    void ensureAnExceptionIsThrownWhenNotValidId() {
        // Arrange
        String expected = "Invalid or unsupported country for tax ID validation.";

        TaxId taxId = new TaxId("028014885");

        // Act
        InvalidTaxIdException result = Assertions.assertThrows(InvalidTaxIdException.class,
                taxId::isValid);

        // Assert
        assertEquals(expected, result.getMessage());
    }

    @DisplayName("Tax id has incorrect length")
    @Test
    void ensureAnExceptionIsThrownWhenTaxIdLengthIsIncorrect() {
        // Arrange
        String expected = "Invalid or unsupported country for tax ID validation.";

        TaxId taxId = new TaxId("228-01-885");

        // Act
        InvalidTaxIdException result = Assertions.assertThrows(InvalidTaxIdException.class,
                taxId::isValid);

        // Assert
        assertEquals(expected, result.getMessage());
    }

    @DisplayName("Tax id has non-numeric characters")
    @Test
    void ensureAnExceptionIsThrownWhenTaxIdFormatIsIncorrect() {
        // Arrange
        String expected = "Invalid or unsupported country for tax ID validation.";

        TaxId taxId = new TaxId("228-01X-885");

        // Act
        InvalidTaxIdException result = Assertions.assertThrows(InvalidTaxIdException.class,
                taxId::isValid);

        // Assert
        assertEquals(expected, result.getMessage());
    }
}