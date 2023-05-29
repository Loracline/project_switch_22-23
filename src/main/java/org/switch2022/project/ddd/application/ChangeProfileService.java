package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.domain.model.profile.IProfileRepository;
import org.switch2022.project.ddd.domain.model.profile.Profile;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.domain.value_object.ProfileId;
import org.switch2022.project.ddd.exceptions.InvalidInputException;



/**
 * Class ChangeProfileService acts as an intermediary between the
 * controller and the business logic underlying the "US003 - As
 * Administrator, I want to change the profile of a user account."
 */

@Service
public class ChangeProfileService {
    @Qualifier("profile_jpa")
    @Autowired
    private IProfileRepository profileRepository;
    @Autowired
    @Qualifier("account_jpa")
    private IAccountRepository accountRepository;

    /**
     * This method changes the profile of a user account based on the email and the
     * new profile name provided.
     *
     * @param email       the email of the account to change the profile.
     * @param profileName the name of the new profile to assign to the account.
     * @return true if the profile was successfully changed, false otherwise.
     * @throws InvalidInputException if either email or profileName is null.
     */
    public boolean changeProfile(String email, String profileName) {
        if (email == null || profileName == null) {
            throw new InvalidInputException("Email and profile name cannot be null");
        }
        Account account = accountRepository.findAccountByEmail(email);
        Profile profile = profileRepository.findByProfileName(new Name(profileName));

        String[] parts = profile.getProfileId().split("prf");
        int profileNumber = Integer.parseInt(parts[1]);

        ProfileId profileId = new ProfileId(profileNumber);
        account.changeProfile(profileId);

        return true;
    }
}









