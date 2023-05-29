package org.switch2022.project.ddd.dto.mapper;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.dto.ProjectDto;

@Component
public class ProjectMapper {
    /**
     * This method converts a project into a Dto.
     *
     * @param project one must convert.
     * @return Dto carrying data.
     */

    public ProjectDto projectToDto(Project project, String customerName) {
        return new ProjectDto(project.getProjectCode(), project.getProjectName(), customerName,
                project.getProjectStatus(), project.getStartDate(), project.getEndDate());
    }
}
