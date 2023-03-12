package org.switch2022.project.factories;

import org.switch2022.project.model.ProductBacklog;

public interface IFactoryProductBacklog {
    ProductBacklog createProductBacklog(IFactoryUserStory factoryUserStory);
}
