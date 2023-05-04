package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.typology.IFactoryTypology;
import org.switch2022.project.ddd.domain.model.typology.ITypologyRepository;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
     * Scenario 01: The typology is not created.
     * Expected return: FALSE.
     */

    @Test
    void ensureTypologyIsCreatedInsuccessfully() {
        //Arrange

        when(typologyRepository.add(any())).thenReturn(false);
        //Act
        boolean result = typologyService.createTypology("typology01");

        //Assert
        assertFalse(result);
    }

}