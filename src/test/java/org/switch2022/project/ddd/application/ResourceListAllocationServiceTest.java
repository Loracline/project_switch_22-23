package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceRepository;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.dto.AccountDto;
import org.switch2022.project.ddd.dto.mapper.AccountMapper;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ResourceListAllocationService.class
)
class ResourceListAllocationServiceTest {

    @InjectMocks
    ResourceListAllocationService service;

    @MockBean
    @Qualifier("resource_jpa")
    IProjectResourceRepository resourceRepository;

    @MockBean
    IAccountRepository accountRepository;

    @MockBean
    AccountMapper mapper;

    /**
     * METHOD listAccountsInProject()
     */
    @DisplayName("List of accounts allocated to project")
    @Test
    void ensureAccountDtoListIsRetrievedSuccessfully() {
        // Arrange
        String stringOf_projectCode = "P001";
        Code projectCode = Code.getCodeFromString(stringOf_projectCode);

        List<AccountDto> expected = new ArrayList<>();
        AccountDto accountDtoOne = mock(AccountDto.class);
        AccountDto accountDtoTwo = mock(AccountDto.class);
        AccountDto accountDtoThree = mock(AccountDto.class);
        expected.add(accountDtoOne);
        expected.add(accountDtoTwo);
        expected.add(accountDtoThree);

        List<Email> emails = new ArrayList<>();
        Email emailOne = mock(Email.class);
        Email emailTwo = mock(Email.class);
        Email emailThree = mock(Email.class);
        emails.add(emailOne);
        emails.add(emailTwo);
        emails.add(emailThree);
        when(resourceRepository.findAccountEmailsByProjectCode(projectCode)).thenReturn(emails);

        List<Account> accounts = new ArrayList<>();
        Account accountOne = mock(Account.class);
        Account accountTwo = mock(Account.class);
        Account accountThree = mock(Account.class);
        accounts.add(accountOne);
        accounts.add(accountTwo);
        accounts.add(accountThree);
        when(accountRepository.findAccountsByEmails(emails)).thenReturn(accounts);

        when(mapper.listAccountsToDto(accounts)).thenReturn(expected);

        // Act
        List<AccountDto> result = service.listAccountsInProject(stringOf_projectCode);

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("No account is allocated to project")
    @Test
    void ensureAnExceptionIsThrownWhenNoResourceIsAllocated() {
        // Arrange
        String stringOf_projectCode = "P001";
        Code projectCode = Code.getCodeFromString(stringOf_projectCode);

        String expected = "There are no resources allocated to this project.";

        List<Email> emails = new ArrayList<>();
        when(resourceRepository.findAccountEmailsByProjectCode(projectCode)).thenReturn(emails);

        // Act
        NotFoundInRepoException result = assertThrows(NotFoundInRepoException.class,
                () -> service.listAccountsInProject(stringOf_projectCode));

        // Assert
        assertEquals(expected, result.getMessage());
    }
}