package org.switch2022.project.ddd.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.switch2022.project.ddd.application.BusinessSectorService;
import org.switch2022.project.ddd.dto.BusinessSectorCreationDto;

/**
 * The BusinessSectorWebController class is a REST controller for handling requests related to resources.
 */

@RestController
@RequestMapping("/business-sectors")
public class BusinessSectorWebController {
    /**
     * The BusinessSectorService used to creat a new business sector.
     */
    @SuppressWarnings("all")
    @Autowired
    private BusinessSectorService service;

    /**
     * Handles a POST request to create a new business sector.
     *
     * @param businessSectorCreationDto The business sector to be created.
     * @return The created business sector.
     */

    @PostMapping
    public ResponseEntity<Object> createBusinessSector(@RequestBody BusinessSectorCreationDto
                                                               businessSectorCreationDto) {
        try {
            service.createBusinessSector(businessSectorCreationDto.name);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
