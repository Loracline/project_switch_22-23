package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.ProjectListService;
import org.switch2022.project.ddd.dto.ProjectDto;

import java.util.List;

@Controller
public class ProjectListController {
    /**
     * The ProjectController class serves as an intermediary between the user interface
     * (UI) and the business logic of list all projects.
     * This controller handles the use case specified in the US015 where it is requested a list of all projects.
     */
    @Autowired
    ProjectListService projectListService;

    /**
     * This method requests a list of all projects
     *
     * @return a projectDto list
     */
    public List<ProjectDto> listAllProjects() {
        return projectListService.requestAllProjects();
    }
}
