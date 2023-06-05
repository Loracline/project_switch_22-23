package org.switch2022.project.ddd.datamodel_jpa;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}