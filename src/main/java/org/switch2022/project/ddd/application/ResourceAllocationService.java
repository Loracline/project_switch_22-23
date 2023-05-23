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
     * @param projectCode                   corresponds to the code of the project in which I intend to allocate the
     *                                      account.
     * @param accountEmail                  corresponds to the email of the account to be allocated.
     * @param accountRole                   corresponds to the role to be associated with the account in a given project.
     * @param accountCostPerHour            corresponds to the cost per hour of the resource that will be created.
     * @param accountPercentageOfAllocation corresponds to the percentage of application of the account to that role<br>
     *                                      in that project.
     * @param startDate                     allocation start date
     * @param endDate                       the end date of the allocation
     * @return true if all validations are successful and the resource is created and added to the project resource
     * list, false otherwise.
     */
    public boolean addUserToProject(int projectCode, String accountEmail, String accountRole, float accountCostPerHour,
                                    float accountPercentageOfAllocation, LocalDate startDate, LocalDate endDate) {
        boolean addedUser = false;

        Code code = new Code(projectCode);
        Email email = new Email(accountEmail);
        Role role = Role.valueOf(accountRole);
        CostPerHour costPerHour = new CostPerHour(accountCostPerHour);
        PercentageOfAllocation percentageOfAllocation = new PercentageOfAllocation(accountPercentageOfAllocation);
        Period allocationPeriod = new Period(startDate, endDate);
        AccountStatus accountStatus = AccountStatus.valueOf(accountRepository.findAccountByEmail(accountEmail).getAccountStatus());
        int id = resourceRepository.findAll().size() + 1;
        ProjectResourceId projectResourceId = new ProjectResourceId(id);

        if (isProjectValidForAllocation(code, allocationPeriod) && isAccountValidForAllocation(email, accountStatus) &&
                validatePercentageOfAllocation(email, startDate, percentageOfAllocation) && !isResourceOverlapping(code,
                email, allocationPeriod) && isAllocationInfoValid(role, code, allocationPeriod)) {

            ProjectResource projectResource = resourceFactory.createProjectResource(projectResourceId, code, email,
                    role, allocationPeriod, costPerHour, percentageOfAllocation);

            resourceRepository.save(projectResource);

            addedUser = true;
        }
        return addedUser;
    }

    /**
     * This method determines if the allocation of a certain account, with that role, in that project is valid or not.
     *
     * @param role             corresponding to the role you want to associate an account with in a project.
     * @param code             corresponds to the code of the project where the resource intends to be allocated.
     * @param allocationPeriod corresponds to the allocation period of the account to the project.
     * @return true if the allocation of that account in the project is valid, false otherwise.
     */

    private boolean isAllocationInfoValid(Role role, Code code, Period allocationPeriod) {
        boolean allocationIsValid;

        if (isNotScrumMasterNorProjectManager(role)) {
            allocationIsValid = true;

        } else if (role == PRODUCT_OWNER) {
            allocationIsValid = !projectAlreadyHasProductOwnerInThatPeriod(role, code, allocationPeriod);
        } else {
            allocationIsValid = !projectAlreadyHasScrumMasterInThatPeriod(role, code, allocationPeriod);
        }
        return allocationIsValid;
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
    public boolean isProjectValidForAllocation(Code projectCode, Period allocationPeriod) {
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
        return role == PROJECT_MANAGER;
    }

    /**
     * This method checks if a given role is not Project Manager.
     *
     * @param role to check.
     * @return <code>true</code> if role is not Project Manager and <code>false</code> otherwise.
     */
    public boolean isNotProjectManager(Role role) {
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
    public boolean projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(Role role, Code code, Period period) {
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
    public boolean validatePercentageOfAllocation(Email accountEmail, LocalDate date, PercentageOfAllocation toAdd) {
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
    public boolean isResourceOverlapping(Code projectCode, Email email, Period period) {
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
    public boolean isAccountValidForAllocation(Email accountEmail, AccountStatus accountStatus) {
        boolean accountIsValid = false;
        List<Account> accounts = accountRepository.findAll();
        if (!accounts.isEmpty()) {
            for (Account account : accounts) {
                if (account.hasEmail(accountEmail.getEmail()) &&
                        account.isAccountActive(accountStatus.getAccountStatus())) {
                    accountIsValid = true;
                }
            }
        }
        return accountIsValid;
    }

    /**
     * This method retrieves a list of resources to which a given account is allocate to during a period of time.
     *
     * @param email the value object email that represents the desired account.
     * @param period the value object that represents the period of time to search for a given resource.
     *
     * @return a list of resources to which a given account is allocate to during a period of time.
     */
    private List<ProjectResource> findResourcesByEmailWithPeriodOverlapping(Period period, Email email) {
        List<ProjectResource> repository = resourceRepository.findResourcesByAccountEmail(email);
        for (int i = 0; i < repository.size(); i++) {
            boolean isPeriodNotOverlapping = repository.get(i).isPeriodNotOverlapping(period);
            if (isPeriodNotOverlapping) {
                repository.remove(repository.get(i));
            }
        }
        return repository;
    }

    /**
     * Calculates the percentage of allocation for a specific account's resources on a given period.
     *
     * @param email The email associated with the account for which allocation is being calculated.
     * @param period The date for which the allocation percentage is calculated.
     * @return an array that represents the total of the percentage of allocation per day that the account is allocated
     * to.
     */
    private float[] percentageOfAllocationDuringAPeriod(Period period, Email email) {
        List<ProjectResource> repository = findResourcesByEmailWithPeriodOverlapping(period, email);
        int numberOfDays = period.numberOfDaysContainedInPeriod();
        float[] totalPercentageOfAllocation = new float[numberOfDays];
        LocalDate startDateToAdd = LocalDate.parse(period.getStartDate());
        for (int i = 0; i < repository.size(); i++) {
            for (int j = 0; j < numberOfDays; j++) {
                for (int k = 0; k < repository.get(i).numberOfDaysContainedInPeriod(); k++) {
                    LocalDate resourceStartDate = LocalDate.parse(repository.get(i).getPeriod().getStartDate());
                    if (startDateToAdd.plusDays(j).equals(resourceStartDate.plusDays(k))) {
                        totalPercentageOfAllocation[j] += repository.get(i).getPercentageOfAllocation();
                    }
                }
            }
        }
        return totalPercentageOfAllocation;
    }

    /**
     * Checks if the total percentage of allocation for a given account during a period of time is valid, after adding
     * the value of a given PercentageOfAllocation (less than or equal to 100).
     *
     * @param email the email of the account to check the allocation for.
     * @param period the period to check the allocation for.
     * @param percentageOfAllocationToAdd the PercentageOfAllocation object to add to the current allocation percentage.
     * @return TRUE if the total percentage of allocation is less than or equal to the maximum allowed value, FALSE
     * otherwise.
     */
    public boolean isPercentageOfAllocationValid(Period period, Email email,
                                                 PercentageOfAllocation percentageOfAllocationToAdd) {
        float[] totalPercentageOfAllocation = percentageOfAllocationDuringAPeriod(period, email);
        boolean result = true;
        int i = 0;
        while (i < totalPercentageOfAllocation.length && result) {
            totalPercentageOfAllocation[i] += percentageOfAllocationToAdd.getValue();
            if (totalPercentageOfAllocation[i] > 100) {
                result = false;
            }
            i++;
        }
        return result;
    }
}
