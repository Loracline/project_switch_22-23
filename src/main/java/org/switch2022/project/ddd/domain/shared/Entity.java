package org.switch2022.project.ddd.domain.shared;

/**
 * An entity represents an object that has a distinct identity, mutable state, and a lifecycle that spans multiple
 * interactions with the system.
 *
 * This interface defines the contract for objects that meet these criteria.
 *
 * This interface is intended to be implemented by domain-specific entity classes
 * that define additional properties and behaviors.
 */

public interface Entity<T> {
    /**
     * An entity is uniquely identified by its ID. Therefore, entities compare by identity, not by attributes.
     *
     * @param other The other entity.
     * @return <code>true</code> if the identities are the same, regardless of their attributes.
     */
    boolean sameIdentityAs(T other);
}
