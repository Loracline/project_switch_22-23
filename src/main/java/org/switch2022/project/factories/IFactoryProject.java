package org.switch2022.project.factories;

import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectTypology;

public interface IFactoryProject {
    Project createProject (String projectCode, String name, Customer customer,
                           ProjectTypology projectTypology,
                           BusinessSector businessSector);

}
