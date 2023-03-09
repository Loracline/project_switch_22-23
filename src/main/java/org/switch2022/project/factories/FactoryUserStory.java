package org.switch2022.project.factories;

import org.switch2022.project.model.UserStory;

public interface FactoryUserStory {
    public UserStory createUserStory(String userStoryNumber, String actor, String userStoryText);
}
