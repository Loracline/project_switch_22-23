package org.switch2022.project.controller;


import org.switch2022.project.model.Company;
import org.switch2022.project.model.Project;

import org.switch2022.project.utils.dto.ProjectDTO;
import org.switch2022.project.utils.mapper.ProjectListMapper;

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

    public List<ProjectDTO> listProjectsByAccount(String emailUser) {
        List<ProjectDTO> projsDTO = new ArrayList<>();
        if (company.validateUser(emailUser)) {
            List<Project> projects = company.listProjectsByAccount(emailUser);
            projsDTO = ProjectListMapper.projectsToDTO(projects);
        }
        return projsDTO;
    }
}
