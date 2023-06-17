package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.profile.IProfileFactory;
import org.switch2022.project.ddd.domain.model.profile.IProfileRepository;
import org.switch2022.project.ddd.domain.model.profile.Profile;
import org.switch2022.project.ddd.dto.ProfileCreationDto;
import org.switch2022.project.ddd.dto.mapper.ProfileDto;
import org.switch2022.project.ddd.dto.mapper.ProfileMapper;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ProfileService.class
)
class ProfileServiceTest {
    @InjectMocks
    ProfileService service;
    @Qualifier("profile_jpa")
    @MockBean
    IProfileRepository repository;
    @MockBean
    IProfileFactory factory;
    @MockBean
    ProfileMapper mapper;

    /**
     * Method: createProfile().
     * Scenario 01: The profile was successfully created.
     * It should assert true.
     */

    @Test
    void ensureThatProfileWasSuccessfullyCreated() {
        //Arrange
        ProfileCreationDto dto = new ProfileCreationDto("Test profile");
        when(repository.save(any())).thenReturn(true);
        //Act
        boolean result = service.createProfile(dto);

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
        ProfileCreationDto dto = new ProfileCreationDto("Test profile");
        String expected = "This profile already exists.";

        when(repository.save(any())).thenThrow(new AlreadyExistsInRepoException(expected));

        //Act
        AlreadyExistsInRepoException result =
                assertThrows(AlreadyExistsInRepoException.class, () -> service.createProfile(
                        dto));

        //Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * METHOD getProfile()
     */
    @Test
    void ensureProfileIsRetrievedSuccessfully() {
        // Arrange
        ProfileDto profileDtoDouble = mock(ProfileDto.class);
        Profile profileDouble = mock(Profile.class);

        Optional<ProfileDto> expected = Optional.of(profileDtoDouble);

        when(repository.findByNameOfProfile(any())).thenReturn(Optional.ofNullable(profileDouble));
        when(mapper.profileToProfileDto(profileDouble)).thenReturn(profileDtoDouble);

        // Act
        Optional<ProfileDto> result = service.getProfile("batman");

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureProfileIsNotRetrievedBecauseDoesntExist() {
        // Arrange
        Optional<Profile> optional = Optional.empty();
        Optional<ProfileDto> expected = Optional.empty();

        when(repository.findByNameOfProfile(any())).thenReturn(optional);

        // Act
        Optional<ProfileDto> result = service.getProfile("batman");

        // Assert
        assertEquals(expected, result);
    }
}
