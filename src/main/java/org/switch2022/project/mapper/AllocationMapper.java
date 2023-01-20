package org.switch2022.project.mapper;

import org.switch2022.project.DTO.AllocationDTO;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountInProject;
import org.switch2022.project.model.Project;

import java.time.LocalDate;

public class AllocationMapper {

    /**
     * Method to allocate an User to a Project as a Team Member.
     * @param dto
     * @return newAccount
     */

    public AccountInProject addTeamMemberToProject(AllocationDTO dto) {
        Account account = dto.account;
        Project project = dto.project;
        float costPerHour = dto.costPerHour;
        float percentageAllocation = dto.percentageAllocation;
        LocalDate startDate = dto.startDate;

        AccountInProject newAccount = new AccountInProject(account, project, costPerHour,percentageAllocation, startDate);
        newAccount.setRole("team member");
        return newAccount;
    }

    /**
     * Method to allocate an User to a Project as a Product Owner.
     */

    /**
     * Method to allocate an User to a Project as a Scrum Master.
     */
}
