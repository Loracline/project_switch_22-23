package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.dto.AccountDto;
import org.switch2022.project.ddd.dto.mapper.AccountMapper;

import java.util.List;
import java.util.Optional;

/**
 * Class AccountListService allows to list all accounts registered.
 */
@Service
public class AccountListService {
    @Qualifier("account_jpa")

    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private AccountMapper accountMapper;

    /**
     * This method asks the repository for the list of all Accounts and returns it.
     * @return list with all accounts.
     */
    public List<AccountDto> listAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accountMapper.listAccountsToDto(accounts);
    }
    /**
     * Retrieves an optional account DTO (Data Transfer Object) based on the provided email.
     *
     * @param email The email of the account to be retrieved.
     * @return An optional containing the account DTO corresponding to the provided email, or an empty optional if the
     * account is not found.
     */
    public Optional<AccountDto> getAccountByEmail(Email email) {
       Account account = accountRepository.findAccountByEmail(email.getEmailAddress());
        Optional<AccountDto> opAccountDto = Optional.empty();
        if (account != null) {
            opAccountDto = Optional.of(accountMapper.accountToDto(account));
        }
        return opAccountDto;
    }
}
