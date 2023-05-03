package org.switch2022.project.ddd.domain.model.project;


import org.springframework.stereotype.Component;

/**
 * Implementation of the FactoryProductBacklog interface that creates instances of the
 * ProductBacklog class.
 */

@Component
public class FactoryProductBacklog implements IFactoryProductBacklog {
    /**
     * This method creates a new ProductBacklog object.
     *
     * @param projectCode is the id from the project where the Product Backlog is and
     *                    is also necessary to create the  ProductBacklog id.
     * @return a new ProductBacklog.
     */

    public ProductBacklog createProductBacklog(String projectCode) {

        return new ProductBacklog(projectCode);
    }
}
