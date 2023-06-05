package org.switch2022.project.ddd.datamodel_jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

        //Act
    SprintJpa sprintJpa = new SprintJpa(sprintId,sprintNumber,projectCode,startDate,endDate);
    //Assert
        assertEquals(sprintId, sprintJpa.getSprintId());
        assertEquals(sprintNumber, sprintJpa.getSprintNumber());
        assertEquals(projectCode, sprintJpa.getProjectCode());
        assertEquals(startDate, sprintJpa.getStartDate());
        assertEquals(endDate, sprintJpa.getEndDate());
        assertEquals(userStories, sprintJpa.getUserStoriesInSprint());
    }

    @DisplayName("Equals and HashCode Testing")
    @Test
    void testEqualsAndHashCodeMethods() {
        //Arrange
        // Create two instances with the same values
        SprintJpa sprintJpa1 = new SprintJpa("s001", "1", "p003", "2022-01-01", "2022-03-01");
        SprintJpa sprintJpa2 = new SprintJpa("s001", "1", "p003", "2022-01-01", "2022-03-01");
        //Act
        assertTrue(sprintJpa1.equals(sprintJpa2));
        assertTrue(sprintJpa2.equals(sprintJpa1));

        //Assert
        assertEquals(sprintJpa1.hashCode(), sprintJpa2.hashCode());
    }

    @DisplayName("ToString Testing")
    @Test
    void testToStringMethod() {
        // Create an instance with specific values
        SprintJpa sprintJpa = new SprintJpa("s001", "1", "p003", "2022-01-01", "2022-03-01");

        // Test individual field values
        assertEquals("SprintJpa(sprintId=s001, sprintNumber=1, projectCode=p003, startDate=2022-01-01, endDate=2022-03-01, userStoriesInSprint=[])", sprintJpa.toString());
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
    }
}