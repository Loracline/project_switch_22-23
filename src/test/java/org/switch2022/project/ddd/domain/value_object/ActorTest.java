package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {
    /**
     * METHOD getActor()
     * <br>
     * Scenario 1: Verify that the value returned is the same as the value of the actor attribute of the
     * Actor value object.
     */
    @Test
    void ensureActorIsRetrievedSuccessfully() {
        // Arrange
        Actor actor = new Actor("Manager");
        String expected = "Manager".toLowerCase();

        // Act
        String result = actor.getActor();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of Actor are equal if the value of their attribute actor is
     * the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoActorInstancesHaveTheSameActorValue() {
        //Arrange
        Actor actor = new Actor("Manager");
        Actor otherActor = new Actor("Manager");
        //Act
        boolean result = actor.sameValueAs(otherActor);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of Actor are not equal if the value of their attribute actor is not the
     * same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoActorInstancesHaveDifferentActorValues() {
        //Arrange
        Actor actor = new Actor("Manager");
        Actor otherActor = new Actor("Product Owner");
        //Act
        boolean result = actor.sameValueAs(otherActor);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameActorEqualsItself() {
        // Arrange
        Actor reference = new Actor("Manager");
        Actor other = reference;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify if two different objects of the same class are different from
     * each other.
     */
    @Test
    void ensureTwoDifferentActorInstancesAreNotTheSame() {
        // Arrange
        Actor reference = new Actor("Manager");
        Actor other = new Actor("Product Owner");

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: Verify if an Actor instance and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureActorDoesNotEqualOtherTypeOfObject() {
        // Arrange
        Actor reference = new Actor("Manager");
        String other = "other object";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if an Actor and a null object are not the same.
     */
    @Test
    void ensureActorInstanceDoesNotEqualNull() {
        // Arrange
        Actor reference = new Actor("Manager");
        Actor other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Two equal Actor objects are the same.
     */
    @Test
    public void ensureTwoActorInstancesHashcodeAreTheSame() {
        // Arrange
        Actor actorOne = new Actor("Manager");
        Actor actorTwo = new Actor("Manager");

        // Act
        int actorOneHashCode = actorOne.hashCode();
        int actorTwoHashCode = actorTwo.hashCode();

        // Assert
        assertEquals(actorOneHashCode, actorTwoHashCode);
    }

    /**
     * Scenario 2: Two different Actor objects are not the same.
     */
    @Test
    public void ensureTwoActorInstancesHashcodeAreNotTheSame() {
        // Arrange
        Actor actorOne = new Actor("Manager");
        Actor actorThree = new Actor("Scrum Master");

        // Act
        int actorOneHashCode = actorOne.hashCode();
        int actorThreeHashCode = actorThree.hashCode();

        // Assert
        assertNotEquals(actorOneHashCode, actorThreeHashCode);
    }

}