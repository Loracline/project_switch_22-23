package org.switch2022.project.ddd.datamodel_jpa;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a project typology entity in the Java Persistence API (JPA) data model.
 * This class is used to map business sector data to the corresponding database table.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "typologies")
public class TypologyJpa {
    /**
     * Attributes
     */
    @Id
    private String typologyId;
    private String typologyName;

    /**
     * Constructor
     * It creates a TypologyJpa using its identifier: typologyId  and its typologyName.
     *
     * @param typologyId   The identifier of the typology.
     * @param typologyName The name of the typology.
     */
    public TypologyJpa(String typologyId, String typologyName) {
        this.typologyId = typologyId;
        this.typologyName = typologyName;
    }
}
