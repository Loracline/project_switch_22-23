package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.datamodel_jpa.ProjectJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.ProjectDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.infrastructure.JPA.IProjectJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        classes = ProjectRepositoryJpa.class)
class ProjectRepositoryJpaTest {
    @InjectMocks
    ProjectRepositoryJpa repositoryJpa;
    @MockBean
    IProjectJpaRepository repository;
    @MockBean
    ProjectDomainDataAssembler assembler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Method: findByCode
     * scenario 1: return an optional of projectJpa
     */

    @Test
    void ensureProjectIsRetrievedSuccessfullyByCode() {
        //Arrange
        Code code = new Code(2);
        Project project = mock(Project.class);
        Optional<Project> expected = Optional.of(project);
        ProjectJpa projectJpa = mock(ProjectJpa.class);
        Optional<ProjectJpa> projectJpaOptional = Optional.of(projectJpa);
        when(repository.findByProjectCode("p002")).thenReturn(projectJpaOptional);
        when(assembler.toDomain(projectJpa)).thenReturn(project);

        //Act
        Optional<Project> result = repositoryJpa.findByCode(code);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 2: return an empty optional
     */

    @Test
    void ensureProjectIsRetrievedSuccessfullyByCode_EmptyOptional() {
        //Arrange
        Code code = new Code(2);
        Optional<Project> expected = Optional.empty();
        Optional<ProjectJpa> projectJpaOptional = Optional.empty();
        when(repository.findByProjectCode("p002")).thenReturn(projectJpaOptional);

        //Act
        Optional<Project> result = repositoryJpa.findByCode(code);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: count
     */
    @Test
    void ensureCountProjectsInRepository() {
        //Arrange
        int expected = 2;
        when(repository.count()).thenReturn(2L);
        //Act
        int result = repositoryJpa.count();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: save
     * scenario 1: returns true
     */
    @Test
    void ensureProjectIsSavedSuccessfully() {
        //Arrange
        Project project = mock(Project.class);
        when(project.getProjectCode()).thenReturn("p001");
        when(repository.existsById("p001")).thenReturn(false);
        //Act
        boolean result = repositoryJpa.save(project);
        //Assert
        assertTrue(result);
    }

    /**
     * Method: save
     * scenario 2: returns false
     */
    @Test
    void ensureProjectIsNotSavedSuccessfully() {
        //Arrange
        Project project = mock(Project.class);
        when(project.getProjectCode()).thenReturn("p001");
        when(repository.existsById("p001")).thenReturn(true);
        //Act
        boolean result = repositoryJpa.save(project);
        //Assert
        assertFalse(result);
    }

    /**
     * Method: findAll
     */
    @Test
    void ensureListIsRetrievedSuccessfully() {
        //Arrange
        Project projectDouble = mock(Project.class);
        Project projectDoubleTwo = mock(Project.class);
        List<Project> expected = new ArrayList<>();
        expected.add(projectDouble);
        expected.add(projectDoubleTwo);

        ProjectJpa projectJpaDouble = mock(ProjectJpa.class);
        ProjectJpa projectJpaDoubleTwo = mock(ProjectJpa.class);
        List<ProjectJpa> projectsJpa = new ArrayList<>();
        projectsJpa.add(projectJpaDouble);
        projectsJpa.add(projectJpaDoubleTwo);

        when(repository.findAll()).thenReturn(projectsJpa);
        when(assembler.toDomain(projectJpaDouble)).thenReturn(projectDouble);
        when(assembler.toDomain(projectJpaDoubleTwo)).thenReturn(projectDoubleTwo);
        //Act
        List<Project> result = repositoryJpa.findAll();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario: returns an empty list
     */
    @Test
    void ensureEmptyListIsRetrievedSuccessfully() {
        //Arrange
        List<Project> expected = new ArrayList<>();
        List<ProjectJpa> projectsJpa = new ArrayList<>();

        when(repository.findAll()).thenReturn(projectsJpa);

        //Act
        List<Project> result = repositoryJpa.findAll();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: findAllByProjectCodes
     */

    @Test
    void ensureAllProjectsAreRetrievedByCode() {
        //Arrange
        Code codeOne = new Code(1);
        Code codeTwo = new Code(2);
        List<Code> codes = new ArrayList<>();
        codes.add(codeOne);
        codes.add(codeTwo);

        Project projectDouble = mock(Project.class);
        Project projectDoubleTwo = mock(Project.class);

        List<Project> expected = new ArrayList<>();
        expected.add(projectDouble);
        expected.add(projectDoubleTwo);

        List<String> codeString = new ArrayList<>();
        codeString.add("p001");
        codeString.add("p002");

        ProjectJpa projectJpaDouble = mock(ProjectJpa.class);
        ProjectJpa projectJpaDoubleTwo = mock(ProjectJpa.class);

        List<ProjectJpa> projectJpa = new ArrayList<>();
        projectJpa.add(projectJpaDouble);
        projectJpa.add(projectJpaDoubleTwo);

        when(repository.findAllByProjectCodeIn(codeString)).thenReturn(projectJpa);
        when(assembler.toDomain(projectJpaDouble)).thenReturn(projectDouble);
        when(assembler.toDomain(projectJpaDoubleTwo)).thenReturn(projectDoubleTwo);

        //Act
        List<Project> result = repositoryJpa.findAllByProjectCodes(codes);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario: returns an empty list, because the Ids doesn't match
     */
    @Test
    void ensureAnEmptyListIsRetrieved() {
        //Arrange
        Code codeOne = new Code(1);
        Code codeTwo = new Code(2);
        List<Code> codes = new ArrayList<>();
        codes.add(codeOne);
        codes.add(codeTwo);

        List<Project> expected = new ArrayList<>();

        List<String> codeString = new ArrayList<>();
        codeString.add("p001");
        codeString.add("p002");

        List<ProjectJpa> projectJpa = new ArrayList<>();

        when(repository.findAllByProjectCodeIn(codeString)).thenReturn(projectJpa);

        //Act
        List<Project> result = repositoryJpa.findAllByProjectCodes(codes);
        //Assert
        assertEquals(expected, result);
    }
}
