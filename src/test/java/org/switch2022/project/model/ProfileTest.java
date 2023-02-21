package org.switch2022.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProfileTest {
    /**
     * Test to ensure the object equals itself.
     */
    @Test
    void ensureSameObjectEqualsItself() {
        Profile reference = new Profile("Admin");
        Profile other = reference;
        boolean expected = true;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }

    /**
     * Test to ensure that two objects from the same class are different.
     */
    @Test
    void ensureTwoProfilesAreNotTheSame() {
        Profile reference = new Profile("User");
        Profile other = new Profile("Manager");
        boolean expected = false;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }

    /**
     * Test to ensure that two objects are from different classes.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureObjectDoesNotEqualsOtherTypeOfObject() {
        Profile reference = new Profile("User");
        String other = "User";
        boolean expected = false;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }
    /**
     * Test to ensure that object does not equal null
     */
    @Test
    void ensureProfileDoesNotEqualNull() {
        Profile reference = new Profile("User");
        Profile other = null;
        boolean expected = false;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }

    /**
     * Testing if profile is "Manager" by checking its profile name.
     */
    @Test
    void validateIfIsManagerSuccessfully() {
        //Arrange
        Profile reference = new Profile("Manager");
        boolean expected = true;
        //Act
        boolean result = reference.isProfileRequired(Profile.MANAGER);
        assertEquals(expected, result);
    }

    /**
     * Testing if profile is not "Manager" by checking its profile name.
     */
    @Test
    void validateIfIsManagerUnsuccessfully() {
        //Arrange
        Profile reference = new Profile("Administrator");
        boolean expected = false;
        //Act
        boolean result = reference.isProfileRequired(Profile.MANAGER);
        assertEquals(expected, result);
    }

    /**
     * Testing if profile is "Administrator" by checking its profile name.
     */
    @Test
    void validateIfIsAdministratorSuccessfully() {
        //Arrange
        Profile reference = new Profile("Administrator");
        boolean expected = true;
        //Act
        boolean result = reference.isProfileRequired(Profile.ADMINISTRATOR);
        assertEquals(expected, result);
    }

    /**
     * Testing if profile is not "Administrator" by checking its profile name.
     */
    @Test
    void validateIfIsAdministratorUnsuccessfully() {
        //Arrange
        Profile reference = new Profile("Manager");
        boolean expected = false;
        //Act
        boolean result = reference.isProfileRequired(Profile.ADMINISTRATOR);
        assertEquals(expected, result);
    }

    /**
     * Testing if profile is "User" by checking its profile name.
     */
    @Test
    void validateIfIsUserSuccessfully() {
        //Arrange
        Profile reference = new Profile("User");
        boolean expected = true;
        //Act
        boolean result = reference.isProfileRequired(Profile.USER);
        assertEquals(expected, result);
    }

    /**
     * Testing if profile is not "User" by checking its profile name.
     */
    @Test
    void validateIfIsUserUnsuccessfully() {
        //Arrange
        Profile reference = new Profile("Administrator");
        boolean expected = false;
        //Act
        boolean result = reference.isProfileRequired(Profile.USER);
        assertEquals(expected, result);
    }

    /**
     * Test to check the hashcode when objects are equal and unequal
     */
    @Test
    public void testHashCodeProfile() {
        Profile obj1 = new Profile("manager");
        Profile obj2 = new Profile("manager");
        Profile obj3 = new Profile("user");

        // Check that equal objects have the same hash code
        assertEquals(obj1.hashCode(), obj2.hashCode());

        // Check that unequal objects have different hash codes
        assertNotEquals(obj1.hashCode(), obj3.hashCode());
    }
}

