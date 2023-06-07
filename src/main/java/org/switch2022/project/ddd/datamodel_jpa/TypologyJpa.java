package org.switch2022.project.ddd.datamodel_jpa;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Represents a project typology entity in the Java Persistence API (JPA) data model.
 * This class is used to map business sector data to the corresponding database table.
 */
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

    /**
     * This method checks if two instances of TypologyJpa are equal by
     * comparing its ids.
     *
     * @param o object to compare with.
     * @return TRUE if the two have the same id, and FALSE otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TypologyJpa that = (TypologyJpa) o;
        return typologyId.equals(that.typologyId);
    }

    /**
     * The hashCode() method is used to generate a unique hash code for an
     * object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(typologyId);
    }

    /**
     * Retrieves the typology ID.
     * @return The typology ID.
     */
    public String getTypologyId() {
        return typologyId;
    }

    /**
     * Retrieves the typology name.
     * @return The typology name.
     */
    public String getTypologyName() {
        return typologyName;
    }
}
