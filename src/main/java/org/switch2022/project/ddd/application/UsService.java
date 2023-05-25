package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.user_story.IFactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.ProjectNotFoundException;
import org.switch2022.project.ddd.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class UsService allows to create a new userStory.
 */

@Service
public class UsService {
    @Autowired
    @Qualifier("usRepositoryJpa")
    private IUsRepository usRepository;
    @Autowired
    private IProjectRepository projectRepository;
    @Autowired
    private IFactoryUserStory factoryUserStory;


    /**
     * This method creates a userStory and adds it to the product backlog or throws an exception
     * if userStory is not created. This method receives a UserStoryCreationDto and  creates a User
     * Story by converting the
     * project
     * code of interest into a numeric value, creating a project code. The created User Story is
     * then added to the User Story repository, and its ID is generated. The User Story is also
     * added to the product backlog with the specified priority, and if it already exists it
     * should be deleted and the exception is rethrown.
     *
     * @param userStoryCreationDto the UserStoryCreationDto that represents the data for
     *                             creating the user story
     * @return the created User Story ID
     * @throws AlreadyExistsInRepoException if an error occurs during the User Story creation
     *                                      process or if the User Story
     */

    public UsId createUs(UserStoryCreationDto userStoryCreationDto) throws Exception {
        String projectCodeOfInterest = userStoryCreationDto.projectCode;
        int codeNumber = Utils.getIntFromAlphanumericString(projectCodeOfInterest, "P");
        Code projectCode = new Code(codeNumber);

        UserStory userStory = createUserStory(userStoryCreationDto, projectCode);
        usRepository.save(userStory);

        UsId usId = new UsId(projectCode.getCode(), userStory.getUsNumber());

        try {
            addUsToProductBacklog(usId, projectCode, userStoryCreationDto.priority);
        } catch (AlreadyExistsInRepoException e) {
            deleteUs(usId);
            throw e;
        }
        return usId;
    }

    /**
     * This method deletes the userStory or throws an exception if the userStory does not exist.
     *
     * @param usId of userStory to be deleted from the repository.
     */

    public boolean deleteUs(UsId usId) {
        usRepository.delete(usId);
        return true;
    }

    /**
     * This method adds a userStoryId to the productBacklog of a project.
     *
     * @param usId        of the userStory.
     * @param projectCode of the project where the user ID will be added.
     * @param priority    that the ID will have in the ProductBacklog.
     * @return true if the ID is successfully added. otherwise it will return false.
     * @throws ProjectNotFoundException     if the projectCode doesn't match any Project in the
     *                                      repository.
     * @throws AlreadyExistsInRepoException if the User Story is already in the Product Backlog.
     */

    public boolean addUsToProductBacklog(UsId usId, Code projectCode, int priority) {

        Optional<Project> projectOptional = projectRepository.findByCode(projectCode);

        Project project;

        if (projectOptional.isPresent()) {
            project = projectOptional.get();
            if (!project.addUserStory(priority, usId)) {
                throw new AlreadyExistsInRepoException("The User Story is already in the Product " +
                        "Backlog");
            }
        } else {
            throw new ProjectNotFoundException("No project with that code");
        }
        project.addUserStory(priority, usId);
        return true;
    }

    /**
     * This method creates an UserStory given a UserStoryCreationDto and the corresponding
     * Project code.
     *
     * @param userStoryCreationDto represents the data for creating the User Story
     * @param projectCode          of the project where the user ID will be created
     * @return a User Story.
     */
    private UserStory createUserStory(UserStoryCreationDto userStoryCreationDto, Code projectCode) {
        UsNumber userStoryNumber = new UsNumber(userStoryCreationDto.userStoryNumber);
        UsText userStoryText = new UsText(userStoryCreationDto.userStoryText);
        Actor actor = new Actor(userStoryCreationDto.actor);
        List<AcceptanceCriteria> acceptanceCriteria =
                convertToAcceptanceCriteriaList(userStoryCreationDto.acceptanceCriteria);

        return factoryUserStory.createUserStory(userStoryNumber, userStoryText, actor,
                acceptanceCriteria, projectCode);
    }

    /**
     * This method receives a List of String that represent the Acceptance Criteria of a given
     * User Story and converts it into a List of objects of type AcceptanceCriteria.
     *
     * @param acceptanceCriteria list of Acceptance Criteria as a String
     * @return a list of objects of type AcceptanceCriteria.
     */
    private List<AcceptanceCriteria> convertToAcceptanceCriteriaList(List<String> acceptanceCriteria) {
        List<AcceptanceCriteria> newAcceptanceCriteria = new ArrayList<>();
        for (String acceptanceCriterion : acceptanceCriteria) {
            AcceptanceCriteria acceptanceCriteriaElement =
                    new AcceptanceCriteria(acceptanceCriterion);
            newAcceptanceCriteria.add(acceptanceCriteriaElement);
        }
        return newAcceptanceCriteria;
    }
}