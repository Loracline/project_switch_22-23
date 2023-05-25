package org.switch2022.project.ddd.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.switch2022.project.ddd.datamodel_jpa.UserStoryJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.UserStoryDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.user_story.IUsRepository;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.infrastructure.jpa.IUserStoryJpaRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for implementing the IUserStoryRepository interface using JPA for persistence.
 * It performs operations such as save, delete, and find on user stories. The implementation uses
 * UserStoryDomainDataAssembler to convert between UserStory domain objects and UserStoryJpa data objects.
 */
@Repository("usRepositoryJpa")
public class UserStoryRepositoryJpa implements IUsRepository {

    @Autowired
    IUserStoryJpaRepository repository;

    @Autowired
    UserStoryDomainDataAssembler assembler;

    /**
     * Saves a UserStory object to the repository. Throws AlreadyExistsInRepoException if the user story
     * ID already exists.
     *
     * @param userStory The UserStory object to be saved
     * @return true if the save operation is successful, false otherwise
     */

    @Override
    public boolean save(UserStory userStory) {
        UserStoryJpa userStoryJpa = assembler.toData(userStory);
        if (repository.existsByUsId(userStory.getUsId())) {
            throw new AlreadyExistsInRepoException("User story ID already exists");
        }
        repository.save(userStoryJpa);
        return true;
    }

    /**
     * Deletes a UserStory object from the repository using its UsId. Throws AlreadyExistsInRepoException if the
     * user story is already in the product backlog.
     *
     * @param usId The UsId of the user story to be deleted
     * @return true if the delete operation is successful, false otherwise
     */

    @Override
    public boolean delete(UsId usId) {
        if (!repository.existsByUsId(usId.getUserStoryId())) {
            throw new AlreadyExistsInRepoException("The User Story is already in the Product Backlog");
        }
        return repository.deleteByUsId(usId.getUserStoryId());
    }

    /**
     * Retrieves a list of UserStory objects with matching UsIds from the repository.
     *
     * @param usId A list of UsId objects to match against user stories in the repository
     * @return A list of UserStory objects with matching UsIds
     */

    @Override
    public List<UserStory> getListOfUsWithMatchingIds(List<UsId> usId) {
        List<String> userStoryId = new ArrayList<>();
        for (UsId id : usId) {
            String usIdToString = id.getUserStoryId();
            userStoryId.add(usIdToString);
        }

        Iterable<UserStoryJpa> userStoriesWithMatchingIds = repository.findAllByUsIdIn(userStoryId);
        List<UserStory> userStoryList = new ArrayList<>();
        for (UserStoryJpa userStoryJpa : userStoriesWithMatchingIds) {
            userStoryList.add(assembler.toDomain(userStoryJpa));
        }
        return userStoryList;
    }
}