package org.switch2022.project.controller;


import org.switch2022.project.model.Company;
import org.switch2022.project.model.Project;

import org.switch2022.project.utils.dto.GetProjectDTO;
import org.switch2022.project.utils.mapper.ListOfProjectsMapper;

import java.util.ArrayList;
import java.util.List;

public class ListProjectsInAccountController {

    private Company company;

    public ListProjectsInAccountController(Company company) {
        this.company = company;
    }

    /**
     * This method first verifies if User has permission to generate a list of projects
     * and then returns a list of Projects allocated to that User
     *
     * @param emailUser
     * @return
     */

    public List<GetProjectDTO> listProjectsByAccount(String emailUser) {
        List<GetProjectDTO> projsDTO = new ArrayList<>();
        if (company.validateUser(emailUser)) {
            List<Project> projects = company.listProjectsByAccount(emailUser);
            projsDTO = ListOfProjectsMapper.toDTO(projects);
        }
        return projsDTO;
    }
}
