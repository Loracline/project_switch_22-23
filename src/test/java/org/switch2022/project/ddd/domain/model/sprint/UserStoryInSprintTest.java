package org.switch2022.project.ddd.domain.model.sprint;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.Effort;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.switch2022.project.ddd.domain.value_object.Effort.ONE;
import static org.switch2022.project.ddd.domain.value_object.Effort.TWO;

public class UserStoryInSprintTest {

    /**
     * Constructor
     * Scenario 1: A UserStoryInSprint is not created because the usId is null.
     * <p>
     * It should throw an Illegal Argument Exception.
     */
    @Test
    void ensureAUserStoryInSprintIsNotCreatedBecauseTheUsIdIsNull() {
        //ACT & ASSERT
        assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryInSprint(null, ONE);
        });
    }

    /**
     * Constructor with only usId:
     * Scenario 1: fails because id is null
     */
    @Test
    void ensureAUserStoryInSprintIsNotCreatedBecauseTheUsIdIsNull_ConstructorWithOnlyUsId() {
        //ACT & ASSERT
        assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryInSprint(null);
        });
    }

    /**
     * Scenario 2 : create US with only id.
     */

    @Test
    void createsUserStoryInSprintWithOnlyId() {
        //Arrange
        UsId usId = mock(UsId.class);
        //Act
        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(usId);
        //Assert
        assertNotNull(userStoryInSprint);
    }

    /**
     * Scenario 3 :verifies if an instance of UsId is not created because the string corresponding to the user story
     * number passed as argument is null.
     */

    @Test
    void ensureThatAnExceptionIsThrownWhenUsIdISNull() {
        //Arrange
        String expected = "UsId cannot be null";
        //Act
        InvalidInputException exception = assertThrowsExactly(InvalidInputException.class, () ->
                new UserStoryInSprint(null));
        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD equals()
     * <p>
     * Scenario 1: Verify that the same object equals itself.
     * It should assert true.
     */
    @Test
    void ensureThatSameObjectEqualsItself() {
        //ARRANGE
        UsId usId = mock(UsId.class);
        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(usId, ONE);
        //ACT & ASSERT
        assertEquals(userStoryInSprint, userStoryInSprint);
    }

    /**
     * Scenario 2: Verify that two different UserStoryInSprint has the same usId.
     * <p>
     * It should assert true.
     */

    @Test
    void ensureThatTwoUserStoryInSprintHasTheSameId() {
        //ARRANGE
        UsId usId = mock(UsId.class);
        UserStoryInSprint userStoryInSprint_one = new UserStoryInSprint(usId, ONE);
        UserStoryInSprint userStoryInSprint_two = new UserStoryInSprint(usId, TWO);

        //ACT & ASSERT
        assertEquals(userStoryInSprint_one, userStoryInSprint_two);
    }

    /**
     * Scenario 3: Verify that two different UserStoryInSprint has not the same usId.
     * <p>
     * It should assert false.
     */

    @Test
    void ensureThatTwoProfilesHasNotTheSameId() {
        //ARRANGE
        UsId usIdOne = mock(UsId.class);
        UsId usIdTwo = mock(UsId.class);
        UserStoryInSprint userStoryInSprint_one = new UserStoryInSprint(usIdOne, ONE);
        UserStoryInSprint userStoryInSprint_two = new UserStoryInSprint(usIdTwo, ONE);

        //ACT & ASSERT
        assertNotEquals(userStoryInSprint_one, userStoryInSprint_two);
    }

    /**
     * Scenario 4: Verify that the object UserStoryInSprint does not equal null.
     * <p>
     * It should assert false.
     */
    @Test
    void ensureThatObjectProfileDoesNotEqualNull() {
        //ARRANGE
        UsId usId = mock(UsId.class);
        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(usId, ONE);

        //ACT & ASSERT
        assertNotEquals(null, userStoryInSprint);
    }


    /**
     * Scenario 5: Verify that the object Profile is not equal to other type of object.
     * <p>
     * It should assert false.
     */
    @Test
    public void ensureThatObjectUserStoryInSprintDoesNotEqualToOtherObjectType() {
        //ARRANGE
        UsId usId = mock(UsId.class);
        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(usId, ONE);
        Effort effort = ONE;

        //ACT & ASSERT
        assertNotEquals(userStoryInSprint, effort);
    }

    /**
     * Test for the hashCode() method.
     */
    @Test
    void ensureTwoSprintHashcodeAreTheSame() {
        // ARRANGE
        UsId usIdDouble = mock(UsId.class);
        UserStoryInSprint userStoryInSprint_One = new UserStoryInSprint(usIdDouble, ONE);
        UserStoryInSprint userStoryInSprint_Two = new UserStoryInSprint(usIdDouble, ONE);

        //Act
        int userStoryInSprint_OneHashCode = userStoryInSprint_One.hashCode();
        int userStoryInSprint_TwoHashCode = userStoryInSprint_Two.hashCode();


        //ASSERT
        assertEquals(userStoryInSprint_OneHashCode, userStoryInSprint_TwoHashCode );
    }

    @Test
    void ensureTwoSprintHashcodeAreNotTheSame() {
        // ARRANGE
        UsId usIdDouble = mock(UsId.class);
        UsId usIdTriple = mock(UsId.class);
        UserStoryInSprint userStoryInSprint_One = new UserStoryInSprint(usIdDouble, ONE);
        UserStoryInSprint userStoryInSprint_Two = new UserStoryInSprint(usIdTriple, TWO);

        //Act
        int userStoryInSprint_OneHashCode = userStoryInSprint_One.hashCode();
        int userStoryInSprint_TwoHashCode = userStoryInSprint_Two.hashCode();


        //ASSERT
        assertNotEquals(userStoryInSprint_OneHashCode, userStoryInSprint_TwoHashCode );
    }


    /**
     * Method: sameIdentityAs()
     * Scenario 01: This test evaluates if two instances of UserStoryInSprint are the same if
     * their usId attribute are the same.
     * It should assert true.
     */
    @Test
    void ensureTheTwoInstancesOfUserStoryInSprintAreTheSameIfTheirIdAttributeIsTheSame() {
        //ARRANGE
        UsId usIdDouble = mock(UsId.class);
        UserStoryInSprint userStoryInSprintOne = new UserStoryInSprint(usIdDouble, ONE);
        UserStoryInSprint userStoryInSprintTwo = new UserStoryInSprint(usIdDouble, ONE);

        //ACT
        boolean result = userStoryInSprintOne.sameIdentityAs(userStoryInSprintTwo);

        //ASSERT
        assertTrue(result);
    }

    /**
     * Method: sameIdentityAs()
     * Scenario 02: This test evaluates if two instances of UserStoryInSprint are different if
     * their usId attribute are not the same.
     * It should assert false.
     */
    @Test
    void ensureTheTwoInstancesOfUserStoryInSprintAreDifferentIfTheirIdAttributeIsNotTheSame() {
        //ARRANGE
        Effort effortDouble = mock(Effort.class);
        UsId usIdOne = new UsId("P001", "US001");
        UsId usIdTwo = new UsId("P002", "US002");
        UserStoryInSprint userStoryInSprintOne = new UserStoryInSprint(usIdOne, effortDouble);
        UserStoryInSprint userStoryInSprintTwo = new UserStoryInSprint(usIdTwo, effortDouble);

        //ACT
        boolean result = userStoryInSprintOne.sameIdentityAs(userStoryInSprintTwo);

        //ASSERT
        assertFalse(result);
    }

    /**
     * Method: sameIdentityAs()
     * Scenario 03: This test evaluates if two instances of UserStoryInSprint are different if
     * one of them is null.
     * It should assert false.
     */
    @Test
    void ensureTheTwoProfilesAreNotTheSameBecauseOneOfThemIsNull() {
        //ARRANGE
        UsId usIdDouble = mock(UsId.class);
        UserStoryInSprint userStoryInSprintOne = new UserStoryInSprint(usIdDouble, ONE);
        UserStoryInSprint userStoryInSprintTwo = null;

        //ACT
        boolean result = userStoryInSprintOne.sameIdentityAs(userStoryInSprintTwo);

        //ASSERT
        assertFalse(result);
    }

    /**
     * Method: getUsId().
     * Scenario 1: Ensure if the string representation of the UserStory id attribute of a given
     * instance of UserStoryInSprint is retrieved.
     * It should return a string like "P001_US001".
     */
    @Test
    public void ensureStringRepresentationOfUserStoryInSprintIdIsRetrieved() {
        //ARRANGE
        UsId usIdDouble = mock(UsId.class);
        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(usIdDouble, ONE);

        //ACT
        UsId result = userStoryInSprint.getUsId();

        //ASSERT
        assertEquals(usIdDouble, result);
    }

    /**
     * Method setEffort.
     * <br>
     * Scenario 1: Verify if the effort of userStoryOne is set.
     * Expected result: effort of userStoryOne is equal to effort value.
     */
    @Test
    public void ensureEffortIsSetForUserStory() {
        //ARRANGE
        UsId usIdDouble = mock(UsId.class);
        UserStoryInSprint userStoryInSprint_One = new UserStoryInSprint(usIdDouble, ONE);
        boolean result = userStoryInSprint_One.changeEffort(2);

        //ACT & ASSERT
        assertTrue(result);
        assertEquals(2, userStoryInSprint_One.getEffort());
    }

    /**
     * Scenario 2: Verify if the effort of userStoryOne is not set when an invalid input is given.
     * Expected result: effort of userStoryOne remains unchanged.
     */
    @Test
    public void ensureEffortIsNotSetForUserStory() {
        //ARRANGE
        UsId usIdDouble = mock(UsId.class);
        Effort effortDouble = mock(Effort.class);
        UserStoryInSprint userStoryInSprint_One = new UserStoryInSprint(usIdDouble, ONE);
        int initialEffort = userStoryInSprint_One.getEffort();

        //ACT
        boolean result = userStoryInSprint_One.changeEffort(effortDouble.getEffortValue());

        //ASSERT
        assertFalse(result);
        assertEquals(initialEffort, userStoryInSprint_One.getEffort());
    }
}

