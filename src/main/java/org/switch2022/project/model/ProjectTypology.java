package org.switch2022.project.model;

import java.util.Objects;

public class ProjectTypology {

    private String projectTypology;

    public ProjectTypology(String projectTypology){
        this.projectTypology=projectTypology.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectTypology)) return false;
        ProjectTypology that = (ProjectTypology) o;
        return Objects.equals(projectTypology, that.projectTypology);
    }

}
