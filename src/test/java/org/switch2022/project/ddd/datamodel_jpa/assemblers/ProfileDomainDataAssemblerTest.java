package org.switch2022.project.ddd.datamodel_jpa.assemblers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.datamodel_jpa.ProfileJpa;
import org.switch2022.project.ddd.domain.model.profile.IProfileFactory;
import org.switch2022.project.ddd.domain.model.profile.Profile;
import org.switch2022.project.ddd.domain.value_object.Name;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        classes =
                org.switch2022.project.ddd.datamodel_jpa.assemblers.ProfileDomainDataAssembler.class)
class ProfileDomainDataAssemblerTest {
    @InjectMocks
    ProfileDomainDataAssembler assembler;

    @MockBean
    IProfileFactory profileFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("ToData test")
    @Test
    void ensureTypologyJpaIsRetrievedFromTypology() {
        // Arrange
        Profile profile = mock(Profile.class);
        when(profile.getProfileId()).thenReturn("PRF005");
        when(profile.getProfileName()).thenReturn("Director");
        ProfileJpa expected = new ProfileJpa("PRF005", "Director");

        // Act
        ProfileJpa result = assembler.toData(profile);

        // Assert
        assertEquals(expected, result);
    }


    @DisplayName("ToDomain test")
    @Test
    void ensureTypologyIsRetrievedFromTypologyJpa() {
        // Arrange
        ProfileJpa profileJpa = new ProfileJpa("PRF005", "Director");
        Profile expected = mock(Profile.class);
        when(profileFactory.createProfile(new Name("Director"),5)).thenReturn(expected);

        // Act
        Profile result = assembler.toDomain(profileJpa);

        // Assert
        assertEquals(expected, result);
    }
}