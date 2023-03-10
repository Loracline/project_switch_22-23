package org.switch2022.project.factories;

import org.switch2022.project.model.SprintBacklog;
/**
 * Implementation of the IFactorySprintBacklog interface that creates instances of the
 * Sprint Backlog class
 */
public class FactorySprintBacklog implements IFactorySprintBacklog{

    /**
     * This method creates a new Sprint Backlog object.
     *
     * @return a new Sprint Backlog object.
     */
    @Override
    public SprintBacklog createSprintBacklog() {
        return new SprintBacklog();
    }
}
