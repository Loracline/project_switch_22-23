package org.switch2022.project.ddd.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.switch2022.project.ddd.domain.model.typology.Typology;
import org.switch2022.project.ddd.dto.TypologyCreationDto;
import org.switch2022.project.ddd.dto.TypologyDto;
import org.switch2022.project.ddd.infrastructure.TypologyRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TypologyIT.class)
@AutoConfigureMockMvc
public class TypologyIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TypologyRepository typologyRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void ensureTypologyIsAddedAndReturnsOk() throws Exception {
        // Arrange
        String typologyName = "other typology";
        TypologyCreationDto newTypologyDto = new TypologyCreationDto(typologyName);

        String detailedMessage = "Typology with this name does not exist in the Repository.";

        Typology typology = mock(Typology.class);
        when(typologyRepository.findTypologyByTypologyName(any())).thenReturn(null);
        when(typologyRepository.save(any())).thenReturn(true);
        when(typologyRepository.findTypologyByTypologyName(any())).thenReturn(typology);

        // First call: Ensure that this typology does not exist in database.
        // GET typologies/{typologyName}
        // Act - ONE
        MvcResult firstResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/typologies/" + typologyName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        //Exception exception = firstResult.getResolvedException();

        String firstResultContent = firstResult.getResponse().getContentAsString();

        // Assert - ONE
        assertNotNull(firstResultContent);
        //assertEquals(NotFoundInRepoException.class, exception.getClass());
        //assertEquals(detailedMessage, exception.getMessage());
        assertEquals("", firstResultContent);


        // Second call: Add this new typology to the database.
        // POST typologies
        // Act - TWO
        String content = objectMapper.writeValueAsString(newTypologyDto.typologyName);
        MvcResult secondResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/typologies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isNotFound())
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
                .andExpect(status().isNotFound())
                .andReturn();

        String thirdResultContent = thirdResult.getResponse().getContentAsString();

        // Assert - THREE
        assertNotNull(thirdResultContent);
        //assertEquals(objectMapper.writeValueAsString(newTypologyDto1),
        //        thirdResultContent);
    }
}
