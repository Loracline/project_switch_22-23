package org.switch2022.project.ddd.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.switch2022.project.ddd.application.CustomerService;
import org.switch2022.project.ddd.dto.CustomerCreationDto;

/**
 * Controller class that handles HTTP requests related to customers.
 */
@RestController
@RequestMapping("/customers")
public class CustomerWebController {

    @SuppressWarnings("all")
    @Autowired
    private CustomerService service;

    /**
     * Handles the HTTP POST request to add a new customer.
     *
     * @param dto The CustomerCreationDto object containing customer information.
     * @return A ResponseEntity with HTTP status 201 (CREATED) if the customer was added successfully,
     * or a ResponseEntity with the error message and HTTP status 409 (CONFLICT) if an error occurred.
     */
    @PostMapping()
    public ResponseEntity<Object> addCustomer(@RequestBody CustomerCreationDto dto) {
        try {
            service.addCustomer(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
