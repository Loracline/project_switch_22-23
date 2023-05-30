package org.switch2022.project.ddd.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.ddd.application.CreateSprintService;
import org.switch2022.project.ddd.dto.SprintCreationDto;

/**
 * The SprintWebController class is a REST controller for handling requests related to
 * sprints.
 */
@CrossOrigin(maxAge = 3600)
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
     * @param sprintCreationDto to create the sprint
     * @return A ResponseEntity containing the sprint code and a status code of 201
     * (CREATED).
     */
    @PostMapping
    public ResponseEntity<Object> createSprint(@RequestBody SprintCreationDto sprintCreationDto) throws Exception {
        try {
            String sprintCode =
                    createSprintService.createSprint(sprintCreationDto.projectCode,
                            sprintCreationDto.startDate);
            return new ResponseEntity<>(sprintCode, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
