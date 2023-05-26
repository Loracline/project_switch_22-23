package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.datamodel_jpa.ProfileJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.ProfileDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.profile.Profile;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;
import org.switch2022.project.ddd.infrastructure.jpa.IProfileJpaRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {ProfileJpaRepositoryTest.class})
class ProfileJpaRepositoryTest {

    @InjectMocks
    ProfileJpaRepository profileJpaRepository;
    @MockBean
    IProfileJpaRepository jpaRepository;
    @MockBean
    ProfileDomainDataAssembler assembler;

    /**
     * Method: save().
     * Scenario 01: make sure an instance of Profile is successfully added to the repository.
     * Expected return: TRUE.
     */
    @Test
    void ensureThatProfileIsAddedToRepositorySuccessfully() {
        //Arrange
        ProfileJpa profileJpa = mock(ProfileJpa.class);
        Profile profile = mock(Profile.class);
        when(assembler.toData(any())).thenReturn(profileJpa);
        when(jpaRepository.existsById(any())).thenReturn(false);
        //Act
        boolean result = profileJpaRepository.save(profile);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: check that an instance of Profile is not added to the repository
     * if it already exists in the repository.
     * Expected return: AlreadyExistsInRepoException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenProfileAlreadyExistsInRepo() {
        //Arrange
        ProfileJpa profileJpa = mock(ProfileJpa.class);
        Profile profile = mock(Profile.class);
        when(assembler.toData(any())).thenReturn(profileJpa);
        when(jpaRepository.existsById(any())).thenReturn(true);

        String expected = "The profile already exists in the repository.";
        //Act
        AlreadyExistsInRepoException result = assertThrows(AlreadyExistsInRepoException.class,
                () -> profileJpaRepository.save(profile));
        //Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * Method: count().
     * <p>
     * Scenario 01: return the number of profiles from a list
     * Expected result: the number of Profile instances in the list.
     */
    @Test
    void ensureThatTheNumberOfProfilesInTheRepoIsReturned() {
        //Arrange
        int expected = 2;
        when(jpaRepository.count()).thenReturn(2L);
        //Act
        int result = profileJpaRepository.count();
        //Assert
        assertEquals(expected, result);
    }


    /**
     * Method: getProfileByName()
     * Scenario 01: tests getting a profile by name.
     * Expects the method to return the correct profile.
     */
    @Test
    void ensureProfileIsSuccessfullyReturned() {
        //Arrange
        Name profileName = mock(Name.class);
        ProfileJpa profileJpaDouble = mock(ProfileJpa.class);
        Profile expected = mock(Profile.class);
        when(jpaRepository.findByProfileName(profileName)).thenReturn(profileJpaDouble);
        when(assembler.toDomain(profileJpaDouble)).thenReturn(expected);

        //Act
        Profile result = profileJpaRepository.findByProfileName(profileName);

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Scenario 02: tests getting a profile by name and there is no profile in the repository with the given name.
     * Expected return: NotFoundInRepoException.
     */
    @Test
    void ensureAnExceptionIsThrownIfNoProfileIsFoundWithGivenName() {
        //Arrange
        Name profileName = mock(Name.class);
        String expected = "There is no profile with the given name in the repository.";

        //Act
        NotFoundInRepoException result = assertThrows(NotFoundInRepoException.class,
                () -> profileJpaRepository.findByProfileName(profileName));

        //Assert
        assertEquals(expected, result.getMessage());
    }
}