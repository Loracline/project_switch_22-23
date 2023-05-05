package org.switch2022.project.ddd.domain.model.project_resource;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProjectResourceTest {

    /**
     * Testing if Project Resource ID is created when is above zero.
     */
    @Test
    void ensureThatProjectResourceIsCreatedSuccessfully() {
        // Arrange
        ProjectResourceId projectResourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);

        // Act, Assert
        assertDoesNotThrow(() -> new ProjectResource(projectResourceIdDouble, codeDouble, emailDouble));
    }

    /**
     * Testing if Project Resource is not created when Project Resource ID Is Null.
     */
    @Test
    void ensureThatProjectResourceIsNotCreatedSuccessfully_ProjectResourceIdIsNull() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new ProjectResource(null, codeDouble, emailDouble));
    }

    /**
     * Testing if Project Resource is not created when Code Is Null.
     */
    @Test
    void ensureThatProjectResourceIsNotCreatedSuccessfully_CodeIsNull() {
        // Arrange
        ProjectResourceId projectResourceIdDouble = mock(ProjectResourceId.class);
        Email emailDouble = mock(Email.class);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new ProjectResource(projectResourceIdDouble, null, emailDouble));
    }

    /**
     * Testing if Project Resource is not created when Email Is Null.
     */
    @Test
    void ensureThatProjectResourceIsNotCreatedSuccessfully_EmailIsNull() {
        // Arrange
        ProjectResourceId projectResourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new ProjectResource(projectResourceIdDouble, codeDouble, null));
    }

    /**
     * Testing if Role is not set when is Null.
     */
    @Test
    void ensureThatRoleIsNotSetWhenIsNull() {
        // Arrange
        ProjectResourceId projectResourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        ProjectResource projectResource =
                new ProjectResource(projectResourceIdDouble, codeDouble, emailDouble);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> projectResource.setRole(null));
    }

    /**
     * Testing if Role is set when is not Null.
     */
    @Test
    void ensureThatRoleIsSetWhenIsNotNull() {
        // Arrange
        ProjectResourceId projectResourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Role roleDouble = mock(Role.class);
        ProjectResource projectResource =
                new ProjectResource(projectResourceIdDouble, codeDouble, emailDouble);

        // Act, Assert
        assertDoesNotThrow(() -> projectResource.setRole(roleDouble));
    }

    /**
     * Testing if Period is not set when is Null.
     */
    @Test
    void ensureThatPeriodIsNotSetWhenIsNull() {
        // Arrange
        ProjectResourceId projectResourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        ProjectResource projectResource =
                new ProjectResource(projectResourceIdDouble, codeDouble, emailDouble);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> projectResource.setPeriod(null));
    }

    /**
     * Testing if Period is set when is not Null.
     */
    @Test
    void ensureThatPeriodIsSetWhenIsNotNull() {
        // Arrange
        ProjectResourceId projectResourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Period periodDouble = mock(Period.class);
        ProjectResource projectResource =
                new ProjectResource(projectResourceIdDouble, codeDouble, emailDouble);

        // Act, Assert
        assertDoesNotThrow(() -> projectResource.setPeriod(periodDouble));
    }

    /**
     * Testing if Cost Per Hour is not set when is Null.
     */
    @Test
    void ensureThatCostIsNotSetWhenIsNull() {
        // Arrange
        ProjectResourceId projectResourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        ProjectResource projectResource =
                new ProjectResource(projectResourceIdDouble, codeDouble, emailDouble);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> projectResource.setCost(null));
    }

    /**
     * Testing if Cost Per Hour is set when is not Null.
     */
    @Test
    void ensureThatCostIsSetWhenIsNotNull() {
        // Arrange
        ProjectResourceId projectResourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        ProjectResource projectResource =
                new ProjectResource(projectResourceIdDouble, codeDouble, emailDouble);

        // Act, Assert
        assertDoesNotThrow(() -> projectResource.setCost(costDouble));
    }

    /**
     * Testing if Percentage Of Allocation is not set when is Null.
     */
    @Test
    void ensureThatPercentageOfAllocationIsNotSetWhenIsNull() {
        // Arrange
        ProjectResourceId projectResourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        ProjectResource projectResource =
                new ProjectResource(projectResourceIdDouble, codeDouble, emailDouble);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> projectResource.setPercentageOfAllocation(null));
    }

    /**
     * Testing if Percentage Of Allocation is set when is not Null.
     */
    @Test
    void ensureThatPercentageOfAllocationIsSetWhenIsNotNull() {
        // Arrange
        ProjectResourceId projectResourceIdDouble = mock(ProjectResourceId.class);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);
        ProjectResource projectResource =
                new ProjectResource(projectResourceIdDouble, codeDouble, emailDouble);

        // Act, Assert
        assertDoesNotThrow(() -> projectResource.setPercentageOfAllocation(percentageOfAllocationDouble));
    }

    /**
     * Tests for sameIdentityAs()
     */
    @Test
    void ensureThatTwoProjectResourceHaveTheSameIdentity() {
        // Arrange
        ProjectResourceId projectResourceIdOneDouble = new ProjectResourceId(1);
        ProjectResourceId projectResourceIdTwoDouble = new ProjectResourceId(1);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        ProjectResource projectResourceOne = new ProjectResource(projectResourceIdOneDouble, codeDouble, emailDouble);
        ProjectResource projectResourceTwo = new ProjectResource(projectResourceIdTwoDouble, codeDouble, emailDouble);

        // Act
        boolean result = projectResourceOne.sameIdentityAs(projectResourceTwo);

        // Assert
        assertTrue(result);
    }

    @Test
    void ensureThatTwoProjectResourceDoesNotHaveTheSameIdentity() {
        // Arrange
        ProjectResourceId projectResourceIdOne = new ProjectResourceId(1);
        ProjectResourceId projectResourceIdTwo = new ProjectResourceId(2);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        ProjectResource projectResourceOne = new ProjectResource(projectResourceIdOne, codeDouble, emailDouble);
        ProjectResource projectResourceTwo = new ProjectResource(projectResourceIdTwo, codeDouble, emailDouble);

        // Act
        boolean result = projectResourceOne.sameIdentityAs(projectResourceTwo);

        // Assert
        assertFalse(result);
    }

    @Test
    void ensureThatTwoProjectResourceDoesNotHaveTheSameIdentityBecauseOneIsNull() {
        // Arrange
        ProjectResourceId projectResourceIdOne = new ProjectResourceId(1);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        ProjectResource projectResourceOne = new ProjectResource(projectResourceIdOne, codeDouble, emailDouble);

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> projectResourceOne.sameIdentityAs(null));
    }

    /**
     * Tests for the equals() method.
     */
    @Test
    void ensureThatProjectResourceEqualsItself() {
        // Arrange
        ProjectResourceId projectResourceIdOne = new ProjectResourceId(1);
        ProjectResourceId projectResourceIdTwo = new ProjectResourceId(1);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        ProjectResource expected = new ProjectResource(projectResourceIdOne, codeDouble, emailDouble);
        ProjectResource result = new ProjectResource(projectResourceIdTwo, codeDouble, emailDouble);

        // Act, Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatProjectResourceAreNotEquals() {
        // Arrange
        ProjectResourceId projectResourceIdOne = new ProjectResourceId(1);
        ProjectResourceId projectResourceIdTwo = new ProjectResourceId(2);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        ProjectResource expected = new ProjectResource(projectResourceIdOne, codeDouble, emailDouble);
        ProjectResource result = new ProjectResource(projectResourceIdTwo, codeDouble, emailDouble);

        // Act, Assert
        assertNotEquals(expected, result);
    }

    @SuppressWarnings("all")
    @Test
    void ensureThatProjectResourceIsNotEqualOtherTypeOfObject() {
        // Arrange
        String expected = "Hello World!";
        ProjectResourceId projectResourceIdOne = new ProjectResourceId(1);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        ProjectResource result = new ProjectResource(projectResourceIdOne, codeDouble, emailDouble);

        // Act, Assert
        assertNotEquals(expected, result);
    }

    /**
     * Tests for hashCode()
     */
    @Test
    void ensureThatTwoProjectResourceHaveSameHashCode() {
        // ARRANGE
        ProjectResourceId projectResourceIdOne = new ProjectResourceId(1);
        ProjectResourceId projectResourceIdTwo = new ProjectResourceId(1);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        ProjectResource projectResourceOne = new ProjectResource(projectResourceIdOne, codeDouble, emailDouble);
        ProjectResource projectResourceTwo = new ProjectResource(projectResourceIdTwo, codeDouble, emailDouble);

        // ACT
        int hashCodeReference = projectResourceOne.hashCode();
        int hashCodeOther = projectResourceTwo.hashCode();

        // ASSERT
        assertEquals(hashCodeOther, hashCodeReference);
    }

    @Test
    void ensureThatTwoProjectResourceHaveDifferentHashCode() {
        // ARRANGE
        ProjectResourceId projectResourceIdOne = new ProjectResourceId(1);
        ProjectResourceId projectResourceIdTwo = new ProjectResourceId(2);
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        ProjectResource projectResourceOne = new ProjectResource(projectResourceIdOne, codeDouble, emailDouble);
        ProjectResource projectResourceTwo = new ProjectResource(projectResourceIdTwo, codeDouble, emailDouble);

        // ACT
        int hashCodeReference = projectResourceOne.hashCode();
        int hashCodeOther = projectResourceTwo.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther, hashCodeReference);
    }
}



