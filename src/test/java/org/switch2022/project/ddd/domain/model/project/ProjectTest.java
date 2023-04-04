package org.switch2022.project.ddd.domain.model.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.Code;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProjectTest {
    /**
     * BeforeEach and AfterEach execute common code before/after running the
     * tests below.
     */

    Code projectCodeOne, projectCodeTwo, projectCodeThree;
    Project projectOne, projectTwo, projectThree;

    @BeforeEach
    void setUp() {

        //Code
        projectCodeOne = new Code("P001");
        projectCodeTwo = new Code("P002");
        projectCodeThree = new Code("P003");

        //Project
        projectOne = new Project(projectCodeOne);
        projectTwo = new Project(projectCodeTwo);
        projectThree = new Project(projectCodeThree);
    }

    @AfterEach
    void tearDown() {
        projectCodeOne = null;
        projectCodeTwo = null;
        projectCodeThree = null;
        projectOne = null;
        projectTwo = null;
        projectThree = null;
    }

    /**
     * Scenario 01: Test to ensure the object equals itself
     */
    @Test
    void ensureSameObjectEqualsItself() {
        // Arrange
        Project projectReference = projectOne;
        boolean expected = true;

        //Act
        boolean result = projectOne.equals(projectReference);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 02:Test to ensure that two objects are from different classes
     */
    @Test
    void ensureObjectsAreFromDifferentClasses() {
        // Arrange
        Object projectObject = new Object();
        boolean expected = false;

        //Act
        boolean result = projectOne.equals(projectObject);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 03: Test to ensure that the object to compare is equal to null
     */
    @Test
    void ensureObjectToCompareIsNull() {
        //Arrange
        Project other = null;
        boolean expected = false;

        //Act
        boolean result = projectOne.equals(other);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 04: Test to ensure that two objects from the same class are different
     */
    @Test
    void ensureTwoProjectsAreNotEqual() {
        // Arrange
        boolean expected = false;
        //Act
        boolean result = projectOne.equals(projectTwo);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 01:Two objects with the different code and different hash codes are two different objects
     */
    @Test
    void ensureNoTwoProjectsHaveTheSameHashCode() {
        //Arrange
        Code projectCodeOne = mock(Code.class);
        Code projectCodeTwo = mock(Code.class);
        Project projectOne = new Project(projectCodeOne);
        Project projectTwo = new Project(projectCodeTwo);

        //Assert
        assertNotEquals(projectOne.hashCode(), projectTwo.hashCode());
    }

    //ISOLATION TESTS

    /**
     * Scenario 01: Verifies if it's possible to create a valid instance of
     * Project with
     * isolated objects.
     */
    @Test
    public void shouldCreateAValidProjectWithIsolation() {
        //Arrange
        Code projectCodeOne = mock(Code.class);
        Project project = new Project(projectCodeOne);

        // Assert
        assertNotNull(project);
    }

    /**
     * Scenario 02: Verifies if the method equals() returns
     * true, when compared to the same object.
     */
    @Test
    public void shouldReturnTrueEqualsWithSameObjectWithIsolation() {
        //Arrange
        Code projectCodeOne = mock(Code.class);
        Project project = new Project(projectCodeOne);

        // Act
        boolean isEquals = project.equals(project);

        // Assert
        assertTrue(isEquals);
    }

    /**
     * Scenario 03: Verifies if the method equals() returns
     * true, when compared to the
     * same type of object and with the same parameters.
     */
    @Test
    public void shouldReturnTrueEqualsProjectsWithSameParameters() {

        // Arrange
        Code projectCodeOne = mock(Code.class);
        Project projectDouble = new Project(projectCodeOne);
        Project projectDoubleToCompare = new Project(projectCodeOne);

        //Act
        boolean isEquals = projectDouble.equals(projectDoubleToCompare);

        // Assert
        assertTrue(isEquals);
    }

    /**
     * Scenario 04: Verifies if the method equals() returns
     * false, when compared to the
     * same type of object and with the different parameters.
     */
    @Test
    public void shouldReturnFalseEqualsProjectsNotWithSameParameters() {
        // Arrange
        Code projectCodeOne = mock(Code.class);
        Code projectCodeTwo = mock(Code.class);
        Project projectDouble = new Project(projectCodeOne);
        Project projectDoubleToCompare = new Project(projectCodeTwo);

        // Act
        boolean isEquals = projectDouble.equals(projectDoubleToCompare);

        // Assert
        assertFalse(isEquals);
    }

    /**
     * Scenario 05: Verifies if the method equals() returns
     * false, when compared to the
     * different type of object.
     */
    @Test
    public void shouldReturnFalseWithDifferentClasses() {
        // Arrange
        Code projectCode = mock(Code.class);
        Project projectDouble = new Project(projectCode);
        Object projectToCompare = new Object();

        // Act
        boolean isEquals = projectDouble.equals(projectToCompare);

        // Assert
        assertFalse(isEquals);
    }
}