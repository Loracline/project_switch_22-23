package org.switch2022.project.ddd.dto;

public class UserStoryInSprintDto {
    /**
     * Attributes
     */
    public final String userStoryId;

    public final String sprintId;


    /**
     * Constructor
     */
    public UserStoryInSprintDto(String userStoryId, String sprintId) {
        this.userStoryId = userStoryId;
        this.sprintId = sprintId;
    }
}
