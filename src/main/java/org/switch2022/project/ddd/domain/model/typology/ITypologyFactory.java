package org.switch2022.project.ddd.domain.model.typology;

import org.switch2022.project.ddd.domain.value_object.Name;

/**
 * Factory of Typology class.
 */

public interface ITypologyFactory {

    /**
     * This method creates a Typology object with no return.
     *
     * @param typologyNumber of the Typology to create.
     * @param typologyName   is the name.
     */

    Typology createTypology(int typologyNumber, Name typologyName);
}
