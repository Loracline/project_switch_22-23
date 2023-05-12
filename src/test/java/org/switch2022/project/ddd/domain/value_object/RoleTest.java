package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    /**
     * Tests for sameValueAs()
     */
    @Test
    void ensureThatTwoRolesHaveTheSameValue() {
        // Arrange
        Role productOwnerOne = Role.PRODUCT_OWNER;
        Role productOwnerTwo = Role.PRODUCT_OWNER;

        // Act
        boolean result = productOwnerOne.sameValueAs(productOwnerTwo);

        // Assert
        assertTrue(result);
    }

    @Test
    void ensureThatTwoRoleDoesNotHaveTheSameValue() {
        // Arrange
        Role productOwner = Role.PRODUCT_OWNER;
        Role teamMember = Role.TEAM_MEMBER;

        // Act
        boolean result = productOwner.sameValueAs(teamMember);

        // Assert
        assertFalse(result);
    }

    @Test
    void ensureThatTwoRolesCannotBeCompared_RoleIsNull() {
        // Arrange
        Role productOwnerOne = Role.PRODUCT_OWNER;

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> productOwnerOne.sameValueAs(null));
    }

    @Test
    void ensureThatRoleIsGeneratedSuccessfullyWhenIsInUpperCase() {
        // Arrange
        Role expected = Role.PRODUCT_OWNER;
        String productOwner = "PRODUCT_OWNER";

        // Act
        Role result = Role.generateRole(productOwner);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatRoleIsGeneratedSuccessfullyWhenIsInLowerCase() {
        // Arrange
        Role expected = Role.PRODUCT_OWNER;
        String productOwner = "product_owner";

        // Act
        Role result = Role.generateRole(productOwner);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatRoleIsGeneratedSuccessfullyWhenHasBlankSpaces() {
        // Arrange
        Role expected = Role.PRODUCT_OWNER;
        String productOwner = "PRODUCT OWNER";

        // Act
        Role result = Role.generateRole(productOwner);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatRoleIsNotGeneratedSuccessfullyBecauseDoesNotExist() {
        // Arrange
        String owner = "OWNER";

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> Role.generateRole(owner));


    }
}