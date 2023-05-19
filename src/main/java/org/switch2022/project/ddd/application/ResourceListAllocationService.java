package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceRepository;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.dto.AccountDto;
import org.switch2022.project.ddd.dto.mapper.AccountMapper;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.util.List;

/**
 * Service class responsible for allocating resources and retrieving a list of accounts by project.
 */
@Service
public class ResourceListAllocationService {

    @SuppressWarnings("all")
    @Autowired
    private IProjectResourceRepository resourceRepository;

    @SuppressWarnings("all")
    @Autowired
    private IAccountRepository accountRepository;

    @SuppressWarnings("all")
    @Autowired
    private AccountMapper mapper;

    /**
     * Retrieves a list of account DTOs by project.
     *
     * @param stringOf_projectCode The project code as a string representation.
     * @return A list of AccountDto objects representing the accounts allocated to the project.
     */
    public List<AccountDto> listAccountsInProject(String stringOf_projectCode) {
        Code projectCode = Code.getCodeFromString(stringOf_projectCode);

        List<Email> emails = resourceRepository.findAccountEmailsByProjectCode(projectCode);

        if (emails.isEmpty()) {
            throw new NotFoundInRepoException("There are no resources allocated to this project.");
        }

        List<Account> accounts = accountRepository.findAccountsByEmails(emails);

        return mapper.listAccountsToDto(accounts);
    }
}
