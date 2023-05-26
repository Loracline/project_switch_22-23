package org.switch2022.project.ddd.infrastructure;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {TypologyJpaRepositoryTest.class})
class TypologyJpaRepositoryTest {
    @InjectMocks
    TypologyJpaRepository typologyJpaRepository;

    @MockBean
    ITypologyJpaRepository jpaRepository;

    @MockBean
    TypologyDomainDataAssembler assembler;

    /**
     * Method: save().
     * Scenario 01: make sure an instance of tupology is successfully added to the repository.
     * Expected return: true.
     */
    @Test
    void ensureThatTypologyIsAddedToRepositorySuccessfully() {
        //Arrange
        TypologyJpa typologyJpa = mock(TypologyJpa.class);
        Typology typology = mock(Typology.class);
        when(assembler.toData(any())).thenReturn(typologyJpa);
        when(jpaRepository.existsById(any())).thenReturn(false);
        //Act
        boolean result = typologyJpaRepository.save(typology);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: check that an instance of typology is not added to the repository
     * if it already exists in the repository.
     * Expected return: AlreadyExistsInRepoException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenProfileAlreadyExistsInRepo() {
        //Arrange
        TypologyJpa typologyJpa = mock(TypologyJpa.class);
        Typology typology = mock(Typology.class);
        when(assembler.toData(any())).thenReturn(typologyJpa);
        when(jpaRepository.existsById(any())).thenReturn(true);

        String expected = "The typology already exists in the repository.";
        //Act
        AlreadyExistsInRepoException result = assertThrows(AlreadyExistsInRepoException.class,
                () -> typologyJpaRepository.save(typology));
        //Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * Method: count().
     * Scenario 01: return the number of typologies from a list
     * Expected result: the number of typology instances in the list.
     */
    @Test
    void ensureThatTheNumberOfTypologiesInTheRepoIsReturned() {
        //Arrange
        int expected = 2;
        when(jpaRepository.count()).thenReturn(2L);
        //Act
        int result = typologyJpaRepository.count();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: findTypologyIdByTypologyName().
     * Scenario 02: return the typologyId of the typology related to the given TypologyName.
     * Expected result: the typologyId of the typology.
     */
    @Test
    void ensurefindTypologyIdByTypologyName() {
        //Arrange
        String typologyName = "new typology";
        String expected = "T001";

        when(jpaRepository.findTypologyIdByTypologyName(typologyName))
                .thenReturn(expected);
        //Act
        String result = typologyJpaRepository.findTypologyIdByTypologyName(typologyName);

        //Assert
        assertEquals(expected, result);

    }

    /**
     * Scenario 02: tests getting a profile by name and there is no profile in the repository with the given name.
     * Expected return: NotFoundInRepoException.
     */
    @Test
    void ensureAnExceptionIsThrownIfNoProfileIsFoundWithGivenName() {
        //Arrange
        String typologyName = "new typology";
        String expected = "Typology with this name does not exist in the Repository.";

        //Act
        NotFoundInRepoException result = assertThrows(NotFoundInRepoException.class,
                () -> typologyJpaRepository.findTypologyIdByTypologyName(typologyName));

        //Assert
        assertEquals(expected, result.getMessage());
    }
}