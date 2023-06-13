package org.switch2022.project.ddd.dto;

public class UserStoryStatusDto {
    /**
     * Attributes
     */
    public final String usId;
    public final String sprintId;
    public final String status;

    /**
     * Constructor
     */
    public UserStoryStatusDto(String usId, String sprintId, String status) {
        this.usId = usId;
        this.sprintId = sprintId;
        this.status = status;
    }
}
