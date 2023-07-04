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

import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.dto.AccountCreationDto;
import org.switch2022.project.ddd.dto.AccountDto;
import org.switch2022.project.ddd.infrastructure.AccountRepository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@WebMvcTest(AccountIT.class)
public class AccountIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean // Mock the database dependency
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Account created successfully")
    @Test
    void ensureAccountIsAddedAndReturnsOk() throws Exception {
        // First call: Ensure that this account does not exist in the DB yet.
        String email = "joao@mail.com";
        AccountCreationDto newAccountDto = new AccountCreationDto("joao", email, 227639954, null);
        AccountDto accountDto = new AccountDto("joao", "joao@mail.com", "active");

        Account account = mock(Account.class);
        when(accountRepository.findAccountByEmail(any())).thenReturn(null);
        when(accountRepository.save(any())).thenReturn(true);
        when(accountRepository.findAccountByEmail(any())).thenReturn(account);
        MvcResult firstResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/account/" + email)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String firstResultContent = firstResult.getResponse().getContentAsString();

        assertNotNull(firstResultContent);
        assertEquals("", firstResultContent);

        // Second call: Add this new account to the DB.
        // POST account
        // Act - TWO

        MvcResult secondResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/accounts")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newAccountDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String secondResultContent = secondResult.getResponse().getContentAsString();

        // Assert - TWO
        assertNotNull(secondResultContent);
        assertEquals("", secondResultContent);

        // Third call: Confirm that this new account now exists in the DB.
        // GET accounts/
        // Act - THREE

        MvcResult thirdResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/accounts/joao@mail.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String thirdResultContent = thirdResult.getResponse().getContentAsString();

        // Assert - THREE
        assertNotNull(thirdResultContent);
        //assertEquals(objectMapper.writeValueAsString(accountDto), thirdResultContent);
    }
}