package org.switch2022.project.ddd.dto;

/**
 * This class represents the data transfer object (DTO) used for creating a new project.
 * Before the creation of the Project, the attributes businessSectorName, customerName and typologyName must be mapped
 * to their correspondent IDs.
 */
public class ProjectCreationDto {

    /**
     * Attributes.
     */
    public final String projectName;
    public final String projectDescription;
    public final String businessSectorId;
    public final String customerId;
    public final String typologyId;

    /**
     * Constructor.
     * <br>
     *
     * @param projectName        the name of the project.
     * @param projectDescription a brief description of the project.
     * @param businessSectorId   the name of the business sector to which the project belongs.
     * @param customerId         the name of the customer for whom the project is being created.
     * @param typologyId         the name of the project typology.
     */
    public ProjectCreationDto(String projectName, String projectDescription,
                              String businessSectorId, String customerId, String typologyId) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.businessSectorId = businessSectorId;
        this.customerId = customerId;
        this.typologyId = typologyId;
    }
}
