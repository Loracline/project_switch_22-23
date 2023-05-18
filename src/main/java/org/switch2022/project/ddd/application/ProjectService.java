package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.project.IFactoryProject;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.exceptions.ProjectNotFoundException;
import org.switch2022.project.ddd.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class ProjectService allows to create and
 * manipulate Project aggregate.
 */
@Service
public class ProjectService {
    /**
     * Attributes
     */
    @Autowired
    private IFactoryProject factoryProject;
    @Autowired
    private IProjectRepository projectRepository;
    @Autowired
    private IUsRepository usRepository;

    /**
     * This method will return an Optional Project from the repository.
     *
     * @param code of the project to be retrieved.
     * @return an optional from the repository.
     */
    public Optional<Project> getProjectByCode(String code) {
        int codeNumber = Utils.getIntFromAlphanumericString(code, "P");
        Code projectCode = new Code(codeNumber);
        return projectRepository.findByCode(projectCode);
    }

    /**
     * This method returns a list of UsID, from the ProductBacklog of a Project
     *
     * @param code of the Project requested
     * @return a list of userStories ID
     * @throws Exception if the projectCode doesn't match any Project in the repository.
     */

    public List<UserStory> getProductBacklog(String code) {
        Optional<Project> projectOptional = getProjectByCode(code);

        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            List<UsId> productBacklog = project.getProductBacklog();
            return requestAllPlannedUserStories(productBacklog);
        } else {
            throw new ProjectNotFoundException("No project with that code");
        }
    }

    /**
     * Requests a list of userStories with the status planned.
     *
     * @param usIds ID of the userStory.
     * @return a list of all userStoriesDto with the status planned.
     */

    public List<UserStory> requestAllPlannedUserStories(List<UsId> usIds) {
        List<UserStory> userStories = usRepository.getListOfUsWithMatchingIds(usIds);
        List<UserStory> userStoriesPlanned = new ArrayList<>();
        if (!userStories.isEmpty()) {
            for (UserStory userStory : userStories) {
                if (userStory.hasStatus(Status.PLANNED)) {
                    userStoriesPlanned.add(userStory);
                }
            }
        }
        return userStoriesPlanned;
    }
}
