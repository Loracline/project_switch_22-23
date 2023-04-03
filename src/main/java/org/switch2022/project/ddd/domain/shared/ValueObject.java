package org.switch2022.project.ddd.domain.shared;

/**
 * A value object represents a domain concept that has no distinct identity and is immutable, which means that its
 *  properties cannot be changed once they are created
 *
 * This interface defines the contract for objects that meet these criteria.
 *
 * This interface is intended to be implemented by domain-specific value object classes
 that define additional properties and behaviors for specific types of value objects.
 */

public interface ValueObject<T> {

    /**
     * Value objects compare by the values of their attributes, since they don't have an identity.
     *
     * @param other The other value object.
     * @return <code>true</code> if the given value object's and this value object's attributes are the same.
     */
    boolean sameValueAs(T other);
}
