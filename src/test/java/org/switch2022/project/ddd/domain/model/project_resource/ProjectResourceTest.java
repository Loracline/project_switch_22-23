package org.switch2022.project.ddd.domain.model.project_resource;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProjectResourceTest {

    /**
     * Testing if Project Resource is created when all the arguments passed in the constructor are not null.
     * It should not throw an exception.
     */
    @Test
    void ensureThatProjectResourceIsCreatedSuccessfully() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        // Act, Assert
        assertDoesNotThrow(() -> new ProjectResource(codeDouble, emailDouble, roleDouble,
                periodDouble, costDouble, percentageOfAllocationDouble));
    }


    /**
     * Testing if Project Resource is not created when Code Is Null.
     * It should throw an InvalidInputException.
     */
    @Test
    void ensureThatProjectResourceIsNotCreatedWhenCodeIsNull() {
        // Arrange
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new ProjectResource( null,
                emailDouble, roleDouble, periodDouble, costDouble, percentageOfAllocationDouble));
    }

    /**
     * Testing if Project Resource is not created when Email Is Null.
     * It should throw an InvalidInputException.
     */
    @Test
    void ensureThatProjectResourceIsNotCreatedWhenEmailIsNull() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new ProjectResource(codeDouble, null
                , roleDouble, periodDouble, costDouble, percentageOfAllocationDouble));
    }

    /**
     * Testing if Project Resource is not created when Role Is Null.
     * It should throw an InvalidInputException.
     */
    @Test
    void ensureThatProjectResourceIsNotCreatedWhenRoleIsNull() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new ProjectResource(codeDouble, emailDouble
                , null, periodDouble, costDouble, percentageOfAllocationDouble));
    }

    /**
     * Testing if Project Resource is not created when Period Is Null.
     * It should throw an InvalidInputException.
     */
    @Test
    void ensureThatProjectResourceIsNotCreatedWhenPeriodIsNull() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new ProjectResource(codeDouble, emailDouble
                , roleDouble, null, costDouble, percentageOfAllocationDouble));
    }

    /**
     * Testing if Project Resource is not created when CostPerHour Is Null.
     * It should throw an InvalidInputException.
     */
    @Test
    void ensureThatProjectResourceIsNotCreatedWhenCostIsNull() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new ProjectResource(codeDouble, emailDouble
                , roleDouble, periodDouble, null, percentageOfAllocationDouble));
    }

    /**
     * Testing if Project Resource is not created when Role Is Null.
     * It should throw an InvalidInputException.
     */
    @Test
    void ensureThatProjectResourceIsNotCreatedWhenPercentageOfAllocationIsNull() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new ProjectResource(codeDouble, emailDouble
                , roleDouble, periodDouble, costDouble, null));
    }


    /**
     * Tests for sameIdentityAs()
     */
    @Test
    void ensureThatTwoProjectResourcesHaveTheSameIdentity() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResourceOne = new ProjectResource(codeDouble, emailDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);
        ProjectResource projectResourceTwo = new ProjectResource(codeDouble, emailDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);

        // Act
        boolean result = projectResourceOne.sameIdentityAs(projectResourceTwo);

        // Assert
        assertTrue(result);
    }

    @Test
    void ensureThatTwoProjectResourcesDoNotHaveTheSameIdentityBecauseProjectCodesAreDifferent() {
        // Arrange
        Code codeOneDouble = mock(Code.class);
        Code codeTwoDouble = mock(Code.class);

        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResourceOne = new ProjectResource(codeOneDouble, emailDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);
        ProjectResource projectResourceTwo = new ProjectResource(codeTwoDouble, emailDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);

        // Act
        boolean result = projectResourceOne.sameIdentityAs(projectResourceTwo);

        // Assert
        assertFalse(result);
    }

    @Test
    void ensureThatTwoProjectResourcesDoNotHaveTheSameIdentityBecauseAccountEmailsAreDifferent() {
        // Arrange
        Code codeDouble = mock(Code.class);

        Email emailOneDouble = mock(Email.class);
        Email emailTwoDouble = mock(Email.class);

        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResourceOne = new ProjectResource(codeDouble, emailOneDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);
        ProjectResource projectResourceTwo = new ProjectResource(codeDouble, emailTwoDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);

        // Act
        boolean result = projectResourceOne.sameIdentityAs(projectResourceTwo);

        // Assert
        assertFalse(result);
    }

    @Test
    void ensureThatTwoProjectResourcesDoNotHaveTheSameIdentityBecauseRolesAreDifferent() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);

        Role roleOneDouble = mock(Role.class);
        Role roleTwoDouble = mock(Role.class);

        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResourceOne = new ProjectResource(codeDouble, emailDouble,
                roleOneDouble, periodDouble, costDouble, percentageOfAllocationDouble);
        ProjectResource projectResourceTwo = new ProjectResource(codeDouble, emailDouble,
                roleTwoDouble, periodDouble, costDouble, percentageOfAllocationDouble);

        // Act
        boolean result = projectResourceOne.sameIdentityAs(projectResourceTwo);

        // Assert
        assertFalse(result);
    }

    @Test
    void ensureThatTwoProjectResourcesDoNotHaveTheSameIdentityBecauseAllocationPeriodsAreDifferent() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);

        Period periodOneDouble = mock(Period.class);
        Period periodTwoDouble = mock(Period.class);

        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResourceOne = new ProjectResource(codeDouble, emailDouble,
                roleDouble, periodOneDouble, costDouble, percentageOfAllocationDouble);
        ProjectResource projectResourceTwo = new ProjectResource(codeDouble, emailDouble,
                roleDouble, periodTwoDouble, costDouble, percentageOfAllocationDouble);

        // Act
        boolean result = projectResourceOne.sameIdentityAs(projectResourceTwo);

        // Assert
        assertFalse(result);
    }

    @Test
    void ensureThatTwoProjectResourcesDoNotHaveTheSameIdentityBecauseOneIsNull() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResourceOne = new ProjectResource(codeDouble, emailDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> projectResourceOne.sameIdentityAs(null));
    }

    /**
     * Tests for the equals() method.
     */
    @Test
    void ensureThatProjectResourceEqualsItself() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource expected = new ProjectResource(codeDouble, emailDouble, roleDouble,
                periodDouble, costDouble, percentageOfAllocationDouble);
        ProjectResource result = new ProjectResource(codeDouble, emailDouble, roleDouble,
                periodDouble, costDouble, percentageOfAllocationDouble);

        // Act, Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatTwoDifferentProjectResourcesAreNotEqual() {
        // Arrange
        Code codeOneDouble = mock(Code.class);
        Code codeTwoDouble = mock(Code.class);

        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource expected = new ProjectResource(codeOneDouble, emailDouble, roleDouble,
                periodDouble, costDouble, percentageOfAllocationDouble);
        ProjectResource result = new ProjectResource(codeTwoDouble, emailDouble, roleDouble,
                periodDouble, costDouble, percentageOfAllocationDouble);

        // Act, Assert
        assertNotEquals(expected, result);
    }

    @SuppressWarnings("all")
    @Test
    void ensureThatProjectResourceIsNotEqualToOtherTypeOfObject() {
        // Arrange
        String expected = "Hello World!";
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource result = new ProjectResource(codeDouble, emailDouble, roleDouble,
                periodDouble, costDouble, percentageOfAllocationDouble);

        // Act, Assert
        assertNotEquals(expected, result);
    }

    /**
     * Tests for hashCode()
     */
    @Test
    void ensureThatTwoProjectResourcesHaveSameHashCode() {
        // ARRANGE
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResourceOne = new ProjectResource(codeDouble, emailDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);
        ProjectResource projectResourceTwo = new ProjectResource(codeDouble, emailDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);

        // ACT
        int hashCodeReference = projectResourceOne.hashCode();
        int hashCodeOther = projectResourceTwo.hashCode();

        // ASSERT
        assertEquals(hashCodeOther, hashCodeReference);
    }

    @Test
    void ensureThatTwoProjectResourcesWithDifferentProjectCodesHaveDifferentHashCodes() {
        // ARRANGE
        Code codeOneDouble = mock(Code.class);
        Code codeTwoDouble = mock(Code.class);

        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResourceOne = new ProjectResource(codeOneDouble, emailDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);
        ProjectResource projectResourceTwo = new ProjectResource(codeTwoDouble, emailDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);

        // ACT
        int hashCodeReference = projectResourceOne.hashCode();
        int hashCodeOther = projectResourceTwo.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther, hashCodeReference);
    }

    @Test
    void ensureThatTwoProjectResourcesWithDifferentAccountEmailsHaveDifferentHashCodes() {
        // ARRANGE
        Code codeDouble = mock(Code.class);

        Email emailOneDouble = mock(Email.class);
        Email emailTwoDouble = mock(Email.class);

        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResourceOne = new ProjectResource(codeDouble, emailOneDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);
        ProjectResource projectResourceTwo = new ProjectResource(codeDouble, emailTwoDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);

        // ACT
        int hashCodeReference = projectResourceOne.hashCode();
        int hashCodeOther = projectResourceTwo.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther, hashCodeReference);
    }

    @Test
    void ensureThatTwoProjectResourcesWithDifferentRolesHaveDifferentHashCodes() {
        // ARRANGE
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);

        Role roleOneDouble = mock(Role.class);
        Role roleTwoDouble = mock(Role.class);

        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResourceOne = new ProjectResource(codeDouble, emailDouble,
                roleOneDouble, periodDouble, costDouble, percentageOfAllocationDouble);
        ProjectResource projectResourceTwo = new ProjectResource(codeDouble, emailDouble,
                roleTwoDouble, periodDouble, costDouble, percentageOfAllocationDouble);

        // ACT
        int hashCodeReference = projectResourceOne.hashCode();
        int hashCodeOther = projectResourceTwo.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther, hashCodeReference);
    }

    @Test
    void ensureThatTwoProjectResourcesWithDifferentPeriodsHaveDifferentHashCodes() {
        // ARRANGE
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);

        Period periodOneDouble = mock(Period.class);
        Period periodTwoDouble = mock(Period.class);

        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResourceOne = new ProjectResource(codeDouble, emailDouble,
                roleDouble, periodOneDouble, costDouble, percentageOfAllocationDouble);
        ProjectResource projectResourceTwo = new ProjectResource(codeDouble, emailDouble,
                roleDouble, periodTwoDouble, costDouble, percentageOfAllocationDouble);

        // ACT
        int hashCodeReference = projectResourceOne.hashCode();
        int hashCodeOther = projectResourceTwo.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther, hashCodeReference);
    }

    /**
     * Method hasProjectCode(ProjectCode projectCode) checks if an instance of ProjectResource has a given project
     * code as attribute or not.
     *
     * Scenario 01: The instance of ProjectResource has as attribute a project code that is equal to the project code
     * of interest.
     * It should assert true.
     */
    @Test
    void ensureItReturnsTrueIfTheProjectCodesAreEqual() {
        //Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource resource = new ProjectResource(codeDouble, emailDouble, roleDouble, periodDouble, costDouble,
                percentageOfAllocationDouble);
        //Act
        boolean result = resource.hasProjectCode(codeDouble);

        //Assert
        assertTrue(result);
    }


    /**
     * Method hasProjectCode(ProjectCode projectCode) checks if an instance of ProjectResource has a given project
     * code as attribute or not.
     *
     * Scenario 02: The instance of ProjectResource has as attribute a project code that is different from the
     * project code of interest.
     * It should assert false.
     */
    @Test
    void ensureItReturnsFalseIfTheProjectCodesAreDifferent() {
        //Arrange
        Code codeOneDouble = mock(Code.class);
        Code codeTwoDouble = mock(Code.class);

        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource resource = new ProjectResource(codeOneDouble, emailDouble, roleDouble, periodDouble, costDouble,
                percentageOfAllocationDouble);
        //Act
        boolean result = resource.hasProjectCode(codeTwoDouble);

        //Assert
        assertFalse(result);
    }
}



