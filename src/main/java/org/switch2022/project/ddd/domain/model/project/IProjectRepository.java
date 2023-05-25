package org.switch2022.project.ddd.domain.model.project;

import org.switch2022.project.ddd.domain.value_object.Code;

import java.util.List;
import java.util.Optional;

/**
 * Interface for a ProjectRepository.
 */
public interface IProjectRepository {
    /**
     * Signatures of ProjectRepository class methods to ensure that the interface implements all methods.
     */
    Optional<Project> findByCode(Code code);

    int count();

    boolean save(Project project);

    List<Project> findAll();

    List<Project> findAllByProjectCodes(List<Code> projectCodes);
}
