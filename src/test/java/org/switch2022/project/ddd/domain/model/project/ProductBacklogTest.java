package org.switch2022.project.ddd.domain.model.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.UsId;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProductBacklogTest {

    /**
     * Constructor
     * <br>
     * Scenario 1: A Product Backlog is not created because the ID is null.
     */
    @Test
    void ensureAProductBacklogIsNotCreatedBecauseTheIdIsNull() {
        // Arrange
        String projectCode = null;
        String expected = "Product Backlog's ID can't be null.";

        // Act
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new ProductBacklog(projectCode));

        // Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * METHOD sameIdentityAs()
     * <br>
     * Scenario 1: Verify that two instances of ProductBacklog are equal if the value of their attribute product
     * backlog id is the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoProductBacklogInstancesHaveTheSameIdValue() {
        // Arrange
        String code = "P001";
        ProductBacklog productBacklog = new ProductBacklog(code);
        ProductBacklog other = new ProductBacklog(code);
        boolean expected = true;

        // Act
        boolean result = productBacklog.sameIdentityAs(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two instances of ProductBacklog are not equal if the value of their attribute product
     * backlog id is not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoProductBacklogInstancesDoNotHaveTheSameIdValue() {
        // Arrange
        String code = "P001";
        String otherCode = "P002";
        ProductBacklog productBacklog = new ProductBacklog(code);
        ProductBacklog other = new ProductBacklog(otherCode);
        boolean expected = false;

        // Act
        boolean result = productBacklog.sameIdentityAs(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Verify that two instances of ProductBacklog are not equal because one of them is null.
     */
    @Test
    void ensureThatReturnsFalseIfOneOfTheProductBacklogsIsNull() {
        // Arrange
        String code = "P001";
        ProductBacklog productBacklog = new ProductBacklog(code);
        ProductBacklog other = null;
        boolean expected = false;

        // Act
        boolean result = productBacklog.sameIdentityAs(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify that the same object equals itself.
     */
    @Test
    void ensureSameProductBacklogEqualsItself() {
        // Arrange
        String code = "P001";
        ProductBacklog reference = new ProductBacklog(code);
        ProductBacklog other = reference;
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two objects with the same id are equal.
     */
    @Test
    void ensureTwoInstancesWithSameIdAreEqual() {
        // Arrange
        String code = "P001";
        ProductBacklog reference = new ProductBacklog(code);
        ProductBacklog other = new ProductBacklog(code);
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Verify that two objects with different ids are not equal.
     */
    @Test
    void ensureTwoInstancesWithDifferentIdsAreNotEqual() {
        // Arrange
        String code = "P001";
        String otherCode = "P002";
        ProductBacklog reference = new ProductBacklog(code);
        ProductBacklog other = new ProductBacklog(otherCode);
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: Verify that the object ProductBacklog does not equal null.
     */
    @Test
    void ensureProductBacklogDoesNotEqualNull() {
        // Arrange
        String code = "P001";
        ProductBacklog reference = new ProductBacklog(code);
        boolean expected = false;

        // Act
        boolean result = reference.equals(null);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 5: Verify that the object ProductBacklog does not equal other type of object.
     */
    @Test
    void ensureProductBacklogDoesNotEqualOtherTypeOfObject() {
        // Arrange
        String code = "P001";
        ProductBacklog reference = new ProductBacklog(code);
        UsId other = mock(UsId.class);
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Verify that two equal ProductBacklog objects have the same hashcode.
     */
    @Test
    void ensureTwoEqualProductBacklogInstancesHaveTheSameHashcode() {
        // Arrange
        String code = "P001";
        String otherCode = code;
        ProductBacklog reference = new ProductBacklog(code);
        ProductBacklog other = new ProductBacklog(otherCode);
        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two different ProductBacklog objects have different hashcode.
     */
    @Test
    void ensureTwoDifferentProductBacklogInstancesHaveDifferentHashcode() {
        // Arrange
        String code = "P001";
        String otherCode = "P002";
        ProductBacklog reference = new ProductBacklog(code);
        ProductBacklog other = new ProductBacklog(otherCode);
        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertNotEquals(expected, result);
    }

    /**
     * METHOD getUserStories()
     * <br>
     * Scenario 1: Verify that is returned an empty list when the Product Backlog does not have any UsId listed.
     */
    @Test
    void ensureProductBacklogReturnsAnEmptyListWhenThereAreNoUsIdInIt() {
        // Arrange
        String code = "P001";
        ProductBacklog productBacklog = new ProductBacklog(code);
        List<UsId> expected = new ArrayList<>();

        // Act
        List<UsId> result = productBacklog.getUserStories();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that is returned a list of IDs of User Stories when the Product Backlog has UsId listed - the
     * User Stories were added without priority defined.
     */
    @Test
    void ensureProductBacklogReturnsAListOfUserStoryIDsWhenThereAreUsIdInItAddedWithoutPriority() {
        // Arrange
        String code = "P001";
        ProductBacklog productBacklog = new ProductBacklog(code);
        List<UsId> expected = new ArrayList<>();

        UsId usIdOne = mock(UsId.class);
        UsId usIdTwo = mock(UsId.class);
        UsId usIdThree = mock(UsId.class);

        expected.add(usIdOne);
        expected.add(usIdTwo);
        expected.add(usIdThree);

        productBacklog.addUserStory(-1,usIdOne);
        productBacklog.addUserStory(-1,usIdTwo);
        productBacklog.addUserStory(-1, usIdThree);

        // Act
        List<UsId> result = productBacklog.getUserStories();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Verify that is returned a list of IDs of User Stories when the Product Backlog has UsId listed - the
     * User Stories were added with priority defined.
     */
    @Test
    void ensureProductBacklogReturnsAListOfUserStoryIDsWhenThereAreUsIdInItAddedWithPriority() {
        // Arrange
        String code = "P001";
        ProductBacklog productBacklog = new ProductBacklog(code);
        List<UsId> expected = new ArrayList<>();

        UsId usIdOne = mock(UsId.class);
        UsId usIdTwo = mock(UsId.class);
        UsId usIdThree = mock(UsId.class);

        expected.add(usIdOne);
        expected.add(usIdTwo);
        expected.add(usIdThree);

        productBacklog.addUserStory(0,usIdOne);
        productBacklog.addUserStory(1,usIdTwo);
        productBacklog.addUserStory(2, usIdThree);

        // Act
        List<UsId> result = productBacklog.getUserStories();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD addUserStory(userStory)
     * adds a User Story to Product Backlog
     * <p>
     * Scenario 1: verify if a User Story is added to Product Backlog in the specified
     * position if it is not already there. Should return True.
     */
    @Test
    void ensureThatUserStoryIsSuccessfullyAddedToProductBacklog() {
        //Arrange
        String code = "P001";
        UsId usId = mock(UsId.class);
        UsId otherUsId = mock(UsId.class);
        ProductBacklog productBacklog = new ProductBacklog(code);
        productBacklog.addUserStory(0, otherUsId);

        //Act
        boolean result = productBacklog.addUserStory(0, usId);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: verify if a User Story is added to Product Backlog at the end of the
     * list because priority was not specified (-1 is the parameter given from the
     * front end when user doesn't specify the User Story priority. Should return
     * true).
     */
    @Test
    void ensureThatUserStoryIsSuccessfullyAddedToTheEndOfTheProductBacklog() {
        //Arrange
        String code = "P001";
        UsId usId = mock(UsId.class);
        UsId otherUsId = mock(UsId.class);
        ProductBacklog productBacklog = new ProductBacklog(code);
        productBacklog.addUserStory(0, otherUsId);

        //Act
        boolean result = productBacklog.addUserStory(-1, usId);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 3: verify if a User Story is added to Product Backlog when priority
     * equals list size. Should return true.
     */
    @Test
    void ensureThatUserStoryIsSuccessfullyAddedToTheProductBacklogWhenPriorityEqualsListSize() {
        //Arrange
        String code = "P001";
        UsId usId = mock(UsId.class);
        UsId otherUsId = mock(UsId.class);
        ProductBacklog productBacklog = new ProductBacklog(code);
        productBacklog.addUserStory(0, otherUsId);

        //Act
        boolean result = productBacklog.addUserStory(1, usId);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 4: verify if a User Story is not added to Product Backlog if it is
     * already there. Should return false.
     */
    @Test
    void ensureThatUserStoryIsNotAddedToTheProductBacklogBecauseItIsAlreadyThere() {
        //Arrange
        String code = "P001";
        UsId usId = mock(UsId.class);
        ProductBacklog productBacklog = new ProductBacklog(code);
        productBacklog.addUserStory(0, usId);

        //Act
        boolean result = productBacklog.addUserStory(0, usId);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: verify if a User Story is not added to Product Backlog if the index
     * is bigger than the list. Should throw an exception.
     */
    @Test
    void ensureThatUserStoryIsNotAddedToTheProductBacklogBecausePriorityIsBiggerThanListSize() {
        //Arrange
        String expected = "This position doesn't exist, since it's" +
                " bigger than the list of User Stories.";

        String code = "P001";
        UsId usId = mock(UsId.class);
        UsId otherUsId = mock(UsId.class);
        ProductBacklog productBacklog = new ProductBacklog(code);
        productBacklog.addUserStory(0, otherUsId);

        //Act
        IndexOutOfBoundsException exception = assertThrowsExactly(IndexOutOfBoundsException.class, () ->
                productBacklog.addUserStory(2, usId));

        //Assert
        assertEquals(expected, exception.getMessage());
    }
}