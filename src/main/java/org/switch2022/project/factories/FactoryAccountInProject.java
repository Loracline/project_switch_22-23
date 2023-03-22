package org.switch2022.project.factories;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountInProject;
import org.switch2022.project.model.Project;

import java.time.LocalDate;

/**
 * Implementation of the FactoryAccountInProject interface that creates instances of the
 * AccountInProject class.
 */
public class FactoryAccountInProject implements IFactoryAccountInProject {
    /**
     * This method creates a new AccountInProject object with the specified attributes with no
     * return.
     *
     * @param account              the Account
     * @param project              the specific Project
     * @param role                 the role that an Account has on a specific Project
     * @param costPerHour          of the Account on a specific Project
     * @param percentageAllocation of the Account on a specific Project
     * @param startDate            of the AccountInProject
     * @param endDate              of the AccountInProject
     * @return a new AccountInProject object with the specified attributes.
     */

    public AccountInProject createAccountInProject(Account account, Project project, String role,
                                                   float costPerHour,
                                                   float percentageAllocation, LocalDate startDate,
                                                   LocalDate endDate) {
        return new AccountInProject(account, project, role, costPerHour, percentageAllocation,
                startDate,
                endDate);
    }
}
