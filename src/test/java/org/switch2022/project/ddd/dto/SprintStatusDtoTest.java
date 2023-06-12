package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SprintStatusDtoTest {

    /**
     * Method SprintStatusDto().
     * Scenario 1: ensure SprintStatusDto is created successfully.
     */

    @Test
    public void ensureDtoIsCreatedSuccessfully() {
        //ARRANGE
        String sprintId = "p001_s001";
        String status = "Open";

        //ACT
        SprintStatusDto sprintStatusDto = new SprintStatusDto(sprintId, status);

        //ASSERT
        assertEquals(sprintId, sprintStatusDto.getSprintId());
        assertEquals(status, sprintStatusDto.getSprintStatus());
    }

    @Test
    public void ensureSprintIdIsReturnedSuccessfully() {
        //ARRANGE
        String expectedSprintId = "p001_s001";

        SprintStatusDto sprintStatusDtoDouble = mock(SprintStatusDto.class);
        when(sprintStatusDtoDouble.getSprintId()).thenReturn(expectedSprintId);

        //ACT
        String actualSprintId = sprintStatusDtoDouble.getSprintId();

        //ASSERT
        assertEquals(expectedSprintId, actualSprintId);
    }

    @Test
    public void ensureSprintStatusIsReturnedSuccessfully() {
        //ARRANGE
        String expectedStatus = "Open";

        SprintStatusDto sprintStatusDtoDouble = mock(SprintStatusDto.class);
        when(sprintStatusDtoDouble.getSprintStatus()).thenReturn(expectedStatus);

        //ACT
        String actualStatus = sprintStatusDtoDouble.getSprintStatus();

        //ASSERT
        assertEquals(expectedStatus, actualStatus);
    }

}
