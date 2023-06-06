package org.switch2022.project.ddd.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class ErrorMessageTest {
    @Test
    public void ensureStatusCodeIsReturned() {
        int expectedStatusCode = 200;
        ErrorMessage errorMessage = new ErrorMessage(expectedStatusCode, LocalTime.now(),
                "OK");
        int actualStatusCode = errorMessage.getStatusCode();
        Assertions.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Test
    public void ensureTimestampIsReturned() {
        LocalTime expectedTimestamp = LocalTime.now();
        ErrorMessage errorMessage = new ErrorMessage(200, expectedTimestamp, "OK");
        LocalTime actualTimestamp = errorMessage.getTimestamp();
        Assertions.assertEquals(expectedTimestamp, actualTimestamp);
    }

    @Test
    public void ensureMessageIsReturned() {
        String expectedMessage = "INTERNAL SERVER ERROR";
        ErrorMessage errorMessage = new ErrorMessage(500, LocalTime.now(), expectedMessage);
        String actualMessage = errorMessage.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void ensureErrorMessageInitializationIsReturned() {
        int expectedStatusCode = 404;
        LocalTime expectedTimestamp = LocalTime.now();
        String expectedMessage = "PAGE NOT FOUND";

        ErrorMessage errorMessage = new ErrorMessage(expectedStatusCode, expectedTimestamp,
                expectedMessage);

        Assertions.assertEquals(expectedStatusCode, errorMessage.getStatusCode());
        Assertions.assertEquals(expectedTimestamp, errorMessage.getTimestamp());
        Assertions.assertEquals(expectedMessage, errorMessage.getMessage());
    }
}
