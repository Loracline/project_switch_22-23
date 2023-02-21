package org.switch2022.project.model;

import java.util.Objects;

/**
 * Class ProjectTypology is built to create and manage new project typologies.
 * A project typology is defined by name.
 */
public class ProjectTypology {
    /**
     * Attributes
     */
    private final String projectTypologyName;

    /**
     * Constructor
     */
    public ProjectTypology(String projectTypologyName) {
        this.projectTypologyName = projectTypologyName.toLowerCase();
    }

    public String getProjectTypologyName() {
        return this.projectTypologyName;
    }

    /**
     * This method checks if two instances of ProjectTypology are equal by
     * comparing its names.
     *
     * @param toCompare ProjectTypology instance to compare with.
     * @return TRUE if the two have the same typology name, and FALSE otherwise.
     */
    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) {
            return true;
        }
        if (!(toCompare.getClass() == this.getClass())) {
            return false;
        }
        ProjectTypology that = (ProjectTypology) toCompare;
        return Objects.equals(projectTypologyName, that.projectTypologyName.toLowerCase());
    }
    /**
     * The hashCode() method is used to generate a unique hash code for an
     * object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(projectTypologyName);
    }
}
