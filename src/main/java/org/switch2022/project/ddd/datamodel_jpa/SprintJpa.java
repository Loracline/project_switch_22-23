package org.switch2022.project.ddd.datamodel_jpa;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Sprint was built to create and manage new sprints.
 * A Sprint is defined by: a unique sprintId, sprint number, project code, period and a
 * list of user stories in that sprint.
 */
@Data
@Entity
@Table(name="sprints")
public class SprintJpa {
    /**
     * Attributes
     */
    @Id
    @Getter
    private String sprintId;
    private String sprintNumber;
    private String projectCode;
    private String startDate;
    private String endDate;
    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserStoryInSprintJpa> userStoriesInSprint;

    /**
     * Constructor
     * It creates a SprintJpa using its identifier: sprintId  and its minimals
     * attributes.
     * Attributes: usNumber, actor, usText, acceptanceCriteria.
     */
    public SprintJpa(String sprintId, String sprintNumber, String projectCode,
                  String startDate, String endDate){
        this.sprintId = sprintId;
        this.sprintNumber = sprintNumber;
        this.projectCode = projectCode;
        this.startDate = startDate;
        this.endDate = endDate;
        userStoriesInSprint = new ArrayList<>();
    }

    protected SprintJpa(){

    }

}
