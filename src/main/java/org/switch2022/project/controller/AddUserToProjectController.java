package org.switch2022.project.controller;

import org.switch2022.project.model.Company;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.dto.AllocationDTO;
import org.switch2022.project.utils.dto.ProjectDTO;

public class AddUserToProjectController {

    Company company;

    public AddUserToProjectController (Company company) {
        this.company = company;
    }

    public boolean addUserToProject(String emailManager, AccountDTO accountDTO,
                                    ProjectDTO projectDTO,
                                    AllocationDTO allocationDTO){
        boolean result = false;
        if(company.validateManager(emailManager)){
            result = this.company.addUserToProject(accountDTO,projectDTO,
                    allocationDTO);
        }
        return result;
    }
}
