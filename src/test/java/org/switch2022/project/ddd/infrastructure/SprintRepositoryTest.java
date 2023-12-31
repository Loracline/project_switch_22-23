package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.value_object.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SprintRepositoryTest {
    /**
     * Method: equals()
     * Scenario 01: Test to ensure the object equals itself
     */
    @Test
    void ensureSameSprintEqualsItself() {
        // Arrange
        SprintRepository sprintRepository = new SprintRepository();
        SprintRepository sprintRepositoryReference = sprintRepository;
        boolean expected = true;

        //Act
        boolean result = sprintRepository.equals(sprintRepositoryReference);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 02:Test to ensure that two objects are from different classes
     */
    @Test
    void ensureSprintsAreFromDifferentClasses() {
        // Arrange
        SprintRepository sprintRepository = new SprintRepository();

        Object sprintRepositoryObject = new Object();
        boolean expected = false;

        //Act
        boolean result = sprintRepository.equals(sprintRepositoryObject);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 04: Test to ensure that two objects from the same class are different
     */
    @Test
    void ensureTwoSprintsAreNotEqual() {
        // Arrange
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprint = mock(Sprint.class);
        sprintRepository.save(sprint);
        SprintRepository sprintRepositoryOne = new SprintRepository();

        boolean expected = false;
        //Act
        boolean result = sprintRepository.equals(sprintRepositoryOne);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 05: Test to ensure that two objects from the same class are equals.
     */
    @Test
    void ensureTwoSprintsAreEqual() {
        // Arrange
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprint = mock(Sprint.class);
        sprintRepository.save(sprint);
        SprintRepository sprintRepositoryOne = new SprintRepository();
        sprintRepositoryOne.save(sprint);

        boolean expected = true;
        //Act
        boolean result = sprintRepository.equals(sprintRepositoryOne);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 06: Test to ensure that Repository doesn't equals a null object
     */
    @Test
    void ensureThatSprintDoesNotEqualsNull() {
        // Arrange
        SprintRepository sprintRepository = new SprintRepository();
        SprintRepository sprintRepositoryOne = null;

        boolean expected = false;
        //Act
        boolean result = sprintRepository.equals(sprintRepositoryOne);
        //Assert
        assertEquals(expected, result);
    }


    /**
     * Method:hashCode()
     * Scenario 01:Two equal SprintsRepository objects are the same.
     */
    @Test
    void ensureTwoSprintsIdInstancesHashcodeAreTheSame() {
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprint = mock(Sprint.class);
        sprintRepository.save(sprint);
        SprintRepository sprintRepositoryOne = new SprintRepository();
        sprintRepositoryOne.save(sprint);
        //Assert
        assertEquals(sprintRepository.hashCode(), sprintRepositoryOne.hashCode());
    }


    /**
     * Scenario 02:Two objects with the different code and different hash codes are two different objects
     */
    @Test
    void ensureNoTwoSprintsHaveTheSameHashCode() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprint = mock(Sprint.class);
        sprintRepository.save(sprint);
        SprintRepository sprintRepositoryOne = new SprintRepository();
        Sprint sprintOne = mock(Sprint.class);
        sprintRepositoryOne.save(sprintOne);

        //Assert
        assertNotEquals(sprintRepositoryOne.hashCode(), sprintRepository.hashCode());
    }

    /**
     * METHOD: getSprintById
     * scenario 01: returns an empty optional because the repository is empty
     */
    @Test
    void ensureSprintIsEmptyBecauseRepositoryIsEmpty() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        SprintId sprintId = new SprintId("P001", "S001");

        Optional<Sprint> expected = Optional.empty();
        //Act
        Optional<Sprint> result = sprintRepository.findById(sprintId);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 02: returns an empty optional because the id doesn't match any sprintID
     */
    @Test
    void ensureSprintIsEmptyBecauseRepositoryDoesntHaveSprintId() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprintDouble = mock(Sprint.class);
        sprintRepository.save(sprintDouble);
        SprintId sprintId = new SprintId("P001", "S001");

        Optional<Sprint> expected = Optional.empty();
        //Act
        Optional<Sprint> result = sprintRepository.findById(sprintId);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 03: returns an optional with a sprint
     */
    @Test
    void ensureReturnsSprintSuccessfully() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprintDouble = mock(Sprint.class);
        sprintRepository.save(sprintDouble);
        SprintId sprintId = new SprintId("P001", "S001");
        when(sprintDouble.hasSprintId(sprintId)).thenReturn(true);
        Optional<Sprint> expected = Optional.of(sprintDouble);
        //Act
        Optional<Sprint> result = sprintRepository.findById(sprintId);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD: getSprintNumber
     */
    @Test
    void ensureSprintNumberIsRetrieved() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprintDouble = mock(Sprint.class);
        sprintRepository.save(sprintDouble);
        long expected = 1L;
        //Act
        long result = sprintRepository.count();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD:addSprintToSprintRepository
     * scenario 1: returns true
     */
    @Test
    void ensureSprintIsAddedToRepository() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprintDouble = mock(Sprint.class);
        //Act
        boolean result = sprintRepository.save(sprintDouble);
        //Assert
        assertTrue(result);
    }

    /**
     * METHOD:addSprintToSprintRepository
     * scenario 2: returns false
     */
    @Test
    void ensureSprintIsNotAddedToRepository() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprintDouble = mock(Sprint.class);
        sprintRepository.save(sprintDouble);
        //Act
        boolean result = sprintRepository.save(sprintDouble);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD: findAllByProject
     * scenario 1: returns an empty list, because repository is empty
     */
    @Test
    void ensureAnEmptyListIsReturned_EmptyRepository() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        List<Sprint> expected = new ArrayList<>();
        Code projectCode = new Code(1);
        //Act
        List<Sprint> result = sprintRepository.findByProjectCode(projectCode);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 2: returns an empty list because there are no sprints matching the ProjectCode
     */
    @Test
    void ensureAnEmptyListIsReturned_NoMatchingCode() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprintDouble = mock(Sprint.class);
        sprintRepository.save(sprintDouble);
        List<Sprint> expected = new ArrayList<>();
        Code projectCode = new Code(1);
        when(sprintDouble.hasProjectCode(projectCode)).thenReturn(false);
        //Act
        List<Sprint> result = sprintRepository.findByProjectCode(projectCode);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 3: returns a list with sprints
     */
    @Test
    void ensureListOfSprintsIsReturned() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        Sprint sprintDouble = mock(Sprint.class);
        Sprint sprintDoubleTwo = mock(Sprint.class);
        sprintRepository.save(sprintDouble);
        sprintRepository.save(sprintDoubleTwo);
        List<Sprint> expected = new ArrayList<>();
        expected.add(sprintDouble);
        Code projectCode = new Code(1);
        when(sprintDouble.hasProjectCode(projectCode)).thenReturn(true);
        //Act
        List<Sprint> result = sprintRepository.findByProjectCode(projectCode);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: existsByProjectCodeAndStatus(projectCode, status)
     * Scenario 1: checks if the list of sprints of a given project has any sprint with a given status.
     * It should assert true.
     */
    @Test
    void ensureThatReturnsTrueIfAtLeastOneInstanceOfSprintInTheListOfSprintsInAGivenProjectHasTheStatusPassedAsParameter() {
        // ARRANGE
        SprintRepository sprintRepository = new SprintRepository();

        Sprint sprint = mock(Sprint.class);
        Code projectCode = mock(Code.class);
        sprintRepository.save(sprint);
        when(sprint.hasStatus(SprintStatus.OPEN)).thenReturn(true);
        when(sprint.hasProjectCode(projectCode)).thenReturn(true);
        // ACT
        boolean result = sprintRepository.existsByProjectCodeAndStatus(projectCode, SprintStatus.OPEN);
        // ASSERT
        assertTrue(result);
    }

    /**
     * Method: existsByProjectCodeAndStatus(projectCode, status)
     * Scenario 2: checks if the list of sprints of a given project does not have any sprint with a given status.
     * It should assert false.
     */
    @Test
    void ensureThatReturnsFalseIfNoInstancesOfSprintInTheListOfSprintsHaveTheStatusPassedAsParameter() {
        // ARRANGE
        SprintRepository sprintRepository = new SprintRepository();

        Sprint sprint = mock(Sprint.class);
        Code projectCode = mock(Code.class);

        when(sprint.hasProjectCode(projectCode)).thenReturn(true);
        when(sprint.hasStatus(SprintStatus.OPEN)).thenReturn(false);

        sprintRepository.save(sprint);
        // ACT
        boolean result = sprintRepository.existsByProjectCodeAndStatus(projectCode, SprintStatus.OPEN);
        // ASSERT
        assertFalse(result);
    }

    /**
     * Method: existsByProjectCodeAndStatus(projectCode, status)
     * Scenario 3: checks if the list of sprints does not have any sprint with a given project code.
     * It should assert false.
     */
    @Test
    void ensureThatReturnsFalseIfNoInstancesOfSprintInTheListOfSprintsHaveTheProjectCodePassedAsParameter() {
        // ARRANGE
        SprintRepository sprintRepository = new SprintRepository();

        Sprint sprint = mock(Sprint.class);
        Code projectCode = mock(Code.class);

        when(sprint.hasProjectCode(projectCode)).thenReturn(false);
        when(sprint.hasStatus(SprintStatus.OPEN)).thenReturn(true);

        sprintRepository.save(sprint);
        // ACT
        boolean result = sprintRepository.existsByProjectCodeAndStatus(projectCode, SprintStatus.OPEN);
        // ASSERT
        assertFalse(result);
    }

    /**
     * Method: hasStatus
     * Scenario 1: the sprint has the given status, returns true
     */

    @Test
    void ensureThatSprintHasTheGivenStatus() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();

        Sprint sprint = mock(Sprint.class);
        sprintRepository.save(sprint);

        SprintId sprintId = new SprintId("p001", "s001");
        SprintStatus sprintStatus = SprintStatus.OPEN;
        when(sprint.hasSprintId(sprintId)).thenReturn(true);
        when(sprint.hasStatus(sprintStatus)).thenReturn(true);


        //Act
        boolean result = sprintRepository.hasStatus(sprintId, sprintStatus);

        //Assert
        assertTrue(result);
    }

    /**
     * Method: hasStatus
     * Scenario 2: the sprint hasn't the given status, returns false
     */

    @Test
    void ensureThatSprintHasntTheGivenStatus() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();

        Sprint sprint = mock(Sprint.class);
        sprintRepository.save(sprint);

        SprintId sprintId = new SprintId("p001", "s001");
        SprintStatus sprintStatus = SprintStatus.OPEN;
        when(sprint.hasSprintId(sprintId)).thenReturn(true);
        when(sprint.hasStatus(sprintStatus)).thenReturn(false);


        //Act
        boolean result = sprintRepository.hasStatus(sprintId, sprintStatus);

        //Assert
        assertFalse(result);
    }

    /**
     * Method: hasStatus
     * Scenario 3: the sprint is not present, returns false
     */

    @Test
    void ensureThatSprintHasNotTheGivenStatus_NoSprint() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();

        Sprint sprint = mock(Sprint.class);

        SprintId sprintId = new SprintId("p001", "s001");
        SprintStatus sprintStatus = SprintStatus.OPEN;
        when(sprint.hasSprintId(sprintId)).thenReturn(false);
        when(sprint.hasStatus(sprintStatus)).thenReturn(true);


        //Act
        boolean result = sprintRepository.hasStatus(sprintId, sprintStatus);

        //Assert
        assertFalse(result);
    }

    /**
     * Method: existsById(sprintId)
     * Scenario 1: checks if the list of sprints has any sprint with a given id.
     * It should assert true.
     */
    @Test
    void ensureThatReturnsTrue_ifAtLeastOneInstanceOfSprintInTheListOfSprints() {
        // ARRANGE
        SprintRepository sprintRepository = new SprintRepository();
        SprintId sprintId = new SprintId("P001", "S001");

        Sprint sprint = mock(Sprint.class);
        sprintRepository.save(sprint);
        when(sprint.hasSprintId(any())).thenReturn(true);

        // ACT
        boolean result = sprintRepository.existsById(sprintId);

        // ASSERT
        assertTrue(result);
    }

    /**
     * Method: existsByStatus(status)
     * Scenario 2: checks if the list of sprints does not have any sprint with a given status.
     * It should assert false.
     */
    @Test
    void ensureThatReturnsFalse_thereIsNoInstanceOfSprintInTheListOfSprints() {
        // ARRANGE
        SprintRepository sprintRepository = new SprintRepository();
        SprintId sprintId = new SprintId("P001", "S001");

        Sprint sprint = mock(Sprint.class);
        sprintRepository.save(sprint);
        when(sprint.hasSprintId(any())).thenReturn(false);

        // ACT
        boolean result = sprintRepository.existsById(sprintId);

        // ASSERT
        assertFalse(result);
    }

    /**
     * Method: hasUsId
     * Scenario 1: the sprint has user story, returns true
     */

    @Test
    void ensureThatSprintHasTheGivenUserStoryId() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();

        Sprint sprint = mock(Sprint.class);
        sprintRepository.save(sprint);
        SprintId sprintIdDouble = mock(SprintId.class);
        UsId usIdDouble = mock(UsId.class);
        when(sprint.hasSprintId(sprintIdDouble)).thenReturn(true);
        when(sprint.hasUserStory(usIdDouble)).thenReturn(true);


        //Act
        boolean result = sprintRepository.hasUsId(sprintIdDouble, usIdDouble);

        //Assert
        assertTrue(result);
    }

    /**
     * Method: hasUsId
     * Scenario 2: the sprint has not user story, returns false
     */

    @Test
    void ensureThatSprintHasNotTheGivenUserStoryId() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();

        Sprint sprint = mock(Sprint.class);
        sprintRepository.save(sprint);
        SprintId sprintIdDouble = mock(SprintId.class);
        UsId usIdDouble = mock(UsId.class);
        when(sprint.hasSprintId(sprintIdDouble)).thenReturn(true);
        when(sprint.hasUserStory(usIdDouble)).thenReturn(false);


        //Act
        boolean result = sprintRepository.hasUsId(sprintIdDouble, usIdDouble);

        //Assert
        assertFalse(result);
    }

    /**
     * Method: hasUsId
     * Scenario 3: the sprint is not in the repository, returns false
     */

    @Test
    void ensureThatSprintHasNotTheGivenUserStoryId_SprintNotInRepo() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();

        Sprint sprint = mock(Sprint.class);
        sprintRepository.save(sprint);
        SprintId sprintIdDouble = mock(SprintId.class);
        UsId usIdDouble = mock(UsId.class);
        when(sprint.hasSprintId(sprintIdDouble)).thenReturn(false);


        //Act
        boolean result = sprintRepository.hasUsId(sprintIdDouble, usIdDouble);

        //Assert
        assertFalse(result);
    }
    /**
     * Method: findByProjectCodeAndStatus
     * scenario 1: returns an empty sprint due to repository being empty
     */

    @Test
    void ensureSprintIsEmptyBecauseRepositoryIsEmpty_findByProjectCodeAndStatus() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        SprintStatus status= mock(SprintStatus.class);
        Code projectCode = mock(Code.class);

        Optional<Sprint> expected = Optional.empty();
        //Act
        Optional<Sprint> result = sprintRepository.findByProjectCodeAndStatus(projectCode,status);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 2: returns an empty sprint due to projectCode not matching
     */
    @Test
    void ensureSprintIsEmptyBecauseProjectCodeNotMatch() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        SprintStatus status= mock(SprintStatus.class);
        Code projectCode = mock(Code.class);
        Sprint sprintDouble = mock(Sprint.class);
        sprintRepository.save(sprintDouble);
        when(sprintDouble.hasProjectCode(projectCode)).thenReturn(false);
        Optional<Sprint> expected = Optional.empty();
        //Act
        Optional<Sprint> result = sprintRepository.findByProjectCodeAndStatus(projectCode,status);
        //Assert
        assertEquals(expected, result);
    }
    /**
     * scenario 3: returns an empty sprint due to sprintStatus not matching
     */
    @Test
    void ensureSprintIsEmptyBecauseSprintStatusNotMatch() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        SprintStatus status= mock(SprintStatus.class);
        Code projectCode = mock(Code.class);
        Sprint sprintDouble = mock(Sprint.class);
        sprintRepository.save(sprintDouble);
        when(sprintDouble.hasProjectCode(projectCode)).thenReturn(true);
        when(sprintDouble.hasStatus(status)).thenReturn(false);
        Optional<Sprint> expected = Optional.empty();
        //Act
        Optional<Sprint> result = sprintRepository.findByProjectCodeAndStatus(projectCode,status);
        //Assert
        assertEquals(expected, result);
    }
    /**
     * scenario 4: returns a sprint
     */
    @Test
    void ensureSprintIsRetrieved() {
        //Arrange
        SprintRepository sprintRepository = new SprintRepository();
        SprintStatus status= mock(SprintStatus.class);
        Code projectCode = mock(Code.class);
        Sprint sprintDouble = mock(Sprint.class);
        sprintRepository.save(sprintDouble);
        when(sprintDouble.hasProjectCode(projectCode)).thenReturn(true);
        when(sprintDouble.hasStatus(status)).thenReturn(true);
        Optional<Sprint> expected = Optional.of(sprintDouble);
        //Act
        Optional<Sprint> result = sprintRepository.findByProjectCodeAndStatus(projectCode,status);
        //Assert
        assertEquals(expected, result);
    }
}