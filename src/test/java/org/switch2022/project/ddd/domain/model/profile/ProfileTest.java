package org.switch2022.project.ddd.domain.model.profile;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSector;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.domain.value_object.ProfileId;

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
        Profile profile = mock(Profile.class);
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
        ProfileId profileId = mock(ProfileId.class);
        //when(profileId.getProfileId()).thenReturn("prof01");
        Profile profile_one = new Profile(new Name("Sofia"), 1);
        Profile profile_two = new Profile(new Name("Sofia"), 1);

        //ACT & ASSERT
        assertTrue(profile_one.equals(profile_two));
    }

    /**
     * Scenario 3: Verify that two different Profiles has not the same id.
     * <p>
     * It should assert false.
     */

    @Test
    void ensureThatTwoProfilesHasNotTheSameId() {
        //ARRANGE
        Profile profile_one = mock(Profile.class);
        Profile profile_two = mock(Profile.class);
        when(profile_one.getProfileId()).thenReturn("pr001");
        when(profile_two.getProfileId()).thenReturn("pr002");

        //ACT & ASSERT
        assertFalse(profile_one.equals(profile_two));
    }

    /**
     * Scenario 4: Verify that the object Profile does not equal null.
     * <p>
     * It should assert false.
     */
    @Test
    void ensureThatObjectProfileDoesNotEqualNull() {
        //ARRANGE
        Profile profile = mock(Profile.class);

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
        Profile profile = mock(Profile.class);
        String name = "Sofia";

        //ACT & ASSERT
        assertFalse(profile.equals(name));
    }

    /**
     * Test for the hashCode() method.
     */
    @Test
    void ensureTwoInstancesOfProfileWithDifferentHashCodesAreNotEqual() {
        // ARRANGE
        Name profileNameDouble = mock(Name.class);
        Profile profile_One = new Profile(profileNameDouble, 1);
        Profile profile_Two = new Profile(profileNameDouble, 2);

        //ACT & ASSERT
        assertNotEquals(profile_One.hashCode(), profile_Two.hashCode());
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
        Profile profile_One = new Profile(nameDouble, 1);
        String expected = "Sofia";

        //ACT
        String result = profile_One.getProfileName();

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
        Profile profileOne = new Profile(nameDouble, 1);
        String expected = "pr001";

        //ACT
        String result = profileOne.getProfileId();

        //ASSERT
        assertEquals(expected, result);
    }
}