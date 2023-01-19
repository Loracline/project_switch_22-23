package org.switch2022.project.model;


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

    /**
     * Constructor of the class AccountInProject.
     * New instance is created using as parameter the essential attributes.
     *
     * @param account of the new account
     * @param project of the new account
     * @param role    of the new account
     */
    public AccountInProject(Account account, Project project, String role) {
        this.account = account;
        this.project = project;
        this.role = role;
    }
}
