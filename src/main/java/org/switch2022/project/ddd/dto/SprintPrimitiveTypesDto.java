package org.switch2022.project.ddd.dto;

/**
 * DTO (Data Transfer Object) representing a sprint using primitive types.
 * This class encapsulates the data related to a sprint using primitive types for all properties.
 * It is used for transferring sprint information between different layers of the application.
 */
public class SprintPrimitiveTypesDto {

    private final String number;
    private final String status;
    private final String startDate;
    private final String endDate;

    /**
     * Constructs a new instance of the SprintPrimitiveTypesDto class.
     *
     * @param number    The sprint number as a string.
     * @param status    The sprint status as a string.
     * @param startDate The start date of the sprint as a string.
     * @param endDate   The end date of the sprint as a string.
     */
    public SprintPrimitiveTypesDto(String number, String status, String startDate, String endDate) {
        this.number = number;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Retrieves the sprint number as a string.
     *
     * @return The sprint number as a string.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Retrieves the sprint status as a string.
     *
     * @return The sprint status as a string.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Retrieves the start date of the sprint as a string.
     *
     * @return The start date of the sprint as a string.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Retrieves the end date of the sprint as a string.
     *
     * @return The end date of the sprint as a string.
     */
    public String getEndDate() {
        return endDate;
    }
}
