package org.switch2022.project.controller;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;



class CreateProfileControllerTest {
    @Test
    void addNewProfileSuccessfully(){
        boolean expected = true;
        ProfileContainer profileContainer = new ProfileContainer (new ArrayList<>());
        AccountContainer accountContainer = new AccountContainer(new  ArrayList<>());
        profileContainer.addProfile(new Profile("Admin"));
        profileContainer.addProfile(new Profile("User"));
        Company company = new Company(accountContainer,profileContainer);
        CreateProfileController controller = new CreateProfileController(company);
        boolean result = controller.createProfile("Manager");
        assertEquals(expected,result);
    }
    @Test
    void addNewProfileUnsuccessfullyInvalidName(){
        boolean expected = false;
        ProfileContainer profileContainer = new ProfileContainer (new ArrayList<>());
        AccountContainer accountContainer = new AccountContainer(new  ArrayList<>());
        profileContainer.addProfile(new Profile("Admin"));
        profileContainer.addProfile(new Profile("User"));
        Company company = new Company(accountContainer,profileContainer);
        CreateProfileController controller = new CreateProfileController(company);
        boolean result = controller.createProfile("admin");
        assertEquals(expected,result);
    }
}