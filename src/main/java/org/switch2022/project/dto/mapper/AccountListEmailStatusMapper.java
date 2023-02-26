package org.switch2022.project.dto.mapper;


import org.switch2022.project.dto.AccountEmailStatusDTO;
import org.switch2022.project.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountListEmailStatusMapper {
    /**
     * Constructor of the class AccountMapper.
     */
    private AccountListEmailStatusMapper() {
    }

    // DTO -> ACCOUNT

    /**
     * This method converts an account into a DTO.
     *
     * @param account one must convert.
     * @return DTO carrying data.
     */
    private static AccountEmailStatusDTO accountToDTO(Account account) {
        return new AccountEmailStatusDTO(account.getEmail(), account.isAccountStatus());
    }

    // LIST OF ACCOUNTS -> LIST OF DTO
    /**
     * This method converts a list of accounts into a list of accountDTOs.
     *
     * @param accounts list one must convert in DTO
     * @return a list of accountDTOs.
     */
    public static List<AccountEmailStatusDTO> listAccountsToDTO(List<Account> accounts) {
        List<AccountEmailStatusDTO> accountsDTO = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            AccountEmailStatusDTO accountDTO = accountToDTO(accounts.get(i));
            accountsDTO.add(accountDTO);
        }
        return accountsDTO;
    }
}
