package org.switch2022.project.controller;

import org.switch2022.project.DTO.AccountInProjectDTO;
import org.switch2022.project.model.Company;

public class AddUserToProjectController {

    Company company;

    public boolean addUserToProject(AccountInProjectDTO accountInProjectDTO){
        return this.company.addUserToProject(accountInProjectDTO);
    }
}
