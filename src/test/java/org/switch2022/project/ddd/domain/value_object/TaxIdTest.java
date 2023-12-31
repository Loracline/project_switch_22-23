package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        int numberTwo = 514024054;

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
    @DisplayName("Valid Portugal tax ID - collective person")
    @Test
    void ensureReturnsTrueWhenItsValidPortugalIdEntity_collectivePerson() {
        // Arrange
        boolean expected = true;

        TaxId taxId = new TaxId("597664447");

        // Act
        boolean result = taxId.isValid();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Valid Portugal tax ID - collective public person")
    @Test
    void ensureReturnsTrueWhenItsValidPortugalIdEntity_collectivePublicPerson() {
        // Arrange
        boolean expected = true;

        TaxId taxId = new TaxId("621252158");

        // Act
        boolean result = taxId.isValid();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Valid Portugal tax ID - sole proprietorship")
    @Test
    void ensureReturnsTrueWhenItsValidPortugalIdEntity_soleProprietorship() {
        // Arrange
        boolean expected = true;

        TaxId taxId = new TaxId("885999134");

        // Act
        boolean result = taxId.isValid();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Valid Portugal tax ID - irregular legal person or provisional number")
    @Test
    void ensureReturnsTrueWhenItsValidPortugalIdEntity_irregularOrProvisional() {
        // Arrange
        boolean expected = true;

        TaxId taxId = new TaxId("922359199");

        // Act
        boolean result = taxId.isValid();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Valid Portugal tax ID - singular person (1)")
    @Test
    void ensureReturnsTrueWhenItsValidPortugalId_singularPerson_One() {
        // Arrange
        boolean expected = true;

        TaxId taxId = new TaxId("156948800");

        // Act
        boolean result = taxId.isValid();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Valid Portugal tax ID - singular person (2)")
    @Test
    void ensureReturnsTrueWhenItsValidPortugalId_singularPerson_Two() {
        // Arrange
        boolean expected = true;

        TaxId taxId = new TaxId("241582326");

        // Act
        boolean result = taxId.isValid();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Valid Portugal tax ID - singular person (3)")
    @Test
    void ensureReturnsTrueWhenItsValidPortugalId_singularPerson_Three() {
        // Arrange
        boolean expected = true;

        TaxId taxId = new TaxId("313874646");

        // Act
        boolean result = taxId.isValid();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Invalid tax ID (0)")
    @Test
    void ensureAnExceptionIsThrownWhenIdStartsWithZero() {
        // Arrange
        String expected = "Invalid or unsupported country for tax ID validation.";

        TaxId taxId = new TaxId("028014885");

        // Act
        InvalidInputException result = Assertions.assertThrows(InvalidInputException.class,
                taxId::isValid);

        // Assert
        assertEquals(expected, result.getMessage());
    }

    @DisplayName("Invalid tax ID (4)")
    @Test
    void ensureAnExceptionIsThrownWhenIdStartsWithFour() {
        // Arrange
        String expected = "Invalid or unsupported country for tax ID validation.";

        TaxId taxId = new TaxId("428014885");

        // Act
        InvalidInputException result = Assertions.assertThrows(InvalidInputException.class,
                taxId::isValid);

        // Assert
        assertEquals(expected, result.getMessage());
    }

    @DisplayName("Invalid tax ID (7)")
    @Test
    void ensureAnExceptionIsThrownWhenIdStartsWithSeven() {
        // Arrange
        String expected = "Invalid or unsupported country for tax ID validation.";

        TaxId taxId = new TaxId("728014885");

        // Act
        InvalidInputException result = Assertions.assertThrows(InvalidInputException.class,
                taxId::isValid);

        // Assert
        assertEquals(expected, result.getMessage());
    }

    @DisplayName("Tax id has incorrect length - less")
    @Test
    void ensureAnExceptionIsThrownWhenTaxIdLengthIsIncorrect_less() {
        // Arrange
        String expected = "Invalid or unsupported country for tax ID validation.";

        TaxId taxId = new TaxId("228-01-885");

        // Act
        InvalidInputException result = Assertions.assertThrows(InvalidInputException.class,
                taxId::isValid);

        // Assert
        assertEquals(expected, result.getMessage());
    }

    @DisplayName("Tax id has incorrect length - more")
    @Test
    void ensureAnExceptionIsThrownWhenTaxIdLengthIsIncorrect_more() {
        // Arrange
        String expected = "Invalid or unsupported country for tax ID validation.";

        TaxId taxId = new TaxId("228-0185-885");

        // Act
        InvalidInputException result = Assertions.assertThrows(InvalidInputException.class,
                taxId::isValid);

        // Assert
        assertEquals(expected, result.getMessage());
    }

    @DisplayName("Tax id has non-numeric characters")
    @Test
    void ensureAnExceptionIsThrownWhenTaxIdFormatIsIncorrect() {
        // Arrange
        String expected = "Invalid or unsupported country for tax ID validation.";

        TaxId taxId = new TaxId("228-01I-885");

        // Act
        InvalidInputException result = Assertions.assertThrows(InvalidInputException.class,
                taxId::isValid);

        // Assert
        assertEquals(expected, result.getMessage());
    }
}