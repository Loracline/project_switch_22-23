package org.switch2022.project.ddd.datamodel_jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SprintJpaTest {

    @DisplayName("SprintJpa is created successfully")
    @Test
    void ensureSprintJpaIsCreatedSuccessfully () {
    // Arrange
    String sprintId = "s001";
    String sprintNumber = "1";
    String projectCode = "p003";
    String startDate = "2022-01-01";
    String endDate = "2022-03-01";
    List<UserStoryInSprintJpa> userStories = new ArrayList<>();
    String status = "Planned";
    //Act
    SprintJpa sprintJpa = new SprintJpa(sprintId,sprintNumber,projectCode,startDate,endDate, status);
    //Assert
        assertEquals(sprintId, sprintJpa.getSprintId());
        assertEquals(sprintNumber, sprintJpa.getSprintNumber());
        assertEquals(projectCode, sprintJpa.getProjectCode());
        assertEquals(startDate, sprintJpa.getStartDate());
        assertEquals(endDate, sprintJpa.getEndDate());
        assertEquals(userStories, sprintJpa.getUserStoriesInSprint());
        assertEquals(status, sprintJpa.getStatus());
    }

    @DisplayName("Equals and HashCode Testing")
    @Test
    void testEqualsAndHashCodeMethods() {
        // Arrange
        SprintJpa sprint1 = new SprintJpa("s001", "1", "p003", "2022-01-01", "2022-03-01","open");
        SprintJpa sprint2 = new SprintJpa("s001", "1", "p003", "2022-01-01", "2022-03-01","open");
        SprintJpa sprint3 = new SprintJpa("s002", "1", "p003", "2022-01-01", "2022-03-01","open");
        SprintJpa sprint4 = new SprintJpa("s001", "2", "p003", "2022-01-01", "2022-03-01","open");
        SprintJpa sprint5 = new SprintJpa("s001", "1", "p004", "2022-01-01", "2022-03-01","open");
        SprintJpa sprint6 = new SprintJpa("s001", "1", "p003", "2022-01-01", "2022-03-01","open");
        sprint6.setUserStoriesInSprint(Collections.singletonList(mock(UserStoryInSprintJpa.class)));
        SprintJpa sprint7 = new SprintJpa("s001", "1", "p003", "2022-01-01", "2022-03-01","open");
        sprint7.setUserStoriesInSprint(Collections.singletonList(mock(UserStoryInSprintJpa.class)));

        // Act & Assert
        assertEquals(sprint1, sprint2);
        assertEquals(sprint2, sprint1);
        assertEquals(sprint1.hashCode(), sprint2.hashCode());

        assertNotEquals(sprint1, sprint3);
        assertNotEquals(sprint1, sprint4);
        assertNotEquals(sprint1, sprint5);
        assertNotEquals(null, sprint1);
        assertNotEquals(sprint1, new Object());
        assertNotEquals(sprint1, sprint6);
        assertNotEquals(sprint6, sprint1);
        assertNotEquals(sprint1, sprint7);
    }
    @Test
    public void equals_WithSameObject_ShouldReturnTrue() {
        // Arrange
        SprintJpa sprint = new SprintJpa("sprintId", "sprintNumber", "projectCode", "startDate", "endDate","open");

        // Act & Assert
        assertEquals(sprint, sprint);
    }

    @Test
    public void equals_WithNullObject_ShouldReturnFalse() {
        // Arrange
        SprintJpa sprint = new SprintJpa("sprintId", "sprintNumber", "projectCode", "startDate", "endDate","open");

        // Act & Assert
        assertNotEquals(null, sprint);
    }

    @Test
    public void equals_WithDifferentClass_ShouldReturnFalse() {
        // Arrange
        SprintJpa sprint = new SprintJpa("sprintId", "sprintNumber", "projectCode", "startDate", "endDate","open");
        String otherObject = "Not a SprintJpa object";

        // Act & Assert
        assertNotEquals(sprint, otherObject);
    }

    @Test
    public void equals_WithSameAttributes_ShouldReturnTrue() {
        // Arrange
        SprintJpa sprint1 = new SprintJpa("sprintId", "sprintNumber", "projectCode", "startDate", "endDate","open");
        SprintJpa sprint2 = new SprintJpa("sprintId", "sprintNumber", "projectCode", "startDate", "endDate","open");

        // Act & Assert
        assertEquals(sprint1, sprint2);
        assertEquals(sprint2, sprint1);
    }

    @Test
    public void equals_WithDifferentAttributes_ShouldReturnFalse() {
        // Arrange
        SprintJpa sprint1 = new SprintJpa("sprintId1", "sprintNumber", "projectCode", "startDate", "endDate","open");
        SprintJpa sprint2 = new SprintJpa("sprintId2", "sprintNumber", "projectCode", "startDate", "endDate","open");

        // Act & Assert
        assertNotEquals(sprint1, sprint2);
        assertNotEquals(sprint2, sprint1);
    }

    @Test
    public void equals_WithNullAttributes_ShouldReturnFalse() {
        // Arrange
        SprintJpa sprint1 = new SprintJpa(null, null, null, null, null, null);
        SprintJpa sprint2 = new SprintJpa(null, null, null, null, null, null);

        // Act & Assert
        assertEquals(sprint1, sprint2);
        assertEquals(sprint2, sprint1);
    }

    @Test
    public void equals_WithMixedAttributes_ShouldReturnFalse() {
        // Arrange
        SprintJpa sprint1 = new SprintJpa("sprintId", "sprintNumber1", "projectCode", "startDate", "endDate","open");
        SprintJpa sprint2 = new SprintJpa("sprintId", "sprintNumber2", "projectCode", "startDate", "endDate","open");

        // Act & Assert
        assertNotEquals(sprint1, sprint2);
        assertNotEquals(sprint2, sprint1);
    }
    @Test
    public void equals_WithSameAttributesInDifferentOrder_ShouldReturnTrue() {
        // Arrange
        SprintJpa sprint1 = new SprintJpa("sprintId", "sprintNumber", "projectCode", "startDate", "endDate","open");
        SprintJpa sprint2 = new SprintJpa("sprintId", "sprintNumber", "projectCode", "endDate", "startDate","open");

        // Act & Assert
        assertEquals(sprint1.getSprintId(), sprint2.getSprintId());
        assertEquals(sprint1.getSprintNumber(), sprint2.getSprintNumber());
        assertEquals(sprint1.getProjectCode(), sprint2.getProjectCode());
        assertNotEquals(sprint1.getStartDate(), sprint2.getStartDate());
        assertNotEquals(sprint1.getEndDate(), sprint2.getEndDate());
    }
    @DisplayName("Equals Method Testing")
    @Test
    void testEqualsMethod() {
        // Create SprintJpa instances
        SprintJpa sprint1 = new SprintJpa("S1", "1", "P1", "2023-06-01", "2023-06-10","open");
        SprintJpa sprint2 = new SprintJpa("S1", "1", "P1", "2023-06-01", "2023-06-10","open");

        // Test reflexivity
        assertEquals(sprint1, sprint1);

        // Test null comparison
        assertNotEquals(null, sprint1);

        // Test different class comparison
        assertNotEquals("not a SprintJpa object", sprint1);

        // Test equality with same attributes
        assertEquals(sprint1, sprint2);
        assertEquals(sprint2, sprint1);

        // Test inequality with different sprintId
        SprintJpa sprint4 = new SprintJpa("S3", "1", "P1", "2023-06-01", "2023-06-10","open");
        assertNotEquals(sprint1, sprint4);
        assertNotEquals(sprint4, sprint1);

        // Test inequality with different sprintNumber
        SprintJpa sprint5 = new SprintJpa("S1", "2", "P1", "2023-06-01", "2023-06-10","open");
        assertNotEquals(sprint1, sprint5);
        assertNotEquals(sprint5, sprint1);

        // Test inequality with different projectCode
        SprintJpa sprint6 = new SprintJpa("S1", "1", "P2", "2023-06-01", "2023-06-10","open");
        assertNotEquals(sprint1, sprint6);
        assertNotEquals(sprint6, sprint1);

        // Test inequality with different startDate
        SprintJpa sprint7 = new SprintJpa("S1", "1", "P1", "2023-06-02", "2023-06-10","open");
        assertNotEquals(sprint1, sprint7);
        assertNotEquals(sprint7, sprint1);

        // Test inequality with different endDate
        SprintJpa sprint8 = new SprintJpa("S1", "1", "P1", "2023-06-01", "2023-06-11","open");
        assertNotEquals(sprint1, sprint8);
        assertNotEquals(sprint8, sprint1);

        // Test inequality with different status
        SprintJpa sprint9 = new SprintJpa("S1", "1", "P1", "2023-06-01", "2023-06-11","closed");
        assertNotEquals(sprint1, sprint9);
        assertNotEquals(sprint9, sprint1);
    }
    @Test
    public void hashCode_WithSameAttributes_ShouldReturnEqualHashCodes() {
        // Arrange
        SprintJpa sprint1 = new SprintJpa("sprintId", "sprintNumber", "projectCode", "startDate", "endDate","open");
        SprintJpa sprint2 = new SprintJpa("sprintId", "sprintNumber", "projectCode", "startDate", "endDate","open");

        // Act
        int hashCode1 = sprint1.hashCode();
        int hashCode2 = sprint2.hashCode();

        // Assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void hashCode_WithDifferentAttributes_ShouldReturnDifferentHashCodes() {
        // Arrange
        SprintJpa sprint1 = new SprintJpa("sprintId1", "sprintNumber", "projectCode", "startDate", "endDate","open");
        SprintJpa sprint2 = new SprintJpa("sprintId2", "sprintNumber", "projectCode", "startDate", "endDate","open");

        // Act
        int hashCode1 = sprint1.hashCode();
        int hashCode2 = sprint2.hashCode();

        // Assert
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    public void hashCode_WithNullAttributes_ShouldReturnSameHashCode() {
        // Arrange
        SprintJpa sprint1 = new SprintJpa(null, null, null, null, null,null);
        SprintJpa sprint2 = new SprintJpa(null, null, null, null, null,null);

        // Act
        int hashCode1 = sprint1.hashCode();
        int hashCode2 = sprint2.hashCode();

        // Assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void hashCode_WithMixedAttributes_ShouldReturnDifferentHashCodes() {
        // Arrange
        SprintJpa sprint1 = new SprintJpa("sprintId", "sprintNumber1", "projectCode", "startDate", "endDate","open");
        SprintJpa sprint2 = new SprintJpa("sprintId", "sprintNumber2", "projectCode", "startDate", "endDate","open");

        // Act
        int hashCode1 = sprint1.hashCode();
        int hashCode2 = sprint2.hashCode();

        // Assert
        assertNotEquals(hashCode1, hashCode2);
    }


    @DisplayName("ToString Testing")
    @Test
    void testToStringMethod() {
        // Create an instance with specific values
        SprintJpa sprintJpa = new SprintJpa("s001", "1", "p003", "2022-01-01", "2022-03-01","open");

        // Test individual field values
        assertEquals("SprintJpa(sprintId=s001, sprintNumber=1, projectCode=p003, startDate=2022-01-01, " +
                "endDate=2022-03-01, userStoriesInSprint=[], status=open)", sprintJpa.toString());
    }

    @DisplayName("Empty Constructor Testing")
    @Test
    void testEmptyConstructor() {
        // Create an instance using the empty constructor
        SprintJpa sprint = new SprintJpa();

        // Verify that the attributes are initialized with default values
        assertNull(sprint.getSprintId());
        assertNull(sprint.getSprintNumber());
        assertNull(sprint.getProjectCode());
        assertNull(sprint.getStartDate());
        assertNull(sprint.getEndDate());
        assertNull(sprint.getUserStoriesInSprint());
        assertNull(sprint.getStatus());
    }
    @DisplayName("Invalid Input Values")
    @Test
    void testInvalidInputValues() {
        // Arrange and Act
        SprintJpa sprintJpa = new SprintJpa(null, null, "p003", "2022-01-01", "2022-03-01","open");

        // Assert
        assertNull(sprintJpa.getSprintId());
        assertNull(sprintJpa.getSprintNumber());
        assertEquals("p003", sprintJpa.getProjectCode());
        assertEquals("2022-01-01", sprintJpa.getStartDate());
        assertEquals("2022-03-01", sprintJpa.getEndDate());
        assertEquals(0, sprintJpa.getUserStoriesInSprint().size());
        assertEquals("open", sprintJpa.getStatus());
    }

    @DisplayName("Date Format Parsing")
    @Test
    void testDateFormatParsing() {
        // Arrange
        String startDate = "01/01/2022";
        String endDate = "03/01/2022";

        // Act
        SprintJpa sprintJpa = new SprintJpa("s001", "1", "p003", startDate, endDate, "open");

        // Assert
        assertEquals("s001", sprintJpa.getSprintId());
        assertEquals("1", sprintJpa.getSprintNumber());
        assertEquals("p003", sprintJpa.getProjectCode());
        assertEquals("01/01/2022", sprintJpa.getStartDate());
        assertEquals("03/01/2022", sprintJpa.getEndDate());
        assertEquals(0, sprintJpa.getUserStoriesInSprint().size());
        assertEquals("open",sprintJpa.getStatus());
    }
    @DisplayName("Setters Testing")
    @Test
    void testSetters() {
        // Arrange
        SprintJpa sprintJpa = new SprintJpa();

        // Act
        sprintJpa.setSprintId("s001");
        sprintJpa.setSprintNumber("1");
        sprintJpa.setProjectCode("p003");
        sprintJpa.setStartDate("2022-01-01");
        sprintJpa.setEndDate("2022-03-01");
        List<UserStoryInSprintJpa> userStories = new ArrayList<>();
        sprintJpa.setUserStoriesInSprint(userStories);
        sprintJpa.setStatus("open");

        // Assert
        assertEquals("s001", sprintJpa.getSprintId());
        assertEquals("1", sprintJpa.getSprintNumber());
        assertEquals("p003", sprintJpa.getProjectCode());
        assertEquals("2022-01-01", sprintJpa.getStartDate());
        assertEquals("2022-03-01", sprintJpa.getEndDate());
        assertEquals(userStories, sprintJpa.getUserStoriesInSprint());
        assertEquals("open",sprintJpa.getStatus());
    }
}