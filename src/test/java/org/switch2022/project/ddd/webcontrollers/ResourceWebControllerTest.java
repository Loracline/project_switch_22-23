package org.switch2022.project.ddd.webcontrollers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.ddd.application.ResourceAllocationService;
import org.switch2022.project.ddd.dto.AllocationDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        classes = ResourceWebControllerTest.class)
class ResourceWebControllerTest {
    @InjectMocks
    ResourceWebController controller;
    @MockBean
    ResourceAllocationService service;

    @Test
    void ensureThatResourceIsCreated() {
        //Arrange
        AllocationDto dtoDouble = mock(AllocationDto.class);

        when(service.addUserToProject(dtoDouble)).thenReturn(true);
        //Act
        ResponseEntity<Object> responseEntity = controller.createResource(dtoDouble);
        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);
    }
}