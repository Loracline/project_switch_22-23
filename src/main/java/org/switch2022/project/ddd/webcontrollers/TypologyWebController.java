package org.switch2022.project.ddd.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.switch2022.project.ddd.application.TypologyService;
import org.switch2022.project.ddd.dto.TypologyCreationDto;


/**
 * The TypologyWebController class is a REST controller for handling requests related to typologies.
 */
@RestController
@RequestMapping("/typologies")
public class TypologyWebController {
    /**
     * The TypologyService used to create a new typology.
     */
    @SuppressWarnings("all")
    @Autowired
    private TypologyService service;

    /**
     * Handles a POST request to create a new typology.
     *
     * @param dto object containing the information needed to create a new typology.
     * @return a ResponseEntity containing the typology name and a status code of 201 (CREATED)or a status code of 409
     * (CONFLICT), or a status code of 409 (CONFLICT) plus an exception message in case an exception is caught.
     */
    @PostMapping()
    public ResponseEntity<Object> createTypology(@RequestBody TypologyCreationDto dto) {
        try {
            service.createTypology(dto.typologyName);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
