package org.switch2022.project.controller;

import org.switch2022.project.model.Company;
import org.switch2022.project.utils.dto.AccountInProjectDTO;

public class AddUserToProjectController {

    Company company;

    public AddUserToProjectController (Company company) {
        this.company = company;
    }

  /*  public boolean addUserToProject(String emailManager,
                                    AccountInProjectDTO accountInProjectDTO){
        boolean result = false;
        if(company.validateManager(emailManager)){
            result = this.company.addUserToProject(accountInProjectDTO);
        }
        return result;
    }*/
}
