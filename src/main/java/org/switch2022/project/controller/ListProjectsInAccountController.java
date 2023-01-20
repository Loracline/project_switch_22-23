package org.switch2022.project.controller;

import org.switch2022.project.DTO.ProjectDTO;
import org.switch2022.project.mapper.ProjectMapper;
import org.switch2022.project.model.Company;
import org.switch2022.project.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ListProjectsInAccountController {

    private Company company;


    public List<ProjectDTO> listProjectsByAccount(String email) {
        List <ProjectDTO> projectsDTO = new ArrayList<>();
        if( company.validateUser(email)) {
            List<Project> projects = company.listProjectsByAccount(email);
            ProjectMapper projectMapper = new ProjectMapper();
            projectsDTO = projectMapper.projectsToDTO(projects);
        }
        return projectsDTO;
    }

}
