package org.switch2022.project.ddd.infrastructure.JPA;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.ddd.datamodel.JPA.UserStoryJpa;
import org.switch2022.project.ddd.domain.value_object.UsId;


/**
 * Repository interface for managing UserStoryJpa entities.
 * Extends CrudRepository for basic CRUD operations.
 */
public interface IUserStoryJpaRepository extends CrudRepository<UserStoryJpa, UsId> {

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
    * Checks if a UserStoryJpa entity exists in the repository by its unique identifier (usId).
    *
    * @param usId The unique identifier of the UserStoryJpa entity to check for existence.
    * @return true if the entity exists, false otherwise.
    */

   public boolean existsByUsId(String usId);

   /**
    * Finds all UserStoryJpa entities in the repository that match the provided usId.
    *
    * @param usId The usId to match against UserStoryJpa entities in the repository.
    * @return An Iterable of UserStoryJpa entities with matching usId.
    */

   Iterable<UserStoryJpa> findAllByUsId(String usId);
}

