package org.switch2022.project.ddd.infrastructure.JPA;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.ddd.datamodel.JPA.SprintJpa;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;

import java.util.List;
import java.util.Optional;

public interface ISprintJpaRepository extends CrudRepository<SprintJpa, String> {
    /**
     * Finds a SprintJpa entity by its unique identifier (sprintId).
     *
     * @param id The unique identifier of the SprintJpa entity to be found.
     * @return An Optional containing the found SprintJpa entity, or empty if not found.
     */
    public Optional<SprintJpa> findById(String id);

    /**
     * This method returns the number of sprints contained in the list.
     *
     * @return int equivalent to the number of elements  in the list.
     */
    public long count();

    /**
     * Saves a Sprint entity to the repository.
     *
     * @param sprint The Sprint entity to be saved.
     * @return The saved Sprint entity.
     */
    public boolean save(Sprint sprint);

    /**
     * Finds all SprintJpa entities with the specified identifiers (sprintId).
     *
     * @param projectCode An iterable containing the unique identifiers of the SprintJpa entities to be found.
     * @return An Iterable containing the found SprintJpa entities.
     */
    List<SprintJpa> findByProjectCode(String projectCode);

    /**
     * Verifies that the Sprint id exists.
     *
     * @param id of the sprint.
     * @return true if exists.
     */
    boolean existsById (String id);
}