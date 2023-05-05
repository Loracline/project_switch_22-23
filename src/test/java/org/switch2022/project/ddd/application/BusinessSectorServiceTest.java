package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.business_sector.IBusinessSectorFactory;
import org.switch2022.project.ddd.domain.model.business_sector.IBusinessSectorRepository;
import org.switch2022.project.ddd.domain.model.typology.IFactoryTypology;
import org.switch2022.project.ddd.domain.model.typology.ITypologyRepository;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BusinessSectorServiceTest.class
)
class BusinessSectorServiceTest {
    @InjectMocks
    BusinessSectorService service;
    @MockBean
    IBusinessSectorRepository repository;
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

        when(repository.add(any())).thenReturn(true);
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

        when(repository.add(any())).thenThrow(new AlreadyExistsInRepoException(expected));

        //Act
        AlreadyExistsInRepoException result =
                assertThrows(AlreadyExistsInRepoException.class, () -> service.createBusinessSector("test"));

        //Assert
        assertEquals(expected, result.getMessage());
    }


}