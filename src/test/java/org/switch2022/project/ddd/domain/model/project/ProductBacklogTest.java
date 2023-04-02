package org.switch2022.project.ddd.domain.model.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.PbId;
import org.switch2022.project.ddd.domain.value_object.UsId;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
        PbId pbId = null;
        String expected = "Product Backlog's ID can't be null.";

        // Act
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new ProductBacklog(pbId));

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
        PbId pbId = mock(PbId.class);
        ProductBacklog productBacklog = new ProductBacklog(pbId);
        ProductBacklog other = new ProductBacklog(pbId);
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
        PbId pbId = mock(PbId.class);
        PbId otherId = mock(PbId.class);
        ProductBacklog productBacklog = new ProductBacklog(pbId);
        ProductBacklog other = new ProductBacklog(otherId);
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
        PbId pbId = mock(PbId.class);
        ProductBacklog reference = new ProductBacklog(pbId);
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
        PbId pbId = mock(PbId.class);
        ProductBacklog reference = new ProductBacklog(pbId);
        ProductBacklog other = new ProductBacklog(pbId);
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
        PbId pbId = mock(PbId.class);
        PbId otherPbId = mock(PbId.class);
        ProductBacklog reference = new ProductBacklog(pbId);
        ProductBacklog other = new ProductBacklog(otherPbId);
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
        PbId pbId = mock(PbId.class);
        ProductBacklog reference = new ProductBacklog(pbId);
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
        PbId pbId = mock(PbId.class);
        ProductBacklog reference = new ProductBacklog(pbId);
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
        PbId pbId = mock(PbId.class);
        PbId otherPbId = pbId;
        ProductBacklog reference = new ProductBacklog(pbId);
        ProductBacklog other = new ProductBacklog(otherPbId);
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
        PbId pbId = mock(PbId.class);
        PbId otherPbId = mock(PbId.class);
        ProductBacklog reference = new ProductBacklog(pbId);
        ProductBacklog other = new ProductBacklog(otherPbId);
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
        PbId pbId = mock(PbId.class);
        ProductBacklog productBacklog = new ProductBacklog(pbId);
        List<UsId> expected = new ArrayList<>();

        // Act
        List<UsId> result = productBacklog.getUserStories();

        // Assert
        assertEquals(expected, result);
    }
}