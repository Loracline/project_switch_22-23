package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.TypologyService;

/**
 * Class CreateTypologyController is built to register projects.
 */
@Controller
public class CreateTypologyController {
    @Autowired
    private TypologyService typologyService;

    /**
     * This method receives a String name and call outher class method typology sevice.
     *
     * @param name that represents a String.
     * @return TRUE if the typology is created and added to the typology repository successfully.
     */

    public boolean createTypology(String name) {
        return typologyService.createTypology(name);
    }

}
