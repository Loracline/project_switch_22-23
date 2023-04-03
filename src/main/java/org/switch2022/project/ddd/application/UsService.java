package org.switch2022.project.ddd.application;

import org.switch2022.project.ddd.domain.infrastructure.UsRepository;
import org.switch2022.project.ddd.domain.interfaces.IUsRepository;
import org.switch2022.project.ddd.domain.interfaces.IUsService;
import org.switch2022.project.ddd.domain.model.user_story.FactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.IFactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;

import java.util.List;

/**
 * Class UsService allows to create a new userStory.
 */

public class UsService implements IUsService {

    private final IUsRepository usRepository;
    private final IFactoryUserStory factoryUserStory;

    /**
     * Constructor.
     *
     * @param usRepository     where the userStory will be saved.
     * @param factoryUserStory allows to create a userStory.
     */

    public UsService(UsRepository usRepository, FactoryUserStory factoryUserStory) {
        this.usRepository = usRepository;
        this.factoryUserStory = factoryUserStory;
    }

    /**
     * This method returns the ID of the userStory newly created or throws an exception if userStory
     * not created.
     *
     * @param userStoryCreationDto will be the base for the creation of a new userStory.
     * @param projectCode          will be associated with the newly created userStory.
     * @return userStoryId from the newly created userStory.
     */

    @Override
    public UsId createUs(UserStoryCreationDto userStoryCreationDto, String projectCode) throws Exception {
        final UserStory userStory = factoryUserStory.createUserStory(userStoryCreationDto, projectCode);
        usRepository.add(userStory);
        return userStory.getUsId();
    }

    /**
     * This method deletes the userStory or throws an exception if the userStory does not exist.
     *
     * @param userStory to be deleted from the repository.
     * @return userStory deletion.
     */

    @Override
    public void deleteUs(UserStory userStory) throws Exception {
        usRepository.delete(userStory);
    }

    /**
     * Requests a list of userStories with the status planned or throws an exception if empty.
     *
     * @param usId ID of the userStory.
     * @return a list of all userStories with the status planned.
     */

    @Override
    public List<UserStory> requestAllPlannedUs(List usId) throws Exception {
        return usRepository.getAllPlannedUs(usId);
    }
}