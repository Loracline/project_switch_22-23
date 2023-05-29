package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.datamodel_jpa.TypologyJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.TypologyDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.typology.Typology;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;
import org.switch2022.project.ddd.infrastructure.jpa.ITypologyJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {TypologyJpaRepositoryTest.class})
class TypologyJpaRepositoryTest {
    @InjectMocks
    TypologyJpaRepository repository;

    @MockBean
    ITypologyJpaRepository crudRepository;

    @MockBean
    TypologyDomainDataAssembler assembler;

    /**
     * Method: save().
     * Scenario 01: make sure an instance of tupology is successfully added to the repository.
     * Expected return: true.
     */
    @DisplayName("Typology is saved")
    @Test
    void ensureThatTypologyIsAddedToRepositorySuccessfully() {
        //Arrange
        TypologyJpa typologyJpa = mock(TypologyJpa.class);
        Typology typology = mock(Typology.class);
        when(assembler.toData(any())).thenReturn(typologyJpa);
        when(crudRepository.existsById(any())).thenReturn(false);
        //Act
        boolean result = repository.save(typology);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: check that an instance of typology is not added to the repository
     * if it already exists in the repository.
     * Expected return: AlreadyExistsInRepoException.
     */
    @DisplayName("Typology already exists")
    @Test
    void ensureThatAnExceptionIsThrownWhenProfileAlreadyExistsInRepo() {
        //Arrange
        TypologyJpa typologyJpa = mock(TypologyJpa.class);
        Typology typology = mock(Typology.class);
        when(assembler.toData(any())).thenReturn(typologyJpa);
        when(crudRepository.existsById(any())).thenReturn(true);

        String expected = "The typology already exists in the repository.";
        //Act
        AlreadyExistsInRepoException result = assertThrows(AlreadyExistsInRepoException.class,
                () -> repository.save(typology));
        //Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * Method: count().
     * Scenario 01: return the number of typologies from a list
     * Expected result: the number of typology instances in the list.
     */
    @DisplayName("Number of typology in the list")
    @Test
    void ensureThatTheNumberOfTypologiesInTheRepoIsReturned() {
        //Arrange
        int expected = 2;
        when(crudRepository.count()).thenReturn(2L);
        //Act
        int result = repository.count();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: findTypologyIdByTypologyName().
     * Scenario 02: return the typologyId of the typology related to the given TypologyName.
     * Expected result: the typologyId of the typology.
     */
    @DisplayName("Typology ID is retrieved")
    @Test
    void ensurefindTypologyIdByTypologyName() {
        //Arrange
        String typologyName = "new typology";
        String expected = "001";

        TypologyJpa typologyJpaDouble = mock(TypologyJpa.class);
        Optional<TypologyJpa> optionalDouble = Optional.of(typologyJpaDouble);

        when(crudRepository.findByTypologyName(typologyName)).thenReturn(optionalDouble);
        when(optionalDouble.get().getTypologyId()).thenReturn(expected);
        //Act
        String result = repository.findTypologyIdByTypologyName(typologyName);

        //Assert
        assertEquals(expected, result);

    }

    /**
     * Scenario 02: tests getting a profile by name and there is no profile in the repository with the given name.
     * Expected return: NotFoundInRepoException.
     */
    @DisplayName("Typology ID not found")
    @Test
    void ensureAnExceptionIsThrownIfNoProfileIsFoundWithGivenName() {
        //Arrange
        String typologyName = "new typology";
        String expected = "Typology with this name does not exist in the Repository.";

        //Act
        NotFoundInRepoException result = assertThrows(NotFoundInRepoException.class,
                () -> repository.findTypologyIdByTypologyName(typologyName));

        //Assert
        assertEquals(expected, result.getMessage());
    }
    /**
     * Method: findAll().
     * Scenario 01: returns the list of all typologies
     * Expected result: return the list of all repository typologies.
     */
    @DisplayName("List of all typologies")
    @Test
    void ensureListIsRetrievedSuccessfully() {
        //Arrange
        Typology typologyDouble = mock(Typology.class);
        Typology typologyDoubleTwo = mock(Typology.class);
        List<Typology> expected = new ArrayList<>();
        expected.add(typologyDouble);
        expected.add(typologyDoubleTwo);

        TypologyJpa typologyJpaDouble = mock(TypologyJpa.class);
        TypologyJpa typologyJpaDoubleTwo = mock(TypologyJpa.class);
        List<TypologyJpa> typologiesJpa = new ArrayList<>();
        typologiesJpa.add(typologyJpaDouble);
        typologiesJpa.add(typologyJpaDoubleTwo);

        when(crudRepository.findAll()).thenReturn(typologiesJpa);
        when(assembler.toDomain(typologyJpaDouble)).thenReturn(typologyDouble);
        when(assembler.toDomain(typologyJpaDoubleTwo)).thenReturn(typologyDoubleTwo);
        //Act
        List<Typology> result = repository.findAll();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 02: empty list is retrieved successfully.
     * Expected result: an empty list.
     */
    @DisplayName("Empty typology list")
    @Test
    void ensureEmptyListIsRetrievedSuccessfully() {
        //Arrange
        List<Typology> expected = new ArrayList<>();
        List<TypologyJpa> projectsJpa = new ArrayList<>();

        when(crudRepository.findAll()).thenReturn(projectsJpa);

        //Act
        List<Typology> result = repository.findAll();
        //Assert
        assertEquals(expected, result);
    }

}