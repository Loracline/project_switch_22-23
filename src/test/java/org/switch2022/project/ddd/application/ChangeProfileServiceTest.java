package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.AccountFactory;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.domain.model.profile.IProfileRepository;
import org.switch2022.project.ddd.domain.model.profile.Profile;
import org.switch2022.project.ddd.domain.model.profile.ProfileFactory;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.domain.value_object.PhoneNumber;
import org.switch2022.project.ddd.domain.value_object.Photo;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ChangeProfileServiceTest {

    @InjectMocks
    private ChangeProfileService changeProfileService;
    @Mock
    private IAccountRepository accountRepository;
    @Mock
    private IProfileRepository profileRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    /**
     * Method changeProfile()
     *
     * Scenario 1: this test ensures that the account's profile is changed successfully
     * when given an email and a profile name.
     * The method should return true, indicating that the profile was successfully changed.
     * It also verifies that the correct methods of the AccountRepository and ProfileRepository} are called,
     * and that the returned account has the expected profile set.
     * Should return true.
     */

    @Test
    void ensureThatAccountProfileIsChangedSuccessfully() {

        // Arrange
        String profileName = "admin";
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);
        AccountFactory accountFactory = new AccountFactory();
        Account accountTest = accountFactory.create(name, email, phoneNumber, photo);

        when(accountRepository.getAccountByEmail(String.valueOf(email))).thenReturn(accountTest);
        ProfileFactory profileFactory = new ProfileFactory();
        Profile profile = profileFactory.createProfile(name, 1);
        when(profileRepository.getProfileByName(new Name(profileName))).thenReturn(profile);

        // Act
        boolean result = changeProfileService.changeProfile(String.valueOf(email), profileName);

        // Assert
        verify(accountRepository).getAccountByEmail(String.valueOf(email));
        verify(profileRepository).getProfileByName(new Name(profileName));
        assertTrue(result);
        assertEquals(profile, accountTest.getProfile());
    }


    /**
     * Scenario 2: this test verifies the attempting to change to an invalid profile.
     * Should return false and leave the profile unchanged in the account.
     */
    @Test
    void ensureThatAccountProfileIsNotChangedWhenGivenInvalidProfile() {
        // Arrange
        String profileName = "invalid_profile";
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);
        AccountFactory accountFactory = new AccountFactory();
        Account accountTest = accountFactory.create(name, email, phoneNumber, photo);

        when(accountRepository.getAccountByEmail(String.valueOf(email))).thenReturn(accountTest);
        when(profileRepository.getProfileByName(new Name(profileName))).thenReturn(null);

        // Act
        boolean result = changeProfileService.changeProfile(String.valueOf(email), profileName);

        // Assert
        verify(accountRepository).getAccountByEmail(String.valueOf(email));
        verify(profileRepository).getProfileByName(new Name(profileName));
        assertFalse(result);
        assertNull(accountTest.getProfile());
    }

    /**
     * Scenario 3: this test verifies the attempting to change profile with null inputs.
     * Should return false and leave the profile unchanged in the account.
     */
    @Test
    void ensureThatAccountProfileIsNotChangedWhenGivenNullInputs() {
        // Arrange
        String profileName = "admin";
        // Inputs nulls
        Name name = null;
        Email email = null;
        PhoneNumber phoneNumber = null;
        Photo photo = null;
        AccountFactory accountFactory = new AccountFactory();
        Account accountTest = accountFactory.create(name, email, phoneNumber, photo);

        // Act
        boolean result = changeProfileService.changeProfile(String.valueOf(email), profileName);

        // Assert
        assertFalse(result);
        assertNull(accountTest.getProfile());
    }

    /**
     * Scenario 4: this test verifies the attempting to change to a null profile.
     * Should return false and leave the profile unchanged in the account.
     */
    @Test
    void ensureThatAccountProfileIsNotChangedWhenGivenNullProfile() {
        // Arrange
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);
        AccountFactory accountFactory = new AccountFactory();
        Account accountTest = accountFactory.create(name, email, phoneNumber, photo);

        when(accountRepository.getAccountByEmail(String.valueOf(email))).thenReturn(accountTest);
        when(profileRepository.getProfileByName(null)).thenReturn(null);

        // Act & Assert
        assertThrows(InvalidInputException.class, () -> {
            changeProfileService.changeProfile(String.valueOf(email), null);
        });
        assertNull(accountTest.getProfile());
    }

    /**
     * Scenario 5: Tests that an InvalidInputException is thrown when the email parameter is null.
     */
    @Test
    void ensureThatAnExceptionsIsThrowWhenEmailIsNull() {
        assertThrows(InvalidInputException.class, () -> {
            changeProfileService.changeProfile(null, "admin");
        });
    }

    /**
     * Scenario 6: Tests that an InvalidInputException is thrown when the profileName parameter is null.
     */
    @Test
    void ensureThatAnExceptionsIsThrowWhenProfileNameIsNull() {
        assertThrows(InvalidInputException.class, () -> {
            changeProfileService.changeProfile("user@example.com", null);
        });
    }
}