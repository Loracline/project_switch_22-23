package org.switch2022.project.container;


import org.switch2022.project.dto.*;
import org.switch2022.project.factories.*;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.ProductBacklog;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.SprintBacklog;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Class Company is built to create and manipulate the containers.
 * Containers: Accounts, Profiles, Projects, Business Sectors, Project Typologies,
 * Customers and Accounts in Projects.
 */
public class Company {
    /**
     * Attributes
     */
    private final AccountContainer accountContainer;
    private final ProfileContainer profileContainer;
    private final ProjectContainer projectContainer;
    private final BusinessSectorContainer businessSectorContainer;
    private final ProjectTypologyContainer projectTypologyContainer;
    private final AccountInProjectContainer accountInProjectContainer;
    private final CustomerContainer customerContainer;

    /**
     * Constructor
     */
    public Company(AccountContainer accountContainer,
                   ProfileContainer profileContainer,
                   BusinessSectorContainer businessSectorContainer,
                   ProjectContainer projectContainer,
                   ProjectTypologyContainer projectTypologyContainer,
                   AccountInProjectContainer accountInProjectContainer,
                   CustomerContainer customerContainer) {
        this.accountContainer = accountContainer;
        this.profileContainer = profileContainer;
        this.projectContainer = projectContainer;
        this.businessSectorContainer = businessSectorContainer;
        this.projectTypologyContainer = projectTypologyContainer;
        this.customerContainer = customerContainer;
        this.accountInProjectContainer = accountInProjectContainer;
    }

    // ACTOR VALIDATION METHODS

    /**
     * This method validates if the actor is an account with profile the required profile.
     *
     * @param email of the actor's account and profileName required.
     * @return TRUE if it has the profile required and FALSE otherwise.
     */

    public boolean validateProfileRequired(String email, String profileNameRequired) {
        return accountContainer.validateProfileRequired(email, profileNameRequired);
    }

    // PROFILE METHODS

    /**
     * This method creates a new profile and adds it to the container.
     *
     * @param profileName of the intended profile.
     * @return TRUE if profile is created and FALSE otherwise.
     */
    public boolean createProfile(String profileName) {
        return profileContainer.createProfile(profileName);
    }

    /**
     * This method changes the profile of existing account in the container.
     *
     * @param email       of account.
     * @param profileName of the profile one intend to change to.
     * @return TRUE if changed, and FALSE otherwise.
     */
    public boolean changeProfile(String email, String profileName) {
        boolean wasAccountProfileUpdated = false;
        Account account = accountContainer.getAccountByEmail(email);
        if (account != null && profileContainer.getProfileByName(profileName) != null) {
            account.setProfile(profileContainer, profileName);
            wasAccountProfileUpdated = true;
        }
        return wasAccountProfileUpdated;
    }

    /**
     * This method lists the accounts in the container that have the profile
     * "User".
     *
     * @return a list of accounts with the profile "User".
     */
    public List<Account> listAllUsers() {
        return accountContainer.listAllUsers();
    }

    // PROJECT METHODS

    /**
     * This method registers a new project and adds it to the container.
     *
     * @param projectDto data transfer object of projects information.
     * @return TRUE if registered, and FALSE otherwise.
     */
    public boolean registerProject(ProjectCreationDto projectDto,
                                   IFactoryProductBacklog factoryProductBacklog,
                                   IFactoryUserStory factoryUserStory,
                                   IFactoryProject factoryProject,
                                   IFactoryPeriod iFactoryPeriod,
                                   IFactorySprintBacklog iFactorySprintBacklog,
                                   IFactorySprint iFactorySprint) {
        return (projectContainer.registerProject(projectDto, projectTypologyContainer,
                customerContainer,
                businessSectorContainer, factoryProductBacklog, factoryUserStory,
                factoryProject,
                iFactoryPeriod,
                iFactorySprintBacklog, iFactorySprint));
    }

    // BUSINESS SECTOR METHODS

    /**
     * This method adds a new business sector to the container.
     *
     * @param businessSectorName of the business sector one intend to add.
     * @return TRUE if added and FALSE otherwise.
     */
    public boolean addBusinessSector(String businessSectorName) {
        return businessSectorContainer.createBusinessSector(businessSectorName);
    }


    // CUSTOMER METHODS

    /**
     * This method adds a new customer to the container.
     *
     * @return TRUE if added and FALSE otherwise.
     */
    public boolean addCustomer(String customerName, String customerNIF,
                               IFactoryCustomer iFactoryCustomer) {
        return customerContainer.addCustomer(customerName, customerNIF, iFactoryCustomer);
    }
    // PROJECT TYPOLOGY METHODS

    /**
     * This method adds a new typology of project to the container.
     *
     * @param projectTypology of project one intend to create.
     * @return TRUE if added, and FALSE otherwise.
     */
    public boolean createProjectTypology(String projectTypology) {
        return projectTypologyContainer.createProjectTypology(projectTypology);
    }

