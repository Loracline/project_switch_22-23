package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.datamodel_jpa.ProjectResourceJpa;
import org.switch2022.project.ddd.datamodel_jpa.SprintJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.SprintDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.model.sprint.SprintFactory;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;
import org.switch2022.project.ddd.infrastructure.jpa.ISprintJpaRepository;

import java.time.LocalDate;
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
    ISprintJpaRepository ISprintJpaRepository;
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

        when(ISprintJpaRepository.findById(sprintIdString)).thenReturn(sprintJpaOptional);
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

        when(ISprintJpaRepository.findById(sprintIdString)).thenReturn(sprintJpaOptional);
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
        when(ISprintJpaRepository.count()).thenReturn(expected);

        //Act
        long result = sprintRepositoryJpa.count();

        //Assert
        assertEquals(expected, result);
    }


    /**
     * Method : Save.
     * Scenario 1 : ensure that Sprint is saved.
     */
    @Test
    void ensureThatSprintIsSaved() {
        //Arrange
        Sprint sprintDouble = mock(Sprint.class);
        SprintJpa sprintJpa = mock(SprintJpa.class);
        when(sprintDomainDataAssembler.toData(sprintDouble)).thenReturn(sprintJpa);
        when(ISprintJpaRepository.save(sprintDouble)).thenReturn(true);

        //Act
        boolean result = sprintRepositoryJpa.save(sprintDouble);

        //Assert

        assertTrue(result);
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

        when(ISprintJpaRepository.findByProjectCode(any())).thenReturn(sprintJpas);

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

        when(ISprintJpaRepository.findByProjectCode(any())).thenReturn(sprintJpas);

        Code projectCode = mock(Code.class);

        //Act
        List<Sprint> result = sprintRepositoryJpa.findByProjectCode(projectCode);

        //Assert
        assertEquals(sprintByProject, result);

    }

    /**
     * Method: existsByStatus(status)
     * Scenario 1: checks if the repository of sprints has any sprint with a status matching the status passed as
     * parameter.
     * It should assert true.
     */
    @Test
    void ensureThatReturnsTrueIfAtLeastOneInstanceOfSprintInTheSprintRepositoryHasTheStatusPassedAsParameter() {
        // ARRANGE
        when(ISprintJpaRepository.existsByStatus(SprintStatus.OPEN.getStatus())).thenReturn(true);
        // ACT
        boolean result = sprintRepositoryJpa.existsByStatus(SprintStatus.OPEN);
        // ASSERT
        assertTrue(result);
    }

    /**
     * Method: existsByStatus(status)
     * Scenario 2: checks if the sprint repository does not have any sprint with a status matching the status passed
     * as argument.
     * It should throw an NotFoundInRepoException.
     */
    @Test
    void ensureThatThrowsAnExceptionIfNoInstancesOfSprintInTheSprintRepositoryHaveTheStatusPassedAsParameter() {
        //Arrange
        String expected = "There are no CLOSED sprints in the repository.";

        //Act
        NotFoundInRepoException result = assertThrows(NotFoundInRepoException.class,
                () -> sprintRepositoryJpa.existsByStatus(SprintStatus.CLOSED));

        //Assert
        assertEquals(expected, result.getMessage());
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
        SprintId sprintId = new SprintId("P001","S001");
        when(ISprintJpaRepository.existsById(any())).thenReturn(true);
        // ACT
        boolean result = sprintRepositoryJpa.existsById(sprintId);
        // ASSERT
        assertTrue(result);
    }
}