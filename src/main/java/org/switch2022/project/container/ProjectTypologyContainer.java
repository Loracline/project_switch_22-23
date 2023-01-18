package org.switch2022.project.container;

import org.switch2022.project.model.ProjectTypology;
import java.util.List;

public class ProjectTypologyContainer {

    private List<ProjectTypology> typologies;

    public ProjectTypologyContainer(List<ProjectTypology> typologies) {
        this.typologies = typologies;
    }

    public boolean doesTypologyExist(ProjectTypology projectTypology) {
        return this.typologies.contains(projectTypology);
    }

    public boolean createProjectTypology(String projectTypology) {
        ProjectTypology newprojectTypology = new ProjectTypology(projectTypology);
        boolean isAddedToList = false;
        if (!doesTypologyExist(newprojectTypology)) {
            typologies.add(newprojectTypology);
            isAddedToList = true;
        }
        return isAddedToList;
    }
    public List<ProjectTypology> getTypologies() {
        return typologies;
    }
}