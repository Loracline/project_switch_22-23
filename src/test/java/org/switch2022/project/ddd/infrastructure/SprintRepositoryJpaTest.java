package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.datamodel_jpa.SprintJpa;
import org.switch2022.project.ddd.datamodel_jpa.UserStoryInSprintJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.SprintDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.SprintId;
import org.switch2022.project.ddd.domain.value_object.SprintStatus;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.infrastructure.jpa.ISprintJpaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SprintRepositoryJpa.class
)
class SprintRepositoryJpaTest {

    @InjectMocks
    SprintRepositoryJpa sprintRepositoryJpa;
    @MockBean
    ISprintJpaRepository iSprintJpaRepository;
    @MockBean
    SprintDomainDataAssembler sprintDomainDataAssembler;


    /**
     * METHOD: getSprintById
     * scenario 01: returns an empty optional because the repository is empty
     */
    @Test
    void ensureSprintIsEmptyBecauseRepositoryIsEmpty() {
        //Arrange
        SprintId sprintId = new SprintId("P001", "S001");

        Optional<Sprint> expected = Optional.empty();
        //Act
        Optional<Sprint> result = sprintRepositoryJpa.findById(sprintId);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 02: returns an empty optional because the id doesn't match any sprintID
     */
    @Test
    void ensureSprintIsEmptyBecauseRepositoryDoesntHaveSprintId() {
        //Arrange
        Optional<SprintJpa> sprintJpaOptional = Optional.ofNullable(null);

        String sprintIdString = "P001_S002";
        SprintId sprintId = new SprintId("P001", "S001");

        when(iSprintJpaRepository.findById(sprintIdString)).thenReturn(sprintJpaOptional);
        when(sprintDomainDataAssembler.toDomain(any())).thenReturn(null);

        Optional<Sprint> expected = Optional.empty();
        //Act
        Optional<Sprint> result = sprintRepositoryJpa.findById(sprintId);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 03: returns an optional with a sprint
     */
    @Test
    void ensureReturnsSprintSuccessfully() {
        //Arrange
        SprintJpa sprintJpa = mock(SprintJpa.class);
        Optional<SprintJpa> sprintJpaOptional = Optional.of(sprintJpa);
        String sprintIdString = "p001_s001";
        SprintId sprintId = new SprintId("P001", "S001");

        when(iSprintJpaRepository.findById(sprintIdString)).thenReturn(sprintJpaOptional);
        Sprint sprintDouble = mock(Sprint.class);
        when(sprintDomainDataAssembler.toDomain(sprintJpa)).thenReturn(sprintDouble);

        Optional<Sprint> expected = Optional.of(sprintDouble);
        //Act
        Optional<Sprint> result = sprintRepositoryJpa.findById(sprintId);
        //Assert
        assertEquals(expected, result);
    }


    /**
     * Method : Count
     * Scenario 1 : ensure it returns the list size.
     */

    @Test
    void ensureTheListSizeIsReturned() {
        //Arrange
        long expected = 2L;
        when(iSprintJpaRepository.count()).thenReturn(expected);

        //Act
        long result = sprintRepositoryJpa.count();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method : findAllByProjectCode
     * Scenario 1 : ensure that a list with all sprints with a given projectCode is retrieved.
     */

    @Test
    void ensureThatAListWithAllSprintsWithGivenProjectCodeIsRetrieved() {
        //Arrange
        Sprint sprintDouble = mock(Sprint.class);
        Sprint sprint = mock(Sprint.class);
        List<Sprint> sprintByProject = Arrays.asList(sprint, sprintDouble);
        SprintJpa sprintJpaDouble = mock(SprintJpa.class);
        SprintJpa sprintJpa = mock(SprintJpa.class);
        List<SprintJpa> sprintJpas = Arrays.asList(sprintJpa, sprintJpaDouble);

        when(iSprintJpaRepository.findByProjectCode(any())).thenReturn(sprintJpas);

        Code projectCode = mock(Code.class);
        when(sprintDomainDataAssembler.toDomain(sprintJpa)).thenReturn(sprint);
        when(sprintDomainDataAssembler.toDomain(sprintJpaDouble)).thenReturn(sprintDouble);


        //Act
        List<Sprint> result = sprintRepositoryJpa.findByProjectCode(projectCode);


        //Assert
        assertEquals(sprintByProject, result);

    }

    /**
     * Scenario 2 : ensure that an empty list is retrieved because there are no sprints with given project code.
     */

    @Test
    void ensureThatAnEmptyListIsRetrievedBecauseThereAreNoSprintsWithGivenProjectCode() {
        //Arrange
        List<Sprint> sprintByProject = new ArrayList<>();
        List<SprintJpa> sprintJpas = new ArrayList<>();

        when(iSprintJpaRepository.findByProjectCode(any())).thenReturn(sprintJpas);

        Code projectCode = mock(Code.class);

        //Act
        List<Sprint> result = sprintRepositoryJpa.findByProjectCode(projectCode);

        //Assert
        assertEquals(sprintByProject, result);

    }

    /**
     * Method: existsByProjectCodeAndStatus(projectCode, status)
     * Scenario 1: checks if the repository of sprints has any sprint with a given project and given a status matching
     * the project and status passed as parameters.
     * It should assert true.
     */
    @Test
    void ensureThatReturnsTrueIfAtLeastOneInstanceOfSprintInTheSprintRepositoryHasTheProjectCodeAndStatusPassedAsParameters() {
        // ARRANGE
        Code projectCode = mock(Code.class);
        when(projectCode.getCode()).thenReturn("P001");
        when(iSprintJpaRepository.existsByProjectCodeAndStatus("P001", SprintStatus.OPEN.getStatus())).thenReturn(true);
        // ACT
        boolean result = sprintRepositoryJpa.existsByProjectCodeAndStatus(projectCode, SprintStatus.OPEN);
        // ASSERT
        assertTrue(result);
    }

    /**
     * Method: existsByProjectCodeAndStatus(projectCode, status)
     * Scenario 2: checks if the sprint repository does not have any sprint with a project code and a status matching
     * the project code and status passed as arguments.
     * It should throw an NotFoundInRepoException.
     */
    @Test
    void ensureThatReturnsFalseIfThereAreNoInstancesOfSprintInTheSprintRepositoryWithTheProjectCodeAndStatusPassedAsParameter() {
        //Arrange
        Code projectCode = mock(Code.class);
        when(projectCode.getCode()).thenReturn("P001");
        when(iSprintJpaRepository.existsByProjectCodeAndStatus("P001", SprintStatus.OPEN.getStatus())).thenReturn(false);

        //Act
        boolean result = sprintRepositoryJpa.existsByProjectCodeAndStatus(projectCode, SprintStatus.OPEN);

        //Assert
        assertFalse(result);
    }

    /**
     * Method: hasStatus
     * Scenario 1: the sprint has the given status, returns true
     */

    @Test
    void ensureThatSprintHasTheGivenStatus() {
        //Arrange
        SprintId sprintId = new SprintId("p001", "s001");
        SprintStatus status = SprintStatus.OPEN;
        when(iSprintJpaRepository.existsBySprintIdAndStatus(sprintId.getSprintId(), status.getStatus()))
                .thenReturn(true);

        //Act
        boolean result = sprintRepositoryJpa.hasStatus(sprintId, status);

        //Assert
        assertTrue(result);
    }

    /**
     * Method: hasStatus
     * Scenario 2: the sprint has not the given status, returns false
     */

    @Test
    void ensureThatSprintHasNotTheGivenStatus() {
        //Arrange
        SprintId sprintId = new SprintId("p001", "s001");
        SprintStatus status = SprintStatus.OPEN;
        when(iSprintJpaRepository.existsBySprintIdAndStatus(sprintId.getSprintId(), status.getStatus()))
                .thenReturn(false);

        //Act
        boolean result = sprintRepositoryJpa.hasStatus(sprintId, status);

        //Assert
        assertFalse(result);
    }

    /**
     * Method: existsByStatus(status)
     * Scenario 1: checks if the repository of sprints has any sprint with a status matching the status passed as
     * parameter.
     * It should assert true.
     */
    @Test
    void ensureThatReturnsTrue_ifAtLeastOneInstanceOfSprintInTheSprintRepositoryHasTheIdPassedAsParameter() {
        // ARRANGE
        SprintId sprintId = new SprintId("P001", "S001");
        when(iSprintJpaRepository.existsById(any())).thenReturn(true);
        // ACT
        boolean result = sprintRepositoryJpa.existsById(sprintId);
        // ASSERT
        assertTrue(result);
    }

    /**
     * Method: hasUsId
     * Scenario 1: the sprint has the usId, returns true
     */

    @Test
    void ensureThatSprintHasTheGivenUsId() {
        //Arrange
        SprintId sprintId = mock(SprintId.class);
        when(sprintId.getSprintId()).thenReturn("p001_s001");
        UsId usId = mock(UsId.class);
        when(usId.getUserStoryId()).thenReturn("p001_us001");
        SprintJpa sprintJpa= mock(SprintJpa.class);
        when(iSprintJpaRepository.findById(sprintId.getSprintId())).thenReturn(Optional.ofNullable(sprintJpa));
        UserStoryInSprintJpa userStoryInSprint = mock(UserStoryInSprintJpa.class);
        List<UserStoryInSprintJpa> userStoryInSprintJpas = new ArrayList<>();
        userStoryInSprintJpas.add(userStoryInSprint);
        when(userStoryInSprint.getUsId()).thenReturn("p001_us001");
        when(sprintJpa.getUserStoriesInSprint()).thenReturn(userStoryInSprintJpas);

        //Act
        boolean result = sprintRepositoryJpa.hasUsId(sprintId, usId);

        //Assert
        assertTrue(result);
    }

    /**
     * Method: hasUsId
     * Scenario 2: the sprint has the usId, returns false
     */

    @Test
    void ensureThatSprintHasNotTheGivenUsId() {
        //Arrange
        SprintId sprintId = mock(SprintId.class);
        when(sprintId.getSprintId()).thenReturn("p001_s001");
        UsId usId = mock(UsId.class);
        when(usId.getUserStoryId()).thenReturn("p001_us001");
        SprintJpa sprintJpa= mock(SprintJpa.class);
        when(iSprintJpaRepository.findById(sprintId.getSprintId())).thenReturn(Optional.ofNullable(sprintJpa));
        UserStoryInSprintJpa userStoryInSprint = mock(UserStoryInSprintJpa.class);
        List<UserStoryInSprintJpa> userStoryInSprintJpas = new ArrayList<>();
        userStoryInSprintJpas.add(userStoryInSprint);
        when(userStoryInSprint.getUsId()).thenReturn("p001_us002");
        when(sprintJpa.getUserStoriesInSprint()).thenReturn(userStoryInSprintJpas);
        //Act
        boolean result = sprintRepositoryJpa.hasUsId(sprintId, usId);

        //Assert
        assertFalse(result);
    }

    /**
     * Method: findByProjectCodeAndStatus
     * scenario 1: returns a sprint
     */
    @Test
    void ensureThatSprintIsRetrievedSuccessfully() {
        //Arrange
        SprintJpa sprintJpa = mock(SprintJpa.class);
        Optional<SprintJpa> sprintJpaOptional = Optional.of(sprintJpa);
        Code projectCode = mock(Code.class);
        SprintStatus status = mock(SprintStatus.class);

        when(iSprintJpaRepository.findByProjectCodeAndStatus(any(),any())).thenReturn(sprintJpaOptional);
        Sprint sprintDouble = mock(Sprint.class);
        when(sprintDomainDataAssembler.toDomain(sprintJpa)).thenReturn(sprintDouble);

        Optional<Sprint> expected = Optional.of(sprintDouble);
        //Act
        Optional<Sprint> result = sprintRepositoryJpa.findByProjectCodeAndStatus(projectCode,status);
        //Assert
        assertEquals(expected, result);
    }
    /**
     * scenario 2: returns an  empty sprint
     */
    @Test
    void ensureThatSprintIsNotRetrievedSuccessfully() {
        //Arrange
        SprintJpa sprintJpa = mock(SprintJpa.class);
        Optional<SprintJpa> sprintJpaOptional = Optional.empty();
        Code projectCode = mock(Code.class);
        SprintStatus status = mock(SprintStatus.class);

        when(iSprintJpaRepository.findByProjectCodeAndStatus(any(),any())).thenReturn(sprintJpaOptional);
        Sprint sprintDouble = mock(Sprint.class);
        when(sprintDomainDataAssembler.toDomain(sprintJpa)).thenReturn(sprintDouble);

        Optional<Sprint> expected = Optional.empty();
        //Act
        Optional<Sprint> result = sprintRepositoryJpa.findByProjectCodeAndStatus(projectCode,status);
        //Assert
        assertEquals(expected, result);
    }


}