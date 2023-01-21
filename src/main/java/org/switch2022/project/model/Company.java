package org.switch2022.project.model;

import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.AllocationDTO;
import org.switch2022.project.utils.dto.ProjectDTO;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Class Company is built to create and manipulate the containers.
 * Containers: Accounts, Profiles, Projects, Business Sectors, Project Typologies,
 * Customers and Accounts in Projects.
 *
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
     * Company constructor
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
     * Getter method for the attribute accounts.
     *
     * @return the list of Accounts in the Company
     */
    public AccountContainer getAccountContainer() {
        return accountContainer;
    }

    /**
     * Method createProfile
     *
     * @return true if profile is created
     */
    public boolean createProfile(String name) {
        return profileContainer.createProfile(name);
    }

    public boolean registerAccount(String name, String email, long phoneNumber, BufferedImage photo) {
        return accountContainer.addAccount(name, email, phoneNumber, photo);
    }

    public boolean changeStatus(String email, boolean status) {
        return accountContainer.changeStatus(email, status);
    }

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
     * This method registers projects
     *
     * @param projectDTO
     * @param email
     * @return true if project was registered and false if the project was not registered
     */
    public boolean registerProject(ProjectDTO projectDTO, String email) {
        boolean projectRegistered = false;
        if (accountContainer.validateManager(email)) {
            if (projectContainer.registerProject(projectDTO)) {
                projectRegistered = true;
            } else {
                projectRegistered = false;
            }
        }
        return projectRegistered;
    }

    /**
     * Method addBusinessSector
     *
     * @return true if businessSector is created
     */
    public boolean addBusinessSector(String businessSector) {
        return businessSectorContainer.createBusinessSector(businessSector);
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

    public boolean addTeamMemberToProject(AllocationDTO allocationDTO) {
        return this.accountInProjectContainer.addTeamMemberToProject(allocationDTO);
    }



    /**
     * Getter method for the attribute Customer Container.
     *
     * @return the container of customers
     */

    public CustomerContainer getCustomerContainer() {
        return customerContainer;
    }

    /**
     * Method addCustomer
     *
     * @return true if customer is added
     */
    public boolean addCustomer (String customerName){
        return customerContainer.addCustomer(customerName);
    }


}

