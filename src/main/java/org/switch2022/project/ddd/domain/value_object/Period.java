package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

import java.time.LocalDate;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;

public class Period implements ValueObject<Period> {

    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructor.
     * <p>
     * Creates a new period with the given start date and duration.
     *
     * @param startDate the start date of the period.
     * @param duration  the duration of the period.
     */
    public Period(final LocalDate startDate, final Number duration) {
        Validate.notNull(startDate, "The start date must not be null");
        Validate.notNull(duration, "The duration must not be null");
        Validate.notNegative(duration, "duration");
        this.startDate = startDate;
        this.endDate = calculateEndDate(startDate, duration);
    }

    /**
     * Constructor.
     * <p>
     * Creates a new period with the given start and end dates.
     *
     * @param startDate the start date of the period.
     * @param endDate   the end date of the period.
     */
    public Period(final LocalDate startDate, final LocalDate endDate) {
        Validate.notNull(startDate, "The start date must not be null");
        Validate.notNull(endDate, "The end date must not be null");
        Validate.isAfter(endDate, startDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Getter method for the attribute: startDate.
     *
     * @return the start date of the project.
     */
    public String getStartDate() {
        return startDate.toString();
    }

    /**
     * Getter method for the attribute: endDate.
     *
     * @return the end date of the project.
     */
    public String getEndDate() {
        return endDate.toString();
    }

    /**
     * This method calculates the end date of the period given a start date and a
     * duration.
     *
     * @param startDate the start date of the period.
     * @param duration  the duration of the period.
     * @return the end date of the period.
     */
    private static LocalDate calculateEndDate(final LocalDate startDate,
                                              final Number duration) {
        return startDate.plusWeeks(duration.intValue());
    }

    /**
     * This method checks if two instances of Period are equal by comparing the value
     * of their attributes
     * startDate and endDate.
     *
     * @param other Period instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and
     * <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(Period other) {
        return other != null && this.startDate.equals(other.startDate) && this.endDate.equals(other.endDate);
    }

    /**
     * This method checks if an instance of Period is equal to another object.
     *
     * @param o object to compare with.
     * @return <code>true</code> if the two have the same attribute value, and
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Period other = (Period) o;

        return sameValueAs(other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the
     * object's state.
     *
     * @return a unique value that represents the object.
     */
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
        return this.endDate.isBefore(period.startDate) && period.endDate.isAfter(this.startDate);
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
     * This method checks if date is equal or lower than start date.
     *
     * @param date to compare.
     * @return true if date is equal or lower than start date or false otherwise.
     */
    public boolean isDateEqualOrLowerThanStartDate(LocalDate date) {
        boolean isEqualOrLower = false;
        if (date != null) {
            isEqualOrLower =
                    date.isBefore(this.startDate) || date.isEqual(this.startDate);
        }
        return isEqualOrLower;
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

    /**
     * This method checks if date is equal or greater than end date.
     *
     * @param date to compare.
     * @return true if date is equal or greater than end date or false otherwise.
     */
    public boolean isDateEqualOrGreaterThanEndDate(LocalDate date) {
        boolean isEqualOrGreater = false;
        if (date != null) {
            isEqualOrGreater = date.isAfter(this.endDate) || date.isEqual(this.endDate);
        }
        return isEqualOrGreater;
    }

    /**
     * Checks if this period contains the specified period.
     *
     * @param otherPeriod The period to check for containment.
     * @return {@code TRUE} if this period contains the specified period, {@code FALSE} otherwise.
     * @throws IllegalArgumentException if the specified period is null.
     */
    public boolean contains(Period otherPeriod) {
        Validate.notNull(otherPeriod, "The period must not be null");
        LocalDate otherStart = LocalDate.parse(otherPeriod.getStartDate());
        LocalDate otherEnd = LocalDate.parse(otherPeriod.getEndDate());

        return this.startDate.compareTo(otherStart) <= 0 && this.endDate.compareTo(otherEnd) >= 0;
    }

    /**
     * This method verifies that current date is contained in a period.
     *
     * @return true if the current date is contained in the period, and false otherwise.
     */
    public boolean containsCurrentDate() {
        boolean result;
        if (this.startDate.equals(LocalDate.now()) || this.endDate.equals(LocalDate.now())) {
            result = true;
        } else {
            result = this.startDate.isBefore(LocalDate.now()) && this.endDate.isAfter(LocalDate.now());
        }
        return result;
    }

    /**
     * This method counts the number of days included in a time period including the first day, and the last day.
     *
     * @return the number of days contained in period.
     */
    public int numberOfDaysContainedInPeriod() {
        return (int) DAYS.between(this.startDate, this.endDate) + 1;
    }
}
