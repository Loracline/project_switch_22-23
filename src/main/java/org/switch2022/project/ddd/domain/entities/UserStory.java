package org.switch2022.project.ddd.domain.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.switch2022.project.ddd.domain.value_object.AcceptanceCriteria;

import java.util.List;

/**
 * Represents a User Story in a project.
 */
@ToString
public class UserStory {

    @Getter
    private String usId;
    @Setter
    private List<AcceptanceCriteria> acceptanceCriteria;
    @Getter
    private String usNumber;
    @Getter
    private String actor;
    @Getter
    private String usText;
    @Getter
    private String status;
    @Getter
    private String projectCode;

    /**
     * Constructs a new UserStory with the given parameters.
     *
     * @param usId The unique identifier of the User Story.
     * @param acceptanceCriteria The list of Acceptance Criteria for the User Story.
     * @param usNumber The User Story number.
     * @param actor The actor of the User Story.
     * @param usText The text of the User Story.
     * @param status The status of the User Story.
     * @param projectCode The project code associated with the User Story.
     */
    public UserStory(String usId, List<AcceptanceCriteria> acceptanceCriteria, String usNumber, String actor,
                     String usText, String status, String projectCode){
        this.usId = usId;
        this.acceptanceCriteria = acceptanceCriteria;
        this.usNumber = usNumber;
        this.actor = actor;
        this.usText = usText;
        this.status = status;
        this.projectCode = projectCode;
    }
}
