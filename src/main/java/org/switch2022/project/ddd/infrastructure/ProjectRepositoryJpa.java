package org.switch2022.project.ddd.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.switch2022.project.ddd.datamodel_jpa.ProjectJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.ProjectDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.infrastructure.JPA.IProjectJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("project_jpa")
public class ProjectRepositoryJpa implements IProjectRepository {

    @Autowired
    IProjectJpaRepository projectJpaRepository;
    @Autowired
    ProjectDomainDataAssembler assembler;

    /**
     * Finds a project by its code.
     *
     * @param code the code of the project to find
     * @return an Optional containing the Project with the specified code, or an empty Optional if no such
     * project exists
     */
    @Override
    public Optional<Project> findByCode(Code code) {
        Optional<ProjectJpa> projectJpa = projectJpaRepository.findByProjectCode(code.getCode());
        Optional<Project> projectOptional = Optional.empty();
        if (projectJpa.isPresent()) {
            Project project = assembler.toDomain(projectJpa.get());
            projectOptional = Optional.of(project);
        }
        return projectOptional;
    }

    /**
     * Returns the number of projects in the repository.
     *
     * @return the number of projects in the repository
     */

    @Override
    public int count() {
        return (int) projectJpaRepository.count();
    }

    /**
     * Saves a project to the repository, if it doesn't already exist.
     *
     * @param project the project to save
     * @return true if the project was saved, false if it already existed in the repository
     */
    @Override
    public boolean save(Project project) {
        boolean result = false;
        if (!projectJpaRepository.existsById(project.getProjectCode())) {
            projectJpaRepository.save(project);
            result = true;
        }
        return result;
    }

    /**
     * Returns a list of all projects in the repository.
     *
     * @return a list of all projects in the repository
     */

    @Override
    public List<Project> findAll() {
        Iterable<ProjectJpa> projectsJpa = projectJpaRepository.findAll();
        List<Project> projects = new ArrayList<>();
        projectsJpa.forEach(jpa -> projects.add(assembler.toDomain(jpa)));
        return projects;
    }

    /**
     * Returns a list of projects in the repository with the given project codes.
     *
     * @param projectCodes the list of project codes to search for
     * @return a list of projects with the given project codes
     */

    @Override
    public List<Project> findAllByProjectCodes(List<Code> projectCodes) {
        List<String> projectCode = new ArrayList<>();
        for (Code code : projectCodes) {
            String codeInString = code.getCode();
            projectCode.add(codeInString);
        }
        Iterable<ProjectJpa> projectsJpa = projectJpaRepository.findAllByProjectCodeIn(projectCode);
        List<Project> projects = new ArrayList<>();
        for (ProjectJpa projectJpa : projectsJpa) {
            Project project = assembler.toDomain(projectJpa);
            projects.add(project);
        }
        return projects;
    }
}
