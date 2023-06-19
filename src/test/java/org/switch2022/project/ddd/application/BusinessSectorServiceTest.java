package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSector;
import org.switch2022.project.ddd.domain.model.business_sector.IBusinessSectorFactory;
import org.switch2022.project.ddd.domain.model.business_sector.IBusinessSectorRepository;
import org.switch2022.project.ddd.domain.value_object.BusinessSectorId;
import org.switch2022.project.ddd.dto.BusinessSectorDto;
import org.switch2022.project.ddd.dto.mapper.BusinessSectorMapper;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BusinessSectorService.class
)
class BusinessSectorServiceTest {
    @InjectMocks
    BusinessSectorService service;
    @MockBean
    @Qualifier("businessSector_jpa")
    IBusinessSectorRepository repository;
    @MockBean
    BusinessSectorMapper businessSectorMapper;
    @MockBean
    IBusinessSectorFactory factory;

    /**
     * Method: createBusinessSector().
     * Scenario 01: The business sector is created successfully.
     * Expected return: TRUE.
     */
    @DisplayName("business sector created successfully")
    @Test
    void ensureThatReturnsTrueIfBusinessSectorIsCreatedSuccessfully() {
        //Arrange

        when(repository.save(any())).thenReturn(true);
        //Act
        boolean result = service.createBusinessSector("test");

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: The business sector is not created because it already exists in the repository.
     * Expected return: AlreadyExistsInRepoException with "The business sector already exists in the repository."
     * message.
     */
    @DisplayName("business sector not created")
    @Test
    void ensureThanAnExceptionIsThrownWhenTheBusinessSectorIsNotCreatedBecauseItAlreadyExistsInTheRepository() {
        //Arrange
        String expected = "The business sector already exists in the repository.";

        when(repository.save(any())).thenThrow(new AlreadyExistsInRepoException(expected));

        //Act
        AlreadyExistsInRepoException result =
                assertThrows(AlreadyExistsInRepoException.class, () -> service.createBusinessSector("test"));

        //Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * Method requestAllBusinessSectors().
     * Test case to ensure that all business sectors are returned in a DTO list.
     */
    @Test
    void ensureAllBusinessSectorsAreReturnedInDtoList() {
        // Arrange
        BusinessSector businessSectorOne = mock(BusinessSector.class);
        BusinessSector businessSectorTwo = mock(BusinessSector.class);
        List<BusinessSector> businessSectors = new ArrayList<>();
        businessSectors.add(businessSectorOne);
        businessSectors.add(businessSectorTwo);
        when(repository.findAll()).thenReturn(businessSectors);
        BusinessSectorDto businessSectorDtoOne = mock(BusinessSectorDto.class);
        BusinessSectorDto businessSectorDtoTwo = mock(BusinessSectorDto.class);
        when(businessSectorMapper.businessSectorToDto(businessSectorOne)).thenReturn(businessSectorDtoOne);
        when(businessSectorMapper.businessSectorToDto(businessSectorTwo)).thenReturn(businessSectorDtoTwo);
        List<BusinessSectorDto> expected = new ArrayList<>();
        expected.add(businessSectorDtoOne);
        expected.add(businessSectorDtoTwo);

        // Act
        List<BusinessSectorDto> result = service.requestAllBusinessSectors();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: ensure that an empty list is returned when no business sectors are found.
     */
    @Test
    void ensureEmptyListIsReturnedWhenNoBusinessSectorsAreFound() {
        // Arrange
        when(repository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<BusinessSectorDto> result = service.requestAllBusinessSectors();

        // Assert
        assertTrue(result.isEmpty());
    }

    /**
     * Method getBusinessSectorById()
     * Scenario 1: Retrieves a BusinessSectorDto object corresponding to the provided ID.
     *
     * @returnAn Optional containing the corresponding BusinessSectorDto, if found;
     * otherwise, an empty Optional
     */
    @Test
    void ensureBusinessSectorIsRetrievedSuccessfully() {
        // Arrange
        BusinessSectorId businessSectorIdDouble = mock(BusinessSectorId.class);
        BusinessSectorDto businessSectorDtoDouble = mock(BusinessSectorDto.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);

        Optional<BusinessSector> optional = Optional.of(businessSectorDouble);
        Optional<BusinessSectorDto> expected = Optional.of(businessSectorDtoDouble);

        when(repository.findByIdNumber(any())).thenReturn(optional);
        when(businessSectorMapper.businessSectorToDto(businessSectorDouble)).thenReturn(businessSectorDtoDouble);

        // Act
        Optional<BusinessSectorDto> result = service.getByIdNumber(businessSectorIdDouble);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Method getBusinessSectorById()
     * Scenario 2: Fails to retrieve a BusinessSectorDto object because it doesn't exist.
     *
     * @return An empty Optional because the BusinessSectorDto corresponding to the
     * provided ID is not found.
     */
    @Test
    void ensureBusinessSectorIsNotRetrievedBecauseDoesntExist() {
        // Arrange
        BusinessSectorId businessSectorIdDouble = mock(BusinessSectorId.class);

        Optional<BusinessSector> optional = Optional.empty();
        Optional<BusinessSectorDto> expected = Optional.empty();

        when(repository.findByIdNumber(any())).thenReturn(optional);

        // Act
        Optional<BusinessSectorDto> result = service.getByIdNumber(businessSectorIdDouble);

        // Assert
        assertEquals(expected, result);
    }
}

