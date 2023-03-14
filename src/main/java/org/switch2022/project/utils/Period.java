package org.switch2022.project.utils;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a period of time defined by a start date and an end date.
 */
public class Period {
    /**
     * The start date of the period.
     */
    private final LocalDate startDate;

    /**
     * The end date of the period.
     */
    private final LocalDate endDate;

    /**
     * Creates a new period with the given start date and duration in sprints.
     *
     * @param startDate      the start date of the period
     * @param sprintDuration the duration of the period in sprints
     */
    public Period(LocalDate startDate, int sprintDuration) {
        this.startDate = startDate;
        this.endDate = calculateEndDate(startDate, sprintDuration);
    }

    /**
     * Returns the start date of the period.
     *
     * @return the start date of the period
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date of the period.
     *
     * @return the end date of the period
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Calculates the end date of the period given the start date and duration in sprints.
     *
     * @param startDate      the start date of the period
     * @param sprintDuration the duration of the period in sprints
     * @return the end date of the period
     */
    private final LocalDate calculateEndDate(LocalDate startDate, int sprintDuration) {
        return startDate.plusWeeks(sprintDuration);
    }

    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) {
            return true;
        }
        if (toCompare == null) {
            return false;
        }
        if (toCompare.getClass() != this.getClass()) {
            return false;
        }
        Period period = (Period) toCompare;
        return startDate.equals(period.startDate) && endDate.equals(period.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }

    /**
     * Verifies the startDate is not before today date.
     *
     * @return true if the startDate is before present day.
     */
    public boolean isStartDateBeforeNow() {
        return this.startDate.isBefore(LocalDate.now());
    }

    /**
     * Determines if this period does not overlap with the given period.
     *
     * @param period the period to compare with
     * @return true if the periods do not overlap, false otherwise
     */
    public boolean isPeriodNotOverlapping(Period period) {
        // check if this period ends before the start of the given period
        return this.endDate.isBefore(period.getStartDate()) || period.getEndDate().isBefore(this.startDate);
    }


    /**
     * This method checks if date is equal or greater than start date.
     *
     * @param date to compare.
     * @return true if date is equal or greater than start date or false otherwise.
     */
    public boolean isDateEqualOrGreaterThanStartDate(LocalDate date) {
        boolean isEqualOrGreater = false;
        if (date != null) {
            isEqualOrGreater =
                    date.isAfter(this.startDate) || date.isEqual(this.startDate);
        }
        return isEqualOrGreater;
    }

    /**
     * This method checks if date is equal or lower than end date.
     *
     * @param date to compare.
     * @return true if date is equal or lower than end date or false otherwise.
     */
    public boolean isDateEqualOrLowerThanEndDate(LocalDate date) {
        boolean isEqualOrLower = false;
        if (date != null) {
            isEqualOrLower = date.isBefore(this.endDate) || date.isEqual(this.endDate);
        }
        return isEqualOrLower;
    }
}