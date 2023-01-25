package org.switch2022.project.utils.mapper;

import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.ProjectTypology;
import org.switch2022.project.utils.dto.ProjectDTO;
import org.switch2022.project.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectMapper {

  public static ProjectDTO getDTOFromProject(Project project) {
    ProjectDTO projectDTO = new ProjectDTO(project.getProjectCode(), project.getProjectName(),
            project.getCustomer().getCustomerName(),project.getProjectTypology().getProjectTypologyName(),
            project.getBusinessSector().getBusinessSectorName());
    return projectDTO;
  }

  public static Project getProjectFromDTO(ProjectDTO projectDTO) {
    Customer customer= new Customer(projectDTO.customer);
    ProjectTypology projectTypology= new ProjectTypology(projectDTO.projectTypology);
    BusinessSector businessSector= new BusinessSector(projectDTO.customer);
    Project project = new Project(projectDTO.code, projectDTO.name, customer,
            projectTypology, businessSector);
    return project;
  }
  public static List<ProjectDTO> projectsToDTO(List<Project> projects) {
    List<ProjectDTO> projectsDto = new ArrayList<>();
    for (int i = 0; i < projects.size(); i++) {
      ProjectDTO projectDTO = getDTOFromProject(projects.get(i));
      projectsDto.add(projectDTO);
    }
    return projectsDto;
  }
}

