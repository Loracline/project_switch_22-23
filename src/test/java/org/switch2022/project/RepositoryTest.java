package org.switch2022.project;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
    @Test
    void getListAllAccounts() {
        Profile user = new Profile("User");
        Profile administrator = new Profile("Administrator");
        Profile manager = new Profile("Manager");
        Account user1 = new Account("Ana", "ana@mail.com", 12345678, null, true);
        Account user2 = new Account("Paulo", "paulo@mail.com", 23456789, null, true);

        List<Account> accountList = new ArrayList<>();
        accountList.add(user1);
        accountList.add(user2);

        List<Profile> profileList = new ArrayList<>();
        profileList.add(user);
        profileList.add(administrator);
        profileList.add(manager);
        Repository test = new Repository(accountList, profileList);

        List<Account> expected = accountList;
        List<Account> result = test.getAccountsList();

        assertEquals(expected, result);
    }

    @Test
    void ensureThatNewAccountIsRegisteredIfEmailIsUnique() {
        //ARRANGE
        //create empty repository with empty accountList
        Repository repo = new Repository();

        //expected result; if account is registered, returns TRUE
        boolean expected = true;

        //ACT
        //add user to accountList
        boolean result = repo.registerAccount("Ana", "ana@mail.com", 12345678, null, true);

        //ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureThatNewAccountIsNotRegisteredIfEmailIsDuplicated() {
        //ARRANGE
        //create profiles to initialize profileList
        Profile user = new Profile("User");
        Profile administrator = new Profile("Administrator");
        Profile manager = new Profile("Manager");
        //create accounts to initialize accountList
        Account user1 = new Account("Ana", "ana@mail.com", 12345678, null, true);
        Account user2 = new Account("Paulo", "paulo@mail.com", 23456789, null, true);

        List<Account> accountList = new ArrayList<>();
        accountList.add(user1);
        accountList.add(user2);

        List<Profile> profileList = new ArrayList<>();
        profileList.add(user);
        profileList.add(administrator);
        profileList.add(manager);

        //create repository with initialized account and profile lists
        Repository repo = new Repository(accountList, profileList);

        //expected result; if account is not registered, returns FALSE
        boolean expected = false;

        //ACT
        //add user to accountList
        boolean result = repo.registerAccount("Ana", "ana@mail.com", 12345678, null, true);

        //ASSERT
        assertEquals(expected, result);
    }

    @Test
    void getAccount() {
        //ARRANGE
        //create empty repository with empty accountList
        Repository repo = new Repository();

        //register new user
        repo.registerAccount("Ana", "ana@mail.com", 12345678, null, true);
        repo.registerAccount("Paulo", "paulo@mail.com", 23456789,null, true);
        repo.registerAccount("Sofia", "sofia@mail.com", 232423423,null, true);

        //Create account to compare with
        Account expected = new Account("Ana", "ana@mail.com", 12345678, null, true);

        //ACT
        Account result = repo.getAccount("ana@mail.com");

        //ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureThatGetAccountReturnNull() {
        //ARRANGE
        //create empty repository with empty accountList
        Repository repo = new Repository();

        //register new user
        repo.registerAccount("Ana", "ana@mail.com", 12345678, null, true);
        repo.registerAccount("Paulo", "paulo@mail.com", 23456789,null, true);
        repo.registerAccount("Sofia", "sofia@mail.com", 232423423,null, true);

        //ACT
        Account result = repo.getAccount("anaPinho@mail.com");

        //ASSERT
        assertNull(result);
    }
    @Test
    void createRepositorySucessfully() {
        List<Profile> profilesList = new ArrayList<>();
        profilesList.add(new Profile("Admin"));
        profilesList.add(new Profile("User"));
        Repository test = new Repository(profilesList);
    }

    @Test
    void ensureSameObjectEqualsItself() {
        List<Profile> profilesList = new ArrayList<>();
        profilesList.add(new Profile("Admin"));
        Repository reference = new Repository(profilesList);
        Repository other = reference;
        boolean expected = true;

        boolean result = reference.equals(other);

        assertEquals(expected, result);
    }

    @Test
    void ensureTwoAccountsAreNotTheSame() {
        List<Profile> profilesList = new ArrayList<>();
        profilesList.add(new Profile("Admin"));
        Repository reference = new Repository(profilesList);
        List<Profile> profileList = new ArrayList<>();
        profilesList.add(new Profile("Manager"));
        Repository other = new Repository(profileList);

        boolean expected = false;

        boolean result = reference.equals(other);

        assertEquals(expected, result);
    }

    @Test
    void ensureObjectDoesNotEqualsOtherTypeOfObject() {
        List<Profile> profilesList = new ArrayList<>();
        profilesList.add(new Profile("Admin"));
        Repository reference = new Repository(profilesList);
        Profile other = new Profile("Admin");
        boolean expected = false;

        boolean result = reference.equals(other);

        assertEquals(expected, result);
    }

    @Test
    void createProfile() {
        Profile expected = new Profile("Joana");
        List<Profile> profilesList = new ArrayList<>();
        Repository repository = new Repository(profilesList);
        Profile result = repository.createProfile("Joana");
        assertEquals(expected, result);
    }

    @Test
    void validateProfileAsFalse() {
        boolean expected = false;
        List<Profile> profilesList = new ArrayList<>();
        Repository repository = new Repository(profilesList);
        profilesList.add(new Profile("Admin"));
        profilesList.add(new Profile("User"));
        boolean result = repository.validateProfile("User");
        assertEquals(expected, result);
    }

    @Test
    void validateProfileAsTrue() {
        boolean expected = true;
        List<Profile> profilesList = new ArrayList<>();
        Repository repository = new Repository(profilesList);
        profilesList.add(new Profile("Admin"));
        profilesList.add(new Profile("User"));
        boolean result = repository.validateProfile("Manager");
        assertEquals(expected, result);
    }

    @Test
    void validateProfileAsFalseCaseInsensitive() {
        boolean expected = false;
        List<Profile> profilesList = new ArrayList<>();
        Repository repository = new Repository(profilesList);
        profilesList.add(new Profile("Admin"));
        profilesList.add(new Profile("User"));
        boolean result = repository.validateProfile("user");
        assertEquals(expected, result);
    }

    @Test
    void addProfileToProfilesList() {
        List<Profile> profilesListE = new ArrayList<>();

        Repository expected = new Repository(profilesListE);

        profilesListE.add(new Profile("Admin"));
        profilesListE.add(new Profile("User"));
        profilesListE.add(new Profile("Manager"));

        Profile profile = new Profile("Manager");

        List<Profile> profilesList = new ArrayList<>();

        Repository result = new Repository(profilesList);

        profilesList.add(new Profile("Admin"));
        profilesList.add(new Profile("User"));
        result.addProfileToProfilesList(profile);

        assertEquals(expected, result);
    }

    @Test
    void addProfileToProfilesListTrue() {
        boolean expected = true;
        List<Profile> profilesList = new ArrayList<>();
        Repository repository = new Repository(profilesList);
        Profile profile = new Profile("Manager");
        boolean result = repository.addProfileToProfilesList(profile);
        assertEquals(expected, result);
    }
}