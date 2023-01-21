package org.switch2022.project.utils.mapper;

import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountMapper {
    public AccountDTO accountToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.name = account.getAccountName();
        accountDTO.email = account.getEmail();
        accountDTO.phoneNumber = account.getPhoneNumber();
        accountDTO.profile = account.getProfile();
        accountDTO.status = account.getAccountStatus();
        accountDTO.photo = account.getPhoto();
        return accountDTO;
    }

    /**
     * Method that transforms an AccountDTO into an instance of Account
     * @param accountDTO
     * @return new instance of account
     */
    public Account accountFromDTO(AccountDTO accountDTO) {
        Account account = new Account(accountDTO.name, accountDTO.email,
                accountDTO.phoneNumber, accountDTO.photo);
        account.setProfile(accountDTO.profile);
        account.setStatus(accountDTO.status);
        return account;
    }

    public List<AccountDTO> accountsToDTO(List<Account> accounts) {
        List<AccountDTO> accountsDto = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            AccountDTO accountDTO = accountToDTO(accounts.get(i));
            accountsDto.add(accountDTO);
        }
        return accountsDto;
    }
}

