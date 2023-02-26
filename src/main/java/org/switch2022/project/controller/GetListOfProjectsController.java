package org.switch2022.project.controller;

import org.switch2022.project.container.Company;
import org.switch2022.project.model.Profile;
import org.switch2022.project.model.Project;
import org.switch2022.project.dto.ManagerListProjectsDTO;
import org.switch2022.project.dto.mapper.ManagerListProjectsMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Class GetListOfProjectsController is built to allow access to ProjectContainer
 * in Company Class.
 */
public class GetListOfProjectsController {
    /**
     * Attributes of the class GetListOfProjectController.
     */
    private final Company company;

    /**
     * GetListOfProjectsController constructor
     */
    public GetListOfProjectsController(Company company) {
        this.company = company;
    }

    /**
     * This method first verify if Manager has permission to generate a list of Projects DTO
     * and then returns it
     *
     * @param email Email of the account to be verified
     * @return a list of Projects DTO
     */
    public List<ManagerListProjectsDTO> getListOfProjects(String email) {
        List<ManagerListProjectsDTO> listOfProjectsDTO = new ArrayList<>();
        if (company.validateProfileRequired(email, Profile.MANAGER)) {
            List<Project> projects = company.listAllProjects();
            listOfProjectsDTO = ManagerListProjectsMapper.getListOfProjectsDTO(projects);
        }
        return listOfProjectsDTO;
    }
}
