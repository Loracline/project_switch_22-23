package org.switch2022.project.ddd.dto;

public class UserStoryStatusDto {
    /**
     * Attributes
     */
    public final String usId;
    public final String projectCode;
    public final String status;

    /**
     * Constructor
     */
    public UserStoryStatusDto(String usId, String projectCode, String status) {
        this.usId = usId;
        this.projectCode = projectCode;
        this.status = status;
    }
}
