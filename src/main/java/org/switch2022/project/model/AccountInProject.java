package org.switch2022.project.model;


import java.time.LocalDate;

/**
 * Class AccountInProject is built to associate a certain account with a specific role
 * to a specific project.
 * An accountInProject is defined by an account, a project, a role, a cost per hour, a
 * percentage of allocation, a start date and an end date.
 */
public class AccountInProject {
    private final static String PRODUCT_OWNER = "Product Owner";
    private final static String SCRUM_MASTER = "Scrum Master";
    private final static String TEAM_MEMBER = "Team Member";
    /**
     * Attributes of the class AccountInProject, according to the Class Diagram.
     */
    private Account account;
    private Project project;
    private String role;
    private float costPerHour;
    private float percentageAllocation;
    private LocalDate startDate;
    private LocalDate endDate;


    /**
     * Constructor of the class AccountInProject.
     * New instance is created using as parameter the essential attributes.
     *
     * @param account of the new account
     * @param project of the new account
     */
    public AccountInProject(Account account, Project project, String role,
                            float costPerHour,
                            float percentageAllocation, LocalDate startDate) {
        this.account = account;
        this.project = project;
        this.role = role;
        this.costPerHour = costPerHour;
        this.percentageAllocation = percentageAllocation;
        this.startDate = startDate;
    }

    /**
     * Method that checks if two instances of AccountInProject are equal by comparing
     * the account, project, role and startDate attributes.
     *
     * @param objectToCompare AccountInProject instance to compare with
     * @return TRUE if two instances of AccountInProject have the same account,
     * project, role and startDate attributes, and FALSE otherwise
     */
    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) {
            return true;
        }
        if (objectToCompare == null || getClass() != objectToCompare.getClass()) {
            return false;
        }
        AccountInProject that = (AccountInProject) objectToCompare;
        return account.equals(that.account) && project.equals(that.project) && role.equals(that.role) && startDate.equals(that.startDate);
    }

    /**
     * Method that verifies if the role is valid.
     *
     * @return TRUE if role is valid, and FALSE otherwise
     */
    public boolean isRoleValid() {
        switch (this.role) {
            case TEAM_MEMBER:
            case PRODUCT_OWNER:
            case SCRUM_MASTER:
                return true;
            default:
                return false;
        }
    }

    public Account getAccountByProject(Project project) {
        return account;
    }
}
