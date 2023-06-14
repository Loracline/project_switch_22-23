package org.switch2022.project.ddd.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.ddd.application.UsService;
import org.switch2022.project.ddd.application.UserStoriesInSprintService;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;
import org.switch2022.project.ddd.dto.UserStoryStatusDto;

@CrossOrigin(maxAge = 3600, origins={"http://localhost:3000"})
@RestController
@RequestMapping("/userStories")
public class UserStoryWebController {

    /**
     * The UsService used to create a user story.
     */
    @Autowired
    private UsService usService;
    @Autowired
    private UserStoriesInSprintService usInSprintService;

    /**
     * Handles the HTTP POST request to create a User Story.
     *
     * @param userStoryCreationDto The DTO containing the data for creating the User Story.
     * @return A ResponseEntity with the created User Story ID and the HTTP status code 201
     * (CREATED).
     * @throws Exception If an error occurs during the creation process.
     */
    @PostMapping
    public ResponseEntity<Object> createUs(@RequestBody UserStoryCreationDto userStoryCreationDto) {
        UsId usId = usService.createUs(userStoryCreationDto);
        return new ResponseEntity<>(usId, HttpStatus.CREATED);
    }
    /**
     * Updates the status of a user story in a sprint using a JSON Patch document.
     *
     * @param userStoryStatusDto the User Story Status DTO containing the JSON Patch document
     * @return a ResponseEntity containing the HTTP status code and a response body
     */
    @PatchMapping("/{usId}")
    @ResponseBody
    public ResponseEntity<Object> changeUserStoryStatus(@RequestBody UserStoryStatusDto userStoryStatusDto) {
        usInSprintService.changeUserStoryStatus(userStoryStatusDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

