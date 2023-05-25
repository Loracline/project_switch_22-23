package org.switch2022.project.ddd.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.switch2022.project.ddd.datamodel_jpa.ProjectResourceJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.ResourceDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceRepository;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.infrastructure.jpa.IProjectResourceJpaRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository("jpa")
public class ProjectResourceJpaRepository implements IProjectResourceRepository {
    /**
     * Attributes
     */
    @Autowired
    ResourceDomainDataAssembler assembler;
    @Autowired
    IProjectResourceJpaRepository jpaRepository;

    /**
     * This method adds a new ProjectResource instance to the repository of project resources.
     *
     * @param projectResource to be added to the repository.
     * @return true if the project resource is added, and throws an exception otherwise.
     */
    @Override
    public boolean save(ProjectResource projectResource) {
        ProjectResourceJpa projectResourceJpa = assembler.toData(projectResource);
        if (jpaRepository.existsById(projectResourceJpa.getId())) {
            throw new AlreadyExistsInRepoException("The project resource already exists in the repository.");
        } else {
            jpaRepository.save(projectResourceJpa);
            return true;
        }
    }
    /**
     * This method returns an unmodifiable list (read-only) of Project Resources.
     *
     * @return an unmodifiable view of Project Resources.
     */
    @Override
    public List<ProjectResource> findAll() {
        Iterable<ProjectResourceJpa> list = jpaRepository.findAll();
        List<ProjectResource> projectResourceList = new ArrayList<>();
        list.forEach(jpa -> projectResourceList.add(assembler.toDomain(jpa)));
        return Collections.unmodifiableList(projectResourceList);
    }

    /**
     * Finds project resources associated with the specified email.
     *
     * @param email The email address used to search for project resources.
     * @return A List of ProjectResource objects that match the provided email.
     */
    @Override
    public List<ProjectResource> findResourcesByAccountEmail(Email email) {
        List <ProjectResourceJpa> list = jpaRepository.findAllByAccountEmail(email);
        List<ProjectResource> projectResourceList = new ArrayList<>();
        list.forEach(jpa -> projectResourceList.add(assembler.toDomain(jpa)));
        return Collections.unmodifiableList(projectResourceList);
    }

    /**
     * This method retrieves a list of project codes to which a given account is allocated to.
     *
     * @param email the value object email that represents the desired account.
     * @return a list of Codes representations of the project codes, or an empty list otherwise.
     */
    @Override
    public List<Code> findProjectCodesByAccountEmail(Email email) {
        List<ProjectResource> projectResourceList = findResourcesByAccountEmail(email);
        List<Code> projectCodes = new ArrayList<>();
        for (ProjectResource projectResource : projectResourceList) {
            String code = projectResource.getCode();
            Code codeToAdd = Code.getCodeFromString(code);
            if (!projectCodes.contains(codeToAdd)) {
                projectCodes.add(codeToAdd);
            }
        }
        return Collections.unmodifiableList(projectCodes);
    }

    /**
     * This method retrieves a list ProjectResources allocated to a given project
     *
     * @param code the value object code that represents the desired project.
     * @return a list of ProjectResources allocated to a given project, or an empty list otherwise.
     */

    private List<ProjectResource> findResourcesByProjectCode(Code code) {
        List <ProjectResourceJpa> list = jpaRepository.findAllByProjectCode(code);
        List<ProjectResource> projectResourceList = new ArrayList<>();
        list.forEach(jpa -> projectResourceList.add(assembler.toDomain(jpa)));
        return Collections.unmodifiableList(projectResourceList);
    }

    /**
     * Retrieves a list of Email objects associated with the specified project code.
     *
     * @param code The project code used to filter the account emails.
     * @return A List of Email objects representing the account emails.
     */
    @Override
    public List<Email> findAccountEmailsByProjectCode(Code code) {
        List<ProjectResource> projectResourceList = findResourcesByProjectCode(code);
        List<Email> accountEmails = new ArrayList<>();
        for (ProjectResource projectResource : projectResourceList) {
            Email emailToAdd = new Email(projectResource.getEmail());
            if (!accountEmails.contains(emailToAdd)) {
                accountEmails.add(emailToAdd);
            }
        }
        return Collections.unmodifiableList(accountEmails);
    }
}
