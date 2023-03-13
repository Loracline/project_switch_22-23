package org.switch2022.project.factories;

import org.switch2022.project.dto.ProjectCreationDto;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectTypology;

public interface IFactoryProject {
    Project createProject (ProjectCreationDto projectCreationDto, Customer customer,
                           ProjectTypology projectTypology,
                           BusinessSector businessSector, IFactoryProductBacklog factoryProductBacklog,
                           IFactoryUserStory factoryUserStory,IFactoryPeriod iFactoryPeriod,
                           IFactorySprintBacklog iFactorySprintBacklog, IFactorySprint iFactorySprint);

}
