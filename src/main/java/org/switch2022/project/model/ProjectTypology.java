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
        if (!(toCompare instanceof ProjectTypology)) {
            return false;
        }
        ProjectTypology that = (ProjectTypology) toCompare;
        return Objects.equals(projectTypologyName, that.projectTypologyName);
    }
}
