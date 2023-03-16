package org.switch2022.project.dto.mapper;


import org.switch2022.project.dto.AccountEmailStatusDto;
import org.switch2022.project.model.Account;

import java.util.ArrayList;
import java.util.List;

public final class AccountListEmailStatusMapper {
    /**
     * Constructor of the class AccountMapper.
     */
    private AccountListEmailStatusMapper() {
    }

    // DTO -> ACCOUNT

    /**
     * This method converts an account into a Dto.
     *
     * @param account one must convert.
     * @return Dto carrying data.
     */
    private static AccountEmailStatusDto accountToDto(Account account) {
        return new AccountEmailStatusDto(account.getEmail(), account.isAccountStatus());
    }

    // LIST OF ACCOUNTS -> LIST OF DTO

    /**
     * This method converts a list of accounts into a list of accountDto's.
     *
     * @param accounts list one must convert in Dto
     * @return a list of accountDto's.
     */
    public static List<AccountEmailStatusDto> listAccountsToDto(List<Account> accounts) {
        List<AccountEmailStatusDto> accountsDto = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            AccountEmailStatusDto accountDTO = accountToDto(accounts.get(i));
            accountsDto.add(accountDTO);
        }
        return accountsDto;
    }
}
