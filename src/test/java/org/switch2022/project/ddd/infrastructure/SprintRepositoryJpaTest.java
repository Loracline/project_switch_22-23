package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.datamodel.JPA.SprintJpa;
import org.switch2022.project.ddd.datamodel.JPA.assemblers.SprintDataAssembler;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.SprintId;
import org.switch2022.project.ddd.infrastructure.JPA.ISprintJpaRepository;

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
    SprintDataAssembler sprintDataAssembler;


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
        SprintJpa sprintJpa = mock(SprintJpa.class);
        List<SprintJpa> sprintJpas = new ArrayList<>();
        sprintJpas.add(sprintJpa);
        when(ISprintJpaRepository.findAll()).thenReturn(sprintJpas);


        String sprintIdString = "P001_S002";
        SprintId sprintId = new SprintId("P001", "S001");
        when(ISprintJpaRepository.existsById(sprintIdString)).thenReturn(false);

        when(sprintDataAssembler.toDomain(sprintJpa)).thenReturn(null);

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
        String sprintIdString = "p001_s001";
        SprintId sprintId = new SprintId("P001", "S001");
        List<SprintJpa> sprintJpas = new ArrayList<>();
        sprintJpas.add(sprintJpa);

        when(ISprintJpaRepository.findAll()).thenReturn(sprintJpas);
        when(ISprintJpaRepository.existsById(sprintIdString)).thenReturn(true);

        Sprint sprintDouble = mock(Sprint.class);
        when(sprintDataAssembler.toDomain(sprintJpa)).thenReturn(sprintDouble);

        Optional<Sprint> expected = Optional.of(sprintDouble);
        //Act
        Optional<Sprint> result = sprintRepositoryJpa.findById(sprintId);
        //Assert
        assertEquals(expected, result);
    }


    /**
     * Method : Count
     * Scenario 1 : ensure it returns the list size.
     *
     */

    @Test
    void ensureTheListSizeIsReturned() {
        //Arrange
        long expected = 2L;
        when(ISprintJpaRepository.count()).thenReturn(expected);

        //Act
        long result = sprintRepositoryJpa.count();

        //Assert
        assertEquals(expected , result);
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
        when(sprintDataAssembler.toData(sprintDouble)).thenReturn(sprintJpa);
        String sprintIdString = "p001_s001";
        when(sprintJpa.getSprintId()).thenReturn(sprintIdString);
        when(ISprintJpaRepository.existsById(sprintIdString)).thenReturn(false);
        when(ISprintJpaRepository.save(sprintDouble)).thenReturn(true);

        //Act
        boolean result = sprintRepositoryJpa.save(sprintDouble);

        //Assert

        assertTrue(result);
    }

    /**
     * Method : Save.
     * Scenario 2 : ensure Sprint is not saved.
     */
    @Test
    void ensureThatSprintIsNotSaved() {
        //Arrange
        Sprint sprintDouble = mock(Sprint.class);
        SprintJpa sprintJpa = mock(SprintJpa.class);
        when(sprintDataAssembler.toData(sprintDouble)).thenReturn(sprintJpa);
        String sprintIdString = "p001_s001";
        when(sprintJpa.getSprintId()).thenReturn(sprintIdString);
        when(ISprintJpaRepository.existsById(sprintIdString)).thenReturn(true);

        //Act
        boolean result = sprintRepositoryJpa.save(sprintDouble);

        //Assert
        assertFalse(result);
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
        List<SprintJpa> sprintJpas = Arrays.asList(sprintJpa,sprintJpaDouble);

        when( ISprintJpaRepository.findByProjectCode(any())).thenReturn(sprintJpas);

        Code projectCode = mock(Code.class);
        when(sprintDataAssembler.toDomain(sprintJpa)).thenReturn(sprint);
        when(sprintDataAssembler.toDomain(sprintJpaDouble)).thenReturn(sprintDouble);


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

        when( ISprintJpaRepository.findByProjectCode(any())).thenReturn(sprintJpas);

        Code projectCode = mock(Code.class);

        //Act
        List<Sprint> result = sprintRepositoryJpa.findByProjectCode(projectCode);

        //Assert
        assertEquals(sprintByProject, result);

    }
}