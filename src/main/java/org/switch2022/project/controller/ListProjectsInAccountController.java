package org.switch2022.project.controller;
import org.switch2022.project.model.Company;
import org.switch2022.project.model.Project;
import org.switch2022.project.utils.dto.UserListProjectsDTO;
import org.switch2022.project.utils.mapper.UserListProjectsMapper;
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

    public List<UserListProjectsDTO> listProjectsByAccount(String emailUser) {
        List<UserListProjectsDTO> userListProjectsDTO = new ArrayList<>();
        if (company.validateUser(emailUser)) {
            List<Project> projects = company.listProjectsByAccount(emailUser);
            userListProjectsDTO = UserListProjectsMapper.getListOfProjectsDTO(projects);
        }
        return userListProjectsDTO;
    }
}
