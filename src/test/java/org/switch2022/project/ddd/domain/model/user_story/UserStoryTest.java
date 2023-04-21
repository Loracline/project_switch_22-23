package org.switch2022.project.ddd.domain.model.user_story;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryTest {

    // UNIT TESTS

    /**
     * Constructor
     * <br>
     * Scenario 1: An User Story is not created because the ID is null.
     */
    @Test
    void ensureAnUserStoryIsNotCreatedBecauseTheIdIsNull() {
        // Arrange
        UsId usId = null;
        String expected = "User Story's ID can't be null.";

        // Act
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new UserStory(usId));

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
        UsId usId = mock(UsId.class);
        UserStory reference = new UserStory(usId);
        UserStory other = reference;
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
        UsId usId = mock(UsId.class);
        UserStory reference = new UserStory(usId);
        UserStory other = new UserStory(usId);
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
        UsId usId = mock(UsId.class);
        UsId otherUsId = mock(UsId.class);
        UserStory reference = new UserStory(usId);
        UserStory other = new UserStory(otherUsId);
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
        UsId usId = mock(UsId.class);
        UserStory reference = new UserStory(usId);
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
        UsId usId = mock(UsId.class);
        UserStory reference = new UserStory(usId);
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
     * Scenario 1: Verify that two equal UserStory objects have the same hashcode.
     */
    @Test
    void ensureTwoEqualUserStoryInstancesHaveTheSameHashcode() {
        // Arrange
        UsId usId = mock(UsId.class);
        UsId otherUsId = usId;
        UserStory reference = new UserStory(usId);
        UserStory other = new UserStory(otherUsId);
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
        UsId usId = mock(UsId.class);
        UsId otherUsId = mock(UsId.class);
        UserStory reference = new UserStory(usId);
        UserStory other = new UserStory(otherUsId);
        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertNotEquals(expected, result);
    }

    /**
     * METHOD sameIdentityAs()
     * <br>
     * Scenario 1: Verify that two instances of UserStory are equal if the value of their
     * attribute user story id is the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoUserStoryInstancesHaveTheSameIdValue() {
        // Arrange
        UsId usId = mock(UsId.class);
        UserStory userStory = new UserStory(usId);
        UserStory other = new UserStory(usId);
        boolean expected = true;

        // Act
        boolean result = userStory.sameIdentityAs(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two instances of UserStory are not equal if the value of
     * their attribute user story id is not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoUserStoryInstancesDoNotHaveTheSameIdValue() {
        // Arrange
        UsId usId = mock(UsId.class);
        UsId otherUsId = mock(UsId.class);
        UserStory userStory = new UserStory(usId);
        UserStory other = new UserStory(otherUsId);
        boolean expected = false;

        // Act
        boolean result = userStory.sameIdentityAs(other);

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
        UsId usId = mock(UsId.class);
        when(usId.getUserStoryId()).thenReturn("123");
        UserStory userStory = new UserStory(usId);


        // Act
        String result = userStory.getUsId();

        // Assert
        assertEquals("123", result);
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
        UsText usText = mock(UsText.class);
        when(usText.getUserStoryText()).thenReturn("jogar 24/7");
        UsId usId = mock(UsId.class);
        UserStory userStory = new UserStory(usId);
        userStory.setUsText(usText);

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
        UsNumber usNumber = mock(UsNumber.class);
        when(usNumber.getUserStoryNumber()).thenReturn("US01");
        UsId usId = mock(UsId.class);
        UserStory userStory = new UserStory(usId);
        userStory.setUsNumber(usNumber);

        // Act
        String result = userStory.getUsNumber();

        // Assert
        assertEquals("US01", result);
    }

    /**
     * METHOD getUsStatus()
     * <br>
     * Scenario 1: Verify that the returned user story status is the same of the User
     * Story.
     */
    @Test
    void ensureUserStoryStatusIsRetrievedSuccessfully() {
        // Arrange
        Status usStatus = mock(Status.class);
        when(usStatus.getStatus()).thenReturn("Planned");
        UsId usId = mock(UsId.class);
        UserStory userStory = new UserStory(usId);
        userStory.setStatus(usStatus);

        // Act
        String result = userStory.getStatus();

        // Assert
        assertEquals("Planned", result);
    }

    /**
     * METHOD has(usNumber) verifies if the User Story has the given User Story Number.
     * <br>
     * Scenario 1: User Story has the given USNumber. Should return true.
     */
    @Test
    void ensureThatUserStoryHasTheGivenUSNumber() {
        // Arrange
        UsId usId = mock(UsId.class);
        UserStory userStory = new UserStory(usId);
        UsNumber usNumberDouble = mock(UsNumber.class);
        userStory.setUsNumber(usNumberDouble);

        // Act
        boolean result = userStory.has(usNumberDouble);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: User Story doesn't have the given USNumber. Should return false.
     */
    @Test
    void ensureThatUserStoryDoesNotHaveTheGivenUSNumber() {
        // Arrange
        UsId usId = mock(UsId.class);
        UserStory userStory = new UserStory(usId);
        UsNumber usNumberDouble = mock(UsNumber.class);
        UsNumber usNumberToVerify = mock(UsNumber.class);
        userStory.setUsNumber(usNumberDouble);

        // Act
        boolean result = userStory.has(usNumberToVerify);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD setValidUserStory() sets the relevant attributes for a user story to be in a valid state.
     * <br>
     * Scenario 1: All three relevant attributes are set successfully - not one of them is null.
     */
    @DisplayName("User story relevant attributes are set")
    @Test
    void ensureUserStoryIsSetInAValidState() {
        // ARRANGE
        UsId usId = mock(UsId.class);
        UserStory userStory = new UserStory(usId);

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
    }
}
