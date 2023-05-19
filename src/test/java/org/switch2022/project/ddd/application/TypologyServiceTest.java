package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.typology.IFactoryTypology;
import org.switch2022.project.ddd.domain.model.typology.ITypologyRepository;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = TypologyService.class
)
class TypologyServiceTest {
    @InjectMocks
    TypologyService typologyService;
    @MockBean
    ITypologyRepository typologyRepository;
    @MockBean
    IFactoryTypology factoryTypology;

    /**
     * Method: createTypology().
     * Scenario 01: The typology is created successfully.
     * Expected return: TRUE.
     */
    @Test
    void ensureTypologyIsCreatedSuccessfully() {
        //Arrange

        when(typologyRepository.add(any())).thenReturn(true);
        //Act
        boolean result = typologyService.createTypology("typology01");

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: The typology is not created.
     * Expected return: FALSE.
     */

    @Test
    void ensureTypologyIsCreatedInsuccessfully() {
        //Arrange
        String expected = "The typology already exists in the repository.";

        when(typologyRepository.add(any())).thenThrow(new AlreadyExistsInRepoException(expected));

        //Act
        AlreadyExistsInRepoException result =
                assertThrows(AlreadyExistsInRepoException.class, () -> typologyService.createTypology("typology01"));

        //Assert
        assertEquals(expected, result.getMessage());
    }

}