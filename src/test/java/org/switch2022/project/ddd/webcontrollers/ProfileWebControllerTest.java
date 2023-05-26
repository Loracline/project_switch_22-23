package org.switch2022.project.ddd.webcontrollers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.ddd.application.ProfileService;
import org.switch2022.project.ddd.dto.ProfileCreationDto;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = ProfileWebControllerTest.class)
class ProfileWebControllerTest {
    @InjectMocks
    ProfileWebController controller;
    @MockBean
    ProfileService service;

    @Test
    void ensureThatProfileIsCreated() {
        //Arrange
        ProfileCreationDto dtoDouble = mock(ProfileCreationDto.class);

        when(service.createProfile(dtoDouble)).thenReturn(true);
        //Act
        ResponseEntity<Object> responseEntity = controller.createProfile(dtoDouble);
        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);
    }

    @Test
    void ensureThatResourceIsNotCreated_ExceptionIsThrow() {
        //Arrange
        ProfileCreationDto dtoDouble = mock(ProfileCreationDto.class);
        String expected = "The profile already exists in the repository.";
        when(service.createProfile(dtoDouble)).thenThrow(new AlreadyExistsInRepoException(expected));

        //Act
        ResponseEntity<Object> responseEntity = controller.createProfile(dtoDouble);
        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 409);
        assertEquals(expected, responseEntity.getBody());
    }
}