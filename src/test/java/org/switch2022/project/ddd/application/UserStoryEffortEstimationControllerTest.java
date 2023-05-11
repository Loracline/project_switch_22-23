package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(
        classes = UserStoryEffortEstimationController.class)
class UserStoryEffortEstimationControllerTest {

    @InjectMocks
    UserStoryEffortEstimationController controller;

    @MockBean
    UserStoryInSprintService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Method: estimateEffortUserStory(usId, effort, sprintId)
     * Scenario 1: Estimates the effort of a US.
     *
     * Expected result: user story effort is estimated.
     */

    @Test
    void ensureEffortOfUserStoryIsEstimated() throws Exception {
        String usId = "us001";
        String sprintId = "p001";
        int effort = 1;

        when(service.estimateEffortUserStory(any(), anyInt(), any())).thenReturn(true);
        boolean result = controller.estimateEffortUserStory(usId, effort, sprintId);
        assertTrue(result);
    }

    /**
     * Scenario 2: Does not estimate the effort of a US.
     *
     * Expected result: user story effort is not estimated.
     */

    @Test
    void ensureEffortOfUserStoryIsNotEstimated() throws Exception {
        String usId = "us001";
        String sprintId = "p001";
        int effort = 1;

        when(service.estimateEffortUserStory(any(), anyInt(), any())).thenReturn(false);
        boolean result = controller.estimateEffortUserStory(usId, effort, sprintId);
        assertFalse(result);
    }
}