package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.customer.ICustomerRepository;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.dto.ProjectDto;
import org.switch2022.project.ddd.dto.mapper.ProjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectListService {

    /**
     * Attributes
     */
    @Autowired
    @Qualifier("project_jpa")
    private IProjectRepository projectRepository;
    @Autowired
    private ProjectMapper projectMapper;
    @Qualifier("customer_jpa")
    @Autowired
    private ICustomerRepository customerRepository;

    /**
     * Requests a list of all projects
     *
     * @return a list of all projectsDto.
     */
    public List<ProjectDto> requestAllProjects() {
        List<ProjectDto> projectsDto = new ArrayList<>();
        List<Project> projects = projectRepository.findAll();
        for (Project project : projects) {
            String customerName = customerRepository.findCustomerNameByTaxId(project.getCustomerTaxId());
            projectsDto.add(projectMapper.projectToDto(project, customerName));
        }
        return projectsDto;
    }
}
