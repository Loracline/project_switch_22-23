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

    // LIST OF ACCOUNTS -> LIST OF DTO

    /**
     * This method converts a list of accounts into a list of accountDto's.
     *
     * @param accounts list one must convert in Dto
     * @return a list of accountDto's.
     */

    public List<AccountDto> accountsToDtoList(List<Account> accounts) {
        List<AccountDto> accountsDTO = new ArrayList<>();
        int i = 0;
        while (i < accounts.size()) {
            AccountDto accountDTO = accountToDto(accounts.get(i));
            accountsDTO.add(accountDTO);
            i++;
        }
        return accountsDTO;
    }

}
