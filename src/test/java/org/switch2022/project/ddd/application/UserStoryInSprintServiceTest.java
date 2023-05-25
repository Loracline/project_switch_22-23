package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@SpringBootTest(
        classes = UserStoryInSprintService.class)
class UserStoryInSprintServiceTest {

    @InjectMocks
    UserStoryInSprintService service;

    @MockBean
    ISprintRepository sprintRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Method: estimateEffortUserStory(usId, effort, sprintId)
     * Scenario 1: Estimates the effort of a US.
     * Expected result: user story effort is estimated.
     */

    @Test
    void ensureEffortIsEstimated() throws Exception {
        //Arrange
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        int effort = 3;
        Sprint sprintDouble = mock(Sprint.class);
        Optional<Sprint> sprintOptional = Optional.of(sprintDouble);

        when(sprintRepository.findById(any())).thenReturn(sprintOptional);
        when(sprintDouble.isPeriodAfterOrEqualThanDate(any())).thenReturn(false);
        when(sprintDouble.hasUserStory(any())).thenReturn(true);
        when(sprintDouble.estimateEffortUserStory(any(),anyInt())).thenReturn(true);

        //Act
        boolean result = service.estimateEffortUserStory(usId, effort, sprintId);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: does not estimate the effort of a US due to effort value not corresponding to fibonacci sequence.
     * Expected result: user story effort is not estimated.
     */

    @Test
    void ensureEffortIsNotEstimatedEffortValueNotFromFibonacciSequence() throws Exception {
        //Arrange
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        int effort = 4;
        Sprint sprintDouble = mock(Sprint.class);
        Optional<Sprint> sprintOptional = Optional.of(sprintDouble);

        when(sprintRepository.findById(any())).thenReturn(sprintOptional);
        when(sprintDouble.isPeriodAfterOrEqualThanDate(any())).thenReturn(false);
        when(sprintDouble.hasUserStory(any())).thenReturn(true);
        when(sprintDouble.estimateEffortUserStory(any(),anyInt())).thenReturn(false);

        //Act
        boolean result = service.estimateEffortUserStory(usId, effort, sprintId);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: does not estimate the effort of a US despite the spring having started.
     * Expected result: user story effort is not estimated.
     */
    @Test
    void ensureEffortIsNotEstimatedInAStartedSprint(){
        //Arrange
        String message = "The Sprint is not valid";
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        int effort = 1;
        Sprint sprintDouble = mock(Sprint.class);
        Optional<Sprint> sprintOptional = Optional.of(sprintDouble);

        when(sprintRepository.findById(any())).thenReturn(sprintOptional);
        when(sprintDouble.isPeriodAfterOrEqualThanDate(any())).thenReturn(true);

        //Act
        Exception exception = assertThrows(Exception.class,
                () -> service.estimateEffortUserStory(usId, effort, sprintId));

        //Assert
        assertEquals(message, exception.getMessage());
    }
}