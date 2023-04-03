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
    public final String businessSectorName;
    public final String customerName;
    public final String typologyName;
    public final int sprintDuration;

    /**
     * Constructor.
     * <br>
     *
     * @param projectName        the name of the project.
     * @param projectDescription a brief description of the project.
     * @param businessSectorName the name of the business sector to which the project belongs.
     * @param customerName       the name of the customer for whom the project is being created.
     * @param typologyName       the name of the project typology.
     * @param sprintDuration     the duration of each sprint in the project.
     */
    public ProjectCreationDto(String projectName, String projectDescription,
                              String businessSectorName, String customerName, String typologyName, int sprintDuration) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.businessSectorName = businessSectorName;
        this.customerName = customerName;
        this.typologyName = typologyName;
        this.sprintDuration = sprintDuration;
    }
}
