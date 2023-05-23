package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountFactory;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceFactory;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceRepository;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.value_object.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.switch2022.project.ddd.domain.value_object.AccountStatus;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.domain.value_object.Period;
import org.switch2022.project.ddd.dto.AllocationDto;

import static org.switch2022.project.ddd.domain.value_object.Role.*;

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
    @SuppressWarnings("all")
    @Autowired
    private IAccountFactory accountFactory;

    /**
     * This method does all the necessary validations to create and add a given resource to the project resource list.
     *
     * @param allocationDto data transfer object carrying the allocation info: projectCode, accountEmail, accountRole,
     *                      accountCostPerHour, accountPercentageOfAllocation, startDate, endDate.
     * @return true if all validations are successful and the resource is created and added to the project resource
     * list, false otherwise.
     */
    public boolean addUserToProject( AllocationDto allocationDto) {

        Code code = new Code(allocationDto.projectCode);
        Email email = new Email(allocationDto.accountEmail);
        Role role = Role.valueOf(allocationDto.accountRole);
        CostPerHour costPerHour = new CostPerHour(allocationDto.accountCostPerHour);
        PercentageOfAllocation percentageOfAllocation = new PercentageOfAllocation(allocationDto.accountPercentageOfAllocation);
        Period allocationPeriod = new Period(allocationDto.startDate,allocationDto.endDate);
        int id = Math.addExact(resourceRepository.findAll().size(),1);
        ProjectResourceId projectResourceId = new ProjectResourceId(id);

        boolean addedUser = false;
        if (isResourceValid(role, code, allocationPeriod, email, percentageOfAllocation)) {
            ProjectResource projectResource = resourceFactory.createProjectResource(projectResourceId, code, email, role, allocationPeriod, costPerHour, percentageOfAllocation);
            addedUser = resourceRepository.save(projectResource);
        }
        return addedUser;
    }

    /**
     * This method checks if the resource is valid, To do this, it combines the validations of the project
     * (if it exists), the period (if it is compatible with the allocation period),
     * the account (if the account is valid), validates the role (since there can only be one PO and one SM per
     * project), validates that the resource is not overlapping, and validates the allocation percentage.
     *
     * @param role role set to resource, as each project cannot have more than one scrum master or product owner.
     * @param code project code where the resource will be allocated.
     * @param period period for which the resource is to be allocated.
     * @param email email of the account that will be allocated.
     * @param percentage of resource allocation.
     * @return true if all validations succeed, false otherwise.
     */

    protected boolean isResourceValid(Role role, Code code, Period period, Email email,
                                    PercentageOfAllocation percentage) {
        return isProjectValidForAllocation(code, period) &&
                isAccountValidForAllocation(email) &&
                isNotProjectManager(role) &&
                !projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(role, code, period)
                && !isResourceOverlapping(code, email, period) /*&&
                validatePercentageOfAllocation(email, LocalDate.now(), percentage)*/;
    }


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
    protected boolean isProjectValidForAllocation(Code projectCode, Period allocationPeriod) {
        return doesNotHaveStatus(projectCode, ProjectStatus.PLANNED) &&
                doesNotHaveStatus(projectCode, ProjectStatus.CLOSED) &&
                containsPeriod(projectCode, allocationPeriod);
    }

    /**
     * This method checks if a given role is Project Manager.
     *
     * @param role to check.
     * @return <code>true</code> if role is Project Manager and <code>false</code> otherwise.
     */
    private boolean isProjectManager(Role role) {
        return role.sameValueAs(PROJECT_MANAGER);
    }

    /**
     * This method checks if a given role is not Project Manager.
     *
     * @param role to check.
     * @return <code>true</code> if role is not Project Manager and <code>false</code> otherwise.
     */
    protected boolean isNotProjectManager(Role role) {
        return !isProjectManager(role);
    }

    /**
     * This method checks if a given role is Scrum Master.
     *
     * @param role to check.
     * @return <code>true</code> if role is Scrum Master and <code>false</code> otherwise.
     */

    private boolean isScrumMaster(Role role) {
        return role == SCRUM_MASTER;
    }

    /**
     * This method checks that a given role is neither Project Manager nor Scrum Master.
     *
     * @param role to check.
     * @return <code>true</code> if role is not Project Managern nor Scrum Master and <code>false</code> otherwise.
     */
    private boolean isNotScrumMasterNorProjectManager(Role role) {
        return !isScrumMaster(role) && !isProjectManager(role);
    }

    /**
     * This method checks if one specific Project already has the role of Scrum Master or Product Owner in a specific
     * period.
     *
     * @param role, code, period to check.
     * @return <code>true</code> if the project already has a Scrum Master or Product Owner in a specific Period and
     * <code>false</code> otherwise.
     */
    protected boolean projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(Role role, Code code, Period period) {
        return projectAlreadyHasScrumMasterInThatPeriod(role, code, period) ||
                projectAlreadyHasProductOwnerInThatPeriod(role, code, period);
    }

    /**
     * This method checks if one specific Project already has the role of Scrum Master in a specific
     * period.
     *
     * @param code, role, period to check.
     * @return <code>true</code> if the project already has a Scrum Master in a specific Period and
     * <code>false</code> otherwise.
     */
    private boolean projectAlreadyHasScrumMasterInThatPeriod(Role role, Code code, Period period) {
        return role.sameValueAs(Role.SCRUM_MASTER) && projectHasRoleInThatPeriod(role, code, period);
    }

    /**
     * This method checks if one specific Project already has the role of Product Owner in a specific period.
     *
     * @param code, role, period to check.
     * @return <code>true</code> if the project already has a Product Owner in a specific Period and
     * <code>false</code> otherwise.
     */
    private boolean projectAlreadyHasProductOwnerInThatPeriod(Role role, Code code, Period period) {
        return role.sameValueAs(PRODUCT_OWNER) && projectHasRoleInThatPeriod(role, code, period);
    }

    /**
     * This method checks if one specific Project already has a specific role in a specific period.
     *
     * @param role, code, period to check.
     * @return <code>true</code> if the project already has a Role in a specific Period and
     * <code>false</code> otherwise.
     */
    private boolean projectHasRoleInThatPeriod(Role role, Code code, Period period) {
        boolean result = false;
        int i = 0;
        while (i < resourceRepository.findAll().size() && !result) {
            ProjectResource resource = resourceRepository.findAll().get(i);
            if (resource.hasProjectCode(code) && resource.isPeriodOverlapping(period)
                    && resource.hasRole(role)) {
                result = true;
            }
            i++;
        }
        return result;
    }

    /**
     * Calculates the current percentage of allocation for a specific account's resources on a given date.
     *
     * @param accountEmail The email associated with the account for which allocation is being calculated.
     * @param date         The date for which the allocation percentage is calculated.
     * @return The sum of the percentage allocations for the resources associated with the account on the specified
     * date.
     */
    private float currentPercentageOfAllocation(Email accountEmail, LocalDate date) {
        float sum = 0.0F;
        List<ProjectResource> resources = resourceRepository.findResourcesByAccountEmail(accountEmail);
        for (int i = 0; i < resources.size(); i++) {
            if (resources.get(i).allocationPeriodIncludesDate(date)) {
                sum += resources.get(i).getPercentageOfAllocation();
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
    protected boolean validatePercentageOfAllocation(Email accountEmail, LocalDate date, PercentageOfAllocation toAdd) {
        final int MAXIMUM_ALLOWED = 100;

        return totalPercentageOfAllocation(accountEmail, date, toAdd) <= MAXIMUM_ALLOWED;
    }

    /**
     * This method checks if there are any project resource in the repository that have the same project ID,
     * same account email, and overlapping period.
     *
     * @param projectCode being checked.
     * @param email       being checked.
     * @param period      being checked.
     * @return return true if the projectResource is overllaping, false otherwise.
     */
    protected boolean isResourceOverlapping(Code projectCode, Email email, Period period) {
        boolean resourceIsOverlapping = false;
        List<ProjectResource> resources = resourceRepository.findAll();
        if (!resources.isEmpty()) {
            for (ProjectResource resource : resources) {
                if (resource.hasProjectCode(projectCode) &&
                        resource.hasAccount(email) &&
                        resource.isPeriodOverlapping(period)) {
                    resourceIsOverlapping = true;
                }
            }
        }
        return resourceIsOverlapping;
    }

    /**
     * This method checks if the account exists and if its status is active (if the account is valid).
     *
     * @param accountEmail to check if it exists and if it does, if the status is activated.
     * @return true if the account exists and the status is activated. If one of these conditions is not true,
     * it returns false.
     */
    protected boolean isAccountValidForAllocation(Email accountEmail) {
        boolean accountIsValid = false;
        List<Account> accounts = accountRepository.findAll();
        if (!accounts.isEmpty()) {
            for (Account account : accounts) {
                if (account.hasEmail(accountEmail.getEmail()) &&
                        account.isAccountActive()) {
                    accountIsValid = true;
                }
            }
        }
        return accountIsValid;
    }

}
