package org.switch2022.project.factories;

import org.switch2022.project.model.ProductBacklog;

public class FactoryProductBacklog implements IFactoryProductBacklog {
    @Override
    public ProductBacklog createProductBacklog(IFactoryUserStory factoryUserStory) {
        return new ProductBacklog(factoryUserStory);
    }
}
