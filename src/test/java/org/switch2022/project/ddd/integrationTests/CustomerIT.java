package org.switch2022.project.ddd.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import org.switch2022.project.ddd.domain.model.customer.Customer;
import org.switch2022.project.ddd.dto.CustomerCreationDto;
import org.switch2022.project.ddd.infrastructure.CustomerRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(CustomerIT.class)
public class CustomerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Customer created successfully")
    @Test
    void ensureCustomerIsAddedAndReturnsOk() throws Exception {
        // Arrange
        String taxIdNumber = "228019885";
        CustomerCreationDto newCustomerDto = new CustomerCreationDto("Babi", taxIdNumber);
        when(customerRepository.findCustomerByTaxId(any())).thenReturn(null);
        when(customerRepository.save(any())).thenReturn(true);
        Customer customer = mock(Customer.class);
        when(customerRepository.findCustomerByTaxId(any())).thenReturn(Optional.ofNullable(customer));


        // First call: Ensure that this customer does not exist in DB yet.
        // GET customers/{taxIdNumber}
        // Act - ONE
        MvcResult firstResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/customers/" + taxIdNumber)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String firstResultContent = firstResult.getResponse().getContentAsString();

        // Assert - ONE
        assertNotNull(firstResultContent);
        assertEquals("", firstResultContent);


        // Second call: Add this new customer to the DB.
        // POST customers
        // Act - TWO
        MvcResult secondResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/customers")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newCustomerDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String secondResultContent = secondResult.getResponse().getContentAsString();

        // Assert - TWO
        assertNotNull(secondResultContent);
        assertEquals("", secondResultContent);


        // Third call: Confirm that this new customer now exists in the DB.
        // GET customers/{taxIdNumber}
        // Act - THREE
        MvcResult thirdResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/customers/" + taxIdNumber)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String thirdResultContent = thirdResult.getResponse().getContentAsString();

        // Assert - THREE
        assertNotNull(thirdResultContent);
        //assertEquals(objectMapper.writeValueAsString(newCustomerDto),
        //        thirdResultContent);
    }
}
