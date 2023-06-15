package org.switch2022.project.ddd.webcontrollers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.ddd.application.CustomerListService;
import org.switch2022.project.ddd.application.CustomerService;
import org.switch2022.project.ddd.dto.CustomerCreationDto;
import org.switch2022.project.ddd.dto.CustomerDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        classes = CustomerWebControllerTest.class
)
class CustomerWebControllerTest {

    @InjectMocks
    CustomerWebController controller;

    @MockBean
    CustomerService createService;

    @MockBean
    CustomerListService listService;

    @DisplayName("Customer created successfully")
    @Test
    void ensureCustomerIsCreatedSuccessfully() {
        // Arrange
        CustomerCreationDto dtoDouble = mock(CustomerCreationDto.class);

        when(createService.addCustomer(dtoDouble)).thenReturn(true);

        // Act
        ResponseEntity<Object> response = controller.addCustomer(dtoDouble);

        // Assert
        assertEquals(response.getStatusCodeValue(), 201);
    }

    @DisplayName("Customer is not created")
    @Test
    void ensureCustomerIsNotCreated() {
        //Arrange
        CustomerCreationDto dtoDouble = mock(CustomerCreationDto.class);

        when(createService.addCustomer(dtoDouble)).thenReturn(false);

        //Act
        ResponseEntity<Object> response = controller.addCustomer(dtoDouble);

        //Assert
        assertEquals(response.getStatusCodeValue(), 409);
    }

    @DisplayName("Empty list when repository is empty")
    @Test
    void ensureEmptyListIsRetrievedWhenRepositoryIsEmpty() {
        // Arrange
        List<CustomerDto> expected = new ArrayList<>();

        when(listService.listAllCustomers()).thenReturn(expected);

        // Act
        ResponseEntity<List<CustomerDto>> response = controller.listAllCustomers();

        // Assert
        assertEquals(expected, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @DisplayName("List of DTOs of all customers")
    @Test
    void ensureListOfDtosOfAllCustomersInRepositoryIsRetrieved() {
        // Arrange
        List<CustomerDto> expected = new ArrayList<>();

        CustomerDto customerDtoOne = mock(CustomerDto.class);
        CustomerDto customerDtoTwo = mock(CustomerDto.class);
        CustomerDto customerDtoThree = mock(CustomerDto.class);

        expected.add(customerDtoOne);
        expected.add(customerDtoTwo);
        expected.add(customerDtoThree);

        when(listService.listAllCustomers()).thenReturn(expected);

        // Act
        ResponseEntity<List<CustomerDto>> response = controller.listAllCustomers();

        // Assert
        assertEquals(expected, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * METHOD getByTaxId()
     */
    @DisplayName("Customer DTO")
    @Test
    void ensureCustomerDtoIsRetrievedWhenCustomerExistsInDatabase() {
        // Arrange
        String taxId = "217746691";

        CustomerDto expected = new CustomerDto("XPTO, SA", "217746691");

        when(listService.getCustomerByTaxId(any())).thenReturn(Optional.of(expected));

        // Act
        ResponseEntity<CustomerDto> response = controller.getByTaxId(taxId);

        // Assert
        assertEquals(expected, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @DisplayName("Not found")
    @Test
    void ensureCustomerNotFoundWhenDoesntExistInDatabase() {
        // Arrange
        String taxId = "228019885";

        when(listService.getCustomerByTaxId(any())).thenReturn(Optional.empty());

        // Act
        ResponseEntity<CustomerDto> response = controller.getByTaxId(taxId);

        // Assert
        assertNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}