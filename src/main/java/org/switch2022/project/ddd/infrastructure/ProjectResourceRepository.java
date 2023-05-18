package org.switch2022.project.ddd.infrastructure;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceRepository;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Component
public class ProjectResourceRepository implements IProjectResourceRepository {

    /**
     * Attributes
     */
    private final List<ProjectResource> projectResources = new ArrayList<>();

    /**
     * Method equals(Object o)
     * This method checks if an instance of ProjectResourceRepository is equal to another object.
     *
     * @param o object to compare with.
     * @return true if the two have the same attribute value, and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectResourceRepository that = (ProjectResourceRepository) o;
        return Objects.equals(projectResources, that.projectResources);
    }

    /**
     * Method hashCode()
     * This method is used to generate a unique hash code for an object, based on the
     * object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(projectResources);
    }


    /**
     * This method returns an unmodifiable list (read-only) of Project Resources.
     *
     * @return an unmodifiable view of Project Resources.
     */
    public List<ProjectResource> findAll() {
        return Collections.unmodifiableList(projectResources);
    }

    /**
     * This method adds a new ProjectResource instance to the repository of project resources.
     *
     * @param projectResource to be added to the repository.
     * @return true if the project resource is added, and throws an exception otherwise.
     */
    public boolean add(ProjectResource projectResource) {
        if (hasResource(projectResource)) {
            throw new AlreadyExistsInRepoException("The project resource already exists in the repository.");
        } else {
            projectResources.add(projectResource);
            return true;
        }
    }

    /**
     * Retrieves a list of email accounts allocated to a specific project.
     *
     * @param projectCode The project code used to filter the allocated accounts.
     * @return A list of email accounts allocated to the specified project.
     */
    public List<String> getAccountsAllocatedToProject(Code projectCode) {
        List<String> emails = new ArrayList<>();
        for (int i = 0; i < projectResources.size(); i++) {
            if (projectResources.get(i).hasProjectCode(projectCode)) {
                String email = this.projectResources.get(i).getEmail();
                emails.add(email);
            }
        }
        return Collections.unmodifiableList(emails);
    }

    /**
     * This method checks if a given instance of ProjectResource already exists in the list of project resources.
     *
     * @param projectResource project resource to look for in the project resource list.
     * @return true if an instance of ProjectResource with the same projectCode, accountEmail, role, and
     * allocationPeriod as the projectResource of interest already exists in the list, and false otherwise.
     */
    private boolean hasResource(ProjectResource projectResource) {
        boolean hasResource = false;

        for (int i = 0; i < projectResources.size(); i++) {
            if (projectResources.get(i).hasSameAllocationInfo(projectResource)) {
                hasResource = true;
                i = projectResources.size();
            }
        }
        return hasResource;
    }

    /**
     * Calculates the current percentage allocation for a given account email and date, by iterating through all project
     * resources and summing up the percentage allocations for the resources that match the given email and date.
     *
     * @param accountEmail the email of the account to calculate the allocation for.
     * @param date         the date to calculate the allocation for.
     * @return the current percentage allocation for the given account email and date.
     */
    private float currentPercentageOfAllocation(Email accountEmail, LocalDate date) {
        float sum = 0.0F;

        for (int i = 0; i < this.projectResources.size(); i++) {
            if (this.projectResources.get(i).hasAccount(accountEmail)
                    && this.projectResources.get(i).allocationPeriodIncludesDate(date)) {

                sum += this.projectResources.get(i).getPercentageOfAllocation();

            }
        }

        return sum;
    }

    /**
     * Calculates the total percentage of allocation for a given account email and date by adding the value of a given
     * PercentageOfAllocation object to the current allocation percentage.
     *
     * @param accountEmail the email of the account to calculate the allocation for.
     * @param date         the date to calculate the allocation for.
     * @param toAdd        the PercentageOfAllocation object to add to the current allocation percentage.
     * @return the total percentage of allocation for the given account email and date after adding the value of the
     * given PercentageOfAllocation object
     */
    private float totalPercentageOfAllocation(Email accountEmail, LocalDate date, PercentageOfAllocation toAdd) {
        return currentPercentageOfAllocation(accountEmail, date) + toAdd.getValue();
    }

    /**
     * Checks if the total percentage of allocation for a given account email and date, after adding the value of a
     * given PercentageOfAllocation object, is less than or equal to the maximum allowed value.
     *
     * @param accountEmail the email of the account to check the allocation for.
     * @param date         the date to check the allocation for.
     * @param toAdd        the PercentageOfAllocation object to add to the current allocation percentage.
     * @return TRUE if the total percentage of allocation is less than or equal to the maximum allowed value, FALSE
     * otherwise.
     */
    public boolean validatePercentageOfAllocation(Email accountEmail, LocalDate date, PercentageOfAllocation toAdd) {
        final int MAXIMUM_ALLOWED = 100;

        return totalPercentageOfAllocation(accountEmail, date, toAdd) <= MAXIMUM_ALLOWED;
    }

    /**
     * This method checks if there are any projectresource in the repository that have the same project ID,
     * same account email, and overlapping period.
     * @param projectCode being checked.
     * @param email being checked.
     * @param period being checked.
     * @return return true if the projectResource is overllaping, false otherwise.
     */
    public boolean isResourceOverlapping(Code projectCode, Email email, Period period){
        boolean resourceIsOverlapping = false;
        for (int i = 0; i < this.projectResources.size(); i++) {
            if (this.projectResources.get(i).hasProjectCode(projectCode) &&
                    this.projectResources.get(i).hasAccount(email) &&
                    this.projectResources.get(i).isPeriodOverlapping(period)){
                resourceIsOverlapping = true;
            }
        } return resourceIsOverlapping;
    }

    /**
     * This method retrieves a list of string representations of project codes to which a given account is allocated
     * to.
     *
     * @param email the value object email that represents the desired account.
     * @return a list of string representations of the project codes, or an empty list if no resource with the given account email was found.
     */
    public List<Code> findProjectCodesByAccountEmail(Email email){
        List <Code> projectCodes = new ArrayList<>();

        for (int i = 0; i < projectResources.size(); i++) {
            if(projectResources.get(i).hasAccount(email)){
                int codeNumber = Utils.getIntFromAlphanumericString(projectResources.get(i).getCode(), "P");
                Code code = new Code(codeNumber);
                projectCodes.add(code);
            }
        }
        return Collections.unmodifiableList(projectCodes);
    }
}

