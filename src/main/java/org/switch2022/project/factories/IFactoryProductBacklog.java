package org.switch2022.project.factories;

import org.switch2022.project.model.ProductBacklog;

/**
 * Interface for a ProductBacklog factory.
 */
public interface IFactoryProductBacklog {
    /**
     * Creates a new ProductBacklog object with no return.
     *
     * @param factoryUserStory creates user stories that will be stored in the ProductBacklog
     */
    ProductBacklog createProductBacklog(IFactoryUserStory factoryUserStory);
}
