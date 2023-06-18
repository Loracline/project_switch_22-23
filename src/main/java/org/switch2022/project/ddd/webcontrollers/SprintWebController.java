package org.switch2022.project.ddd.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.ddd.application.*;
import org.switch2022.project.ddd.dto.*;
import org.switch2022.project.ddd.dto.mapper.ProjectCodeDtoAssembler;
import org.switch2022.project.ddd.dto.mapper.SprintDtoAssembler;

import java.util.ArrayList;
import java.util.List;

/**
 * The SprintWebController class is a REST controller for handling requests related to
 * sprints.
 */
@CrossOrigin(maxAge = 3600, origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/sprints")
public class SprintWebController {
    /**
     * The CreateSprintService used to create a new sprint.
     */
    @Autowired
    CreateSprintService createSprintService;
    @Autowired
    SprintStatusChangeService sprintStatusChangeService;
    @Autowired
    UserStoriesInSprintService userStoriesInSprintService;
    @Autowired
    AddUserStoryToSprintBacklogService addUserStoryToSprintBacklogService;
    @Autowired
    SprintListService sprintListService;

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
     * Handles a PATCH request to change the status of a sprint with the specified sprint ID.
     *
     * @param sprintStatusDto with the necessary info to change the state of a sprint.
     * @return ResponseEntity<Object> representing the result of the status change operation: if
     * the status change is successful, returns a ResponseEntity with HTTP status
     * code 200 (OK); if the sprint with the specified ID is not found, returns a
     * ResponseEntity with HTTP status code 404 (NOT FOUND).
     */

    @PatchMapping("/{sprintId}")
    public ResponseEntity<Object> changeSprintStatus(@RequestBody SprintStatusDto sprintStatusDto,
                                                     @PathVariable String sprintId) {
        sprintStatusChangeService.changeSprintStatus(sprintStatusDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Handles a POST request to create a new userStoryInSprint.
     *
     * @param userStoryInSprintDto containing the information needed to create a new UserStoryInSprint.
     * @return A ResponseEntity containing the status code of 201 (CREATED).
     */
    @PostMapping("/sprint_backlog")
    public ResponseEntity<Object> addUserStoryToSprintBacklog(@RequestBody UserStoryInSprintDto userStoryInSprintDto) {
        addUserStoryToSprintBacklogService.addUserStoryToSprint(userStoryInSprintDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of sprints from a project identified by the provided project code.
     *
     * @param projectCode The project code used to identify the project.
     * @return ResponseEntity containing the list of sprints represented as SprintPrimitiveTypesDto objects.
     */
    @GetMapping("/{projectCode}")
    public ResponseEntity<Object> listSprintsFromProject(@PathVariable ProjectCodeStringDto projectCode) {
        ProjectCodeValueObjectDto codeValueObjectDto = ProjectCodeDtoAssembler.convertToValueObject(projectCode);
        List<SprintValueObjectsDto> valueObjectsDtos = sprintListService.listSprintsFromProject(codeValueObjectDto);
        List<SprintPrimitiveTypesDto> primitiveTypesDtos = new ArrayList<>();
        for (int i = 0; i < valueObjectsDtos.size(); i++) {
            SprintPrimitiveTypesDto primitiveTypesDto =
                    SprintDtoAssembler.convertToPrimitiveTypes(valueObjectsDtos.get(i));
            primitiveTypesDtos.add(primitiveTypesDto);
        }
        return new ResponseEntity<>(primitiveTypesDtos, HttpStatus.OK);
    }
    /**
     * Handles a GET request to retrieve a list of user stories of the scrumBoard.
     *
     * @return A ResponseEntity containing a list of User stories objects and a status code of
     * 200 (OK).
     */
    @GetMapping("/{projectCode}/scrumBoard")
    @ResponseBody
    public ResponseEntity<List<UserStoryDto>> getScrumBoard(@PathVariable ProjectCodeStringDto projectCode) {
        ProjectCodeValueObjectDto codeValueObjectDto = ProjectCodeDtoAssembler.convertToValueObject(projectCode);
        List<UserStoryDto> userStories = userStoriesInSprintService.getScrumBoard(codeValueObjectDto);
        return new ResponseEntity<>(userStories, HttpStatus.OK);
    }
}
