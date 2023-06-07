package org.switch2022.project.ddd.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.ddd.application.ProfileService;
import org.switch2022.project.ddd.dto.ProfileCreationDto;


/**
 * The ProfileWebController class is a REST controller for handling requests related to profiles.
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/profiles")
public class ProfileWebController {

    /**
     * The ProfileService used to create a new profiles.
     */
    @Autowired
    private ProfileService service;

    /**
     * Handles a POST request to create a new profile.
     *
     * @param profileCreationDto The ProfileCreationDto object containing the information needed to create a new
     *                           profile.
     * @return A ResponseEntity containing status code of 201 (CREATED), or a status code of 409 (CONFLICT), or a
     * status code of 409 (CONFLICT) plus an exception message in case an exception is caught.
     */
    @PostMapping()
    public ResponseEntity<Object> createProfile(@RequestBody ProfileCreationDto profileCreationDto) {
        service.createProfile(profileCreationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
