package org.switch2022.project.ddd.datamodel_jpa;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


/**
 * The class UserStory was built to create and manage new userStories.
 * An UserStory is defined by: an userStoryNumber, which is it unique ID, a
 * projectCode,an actor, an userStoryText,
 * an effort and a status.
 */
@NoArgsConstructor
@Entity
@Table(name = "userStories")
public class UserStoryJpa {
    /**
     * Attributes
     */
    @Id
    @Getter
    private String usId;
    @ElementCollection
    private List<String> acceptanceCriteria;
    private String usNumber;
    private String actor;
    private String usText;
    private String status;
    private String projectCode;

    /**
     * Constructor
     * It creates an userStory using its identifier: usId  and your minimals attributes.
     * Attributes: usNumber, actor, usText, acceptanceCriteria.
     */
    public UserStoryJpa(String usId, List<String> acceptanceCriteria, String usNumber,
                        String actor, String usText,
                        String status, String projectCode) {
        this.usId = usId;
        this.acceptanceCriteria = Collections.unmodifiableList(acceptanceCriteria);
        this.usNumber = usNumber;
        this.actor = actor;
        this.usText = usText;
        this.status = status;
        this.projectCode = projectCode;
    }

    public String getUsId() {
        return usId;
    }

    public List<String> getAcceptanceCriteria() {return new ArrayList<>(acceptanceCriteria);}

    public String getUsNumber() {
        return usNumber;
    }

    public String getActor() {
        return actor;
    }

    public String getUsText() {
        return usText;
    }

    public String getStatus() {
        return status;
    }

    public String getProjectCode() {
        return projectCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserStoryJpa that = (UserStoryJpa) o;
        return Objects.equals(usId, that.usId) &&
                Objects.equals(acceptanceCriteria, that.acceptanceCriteria) &&
                Objects.equals(usNumber, that.usNumber) &&
                Objects.equals(actor, that.actor) &&
                Objects.equals(usText, that.usText) &&
                Objects.equals(status, that.status) &&
                Objects.equals(projectCode, that.projectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usId, acceptanceCriteria, usNumber, actor, usText, status, projectCode);
    }
}
