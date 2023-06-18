package org.switch2022.project.ddd.webcontrollers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.ddd.application.TypologyService;
import org.switch2022.project.ddd.dto.TypologyCreationDto;
import org.switch2022.project.ddd.dto.TypologyDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
     * Scenario 02: typology is not created.
     * Expected return: ResponseEntity status code is checked to
     * ensure it is equal to 400 (HttpStatus.BAD_REQUEST).
     */
    @Test
    void ensureThatResourceIsNotCreated() {
        //Arrange
        TypologyCreationDto dtoDouble = mock(TypologyCreationDto.class);

        when(service.createTypology(any())).thenReturn(false);

        //Act
        ResponseEntity<Object> responseEntity = controller.createTypology(dtoDouble);
        //Assert
        assertEquals(HttpStatus.CONFLICT,responseEntity.getStatusCode());
    }
    /**
     * Method: listAllProjects()
     * Scenario 1: returns a list of TypologiesDto successfully.
     */

    @Test
    void ensureThatAListOfProjectsDtoIsReturned() {
        // Arrange
        List<TypologyDto> expected = new ArrayList<>();
        TypologyDto typologyDtoDouble = mock(TypologyDto.class);
        expected.add(typologyDtoDouble);
        when(service.requestAllTypologies()).thenReturn(expected);

        // Act
        ResponseEntity<List<TypologyDto>> responseEntity =
                controller.listAllTypologies();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<TypologyDto> actual = responseEntity.getBody();
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    /**
     * Scenario 2: returns an empty list when there are no typologies.
     */
    @Test
    void ensureThatAnEmptyListIsReturnedWhenThereAreNoProjects() {
        // Arrange
        when(service.requestAllTypologies()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<TypologyDto>> responseEntity =
                controller.listAllTypologies();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<TypologyDto> actual = responseEntity.getBody();
        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }

    @Test
    void ensureThatTypologyIsReturned() {
        // Arrange
        String typologyName = "typology name";
        TypologyDto dtoDouble = mock(TypologyDto.class);
        when(service.getByTypologyName(typologyName)).thenReturn(dtoDouble);

        // Act
        ResponseEntity<TypologyDto> responseEntity = controller.getByTypologyName(typologyName);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);
        assertEquals(dtoDouble, responseEntity.getBody());
    }
}