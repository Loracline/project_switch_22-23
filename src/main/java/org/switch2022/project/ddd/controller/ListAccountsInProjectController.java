package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.ResourceListAllocationService;
import org.switch2022.project.ddd.dto.AccountDto;

import java.util.List;

/**
 * Controller class responsible for implementing US014: "As Manager, I want to get a list of all human resources in
 * a project."
 */
@Controller
public class ListAccountsInProjectController {

    @SuppressWarnings("all")
    @Autowired
    private ResourceListAllocationService service;

    /**
     * Retrieves a list of account DTOs by project.
     *
     * @param stringOf_projectCode The project code as a string representation.
     * @return A list of AccountDto objects representing the accounts allocated to the project.
     */
    public List<AccountDto> listAccountsByProject(String stringOf_projectCode) {
        return service.listAccountsInProject(stringOf_projectCode);
    }
}
