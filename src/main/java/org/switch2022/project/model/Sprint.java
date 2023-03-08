package org.switch2022.project.model;

import org.switch2022.project.utils.Period;

import java.time.LocalDate;

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
}
