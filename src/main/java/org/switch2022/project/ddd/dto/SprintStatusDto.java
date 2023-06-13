package org.switch2022.project.ddd.dto;

/**
 * Data transfer object with the necessary parameters to change the state of a Sprint.
 * This class contains information about the sprint such as the ID and the respective status.
 */
public class SprintStatusDto {
    /**
     * Attributes
     */
    private final String sprintId;
    private final String status;

    /**
     * Constructs a new SprintStatusDto with the specified sprint ID and the status that will
     * define and triggers the state change.
     *
     * @param sprintId the sprint ID that allows the searching for the specific sprint of a
     *                 given project.
     * @param status   the status which triggers the sprint state change.
     */
    public SprintStatusDto(String sprintId, String status) {
        this.sprintId = sprintId;
        this.status = status;
    }

    /**
     * Retrieves the sprint ID of the Sprint.
     *
     * @return the sprint ID of the Sprint as a String.
     */
    public String getSprintId() {
        return sprintId;
    }

    /**
     * Retrieves the status of the Sprint.
     *
     * @return the status of the Sprint as a String.
     */
    public String getSprintStatus() {
        return status;
    }

}
