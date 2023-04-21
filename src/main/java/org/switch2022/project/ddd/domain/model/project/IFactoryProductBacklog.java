package org.switch2022.project.ddd.domain.model.project;


/**
 * Interface for a ProductBacklog factory.
 */
public interface IFactoryProductBacklog {
    /**
     * Creates a new ProductBacklog object with no return.
     *
     * @param projectCode to generate Product Backlog's id.
     */
    ProductBacklog createProductBacklog(String projectCode);
}

