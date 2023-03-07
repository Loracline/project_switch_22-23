package org.switch2022.project.controller;
import org.switch2022.project.container.Company;
import org.switch2022.project.model.Profile;
import org.switch2022.project.model.Project;
import org.switch2022.project.dto.ProjectDto;
import org.switch2022.project.dto.mapper.ProjectMapper;

import java.util.ArrayList;
import java.util.List;

public class ListProjectsInAccountController {

    private final Company company;

    public ListProjectsInAccountController(Company company) {
        this.company = company;
    }

    /**
     * This method first verifies if User has permission to generate a list of projects
     * and then returns a list of Projects allocated to that User.
     * creates a new list, validates the email thru the company method "validateUser",
     * if User is valid , calls for method listProjectsByAccount on company passing email as argument,
     * returns a list of projects associated with the email given, then calls for getListOfProjectsDTO.
     *
     * @param emailUser email of the user.
     * @return userListProjectsDTO
     */

    public List<ProjectDto> listProjectsByAccount(String emailUser) {
        List<ProjectDto> userListProjectsDTO = new ArrayList<>();
        if (company.validateProfileRequired(emailUser, Profile.USER)) {
            List<Project> projects = company.listProjectsByAccount(emailUser);
            userListProjectsDTO = ProjectMapper.getListOfProjectsDto(projects);
        }
        return userListProjectsDTO;
    }
}
