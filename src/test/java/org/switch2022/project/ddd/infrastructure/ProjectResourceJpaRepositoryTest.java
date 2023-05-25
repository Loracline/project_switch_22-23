package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.datamodel.JPA.ProjectResourceJpa;
import org.switch2022.project.ddd.datamodel.JPA.assemblers.ResourceDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.infrastructure.JPA.IProjectResourceJpaRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {ProjectResourceJpaRepository.class})
class ProjectResourceJpaRepositoryTest {
    @InjectMocks
    ProjectResourceJpaRepository projectResourceJpaRepository;
    @MockBean
    IProjectResourceJpaRepository jpaRepository;
    @MockBean
    ResourceDomainDataAssembler assembler;

    @Test
    void ensureThatProjectResourceIsSavedInDataBase() {
        //Arrange
        ProjectResourceJpa projectResourceJpa = mock(ProjectResourceJpa.class);
        ProjectResource projectResource = mock(ProjectResource.class);
        when(assembler.toData(any())).thenReturn(projectResourceJpa);
        when(jpaRepository.existsById(any())).thenReturn(false);
        //Act
        boolean result = projectResourceJpaRepository.save(projectResource);
        //Assert
        assertTrue(result);
    }

    @Test
    void ensureThatThrowsExceptionWhenProjectResourceAlreadyExistsInRepo() {
        //Arrange
        String expected = "The project resource already exists in the repository.";
        ProjectResourceJpa projectResourceJpa = mock(ProjectResourceJpa.class);
        ProjectResource projectResource = mock(ProjectResource.class);
        when(assembler.toData(any())).thenReturn(projectResourceJpa);
        when(jpaRepository.existsById(any())).thenReturn(true);

        //Act
        AlreadyExistsInRepoException result = assertThrows(AlreadyExistsInRepoException.class,
                () -> projectResourceJpaRepository.save(projectResource));

        //Assert
        assertEquals(expected, result.getMessage());
    }

    @Test
    void ensureThatReturnsAnEmptyListOfProjectResource() {
        //Arrange
        Iterable<ProjectResourceJpa> iterableList = new ArrayList<>();
        when(jpaRepository.findAll()).thenReturn(iterableList);

        //Act
        List<ProjectResource> result = projectResourceJpaRepository.findAll();

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void ensureThatReturnsAListOfProjectResource() {
        //Arrange
        ProjectResource projectResource = mock(ProjectResource.class);
        List<ProjectResource> expected = new ArrayList<>();
        expected.add(projectResource);

        ProjectResourceJpa projectResourceJpa = mock(ProjectResourceJpa.class);
        List<ProjectResourceJpa> list = new ArrayList<>();
        list.add(projectResourceJpa);

        when(jpaRepository.findAll()).thenReturn(list);
        when(assembler.toDomain(projectResourceJpa)).thenReturn(projectResource);

        //Act
        List<ProjectResource> result = projectResourceJpaRepository.findAll();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatReturnsAnEmptyListWhenSearchingForAGivenEmailThatDoesNotExist() {
        //Arrange
        Email email = mock(Email.class);

        //Act
        List<ProjectResource> result = projectResourceJpaRepository.findResourcesByAccountEmail(email);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void ensureThatReturnsAListWhenSearchingForAGivenEmail() {
        //Arrange
        Email email = mock(Email.class);
        ProjectResource projectResource = mock(ProjectResource.class);
        List<ProjectResource> expected = new ArrayList<>();
        expected.add(projectResource);

        ProjectResourceJpa projectResourceJpa = mock(ProjectResourceJpa.class);
        List<ProjectResourceJpa> list = new ArrayList<>();
        list.add(projectResourceJpa);

        when(jpaRepository.findAllByAccountEmail(email)).thenReturn(list);
        when(assembler.toDomain(projectResourceJpa)).thenReturn(projectResource);

        //Act
        List<ProjectResource> result = projectResourceJpaRepository.findResourcesByAccountEmail(email);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatReturnsAnEmptyListWhenSearchingForEmailThatDoesNotExist() {
        //Arrange
        Email email = mock(Email.class);

        //Act
        List<Code> result = projectResourceJpaRepository.findProjectCodesByAccountEmail(email);
        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void ensureThatReturnsAListWithAValueObjectEmail() {
        //Arrange
        Email email = mock(Email.class);
        Code code = new Code(1);
        List<Code> expected = new ArrayList<>();
        expected.add(code);
        ProjectResourceJpa projectResourceJpa = mock(ProjectResourceJpa.class);
        List<ProjectResourceJpa> list = new ArrayList<>();
        list.add(projectResourceJpa);
        when(jpaRepository.findAllByAccountEmail(email)).thenReturn(list);
        ProjectResource projectResource = mock(ProjectResource.class);
        when(assembler.toDomain(projectResourceJpa)).thenReturn(projectResource);
        when(projectResource.getCode()).thenReturn("p1");

        //Act
        List<Code> result = projectResourceJpaRepository.findProjectCodesByAccountEmail(email);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatReturnsAnEmptyListWhenSearchingForCodeThatDoesNotExist() {
        //Arrange
        Code code = mock(Code.class);

        //Act
        List<Email> result = projectResourceJpaRepository.findAccountEmailsByProjectCode(code);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void ensureThatReturnsAListWithAValueObjectCode() {
        //Arrange
        Code code = mock(Code.class);
        Email email = new Email("email@hotmail.com");
        List<Email> expected = new ArrayList<>();
        expected.add(email);
        ProjectResourceJpa projectResourceJpa = mock(ProjectResourceJpa.class);
        List<ProjectResourceJpa> list = new ArrayList<>();
        list.add(projectResourceJpa);
        when(jpaRepository.findAllByProjectCode(code)).thenReturn(list);
        ProjectResource projectResource = mock(ProjectResource.class);
        when(assembler.toDomain(projectResourceJpa)).thenReturn(projectResource);
        when(projectResource.getEmail()).thenReturn("email@hotmail.com");

        //Act
        List<Email> result = projectResourceJpaRepository.findAccountEmailsByProjectCode(code);

        //Assert
        assertEquals(expected, result);
    }

}