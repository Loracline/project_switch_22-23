package org.switch2022.project.controller;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Profile;
import org.switch2022.project.model.Company;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class CreateProfileControllerTest {
    @Test
    void createRepositorySuccessfully(){
        List<Profile> profileList = new ArrayList<>();
        profileList.add(new Profile("Admin"));
        profileList.add(new Profile("User"));
        Company company = new Company(profileList);
        CreateProfileController controller = new CreateProfileController(company);
    }
    @Test
    void addNewProfileSuccessfully(){
        boolean expected = true;
        List<Profile> profileList = new ArrayList<>();
        profileList.add(new Profile("Admin"));
        profileList.add(new Profile("User"));
        Company company = new Company(profileList);
        CreateProfileController controller = new CreateProfileController(company);
        boolean result = controller.addNewProfile("Manager");
        assertEquals(expected,result);
    }
    @Test
    void addNewProfileUnsuccessfullyInvalidName(){
        boolean expected = false;
        List<Profile> profileList = new ArrayList<>();
        profileList.add(new Profile("Admin"));
        profileList.add(new Profile("User"));
        Company company = new Company(profileList);
        CreateProfileController controller = new CreateProfileController(company);
        boolean result = controller.addNewProfile("admin");
        assertEquals(expected,result);
    }

}