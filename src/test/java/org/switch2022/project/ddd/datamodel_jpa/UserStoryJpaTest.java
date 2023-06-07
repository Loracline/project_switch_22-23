package org.switch2022.project.ddd.datamodel_jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class UserStoryJpaTest {
    private String usId;
    private String usIdTwo;
    private List<String> acceptanceCriteria;
    private String usNumber;
    private String actor;
    private String usText;
    private String status;
    private String projectCode;
    private String projectCodeTwo;

    @BeforeEach
    void setup() {
        usId = "us001";
        usIdTwo = "us002";
        acceptanceCriteria = Arrays.asList("AC1", "AC2", "AC3");
        usNumber = "1";
        actor = "User";
        usText = "As a user, I want to login to the system.";
        status = "Planned";
        projectCode = "p001";
        projectCodeTwo = "p002";
    }

    @Test
    void ensureUserStoryJpaIsCreated() {
        //ACT
        UserStoryJpa userStoryJpa = new UserStoryJpa();

        //ASSERT
        assertNotNull(userStoryJpa);
    }

    @Test
    void ensureUsIdIsReturned() {
        //ARRANGE & ACT
        UserStoryJpa userStory = new UserStoryJpa(usId, acceptanceCriteria, usNumber, actor,
                usText, status, projectCode);
        String actualUsId = userStory.getUsId();

        //ASSERT
        Assertions.assertEquals(usId, actualUsId);
    }

    @Test
    void ensureAcceptanceCriteriaIsReturned() {
        //ARRANGE & ACT
        UserStoryJpa userStory = new UserStoryJpa(usId, acceptanceCriteria, usNumber, actor,
                usText, status, projectCode);
        List<String> actualAcceptanceCriteria = userStory.getAcceptanceCriteria();

        //ASSERT
        Assertions.assertEquals(acceptanceCriteria, actualAcceptanceCriteria);
    }

    @Test
    void ensureUsNumberIsReturned() {
        //ARRANGE & ACT
        UserStoryJpa userStory = new UserStoryJpa(usId, acceptanceCriteria, usNumber, actor,
                usText, status, projectCode);
        String actualUsNumber = userStory.getUsNumber();

        //ASSERT
        Assertions.assertEquals(usNumber, actualUsNumber);
    }

    @Test
    void ensureActorIsReturned() {
        //ARRANGE & ACT
        UserStoryJpa userStory = new UserStoryJpa(usId, acceptanceCriteria, usNumber, actor,
                usText, status, projectCode);
        String actualActor = userStory.getActor();

        //ASSERT
        Assertions.assertEquals(actor, actualActor);
    }

    @Test
    void ensureUsTextIsReturned() {
        //ARRANGE & ACT
        UserStoryJpa userStory = new UserStoryJpa(usId, acceptanceCriteria, usNumber, actor,
                usText, status, projectCode);
        String actualUsText = userStory.getUsText();

        //ASSERT
        Assertions.assertEquals(usText, actualUsText);
    }

    @Test
    void ensureStatusIsReturned() {
        //ARRANGE & ACT
        UserStoryJpa userStory = new UserStoryJpa(usId, acceptanceCriteria, usNumber, actor,
                usText, status, projectCode);
        String actualStatus = userStory.getStatus();

        //ASSERT
        Assertions.assertEquals(status, actualStatus);
    }

    @Test
    void ensureProjectCodeIsReturned() {
        //ARRANGE & ACT
        UserStoryJpa userStory = new UserStoryJpa(usId, acceptanceCriteria, usNumber, actor,
                usText, status, projectCode);
        String actualProjectCode = userStory.getProjectCode();

        //ASSERT
        Assertions.assertEquals(projectCode, actualProjectCode);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify that the same object equals itself.
     */
    @Test
    void ensureSameUserStoryJpaEqualsItself() {
        //ARRANGE
        UserStoryJpa userStoryJpa = new UserStoryJpa(usId, acceptanceCriteria, usNumber, actor,
                usText, status, projectCode);
        UserStoryJpa other = userStoryJpa;
        boolean expected = true;

        //ACT
        boolean result = userStoryJpa.equals(other);

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two objects with the same attributes are equal.
     */
    @Test
    void ensureTwoInstancesWithSameIdAreEqual() {
        //ARRANGE
        UserStoryJpa userStoryJpa = new UserStoryJpa(usId, acceptanceCriteria, usNumber, actor,
                usText, status, projectCode);
        UserStoryJpa other = new UserStoryJpa(usId, acceptanceCriteria, usNumber, actor,
                usText, status, projectCode);

        boolean expected = true;

        //ACT
        boolean result = userStoryJpa.equals(other);

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Verify that two objects with different ids are not equal.
     */
    @Test
    void ensureTwoInstancesWithDifferentIdsAreNotEqual() {
        //ARRANGE
        UserStoryJpa userStoryJpa = new UserStoryJpa(usId, acceptanceCriteria, usNumber, actor,
                usText, status, projectCode);
        UserStoryJpa other = new UserStoryJpa(usIdTwo, acceptanceCriteria, usNumber, actor,
                usText, status, projectCode);

        boolean expected = false;

        //ACT
        boolean result = userStoryJpa.equals(other);

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: Verify that the object does not equal null.
     */
    @Test
    void ensureObjectDoesNotEqualNull() {
        //ARRANGE
        UserStoryJpa userStoryJpa = new UserStoryJpa(usId, acceptanceCriteria, usNumber, actor,
                usText, status, projectCode);

        UserStoryJpa other = null;
        boolean expected = false;

        //ACT
        boolean result = userStoryJpa.equals(other);

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 5: Verify that the object UserStoryJpa does not equal other type of object.
     */
    @Test
    void ensureUserStoryJpaDoesNotEqualOtherTypeOfObject() {
        //ARRANGE
        UserStoryJpa userStoryJpa = new UserStoryJpa(usId, acceptanceCriteria, usNumber, actor,
                usText, status, projectCode);

        UserStoryJpa other = mock(UserStoryJpa.class);
        boolean expected = false;

        //ACT
        boolean result = userStoryJpa.equals(other);

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Verify that two equal UserStoryJpa objects have the same hashcode.
     */
    @Test
    void ensureTwoEqualUserStoryInstancesHaveTheSameHashcode() {
        //ARRANGE
        UserStoryJpa userStoryJpa = new UserStoryJpa(usId, acceptanceCriteria, usNumber, actor,
                usText, status, projectCode);
        UserStoryJpa other = new UserStoryJpa(usId, acceptanceCriteria, usNumber, actor,
                usText, status, projectCode);

        int expected = userStoryJpa.hashCode();

        //ACT
        int result = other.hashCode();

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two different UserStoryJpa objects have different hashcode.
     */
    @Test
    void ensureTwoDifferentProjectInstancesHaveDifferentHashcode() {
        //ARRANGE
        UserStoryJpa userStoryJpa = new UserStoryJpa(usId, acceptanceCriteria, usNumber, actor,
                usText, status, projectCode);
        UserStoryJpa other = new UserStoryJpa(usId, acceptanceCriteria, usNumber, actor,
                usText, status, projectCodeTwo);

        int expected = userStoryJpa.hashCode();

        //ACT
        int result = other.hashCode();

        //ASSERT
        assertNotEquals(expected, result);
    }
}
