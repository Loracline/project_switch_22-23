package org.switch2022.project.ddd.dto.mapper;

import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.dto.ProjectCodeStringDto;
import org.switch2022.project.ddd.dto.ProjectCodeValueObjectDto;

/**
 * Assembler class for converting between project code DTOs.
 * This class provides a method for converting a ProjectCodeStringDto to a ProjectCodeValueObjectDto.
 */
public class ProjectCodeDtoAssembler {

    /**
     * Converts a ProjectCodeStringDto to a ProjectCodeValueObjectDto.
     *
     * @param dto The ProjectCodeStringDto to be converted.
     * @return The converted ProjectCodeValueObjectDto.
     */
    public static ProjectCodeValueObjectDto convertToValueObject(ProjectCodeStringDto dto) {
        return new ProjectCodeValueObjectDto(Code.getCodeFromString(dto.getCode()));
    }
}
