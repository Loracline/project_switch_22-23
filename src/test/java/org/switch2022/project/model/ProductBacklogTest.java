package org.switch2022.project.model;

import org.junit.jupiter.api.Test;
import org.switch2022.project.factories.IFactoryUserStory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



public class ProductBacklogTest {
    /*
      METHOD equals()
     */

    /**
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameProductBacklogEqualsItself() {
        // Arrange
        IFactoryUserStory IFactoryUserStory = mock(IFactoryUserStory.class);
        ProductBacklog reference = new ProductBacklog(IFactoryUserStory);
        ProductBacklog other = reference;
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Verify if a ProductBacklog and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureProductBacklogDoesNotEqualOtherTypeOfObject() {
        // Arrange
        IFactoryUserStory IFactoryUserStory = mock(IFactoryUserStory.class);
        ProductBacklog reference = new ProductBacklog(IFactoryUserStory);
        String other = "User";
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: Verify if a ProductBacklog and a null object are not the same.
     */
    @Test
    void ensureProductBacklogDoesNotEqualNull() {
        // Arrange
        IFactoryUserStory IFactoryUserStory = mock(IFactoryUserStory.class);
        ProductBacklog reference = new ProductBacklog(IFactoryUserStory);
        ProductBacklog other = null;
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /*
      METHOD hashCode()
     */

    /**
     * Scenario 1: Two ProductBacklog objects are the same.
     */
    @Test
    public void ensureTwoProductBacklogsHashcodeAreTheSame() {
        // Arrange
        IFactoryUserStory IFactoryUserStory = mock(IFactoryUserStory.class);
        ProductBacklog productBacklogOne = new ProductBacklog(IFactoryUserStory);
        ProductBacklog productBacklogTwo = new ProductBacklog(IFactoryUserStory);

        // Act
        int productBacklogOneHashCode = productBacklogOne.hashCode();
        int productBacklogTwoHashCode = productBacklogTwo.hashCode();

        // Assert
        assertEquals(productBacklogOneHashCode, productBacklogTwoHashCode);
    }

    /**
     * METHOD getUserStoryByNumber(userStoryNumber)
     * verifies that method returns a User Story from the Product Backlog with a given User Story number.
     * <p>
     * Scenario 1: returns an Optional containing a User Story with a giving User Story Number.
     */
    @Test
    void ensureThatReturnsAnOptionalWithAUserStory() {
        //ARRANGE
        IFactoryUserStory IFactoryUserStory = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(IFactoryUserStory);
        UserStory userStoryDouble = mock(UserStory.class);
        productBacklog.addUserStory(userStoryDouble);
        when(userStoryDouble.hasUserStoryNumber("US002")).thenReturn(true);
        Optional<UserStory> userStoryOptionalExpected = Optional.of(userStoryDouble);

        //ACT
        Optional<UserStory> userStoryOptional = productBacklog.getUserStoryByNumber("US002");

        //ASSERT
        assertEquals(userStoryOptionalExpected, userStoryOptional);
    }

    /**
     * Scenario 2: returns an Optional containing a null object because there is no User Story with that User Story number.
     */
    @Test
    void ensureThatReturnsAnOptionalWithANullObject() {
        //ARRANGE
        IFactoryUserStory IFactoryUserStory = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(IFactoryUserStory);
        UserStory userStoryDouble = mock(UserStory.class);
        productBacklog.addUserStory(userStoryDouble);
        when(userStoryDouble.hasUserStoryNumber("US002")).thenReturn(false);

        //Optional<UserStory> userStoryOptionalExpected = Optional.ofNullable(null);
        Optional<UserStory> userStoryOptionalExpected = Optional.empty();

        //ACT
        Optional<UserStory> userStoryOptional = productBacklog.getUserStoryByNumber("US002");

        //ASSERT
        assertEquals(userStoryOptionalExpected, userStoryOptional);
    }

    /**
     * METHOD addUserStory(userStory)
     * adds a User Story to Product Backlog
     * <p>
     * Scenario 1: verify if a User Story is added to Product Backlog if it is not
     * already there. Should return True.
     */

    @Test
    void ensureThatUserStoryIsSuccessfullyAddedToProductBacklog() {
        //Arrange
        IFactoryUserStory IFactoryUserStory = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(IFactoryUserStory);
        UserStory userStoryDouble = mock(UserStory.class);

        //Act
        boolean result = productBacklog.addUserStory(userStoryDouble);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: verify if a User Story is not added to Product Backlog if it is
     * already there. Should return false.
     */

    @Test
    void ensureThatUserStoryIsNotAddedToProductBacklog() {
        //Arrange
        IFactoryUserStory IFactoryUserStory = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(IFactoryUserStory);
        UserStory userStoryDouble = mock(UserStory.class);
        productBacklog.addUserStory(userStoryDouble);

        //Act
        boolean result = productBacklog.addUserStory(userStoryDouble);

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD removeUserStory(userStory)
     * remove a User Story from Product Backlog
     * <p>
     * Scenario 1: verify if a User Story is removed from Product Backlog if it is
     * there. Should return TRUE.
     */
    @Test
    void ensureThatUserStoryIsRemovedFromProductBacklog() {
        //ARRANGE
        IFactoryUserStory IFactoryUserStory = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(IFactoryUserStory);
        UserStory userStoryDouble = mock(UserStory.class);
        productBacklog.addUserStory(userStoryDouble);

        //ACT
        boolean result = productBacklog.removeUserStory(userStoryDouble);

        //ASSERT
        assertTrue(result);

    }

    /**
     * Scenario 2: verify if a User Story is not removed from Product Backlog if it is not
     * there. Should return FALSE.
     */
    @Test
    void ensureThatUserStoryIsNotRemovedFromProductBacklogBecauseItIsNotThere() {
        //ARRANGE
        IFactoryUserStory IFactoryUserStory = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(IFactoryUserStory);
        UserStory userStoryDouble = mock(UserStory.class);

        //ACT
        boolean result = productBacklog.removeUserStory(userStoryDouble);

        //ASSERT
        assertFalse(result);
    }

    /**
     * METHOD getUserStoriesCopy(). Copy the list of user stories contained in the
     * product backlog.
     *
     * Scenario 1: Verifies that the method returns a list of deep copies of the user
     * stories that are in the product backlog.
     */
    @Test
    void ensureCopyListEqualsListInProductBacklog() {
        // ARRANGE
        FactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        ProductBacklog productBacklogDouble = new ProductBacklog(factoryUserStoryDouble);

        UserStory userStoryDouble = mock(UserStory.class);
        userStoryDouble.setStatus(any());
        userStoryDouble.setEffort(any());
        when(factoryUserStoryDouble.createUserStory(any(), any(), any())).
                thenReturn(userStoryDouble);
        productBacklogDouble.addUserStory(userStoryDouble);

        List<UserStory> expected = new ArrayList<>();
        expected.add(userStoryDouble);

        // ACT
        List<UserStory> result = productBacklogDouble.getUserStoriesCopy();

        // ASSERT
        assertEquals(expected, result);
    }
}
