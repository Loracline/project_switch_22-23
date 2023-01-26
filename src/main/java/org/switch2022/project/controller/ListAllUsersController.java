package org.switch2022.project.controller;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.Company;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.mapper.AccountMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ListAllUsersController is responsible for handling requests to
 * retrieve a list of all users in the system.
 */
public class ListAllUsersController {
    /**
     * Attributes
     */
    private final Company company;


    /**
     * Constructor
     */
    public ListAllUsersController(Company company) {
        this.company = company;
    }


    /**
     * This method lists all accounts with "User" profile after validating the
     * e-mail of the actor.
     *
     * @param actorEmail must be a "Manager" profile.
     * @return list of all accounts with the profile "User".
     */
    public List<AccountDTO> listAllUsers(String actorEmail) {
        List<Account> users = new ArrayList<>();
        if (company.validateManager(actorEmail)) {
            users = company.listAllUsers();
        }
        return AccountMapper.listAccountsToDTO(users);
    }
}
