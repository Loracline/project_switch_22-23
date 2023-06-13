package org.switch2022.project.ddd.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.switch2022.project.ddd.datamodel_jpa.SprintJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.SprintDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.SprintId;
import org.switch2022.project.ddd.domain.value_object.SprintStatus;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;
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
     * This method checks if at least one instance of Sprint with a given status already exists in the list of sprints.
     *
     * @param sprintStatus SprintStatus to look for in the sprint list.
     * @return true if at least one instance of Sprint with a given status already exists in the list, and false
     * otherwise.
     */
    public boolean existsByStatus(SprintStatus sprintStatus) {
        String status = sprintStatus.getStatus();
        if (iSprintJpaRepository.existsByStatus(status)) {
            return true;
        } else {
            throw new NotFoundInRepoException(String.format("There are no %s sprints in the repository.",
                    status));
        }
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
}
