package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.ProfileService;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CreateProfileController.class
)
class CreateProfileControllerTest {
    @InjectMocks
    CreateProfileController controller;
    @MockBean
    ProfileService service;

    /**
     * Method: createProfile()
     * Scenario 01: The profile is created successfully.
     * Expected return: TRUE.
     */
    @DisplayName("profile created successfully")
    @Test
    void ensureItReturnsTrueIfProfileIsCreatedSuccessfully() {
        //Arrange
        when(service.createProfile(any())).thenReturn(true);

        //Act
        boolean result = controller.createProfile("user");

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: The profile is not created because it already exists in the repository.
     * Expected return: AlreadyExistsInRepoException with "The profile already exists in
     * the repository." message.
     */
    @DisplayName("profile not created")
    @Test
    void ensureThatThrowsAnExceptionWhenProfileIsNotCreatedBecauseItAlreadyExistsInTheRepository() {
        //Arrange
        String expected = "The profile already exists in the repository.";
        when(service.createProfile(any())).thenThrow(new AlreadyExistsInRepoException(expected));

        //Act
        AlreadyExistsInRepoException result =
                assertThrows(AlreadyExistsInRepoException.class, () -> controller.createProfile("user"));

        //Assert
        assertEquals(expected, result.getMessage());
    }
}
