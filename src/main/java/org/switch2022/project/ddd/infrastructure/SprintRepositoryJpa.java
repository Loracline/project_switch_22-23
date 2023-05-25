package org.switch2022.project.ddd.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.switch2022.project.ddd.datamodel_jpa.SprintJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.SprintDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.SprintId;
import org.switch2022.project.ddd.infrastructure.jpa.ISprintJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
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
        boolean result = false;
        SprintJpa sprintJpa = sprintDomainDataAssembler.toData(sprint);
        if (!iSprintJpaRepository.existsById(sprintJpa.getSprintId())) {
            iSprintJpaRepository.save(sprintJpa);
            result = true;
        }
        return result;
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
        for ( SprintJpa sprintJpa : sprintJpas) {
            sprintsByProject.add(sprintDomainDataAssembler.toDomain(sprintJpa));
        }
        return sprintsByProject;
    }
}
