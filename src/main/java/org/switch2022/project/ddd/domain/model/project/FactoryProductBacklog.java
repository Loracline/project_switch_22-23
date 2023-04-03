package org.switch2022.project.ddd.domain.model.project;

import org.switch2022.project.ddd.domain.value_object.PbId;

/**
 * Implementation of the FactoryProductBacklog interface that creates instances of the
 * ProductBacklog class.
 */
public class FactoryProductBacklog implements IFactoryProductBacklog {
    /**
     * This method creates a new ProductBacklog object.
     *
     * @param projectCode is the id from the project where the Product Backlog is and
     *                    is also part of the ProductBacklog id, which in this method
     *                    as well.
     * @return a new ProductBacklog.
     */

    public ProductBacklog createProductBacklog(String projectCode) {
        return new ProductBacklog(new PbId(projectCode));
    }
}
