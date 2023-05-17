package org.switch2022.project.ddd.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.ddd.application.ProjectCreationService;
import org.switch2022.project.ddd.application.ProjectListService;
import org.switch2022.project.ddd.application.ProjectService;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.dto.ProjectCreationDto;
import org.switch2022.project.ddd.dto.ProjectDto;

import java.util.List;

/**
 * The ProjectWebController class is a REST controller for handling requests related to projects.
 */
@RestController
@RequestMapping("/projects")
public class ProjectWebController {

    /**
     * The ProjectCreationService used to create a new project.
     */
    @Autowired
    private ProjectCreationService projectCreationService;
    @Autowired
    private ProjectService projectService;

    /**
     * The ProjectListService used to retrieve a list of all projects.
     */
    @Autowired
    private ProjectListService projectListService;

    /**
     * Handles a POST request to create a new project.
     *
     * @param projectCreationDto The ProjectCreationDto object containing the information needed to create a new project.
     * @return A ResponseEntity containing the project code and a status code of 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<Object> createProject(@RequestBody ProjectCreationDto projectCreationDto) {

        String projectCode = projectCreationService.createProject(projectCreationDto);
        return new ResponseEntity<>(projectCode, HttpStatus.CREATED);
    }

    /**
     * Handles a GET request to retrieve a list of all projects.
     *
     * @return A ResponseEntity containing a list of ProjectDto objects and a status code of 200 (OK).
     */
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<ProjectDto>> ListProjects() {
        List<ProjectDto> projects = projectListService.requestAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    /**
     * Handles a GET request to retrieve a list of all user stories.
     *
     * @return A ResponseEntity containing a list of User stories objects and a status code of 200 (OK).
     */
    @GetMapping("/{code}/productBacklog")
    @ResponseBody
    public ResponseEntity<List<UserStory>> getProductBacklog(@PathVariable String code) {
        List<UserStory> userStories = projectService.getProductBacklog(code);
        return new ResponseEntity<>(userStories, HttpStatus.OK);
    }
}

