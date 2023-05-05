package org.switch2022.project.ddd.domain.model.sprint;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.Effort;
import org.switch2022.project.ddd.domain.value_object.UsId;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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
     * Constructor with only usId
     */
    @Test
    void ensureAUserStoryInSprintIsNotCreatedBecauseTheUsIdIsNull_ConstructorWithOnlyUsId() {
        //ACT & ASSERT
        assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryInSprint(null);
        });
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
        UserStoryInSprint userStoryInSprint = mock(UserStoryInSprint.class);
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
        when(usId.getUserStoryId()).thenReturn("P001_US001");
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
        UserStoryInSprint userStoryInSprint_one = mock(UserStoryInSprint.class);
        UserStoryInSprint userStoryInSprint_two = mock(UserStoryInSprint.class);
        UsId usIdOne = mock(UsId.class);
        UsId usIdTwo = mock(UsId.class);
        when(userStoryInSprint_one.getUsId()).thenReturn(usIdOne);
        when(userStoryInSprint_two.getUsId()).thenReturn(usIdTwo);

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
        UserStoryInSprint userStoryInSprint = mock(UserStoryInSprint.class);

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
        UserStoryInSprint userStoryInSprint = mock(UserStoryInSprint.class);
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
        UserStoryInSprint userStoryInSprint_Two = new UserStoryInSprint(usIdDouble, TWO);

        //ACT & ASSERT
        assertEquals(userStoryInSprint_One, userStoryInSprint_Two);
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
        boolean result = userStoryInSprint_One.changeEffort(TWO);

        //ACT & ASSERT
        assertTrue(result);
        assertEquals(TWO, userStoryInSprint_One.getEffort());
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
        Effort initialEffort = userStoryInSprint_One.getEffort();

        //ACT
        boolean result = userStoryInSprint_One.changeEffort(effortDouble);

        //ASSERT
        assertFalse(result);
        assertEquals(initialEffort, userStoryInSprint_One.getEffort());
    }
}

