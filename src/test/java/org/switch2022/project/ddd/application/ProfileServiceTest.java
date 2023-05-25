package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.profile.IProfileFactory;
import org.switch2022.project.ddd.domain.model.profile.IProfileRepository;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ProfileService.class
)
class ProfileServiceTest {
    @InjectMocks
    ProfileService service;
    @MockBean
    IProfileRepository repository;
    @MockBean
    IProfileFactory factory;

    /**
     * Method: createProfile().
     * Scenario 01: The profile was successfully created.
     * It should assert true.
     */

    @Test
    void ensureThatProfileWasSuccessfullyCreated() {
        //Arrange

        when(repository.save(any())).thenReturn(true);
        //Act
        boolean result = service.createProfile("John");

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: The profile is not created because it already exists in the repository.
     * It should throw a AlreadyExistsInRepoException.
     */

    @Test
    void ensureThanAnExceptionIsThrownWhenTheProfileIsNotCreatedBecauseItAlreadyExists() {
        //Arrange
        String expected = "This profile already exists.";

        when(repository.save(any())).thenThrow(new AlreadyExistsInRepoException(expected));

        //Act
        AlreadyExistsInRepoException result =
                assertThrows(AlreadyExistsInRepoException.class, () -> service.createProfile(
                        "John"));

        //Assert
        assertEquals(expected, result.getMessage());
    }
}
