package org.switch2022.project.ddd.dto;

import org.springframework.hateoas.RepresentationModel;

/**
 * DTO (Data Transfer Object) representing a project code as a string.
 * This class encapsulates the data related to a project code, using a string value.
 * It is used for transferring project code information between different layers of the application.
 * <p>
 * In the context of Spring HATEOAS, RepresentationModel is used as a base class for creating
 * resource representations. ProjectCodeStringDto extends the RepresentationModel class, so this
 * means that ProjectCodeStringDto is a resource representation that can contain hypermedia links.
 */
public class ProjectCodeStringDto extends RepresentationModel<ProjectCodeStringDto> {

    private final String code;

    /**
     * Constructs a new instance of the ProjectCodeStringDto class.
     *
     * @param code The project code as a string.
     */
    public ProjectCodeStringDto(String code) {
        this.code = code;
    }

    /**
     * Retrieves the project code as a string.
     *
     * @return The project code as a string.
     */
    public String getCode() {
        return code;
    }
}
