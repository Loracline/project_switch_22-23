package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.ResourceAllocationService;

import java.time.LocalDate;

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
        LocalDate startDateDouble = mock(LocalDate.class);
        LocalDate endaDateDouble = mock(LocalDate.class);
        when(service.addUserToProject(anyInt(), anyString(), anyString(), anyFloat(), anyFloat(), any(), any())).thenReturn(true);

        //Act
        boolean result = controller.addUserToProject(1, "Email", "Role", 10, 100
                , startDateDouble, endaDateDouble);

        //Assert
        assertTrue(result);
    }
}