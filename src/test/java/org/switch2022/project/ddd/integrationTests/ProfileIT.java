package org.switch2022.project.ddd.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.switch2022.project.ddd.dto.ProfileCreationDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ProfileIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ensureProfileIsAddedAndReturnsOk() throws Exception {
        // Arrange
        String profileName = "flash";
        ProfileCreationDto profileDto = new ProfileCreationDto(profileName);


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
                .andExpect(status().isCreated())
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
                .andExpect(status().isOk())
                .andReturn();

        String thirdResultContent = thirdResult.getResponse().getContentAsString();

        // Assert - THREE
        assertNotNull(thirdResultContent);
        assertEquals(objectMapper.writeValueAsString(profileDto), thirdResultContent);
    }
}
