package org.switch2022.project.model;

import org.switch2022.project.DTO.ProjectDTO;
import org.switch2022.project.container.AccountContainer;
import org.switch2022.project.container.BusinessSectorContainer;
import org.switch2022.project.container.ProfileContainer;
import org.switch2022.project.container.ProjectContainer;
import org.switch2022.project.mapper.ProjectMapper;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Objects;

/**
 * Class Company is built to create and manipulate AccountContainer and ProfileContainer.
 */
public class Company {
    /**
     * Attributes of the class Company, according to the Class Diagram.
     */
    private AccountContainer accountContainer;
    private ProfileContainer profileContainer;
    private ProjectContainer projectContainer;
    private BusinessSectorContainer businessSectorContainer;

    /**
     * Company constructor
     */
    public Company(AccountContainer accountContainer, ProfileContainer profileContainer, BusinessSectorContainer businessSectorContainer) {
        this.accountContainer = accountContainer;
        this.profileContainer = profileContainer;
        this.projectContainer = projectContainer;
        this.businessSectorContainer = businessSectorContainer;
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
     * Getter method for the attribute Profile Container.
     *
     * @return the container of profiles
     */
    public ProjectContainer getProjectContainer() {
        return projectContainer;
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
     * @return false if businessSector isn't created successfully
     */
    public boolean addBusinessSector(String businessSector) {
        return businessSectorContainer.createBusinessSector(businessSector);
    }

    public boolean validateManager(String email) {
        return accountContainer.validateManager(email);
    }

    public List<Account> listAccountsByProject(String projectCode) {
        return projectContainer.listAccountsByProject(projectCode);
    }
}

