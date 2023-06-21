package org.switch2022.project.ddd.datamodel_jpa;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "userStoriesInSprint")
public class UserStoryInSprintJpa {

    /**
     * Attributes
     */
    @Id
    @Getter
    private String usId;
    private int effort;
    @ManyToOne
    @JoinColumn(name = "sprint")
    private SprintJpa sprint;

    /**
     * Constructor
     *
     * @param usId   of the User Story.
     * @param effort of this user story in this sprint.
     * @param sprint where this User Story will be located.
     */
    public UserStoryInSprintJpa(String usId, int effort, SprintJpa sprint) {
        this.usId = usId;
        this.effort = effort;
        this.sprint = sprint;
    }

    /**
     * Constructor
     * It creates an userStoryInSprint with empty .
     */
    protected UserStoryInSprintJpa() {
    }
}
