package org.switch2022.project.model;

import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.AccountInProjectDTO;
import org.switch2022.project.utils.dto.ProjectDTO;

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
    public Company(AccountContainer accountContainer, ProfileContainer profileContainer, BusinessSectorContainer
            businessSectorContainer, ProjectContainer projectContainer, ProjectTypologyContainer projectTypologyContainer,
                   AccountInProjectContainer accountInProjectContainer, CustomerContainer customerContainer) {
        this.accountContainer = accountContainer;
        this.profileContainer = profileContainer;
        this.projectContainer = projectContainer;
        this.businessSectorContainer = businessSectorContainer;
        this.projectTypologyContainer = projectTypologyContainer;
        this.customerContainer = customerContainer;
        this.accountInProjectContainer = accountInProjectContainer;
    }

    /**
     * Getter method for the attribute: accountContainer.
     *
     * @return the container of accounts of this company.
     */
    public AccountContainer getAccountContainer() {
        return accountContainer;
    }

    /**
     * Getter method for the attribute: customerContainer.
     *
     * @return the container of costumers of this company.
     */
    public CustomerContainer getCustomerContainer() {
        return customerContainer;
    }

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
     * This method registers a new account and adds it to the container.
     *
     * @param accountName of the account.
     * @param email       of the account.
     * @param phoneNumber of the account.
     * @param photo       of the account.
     * @return TRUE if account is registered and FALSE otherwise.
     */
    public boolean registerAccount(String accountName, String email, long phoneNumber,
                                   BufferedImage photo) {
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

    /**
     * This method changes the profile of existing account in the container.
     *
     * @param email       of account.
     * @param profileName of the profile one intend to change to.
     * @return TRUE if changed, and FALSE otherwise.
     */
    public boolean changeProfile(String email, String profileName) {
        boolean wasAccountProfileUpdated = false;
        Profile profile = profileContainer.getProfileByName(profileName);
        Account account = accountContainer.getAccountByEmail(email);
        if (account != null && profile != null) {
            account.setProfile(profile);
            wasAccountProfileUpdated = true;
        }
        return wasAccountProfileUpdated;
    }

    /**
     * This method registers a new project and adds it to the container.
     *
     * @param projectDTO data transfer object of projects information.
     * @param email      of the actor performing the task.
     * @return TRUE if registered, and FALSE otherwise.
     */
    public boolean registerProject(ProjectDTO projectDTO, String email) {
        boolean isProjectRegistered = false;
        if (accountContainer.validateManager(email)) {
            if (projectContainer.registerProject(projectDTO)) {
                isProjectRegistered = true;
            }
        }
        return isProjectRegistered;
    }

    /**
     * This method adds a new business sector to the container.
     *
     * @param businessSectorName of the business sector one intend to add.
     * @return TRUE if added and FALSE otherwise.
     */
    public boolean addBusinessSector(String businessSectorName) {
        return businessSectorContainer.createBusinessSector(businessSectorName);
    }

    /**
     * This method adds a new customer to the container.
     *
     * @param customerName of the customer one intends to add.
     * @return TRUE if added and FALSE otherwise.
     */
    public boolean addCustomer(String customerName) {
        return customerContainer.addCustomer(customerName);
    }








    public boolean validateManager(String email) {
        return accountContainer.validateManager(email);
    }

    /**
     * This method returns a list of Accounts Allocated To a Project
     *
     * @return a list of Accounts
     */
    public List<Account> listAccountsByProject(String projectCode) {
        return accountInProjectContainer.listAccountsByProject(projectCode);
    }

    public boolean validateUser(String email) {
        return accountContainer.validateUser(email);
    }

    public boolean validateAdministrator(String email) {
        return accountContainer.validateAdministrator(email);
    }

    public boolean createProjectTypology(String email, String projectTypology) {
        boolean projectTypologyCreated = false;
        if (accountContainer.validateAdministrator(email)) {
            projectTypologyCreated = projectTypologyContainer.createProjectTypology(projectTypology);
        }
        return projectTypologyCreated;
    }

    /**
     * This method returns list of projects
     *
     * @return list of projects
     */
    public List<Project> getListAllProjects() {
        return projectContainer.getProjects();
    }

    /**
     * This method associates a user account to a project with a role
     *
     * @return true if user account is successfully associated to a project
     */
    public boolean addUserToProject(AccountInProjectDTO accountInProjectDTO) {
        return this.accountInProjectContainer.addUserToProject(accountInProjectDTO);
    }


}

