package org.switch2022.project.ddd.webcontrollers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.ddd.application.TypologyService;
import org.switch2022.project.ddd.dto.TypologyCreationDto;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        classes = TypologyWebControllerTest.class)
class TypologyWebControllerTest {
    @InjectMocks
    TypologyWebController controller;

    @MockBean
    TypologyService service;

    /**
     * Method createTypology()
     * Scenario 01: typology created successfully.
     * Expected return: ResponseEntity status code is checked to
     * ensure it is equal to 201 (HttpStatus.CREATED).
     */
    @Test
    void ensureThatTypologyIsCreated() {
        //Arrange

        TypologyCreationDto dtoDouble = mock(TypologyCreationDto.class);
        when(service.createTypology(any())).thenReturn(true);
        //Act
        ResponseEntity<Object> responseEntity = controller.createTypology(dtoDouble);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);
    }
    /**
     * Scenario 01: typology created successfully.
     * Expected return: ResponseEntity status code is checked to
     * ensure it is equal to 400 (HttpStatus.BAD_REQUEST).
     */
    @Test
    void ensureThatResourceIsNotCreated_ExceptionIsThrow() {
        //Arrange
        TypologyCreationDto dtoDouble = null;
        String expected = "The typology already exists in the repository.";
        when(service.createTypology(any())).thenThrow(new AlreadyExistsInRepoException(expected));

        //Act
        ResponseEntity<Object> responseEntity = controller.createTypology(dtoDouble);
        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
}