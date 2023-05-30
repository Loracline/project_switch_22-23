package org.switch2022.project.ddd.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.ddd.application.CustomerListService;
import org.switch2022.project.ddd.application.CustomerService;
import org.switch2022.project.ddd.dto.CustomerCreationDto;
import org.switch2022.project.ddd.dto.CustomerDto;

import java.util.List;

/**
 * Controller class that handles HTTP requests related to customers.
 */
@RestController
@RequestMapping("/customers")
public class CustomerWebController {

    @SuppressWarnings("all")
    @Autowired
    private CustomerService createService;

    @SuppressWarnings("all")
    @Autowired
    private CustomerListService listService;

    /**
     * Handles the HTTP POST request to add a new customer.
     *
     * @param dto The CustomerCreationDto object containing customer information.
     * @return A ResponseEntity with HTTP status 201 (CREATED) if the customer was added successfully,
     * or a ResponseEntity with the error message and HTTP status 409 (CONFLICT) if an error occurred.
     */
    @PostMapping()
    public ResponseEntity<Object> addCustomer(@RequestBody CustomerCreationDto dto) {
        boolean isCreated = createService.addCustomer(dto);
        if (isCreated) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * Retrieves a list of all customers.
     * This method is mapped to an HTTP GET request without any specific endpoint path.
     * It fetches the list of customers using the CustomerListService's listAllCustomers method,
     * and returns a ResponseEntity containing the list of CustomerDto objects as the response body.
     * The HTTP status code of the response is set to HttpStatus.OK.
     *
     * @return A ResponseEntity object containing a list of CustomerDto objects representing the customers,
     * with the HTTP status code set to HttpStatus.OK.
     */
    @GetMapping()
    public ResponseEntity<List<CustomerDto>> listAllCustomers() {
        List<CustomerDto> customerDtos = listService.listAllCustomers();
        return new ResponseEntity<>(customerDtos, HttpStatus.OK);
    }
}