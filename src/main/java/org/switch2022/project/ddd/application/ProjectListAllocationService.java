package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.customer.ICustomerRepository;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceRepository;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.dto.ProjectDto;
import org.switch2022.project.ddd.dto.mapper.ProjectMapper;
import org.switch2022.project.ddd.utils.Validate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectListAllocationService {
    /**
     * Attributes
     */
    @Autowired
    @Qualifier("resource_jpa")
    private IProjectResourceRepository iProjectResourceRepository;
    @Autowired
    @Qualifier("project_jpa")
    private IProjectRepository iProjectRepository;
    @Qualifier("customer_jpa")
    @Autowired
    private ICustomerRepository iCustomerRepository;
    @Autowired
    private ProjectMapper projectMapper;

    /**
     * Retrieves a list of project DTOs by account email.
     *
     * @param email String representation of the account email.
     * @return A list of ProjectDto objects representing the projects to which the account is currently
     * allocated to.
     */
    public List<ProjectDto> listProjectsByAccount(String email) {
        Validate.notNullOrEmptyOrBlank(email, "email");
        Email accountEmail = new Email(email);
        List<Code> projectCodes = iProjectResourceRepository.findProjectCodesByAccountEmail(accountEmail);

        List<Project> projects = iProjectRepository.findAllByProjectCodes(projectCodes);

        List<ProjectDto> projectDtos = new ArrayList<>();

        for (int i = 0; i < projects.size(); i++) {
            if(projects.get(i).containsCurrentDate()){
                String customerName = iCustomerRepository.findCustomerNameByTaxId(projects.get(i).getCustomerTaxId());
                projectDtos.add(projectMapper.projectToDto(projects.get(i), customerName));
            }
        }
        return projectDtos;
    }
}
