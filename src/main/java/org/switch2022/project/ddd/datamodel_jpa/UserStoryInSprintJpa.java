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

    @ManyToOne()
    @JoinColumn(name = "sprint", nullable=false)
    private SprintJpa sprint;

    /**
     * Constructor
     * It creates an userStoryInSprint using its identifier: usId  and effort.
     */
    public UserStoryInSprintJpa(String usId, int effort, SprintJpa sprintJpa) {
        this.usId = usId;
        this.effort = effort;
        this.sprint = sprintJpa;

    }

    protected UserStoryInSprintJpa() {
    }
}
