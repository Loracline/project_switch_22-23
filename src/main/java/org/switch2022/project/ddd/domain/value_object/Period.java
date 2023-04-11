package org.switch2022.project.ddd.domain.value_object;

import org.switch2022.project.ddd.domain.shared.ValueObject;
import org.switch2022.project.ddd.utils.Validate;

import java.time.LocalDate;
import java.util.Objects;

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
        Validate.notNull(duration, "duration");
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
     * @param endDate  the end date of the period.
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
     * This method calculates the end date of the period given a start date and a duration.
     *
     * @param startDate the start date of the period.
     * @param duration  the duration of the period.
     * @return the end date of the period.
     */
    private static final LocalDate calculateEndDate(final LocalDate startDate, final Number duration) {
        return startDate.plusWeeks(duration.intValue());
    }

    /**
     * This method checks if two instances of Period are equal by comparing the value of their attributes
     * startDate and endDate.
     *
     * @param other Period instance to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameValueAs(Period other) {
        return other != null && this.startDate.equals(other.startDate) && this.endDate.equals(other.endDate);
    }

    /**
     * This method checks if an instance of Period is equal to another object.
     *
     * @param o object to compare with.
     * @return <code>true</code> if the two have the same attribute value, and <code>false</code> otherwise.
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
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
