package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.IFactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;
import org.switch2022.project.ddd.dto.UserStoryDto;
import org.switch2022.project.ddd.dto.mapper.UserStoryMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class UsService allows to create a new userStory.
 */

public class UsService {
    @Autowired
    private IUsRepository usRepository;
    @Autowired
    private IProjectRepository projectRepository;
    @Autowired
     private IFactoryUserStory factoryUserStory;
    @Autowired
    private UserStoryMapper userStoryMapper;

    public UsService() {
    }

    /**
     * This method returns the ID of the userStory newly created or throws an exception if userStory
     * not created.
     *
     * @param userStoryNumber the number of the userStory.
     * @param userStoryText the description of the userStory.
     * @param actor the actor of the userStory.
     * @param priority the priority of the userStory.
     * @param projectCode          will be associated with the newly created userStory.
     * @return userStoryId from the newly created userStory.
     */

    public UsId createUs(UsNumber userStoryNumber, UsText userStoryText, Actor actor, int priority, Code projectCode) throws Exception {
        final UserStory userStory = factoryUserStory.createUserStory(userStoryNumber, userStoryText, actor, priority, projectCode);
        usRepository.add(userStory);
        UsId usId = new UsId(projectCode.getCode(), userStory.getUsNumber());
        return usId;
    }

    /**
     * This method deletes the userStory or throws an exception if the userStory does not exist.
     *
     * @param usId of userStory to be deleted from the repository.
     */

    public boolean deleteUs(UsId usId) throws Exception {
        usRepository.delete(usId);
        return true;
    }

    /**
     * Requests a list of userStories with the status planned.
     *
     * @param usId ID of the userStory.
     * @return a list of all userStoriesDto with the status planned.
     */

    public List<UserStoryDto> requestAllPlannedUs(List<UsId> usId) {
        List<UserStory> userStories = usRepository.getListOfUsWithMatchingIds(usId);
        List<UserStoryDto> userStoriesDto = new ArrayList<>();
        if (!userStories.isEmpty()) {
            for (UserStory userStory : userStories) {
                if (userStory.hasStatus(Status.PLANNED)) {
                    userStoriesDto.add(userStoryMapper.userStoryToDto(userStory));
                }
            }
        }
        return userStoriesDto;
    }

    /**
     * This method adds a userStoryId to the productBacklog of a project.
     *
     * @param usId        of the userStory.
     * @param projectCode of the project where the user ID will be added.
     * @param priority    that the ID will have in the ProductBacklog.
     * @return true if the ID is successfully added. otherwise it will return false.
     * @throws Exception if the priority is out og bounds, if the id is already in the ProductBacklog
     *                   and if the projectCode doesn't match any Project in the repository.
     */

    public boolean addUsToProductBacklog(UsId usId, Code projectCode, int priority) throws Exception {

        Optional<Project> projectOptional = projectRepository.getProjectByCode(projectCode);

        Project project;

        if (projectOptional.isPresent()) {
            project = projectOptional.get();
            if (!project.addUserStory(priority, usId)) {
                throw new Exception("The User Story is already in the Product Backlog");
            }
        } else {
            throw new Exception("No project with that code");
        }
        project.addUserStory(priority, usId);
        return true;
    }
}