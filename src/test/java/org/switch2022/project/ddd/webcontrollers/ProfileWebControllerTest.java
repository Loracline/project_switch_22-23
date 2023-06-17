package org.switch2022.project.ddd.webcontrollers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.ddd.application.ProfileService;
import org.switch2022.project.ddd.dto.ProfileCreationDto;
import org.switch2022.project.ddd.dto.mapper.ProfileDto;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
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
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

    /**
     * METHOD getProfile()
     */
    @Test
    void ensureProfileDtoIsRetrievedWhenCustomerExistsInDatabase() {
        // Arrange
        ProfileDto expected = new ProfileDto("batman");

        when(service.getProfile(any())).thenReturn(Optional.of(expected));

        // Act
        ResponseEntity<ProfileDto> response = controller.getProfile("batman");

        // Assert
        assertEquals(expected, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void ensureProfileNotFoundWhenDoesntExistInDatabase() {
        // Arrange
        when(service.getProfile(any())).thenReturn(Optional.empty());

        // Act
        ResponseEntity<ProfileDto> response = controller.getProfile("batman");

        // Assert
        assertNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}