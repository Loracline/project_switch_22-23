package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.ResourceAllocationService;
import org.switch2022.project.ddd.dto.AllocationDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = AddUserToProjectControllerTest.class)
class AddUserToProjectControllerTest {

    @InjectMocks
    AddUserToProjectController controller;
    @MockBean
    ResourceAllocationService service;

    @Test
    void ensureThatUserIsAllocatedToProjectSuccessfully() {
        //Arrange
        AllocationDto allocationDtoDouble = mock(AllocationDto.class);
        when(service.addUserToProject(any())).thenReturn(true);

        //Act
        boolean result = controller.addUserToProject(allocationDtoDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void ensureThatUserIsNotAllocatedToProject() {
        //Arrange
        AllocationDto allocationDtoDouble = mock(AllocationDto.class);
        when(service.addUserToProject(any())).thenReturn(false);

        //Act
        boolean result = controller.addUserToProject(allocationDtoDouble);

        //Assert
        assertFalse(result);
    }
}