package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountFactory;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceFactory;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceRepository;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.value_object.AccountStatus;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.domain.value_object.Period;

import java.util.List;

/**
 * Class ResourceAllocationService allows to create and manipulate the ProjectResource aggregate.
 */
@Service
public class ResourceAllocationService {
    /**
     * Attributes
     */
    @Autowired
    private IProjectResourceRepository resourceRepository;
    @Autowired
    private IProjectRepository projectRepository;
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IProjectResourceFactory resourceFactory;
    @Autowired
    private IAccountFactory accountFactory;


    /**
     * This method checks if there are any projectresource in the repository that have the same project ID,
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
            for (int i = 0; i < resources.size(); i++) {
                if (resources.get(i).hasProjectCode(projectCode) &&
                        resources.get(i).hasAccount(email) &&
                        resources.get(i).isPeriodOverlapping(period)) {
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

    public boolean isAValidAccount(Email accountEmail, AccountStatus accountStatus) {
        boolean accountIsValid = false;
        List<Account> accounts = accountRepository.findAll();
        if (!accounts.isEmpty()) {
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).hasEmail(accountEmail.getEmail()) &&
                        accounts.get(i).isAccountActive(accountStatus.getAccountStatus())) {
                    accountIsValid = true;
                }
                ;
            }
        }
        return accountIsValid;
    }
}
