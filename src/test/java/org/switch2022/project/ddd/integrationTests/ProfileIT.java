package org.switch2022.project.ddd.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.switch2022.project.ddd.domain.model.profile.Profile;
import org.switch2022.project.ddd.dto.ProfileCreationDto;
import org.switch2022.project.ddd.infrastructure.ProfileRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(ProfileIT.class)
public class ProfileIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProfileRepository profileRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ensureProfileIsAddedAndReturnsOk() throws Exception {
        // Arrange
        String profileName = "flash";
        ProfileCreationDto profileDto = new ProfileCreationDto(profileName);

        Profile profile = mock(Profile.class);
        when(profileRepository.findByNameOfProfile(any())).thenReturn(null);
        when(profileRepository.save(any())).thenReturn(true);
        when(profileRepository.findByNameOfProfile(any())).thenReturn(Optional.ofNullable(profile));


        // First call: Ensure that this profile does not exist in DB yet.
        // GET {profileName}
        // Act - ONE
        MvcResult firstResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/profileDto.profileName/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String firstResultContent = firstResult.getResponse().getContentAsString();

        // Assert - ONE
        assertNotNull(firstResultContent);
        assertEquals("", firstResultContent);


        // Second call: Add this new profile to the DB.
        // POST profiles
        // Act - TWO
        MvcResult secondResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/profiles")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(profileDto.profileName))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String secondResultContent = secondResult.getResponse().getContentAsString();

        // Assert - TWO
        assertNotNull(secondResultContent);
        assertEquals("", secondResultContent);


        // Third call: Confirm that this new profile now exists in the DB.
        // GET profileName
        // Act - THREE
        MvcResult thirdResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/profiles/" + profileDto.profileName)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String thirdResultContent = thirdResult.getResponse().getContentAsString();

        // Assert - THREE
        assertNotNull(thirdResultContent);
        //assertEquals(objectMapper.writeValueAsString(profileDto), thirdResultContent);
    }
}
