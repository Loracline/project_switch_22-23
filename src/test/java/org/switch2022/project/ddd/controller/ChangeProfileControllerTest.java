package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.ChangeProfileService;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ChangeProfileController.class
)
public class ChangeProfileControllerTest {
    @InjectMocks
    ChangeProfileController controller;
    @MockBean
    ChangeProfileService service;

    /**
     * Method: changeProfile()
     * Validations
     * Scenario 1: profile is not changed because email is null.
     */
    @Test
    void ensureThatThrowsAnExceptionWhenEmailIsNull() {
        //ARRANGE
        String email = null;
        String profileName = "Manager";
        String expectedMessage = "The email must not be null";

        //ACT
        InvalidInputException result =
                assertThrows(InvalidInputException.class, () -> controller.changeProfile(email,
                        profileName));

        //ASSERT
        assertEquals(expectedMessage, result.getMessage());
    }

    /**
     * Method: changeProfile()
     * Validations
     * Scenario 2: profile is not changed because email is blank.
     */
    @Test
    void ensureThatThrowsAnExceptionWhenEmailIsBlank() {
        //ARRANGE
        String email = " ";
        String profileName = "Manager";
        String expectedMessage = "The email must not be blank";

        //ACT
        InvalidInputException result =
                assertThrows(InvalidInputException.class, () -> controller.changeProfile(email,
                        profileName));

        //ASSERT
        assertEquals(expectedMessage, result.getMessage());
    }

    /**
     * Method: changeProfile()
     * Validations
     * Scenario 3: profile is not changed because email is empty.
     */
    @Test
    void ensureThatThrowsAnExceptionWhenEmailIsEmpty() {
        //ARRANGE
        String email = "";
        String profileName = "Manager";
        String expectedMessage = "The email must not be empty";

        //ACT
        InvalidInputException result =
                assertThrows(InvalidInputException.class, () -> controller.changeProfile(email,
                        profileName));

        //ASSERT
        assertEquals(expectedMessage, result.getMessage());
    }

    /**
     * Method: changeProfile()
     * Validations
     * Scenario 1: profile is not changed because profileName is null.
     */
    @Test
    void ensureThatThrowsAnExceptionWhenProfileNameIsNull() {
        //ARRANGE
        String email = "isep@ipp.pt";
        String profileName = null;
        String expectedMessage = "The profileName must not be null";

        //ACT
        InvalidInputException result =
                assertThrows(InvalidInputException.class, () -> controller.changeProfile(email,
                        profileName));

        //ASSERT
        assertEquals(expectedMessage, result.getMessage());
    }

    /**
     * Method: changeProfile()
     * Validations
     * Scenario 2: profile is not changed because profileName is blank.
     */
    @Test
    void ensureThatThrowsAnExceptionWhenProfileNameIsBlank() {
        //ARRANGE
        String email = "isep@ipp.pt";
        String profileName = " ";
        String expectedMessage = "The profileName must not be blank";

        //ACT
        InvalidInputException result =
                assertThrows(InvalidInputException.class, () -> controller.changeProfile(email,
                        profileName));

        //ASSERT
        assertEquals(expectedMessage, result.getMessage());
    }

    /**
     * Method: changeProfile()
     * Validations
     * Scenario 3: profile is not changed because profileName is empty.
     */
    @Test
    void ensureThatThrowsAnExceptionWhenProfileNameIsEmpty() {
        //ARRANGE
        String email = "isep@ipp.pt";
        String profileName = "";
        String expectedMessage = "The profileName must not be empty";

        //ACT
        InvalidInputException result =
                assertThrows(InvalidInputException.class, () -> controller.changeProfile(email,
                        profileName));

        //ASSERT
        assertEquals(expectedMessage, result.getMessage());
    }

    /**
     * Method: changeProfile()
     * Scenario 01: The profile is successfully changed.
     * Expected return: TRUE.
     */

    @Test
    void ensureItReturnsTrueIfProfileIsSuccessfullyChanged() {
        //ARRANGE
        String email = "isep@ipp.pt";
        String profileName = "Manager";
        when(service.changeProfile(email, profileName)).thenReturn(true);

        //ACT
        boolean result = controller.changeProfile(email, profileName);

        //ASSERT
        assertTrue(result);
    }

    /**
     * Scenario 02: The profile is not changed because it already exists in the repository.
     * Expected return: FALSE.
     */

    @Test
    void ensureItReturnsFalseIfProfileIsNotSuccessfullyChanged() {
        //ARRANGE
        String email = "isep@ipp.pt";
        String profileName = "Manager";
        when(service.changeProfile(email, profileName)).thenReturn(false);

        //ACT
        boolean result = controller.changeProfile(email, profileName);

        //ASSERT
        assertFalse(result);
    }
}
