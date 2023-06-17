package org.switch2022.project.ddd.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.switch2022.project.ddd.datamodel_jpa.SprintJpa;
import org.switch2022.project.ddd.datamodel_jpa.UserStoryInSprintJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.SprintDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.SprintId;
import org.switch2022.project.ddd.domain.value_object.SprintStatus;
import org.switch2022.project.ddd.domain.value_object.UsId;
import org.switch2022.project.ddd.infrastructure.jpa.ISprintJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("sprint_jpa")
public class SprintRepositoryJpa implements ISprintRepository {

    @Autowired
    ISprintJpaRepository iSprintJpaRepository;
    @Autowired
    SprintDomainDataAssembler sprintDomainDataAssembler;

    /**
     * Retrieves a Sprint object by its SprintId.
     *
     * @param sprintId The identifier of the Sprint.
     * @return An Optional object containing the found Sprint, or an empty Optional if no Sprint is found.
     */
    @Override
    public Optional<Sprint> findById(SprintId sprintId) {
        Sprint sprint = null;
        Optional<SprintJpa> sprintJpaOptional =
                iSprintJpaRepository.findById(sprintId.getSprintId());
        if (sprintJpaOptional.isPresent()) {
            sprint = sprintDomainDataAssembler.toDomain(sprintJpaOptional.get());
        }
        return Optional.ofNullable(sprint);
    }

    /**
     * Counts the number of Sprint objects.
     *
     * @return The total count of Sprint objects.
     */
    @Override
    public long count() {
        return iSprintJpaRepository.count();
    }

    /**
     * Saves a Sprint object.
     *
     * @param sprint The Sprint object to be saved.
     * @return True if the save operation is successful, false otherwise.
     */
    @Override
    public boolean save(Sprint sprint) {
        SprintJpa sprintJpa = sprintDomainDataAssembler.toData(sprint);
        iSprintJpaRepository.save(sprintJpa);
        return true;
    }

    /**
     * Retrieves a list of Sprint objects associated with a specific project code.
     *
     * @param projectCode The code of the project.
     * @return A list of Sprint objects associated with the project code.
     */
    @Override
    public List<Sprint> findByProjectCode(Code projectCode) {
        List<Sprint> sprintsByProject = new ArrayList<>();
        List<SprintJpa> sprintJpas = iSprintJpaRepository.findByProjectCode(projectCode.getCode());
        for (SprintJpa sprintJpa : sprintJpas) {
            sprintsByProject.add(sprintDomainDataAssembler.toDomain(sprintJpa));
        }
        return sprintsByProject;
    }

    /**
     * Checks if a Sprint with the given SprintId exists in the repository.
     *
     * @param sprintId The SprintId to check.
     * @return {@code true} if a Sprint with the given SprintId exists, {@code false} otherwise.
     */
    @Override
    public boolean existsById(SprintId sprintId) {
        return iSprintJpaRepository.existsById(sprintId.getSprintId());
    }

    /**
     * This method checks if one given sprint has the status given
     *
     * @param sprintId the identifier of the sprint
     * @param status   the sprint status that needs to be checked
     * @return true if the sprint has the given status and false otherwise
     */
    public boolean hasStatus(SprintId sprintId, SprintStatus status) {
        String sprintStatus = status.getStatus();
        String id = sprintId.getSprintId();
        return iSprintJpaRepository.existsBySprintIdAndStatus(id, sprintStatus);
    }

    /**
     * This method checks if the userStory is in the sprint
     *
     * @param usId     the id of the userStory
     * @param sprintId the id of the sprint
     * @return true if the userStory is present
     */

    @Override
    public boolean hasUsId(SprintId sprintId, UsId usId) {
        boolean result = false;
        String userStoryId = usId.getUserStoryId();
        String id = sprintId.getSprintId();
        Optional<SprintJpa> sprintOptional = iSprintJpaRepository.findById(id);

        if (sprintOptional.isPresent()) {
            List<UserStoryInSprintJpa> userStories = sprintOptional.get().getUserStoriesInSprint();
            result = userStories.stream()
                    .anyMatch(us -> us.getUsId().equals(userStoryId));
        }

        return result;
    }

    /**
     * Retrieves a Sprint object based on the provided project code and status.
     *
     * @param projectCode The project code to search for.
     * @param status      The status of the Sprint to search for.
     * @return An Optional containing the retrieved Sprint object if found, or an empty Optional otherwise.
     */
    @Override
    public Optional<Sprint> findByProjectCodeAndStatus(Code projectCode, SprintStatus status) {
        String sprintStatus = status.getStatus();
        String code = projectCode.getCode();
        Sprint sprint = null;
        Optional<SprintJpa> sprintJpaOptional =
                iSprintJpaRepository.findByProjectCodeAndStatus(code, sprintStatus);
        if (sprintJpaOptional.isPresent()) {
            sprint = sprintDomainDataAssembler.toDomain(sprintJpaOptional.get());
        }
        return Optional.ofNullable(sprint);
    }

    /**
     * This method checks if at least one instance of Sprint with a given status already exists in the list of
     * sprints of a given project.
     *
     * @para projectCode the code of the project to which the sprint belong.
     * @param sprintStatus SprintStatus to look for in the sprint lisst.
     * @return true if at least one instance of Sprint with a given status already exists in the list, and false
     * otherwise.
     */
    public boolean existsByProjectCodeAndStatus(Code projectCode, SprintStatus sprintStatus) {
        String status = sprintStatus.getStatus();
        String code = projectCode.getCode();
        return iSprintJpaRepository.existsByProjectCodeAndStatus(code, status);
    }
}
