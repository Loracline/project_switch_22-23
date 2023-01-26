package org.switch2022.project.controller;

import org.switch2022.project.model.Company;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.dto.AllocationDTO;
import org.switch2022.project.utils.dto.ProjectDTO;

/**
 * Class AddUserToProjectController acts as an intermediary between the
 * user interface (UI) and the business logic underlying the following USs:
 * "US011 - As Manager, I want to associate a user as Team Member of a
 * project."
 * "US012 - As Manager, I want to define the PO of a project."
 * "US013 - As Manager, I want to define the SM of a project."
 */
public class AddUserToProjectController {
    /**
     * Attributes
     */
    private final Company company;


    /**
     * Constructor
     */
    public AddUserToProjectController(Company company) {
        this.company = company;
    }


    /**
     * This method adds an account with the "User" profile to a certain project
     * with a given role.
     *
     * @param emailManager  of the actor performing the task.
     * @param accountDTO    object carrying data about the selected account.
     * @param projectDTO    object carrying data about the selected project.
     * @param allocationDTO object carrying data about the allocation of the
     *                      account in the project.
     * @return TRUE if added, and FALSE otherwise.
     */
    public boolean addUserToProject(String emailManager, AccountDTO accountDTO,
                                    ProjectDTO projectDTO,
                                    AllocationDTO allocationDTO) {
        boolean result = false;
        if (company.validateManager(emailManager)) {
            result = this.company.addUserToProject(accountDTO, projectDTO,
                    allocationDTO);
        }
        return result;
    }
}
