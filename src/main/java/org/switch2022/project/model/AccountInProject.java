package org.switch2022.project.model;


import java.time.LocalDate;
/**
 * Class AccountInProject is built to associate a certain account with a specific role to a project.
 * An accountInProject is defined by an account, a project and a role.
 */
public class AccountInProject {
    /**
     * Attributes of the class AccountInProject, according to the Class Diagram.
     */
    private Account account;
    private Project project;
    private String role;
    private float costPerHour;
    private float percentageAllocation;
    private LocalDate startDate;
    private String endDate;


    /**
     * Constructor of the class AccountInProject.
     * New instance is created using as parameter the essential attributes.
     *
     * @param account of the new account
     * @param project of the new account
     */
    public AccountInProject(Account account, Project project, float costPerHour,
                            float percentageAllocation, LocalDate startDate) {
        this.account = account;
        this.project = project;
        this.costPerHour = costPerHour;
        this.percentageAllocation = percentageAllocation;
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountInProject that = (AccountInProject) o;
        return account.equals(that.account) && project.equals(that.project) && role.equals(that.role) && startDate.isEqual(that.startDate);
    }

    public boolean setRole(String role) {
        switch (role) {
            case "team member":
                this.role = "Team Member";
                return true;
            case "product owner":
                this.role = "Product Owner";
                return true;
            case "project manager":
                this.role = "Project Manager";
                return true;
            case "scrum master":
                this.role = "Scrum Master";
                return true;
            default:
                return false;
        }
    }
}
