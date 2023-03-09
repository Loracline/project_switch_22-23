package org.switch2022.project.model;

import org.switch2022.project.factories.FactoryUserStory;
import org.switch2022.project.utils.Period;

import java.time.LocalDate;
import java.util.List;

class Sprint {
    private int sprintNumber;
    private FactoryPeriod factoryPeriod;
    private Period period;
    private SprintBacklog sprintBacklog;

    public Sprint(LocalDate startDate, int sprintDuration, int sprintNumber) {
        this.sprintNumber = sprintNumber;
        this.factoryPeriod = new FactoryPeriod();
        this.period = new Period(startDate, sprintDuration);
        this.sprintBacklog = new SprintBacklog();
    }

    public LocalDate getStartDate() {
        return period.getStartDate();
    }

    public LocalDate getEndDate() {
        return period.getEndDate();
    }

    /**
     * This method makes a deep copy of the Sprint Backlog of this Sprint.
     * @param factoryUserStory used to create the copy User Stories.
     * @return a copy Sprint Backlog with a list of the copied User Stories.
     */
    public SprintBacklog getSprintBacklogCopy(FactoryUserStory factoryUserStory) {
        SprintBacklog sprintBacklogCopy = new SprintBacklog();
        List<UserStory> copyList =
                this.sprintBacklog.getUserStoriesCopy(factoryUserStory);
        int i = 0;
        while (i < copyList.size()) {
            sprintBacklogCopy.addUserStory(copyList.get(i));
            i++;
        }
        return sprintBacklogCopy;
    }
}
