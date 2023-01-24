package org.switch2022.project.mapper;

import org.switch2022.project.DTO.AccountDTO;
import org.switch2022.project.model.Account;
import java.util.ArrayList;
import java.util.List;

public class AccountMapper {
    public static AccountDTO accountToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();

        accountDTO.name = account.getName();
        accountDTO.email = account.getEmail();
        accountDTO.phoneNumber = account.getPhoneNumber();
        accountDTO.profile = account.getProfile();
        accountDTO.status = account.getStatus();
        accountDTO.photo = account.getPhoto();
        return accountDTO;
    }

    public static List<AccountDTO> cloneList(List<Account> accounts) {
        List<AccountDTO> accountsDto = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            AccountDTO accountDTO = accountToDTO(accounts.get(i));
            accountsDto.add(accountDTO);
        }
        return accountsDto;
    }

    public static boolean accountsDTOListAreEquals(List<AccountDTO> listOne, List<AccountDTO> listTwo) {
        boolean result = false;
        if (listOne.size() == listTwo.size()) {
            for (int i = 0; i < listOne.size(); i++) {
                if (listOne.get(i).equals(listTwo.get(i))) {
                    result = true;
                }
            }
        }
        return result;
    }

    public static boolean accountsListAreEquals(List<Account> listOne, List<Account> listTwo) {
        boolean result = false;
        if (listOne.size() == listTwo.size()) {
            for (int i = 0; i < listOne.size(); i++) {
                if (listOne.get(i).equals(listTwo.get(i))) {
                    result = true;
                }
            }
        }
        return result;
    }
}

