package org.switch2022.project.controller;

import org.switch2022.project.model.Company;
import org.switch2022.project.model.Project;
import org.switch2022.project.utils.dto.ListProjectsDTO;
import org.switch2022.project.utils.mapper.ListOfProjectsMapper;

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
    private final ListOfProjectsMapper mapper;

    /**
     * GetListOfProjectsController constructor
     */
    public GetListOfProjectsController(Company company, ListOfProjectsMapper mapper) {
        this.company = company;
        this.mapper = mapper;
    }

    /**
     * This method first verify if Manager has permission to generate a list of Projects DTO
     * and then returns it
     *
     * @param email Email of the account to be verified
     * @return a list of Projects DTO
     */
    public List<ListProjectsDTO> getListOfProjects(String email) {
        List<ListProjectsDTO> listOfProjectsDTO = new ArrayList<>();
        if (company.validateManager(email)) {
            List<Project> projects = company.listAllProjects();
            listOfProjectsDTO = mapper.getListOfProjectsDTO(projects);
        }
        return listOfProjectsDTO;
    }
}
