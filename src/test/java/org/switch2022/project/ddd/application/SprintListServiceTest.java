package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.dto.ProjectCodeValueObjectDto;
import org.switch2022.project.ddd.dto.SprintValueObjectsDto;
import org.switch2022.project.ddd.dto.mapper.SprintDtoAssembler;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        classes = SprintListServiceTest.class
)
class SprintListServiceTest {

    @InjectMocks
    SprintListService service;

    @MockBean
    @Qualifier("sprint_jpa")
    ISprintRepository repository;

    @DisplayName("Project has no sprints - empty list")
    @Test
    void ensureEmptyListIsRetrievedWhenProjectHasNoSprintsYet() {
        // Arrange
        ProjectCodeValueObjectDto dtoDouble = mock(ProjectCodeValueObjectDto.class);

        List<Sprint> sprints = new ArrayList<>();
        List<SprintValueObjectsDto> expected = new ArrayList<>();

        when(repository.findByProjectCode(any())).thenReturn(sprints);

        // Act
        List<SprintValueObjectsDto> result = service.listSprintsFromProject(dtoDouble);

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Project has sprints - filled list")
    @Test
    void ensureFilledListIsRetrievedSuccessfullyWhenProjectHasSprints() {
        // Arrange
        ProjectCodeValueObjectDto dtoDouble = mock(ProjectCodeValueObjectDto.class);

        List<Sprint> sprints = new ArrayList<>();
        Sprint sprintDoubleOne = mock(Sprint.class);
        Sprint sprintDoubleTwo = mock(Sprint.class);
        sprints.add(sprintDoubleOne);
        sprints.add(sprintDoubleTwo);

        List<SprintValueObjectsDto> expected = new ArrayList<>();
        SprintValueObjectsDto sprintDtoDoubleOne = mock(SprintValueObjectsDto.class);
        SprintValueObjectsDto sprintDtoDoubleTwo = mock(SprintValueObjectsDto.class);
        expected.add(sprintDtoDoubleOne);
        expected.add(sprintDtoDoubleTwo);

        // Mock repository's behaviour.
        when(repository.findByProjectCode(any())).thenReturn(sprints);

        // MockedStatic is a Mockito class that allows mocking static methods.
        // Provides a scope ('try' block) within which the static method mocking is active.
        // Creates a new instance of 'MockedStatic' for the assembler class, setting up the environment.
        try (MockedStatic<SprintDtoAssembler> mockedStatic = Mockito.mockStatic(SprintDtoAssembler.class)) {
            // Configures the behaviour of the static method, specifying what it should return.
            mockedStatic.when(() -> SprintDtoAssembler.sprintToValueObjectsDto(sprintDoubleOne)).
                    thenReturn(sprintDtoDoubleOne);
            mockedStatic.when(() -> SprintDtoAssembler.sprintToValueObjectsDto(sprintDoubleTwo)).
                    thenReturn(sprintDtoDoubleTwo);

            // Act
            List<SprintValueObjectsDto> result = service.listSprintsFromProject(dtoDouble);

            // Assert
            assertEquals(expected, result);
        }
    }
}