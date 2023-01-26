package org.switch2022.project.utils.mapper;

import org.switch2022.project.model.Account;
import org.switch2022.project.utils.dto.AccountDTO;


public class AccountMapper {
    // DTO -> ACCOUNT
    /**
     * This method converts an account into a DTO.
     *
     * @param account one must convert.
     * @return DTO carrying data.
     */
    public static AccountDTO accountToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.name = account.getAccountName();
        accountDTO.email = account.getEmail();
        accountDTO.status = account.getAccountStatus();
        return accountDTO;
    }

}

