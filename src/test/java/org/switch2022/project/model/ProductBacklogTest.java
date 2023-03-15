package org.switch2022.project.model;

import org.junit.jupiter.api.Test;
import org.switch2022.project.dto.UserStoryCreationDto;
import org.switch2022.project.factories.FactoryUserStory;
import org.switch2022.project.factories.IFactoryUserStory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductBacklogTest {
    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameProductBacklogEqualsItself() {
        // Arrange
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        ProductBacklog reference = new ProductBacklog(factoryUserStoryDouble);
        ProductBacklog other = reference;
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify if two objects of the same class are different from
     * each other.
     */
    @Test
    void ensureTwoProductBacklogsAreNotTheSame() {
        // Arrange
        IFactoryUserStory factoryUserStoryDoubleOne = mock(FactoryUserStory.class);
        ProductBacklog reference = new ProductBacklog(factoryUserStoryDoubleOne);

        UserStory userStoryDouble = mock(UserStory.class);
        reference.addUserStory(userStoryDouble, 0);

        IFactoryUserStory factoryUserStoryDoubleTwo = mock(FactoryUserStory.class);
        ProductBacklog other = new ProductBacklog(factoryUserStoryDoubleTwo);

        boolean expected = false;

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
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        ProductBacklog reference = new ProductBacklog(factoryUserStoryDouble);
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
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        ProductBacklog reference = new ProductBacklog(factoryUserStoryDouble);
        ProductBacklog other = null;
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two ProductBacklog objects are the same.
     */
    @Test
    public void ensureTwoProductBacklogsHashcodeAreTheSame() {
        // Arrange
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        ProductBacklog productBacklogOne = new ProductBacklog(factoryUserStoryDouble);
        ProductBacklog productBacklogTwo = new ProductBacklog(factoryUserStoryDouble);

        // Act
        int productBacklogOneHashCode = productBacklogOne.hashCode();
        int productBacklogTwoHashCode = productBacklogTwo.hashCode();

        // Assert
        assertEquals(productBacklogOneHashCode, productBacklogTwoHashCode);
    }

    /**
     * Scenario 2: Two different ProductBacklog objects are not the same.
     */
    @Test
    public void ensureTwoProductBacklogsHashcodeAreNotTheSame() {
        // Arrange
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        ProductBacklog productBacklogOne = new ProductBacklog(factoryUserStoryDouble);

        UserStory userStoryDouble = mock(UserStory.class);
        productBacklogOne.addUserStory(userStoryDouble, 0);

        ProductBacklog productBacklogTwo = new ProductBacklog(factoryUserStoryDouble);

        // Act
        int productBacklogOneHashCode = productBacklogOne.hashCode();
        int productBacklogTwoHashCode = productBacklogTwo.hashCode();

        // Assert
        assertNotEquals(productBacklogOneHashCode, productBacklogTwoHashCode);
    }

    /**
     * METHOD getUserStoryByNumber(userStoryNumber)
     * verifies that method returns a User Story from the Product Backlog with a given
     * User Story number.
     * <p>
     * Scenario 1: returns an Optional containing a User Story with a giving User Story
     * Number.
     */
    @Test
    void ensureThatReturnsAnOptionalWithAUserStory() {
        //ARRANGE
        IFactoryUserStory IFactoryUserStory = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(IFactoryUserStory);
        UserStory userStoryDouble = mock(UserStory.class);
        productBacklog.addUserStory(userStoryDouble, 0);
        when(userStoryDouble.hasUserStoryNumber("US002")).thenReturn(true);
        Optional<UserStory> userStoryOptionalExpected =
                Optional.ofNullable(userStoryDouble);

        //ACT
        Optional<UserStory> userStoryOptional =
                productBacklog.getUserStoryByNumber("US002");

        //ASSERT
        assertEquals(userStoryOptionalExpected, userStoryOptional);
    }

    /**
     * Scenario 2: returns an Optional containing a null object because there is no
     * User Story with that User Story number.
     */
    @Test
    void ensureThatReturnsAnOptionalWithANullObject() {
        //ARRANGE
        IFactoryUserStory IFactoryUserStory = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(IFactoryUserStory);
        UserStory userStoryDouble = mock(UserStory.class);
        productBacklog.addUserStory(userStoryDouble, 0);
        when(userStoryDouble.hasUserStoryNumber("US002")).thenReturn(false);

        Optional<UserStory> userStoryOptionalExpected = Optional.empty();

        //ACT
        Optional<UserStory> userStoryOptional = productBacklog.getUserStoryByNumber(
                "US002");

        //ASSERT
        assertEquals(userStoryOptionalExpected, userStoryOptional);
    }

    /**
     * METHOD createUserStory
     * creates a new User Story and add it to the Product Backlog
     * <p>
     * Scenario 1: verify if a User Story is added to Product Backlog if it is not
     * already there. Should return True.
     */
    @Test
    void ensureThatUserStoryIsSuccessfullyCreated() {
        //Arrange
        IFactoryUserStory iFactoryUserStory = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(iFactoryUserStory);
        UserStoryCreationDto userStoryCreationDtoDouble = mock (UserStoryCreationDto.class);
        when(userStoryCreationDtoDouble.getPriority()).thenReturn(0);

        //Act
        boolean result = productBacklog.createUserStory(userStoryCreationDtoDouble);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: verify if a User Story is added to Product Backlog if the priority value
     * is bigger than the size of the list already there.
     * Should return False.
     */
    @Test
    void ensureThatUserStoryIsUnsuccessfullyCreated() {
        //Arrange
        IFactoryUserStory iFactoryUserStory = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(iFactoryUserStory);
        UserStoryCreationDto userStoryCreationDtoDouble = mock (UserStoryCreationDto.class);
        when(userStoryCreationDtoDouble.getPriority()).thenReturn(2);
        //Act
        boolean result = productBacklog.createUserStory(userStoryCreationDtoDouble);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: verify if a User Story is not created, if it is
     * already in the Product Backlog. Should return false.
     */
    @Test
    void ensureThatUserStoryIsNotCreated_RepeatedInTheProductBacklog() {
        //Arrange
        IFactoryUserStory iFactoryUserStory = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(iFactoryUserStory);
        UserStoryCreationDto userStoryCreationDtoDouble = mock (UserStoryCreationDto.class);
        when(userStoryCreationDtoDouble.getPriority()).thenReturn(0);
        productBacklog.createUserStory(userStoryCreationDtoDouble);

        //Act
        boolean result = productBacklog.createUserStory(userStoryCreationDtoDouble);

        //Assert
        assertFalse(result);
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
        IFactoryUserStory factoryUserStory = mock(FactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStory);
        UserStory userStoryDouble = mock(UserStory.class);

        //Act
        boolean result = productBacklog.addUserStory(userStoryDouble, 0);

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
        IFactoryUserStory factoryUserStory = mock(FactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStory);
        UserStory userStoryDouble = mock(UserStory.class);
        productBacklog.addUserStory(userStoryDouble, 0);

        //Act
        boolean result = productBacklog.addUserStory(userStoryDouble, 3);

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
        productBacklog.addUserStory(userStoryDouble, 0);

        //ACT
        boolean result = productBacklog.removeUserStory(userStoryDouble);

        //ASSERT
        assertTrue(result);

    }

    /**
     * Scenario 2: verify if a User Story is not removed from Product Backlog because
     * it is empty. Should return FALSE.
     */
    @Test
    void ensureThatUserStoryIsNotRemovedFromProductBacklogBecauseItIsEmpty() {
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
     * Scenario 3: verify if a User Story is not removed from Product Backlog if it is not
     * there. Should return FALSE.
     */
    @Test
    void ensureThatUserStoryIsNotRemovedFromProductBacklogBecauseItIsNotThere() {
        //ARRANGE
        IFactoryUserStory IFactoryUserStory = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(IFactoryUserStory);
        UserStory userStoryDouble = mock(UserStory.class);
        UserStory userStoryDoubleTwo = mock(UserStory.class);
        productBacklog.addUserStory(userStoryDoubleTwo, 0);

        //ACT
        boolean result = productBacklog.removeUserStory(userStoryDouble);

        //ASSERT
        assertFalse(result);
    }

    /**
     * METHOD getUserStoriesCopy(). Copy the list of user stories contained in the
     * product backlog.
     * <p>
     * Scenario 1: Verifies that the method returns a list of deep copies of the user
     * stories that are in the product backlog.
     */
    @Test
    void ensureCopyListEqualsListInProductBacklog() {
        // ARRANGE
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        ProductBacklog productBacklogDouble = new ProductBacklog(factoryUserStoryDouble);

        UserStory userStoryDouble = mock(UserStory.class);
        when(factoryUserStoryDouble.createUserStory(any(), any(), any())).
                thenReturn(userStoryDouble);
        productBacklogDouble.addUserStory(userStoryDouble, 0);

        List<UserStory> expected = new ArrayList<>();
        expected.add(userStoryDouble);

        // ACT
        List<UserStory> result = productBacklogDouble.getUserStoriesCopy();

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * METHOD getProductBacklogCopy(). Copy the list of user stories contained in the
     * product backlog and stores them in another instance of product backlog.
     * <p>
     * Scenario 1: Verifies that the method returns a product backlog with a list of deep copies
     * of the user stories that are in the original product backlog.
     */
    @Test
    void ensureThatProductBacklogWithListOfCopiesEqualsTheOriginalProductBacklog() {
        //ARRANGE
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStoryDouble);

        UserStory userStoryDouble = mock(UserStory.class);
        when(factoryUserStoryDouble.createUserStory(any(), any(), any())).thenReturn(userStoryDouble);
        productBacklog.addUserStory(userStoryDouble, 0);

        ProductBacklog expected = new ProductBacklog(factoryUserStoryDouble);
        expected.addUserStory(userStoryDouble, 0);

        //ACT
        ProductBacklog result = productBacklog.getProductBacklogCopy();

        //ASSERT
        assertEquals(expected, result);

    }
}
