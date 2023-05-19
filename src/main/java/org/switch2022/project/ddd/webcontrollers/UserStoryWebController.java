package org.switch2022.project.ddd.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.switch2022.project.ddd.application.UsService;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;

@RestController
@RequestMapping("/userStories/")
public class UserStoryWebController {

    /**
     * The UsService used to create a user story.
     */
        @Autowired
        private UsService usService;

    /**
     * Handles the HTTP POST request to create a User Story.
     *
     * @param projectCodeOfInterest The project code of interest for the User Story.
     * @param userStoryCreationDto The DTO containing the data for creating the User Story.
     * @return A ResponseEntity with the created User Story ID and the HTTP status code 201 (CREATED).
     * @throws Exception If an error occurs during the creation process.
     */
        @PostMapping
        public ResponseEntity<Object> createUs(@RequestBody String projectCodeOfInterest,
                                               UserStoryCreationDto userStoryCreationDto) throws Exception {
            UsId usId = usService.createUs(projectCodeOfInterest, userStoryCreationDto);
            return new ResponseEntity<>(usId, HttpStatus.CREATED);
        }
    }

