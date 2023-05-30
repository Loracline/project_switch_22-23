package org.switch2022.project.ddd.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.ddd.application.BusinessSectorService;
import org.switch2022.project.ddd.dto.BusinessSectorCreationDto;
import org.switch2022.project.ddd.dto.BusinessSectorDto;

import java.util.List;

/**
 * The BusinessSectorWebController class is a REST controller for handling requests related to resources.
 */

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/business_sectors")
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
        if (service.createBusinessSector(businessSectorCreationDto.name)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    /**
     * Handles a GET request to retrieve a list of all business sectors.
     *
     * @return A ResponseEntity containing a list of BusinessSectorDto objects and a status code of 200 (OK).
     */
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<BusinessSectorDto>> listAllBusinessSectors() {
        List<BusinessSectorDto> businessSectors = service.requestAllBusinessSectors();
        return new ResponseEntity<>(businessSectors, HttpStatus.OK);
    }
}
