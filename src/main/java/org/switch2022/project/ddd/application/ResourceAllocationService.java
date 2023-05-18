package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceFactory;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceRepository;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Period;
import org.switch2022.project.ddd.domain.value_object.Role;

import static org.switch2022.project.ddd.domain.value_object.Role.PRODUCT_OWNER;
import static org.switch2022.project.ddd.domain.value_object.Role.PROJECT_MANAGER;

/**
 * Class ResourceAllocationService allows to create and manipulate the ProjectResource aggregate.
 */
@Service
public class ResourceAllocationService {

    @Autowired
    private IProjectResourceRepository resourceRepository;
    @Autowired
    private IProjectRepository projectRepository;
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IProjectResourceFactory resourceFactory;


    /**
     * This method checks if a given role is Project Manager.
     *
     * @param role to check.
     * @return <code>true</code> if role is Project Manager and <code>false</code> otherwise.
     */
    private boolean isProjectManager(Role role) {
        return role == PROJECT_MANAGER;
    }

    /**
     * This method checks if a given role is not Project Manager.
     *
     * @param role to check.
     * @return <code>true</code> if role is not Project Manager and <code>false</code> otherwise.
     */
    public boolean isNotProjectManager(Role role) {
        return !isProjectManager(role);
    }


    /**
     * This method checks if one specific Project already has the role of Scrum Master or Product Owner in a specific
     * period.
     *
     * @param role, code, period to check.
     * @return <code>true</code> if the project already has a Scrum Master or Product Owner in a specific Period and
     * <code>false</code> otherwise.
     */
    public boolean projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(Role role, Code code, Period period) {
        return projectAlreadyHasScrumMasterInThatPeriod(role, code, period) ||
                projectAlreadyHasProductOwnerInThatPeriod(role, code, period);
    }

    /**
     * This method checks if one specific Project already has the role of Scrum Master in a specific
     * period.
     *
     * @param code, role, period to check.
     * @return <code>true</code> if the project already has a Scrum Master in a specific Period and
     * <code>false</code> otherwise.
     */
    private boolean projectAlreadyHasScrumMasterInThatPeriod(Role role, Code code, Period period) {
        return role.sameValueAs(Role.SCRUM_MASTER) && projectHasRoleInThatPeriod(role, code, period);
    }

    /**
     * This method checks if one specific Project already has the role of Product Owner in a specific period.
     *
     * @param code, role, period to check.
     * @return <code>true</code> if the project already has a Product Owner in a specific Period and
     * <code>false</code> otherwise.
     */
    private boolean projectAlreadyHasProductOwnerInThatPeriod(Role role, Code code, Period period) {
        return role.sameValueAs(PRODUCT_OWNER) && projectHasRoleInThatPeriod(role, code, period);
    }

    /**
     * This method checks if one specific Project already has a specific role in a specific period.
     *
     * @param role, code, period to check.
     * @return <code>true</code> if the project already has a Role in a specific Period and
     * <code>false</code> otherwise.
     */
    private boolean projectHasRoleInThatPeriod(Role role, Code code, Period period) {
        boolean result = false;
        int i = 0;
        while (i < resourceRepository.findAll().size() && !result) {
            ProjectResource resource = resourceRepository.findAll().get(i);
            if (resource.hasProjectCode(code) && resource.isPeriodOverlapping(period)
                    && resource.hasRole(role)) {
                result = true;
            }
            i++;
        }
        return result;
    }
}
