package org.switch2022.project.ddd.domain.model.profile;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.Name;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProfileTest {

    // UNIT TESTS

    /**
     * Constructor
     * Scenario 1: A Profile is not created because the name is null.
     * <p>
     * It should throw an Illegal Argument Exception.
     */
    @Test
    void ensureAProfileIsNotCreatedBecauseTheNameIsNull() {
        //ACT & ASSERT
        assertThrows(IllegalArgumentException.class, () -> {
            new Profile(null, 1);
        });
    }

    /**
     * Scenario 2: A Profile is not created because the idProfileNumber is null.
     * <p>
     * It should throw an Illegal Argument Exception.
     */
    @Test
    public void ensureAProfileIsNotCreatedBecauseTheIdIsNull() {
        //ARRANGE
        Name profileName = mock(Name.class);
        //ACT & ASSERT
        assertThrows(IllegalArgumentException.class, () -> {
            new Profile(profileName, null);
        });
    }

    /**
     * Scenario 3: A Profile is not created because the idProfileNumber is negative.
     * <p>
     * It should throw an Illegal Argument Exception.
     */
    @Test
    public void ensureAProfileIsNotCreatedBecauseTheIdIsNegative() {
        //ARRANGE
        Name profileName = mock(Name.class);
        //ACT & ASSERT
        assertThrows(IllegalArgumentException.class, () -> {
            new Profile(profileName, -1);
        });
    }

    /**
     * METHOD equals()
     * <p>
     * Scenario 1: Verify that the same object equals itself.
     * It should assert true.
     */
    @Test
    void ensureThatSameObjectEqualsItself() {
        //ARRANGE
        Name profileName = mock(Name.class);
        Profile profile = new Profile(profileName, 1);
        //ACT & ASSERT
        assertTrue(profile.equals(profile));
    }

    /**
     * Scenario 2: Verify that two different Profiles has the same id.
     * <p>
     * It should assert true.
     */

    @Test
    void ensureThatTwoProfilesHasTheSameId() {
        //ARRANGE
        Name profileName = mock(Name.class);
        Profile profileOne = new Profile(profileName, 1);
        Profile profileTwo = new Profile(profileName, 1);

        //ACT & ASSERT
        assertTrue(profileOne.equals(profileTwo));
    }

    /**
     * Scenario 3: Verify that two different Profiles has not the same id.
     * <p>
     * It should assert false.
     */

    @Test
    void ensureThatTwoProfilesHasNotTheSameId() {
        //ARRANGE
        Name profileName = mock(Name.class);
        Profile profileOne = new Profile(profileName, 1);
        Profile profileTwo = new Profile(profileName, 2);

        //ACT & ASSERT
        assertFalse(profileOne.equals(profileTwo));
    }

    /**
     * Scenario 4: Verify that the object Profile does not equal null.
     * <p>
     * It should assert false.
     */
    @Test
    void ensureThatObjectProfileDoesNotEqualNull() {
        //ARRANGE
        Name profileName = mock(Name.class);
        Profile profile = new Profile(profileName, 1);

        //ACT & ASSERT
        assertFalse(profile.equals(null));
    }

    /**
     * Scenario 5: Verify that the object Profile is not equal to other type of object.
     * <p>
     * It should assert false.
     */
    @Test
    public void ensureThatObjectProfileDoesNotEqualToOtherObjectType() {
        //ARRANGE
        Name profileName = mock(Name.class);
        Profile profile = new Profile(profileName, 1);
        String name = "Sofia";

        //ACT & ASSERT
        assertFalse(profile.equals(name));
    }
    /**
     * Scenario 6: Verify if two Profile objects with different ProfileIds are considered not equal.
     */
    @Test
    void ensureProfilesWithDifferentProfileIdsAreNotEqual() {
        // Arrange
        Integer profileId1 = 1;
        Integer profileId2 = 2;
        Name profileName = new Name("John Doe");

        Profile profile1 = new Profile(profileName, profileId1);
        Profile profile2 = new Profile(profileName, profileId2);

        // Act
        boolean result = profile1.equals(profile2);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 7: Verify if two Profile objects with different Names are considered not equal.
     */
    @Test
    void ensureProfilesWithDifferentNamesAreNotEqual() {
        // Arrange
        Integer profileId = 1;
        Name name1 = new Name("John Doe");
        Name name2 = new Name("Jane Smith");

        Profile profile1 = new Profile(name1, profileId);
        Profile profile2 = new Profile(name2, profileId);

        // Act
        boolean result = profile1.equals(profile2);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 8: Verify if two Profile objects with the same ProfileId and Name are considered equal.
     */
    @Test
    void ensureProfilesWithSameProfileIdAndNameAreEqual() {
        // Arrange
        Integer profileId = 1;
        Name profileName = new Name("John Doe");

        Profile profile1 = new Profile(profileName, profileId);
        Profile profile2 = new Profile(profileName, profileId);

        // Act
        boolean result = profile1.equals(profile2);

        // Assert
        assertTrue(result);
    }

    /**
     * Test for the hashCode() method.
     */
    @Test
    void ensureTwoInstancesOfProfileWithDifferentHashCodesAreNotEqual() {
        // ARRANGE
        Name profileNameDouble = mock(Name.class);
        Profile profileOne = new Profile(profileNameDouble, 1);
        Profile profileTwo = new Profile(profileNameDouble, 2);

        //ACT & ASSERT
        assertNotEquals(profileOne.hashCode(), profileTwo.hashCode());
    }

    /**
     * Method: sameIdentityAs()
     * Scenario 01: This test evaluates if two instances of Profile are the same if their id
     * attribute is the same.
     * It should assert true.
     */
    @Test
    void ensureTheTwoInstancesOfProfileAreTheSameIfTheirIdAttributeIsTheSame() {
        //ARRANGE
        Name nameDouble = mock(Name.class);
        Profile profileOne = new Profile(nameDouble, 1);
        Profile profileTwo = new Profile(nameDouble, 1);

        //ACT
        boolean result = profileOne.sameIdentityAs(profileTwo);

        //ASSERT
        assertTrue(result);
    }

    /**
     * Method: sameIdentityAs()
     * Scenario 02: This test evaluates if two instances of Profile are different if their id
     * attribute is not the same.
     * It should assert false.
     */
    @Test
    void ensureTheTwoInstancesOfProfileAreDifferentIfTheirIdAttributeIsNotTheSame() {
        //ARRANGE
        Name nameDouble = mock(Name.class);
        Profile profileOne = new Profile(nameDouble, 1);
        Profile profileTwo = new Profile(nameDouble, 2);

        //ACT
        boolean result = profileOne.sameIdentityAs(profileTwo);

        //ASSERT
        assertFalse(result);
    }

    /**
     * Method: sameIdentityAs()
     * Scenario 03: This test evaluates if two instances of Profile are different if one of them is
     * null.
     * It should assert false.
     */
    @Test
    void ensureTheTwoProfilesAreNotTheSameBecauseOneOfThemIsNull() {
        //ARRANGE
        Name nameDouble = mock(Name.class);
        Profile profileOne = new Profile(nameDouble, 1);
        Profile profileTwo = null;

        //ACT
        boolean result = profileOne.sameIdentityAs(profileTwo);

        //ASSERT
        assertFalse(result);
    }

    /**
     * Method: getProfileName().
     * Scenario 01: This test evaluates if the string representation of the Profile name attribute
     * of a given instance of Profile is retrieved.
     */
    @Test
    public void ensureStringRepresentationOfProfileNameIsRetrieved() {
        //ARRANGE
        Name nameDouble = mock(Name.class);
        when(nameDouble.getName()).thenReturn("Sofia");
        Profile profile = new Profile(nameDouble, 1);
        String expected = "Sofia";

        //ACT
        String result = profile.getProfileName();

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Method: getProfileId().
     * Scenario 1:This test evaluates if the string representation of the Profile id
     * attribute of a given instance of Profile is retrieved.
     * It should return a string starting with "pr", followed by up to three digits (e.g. pr001).
     */
    @Test
    public void ensureStringRepresentationOfProfileIdIsRetrieved() {
        //ARRANGE
        Name nameDouble = mock(Name.class);
        Profile profile = new Profile(nameDouble, 1);
        String expected = "prf001";

        //ACT
        String result = profile.getProfileId();

        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Method: hasName().
     *
     * Scenario 01: Ensures that the profile with a given name doesn't match a different name.
     * Should return FALSE.
     */
    @Test
    void ensureThatProfileDoesNotHaveDifferentName() {
        // Arrange
        Name profileName = new Name("Test Profile");
        Profile profile = new Profile(profileName, 1);

        // Act
        boolean result = profile.hasName(new Name("Different Profile"));

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 02: Tests the hasName() method for the Profile class when the name provided
     * is the same as the profile's name.
     * Should return TRUE.
     */
    @Test
    void ensureThatProfileHasSameName() {
        // Arrange
        Name profileName = new Name("Test Profile");
        Profile profile = new Profile(profileName, 1);

        // Act
        boolean result = profile.hasName(profileName);

        // Assert
        assertTrue(result);
    }
}