    /**
     * This method creates a Sprint in the requested project
     * returns true if the Sprint is successfully created
     */
    public boolean createSprint(ProjectDto projectDto,
                                SprintCreationDto sprintCreationDto) {
        return projectContainer.createSprint(sprintCreationDto, projectDto);
    }

    // ACCOUNT IN PROJECT METHODS

    /**
     * This method adds an account to a project with a specific role.
     *
     * @param allocationDto data transfer object gathering the information
     *                      needed.
     * @return TRUE if added, and FALSE otherwise.
     */
    public boolean addUserToProject(AccountDto accountDto,
                                    ProjectCreationDto projectCreationDto,
                                    AllocationDto allocationDto) {

        boolean addUserToProject = false;

        Account account =
                this.accountContainer.getAccountByEmail(accountDto.email);
        Project project =
                getProjectToAllocation(projectCreationDto.code);

        if (areAccountAndProjectValid(account, project) && account.isProfileRequired(
                "user") && project.isProjectOpen()) {
            addUserToProject = this.accountInProjectContainer.addUserToProject(
                    account, project,
                    allocationDto);
        }
        return addUserToProject;
    }

    /**
     * This method checks if an instance of account and an instance of project are
     * valid (i.e. not null).
     */
    public boolean areAccountAndProjectValid(Account account, Project project) {
        return account != null && project != null;
    }

    /**
     * This method retrieves an instance of project to be used to allocate an account
     *
     * @param projectCode of the project one searches for.
     * @return the desired project instance.
     */

    private Project getProjectToAllocation(String projectCode) {
        Project project = null;
        int i = 0;
        while (i < projectContainer.getProjects().size()) {
            if (projectContainer.getProjects().get(i).hasProjectCode(projectCode)) {
                project = projectContainer.getProjects().get(i);
            }
            i++;
        }
        return project;
    }

    /**
     * This method returns the scrum board (composed of the sprint backlog) of the
     * project of
     * interest, in a given date.
     *
     * @param projectCode       of the project of interest.
     * @param date              of interest.
     * @param iFactoryUserStory interface one must use to copy the User Stories
     *                          contained in the Sprint Backlog.
     * @return an Optional object of the Sprint Backlog.
     */
    public Optional<SprintBacklog> getScrumBoard(String projectCode, LocalDate date,
                                                 IFactoryUserStory iFactoryUserStory) {
        return projectContainer.getScrumBoard(projectCode, date, iFactoryUserStory);
    }

    /**
     * This method lists the accounts working in a specific project.
     *
     * @param projectCode of the project one searches for.
     * @return list of accounts involved in this project.
     */
    public List<Account> listAccountsByProject(String projectCode) {
        return accountInProjectContainer.listAccountsByProject(projectCode);
    }

    /**
     * This method lists the projects of a specific account.
     *
     * @param emailUser of an account searches for.
     * @return list of accounts involved in this project.
     */
    public List<Project> listProjectsByAccount(String emailUser) {
        return accountInProjectContainer.listProjectsByAccount(emailUser);
    }

    /**
     * This method creates an userStory in the requested project
     * returns true if the userStory is successfully created
     */
    public boolean createUserStory(ProjectDto projectDto,
                                   UserStoryCreationDto userStoryCreationDto) {
        return projectContainer.createUserStory(projectDto, userStoryCreationDto);
    }

    /**
     * This method sets the effort of an userStory.
     *
     * @param userStoryDto to estimate the effort.
     * @param effort       of the userStory.
     * @return true if the effort is set and false otherwise.
     */
    public boolean estimateEffortUserStory(UserStoryDto userStoryDto, int effort,
                                           ProjectDto projectDto, LocalDate date) {
        String projectCode = projectDto.code;
        return projectContainer.estimateEffortUserStory(userStoryDto, effort,
                projectCode, date);
    }

    /**
     * This method returns the product backlog of the project of interest.
     *
     * @param projectCode of interest.
     * @return an Optional object of the Product Backlog.
     */
    public Optional<ProductBacklog> getProductBacklog(String projectCode) {
        return projectContainer.getProductBacklog(projectCode);
    }

    /**
     * This method adds a User Story to Sprint Backlog.
     *
     * @param projectCode     of the project one searches for.
     * @param userStoryNumber of the user story to be added.
     * @param sprintNumber    of the Sprint that contains the sprint backlog.
     * @return TRUE if the User Story was added to Sprint Backlog and FALSE otherwise.
     */
    public boolean addUserStoryToSprintBacklog(String projectCode, String userStoryNumber,
                                               String sprintNumber) {
        return this.projectContainer.addUserStoryToSprintBacklog(projectCode,
                userStoryNumber, sprintNumber);
    }
}

