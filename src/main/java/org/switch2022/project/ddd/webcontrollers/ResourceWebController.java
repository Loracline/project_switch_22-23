package org.switch2022.project.ddd.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.ddd.application.ResourceAllocationService;
import org.switch2022.project.ddd.dto.AllocationDto;

/**
 * The ResourceWebController class is a REST controller for handling requests related to resources.
 */
@RestController
@RequestMapping("/resources")
public class ResourceWebController {
    /**
     * The ResourceAllocationService used to create a new resource.
     */
    @Autowired
    private ResourceAllocationService service;

    /**
     * Handles a POST request to create a new resource.
     *
     * @param allocationDto The AllocationDto object containing the information needed to create a new resource.
     * @return A ResponseEntity containing the project code and a status code of 201 (CREATED).
     */
    @PostMapping()
    public ResponseEntity<Object> createResource(@RequestBody AllocationDto allocationDto) {
        boolean isCreated = service.addUserToProject(allocationDto);
        if (isCreated) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
