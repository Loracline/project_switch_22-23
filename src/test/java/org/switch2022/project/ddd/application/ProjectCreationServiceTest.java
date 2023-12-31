package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.business_sector.IBusinessSectorRepository;
import org.switch2022.project.ddd.domain.model.customer.ICustomerRepository;
import org.switch2022.project.ddd.domain.model.project.IFactoryProject;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.typology.ITypologyRepository;
import org.switch2022.project.ddd.dto.ProjectCodeStringDto;
import org.switch2022.project.ddd.dto.ProjectCreationDto;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ProjectCreationServiceTest.class
)
class ProjectCreationServiceTest {

    @InjectMocks
    ProjectCreationService projectCreationService;
    @MockBean
    IFactoryProject factoryProject;
    @MockBean
    @Qualifier("project_jpa")
    IProjectRepository projectRepository;
    @MockBean
    ITypologyRepository typologyRepository;
    @MockBean
    IBusinessSectorRepository businessSectorRepository;
    @MockBean
    @Qualifier("customer_jpa")
    ICustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Method: addProject
     * scenario1: the project is added successfully
     */
    @Test
    void ensureProjectIsAdded() {
        //Arrange
        Project projectDouble = mock(Project.class);
        when(projectRepository.save(any())).thenReturn(true);
        boolean expected = true;
        //Act
        boolean result = projectCreationService.addProject(projectDouble);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario2: the project is not added
     */
    @Test
    void ensureProjectIsNotAdded() {
        //Arrange
        Project projectDouble = mock(Project.class);
        when(projectRepository.save(any())).thenReturn(false);
        boolean expected = false;
        //Act
        boolean result = projectCreationService.addProject(projectDouble);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method createCode
     * scenario1: the method returns number 1 when Repository is empty
     */
    @Test
    void ensureProjectCodeIsCreatedWithEmptyRepository() {
        //Arrange
        when(projectRepository.count()).thenReturn(0);
        int expected = 1;
        //Act
        int result = projectCreationService.calculateNextProjectNumber();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario2: the method returns number 2, when the Repository has one project
     */
    @Test
    void ensureProjectCodeIsCreatedWithRepositoryWithOneProject() {
        //Arrange
        when(projectRepository.count()).thenReturn(1);
        int expected = 2;
        //Act
        int result = projectCreationService.calculateNextProjectNumber();
        //Assert
        assertEquals(expected, result);
    }


    /**
     * Method: getProjectByCode
     * scenario 1: returns an optional
     */
    @Test
    void ensureProjectIsRetrieved() {
        Project projectDouble = mock(Project.class);
        Optional<Project> optionalProject = Optional.ofNullable(projectDouble);
        when(projectRepository.findByCode(any())).thenReturn(optionalProject);
        //Act
        Optional<Project> result = projectCreationService.getProjectByCode("P001");
        //Assert
        assertEquals(optionalProject, result);
    }

    /**
     * Method: createProject()
     * Scenario 1: returns a project ID.
     */

    @Test
    void ensureProjectIsCreated() {
        Project projectDouble = mock(Project.class);
        ProjectCreationDto projectCreationDto = new ProjectCreationDto("panic",
                "panic total", "BS001", "242526272", "PT001");

        when(projectRepository.count()).thenReturn(1);

        when(factoryProject.createProject(anyInt(), any(), any(), any(), any(), any())).thenReturn(projectDouble);
        when(projectRepository.save(projectDouble)).thenReturn(true);
        when(projectDouble.getProjectCode()).thenReturn("P001");

        String expected = "P001";
        ProjectCodeStringDto result = projectCreationService.createProject(projectCreationDto);

        assertEquals(expected, result.getCode());
    }

}