package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        String country = "portugal";

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
        String country = "portugal";

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
        String country = "portugal";

        String expected = "The number must not be blank";

        // ACT
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new TaxId(number));

        // ASSERT
        assertEquals(expected, result.getMessage());
    }

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

    @DisplayName("Number and country are not the same")
    @Test
    void ensureReturnsFalseWhenTwoTaxIdsHaveDifferentAttributes() {
        // ARRANGE
        String numberOne = "514024054";
        String numberTwo = "12345678Z";
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
}