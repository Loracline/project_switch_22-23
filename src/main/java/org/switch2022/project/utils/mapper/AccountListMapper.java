package org.switch2022.project.utils.mapper;

import org.switch2022.project.model.Account;
import org.switch2022.project.utils.Util;
import org.switch2022.project.utils.dto.AccountDTO;

import java.util.ArrayList;
import java.util.List;

public class AccountListMapper {

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
        while (Util.isLower(i, accounts.size())) {
            AccountDTO accountDTO = AccountMapper.accountToDTO(accounts.get(i));
            accountsDTO.add(accountDTO);
            i++;
        }
        return accountsDTO;
    }


}
