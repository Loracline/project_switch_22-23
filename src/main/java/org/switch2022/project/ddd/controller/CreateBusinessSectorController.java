package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.BusinessSectorService;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

/**
 * Class CreateBusinessSectorController is built to create business sectors.
 */
@Controller
public class CreateBusinessSectorController {
    @Autowired
    private BusinessSectorService businessSectorService;

    /**
     * Constructor
     */

    public CreateBusinessSectorController() {
    }

    /**
     * This method receives a name as a String and creates a new business sector with that name.
     *
     * @param name that represents the name of a business sector.
     * @return TRUE if the business sector is created, and throws an AlreadyExistsInRepoException exception otherwise.
     */

    public boolean createBusinessSector(String name) {
        return businessSectorService.createBusinessSector(name);
    }
}


