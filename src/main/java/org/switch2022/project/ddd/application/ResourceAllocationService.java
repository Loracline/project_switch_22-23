package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceFactory;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceRepository;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Period;
import org.switch2022.project.ddd.domain.value_object.ProjectStatus;

import java.util.Optional;

/**
 * Class ResourceAllocationService allows to create and manipulate the ProjectResource aggregate.
 */
@Service
public class ResourceAllocationService {

    @SuppressWarnings("all")
    @Autowired
    private IProjectResourceRepository resourceRepository;
    @SuppressWarnings("all")
    @Autowired
    private IProjectRepository projectRepository;
    @SuppressWarnings("all")
    @Autowired
    private IAccountRepository accountRepository;
    @SuppressWarnings("all")
    @Autowired
    private IProjectResourceFactory resourceFactory;

    /**
     * Checks if a project with the specified project code is in the specified project status.
     *
     * @param status The project status to check against.
     * @return {@code TRUE} if a project with the given project code exists and has the specified project status,
     * {@code FALSE} otherwise.
     */
    private boolean hasStatus(Code projectCode, ProjectStatus status) {
        boolean has = false;
        Optional<Project> projectOptional = projectRepository.findByCode(projectCode);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            if (project.hasStatus(status)) {
                has = true;
            }
        }
        return has;
    }

    /**
     * Checks if a project with the specified project code does not have the specified project status.
     *
     * @param projectCode The project code to check for status.
     * @param status      The project status to check against.
     * @return {@code TRUE} if a project with the given project code exists and does not have the specified project
     * status, {@code FALSE} otherwise.
     */
    private boolean doesNotHaveStatus(Code projectCode, ProjectStatus status) {
        return !hasStatus(projectCode, status);
    }

    /**
     * Checks if any project within this collection contains the specified allocation period.
     *
     * @param allocationPeriod The allocation period to check for containment within the projects.
     * @return {@code TRUE} if any project within this collection contains the specified allocation period,
     * {@code FALSE} otherwise.
     */
    private boolean containsPeriod(Code projectCode, Period allocationPeriod) {
        boolean contains = false;
        Optional<Project> projectOptional = projectRepository.findByCode(projectCode);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            if (project.contains(allocationPeriod)) {
                contains = true;
            }
        }
        return contains;
    }

    /**
     * Checks if a project with the specified project code is valid for allocation within the specified period.
     *
     * @param projectCode      The project code to check for validity.
     * @param allocationPeriod The allocation period to check against.
     * @return {@code TRUE} if the project with the given project code exists,
     * is not in the "PLANNED" or "CLOSED" status, and contains the specified allocation period;
     * {@code FALSE} otherwise.
     */
    public boolean isProjectValidForAllocation(Code projectCode, Period allocationPeriod) {
        return doesNotHaveStatus(projectCode, ProjectStatus.PLANNED) &&
                doesNotHaveStatus(projectCode, ProjectStatus.CLOSED) &&
                containsPeriod(projectCode, allocationPeriod);
    }
}
