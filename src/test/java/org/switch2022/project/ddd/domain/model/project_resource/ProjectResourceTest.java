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
        ProjectResourceId resourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        // Act, Assert
        assertDoesNotThrow(() -> new ProjectResource(resourceIdDouble, codeDouble, emailDouble, roleDouble,
                periodDouble, costDouble, percentageOfAllocationDouble));
    }

    /**
     * Testing if Project Resource is not created when ID Is Null.
     * It should throw an InvalidInputException.
     */
    @Test
    void ensureThatProjectResourceIsNotCreatedWhenIdIsNull() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new ProjectResource( null, codeDouble, emailDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble));
    }

    /**
     * Testing if Project Resource is not created when Code Is Null.
     * It should throw an InvalidInputException.
     */
    @Test
    void ensureThatProjectResourceIsNotCreatedWhenCodeIsNull() {
        // Arrange
        ProjectResourceId resourceIdDouble = mock(ProjectResourceId.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new ProjectResource(resourceIdDouble, null,
                emailDouble, roleDouble, periodDouble, costDouble, percentageOfAllocationDouble));
    }

    /**
     * Testing if Project Resource is not created when Email Is Null.
     * It should throw an InvalidInputException.
     */
    @Test
    void ensureThatProjectResourceIsNotCreatedWhenEmailIsNull() {
        // Arrange
        ProjectResourceId resourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new ProjectResource(resourceIdDouble, codeDouble, null
                , roleDouble, periodDouble, costDouble, percentageOfAllocationDouble));
    }

    /**
     * Testing if Project Resource is not created when Role Is Null.
     * It should throw an InvalidInputException.
     */
    @Test
    void ensureThatProjectResourceIsNotCreatedWhenRoleIsNull() {
        // Arrange
        ProjectResourceId resourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new ProjectResource(resourceIdDouble, codeDouble,
                emailDouble, null, periodDouble, costDouble, percentageOfAllocationDouble));
    }

    /**
     * Testing if Project Resource is not created when Period Is Null.
     * It should throw an InvalidInputException.
     */
    @Test
    void ensureThatProjectResourceIsNotCreatedWhenPeriodIsNull() {
        // Arrange
        ProjectResourceId resourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new ProjectResource(resourceIdDouble, codeDouble,
                emailDouble, roleDouble, null, costDouble, percentageOfAllocationDouble));
    }

    /**
     * Testing if Project Resource is not created when CostPerHour Is Null.
     * It should throw an InvalidInputException.
     */
    @Test
    void ensureThatProjectResourceIsNotCreatedWhenCostIsNull() {
        // Arrange
        ProjectResourceId resourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new ProjectResource(resourceIdDouble, codeDouble, emailDouble
                , roleDouble, periodDouble, null, percentageOfAllocationDouble));
    }

    /**
     * Testing if Project Resource is not created when Role Is Null.
     * It should throw an InvalidInputException.
     */
    @Test
    void ensureThatProjectResourceIsNotCreatedWhenPercentageOfAllocationIsNull() {
        // Arrange
        ProjectResourceId resourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new ProjectResource(resourceIdDouble, codeDouble, emailDouble
                , roleDouble, periodDouble, costDouble, null));
    }


    /**
     * Tests for sameIdentityAs()
     */
    @Test
    void ensureThatTwoProjectResourcesHaveTheSameIdentity() {
        // Arrange
        ProjectResourceId resourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResourceOne = new ProjectResource(resourceIdDouble, codeDouble, emailDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);
        ProjectResource projectResourceTwo = new ProjectResource(resourceIdDouble, codeDouble, emailDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);

        // Act
        boolean result = projectResourceOne.sameIdentityAs(projectResourceTwo);

        // Assert
        assertTrue(result);
    }

    @Test
    void ensureThatTwoProjectResourcesDoNotHaveTheSameIdentityBecauseResourceIdsAreDifferent() {
        // Arrange
        ProjectResourceId resourceIdOneDouble = mock(ProjectResourceId.class);
        ProjectResourceId resourceIdTwoDouble = mock(ProjectResourceId.class);

        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResourceOne = new ProjectResource(resourceIdOneDouble, codeDouble, emailDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);
        ProjectResource projectResourceTwo = new ProjectResource(resourceIdTwoDouble, codeDouble, emailDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);

        // Act
        boolean result = projectResourceOne.sameIdentityAs(projectResourceTwo);

        // Assert
        assertFalse(result);
    }

    @Test
    void ensureThatTwoProjectResourcesDoNotHaveTheSameIdentityBecauseOneIsNull() {
        // Arrange
        ProjectResourceId resourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResourceOne = new ProjectResource(resourceIdDouble, codeDouble, emailDouble,
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
        ProjectResourceId resourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource expected = new ProjectResource(resourceIdDouble, codeDouble, emailDouble, roleDouble,
                periodDouble, costDouble, percentageOfAllocationDouble);
        ProjectResource result = new ProjectResource(resourceIdDouble, codeDouble, emailDouble, roleDouble,
                periodDouble, costDouble, percentageOfAllocationDouble);

        // Act, Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatTwoDifferentProjectResourcesAreNotEqual() {
        // Arrange
        ProjectResourceId resourceIdOneDouble = mock(ProjectResourceId.class);
        ProjectResourceId resourceIdTwoDouble = mock(ProjectResourceId.class);

        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource expected = new ProjectResource(resourceIdOneDouble, codeDouble, emailDouble, roleDouble,
                periodDouble, costDouble, percentageOfAllocationDouble);
        ProjectResource result = new ProjectResource(resourceIdTwoDouble, codeDouble, emailDouble, roleDouble,
                periodDouble, costDouble, percentageOfAllocationDouble);

        // Act, Assert
        assertNotEquals(expected, result);
    }

    @SuppressWarnings("all")
    @Test
    void ensureThatProjectResourceIsNotEqualToOtherTypeOfObject() {
        // Arrange
        String expected = "Hello World!";
        ProjectResourceId resourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource result = new ProjectResource(resourceIdDouble, codeDouble, emailDouble, roleDouble,
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
        ProjectResourceId resourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResourceOne = new ProjectResource(resourceIdDouble, codeDouble, emailDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);
        ProjectResource projectResourceTwo = new ProjectResource(resourceIdDouble, codeDouble, emailDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);

        // ACT
        int hashCodeReference = projectResourceOne.hashCode();
        int hashCodeOther = projectResourceTwo.hashCode();

        // ASSERT
        assertEquals(hashCodeOther, hashCodeReference);
    }

    @Test
    void ensureThatTwoProjectResourcesWithDifferentIdsHaveDifferentHashCodes() {
        // ARRANGE
        ProjectResourceId resourceIdOneDouble = mock(ProjectResourceId.class);
        ProjectResourceId resourceIdTwoDouble = mock(ProjectResourceId.class);

        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResourceOne = new ProjectResource(resourceIdOneDouble, codeDouble, emailDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);
        ProjectResource projectResourceTwo = new ProjectResource(resourceIdTwoDouble, codeDouble, emailDouble,
                roleDouble, periodDouble, costDouble, percentageOfAllocationDouble);

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
        ProjectResourceId resourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource resource = new ProjectResource(resourceIdDouble, codeDouble, emailDouble, roleDouble,
                periodDouble, costDouble,
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
        ProjectResourceId resourceIdDouble = mock(ProjectResourceId.class);

        Code codeOneDouble = mock(Code.class);
        Code codeTwoDouble = mock(Code.class);

        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource resource = new ProjectResource(resourceIdDouble, codeOneDouble, emailDouble, roleDouble,
                periodDouble,
                costDouble,
                percentageOfAllocationDouble);
        //Act
        boolean result = resource.hasProjectCode(codeTwoDouble);

        //Assert
        assertFalse(result);
    }

    /**
     * Method hasSameAllocationInfo(ProjectResource otherResource) checks if an instance of ProjectResource has the
     * same allocation info (projectCode, accountEmail, roleInProject, and timeInProject) as another ProjectResource
     * instance.
     *
     * Scenario 01: The instance of ProjectResource has the same allocation info as the ProjectResource instance that
     * is passed as argument.
     * It should assert true.
     */
    @Test
    void ensureItReturnsTrueIfTheAllocationInfoIsTheSame() {
        //Arrange
        ProjectResourceId resourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource resource = new ProjectResource(resourceIdDouble, codeDouble, emailDouble, roleDouble,
                periodDouble, costDouble, percentageOfAllocationDouble);

        ProjectResource otherResource = new ProjectResource(resourceIdDouble, codeDouble, emailDouble, roleDouble,
                periodDouble, costDouble, percentageOfAllocationDouble);
        //Act
        boolean result = resource.hasSameAllocationInfo(otherResource);

        //Assert
        assertTrue(result);
    }


    /**
     * Method hasSameAllocationInfo(ProjectResource otherResource) checks if an instance of ProjectResource has the
     * same allocation info (projectCode, accountEmail, roleInProject, and timeInProject) as another ProjectResource
     * instance.
     *
     * Scenario 02: The instance of ProjectResource has not the same allocation info as the ProjectResource instance
     * that is passed as argument because the projectCode is different.
     * It should assert true.
     */
    @Test
    void ensureItReturnsFalseIfTheAllocationInfoIsDifferentDueToDifferentProjectCodes() {
        //Arrange
        ProjectResourceId resourceIdDouble = mock(ProjectResourceId.class);

        Code codeOneDouble = mock(Code.class);
        Code codeTwoDouble = mock(Code.class);

        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource resource = new ProjectResource(resourceIdDouble, codeOneDouble, emailDouble, roleDouble,
                periodDouble, costDouble, percentageOfAllocationDouble);

        ProjectResource otherResource = new ProjectResource(resourceIdDouble, codeTwoDouble, emailDouble, roleDouble,
                periodDouble, costDouble, percentageOfAllocationDouble);
        //Act
        boolean result = resource.hasSameAllocationInfo(otherResource);

        //Assert
        assertFalse(result);
    }

    /**
     * Method hasSameAllocationInfo(ProjectResource otherResource) checks if an instance of ProjectResource has the
     * same allocation info (projectCode, accountEmail, roleInProject, and timeInProject) as another ProjectResource
     * instance.
     *
     * Scenario 03: The instance of ProjectResource has not the same allocation info as the ProjectResource instance
     * that is passed as argument because the accountEmail is different.
     * It should assert false.
     */
    @Test
    void ensureItReturnsFalseIfTheAllocationInfoIsDifferentDueToDifferentAccountEmails() {
        //Arrange
        ProjectResourceId resourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);

        Email emailOneDouble = mock(Email.class);
        Email emailTwoDouble = mock(Email.class);

        Role roleDouble = mock(Role.class);
        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource resource = new ProjectResource(resourceIdDouble, codeDouble, emailOneDouble, roleDouble,
                periodDouble, costDouble, percentageOfAllocationDouble);

        ProjectResource otherResource = new ProjectResource(resourceIdDouble, codeDouble, emailTwoDouble, roleDouble,
                periodDouble, costDouble, percentageOfAllocationDouble);
        //Act
        boolean result = resource.hasSameAllocationInfo(otherResource);

        //Assert
        assertFalse(result);
    }

    /**
     * Method hasSameAllocationInfo(ProjectResource otherResource) checks if an instance of ProjectResource has the
     * same allocation info (projectCode, accountEmail, roleInProject, and timeInProject) as another ProjectResource
     * instance.
     *
     * Scenario 03: The instance of ProjectResource has not the same allocation info as the ProjectResource instance
     * that is passed as argument because the roleInProject is different.
     * It should assert false.
     */
    @Test
    void ensureItReturnsFalseIfTheAllocationInfoIsDifferentDueToDifferentRoles() {
        //Arrange
        ProjectResourceId resourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);

        Role roleOneDouble = mock(Role.class);
        Role roleTwoDouble = mock(Role.class);

        Period periodDouble = mock(Period.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource resource = new ProjectResource(resourceIdDouble, codeDouble, emailDouble, roleOneDouble,
                periodDouble, costDouble, percentageOfAllocationDouble);

        ProjectResource otherResource = new ProjectResource(resourceIdDouble, codeDouble, emailDouble, roleTwoDouble,
                periodDouble, costDouble, percentageOfAllocationDouble);
        //Act
        boolean result = resource.hasSameAllocationInfo(otherResource);

        //Assert
        assertFalse(result);
    }

    /**
     * Method hasSameAllocationInfo(ProjectResource otherResource) checks if an instance of ProjectResource has the
     * same allocation info (projectCode, accountEmail, roleInProject, and timeInProject) as another ProjectResource
     * instance.
     *
     * Scenario 04: The instance of ProjectResource has not the same allocation info as the ProjectResource instance
     * that is passed as argument because the timeInProject is different.
     * It should assert true.
     */
    @Test
    void ensureItReturnsFalseIfTheAllocationInfoIsDifferentDueToDifferentPeriods() {
        //Arrange
        ProjectResourceId resourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);

        Period periodOneDouble = mock(Period.class);
        Period periodTwoDouble = mock(Period.class);

        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource resource = new ProjectResource(resourceIdDouble, codeDouble, emailDouble, roleDouble,
                periodOneDouble, costDouble, percentageOfAllocationDouble);

        ProjectResource otherResource = new ProjectResource(resourceIdDouble, codeDouble, emailDouble, roleDouble,
                periodTwoDouble, costDouble, percentageOfAllocationDouble);
        //Act
        boolean result = resource.hasSameAllocationInfo(otherResource);

        //Assert
        assertFalse(result);
    }
}



