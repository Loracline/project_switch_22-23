package org.switch2022.project.ddd.webcontrollers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.ddd.application.BusinessSectorService;
import org.switch2022.project.ddd.dto.BusinessSectorCreationDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
     * Scenario 01: Test case to ensure that a business sector is created successfully.
     * The returned ResponseEntity status code is checked to
     * ensure it is equal to 201 (HttpStatus.CREATED).
     */
    @Test
    void ensureThatBusinessSectorIsCreated() {
        //Arrange
        BusinessSectorCreationDto dtoDouble = mock(BusinessSectorCreationDto.class);
        when(service.createBusinessSector(any())).thenReturn(true);

        //Act
        ResponseEntity<Object> responseEntity = controller.createBusinessSector(dtoDouble);
        //Assert
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

    /**
     * Scenario 2: Test case to ensure that a business sector is not created when the DTO is null.
     * The returned ResponseEntity status code is checked to
     * ensure it is equal to 400 (HttpStatus.BAD_REQUEST).
     */
    @Test
    void ensureThatBusinessSectorIsNotCreatedWhenDtoIsNull() {
        //Arrange
        BusinessSectorCreationDto dtoNull = null;
        when(service.createBusinessSector(any())).thenReturn(false);

        //Act
        ResponseEntity<Object> responseEntity = controller.createBusinessSector(dtoNull);
        //Assert
        assertEquals(400,responseEntity.getStatusCodeValue());
    }

    /**
     * Scenario 3: Test case to ensure that a business sector is not created when the service throws an exception.
     * The returned ResponseEntity status code is checked to ensure it is equal to 400 (HttpStatus.BAD_REQUEST).
     */
    @Test
    void ensureThatBusinessSectorIsNotCreatedWhenServiceThrowsException() {
        //Arrange
        BusinessSectorCreationDto dto = mock(BusinessSectorCreationDto.class);
        when(service.createBusinessSector(any())).thenThrow(new RuntimeException());

        //Act
        ResponseEntity<Object> responseEntity = controller.createBusinessSector(dto);
        //Assert
        assertEquals(400,responseEntity.getStatusCodeValue());
    }
}







