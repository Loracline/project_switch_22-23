package org.switch2022.project.ddd.datamodel_jpa;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.project.Project;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProjectJpaTest {

    @Test
    void ensureProjectJpaIsCreated() {
        //Act
        ProjectJpa projectJpa = new ProjectJpa();

        //Assert
        assertNotNull(projectJpa);
    }


    @Test
    void getProjectCode() {

        //Arrange
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa projectJpa = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        String expected = "p001";

        //Act
        String result = projectJpa.getProjectCode();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getBudget() {

        //Arrange
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa projectJpa = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        double expected = 175000.0;

        //Act
        double result = projectJpa.getBudget();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getProjectName() {
        //Arrange
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa projectJpa = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        String expected = "dummy1";

        //Act
        String result = projectJpa.getProjectName();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getDescription() {
        //Arrange
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa projectJpa = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        String expected = "A dummy Project";

        //Act
        String result = projectJpa.getDescription();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getProjectStatus() {
        //Arrange
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa projectJpa = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        String expected = "inception";

        //Act
        String result = projectJpa.getProjectStatus();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getNumberOfPlannedSprints() {
        //Arrange
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa projectJpa = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        int expected = 13;

        //Act
        int result = projectJpa.getNumberOfPlannedSprints();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getStartDate() {
        //Arrange
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa projectJpa = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        String expected = "2021-10-03";

        //Act
        String result = projectJpa.getStartDate();

        //Assert
        assertEquals(expected, result);
    }


    @Test
    void getEndDate() {
        //Arrange
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa projectJpa = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        String expected = "2023-10-03";

        //Act
        String result = projectJpa.getEndDate();

        //Assert
        assertEquals(expected, result);

    }

    @Test
    void getSprintDuration() {
        //Arrange
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa projectJpa = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        int expected = 3;

        //Act
        int result = projectJpa.getSprintDuration();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getBusinessSectorId() {
        //Arrange
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa projectJpa = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        String expected = "bs001";

        //Act
        String result = projectJpa.getBusinessSectorId();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getCustomerTaxId() {
        //Arrange
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa projectJpa = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        String expected = "262727262";

        //Act
        String result = projectJpa.getCustomerTaxId();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getProjectTypologyId() {
        //Arrange
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa projectJpa = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        String expected = "pt001";

        //Act
        String result = projectJpa.getProjectTypologyId();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getProductBacklog() {
        //Arrange
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa projectJpa = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        ProductBacklogJpa expected =new ProductBacklogJpa("p001_pb", new ArrayList<>());

        //Act
        ProductBacklogJpa result = projectJpa.getProductBacklog();

        //Assert
        assertEquals(expected, result);
    }
    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify that the same object equals itself.
     */
    @Test
    void ensureSameProjectEqualsItself() {
        // Arrange
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa projectJpa = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        ProjectJpa other = projectJpa;
        boolean expected = true;

        // Act
        boolean result = projectJpa.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two objects with the same attributes are equal.
     */
    @Test
    void ensureTwoInstancesWithSameIdAreEqual() {
        // Arrange
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa projectJpa = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        ProductBacklogJpa productBacklogJpaO = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa other = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpaO);
        boolean expected = true;

        // Act
        boolean result = projectJpa.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Verify that two objects with different ids are not equal.
     */
    @Test
    void ensureTwoInstancesWithDifferentIdsAreNotEqual() {
        // Arrange
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa projectJpa = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        ProductBacklogJpa productBacklogJpaO = new ProductBacklogJpa("p002_pb", new ArrayList<>());
        ProjectJpa other = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpaO);
        boolean expected = false;

        // Act
        boolean result = projectJpa.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: Verify that the object does not equal null.
     */
    @Test
    void ensureObjectDoesNotEqualNull() {
        // Arrange
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa projectJpa = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        ProjectJpa other = null;
        boolean expected = false;

        // Act
        boolean result = projectJpa.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 5: Verify that the object project does not equal other type of object.
     */
    @Test
    void ensureProjectDoesNotEqualOtherTypeOfObject() {
        // Arrange
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa projectJpa = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        Project other = mock(Project.class);
        boolean expected = false;

        // Act
        boolean result = projectJpa.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Verify that two equal BusinessSector objects have the same hashcode.
     */
    @Test
    void ensureTwoEqualProjectInstancesHaveTheSameHashcode() {
        // Arrange
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa reference = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        ProjectJpa other = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);

        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two different projects objects have different hashcode.
     */
    @Test
    void ensureTwoDifferentProjectInstancesHaveDifferentHashcode() {
        // Arrange

        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb", new ArrayList<>());
        ProjectJpa reference = new ProjectJpa("p001", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        ProjectJpa other = new ProjectJpa("p002", 175000.0, "dummy1",
                "A dummy Project", "inception", 13,
                "2021-10-03", "2023-10-03", 3, "bs001",
                "262727262", "pt001", productBacklogJpa);
        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertNotEquals(expected, result);
    }

}