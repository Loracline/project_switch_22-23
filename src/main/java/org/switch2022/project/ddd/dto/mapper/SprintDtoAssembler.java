package org.switch2022.project.ddd.dto.mapper;

import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.value_object.SprintId;
import org.switch2022.project.ddd.domain.value_object.SprintNumber;
import org.switch2022.project.ddd.domain.value_object.SprintStatus;
import org.switch2022.project.ddd.dto.SprintPrimitiveTypesDto;
import org.switch2022.project.ddd.dto.SprintValueObjectsDto;

import java.time.LocalDate;

/**
 * Mapper class for converting between Sprint domain objects and DTOs.
 * This class provides methods for mapping between different representations of sprints.
 */
public class SprintDtoAssembler {

    /**
     * Converts a Sprint domain object to a SprintValueObjectsDto.
     *
     * @param sprint The Sprint domain object to be converted.
     * @return The converted SprintValueObjectsDto.
     */
    public static SprintValueObjectsDto sprintToValueObjectsDto(Sprint sprint) {
        SprintId id = new SprintId(sprint.getProjectCode(), sprint.getFullSprintNumber());
        SprintNumber number = new SprintNumber(sprint.getSprintNumber());
        SprintStatus status = SprintStatus.generateSprintStatus(sprint.getStatus());
        LocalDate startDate = LocalDate.parse(sprint.getStartDate());
        LocalDate endDate = LocalDate.parse(sprint.getEndDate());

        return new SprintValueObjectsDto(id, number, status, startDate, endDate);
    }

    /**
     * Converts a SprintValueObjectsDto to a SprintPrimitiveTypesDto.
     *
     * @param dto The SprintValueObjectsDto to be converted.
     * @return The converted SprintPrimitiveTypesDto.
     */
    public static SprintPrimitiveTypesDto convertToPrimitiveTypes(SprintValueObjectsDto dto) {
        return new SprintPrimitiveTypesDto(
                dto.getId(),
                dto.getNumber(),
                dto.getStatus(),
                dto.getStartDate(),
                dto.getEndDate());
    }
}
