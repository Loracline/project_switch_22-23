package org.switch2022.project.ddd.domain.model.profile;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
     * It should assert true.
     */

    @Test
    void ensureThatTwoProfilesHasTheSameId() {
        //ARRANGE
        ProfileId profileId = mock(ProfileId.class);
        //when(profileId.getProfileId()).thenReturn("prof01");
        Profile profile_one = new Profile(new Name("prof01"), 1);
        Profile profile_two = new Profile(new Name("prof01"), 1);

        //ACT & ASSERT
        assertTrue(profile_one.equals(profile_two));
    }

    /**
     * Scenario 3: Verify that two different Profiles has not the same id.
     * It should assert false.
     */

    @Test
    void ensureThatTwoProfilesHasNotTheSameId() {
        //ARRANGE
        Profile profile_one = mock(Profile.class);
        Profile profile_two = mock(Profile.class);
        when(profile_one.getProfileId()).thenReturn("prof01");
        when(profile_two.getProfileId()).thenReturn("prof02");

        //ACT & ASSERT
        assertFalse(profile_one.equals(profile_two));
    }

    /**
     * Scenario 4: Verify that the object Profile does not equal null.
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
}