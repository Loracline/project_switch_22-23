package org.switch2022.project.controller;
import org.switch2022.project.model.Company;
import org.switch2022.project.model.Project;
import org.switch2022.project.utils.dto.ProjectDTOus016;
import org.switch2022.project.utils.mapper.ProjectMapperUS016;
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
     * @return projectDTOus016
     */

    public List<ProjectDTOus016> listProjectsByAccount(String emailUser) {
        List<ProjectDTOus016> projectDTOus016 = new ArrayList<>();
        if (company.validateUser(emailUser)) {
            List<Project> projects = company.listProjectsByAccount(emailUser);
            projectDTOus016 = ProjectMapperUS016.getListOfProjectsDTO(projects);
        }
        return projectDTOus016;
    }
}
