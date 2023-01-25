package org.switch2022.project.utils.mapper;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountInProject;
import org.switch2022.project.model.Project;
import org.switch2022.project.utils.dto.AllocationDTO;

import java.time.LocalDate;

public class AccountInProjectDTOMapper {

    /**
     * Method that creates an AccountInProject from an AllocationDTO
     *
     * @param dto
     * @return new instance of AccountInProject
     */

    public AccountInProject accountInProjectFromDTO(AccountInProjectDTO dto) {
        ProjectMapper projectMapper = new ProjectMapper();
        AccountMapper accountMapper = new AccountMapper();


        Account account = accountMapper.getAccountFromDTO(dto.accountDTO);
        Project project = projectMapper.getProjectFromDTO(dto.projectDTO);
   /* public AccountInProject accountInProjectFromDTO(AllocationDTO dto) {
        Account account = getAccountFromDTO(dto.accountDTO);
        Project project = fromDTO(dto.projectDTO);

        float costPerHour = dto.costPerHour;
        float percentageAllocation = dto.percentageAllocation;
        LocalDate startDate = dto.startDate;
        String role = dto.role;

        AccountInProject accountInProject = new AccountInProject(account, project,
                role, costPerHour, percentageAllocation, startDate);
        return accountInProject;
    }
}
