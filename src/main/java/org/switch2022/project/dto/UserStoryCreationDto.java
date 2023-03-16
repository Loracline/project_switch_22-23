package org.switch2022.project.dto;

public class UserStoryCreationDto {
    /**
     * Attributes
     */
    public final String userStoryNumber;
    public final String userStoryText;
    public final String actor;
    public final int priority;

    /**
     * This method creates an UserStoryCreationDto
     */
    public UserStoryCreationDto(String userStoryNumber, String userStoryText, String actor, int priority) {
        this.userStoryNumber = userStoryNumber;
        this.userStoryText = userStoryText;
        this.actor = actor;
        this.priority = priority;
    }

    public int getPriority(){
        return this.priority;
    }
}
