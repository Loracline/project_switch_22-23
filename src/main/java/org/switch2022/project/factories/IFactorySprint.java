package org.switch2022.project.factories;

import org.switch2022.project.model.Sprint;

import java.time.LocalDate;

public interface IFactorySprint {

    Sprint createSprint(LocalDate startDate, int sprintDuration,
                               String sprintNumber,
                               IFactoryPeriod iFactoryPeriod,
                               IFactorySprintBacklog iFactorySprintBacklog);
}
