package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.ResourceListAllocationService;
import org.switch2022.project.ddd.dto.AccountDto;
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
        classes = ListAccountsInProjectController.class
)

class ListAccountsInProjectControllerTest {

    @InjectMocks
    ListAccountsInProjectController controller;

    @MockBean
    ResourceListAllocationService service;

    @DisplayName("List of accounts allocated to project")
    @Test
    void ensureListOfAccountDtosIsRetrievedSuccessfully() {
        // Arrange
        String stringOf_projectCode = "P235";
        List<AccountDto> expected = new ArrayList<>();
        AccountDto accountDtoOne = mock(AccountDto.class);
        AccountDto accountDtoTwo = mock(AccountDto.class);
        AccountDto accountDtoThree = mock(AccountDto.class);
        expected.add(accountDtoOne);
        expected.add(accountDtoTwo);
        expected.add(accountDtoThree);

        when(service.listAccountsInProject(stringOf_projectCode)).thenReturn(expected);

        // Act
        List<AccountDto> result = service.listAccountsInProject(stringOf_projectCode);

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("No resource is allocated to project")
    @Test
    void ensureAnExceptionIsThrownWhenNoResourceIsAllocatedToProject() {
        // Arrange
        String stringOf_projectCode = "P235";
        String expected = "There are no resources allocated to this project.";

        when(service.listAccountsInProject(stringOf_projectCode)).thenThrow(new NotFoundInRepoException(expected));

        // Act
        NotFoundInRepoException result = assertThrows(NotFoundInRepoException.class,
                () -> controller.listAccountsByProject(stringOf_projectCode));

        // Assert
        assertEquals(expected, result.getMessage());
    }
    @DisplayName("Empty list is returned when no accounts are allocated to project")
    @Test
    void ensureEmptyListIsReturnedWhenNoAccountsAreAllocatedToProject() {
        // Arrange
        String projectCode = "P235";
        List<AccountDto> expected = new ArrayList<>();

        when(service.listAccountsInProject(projectCode)).thenReturn(expected);

        // Act
        List<AccountDto> result = controller.listAccountsByProject(projectCode);

        // Assert
        assertEquals(expected, result);
    }
}