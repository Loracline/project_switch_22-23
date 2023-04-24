package org.switch2022.project.ddd.domain.model.user_story;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.value_object.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryTest {

    // UNIT TESTS

    /**
     * Constructor
     * <br>
     * Scenario 1: An User Story is not created because the ProjectCode is null.
     */
    @Test
    void ensureAnUserStoryIsNotCreatedBecauseTheProjectCodeIsNull() {
        // Arrange
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);

        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);

        String expected = "User Story's project code can't be null.";

        // Act
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new UserStory(null, usNumberDouble, actorDouble, usTextDouble, acceptanceCriteriaDouble));

        // Assert
        assertEquals(expected, result.getMessage());
    }
    /**
     * Constructor
     * <br>
     * Scenario 2: An User Story is not created because the USNumber is null.
     */

    @Test
    void ensureAnUserStoryIsNotCreatedBecauseTheUSNumberIsNull() {
        // Arrange
        Code projectCodeDouble = mock(Code.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);

        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);

        String expected = "User Story's User Story Number can't be null.";

        // Act
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new UserStory(projectCodeDouble, null, actorDouble, usTextDouble, acceptanceCriteriaDouble));

        // Assert
        assertEquals(expected, result.getMessage());
    }
    /**
     * Constructor
     * <br>
     * Scenario 3: An User Story is not created because the Actor is null.
     */
    @Test
    void ensureAnUserStoryIsNotCreatedBecauseTheActorIsNull() {
        // Arrange
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        UsText usTextDouble = mock (UsText.class);

        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);

        String expected = "User Story's actor can't be null.";

        // Act
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new UserStory(projectCodeDouble, usNumberDouble, null, usTextDouble, acceptanceCriteriaDouble));

        // Assert
        assertEquals(expected, result.getMessage());
    }
    /**
     * Constructor
     * <br>
     * Scenario 4: An User Story is not created because the USText is null.
     */
    @Test
    void ensureAnUserStoryIsNotCreatedBecauseTheUSTextIsNull() {
        // Arrange
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);

        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);

        String expected = "User Story's Text can't be null";

        // Act
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new UserStory(projectCodeDouble, usNumberDouble, actorDouble, null, acceptanceCriteriaDouble));

        // Assert
        assertEquals(expected, result.getMessage());
    }
    /**
     * Constructor
     * <br>
     * Scenario 5: An User Story is not created because the Acceptance Criteria is null.
     */
    @Test
    void ensureAnUserStoryIsNotCreatedBecauseTheAcceptanceCriteriaIsNull() {
        // Arrange
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);


        String expected = "User Story's Acceptance Criteria can't be null.";

        // Act
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble, null));

        // Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify that the same object equals itself.
     */
    @Test
    void ensureSameUserStoryEqualsItself() {
        // Arrange
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);

        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);
        when(projectCodeDouble.getCode()).thenReturn("p001");
        when(usNumberDouble.getUserStoryNumber()).thenReturn("us001");

        UserStory reference = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble, acceptanceCriteriaDouble);

        UserStory other = reference;
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two objects with the same attributes are equal.
     */
    @Test
    void ensureTwoInstancesWithSameIdAreEqual() {
        // Arrange
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);

        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);
        when(projectCodeDouble.getCode()).thenReturn("p001");
        when(usNumberDouble.getUserStoryNumber()).thenReturn("us001");

        UserStory reference = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble, acceptanceCriteriaDouble);
        UserStory other = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble, acceptanceCriteriaDouble);
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
        Code projectCodeDouble = mock(Code.class);
        Code projectCodeDoubleOther = mock(Code.class);

        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);

        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);
        when(projectCodeDouble.getCode()).thenReturn("p001");
        when(usNumberDouble.getUserStoryNumber()).thenReturn("us001");
        when(projectCodeDoubleOther.getCode()).thenReturn("p002");

        UserStory reference = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble,
                acceptanceCriteriaDouble);
        UserStory other = new UserStory(projectCodeDoubleOther, usNumberDouble, actorDouble,
                usTextDouble, acceptanceCriteriaDouble);
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: Verify that the object User Story does not equal null.
     */
    @Test
    void ensureUserStoryDoesNotEqualNull() {
        // Arrange
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);

        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);
        when(projectCodeDouble.getCode()).thenReturn("p001");
        when(usNumberDouble.getUserStoryNumber()).thenReturn("us001");

        UserStory reference = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble,
                acceptanceCriteriaDouble);
        UserStory other = null;
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 5: Verify that the object UserStory does not equal other type of object.
     */
    @Test
    void ensureUserStoryDoesNotEqualOtherTypeOfObject() {
        // Arrange
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);

        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);
        when(projectCodeDouble.getCode()).thenReturn("p001");
        when(usNumberDouble.getUserStoryNumber()).thenReturn("us001");

        UserStory reference = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble,
                acceptanceCriteriaDouble);
        Project other = mock(Project.class);
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Verify that two equal UserStory objects have the same hashcode.
     */
    @Test
    void ensureTwoEqualUserStoryInstancesHaveTheSameHashcode() {
        // Arrange
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);
        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);
        when(projectCodeDouble.getCode()).thenReturn("p001");
        when(usNumberDouble.getUserStoryNumber()).thenReturn("us001");

        //Other

        Code projectCodeDoubleOther = projectCodeDouble;
        UsNumber usNumberDoubleOther = usNumberDouble;
        Actor actorDoubleOther = actorDouble;
        UsText usTextDoubleOther = usTextDouble;
        List<AcceptanceCriteria> acceptanceCriteriaDoubleOther = new ArrayList<>();
        acceptanceCriteriaDoubleOther.add(acceptanceCriteriaElementDouble);

        UserStory reference = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble,
                acceptanceCriteriaDouble);
        UserStory other = new UserStory(projectCodeDoubleOther, usNumberDoubleOther, actorDoubleOther,usTextDoubleOther,
                acceptanceCriteriaDoubleOther);

        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two different UserStory objects have different hashcode.
     */
    @Test
    void ensureTwoDifferentUserStoryInstancesHaveDifferentHashcode() {
        // Arrange
        // US reference
        Code projectCodeDoubleOne = mock(Code.class);
        UsNumber usNumberDoubleOne = mock(UsNumber.class);
        Actor actorDoubleOne = mock(Actor.class);
        UsText usTextDoubleOne = mock (UsText.class);
        AcceptanceCriteria acceptanceCriteriaElementDoubleOne = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDoubleOne = new ArrayList<>();
        acceptanceCriteriaDoubleOne.add(acceptanceCriteriaElementDoubleOne);
        when(projectCodeDoubleOne.getCode()).thenReturn("p001");
        when(usNumberDoubleOne.getUserStoryNumber()).thenReturn("us001");

        //US Other
        Code projectCodeDoubleTwo = mock(Code.class);
        UsNumber usNumberDoubleTwo = mock(UsNumber.class);
        Actor actorDoubleTwo = mock(Actor.class);
        UsText usTextDoubleTwo = mock (UsText.class);
        AcceptanceCriteria acceptanceCriteriaElementDoubleTwo = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDoubleTwo = new ArrayList<>();
        acceptanceCriteriaDoubleTwo.add(acceptanceCriteriaElementDoubleTwo);
        when(projectCodeDoubleTwo.getCode()).thenReturn("p002");
        when(usNumberDoubleTwo.getUserStoryNumber()).thenReturn("us002");

        UserStory reference = new UserStory(projectCodeDoubleOne, usNumberDoubleOne, actorDoubleOne, usTextDoubleOne,
                acceptanceCriteriaDoubleOne);
        UserStory other = new UserStory(projectCodeDoubleTwo, usNumberDoubleTwo,actorDoubleTwo, usTextDoubleTwo,
                acceptanceCriteriaDoubleTwo);
        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertNotEquals(expected, result);
    }

    /**
     * METHOD sameIdentityAs()
     * <br>
     * Scenario 1: Check if two instances of UserStory are equal if the value of their projectCode and usNumber are the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoUserStoryInstancesHaveTheSameIdValue() {
        /// Arrange
        // US reference
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);
        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);
        when(projectCodeDouble.getCode()).thenReturn("p001");
        when(usNumberDouble.getUserStoryNumber()).thenReturn("us001");
        //US Other
        Actor actorDoubleTwo = mock(Actor.class);
        UsText usTextDoubleTwo = mock (UsText.class);
        AcceptanceCriteria acceptanceCriteriaElementDoubleTwo = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDoubleTwo = new ArrayList<>();
        acceptanceCriteriaDoubleTwo.add(acceptanceCriteriaElementDoubleTwo);

        UserStory reference = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble,
                acceptanceCriteriaDouble);
        UserStory other = new UserStory(projectCodeDouble, usNumberDouble,actorDoubleTwo, usTextDoubleTwo,
                acceptanceCriteriaDoubleTwo);

        // Act
        boolean result = reference.sameIdentityAs(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of UserStory are not equal if the value of their projectCode and usNumber
     * are not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoUserStoryInstancesDoNotHaveTheSameIdValue() {
        // Arrange
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);
        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);
        when(projectCodeDouble.getCode()).thenReturn("p001");
        when(usNumberDouble.getUserStoryNumber()).thenReturn("us001");

        //US Other
        Code projectCodeDoubleTwo = mock(Code.class);
        UsNumber usNumberDoubleTwo = mock(UsNumber.class);
        when(projectCodeDoubleTwo.getCode()).thenReturn("p002");
        when(usNumberDoubleTwo.getUserStoryNumber()).thenReturn("us002");

        UserStory reference = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble,
                acceptanceCriteriaDouble);
        UserStory other = new UserStory(projectCodeDoubleTwo, usNumberDoubleTwo,actorDouble, usTextDouble,
                acceptanceCriteriaDouble);

        boolean expected = false;

        // Act
        boolean result = reference.sameIdentityAs(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD getUsId()
     * <br>
     * Scenario 1: Verify that the returned user story id is the same of the User
     * Story.
     */
    @Test
    void ensureUserStoryIdIsRetrievedSuccessfully() {
        // Arrange
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);
        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);
        when(projectCodeDouble.getCode()).thenReturn("p001");
        when(usNumberDouble.getUserStoryNumber()).thenReturn("us001");

        UsId usId = mock(UsId.class);
        when(usId.getUserStoryId()).thenReturn("p001_us001");
        UserStory userStory = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble,
                acceptanceCriteriaDouble);


        String expected = usId.getUserStoryId();

        // Act
        String result = userStory.getUsId();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD getUsText()
     * <br>
     * Scenario 1: Verify that the returned user story text is the same of the User
     * Story.
     */
    @Test
    void ensureUserStoryTextIsRetrievedSuccessfully() {
        // Arrange
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);
        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);
        when(projectCodeDouble.getCode()).thenReturn("p001");
        when(usNumberDouble.getUserStoryNumber()).thenReturn("us001");
        UsText usTextDoubleTwo = mock (UsText.class);
        when(usTextDoubleTwo.getUserStoryText()).thenReturn("jogar 24/7");

        UserStory userStory = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble,
                acceptanceCriteriaDouble);

        userStory.setUsText(usTextDoubleTwo);

        // Act
        String result = userStory.getUsText();

        //Assert
        assertEquals("jogar 24/7", result);
    }

    /**
     * METHOD getUsNumber()
     * <br>
     * Scenario 1: Verify that the returned user story number is the same of the User
     * Story.
     */
    @Test
    void ensureUserStoryNumberIsRetrievedSuccessfully() {
        // Arrange
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);
        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);
        when(projectCodeDouble.getCode()).thenReturn("p001");
        when(usNumberDouble.getUserStoryNumber()).thenReturn("us001");

        UserStory userStory = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble,
                acceptanceCriteriaDouble);

        UsNumber usNumberDoubleTwo = mock(UsNumber.class);
        when(usNumberDoubleTwo.getUserStoryNumber()).thenReturn("us002");

        userStory.setUsNumber(usNumberDoubleTwo);

        // Act
        String result = userStory.getUsNumber();

        // Assert
        assertEquals("us002", result);
    }

    /**
     * METHOD getStatus()
     * <br>
     * Scenario 1: Verify that the returned user story status is the same of the User
     * Story.
     */
    @Test
    void ensureUserStoryStatusIsRetrievedSuccessfully() {
        // Arrange
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);
        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);
        when(projectCodeDouble.getCode()).thenReturn("p001");
        when(usNumberDouble.getUserStoryNumber()).thenReturn("us001");

        UserStory userStory = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble,
                acceptanceCriteriaDouble);

        Status usStatus = mock(Status.class);
        when(usStatus.getStatus()).thenReturn("Planned");
        userStory.setStatus(usStatus);

        // Act
        String result = userStory.getStatus();

        // Assert
        assertEquals("Planned", result);
    }

    /**
     * METHOD getAcceptanceCriteria()
     * <br>
     * Scenario 01: this method returns the list of user story acceptance criteria.
     */
    @Test
    void ensureTheListOfAcceptanceCriteriaIsRetrievedSuccessfully(){
        // Arrange
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);
        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);
        when(projectCodeDouble.getCode()).thenReturn("p001");
        when(usNumberDouble.getUserStoryNumber()).thenReturn("us001");

        UserStory userStory = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble,
                acceptanceCriteriaDouble);
        List<AcceptanceCriteria> expected= userStory.getAcceptanceCriteria();
        //Act
        List<AcceptanceCriteria> result = acceptanceCriteriaDouble;

        //Assert
        assertEquals(expected, result);

    }


    /**
     * METHOD has(usNumber) verifies if the User Story has the given User Story Number.
     * <br>
     * Scenario 1: User Story has the given USNumber. Should return true.
     */
    @Test
    void ensureThatUserStoryHasTheGivenUSNumber() {
        // Arrange
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);
        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);
        when(projectCodeDouble.getCode()).thenReturn("p001");
        when(usNumberDouble.getUserStoryNumber()).thenReturn("us001");

        UserStory userStory = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble,
                acceptanceCriteriaDouble);

        UsNumber usNumberDoubleTwo = mock(UsNumber.class);
        userStory.setUsNumber( usNumberDoubleTwo);

        // Act
        boolean result = userStory.has( usNumberDoubleTwo);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: User Story doesn't have the given USNumber. Should return false.
     */
    @Test
    void ensureThatUserStoryDoesNotHaveTheGivenUSNumber() {
        // Arrange
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);
        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);
        when(projectCodeDouble.getCode()).thenReturn("p001");
        when(usNumberDouble.getUserStoryNumber()).thenReturn("us001");

        UserStory userStory = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble,
                acceptanceCriteriaDouble);
        UsNumber usNumberToVerify = mock(UsNumber.class);

        // Act
        boolean result = userStory.has(usNumberToVerify);

        // Assert
        assertFalse(result);
    }
    /**
     * METHOD hasStatus.
     * <br>
     * Scenario 1: User Story has the given Status. Should return true.
     * Return: TRUE.
     */
    @Test
    void ensureThatUserStoryHasTheGivenStatus(){
        // Arrange
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);
        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);
        when(projectCodeDouble.getCode()).thenReturn("p001");
        when(usNumberDouble.getUserStoryNumber()).thenReturn("us001");

        UserStory userStory = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble,
                acceptanceCriteriaDouble);
        Status usStatusToVerify= mock (Status.class);
        when(usStatusToVerify.getStatus()).thenReturn("Running");
        userStory.setStatus(usStatusToVerify);

        //Act
        boolean result = userStory.hasStatus(usStatusToVerify);

        // Assert
        assertTrue(result);
    }
    /**
     * Scenario 2: User Story doesn't have the given Status.
     * Return: FALSE.
     */
    @Test
    void ensureThatUserStoryDoesNotHaveTheGivenStatus(){
        // Arrange
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);
        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);
        when(projectCodeDouble.getCode()).thenReturn("p001");
        when(usNumberDouble.getUserStoryNumber()).thenReturn("us001");

        UserStory userStory = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble,
                acceptanceCriteriaDouble);
        Status usStatusToVerify= mock (Status.class);
        when(usStatusToVerify.getStatus()).thenReturn("Running");

        //Act
        boolean result = userStory.hasStatus(usStatusToVerify);

        // Assert
        assertFalse(result);
    }

