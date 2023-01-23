package org.switch2022.project.utils.mapper;

import org.switch2022.project.model.Account;
import org.switch2022.project.utils.Helper;
import org.switch2022.project.utils.dto.AccountDTO;

import java.util.ArrayList;
import java.util.List;

public class AccountMapper {
    // DTO -> ACCOUNT

    /**
     * This method converts an account into a DTO.
     *
     * @param account one must convert.
     * @return DTO carrying data.
     */
    public AccountDTO getDTOFromAccount(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.name = account.getAccountName();
        accountDTO.email = account.getEmail();
        accountDTO.phoneNumber = account.getPhoneNumber();
        accountDTO.profile = account.getProfile();
        accountDTO.status = account.getAccountStatus();
        accountDTO.photo = account.getPhoto();
        return accountDTO;
    }



    // ACCOUNT -> DTO

    /**
     * This method extracts data from DTO to an account.
     *
     * @param accountDTO data transfer object carrying data needed.
     * @return an account.
     */
    public Account getAccountFromDTO(AccountDTO accountDTO) {
        Account account = new Account(accountDTO.name, accountDTO.email,
                accountDTO.phoneNumber, accountDTO.photo);
        account.setProfile(accountDTO.profile);
        account.setStatus(accountDTO.status);
        return account;
    }



    // LIST OF ACCOUNTS -> LIST OF DTO

    /**
     * This method converts a list of accounts into a list of accountDTOs.
     *
     * @param accounts list one must convert in DTO
     * @return a list of accountDTOs.
     */
    public List<AccountDTO> getListDTOFromAccounts(List<Account> accounts) {
        List<AccountDTO> accountsDTO = new ArrayList<>();
        int i = 0;
        while (Helper.isLower(i, accounts.size())) {
            AccountDTO accountDTO = getDTOFromAccount(accounts.get(i));
            accountsDTO.add(accountDTO);
            i++;
        }
        return accountsDTO;
    }
}

