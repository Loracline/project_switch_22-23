package org.switch2022.project.ddd.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.switch2022.project.ddd.application.CreateSprintService;

/**
 * The SprintWebController class is a REST controller for handling requests related to
 * sprints.
 */

@RestController
@RequestMapping("/sprints")
public class SprintWebController {
    /**
     * The CreateSprintService used to create a new sprint.
     */
    @Autowired
    CreateSprintService createSprintService;

    /**
     * Handles a POST request to create a new sprint.
     *
     * @param projectCode to add the sprint to.
     * @param startDate of the sprint to be created.
     * @return A ResponseEntity containing the sprint code and a status code of 201
     * (CREATED).
     */
    @PostMapping
    public ResponseEntity<Object> createSprint(@RequestBody String projectCode,
                                               String startDate) throws Exception {
        String sprintCode = createSprintService.createSprint(projectCode, startDate);
        return new ResponseEntity<>(sprintCode, HttpStatus.CREATED);
    }
}
