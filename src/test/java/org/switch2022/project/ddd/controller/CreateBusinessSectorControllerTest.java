package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.BusinessSectorService;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CreateBusinessSectorController.class
)
class CreateBusinessSectorControllerTest {

    @InjectMocks
    CreateBusinessSectorController controller;
    @MockBean
    BusinessSectorService service;

    /**
     * Method: createBusinessSector()
     * Scenario 01: The business sector is created successfully.
     * Expected return: TRUE.
     */
    @DisplayName("business sector created successfully")
    @Test
    void ensureItReturnsTrueIfBusinessSectorIsCreatedSuccessfully() {
        //Arrange
        when(service.createBusinessSector(any())).thenReturn(true);

        //Act
        boolean result = controller.createBusinessSector("test");

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
    void ensureThatThrowsAnExceptionWhenBusinessSectorIsNotCreatedBecauseItAlreadyExistsInTheRepository() {
        //Arrange
        String expected = "The business sector already exists in the repository.";
        when(service.createBusinessSector(any())).thenThrow(new AlreadyExistsInRepoException(expected));

        //Act
        AlreadyExistsInRepoException result =
                assertThrows(AlreadyExistsInRepoException.class, () -> controller.createBusinessSector("test"));

        //Assert
        assertEquals(expected, result.getMessage());
    }

}