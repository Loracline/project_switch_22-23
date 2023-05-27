package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.typology.ITypologyFactory;
import org.switch2022.project.ddd.domain.model.typology.ITypologyRepository;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = TypologyServiceTest.class
)
class TypologyServiceTest {
    @InjectMocks
    TypologyService typologyService;
    @MockBean
    ITypologyRepository typologyRepository;
    @MockBean
    ITypologyFactory factoryTypology;

    /**
     * Method: createTypology().
     * Scenario 01: The typology is created successfully.
     * Expected return: TRUE.
     */
    @DisplayName("Typology created successfully")
    @Test
    void ensureTypologyIsCreatedSuccessfully() {
        //Arrange

        when(typologyRepository.save(any())).thenReturn(true);
        //Act
        boolean result = typologyService.createTypology("typology01");

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: The typology is not created.
     * Expected return:  AlreadyExistsInRepoException with "The typology already exists in the repository." .
     */
    @DisplayName("Typology not created")
    @Test
    void ensureTypologyIsCreatedInsuccessfully() {
        //Arrange
        String expected = "The typology already exists in the repository.";

        when(typologyRepository.save(any())).thenThrow(new AlreadyExistsInRepoException(expected));

        //Act
        AlreadyExistsInRepoException result =
                assertThrows(AlreadyExistsInRepoException.class, () -> typologyService.createTypology("typology01"));

        //Assert
        assertEquals(expected, result.getMessage());
    }
}