package org.switch2022.project.ddd.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.ddd.application.AddUserStoryToSprintBacklogService;
import org.switch2022.project.ddd.application.CreateSprintService;
import org.switch2022.project.ddd.application.UserStoriesInSprintService;
import org.switch2022.project.ddd.domain.model.sprint.UserStoryInSprint;
import org.switch2022.project.ddd.dto.AllocationDto;
import org.switch2022.project.ddd.dto.SprintCreationDto;
import org.switch2022.project.ddd.dto.UserStoryDto;
import org.switch2022.project.ddd.dto.UserStoryInSprintDto;

import java.util.List;

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
    @Autowired
    UserStoriesInSprintService userStoriesInSprintService;
    @Autowired
    AddUserStoryToSprintBacklogService addUserStoryToSprintBacklogService;

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

    /**
     * Handles a GET request to retrieve a list of user stories of the sprint backlog.
     *
     * @return A ResponseEntity containing a list of User stories objects and a status code of
     * 200 (OK).
     */
    @GetMapping("/{sprintId}/userStoriesInSprint")
    @ResponseBody
    public ResponseEntity<List<UserStoryDto>> getSprintBacklog(@PathVariable String sprintId) {
        List<UserStoryDto> userStories = userStoriesInSprintService.getSprintBacklog(sprintId);
        return new ResponseEntity<>(userStories, HttpStatus.OK);
    }

    /**
     * Handles a POST request to create a new userStoryInSprint.
     *
     * @param userStoryInSprintDto containing the information needed to create a new UserStoryInSprint.
     * @return A ResponseEntity containing the status code of 201 (CREATED).
     */
    @PostMapping("/{SprintId}/SprintBacklog")
    public ResponseEntity<Object> addUserStoryToSprintBacklog(@RequestBody UserStoryInSprintDto userStoryInSprintDto,
                                                              @PathVariable String SprintId) {
        if (addUserStoryToSprintBacklogService.addUserStoryToSprint(userStoryInSprintDto)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
