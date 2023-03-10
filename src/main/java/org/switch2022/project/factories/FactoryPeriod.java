package org.switch2022.project.factories;

import org.switch2022.project.utils.Period;
import java.time.LocalDate;


public class FactoryPeriod implements IFactoryPeriod {
    public Period createPeriod(LocalDate startDate, int sprintDuration) {
        return new Period(startDate,  sprintDuration);
    }
}


