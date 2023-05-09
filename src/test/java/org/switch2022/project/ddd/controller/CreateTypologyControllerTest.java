package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.TypologyService;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CreateTypologyController.class
)
class CreateTypologyControllerTest {

    @InjectMocks
    CreateTypologyController controller;
    @MockBean
    TypologyService typologyService;

    /**
     * Method: createTypology()
     * Scenario 01: The typology is created successfully.
     * Expected return: TRUE.
     */
    @Test
    void ensureTypologyIsCreatedSuccessfully() {
        //Arrange
        when(typologyService.createTypology(any())).thenReturn(true);
        //Act
        boolean result = controller.createTypology("test");

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: The typology is not created.
     * Expected return: FALSE.
     */
    @Test
    void ensureTypologyIsCreatedUnsuccessfully() {
        //Arrange
        String expected = "The typology already exists in the repository.";

        when(typologyService.createTypology(any())).thenThrow(new AlreadyExistsInRepoException(expected));

        //Act
        AlreadyExistsInRepoException result =
                assertThrows(AlreadyExistsInRepoException.class, () -> controller.createTypology("typology01"));

        //Assert
        assertEquals(expected, result.getMessage());
    }

}