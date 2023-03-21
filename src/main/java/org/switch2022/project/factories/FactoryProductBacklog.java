package org.switch2022.project.factories;

import org.switch2022.project.model.ProductBacklog;

/**
 * Implementation of the FactoryProductBacklog interface that creates instances of the
 * ProductBacklog class.
 */
public class FactoryProductBacklog implements IFactoryProductBacklog {
    /**
     * This method creates a new ProductBacklog object.
     *
     * @param factoryUserStory creates user stories that will be stored in the ProductBacklog
     * @return a new ProductBacklog
     */

    public ProductBacklog createProductBacklog(IFactoryUserStory factoryUserStory) {
        return new ProductBacklog(factoryUserStory);
    }
}
