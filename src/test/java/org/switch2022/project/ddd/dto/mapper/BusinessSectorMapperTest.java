package org.switch2022.project.ddd.dto.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSector;
import org.switch2022.project.ddd.dto.BusinessSectorDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BusinessSectorMapperTest {

    /**
     * Method: businessSectorToDto().
     * Scenario 1: ensure that the business sector is successfully converted to a BusinessSectorDto.
     */
    @Test
    void ensureThatItReturnsABusinessSectorDtoSuccessfully() {
        //Arrange
        BusinessSectorMapper mapper = new BusinessSectorMapper();
        BusinessSector businessSector = mock(BusinessSector.class);
        String businessSectorName = "Finance";
        String businessSectorId = "BS001";
        when(businessSector.getBusinessSectorName()).thenReturn(businessSectorName);
        when(businessSector.getBusinessSectorId()).thenReturn("BS001");
        BusinessSectorDto expected = new BusinessSectorDto(businessSectorName, businessSectorId);

        //Act
        BusinessSectorDto result = mapper.businessSectorToDto(businessSector);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: ensure that an exception is thrown when trying to convert a null
     * BusinessSector to a BusinessSectorDto.
     */
    @Test
    void ensureThatItThrowsAnExceptionWhenTryingToConvertANullBusinessSectorToDto() {
        // Arrange
        BusinessSectorMapper mapper = new BusinessSectorMapper();
        BusinessSector businessSector = null;

        // Act and Assert
        assertThrows(NullPointerException.class,
                () -> mapper.businessSectorToDto(businessSector));
    }

    /**
     * Method: listBusinessSectorsToDto().
     * Scenario 1: ensure that a list of BusinessSectorDtos is returned successfully.
     */
    @Test
    void ensureThatItReturnsAListOfBusinessSectorDtosSuccessfully() {
        // Arrange
        BusinessSectorMapper mapper = new BusinessSectorMapper();
        List<BusinessSector> businessSectors = new ArrayList<>();
        BusinessSector businessSector1 = mock(BusinessSector.class);
        when(businessSector1.getBusinessSectorName()).thenReturn("Finance");
        when(businessSector1.getBusinessSectorId()).thenReturn("BS001");
        businessSectors.add(businessSector1);
        BusinessSector businessSector2 = mock(BusinessSector.class);
        when(businessSector2.getBusinessSectorName()).thenReturn("Technology");
        when(businessSector2.getBusinessSectorId()).thenReturn("BS002");
        businessSectors.add(businessSector2);
        List<BusinessSectorDto> expected = new ArrayList<>();
        expected.add(new BusinessSectorDto("Finance", "BS001"));
        expected.add(new BusinessSectorDto("Technology", "BS002"));

        // Act
        List<BusinessSectorDto> result = mapper.listBusinessSectorsToDto(businessSectors);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: ensure that an empty list is returned when converting an empty list of BusinessSectors.
     */
    @Test
    void ensureThatItReturnsAnEmptyListWhenConvertingAnEmptyListOfBusinessSectors() {
        // Arrange
        BusinessSectorMapper mapper = new BusinessSectorMapper();
        List<BusinessSector> businessSectors = new ArrayList<>();

        // Act
        List<BusinessSectorDto> result = mapper.listBusinessSectorsToDto(businessSectors);

        // Assert
        assertTrue(result.isEmpty());
    }
}