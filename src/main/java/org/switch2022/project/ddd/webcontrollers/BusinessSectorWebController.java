package org.switch2022.project.ddd.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.ddd.application.BusinessSectorService;
import org.switch2022.project.ddd.domain.value_object.BusinessSectorId;
import org.switch2022.project.ddd.dto.BusinessSectorCreationDto;
import org.switch2022.project.ddd.dto.BusinessSectorDto;
import org.switch2022.project.ddd.utils.Utils;

import java.util.List;
import java.util.Optional;

/**
 * The BusinessSectorWebController.md class is a REST controller for handling requests related to resources.
 */

@CrossOrigin(origins = {"http://localhost:3000"}, maxAge = 3600)
@RestController
@RequestMapping("/business_sectors")
public class BusinessSectorWebController {
    /**
     * The BusinessSectorService used to create a new business sector.
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

    /**
     * Retrieves a BusinessSector by its ID and returns it as a response entity.
     *
     *@param businessSectorId The ID of the BusinessSector to be retrieved.
     *@return A response entity containing the BusinessSector if found, or a not found
     * response if the BusinessSector is not found.
     */
    @GetMapping("/{businessSectorId}")
    public ResponseEntity<BusinessSectorDto> getByIdNumber(@PathVariable String businessSectorId) {
        Number businessSectorNumber = Utils.getIntFromAlphanumericString(businessSectorId
                , "bs");
        BusinessSectorId sectorId = new BusinessSectorId(businessSectorNumber);
        Optional<BusinessSectorDto> opBusinessSector = service.getByIdNumber(sectorId);
        if (opBusinessSector.isPresent()) {
            BusinessSectorDto businessSectorDto = opBusinessSector.get();
            return new ResponseEntity<>(businessSectorDto, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
