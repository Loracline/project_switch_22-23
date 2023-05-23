package org.switch2022.project.ddd.infrastructure.JPA;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.ddd.datamodel.JPA.UserStoryJpa;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;

import java.util.Optional;

/**
 * Repository interface for managing UserStoryJpa entities.
 * Extends CrudRepository for basic CRUD operations.
 */
public interface UserStoryJpaRepository extends CrudRepository<UserStoryJpa, String> {

   /**
    * Adds a new UserStoryJpa entity to the repository.
    *
    * @param userStoryJpa The UserStoryJpa entity to be added.
    * @return true if the operation is successful, false otherwise.
    */
   public boolean add(UserStoryJpa userStoryJpa);

   /**
    * Deletes a UserStoryJpa entity by its unique identifier (usId) from the repository.
    *
    * @param usId The unique identifier of the UserStoryJpa entity to be deleted.
    * @return true if the operation is successful, false otherwise.
    */
   public boolean deleteByUsId(String usId);

   /**
    * Finds all UserStoryJpa entities with the specified identifiers (usId).
    *
    * @param ids An iterable containing the unique identifiers of the UserStoryJpa entities to be found.
    * @return An Iterable containing the found UserStoryJpa entities.
    */
   public Iterable<UserStoryJpa> findAllById(Iterable<String> ids);

   /**
    * Finds a UserStoryJpa entity by its unique identifier (usId).
    *
    * @param id The unique identifier of the UserStoryJpa entity to be found.
    * @return An Optional containing the found UserStoryJpa entity, or empty if not found.
    */
   public Optional<UserStoryJpa> findById(String id);

   /**
    * Saves a UserStory entity to the repository.
    *
    * @param userStory The UserStory entity to be saved.
    * @return The saved UserStory entity.
    */
   public UserStory save(UserStory userStory);
}

