package org.switch2022.project.ddd.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.ddd.application.TypologyService;
import org.switch2022.project.ddd.dto.TypologyCreationDto;
import org.switch2022.project.ddd.dto.TypologyDto;

import java.util.List;


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
        if (service.createTypology(dto.typologyName)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    /**
     * Handles a GET request to retrieve a list of all typologies.
     *
     * @return A ResponseEntity containing a list of TypologyDto objects and a status code of 200 (OK).
     */
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<TypologyDto>> listAllTypologies() {
        List<TypologyDto> typologies = service.requestAllTypologies();
        return new ResponseEntity<>(typologies, HttpStatus.OK);
    }
}
