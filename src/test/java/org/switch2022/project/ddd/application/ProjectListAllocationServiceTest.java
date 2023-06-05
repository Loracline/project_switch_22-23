package org.switch2022.project.ddd.application;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.customer.ICustomerRepository;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceRepository;
import org.switch2022.project.ddd.dto.ProjectDto;
import org.switch2022.project.ddd.dto.mapper.ProjectMapper;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = ProjectListAllocationServiceTest.class)
class ProjectListAllocationServiceTest {
    @InjectMocks
    ProjectListAllocationService service;
    @MockBean
    IProjectResourceRepository resourceRepository;
    @MockBean
    IProjectRepository projectRepository;
    @MockBean
    ICustomerRepository customerRepository;
    @MockBean
    ProjectMapper mapper;

    /**
     * Method: listProjectsByAccount(Email email).
     * Scenario 01: An exception is thrown because the email that is passed as argument is null.
     * Expected return: InvalidInputException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenTheEmailIsNull() {
        //Arrange
        String expected = "The email must not be null";
        //Act
        InvalidInputException result = assertThrows(InvalidInputException.class,
                () -> service.listProjectsByAccount(null));

        //Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * Method: listProjectsByAccount(Email email).
     * Scenario 02: An exception is thrown because the email that is passed as argument is blank.
     * Expected return: InvalidInputException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenTheEmailIsBlank() {
        //Arrange
        String expected = "The email must not be blank";
        //Act
        InvalidInputException result = assertThrows(InvalidInputException.class,
                () -> service.listProjectsByAccount(" "));

        //Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * Method: listProjectsByAccount(Email email).
     * Scenario 03: An exception is thrown because the email that is passed as argument is empty.
     * Expected return: InvalidInputException.
     */
    @Test
    void ensureThatAnExceptionIsThrownWhenTheEmailIsEmpty() {
        //Arrange
        String expected = "The email must not be empty";
        //Act
        InvalidInputException result = assertThrows(InvalidInputException.class,
                () -> service.listProjectsByAccount(""));

        //Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * Method: listProjectsByAccount(Email email).
     * Scenario 04: The account whose email was passed as argument is not involved in any projects ongoing at the
     * time of the request.
     * Expected return: An empty list.
     */
    @Test
    void ensureThatAnEmptyListIsReturnedBecauseThereAreNoProjectsCurrentlyOngoing() {
        //Arrange
        List<ProjectDto> expected = new ArrayList<>();

        //Act
        List<ProjectDto> result = service.listProjectsByAccount("example@ise.ipp.pt");

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: listProjectsByAccount(Email email).
     * Scenario 05: The account whose email was passed as argument is involved in one or more projects ongoing at the
     * time of the request.
     * Expected return: A list as big as the number of ongoing projects to which an account is allocated.
     */
    @Test
    void ensureThatAListOfProjectDtosIsSuccessfullyReturned() {
        //Arrange
        Project projectOne = mock(Project.class);
        Project projectTwo = mock(Project.class);
        
        List<Project> projects = new ArrayList<>();
        projects.add(projectOne);
        projects.add(projectTwo);
        
        when(projectRepository.findAllByProjectCodes(any())).thenReturn(projects);
        
        when(projectOne.containsCurrentDate()).thenReturn(true);
        when(projectTwo.containsCurrentDate()).thenReturn(true);

        ProjectDto projectDtoOne = mock(ProjectDto.class);
        ProjectDto projectDtoTwo = mock(ProjectDto.class);

        String customerName = "ISEP";
        when(customerRepository.findCustomerNameByTaxId(any())).thenReturn(customerName);

        when(mapper.projectToDto(projectOne, customerName)).thenReturn(projectDtoOne);
        when(mapper.projectToDto(projectTwo, customerName)).thenReturn(projectDtoTwo);

        List<ProjectDto> expected = Arrays.asList(projectDtoOne, projectDtoTwo);

        //Act
        List<ProjectDto> result = service.listProjectsByAccount("example@ise.ipp.pt");

        //Assert
        assertEquals(expected, result);
    }
    /**
     * Method: listProjectsByAccount(Email email).
     * Scenario 06: The account whose email was passed as argument is involved in one or more projects.
     * Expected return: An empty list
     */
    @Test
    void ensureThatAListOfProjectDtosIsNotSuccessfullyReturned() {
        //Arrange
        Project projectOne = mock(Project.class);
        Project projectTwo = mock(Project.class);

        List<Project> projects = new ArrayList<>();
        projects.add(projectOne);
        projects.add(projectTwo);

        when(projectRepository.findAllByProjectCodes(any())).thenReturn(projects);

        when(projectOne.containsCurrentDate()).thenReturn(false);
        when(projectTwo.containsCurrentDate()).thenReturn(false);

        ProjectDto projectDtoOne = mock(ProjectDto.class);
        ProjectDto projectDtoTwo = mock(ProjectDto.class);

        String customerName = "ISEP";
        when(customerRepository.findCustomerNameByTaxId(any())).thenReturn(customerName);

        when(mapper.projectToDto(projectOne, customerName)).thenReturn(projectDtoOne);
        when(mapper.projectToDto(projectTwo, customerName)).thenReturn(projectDtoTwo);

        List<ProjectDto> expected = new ArrayList<>();

        //Act
        List<ProjectDto> result = service.listProjectsByAccount("example@ise.ipp.pt");

        //Assert
        assertEquals(expected, result);
    }
}