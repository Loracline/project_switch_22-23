package org.switch2022.project.ddd.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.switch2022.project.ddd.dto.TypologyCreationDto;
import org.switch2022.project.ddd.dto.TypologyDto;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TypologyIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void ensureTypologyIsAddedAndReturnsOk() throws Exception {
        // Arrange
        String typologyName = "other typology";
        TypologyCreationDto newTypologyDto = new TypologyCreationDto(typologyName);

        String detailedMessage = "Typology with this name does not exist in the Repository.";

        // First call: Ensure that this typology does not exist in database.
        // GET typologies/{typologyName}
        // Act - ONE
        MvcResult firstResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/typologies/" + typologyName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        Exception exception = firstResult.getResolvedException();

        // Assert - ONE
        assertNotNull(exception);
        assertEquals(NotFoundInRepoException.class, exception.getClass());
        assertEquals(detailedMessage, exception.getMessage());


        // Second call: Add this new typology to the database.
        // POST typologies
        // Act - TWO
        String content = objectMapper.writeValueAsString(newTypologyDto.typologyName);
        MvcResult secondResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/typologies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated())
                .andReturn();

        String secondResultContent = secondResult.getResponse().getContentAsString();

        // Assert - TWO
        assertNotNull(secondResultContent);
        assertEquals("", secondResultContent);


        // Third call: Confirm that this new typology now exists in the Data Base.
        // GET typologies/{typologyName}
        // Act - THREE
        TypologyDto newTypologyDto1 = new TypologyDto("pt003", typologyName);

        MvcResult thirdResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/typologies/" + typologyName)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String thirdResultContent = thirdResult.getResponse().getContentAsString();

        // Assert - THREE
        assertNotNull(thirdResultContent);
        assertEquals(objectMapper.writeValueAsString(newTypologyDto1), thirdResultContent);
    }
}
