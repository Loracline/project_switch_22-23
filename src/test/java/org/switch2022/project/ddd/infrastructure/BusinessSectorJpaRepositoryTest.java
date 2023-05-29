package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.datamodel_jpa.BusinessSectorJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.BusinessSectorDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSector;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;
import org.switch2022.project.ddd.infrastructure.jpa.IBusinessSectorJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BusinessSectorJpaRepository.class
)
class BusinessSectorJpaRepositoryTest {

    @InjectMocks
    BusinessSectorJpaRepository repository;

    @MockBean
    IBusinessSectorJpaRepository crudRepository;

    @MockBean
    BusinessSectorDomainDataAssembler assembler;

    /**
     * METHOD save()
     * Scenario 1: ensure that a business sector is added successfully.
     * The repository should add the business sector and return true.
     */
    @Test
    void ensureBusinessSectorIsAddedSuccessfully() {
        // Arrange
        BusinessSector businessSector = mock(BusinessSector.class);
        BusinessSectorJpa businessSectorJpa = mock(BusinessSectorJpa.class);
        when(assembler.toData(businessSector)).thenReturn(businessSectorJpa);
        when(businessSectorJpa.getIdNumber()).thenReturn("123");

        when(crudRepository.existsById("123")).thenReturn(false);

        // Act
        boolean result = repository.save(businessSector);

        // Assert
        assertTrue(result);
    }


    /**
     * Scenario 2: ensure that an exception is thrown when a business sector already exists in the repository.
     * Assert that the thrown exception has the expected message.
     */
    @Test
    void ensureExceptionIsThrownWhenBusinessSectorExists() {
        // Arrange
        String expectedMessage = "The business sector already exists in the repository.";
        BusinessSector businessSector = mock(BusinessSector.class);
        BusinessSectorJpa businessSectorJpa = mock(BusinessSectorJpa.class);

        when(assembler.toData(businessSector)).thenReturn(businessSectorJpa);
        when(businessSectorJpa.getIdNumber()).thenReturn("123");
        when(crudRepository.existsById("123")).thenReturn(true);

        // Act and Assert
        AlreadyExistsInRepoException exception = assertThrows(
                AlreadyExistsInRepoException.class,
                () -> repository.save(businessSector));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }


    /**
     * Method: count().
     * Scenario 1: ensure that the number of business sectors in the repository is returned correctly.
     * Should return the number of business sectors in the repository.
     */
    @Test
    void ensureThatTheNumberOfBusinessSectorsInTheRepoIsReturned() {
        // Arrange
        int expected = 4;
        when(crudRepository.count()).thenReturn(4L);

        // Act
        int result = repository.count();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Test case to ensure that the getSize() method returns zero when the repository is empty.
     * Should return zero.
     */
    @Test
    void ensureGetSizeReturnsZeroWhenRepositoryIsEmpty() {
        // Arrange
        int expected = 0;
        when(crudRepository.count()).thenReturn(0L);

        // Act
        int result = repository.count();

        // Assert
        assertEquals(expected, result);
    }


    /**
     * Method getBusinessSectorIdByName().
     * Scenario 1: ensure that the ID of a business sector is successfully retrieved by its name.
     */

    @Test
    void ensureBusinessSectorIdIsRetrievedSuccessfully() {
        // Arrange
        String expected = "BS001";
        String businessSectorName = "Technology";

        BusinessSectorJpa businessSectorJpaDouble = mock(BusinessSectorJpa.class);
        Optional<BusinessSectorJpa> optionalDouble = Optional.of(businessSectorJpaDouble);

        when(crudRepository.findByName(businessSectorName)).thenReturn(optionalDouble);
        when(optionalDouble.get().getName()).thenReturn(expected);

        // Act
        String result = repository.getBusinessSectorIdByName(businessSectorName);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: ensure that a NotFoundInRepoException is thrown when a business sector does not
     * exist in the repository when searching by name.
     */
    @Test
    void ensureExceptionIsThrownWhenBusinessSectorDoesNotExistSearchingByName() {
        // Arrange
        String expected = "There is no business sector with the given name in the repository.";
        String businessSectorName = "Technology";

        Optional<BusinessSectorJpa> businessSector = Optional.empty();

        when(crudRepository.findByName(businessSectorName)).thenReturn(businessSector);

        // Act
        NotFoundInRepoException result = assertThrows(
                NotFoundInRepoException.class,
                () -> repository.getBusinessSectorIdByName(businessSectorName));

        // Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * Method findAll()
     * Scenario 1: Test case to ensure that an empty list is returned when converting an
     * empty list of BusinessSectors.
     */
    @Test
    void ensureAllBusinessSectorsInRepositoryAreReturnedInList() {
        //Arrange
        BusinessSector businessSectorOne = mock(BusinessSector.class);
        BusinessSector businessSectorTwo = mock(BusinessSector.class);

        List<BusinessSector> expected = new ArrayList<>();
        expected.add(businessSectorOne);
        expected.add(businessSectorTwo);

        BusinessSectorJpa businessSectorJpaDoubleOne = mock(BusinessSectorJpa.class);
        BusinessSectorJpa businessSectorJpaDoubleTwo = mock(BusinessSectorJpa.class);
        List<BusinessSectorJpa> businessSectorsJpa = new ArrayList<>();
        businessSectorsJpa.add(businessSectorJpaDoubleOne);
        businessSectorsJpa.add(businessSectorJpaDoubleTwo);

        when(crudRepository.findAll()).thenReturn(businessSectorsJpa);
        when(assembler.toDomain(businessSectorJpaDoubleOne)).thenReturn(businessSectorOne);
        when(assembler.toDomain(businessSectorJpaDoubleTwo)).thenReturn(businessSectorTwo);

        //Act
        List<BusinessSector> result = repository.findAll();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: ensure that an empty list is returned when the repository is empty.
     */
    @Test
    void ensureReturnsEmptyListWhenEmptyRepository() {
        // Arrange
        List<BusinessSector> expected = new ArrayList<>();

        List<BusinessSectorJpa> businessSectorsJpa = new ArrayList<>();

        when(crudRepository.findAll()).thenReturn(businessSectorsJpa);

        // Act
        List<BusinessSector> result = repository.findAll();

        // Assert
        assertEquals(expected, result);
    }
}