package org.switch2022.project.ddd.datamodel.JPA;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


/**
 * The class UserStory was built to create and manage new userStories.
 * An UserStory is defined by: an userStoryNumber, which is it unique ID, a
 * projectCode,an actor, an userStoryText,
 * an effort and a status.
 */
@Data
@Entity
@Table(name="userStories")
public class UserStoryJpa {
    /**
     * Attributes
     */
    @Id
    @Embedded
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
    public UserStoryJpa(String usId, List<String> acceptanceCriteria, String usNumber, String actor, String usText,
                        String status, String projectCode) {
        this.usId = usId;
        this.acceptanceCriteria = acceptanceCriteria;
        this.usNumber = usNumber;
        this.actor = actor;
        this.usText = usText;
        this.status = status;
        this.projectCode = projectCode;
    }

    protected UserStoryJpa(){
    }

}
