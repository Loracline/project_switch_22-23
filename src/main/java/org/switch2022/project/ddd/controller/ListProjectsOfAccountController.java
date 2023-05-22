package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.ProjectListAllocationService;
import org.switch2022.project.ddd.dto.ProjectDto;

import java.util.List;

/**
 * Controller class responsible for implementing US016: "As Authenticated User, I want to get a list of all
 * projects I'm currently allocated to."
 */
@Controller
public class ListProjectsOfAccountController {
    @Autowired
    private ProjectListAllocationService projectListAllocationService;

    public List<ProjectDto> listProjectsByAccount(String email){
        return projectListAllocationService.listProjectsByAccount(email);
    }

}