//    /**
//     * METHOD hasUsId
//     * Scenario 01: verify if the UserStory has the given usId.
//     */
//    @Test
//    void ensureThatUserStoryHasTheGivenUsId(){
//        // Arrange
//        Code projectCodeDouble = mock(Code.class);
//        UsNumber usNumberDouble = mock(UsNumber.class);
//        Actor actorDouble = mock(Actor.class);
//        UsText usTextDouble = mock (UsText.class);
//        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
//        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
//        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);
//        when(projectCodeDouble.getCode()).thenReturn("p001");
//        when(usNumberDouble.getUserStoryNumber()).thenReturn("us001");
//
//        UserStory userStory = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble,
//                acceptanceCriteriaDouble);
//        when(userStory.getUsId()).thenReturn("p001_us001");
//
//        UsId usIdToVerify = mock(UsId.class);
//        when(usIdToVerify.getUserStoryId()).thenReturn("p001_us001");
//        //Act
//        boolean result = userStory.hasUsId(usIdToVerify);
//
//        //Assert
//        assertTrue(result);
//    }


    /*
    /**
     * METHOD setValidUserStory() sets the relevant attributes for a user story to be in a valid state.
     * <br>
     * Scenario 1: All three relevant attributes are set successfully - not one of them is null.
     *//*
    @DisplayName("User story relevant attributes are set")
    @Test
    void ensureUserStoryIsSetInAValidState() {
        // ARRANGE
        Code projectCodeDouble = mock(Code.class);
        UsNumber usNumberDouble = mock(UsNumber.class);
        Actor actorDouble = mock(Actor.class);
        UsText usTextDouble = mock (UsText.class);
        AcceptanceCriteria acceptanceCriteriaElementDouble = mock (AcceptanceCriteria.class);
        List<AcceptanceCriteria> acceptanceCriteriaDouble = new ArrayList<>();
        acceptanceCriteriaDouble.add(acceptanceCriteriaElementDouble);
        UserStory userStory = new UserStory(projectCodeDouble, usNumberDouble, actorDouble, usTextDouble, acceptanceCriteriaDouble);

        UsNumber usNumber = mock(UsNumber.class);
        when(usNumber.getUserStoryNumber()).thenReturn("US01");
        UsText usText = mock(UsText.class);
        when(usText.getUserStoryText()).thenReturn("blablabla");
        Actor actor = mock(Actor.class);
        when(actor.getActor()).thenReturn("User");

        // ACT
        userStory.setValidUserStory(usNumber, usText, actor);

        // ASSERT
        assertNotNull(userStory.getActor());
    }


    // INTEGRATION TESTS (on aggregate UserStory = class + factory + value objects)

    /**
     * Scenario 1: A new user story is created with success, as the information needed is sufficient and valid.
     */
    /*
    @DisplayName("User story is created")
    @Test
    void ensureThatUserStoryIsCreatedSuccessfully() {
        // ARRANGE
        // 1. Creation of an instance of the User Story Factory.
        FactoryUserStory factoryUserStory = new FactoryUserStory();

        // 2. Creation of the value objects of interest.
        Code projectCode = new Code(1);
        UsNumber usNumber = new UsNumber("US1");
        UsId usId = new UsId(projectCode.getCode(), usNumber.getUserStoryNumber());
        UsText usText = new UsText("I want to create profiles.");
        Actor usActor = new Actor("Administrator");

        // 3. Creation of the expected User Story to compare in the assertion.
        UserStory expected = new UserStory(usId);
        expected.setValidUserStory(usNumber, usText, usActor);

        // ACT
        UserStory result = factoryUserStory.createUserStory(usNumber,
                usText, usActor, 0, projectCode);

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Creation of a new user story fails because the actor information is missing.
     */
    /*
    @DisplayName("User story isn't created - actor is missing")
    @Test
    void ensureCreationOfUserStoryFailsBecauseTheActorIsMissing() {
        // ARRANGE
        // 1. Creation of an instance of the User Story Factory.
        FactoryUserStory factoryUserStory = new FactoryUserStory();

        // 2. Creation of the value objects of interest.
        Code projectCode = new Code(1);
        UsNumber usNumber = new UsNumber("US1");
        UsText usText = new UsText("I want to create profiles.");
        Actor usActor = null;

        // 3. Expected message when creating the value object Actor with a null string.
        String expectedMessage = "The actor must not be null";

        // ACT AND ASSERT
        assertThrows(NullPointerException.class,
                () -> factoryUserStory.createUserStory(usNumber,
                        usText, usActor, 0, projectCode));

    }

    /**
     * Scenario 3: Creation of a new user story fails because the number is invalid.
     */
    /*
    @DisplayName("User story isn't created - number is invalid")
    @Test
    void ensureCreationOfUserStoryFailsBecauseNumberIsInvalid() {
        // ARRANGE
        // 1. Creation of an instance of the User Story Factory.
        FactoryUserStory factoryUserStory = new FactoryUserStory();

        // 2. Creation of the value objects of interest.
        Code projectCode = new Code(1);
        UsNumber usNumber = null;
        UsText usText = new UsText("I want to create profiles.");
        Actor usActor = new Actor("Administrator");

        // ACT AND ASSERT
        assertThrows(NullPointerException.class,
                () -> factoryUserStory.createUserStory(usNumber,
                        usText, usActor, 0, projectCode));
    }

    /**
     * Scenario 4: The status of a user story is changed successfully.
     */
    /*
    @DisplayName("Status of user story is changed")
    @Test
    void ensureStatusOfUserStoryIsChangedSuccessfully() {
        // ARRANGE
        // 1. Creation of an instance of the User Story Factory.
        FactoryUserStory factoryUserStory = new FactoryUserStory();

        // 2. Creation of the value objects of interest.
        Code projectCode = new Code(1);
        UsNumber usNumber = new UsNumber("US1");
        UsText usText = new UsText("I want to create profiles.");
        Actor usActor = new Actor("Administrator");

        // 3. Creation of user story by the factory.
        UserStory userStory = factoryUserStory.createUserStory(usNumber, usText, usActor, 0, projectCode);

        // 4. Expected status for the user story created.
        String expected = "Blocked";

        // ACT
        userStory.setStatus(Status.BLOCKED);

        // ASSERT
        assertEquals(expected, userStory.getStatus());
    }

    /**
     * Scenario 5: A list of acceptance criteria is added to the user story successfully.
     */
    /*
    @DisplayName("Acceptance criteria is added to user story")
    @Test
    void ensureAcceptanceCriteriaIsAddedSuccessfullyToUserStory() {
        // ARRANGE
        // 1. Creation of an instance of the User Story Factory.
        FactoryUserStory factoryUserStory = new FactoryUserStory();

        // 2. Creation of the value objects of interest.
        Code projectCode = new Code(1);
        UsNumber usNumber = new UsNumber("US1");
        UsText usText = new UsText("I want to create profiles.");
        Actor usActor = new Actor("Administrator");


        // 4. Creation of user story by the factory.
       UserStory userStory = factoryUserStory.createUserStory(usNumber, usText, usActor, 0, projectCode);

        // 5. Gathering the acceptance criteria of this user story.
        String acceptanceCriteria =
                "1. Fail to create US due to insufficient/invalid information\n" +
                        "\t\n" +
                        "2. Create US and add it to an empty backlog\n" +
                        "\t\n" +
                        "3. Fail to create a US because it has the same number of another (including finished USs)\n" +
                        "\t\n" +
                        "4. Create US and add to a non-empty backlog (at the end or beginning, according to the " +
                        "group's decision)\n" +
                        "\t\n" +
                        "5. Add US to a non-empty backlog at the specified priority n (and the length of the backlog " +
                        "is at least n)\n" +
                        "\t\n" +
                        "6. Add US to a non-empty backlog at the specified priority n (and the length of the backlog " +
                        "is shorter than n)";
        AcceptanceCriteria acceptanceCriteriaVO = new AcceptanceCriteria(acceptanceCriteria);
        String expected = acceptanceCriteriaVO.getAcceptanceCriteria();

        // ACT
        userStory.setAcceptanceCriteria(acceptanceCriteriaVO);

        // ASSERT
        assertEquals(expected, userStory.getAcceptanceCriteria());
    }


    /**
     * Method: hasStatus()
     * Scenario 01: make sure the userStories have the same status
     */
    /*
    @Test
    public void ensureTheUserStoriesStatusAreTheSame() {
        //Arrange
        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);

        Status status = mock(Status.class);

        //Act
        boolean expected = userStoryTwo.hasStatus(status);
        boolean result = userStoryOne.hasStatus(status);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: hasStatus()
     * Scenario 01: make sure the userStories have different status
     */
    /*
    @Test
    public void ensureTheUserStoriesStatusAreNotTheSame() {
        //Arrange
        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);

        Status statusOne = mock(Status.class);
        Status statusTwo = mock(Status.class);

        //Act
        boolean expected = userStoryTwo.hasStatus(statusOne);
        boolean result = userStoryOne.hasStatus(statusTwo);

        //Assert
        assertEquals(expected, result);
    } /*

     */
}
