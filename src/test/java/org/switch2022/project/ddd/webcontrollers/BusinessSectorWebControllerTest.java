package org.switch2022.project.ddd.webcontrollers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.switch2022.project.ddd.application.BusinessSectorService;
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSector;
import org.switch2022.project.ddd.domain.value_object.BusinessSectorId;
import org.switch2022.project.ddd.domain.value_object.TaxId;
import org.switch2022.project.ddd.dto.BusinessSectorCreationDto;
import org.switch2022.project.ddd.dto.BusinessSectorDto;
import org.switch2022.project.ddd.dto.CustomerDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        classes = BusinessSectorWebController.class)
class BusinessSectorWebControllerTest {
    @InjectMocks
    BusinessSectorWebController controller;
    @MockBean
    BusinessSectorService service;


    /**
     * Method: createBusinessSector()
     * Scenario 1: ensure that a business sector is created successfully.
     * The returned ResponseEntity status code is checked to
     * ensure it is equal to 201 (HttpStatus.CREATED).
     */
    @Test
    void ensureThatBusinessSectorIsCreatedSuccessfully() {
        //Arrange
        BusinessSectorCreationDto dto = mock(BusinessSectorCreationDto.class);
        when(service.createBusinessSector(any())).thenReturn(true);

        //Act
        ResponseEntity<Object> responseEntity = controller.createBusinessSector(dto);

        //Assert
        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
    }

    /**
     * Method listAllBusinessSectors().
     * Scenario 1: ensure that a list of BusinessSectorDto is returned.
     */
    @Test
    void ensureThatAListOfBusinessSectorDtoIsReturned() {
        //ARRANGE
        List<BusinessSectorDto> expected = new ArrayList<>();
        BusinessSectorDto businessSectorDtoDouble = mock(BusinessSectorDto.class);
        expected.add(businessSectorDtoDouble);
        when(service.requestAllBusinessSectors()).thenReturn(expected);

        //ACT
        ResponseEntity<List<BusinessSectorDto>> responseEntity =
                controller.listAllBusinessSectors();

        //ASSERT
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<BusinessSectorDto> actual = responseEntity.getBody();
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    /**
     * Scenario 2: ensure that an empty list is returned when no business sectors are found.
     */
    @Test
    void ensureThatAnEmptyListIsReturnedWhenNoBusinessSectorsAreFound() {
        //Arrange
        List<BusinessSectorDto> expected = new ArrayList<>();
        when(service.requestAllBusinessSectors()).thenReturn(expected);

        //Act
        ResponseEntity<List<BusinessSectorDto>> responseEntity = controller.listAllBusinessSectors();

        //Assert
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        List<BusinessSectorDto> actual = responseEntity.getBody();
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    /**
     * Scenario 3: ensure that an error is returned when the service throws an exception.
     */
    @Test
    void ensureThatAnErrorIsReturnedWhenServiceThrowsException() {
        //Arrange
        when(service.requestAllBusinessSectors()).thenThrow(new RuntimeException());

        //Act and Assert
        assertThrows(RuntimeException.class, () -> controller.listAllBusinessSectors());
    }

    /**
     * METHOD getByIdNumber()
     */
    @DisplayName("Business Sector DTO")
    @Test
    void ensureCustomerDtoIsRetrievedWhenBusinessSectorExistsInDatabase() {
        // Arrange
        String businessSectorId = "bs001";

        BusinessSectorDto expected = new BusinessSectorDto("Technology", businessSectorId);

        when(service.getByIdNumber(any())).thenReturn(Optional.of(expected));

        // Act
        ResponseEntity<BusinessSectorDto> response = controller.getByIdNumber(businessSectorId);

        // Assert
        assertEquals(expected, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @DisplayName("Not found")
    @Test
    void ensureCustomerNotFoundWhenDoesntExistInDatabase() {
        // Arrange
        String businessSectorId = "bs007";

        when(service.getByIdNumber(any())).thenReturn(Optional.empty());

        // Act
        ResponseEntity<BusinessSectorDto> response = controller.getByIdNumber(businessSectorId);

        // Assert
        assertNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}







