package org.switch2022.project.ddd.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.ddd.datamodel_jpa.SprintJpa;

import java.util.List;
import java.util.Optional;

public interface ISprintJpaRepository extends CrudRepository<SprintJpa, String> {

    /**
     * Finds all SprintJpa entities with the specified identifiers (sprintId).
     *
     * @param projectCode An iterable containing the unique identifiers of the SprintJpa entities to be found.
     * @return An Iterable containing the found SprintJpa entities.
     */
    List<SprintJpa> findByProjectCode(String projectCode);


    /**
     * This method checks if at least one instance of Sprint with a given status already exists in the list of sprints.
     *
     * @param status SprintStatus to look for in the sprint list.
     * @return true if at least one instance of Sprint with a given status already exists in the list, and false otherwise.
     */
    boolean existsByStatus(String status);
}
