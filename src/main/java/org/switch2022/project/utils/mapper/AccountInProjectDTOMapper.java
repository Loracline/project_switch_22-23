package org.switch2022.project.utils.mapper;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountInProject;
import org.switch2022.project.model.Project;
import org.switch2022.project.utils.dto.AllocationDTO;

import java.time.LocalDate;

import static org.switch2022.project.utils.mapper.AccountMapper.getAccountFromDTO;
import static org.switch2022.project.utils.mapper.ProjectMapper.fromDTO;

public class AccountInProjectDTOMapper {

    /**
     * Method that creates an AccountInProject from an AllocationDTO
     *
     * @param dto instance of accountDTO from which information is going to be retrieved
     * @return new instance of AccountInProject
     */

   /* public AccountInProject accountInProjectFromDTO(AllocationDTO dto) {
        Account account = getAccountFromDTO(dto.accountDTO);
        Project project = fromDTO(dto.projectDTO);

        float costPerHour = dto.costPerHour;
        float percentageAllocation = dto.percentageAllocation;
        LocalDate startDate = dto.startDate;
        String role = dto.role;

        return new AccountInProject(account, project,
                role, costPerHour, percentageAllocation, startDate);
    }*/
}
