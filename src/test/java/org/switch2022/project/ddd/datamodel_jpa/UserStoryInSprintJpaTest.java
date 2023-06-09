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

        //Act
        UserStoryInSprintJpa userStoryInSprintJpa = new UserStoryInSprintJpa(usId,effort);

        //Assert
        assertEquals(usId, userStoryInSprintJpa.getUsId());
        assertEquals(effort, userStoryInSprintJpa.getEffort());
    }
    @DisplayName("Equals and HashCode Testing")
    @Test
    void testEqualsAndHashCodeMethods() {
        // Arrange
        UserStoryInSprintJpa userStory1 = new UserStoryInSprintJpa("us001", 5);
        UserStoryInSprintJpa userStory2 = new UserStoryInSprintJpa("us001", 5);
        UserStoryInSprintJpa userStory3 = new UserStoryInSprintJpa("us002", 5);
        UserStoryInSprintJpa userStory4 = new UserStoryInSprintJpa("us001", 10);
        UserStoryInSprintJpa userStory6 = new UserStoryInSprintJpa(null, 5);
        UserStoryInSprintJpa userStory7 = new UserStoryInSprintJpa("us001", 5);
        userStory7.setSprint(mock(SprintJpa.class));

        // Act & Assert
        assertEquals(userStory1, userStory2);
        assertEquals(userStory2, userStory1);
        assertEquals(userStory1.hashCode(), userStory2.hashCode());

        assertNotEquals(userStory1, userStory3);
        assertNotEquals(userStory1, userStory4);
        assertNotEquals(null, userStory1);
        assertNotEquals(userStory1, userStory6);
        assertNotEquals(userStory1, new Object());
        assertNotEquals(userStory6, userStory1);
        assertNotEquals(userStory1, userStory7);
    }
    @DisplayName("Equals and HashCode Testing - Additional Cases")
    @Test
    void testEqualsAndHashCodeAdditionalCases() {
        // Arrange
        UserStoryInSprintJpa userStory1 = new UserStoryInSprintJpa("us001", 5);
        UserStoryInSprintJpa userStory2 = new UserStoryInSprintJpa("us001", 5);

        // Act & Assert
        assertEquals(userStory1, userStory2);
        assertEquals(userStory2, userStory1);
        assertEquals(userStory1.hashCode(), userStory2.hashCode());
    }
    @DisplayName("Equals and HashCode Testing - additional tests")
    @Test
    void testEqualsAndHashCodeMethodsAdditional() {
        // Arrange
        UserStoryInSprintJpa userStory1 = new UserStoryInSprintJpa("us001", 5);
        UserStoryInSprintJpa userStory2 = new UserStoryInSprintJpa("us001", 5);

        // Act & Assert
        assertEquals(userStory1, userStory2);
        assertEquals(userStory2, userStory1);
        assertEquals(userStory1.hashCode(), userStory2.hashCode());

        // Test with different attribute values
        UserStoryInSprintJpa userStory3 = new UserStoryInSprintJpa("us002", 3);
        assertNotEquals(userStory1, userStory3);
        assertNotEquals(userStory3, userStory1);
    }
    @DisplayName("HashCode Method Testing")
    @Test
    void testHashCodeMethod() {
        // Arrange
        UserStoryInSprintJpa userStory1 = new UserStoryInSprintJpa("us001", 5);
        UserStoryInSprintJpa userStory2 = new UserStoryInSprintJpa("us001", 5);
        UserStoryInSprintJpa userStory3 = new UserStoryInSprintJpa("us002", 10);

        // Act
        int hashCode1 = userStory1.hashCode();
        int hashCode2 = userStory2.hashCode();
        int hashCode3 = userStory3.hashCode();

        // Assert
        assertEquals(hashCode1, hashCode2); // Objects with equal attributes should have the same hash code
        assertNotEquals(hashCode1, hashCode3); // Objects with different attributes should have different hash codes
    }

    @DisplayName("HashCode Method Testing - Attribute Combination 1")
    @Test
    void testHashCodeMethod_AttributeCombination1() {
        // Arrange
        UserStoryInSprintJpa userStory1 = new UserStoryInSprintJpa("us001", 5);
        UserStoryInSprintJpa userStory2 = new UserStoryInSprintJpa("us001", 5);

        // Act
        int hashCode1 = userStory1.hashCode();
        int hashCode2 = userStory2.hashCode();

        // Assert
        assertEquals(hashCode1, hashCode2);
    }

    @DisplayName("Equals Method Testing")
    @Test
    void testEqualsMethod() {
        //Assert
        UserStoryInSprintJpa userStory1 = new UserStoryInSprintJpa();
        userStory1.setUsId("us001");
        userStory1.setEffort(5);
        userStory1.setSprint(new SprintJpa());

        UserStoryInSprintJpa userStory2 = new UserStoryInSprintJpa();
        userStory2.setUsId("us001");
        userStory2.setEffort(5);
        userStory2.setSprint(new SprintJpa());

        // Test equality
        assertEquals(userStory1, userStory2);
        assertEquals(userStory2, userStory1);

        // Test inequality
        userStory2.setUsId("us002");
        assertNotEquals(userStory1, userStory2);
        assertNotEquals(userStory2, userStory1);

        userStory2.setUsId("us001");
        userStory2.setEffort(10);
        assertNotEquals(userStory1, userStory2);
        assertNotEquals(userStory2, userStory1);

        userStory2.setEffort(5);
        userStory2.setSprint(null);
        assertNotEquals(userStory1, userStory2);
        assertNotEquals(userStory2, userStory1);
    }

    @DisplayName("ToString Testing")
    @Test
    void testToStringMethod() {
        //Arrange & Act
        UserStoryInSprintJpa userStory = new UserStoryInSprintJpa("us001", 5);

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

    }
    @DisplayName("Invalid Input Values")
    @Test
    void testInvalidInputValues() {
        // Arrange and Act
        UserStoryInSprintJpa userStoryInSprintJpa = new UserStoryInSprintJpa(null, -1);

        // Assert
        assertNull(userStoryInSprintJpa.getUsId());
        assertEquals(-1, userStoryInSprintJpa.getEffort());
        assertNull(userStoryInSprintJpa.getSprint());
    }

    @DisplayName("Date Format Parsing")
    @Test
    void testDateFormatParsing() {
        // Arrange
        String usId = "us001";
        int effort = 5;

        // Act
        UserStoryInSprintJpa userStoryInSprintJpa = new UserStoryInSprintJpa(usId, effort);

        // Assert
        assertEquals(usId, userStoryInSprintJpa.getUsId());
        assertEquals(effort, userStoryInSprintJpa.getEffort());
    }
    @DisplayName("Setters Testing")
    @Test
    void testSetters() {
        // Arrange
        String usId = "us001";
        int effort = 5;

        UserStoryInSprintJpa userStoryInSprintJpa = new UserStoryInSprintJpa();

        // Act
        userStoryInSprintJpa.setUsId(usId);
        userStoryInSprintJpa.setEffort(effort);

        // Assert
        assertEquals(usId, userStoryInSprintJpa.getUsId());
        assertEquals(effort, userStoryInSprintJpa.getEffort());
    }
}