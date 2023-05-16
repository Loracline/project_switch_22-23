package org.switch2022.project.ddd.dto.mapper;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.dto.AccountDto;


import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapper {

    /**
     * This method converts an account into a Dto.
     *
     * @param account one must convert.
     * @return Dto carrying data.
     */
    public AccountDto accountToDto(Account account) {
        return new AccountDto(account.getAccountName(), account.getAccountEmail(),
                account.getAccountStatus());
    }

    /**
     * This method converts a list of accounts into a list of accountDtos.
     *
     * @param accounts list one must convert in Dto
     * @return a list of accountDtos.
     */
    public List<AccountDto> listAccountsToDto(List<Account> accounts) {
        List<AccountDto> accountDtos = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            AccountDto accountDTO = accountToDto(accounts.get(i));
            accountDtos.add(accountDTO);
        }
        return accountDtos;
    }
}
