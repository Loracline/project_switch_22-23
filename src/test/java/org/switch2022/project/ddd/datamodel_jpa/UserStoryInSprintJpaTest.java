package org.switch2022.project.ddd.datamodel_jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserStoryInSprintJpaTest {
    @DisplayName("UserStoryInSprintJpa is created successfully")
    @Test
    void ensureUserStoryInSprintJpaIsCreatedSuccessfully () {
        //Arrange
        String usId = "us001";
        int effort = 2;
        SprintJpa sprintJpa = mock(SprintJpa.class);

        //Act
        UserStoryInSprintJpa userStoryInSprintJpa = new UserStoryInSprintJpa(usId,effort,sprintJpa);

        //Assert
        assertEquals(usId, userStoryInSprintJpa.getUsId());
        assertEquals(effort, userStoryInSprintJpa.getEffort());
        assertEquals(sprintJpa, userStoryInSprintJpa.getSprint());
    }
    @DisplayName("Equals and HashCode Testing")
    @Test
    void testEqualsAndHashCodeMethods() {
        //Arrange
        UserStoryInSprintJpa userStory1 = new UserStoryInSprintJpa("us001", 5, null);
        UserStoryInSprintJpa userStory2 = new UserStoryInSprintJpa("us001", 5, null);

        //Act & Assert
        assertTrue(userStory1.equals(userStory2));
        assertTrue(userStory2.equals(userStory1));
        assertEquals(userStory1.hashCode(), userStory2.hashCode());
    }
    @DisplayName("ToString Testing")
    @Test
    void testToStringMethod() {
        //Arrange & Act
        UserStoryInSprintJpa userStory = new UserStoryInSprintJpa("us001", 5, null);

        //Assert
        assertEquals("UserStoryInSprintJpa(usId=us001, effort=5, sprint=null)", userStory.toString());
    }
    @DisplayName("Empty Constructor Testing")
    @Test
    void testEmptyConstructor() {
        // Assert
        UserStoryInSprintJpa usSprintJpa = new UserStoryInSprintJpa();

        // Verify that the attributes are initialized with default values
        assertNull(usSprintJpa.getUsId());
        assertEquals(0,usSprintJpa.getEffort());
        assertNull(usSprintJpa.getSprint());
    }
}