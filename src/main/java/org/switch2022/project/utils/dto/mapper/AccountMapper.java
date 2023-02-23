package org.switch2022.project.utils.dto.mapper;

import org.switch2022.project.model.Account;
import org.switch2022.project.utils.dto.AccountDTO;
import java.util.ArrayList;
import java.util.List;


public class AccountMapper {
    /**
     * Constructor of the class AccountMapper.
     */
    private AccountMapper(){}

    // DTO -> ACCOUNT
    /**
     * This method converts an account into a DTO.
     *
     * @param account one must convert.
     * @return DTO carrying data.
     */
    public static AccountDTO accountToDTO(Account account) {
        return new AccountDTO(account.getAccountName(),account.getEmail(),account.isAccountStatus());
    }

    // LIST OF ACCOUNTS -> LIST OF DTO
    /**
     * This method converts a list of accounts into a list of accountDTOs.
     *
     * @param accounts list one must convert in DTO
     * @return a list of accountDTOs.
     */
    public static List<AccountDTO> listAccountsToDTO(List<Account> accounts) {
        List<AccountDTO> accountsDTO = new ArrayList<>();
        int i = 0;
        while (i < accounts.size()) {
            AccountDTO accountDTO = accountToDTO(accounts.get(i));
            accountsDTO.add(accountDTO);
            i++;
        }
        return accountsDTO;
    }

}

