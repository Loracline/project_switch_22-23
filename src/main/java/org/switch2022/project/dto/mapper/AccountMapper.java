package org.switch2022.project.dto.mapper;

import org.switch2022.project.dto.AccountDto;
import org.switch2022.project.model.Account;

import java.util.ArrayList;
import java.util.List;


public class AccountMapper {
    /**
     * Constructor of the class AccountMapper.
     */
    private AccountMapper(){}

    // DTO -> ACCOUNT
    /**
     * This method converts an account into a Dto.
     *
     * @param account one must convert.
     * @return Dto carrying data.
     */
    public static AccountDto accountToDto(Account account) {
        return new AccountDto(account.getAccountName(),account.getEmail(),account.isAccountStatus());
    }

    // LIST OF ACCOUNTS -> LIST OF DTO
    /**
     * This method converts a list of accounts into a list of accountDto's.
     *
     * @param accounts list one must convert in Dto
     * @return a list of accountDto's.
     */
    public static List<AccountDto> listAccountsToDto(List<Account> accounts) {
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
