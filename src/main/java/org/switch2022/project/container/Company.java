package org.switch2022.project.container;


import org.switch2022.project.model.Account;
import org.switch2022.project.model.Project;
import org.switch2022.project.dto.AccountDTO;
import org.switch2022.project.dto.AllocationDto;
import org.switch2022.project.dto.ProjectDtoAsManager;

import java.awt.image.BufferedImage;
import java.util.List;

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


    // "GETTERS"

    /**
     * Getter method for the attribute: accountContainer.
     *
     * @return the container of accounts of this company.
     */
    public AccountContainer getAccountContainer() {
        return accountContainer;
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


    // ACCOUNT METHODS

    /**
     * This method registers a new account and adds it to the container.
     *
     * @param accountName of the account.
     * @param email       of the account.
     * @param phoneNumber of the account.
     * @param photo       of the account.
     * @return TRUE if account is registered and FALSE otherwise.
     */
    public boolean registerAccount(String accountName, String email,
                                   long phoneNumber, BufferedImage photo) {
        return accountContainer.addAccount(accountName, email, phoneNumber, photo);
    }

    /**
     * This method changes the status of an existing account in the container.
     *
     * @param email  of account.
     * @param status one intend to change to (TRUE - Active, FALSE - Inactive).
     * @return TRUE if changed, and FALSE otherwise.
     */
    public boolean changeStatus(String email, boolean status) {
        return accountContainer.changeStatus(email, status);
    }


    // PROJECT METHODS

    /**
     * This method registers a new project and adds it to the container.
     *
     * @param projectDto data transfer object of projects information.
     * @return TRUE if registered, and FALSE otherwise.
     */
    public boolean registerProject(ProjectDtoAsManager projectDto) {
        return (projectContainer.registerProject(projectDto));
    }

    /**
     * This method lists all projects in the container.
     *
     * @return a list of all projects.
     */
    public List<Project> listAllProjects() {
        return projectContainer.getProjects();
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
    public boolean addCustomer(String customerName, String customerNIF) {
        return customerContainer.addCustomer(customerName, customerNIF);
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


    // ACCOUNT IN PROJECT METHODS

    /**
     * This method adds an account to a project with a specific role.
     *
     * @param allocationDTO data transfer object gathering the information
     *                      needed.
     * @return TRUE if added, and FALSE otherwise.
     */
    public boolean addUserToProject(AccountDTO accountDTO,
                                    ProjectDtoAsManager projectDTOAsManager,
                                    AllocationDto allocationDTO) {

        boolean addUserToProject = false;

        Account account =
                this.accountContainer.getAccountByEmail(accountDTO.email);
        Project project =
                this.projectContainer.getProjectByCode(projectDTOAsManager.code);

        if (account != null && project != null) {
            if (account.isProfileRequired("user") && project.isProjectOpen()) {
                addUserToProject = this.accountInProjectContainer.addUserToProject(account, project,
                        allocationDTO);
            }
        }
        return addUserToProject;
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

}

