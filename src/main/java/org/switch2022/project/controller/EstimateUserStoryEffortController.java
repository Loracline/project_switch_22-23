package org.switch2022.project.controller;
import org.switch2022.project.container.Company;


/**
 * Class EstimateUserStoryEffortController acts as an intermediary between the
 * user interface (UI) and the business logic underlying the "US021 - As Team Member,
 * I want to estimate the effort of a user story in a sprint, during
 * the sprint planning ceremony."
 */

public class EstimateUserStoryEffortController {

    /**
     * Attributes of the class EstimateUserStoryEffortController, according to the Class Diagram.
     */
    private final Company company;

    /**
     * EstimateUserStoryEffortController constructor
     */

    public EstimateUserStoryEffortController(Company company) {
        this.company = company;
    }


}
