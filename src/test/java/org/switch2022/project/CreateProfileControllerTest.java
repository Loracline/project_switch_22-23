package org.switch2022.project;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class CreateProfileControllerTest {
    @Test
    void createRepositorySuccessfully(){
        List<Profile> profileList = new ArrayList<>();
        profileList.add(new Profile("Admin"));
        profileList.add(new Profile("User"));
        Repository repository = new Repository(profileList);
        CreateProfileController controller = new CreateProfileController(repository);
    }
    @Test
    void addNewProfileSuccessfully(){
        boolean expected = true;
        List<Profile> profileList = new ArrayList<>();
        profileList.add(new Profile("Admin"));
        profileList.add(new Profile("User"));
        Repository repository = new Repository(profileList);
        CreateProfileController controller = new CreateProfileController(repository);
        boolean result = controller.addNewProfile("Manager");
        assertEquals(expected,result);
    }
    @Test
    void addNewProfileUnsuccessfullyInvalidName(){
        boolean expected = false;
        List<Profile> profileList = new ArrayList<>();
        profileList.add(new Profile("Admin"));
        profileList.add(new Profile("User"));
        Repository repository = new Repository(profileList);
        CreateProfileController controller = new CreateProfileController(repository);
        boolean result = controller.addNewProfile("admin");
        assertEquals(expected,result);
    }

}