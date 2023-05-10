package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.AddUserStoryToSprintBacklogService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(
        classes = AddUserStoryToSprintBacklogController.class)
class AddUserStoryToSprintBacklogControllerTest {
    @InjectMocks
    AddUserStoryToSprintBacklogController controller;

    @MockBean
    AddUserStoryToSprintBacklogService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Method: addUserStoryToSprintBacklog
     * scenario 1: returns true
     */

    @Test
    void ensureUserStoryIsAddedToSprint() throws Exception {
        //Arrange
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        when(service.addUserStoryToSprintBacklog(usId, sprintId)).thenReturn(true);
        //Act
        boolean result = controller.addUserStoryToSprintBacklog(usId, sprintId);
        //Assert
        assertTrue(result);
    }

    /**
     * scenario 2: returns false
     */
    @Test
    void ensureUserStoryIsNotAddedToSprint() throws Exception {
        //Arrange
        String usId = "p001_us001";
        String sprintId = "p001_s001";
        when(service.addUserStoryToSprintBacklog(usId, sprintId)).thenReturn(false);
        //Act
        boolean result = controller.addUserStoryToSprintBacklog(usId, sprintId);
        //Assert
        assertFalse(result);
    }
}