package org.switch2022.project.ddd.domain.model.typology;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.domain.value_object.ProjectTypologyId;
import org.switch2022.project.ddd.utils.Validate;

import java.util.Objects;

/**
 * Class ProjectTypology is built to create and manage new project typologies.
 * A project typology is identied by its id and defined by its name.
 */

public class Typology implements Entity<Typology> {
    /**
     * Attributes
     */
    private final ProjectTypologyId typologyId;
    private final Name typologyName;

    /**
     * Constructor
     */
    protected Typology(int typologyNumber, Name typologyName) {
        Validate.notNegative(typologyNumber, "typology id");
        Validate.notNull(typologyName, "Typology name can't be null");
        this.typologyId = new ProjectTypologyId(typologyNumber);
        this.typologyName = typologyName;
    }

    /**
     * This method checks if two instances of ProjectTypology are equal by
     * comparing its ids.
     *
     * @param o ProjectTypology instance to compare with.
     * @return TRUE if the two have the same id, and FALSE otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Typology typology = (Typology) o;
        return typologyId.equals(typology.typologyId);
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
     * This method checks if two instances of Typology are equal by comparing the
     * value of the attribute typologyId.
     *
     * @param other Typology instance to compare with.
     * @return TRUE if the two have the same attribute value, and FALSE
     * otherwise.
     */
    @Override
    public boolean sameIdentityAs(Typology other) {
        return this.typologyId.equals(other.typologyId);
    }

    /**
     * This getter method returns a string with the typology name.
     */

    public String getTypologyName() {
        return this.typologyName.getName();
    }

    /**
     * This getter method returns a string with the typology id.
     */
    public String getTypologyId() {
        return this.typologyId.getProjectTypologyId();
    }
}
