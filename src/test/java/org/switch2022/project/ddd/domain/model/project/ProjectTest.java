package org.switch2022.project.ddd.domain.model.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {
    /**
     * BeforeEach and AfterEach execute common code before/after running the
     * tests below.
     */
    Project projectOne, projectTwo, projectThree;

    @BeforeEach
    void setUp() {
        projectOne = new org.switch2022.project.ddd.domain.model.project.Project("P001");
        projectTwo = new org.switch2022.project.ddd.domain.model.project.Project("P002");
        projectThree = new org.switch2022.project.ddd.domain.model.project.Project("P003");
    }

    @AfterEach
    void tearDown() {
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
        org.switch2022.project.ddd.domain.model.project.Project projectReference = projectOne;
        boolean expected = true;
        boolean result = projectOne.equals(projectReference);
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
        boolean result = projectOne.equals(projectObject);
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
        boolean result = projectOne.equals(projectTwo);
        assertEquals(expected, result);
    }

    /**
     * Scenario 01:Two objects with the same code but different hash codes are two different objects
     */
    @Test
    void ensureNoTwoProjectsHaveTheSameHashCode() {
        //Arrange
        Project projectOne = new Project("P001");
        Project projectTwo = new Project("P002");

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

        Project project = new Project("P001");

        // Assert
        assertNotNull(project);
    }

    /**
     * Scenario 02: Verifies if the method equals() returns
     * true, when compared to the same object.
     */
    @Test
    public void shouldReturnTrueEqualsWithSameObjectWithIsolation() {

        // Act
        Project project = new Project("P001");

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
        Project projectDouble = new Project("P002");
        Project projectDoubleToCompare = new Project("P002");

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
        Project projectDouble = new Project("P001");
        Project projectDoubleToCompare = new Project("P002");

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
        Project projectDouble = new Project("P001");
        Object projectToCompare = new Object();

        // Act
        boolean isEquals = projectDouble.equals(projectToCompare);

        // Assert
        assertFalse(isEquals);
    }
}