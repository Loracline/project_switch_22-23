package org.switch2022.project.ddd.dto;

/**
 * DTO (Data Transfer Object) representing a sprint using primitive types.
 * This class encapsulates the data related to a sprint using primitive types for all properties.
 * It is used for transferring sprint information between different layers of the application.
 */
public class SprintPrimitiveTypesDto {

    public final String id;
    public final String number;
    public final String status;
    public final String startDate;
    public final String endDate;

    /**
     * Constructs a new instance of the SprintPrimitiveTypesDto class.
     *
     * @param number    The sprint number as a string.
     * @param status    The sprint status as a string.
     * @param startDate The start date of the sprint as a string.
     * @param endDate   The end date of the sprint as a string.
     */
    public SprintPrimitiveTypesDto(String id, String number, String status, String startDate, String endDate) {
        this.id = id;
        this.number = number;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
