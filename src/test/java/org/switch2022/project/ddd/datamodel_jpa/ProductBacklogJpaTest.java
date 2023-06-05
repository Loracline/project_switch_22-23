package org.switch2022.project.ddd.datamodel_jpa;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.project.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProductBacklogJpaTest {

    @Test
    void ensureProductBacklogJpaIsCreated(){
        //Act
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa();

        //Assert
        assertNotNull(productBacklogJpa);
    }

    @Test
    void getProductBacklogId() {
        //Arrange
        List<String> userStories = new ArrayList<>();
        userStories.add("p001_us001");
        userStories.add("p001_us002");
        userStories.add("p001_us003");
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb",userStories);
        String expected =  "p001_pb";

        //Act
        String result = productBacklogJpa.getProductBacklogId();

        //Assert
        assertEquals(expected,result);

    }

    @Test
    void getUserStories() {
        //Arrange
        List<String> userStories = new ArrayList<>();
        userStories.add("p001_us001");
        userStories.add("p001_us002");
        userStories.add("p001_us003");
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb",userStories);
        List<String> expected =  Arrays.asList("p001_us001","p001_us002", "p001_us003");

        //Act
        List<String> result =  productBacklogJpa.getUserStories();

        //Assert
        assertEquals(expected,result);
    }
    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify that the same object equals itself.
     */
    @Test
    void ensureSameProductBacklogEqualsItself() {
        // Arrange
        List<String> userStories = new ArrayList<>();
        userStories.add("p001_us001");
        userStories.add("p001_us002");
        userStories.add("p001_us003");
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb",userStories);
        ProductBacklogJpa other = productBacklogJpa;
        boolean expected = true;

        // Act
        boolean result = productBacklogJpa.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two objects with the same attributes are equal.
     */
    @Test
    void ensureTwoInstancesWithSameIdAreEqual() {
        // Arrange
        List<String> userStories = new ArrayList<>();
        userStories.add("p001_us001");
        userStories.add("p001_us002");
        userStories.add("p001_us003");
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb",userStories);
        ProductBacklogJpa other = new ProductBacklogJpa("p001_pb",userStories);
        boolean expected = true;

        // Act
        boolean result = productBacklogJpa.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Verify that two objects with different ids are not equal.
     */
    @Test
    void ensureTwoInstancesWithDifferentIdsAreNotEqual() {
        // Arrange
        List<String> userStories = new ArrayList<>();
        userStories.add("p001_us001");
        userStories.add("p001_us002");
        userStories.add("p001_us003");
        ProductBacklogJpa reference = new ProductBacklogJpa("p001_pb",userStories);
        List<String> userStoriesB = new ArrayList<>();
        userStories.add("p001_us001");
        ProductBacklogJpa other = new ProductBacklogJpa("p001_pb",userStoriesB);
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: Verify that the object does not equal null.
     */
    @Test
    void ensureObjectDoesNotEqualNull() {
        // Arrange
        List<String> userStories = new ArrayList<>();
        userStories.add("p001_us001");
        userStories.add("p001_us002");
        userStories.add("p001_us003");
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb",userStories);
        ProductBacklogJpa other = null;
        boolean expected = false;

        // Act
        boolean result = productBacklogJpa.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 5: Verify that the object businessSector does not equal other type of object.
     */
    @Test
    void ensureBusinessSectorDoesNotEqualOtherTypeOfObject() {
        // Arrange
        List<String> userStories = new ArrayList<>();
        userStories.add("p001_us001");
        userStories.add("p001_us002");
        userStories.add("p001_us003");
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb",userStories);
        Project other = mock(Project.class);
        boolean expected = false;

        // Act
        boolean result = productBacklogJpa.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Verify that two equal BusinessSector objects have the same hashcode.
     */
    @Test
    void ensureTwoEqualBusinessSectorInstancesHaveTheSameHashcode() {
        // Arrange
        List<String> userStories = new ArrayList<>();
        userStories.add("p001_us001");
        userStories.add("p001_us002");
        userStories.add("p001_us003");
        ProductBacklogJpa reference = new ProductBacklogJpa("p001_pb",userStories);
        ProductBacklogJpa other = new ProductBacklogJpa("p001_pb",userStories);

        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two different BusinessSector objects have different hashcode.
     */
    @Test
    void ensureTwoDifferentBusinessSectorInstancesHaveDifferentHashcode() {
        // Arrange
        List<String> userStories = new ArrayList<>();
        userStories.add("p001_us001");
        userStories.add("p001_us002");
        userStories.add("p001_us003");
        ProductBacklogJpa reference= new ProductBacklogJpa("p001_pb",userStories);
        ProductBacklogJpa other = new ProductBacklogJpa("p002_pb",userStories);
        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertNotEquals(expected, result);
    }
}