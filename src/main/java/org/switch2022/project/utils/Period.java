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
    private LocalDate startDate;

    /**
     * The end date of the period.
     */
    private LocalDate endDate;

    /**
     * Creates a new period with the given start date and duration in sprints.
     *
     * @param startDate the start date of the period
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
    public LocalDate getStartDate() { return startDate; }

    /**
     * Returns the end date of the period.
     *
     * @return the end date of the period
     */
    public LocalDate getEndDate() { return endDate; }

    /**
     * Calculates the end date of the period given the start date and duration in sprints.
     *
     * @param startDate the start date of the period
     * @param sprintDuration the duration of the period in sprints
     * @return the end date of the period
     */
    private LocalDate calculateEndDate(LocalDate startDate, int sprintDuration) {
        return startDate.plusWeeks(sprintDuration);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(startDate, period.startDate) &&
                Objects.equals(endDate, period.endDate);
    }


    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }

    public boolean isStartDateBeforeNow() {
        return this.startDate.isBefore(LocalDate.now()) || this.startDate.isEqual(LocalDate.now());
    }

    /**
     * Determines if this period does not overlap with the given period.
     *
     * @param period the period to compare with
     * @return true if the periods do not overlap, false otherwise
     */

    public boolean isPeriodNotOverlapping(Period period) {
        // check if this period ends before the start of the given period
        if (this.endDate.isBefore(period.getStartDate())) {
            return true;
        }
        // check if the given period ends before the start of this period
        if (period.getEndDate().isBefore(this.startDate)) {
            return true;
        }
        // the periods overlap
        return false;
    }

}