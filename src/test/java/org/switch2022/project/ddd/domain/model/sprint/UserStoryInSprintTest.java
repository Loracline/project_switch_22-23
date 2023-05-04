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
        assertTrue(userStoryInSprint.equals(userStoryInSprint));
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
        assertTrue(userStoryInSprint_one.equals(userStoryInSprint_two));
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
        when(userStoryInSprint_one.getUsId()).thenReturn("P001_US001");
        when(userStoryInSprint_two.getUsId()).thenReturn("P001_US002");

        //ACT & ASSERT
        assertFalse(userStoryInSprint_one.equals(userStoryInSprint_two));
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
        assertFalse(userStoryInSprint.equals(null));
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
        assertFalse(userStoryInSprint.equals(effort));
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
     * Method: getUsId().
     * Scenario 1: Ensure if the string representation of the UserStory id attribute of a given
     * instance of UserStoryInSprint is retrieved.
     * It should return a string like "P001_US001".
     */
    @Test
    public void ensureStringRepresentationOfUserStoryInSprintIdIsRetrieved() {
        //ARRANGE
        UsId usIdDouble = mock(UsId.class);
        when(usIdDouble.getUserStoryId()).thenReturn("P001_US001");
        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(usIdDouble, ONE);
        String expected = "P001_US001";

        //ACT
        String result = userStoryInSprint.getUsId();

        //ASSERT
        assertEquals(expected, result);
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
        boolean result = userStoryInSprint_One.setEffort(TWO.getEffortValue());

        //ACT & ASSERT
        assertTrue(result);
        assertEquals(TWO, userStoryInSprint_One.getEffort());
    }
}

