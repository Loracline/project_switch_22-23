package org.switch2022.project.factories;

import org.switch2022.project.model.UserStory;

public class FactoryUserStory implements  IFactoryUserStory{
    public UserStory createUserStory(String userStoryNumber, String actor, String userStoryText) {
         return UserStory.createUserStory(userStoryNumber, actor, userStoryText);
    }
}
