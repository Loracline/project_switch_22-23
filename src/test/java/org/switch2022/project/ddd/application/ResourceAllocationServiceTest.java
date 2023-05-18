package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountFactory;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceFactory;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceRepository;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.value_object.AccountStatus;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.domain.value_object.Period;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        classes = ResourceAllocationServiceTest.class)

class ResourceAllocationServiceTest {
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    /**
     * BeforeEach execute common code before running the
     * tests below.
     */
    @InjectMocks
    private ResourceAllocationService resourceAllocationService;
    @MockBean
    private IProjectResourceRepository resourceRepository;
    @MockBean
    private IProjectRepository projectRepository;
    @MockBean
    private IAccountRepository accountRepository;
    @MockBean
    private IProjectResourceFactory resourceFactory;
    @MockBean
    private IAccountFactory accountFactory;

    @Test
    void isResourceOverlapping() {
    }

    /**
     * Method IsAValidAccount().
     * Scenario 01: Make sure the account is valid and active.
     * Expected return: True.
     */
    @Test
    void ensureThatAccountIsValid() {
        // Arrange
        Email accountEmailDouble = mock(Email.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account accountDouble = mock(Account.class);
        List<Account> accounts = new ArrayList<>();
        accounts.add(accountDouble);

        when(accountDouble.hasEmail(accountEmailDouble.getEmail())).thenReturn(true);
        when(accountDouble.isAccountActive(accountStatus.getAccountStatus())).thenReturn(true);

        when(accountRepository.findAll()).thenReturn(accounts);

        // Act
        boolean result = resourceAllocationService.isAValidAccount(accountEmailDouble, accountStatus);

        // Assert
        assertTrue(result);
    }

    /**
     * Scerario 02: the account exists, but is inactive.
     * Expected return: false.
     */
    @Test
    void ensureTheAccointExistisButIsInactivate() {
        //Arrange
        Email accountEmailDouble = mock(Email.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account accountDouble = mock(Account.class);
        accountRepository.add(accountDouble);
        when(accountDouble.hasEmail(accountEmailDouble.getEmail())).thenReturn(true);
        when(accountDouble.isAccountActive(accountStatus.getAccountStatus())).thenReturn(false);
        //Act
        boolean result = resourceAllocationService.isAValidAccount(accountEmailDouble, accountStatus);
        //Assert
        assertFalse(result);
    }

    /**
     * Scerario 03: the account does not exist.
     * Expected return: false.
     */
    @Test
    void ensureTheAccountDoesNotExist() {
        //Arrange
        Email accountEmailDouble = mock(Email.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account accountDouble = mock(Account.class);
        accountRepository.add(accountDouble);
        when(accountDouble.hasEmail(accountEmailDouble.getEmail())).thenReturn(false);
        //Act
        boolean result = resourceAllocationService.isAValidAccount(accountEmailDouble, accountStatus);
        //Assert
        assertFalse(result);
    }
    /**
     * Scerario 04: the accounts list is empty.
     * Expected return: false.
     */
    @Test
    void ensureTheAccountsListIsEmpty() {
        //Arrange
        when(accountRepository.findAll()).thenReturn(Collections.emptyList());
        Email accountEmailDouble = mock(Email.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account accountDouble = mock(Account.class);
        when(accountDouble.hasEmail(accountEmailDouble.getEmail())).thenReturn(false);
        //Act
        boolean result = resourceAllocationService.isAValidAccount(accountEmailDouble, accountStatus);
        //Assert
        assertFalse(result);
    }

    @Test
    void calculateNextNumber() {
    }

    /**
     * Method isResourceOverlapping()
     * Scenario 01: Make sure resource is overlapping, projectCode, accountEmail are same and period is overlapping.
     * Expected return: True.
     */
    @Test
    void ensureResourceIsOverlapping() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Period periodDouble = mock(Period.class);
        ProjectResource projectResourceOneDouble = mock(ProjectResource.class);
        List<ProjectResource> resources = new ArrayList<>();
        resources.add(projectResourceOneDouble);
        when(resourceRepository.findAll()).thenReturn(resources);

        resourceRepository.add(projectResourceOneDouble);
        when(projectResourceOneDouble.hasProjectCode(codeDouble)).thenReturn(true);
        when(projectResourceOneDouble.hasAccount(emailDouble)).thenReturn(true);
        when(projectResourceOneDouble.isPeriodOverlapping(periodDouble)).thenReturn(true);
        // Act
        boolean result = resourceAllocationService.isResourceOverlapping(codeDouble, emailDouble, periodDouble);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: Check resource is not overlapping, projectCode is not equal, accountEmail is same,
     * and the period is overlapped.
     * Expected return: false.
     */
    @Test
    void ensureResourceIsNotOverlappingBecauseTheProjectCodeIsNotTheSame() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Period periodDouble = mock(Period.class);
        ProjectResource projectResourceOneDouble = mock(ProjectResource.class);
        List<ProjectResource> resources = new ArrayList<>();
        resources.add(projectResourceOneDouble);
        when(resourceRepository.findAll()).thenReturn(resources);

        resourceRepository.add(projectResourceOneDouble);
        when(projectResourceOneDouble.hasProjectCode(codeDouble)).thenReturn(false);
        when(projectResourceOneDouble.hasAccount(emailDouble)).thenReturn(false);
        when(projectResourceOneDouble.isPeriodOverlapping(periodDouble)).thenReturn(true);
        // Act
        boolean result = resourceAllocationService.isResourceOverlapping(codeDouble, emailDouble, periodDouble);

        // Assert
        assertFalse(result);
    }


    /**
     * Scenario 03: Check resource is not overlapped, projectCode is same but accountEmail is not same
     * and the period is overlapped.
     * Expected return: false.
     */
    @Test
    void assureResourceIsNotOverlappingBecauseTheAccountEmailIsNotTheSame() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Period periodDouble = mock(Period.class);
        ProjectResource projectResourceOneDouble = mock(ProjectResource.class);
        List<ProjectResource> resources = new ArrayList<>();
        resources.add(projectResourceOneDouble);
        when(resourceRepository.findAll()).thenReturn(resources);

        resourceRepository.add(projectResourceOneDouble);
        when(projectResourceOneDouble.hasProjectCode(codeDouble)).thenReturn(true);
        when(projectResourceOneDouble.hasAccount(emailDouble)).thenReturn(false);
        when(projectResourceOneDouble.isPeriodOverlapping(periodDouble)).thenReturn(true);
        // Act
        boolean result = resourceAllocationService.isResourceOverlapping(codeDouble, emailDouble, periodDouble);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 04: Check resource is not overlapped, projectCode, accountEmail are same and period is not
     * overlay.
     * Expected return: false.
     */
    @Test
    void ensureResourceIsNotOverlappingbecauseThePeriodIsNotOverllapping() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Period periodDouble = mock(Period.class);
        ProjectResource projectResourceOneDouble = mock(ProjectResource.class);
        List<ProjectResource> resources = Arrays.asList(projectResourceOneDouble);
        when(resourceRepository.findAll()).thenReturn(resources);

        resourceRepository.add(projectResourceOneDouble);
        when(projectResourceOneDouble.hasProjectCode(codeDouble)).thenReturn(true);
        when(projectResourceOneDouble.hasAccount(emailDouble)).thenReturn(true);
        when(projectResourceOneDouble.isPeriodOverlapping(periodDouble)).thenReturn(false);
        // Act
        boolean result = resourceAllocationService.isResourceOverlapping(codeDouble, emailDouble, periodDouble);

        // Assert
        assertFalse(result);

    }
    /**
     * Scenario 05: Check resource is not overlapped, resourceRepository is empty
     * overlay.
     * Expected return: false.
     */
    @Test
    void ensureResourceIsNotOverlappingbecauseTheResourceRepositoryIsEmpty() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Period periodDouble = mock(Period.class);
        ProjectResource projectResourceOneDouble = mock(ProjectResource.class);
        when(resourceRepository.findAll()).thenReturn(Collections.emptyList());

        resourceRepository.add(projectResourceOneDouble);
        when(projectResourceOneDouble.hasProjectCode(codeDouble)).thenReturn(true);
        when(projectResourceOneDouble.hasAccount(emailDouble)).thenReturn(true);
        when(projectResourceOneDouble.isPeriodOverlapping(periodDouble)).thenReturn(true);
        // Act
        boolean result = resourceAllocationService.isResourceOverlapping(codeDouble, emailDouble, periodDouble);

        // Assert
        assertFalse(result);

    }



}