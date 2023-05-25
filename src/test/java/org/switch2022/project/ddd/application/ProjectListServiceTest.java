package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.customer.ICustomerRepository;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.value_object.TaxId;
import org.switch2022.project.ddd.dto.ProjectDto;
import org.switch2022.project.ddd.dto.mapper.ProjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        classes = org.switch2022.project.ddd.application.ProjectListService.class
)
class ProjectListServiceTest {
    /**
     * BeforeEach execute common code before running the
     * tests below.
     */
    @InjectMocks
    ProjectListService projectListService;
    @MockBean
    @Qualifier("repositoryJpa")
    IProjectRepository projectRepository;
    @MockBean
    ProjectMapper projectMapper;
    @MockBean
    ICustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * METHOD: requestAllProjects
     * Scenario 1: returns and emtpy list because there are no Projects
     */

    @Test
    void ensureThatReturnsAnEmptyListBecauseThereAreNoProjects() {
        //Arrange
        List<ProjectDto> expected = new ArrayList<>();
        List<Project> projects = new ArrayList<>();
        when(projectRepository.findAll()).thenReturn(projects);
        //Act
        List<ProjectDto> result = projectListService.requestAllProjects();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: returns a list of ProjectDto
     */

    @Test
    void ensureThatReturnsAListOfProjectsDto() {
        //Arrange
        List<ProjectDto> expected = new ArrayList<>();
        Project projectDouble = mock(Project.class);
        Project projectDoubleTwo = mock(Project.class);
        ProjectDto projectDtoDouble = mock(ProjectDto.class);
        List<Project> projects = new ArrayList<>();
        String customerName = "ISEP";
        projects.add(projectDouble);
        projects.add(projectDoubleTwo);
        TaxId taxIdDouble = mock(TaxId.class);
        when(projectRepository.findAll()).thenReturn(projects);
        when(customerRepository.findCustomerNameByTaxId(taxIdDouble)).thenReturn(customerName);
        when(projectDouble.getCustomerTaxId()).thenReturn(taxIdDouble);
        when(projectDoubleTwo.getCustomerTaxId()).thenReturn(taxIdDouble);
        when(projectMapper.projectToDto(projectDouble, customerName)).thenReturn(projectDtoDouble);
        when(projectMapper.projectToDto(projectDoubleTwo, customerName)).thenReturn(projectDtoDouble);
        expected.add(projectDtoDouble);
        expected.add(projectDtoDouble);
        //Act
        List<ProjectDto> result = projectListService.requestAllProjects();
        //Assert
        assertEquals(expected, result);
    }
}