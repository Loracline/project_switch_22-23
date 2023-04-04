package org.switch2022.project.ddd.domain.model.project;

import org.switch2022.project.ddd.domain.value_object.Code;

import java.util.Optional;

/**
 * Interface for a ProjectRepository.
 */
public interface IProjectRepository {
    /**
     * Signatures of ProjectRepository class methods to ensure that the interface implements all methods.
     */
    Optional<Project> getProjectByCode(Code code);

    int getProjectNumber();

    boolean addProjectToProjectRepository(Project project);
}
