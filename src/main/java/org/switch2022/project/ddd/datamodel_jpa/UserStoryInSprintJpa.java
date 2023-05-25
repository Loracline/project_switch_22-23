package org.switch2022.project.ddd.datamodel_jpa;

import lombok.Data;
import lombok.Getter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    /**
     * Constructor
     * It creates an userStoryInSprint using its identifier: usId  and effort.
     */
    public UserStoryInSprintJpa(String usId, int effort) {
        this.usId = usId;
        this.effort = effort;

    }

    protected UserStoryInSprintJpa() {
    }
}
