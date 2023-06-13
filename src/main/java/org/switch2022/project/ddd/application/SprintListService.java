package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.sprint.ISprintRepository;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.dto.ProjectCodeValueObjectDto;
import org.switch2022.project.ddd.dto.SprintValueObjectsDto;
import org.switch2022.project.ddd.dto.mapper.SprintDtoAssembler;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class that provides operations for listing sprints from a project.
 */
@Service
public class SprintListService {

    /**
     * The repository for accessing sprint data.
     */
    @SuppressWarnings("all")
    @Autowired
    @Qualifier("sprint_jpa")
    private ISprintRepository repository;

    /**
     * Retrieves a list of sprint value objects for the specified project.
     *
     * @param dto The project code value object DTO specifying the project.
     * @return A list of SprintValueObjectsDto representing the sprints of the project.
     */
    public List<SprintValueObjectsDto> listSprintsFromProject(ProjectCodeValueObjectDto dto) {
        List<Sprint> sprints = repository.findByProjectCode(dto.getCode());
        List<SprintValueObjectsDto> sprintsDto = new ArrayList<>();
        for (int i = 0; i < sprints.size(); i++) {
            SprintValueObjectsDto sprintDto = SprintDtoAssembler.sprintToValueObjectsDto(sprints.get(i));
            sprintsDto.add(sprintDto);
        }
        return sprintsDto;
    }
}
