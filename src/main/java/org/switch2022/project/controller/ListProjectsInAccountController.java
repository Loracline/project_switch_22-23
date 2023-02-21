package org.switch2022.project.controller;
import org.switch2022.project.model.Company;
import org.switch2022.project.model.Profile;
import org.switch2022.project.model.Project;
import org.switch2022.project.utils.dto.ManagerListProjectsDTO;
import org.switch2022.project.utils.mapper.ManagerListProjectsMapper;

import java.util.ArrayList;
import java.util.List;

public class ListProjectsInAccountController {

    private final Company company;

    public ListProjectsInAccountController(Company company) {
        this.company = company;
    }

    /**
     * This method first verifies if User has permission to generate a list of projects
     * and then returns a list of Projects allocated to that User
     *
     * @param emailUser
     * @return userListProjectsDTO
     */

    public List<ManagerListProjectsDTO> listProjectsByAccount(String emailUser) {
        List<ManagerListProjectsDTO> userListProjectsDTO = new ArrayList<>();
        if (company.validateProfileRequired(emailUser, Profile.USER)) {
            List<Project> projects = company.listProjectsByAccount(emailUser);
            userListProjectsDTO = ManagerListProjectsMapper.getListOfProjectsDTO(projects);
        }
        return userListProjectsDTO;
    }
}
