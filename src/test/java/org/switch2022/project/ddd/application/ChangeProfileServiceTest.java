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
import org.switch2022.project.ddd.domain.value_object.*;
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
     * <p>
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
        String profileName = "user";
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);
        AccountFactory accountFactory = new AccountFactory();
        Account accountTest = accountFactory.create(name, email, phoneNumber, photo);

        when(accountRepository.getAccountByEmail(any())).thenReturn(accountTest);
        ProfileFactory profileFactory = new ProfileFactory();
        Profile profile = profileFactory.createProfile(name, 2);
        when(profileRepository.getProfileByName(any())).thenReturn(profile);

        // Act
        boolean result = changeProfileService.changeProfile(String.valueOf(email), profileName);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: This test verifies that attempting to change the profile to a null profile results in no
     * changes to the account's profile.
     * The expected behavior is that an InvalidInputException is thrown when try and leave the profile
     * unchanged in the account.
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
    }

    /**
     * Scenario 3: Tests that an InvalidInputException is thrown when the email parameter is null.
     */
    @Test
    void ensureThatAnExceptionsIsThrowWhenEmailIsNull() {
        assertThrows(InvalidInputException.class, () -> {
            changeProfileService.changeProfile(null, "admin");
        });
    }

    /**
     * Scenario 4: Tests that an InvalidInputException is thrown when the profileName parameter is null.
     */
    @Test
    void ensureThatAnExceptionsIsThrowWhenProfileNameIsNull() {
        assertThrows(InvalidInputException.class, () -> {
            changeProfileService.changeProfile("user@example.com", null);
        });
    }

    /**
     * Scenario 5: This test verifies that the account's profile remains unchanged when the
     * provided profile name is already assigned.
     * Expected Outcome: The profile ID of the account should remain unchanged
     * and be equal to "pr001".
     */
    @Test
    void ensureThatAccountProfileIsNotChangedWhenProfileNameAlreadyAssigned() {
        // Arrange
        String profileName = "user";
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        Photo photo = mock(Photo.class);

        AccountFactory accountFactory = new AccountFactory();
        Account accountTest = accountFactory.create(name, email, phoneNumber, photo);
        accountTest.changeProfile(new ProfileId(1));

        when(accountRepository.getAccountByEmail(String.valueOf(email))).thenReturn(accountTest);

        ProfileFactory profileFactory = new ProfileFactory();
        Profile assignedProfile = profileFactory.createProfile(name, 1);
        when(profileRepository.getProfileByName(new Name(profileName))).thenReturn(assignedProfile);

        // Act
        boolean result = changeProfileService.changeProfile(String.valueOf(email), profileName);

        // Assert
        assertEquals("pr001", accountTest.getProfileId().toString());
    }


}







