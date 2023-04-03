package org.switch2022.project.ddd.dto;


/**
 * Represents a user story creation data transfer object.
 * This class contains information about the user story such as its identifier,
 * description, actor and priority.
 */

public class UserStoryCreationDto {

    /**
     * Attributes
     */
    public final String userStoryNumber;
    public final String userStoryText;
    public final String actor;
    public final int priority;

    /**
     * Constructs a new UserStoryCreationDto object with the specified user story number,
     * user story text, actor and priority.
     *
     * @param userStoryNumber the identifier of the user story
     * @param userStoryText   the description of the user story
     * @param actor           the actor who will use or benefit from the user story
     * @param priority        the priority of the user story
     */
    public UserStoryCreationDto(String userStoryNumber, String userStoryText, String actor, int priority) {
        this.userStoryNumber = userStoryNumber;
        this.userStoryText = userStoryText;
        this.actor = actor;
        this.priority = priority;
    }

    /**
     * Returns the priority of the user story.
     *
     * @return an integer value representing the priority of the user story.
     */
    public int getPriority() {
        return this.priority;
    }
}

