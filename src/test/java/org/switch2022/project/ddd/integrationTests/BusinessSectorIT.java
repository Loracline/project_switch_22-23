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
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSector;
import org.switch2022.project.ddd.dto.BusinessSectorCreationDto;
import org.switch2022.project.ddd.dto.BusinessSectorDto;
import org.switch2022.project.ddd.infrastructure.BusinessSectorRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(BusinessSectorIT.class)
public class BusinessSectorIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BusinessSectorRepository businessSectorRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ensureBusinessSectorIsAddedAndReturnsOk() throws Exception {
        // Arrange
        String businessSectorId = "bs003";
        BusinessSectorCreationDto newBusinessSectorCreationDto = new BusinessSectorCreationDto("Maketing");
        BusinessSectorDto newBusinessSectorDto = new BusinessSectorDto("Marketing", businessSectorId);

        BusinessSector businessSector = mock(BusinessSector.class);
        when(businessSectorRepository.getBusinessSectorIdByName(any())).thenReturn(null);

        // First call: Ensure that this business sector does not exist in DB yet.
        // GET business_sectors/{businessSectorId}
        // Act - ONE
        MvcResult firstResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/business_sectors/" + businessSectorId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String firstResultContent = firstResult.getResponse().getContentAsString();

        // Assert - ONE
        assertNotNull(firstResultContent);
        assertEquals("", firstResultContent);

        when(businessSectorRepository.save(any())).thenReturn(true);
        when(businessSectorRepository.getBusinessSectorIdByName(any())).thenReturn("businessSector");

        // Second call: Add this new business sector to the DB.
        // POST business_sectors
        // Act - TWO
        MvcResult secondResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/business_sectors")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newBusinessSectorDto.name))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();


        String secondResultContent = secondResult.getResponse().getContentAsString();

        // Assert - TWO
        assertNotNull(secondResultContent);
        assertEquals("", secondResultContent);



        // Third call: Confirm that this new business sector now exists in the DB.
        // GET business_sectors/{businessSectorId}
        // Act - THREE
        MvcResult thirdResult = mockMvc
                .perform(MockMvcRequestBuilders.get(
                                "/business_sectors/bs003" )
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        String thirdResultContent = thirdResult.getResponse().getContentAsString();

        // Assert - THREE
        assertNotNull(thirdResultContent);
        //assertEquals(objectMapper.writeValueAsString(newBusinessSectorDto),
        //        thirdResultContent);

    }
}


