package org.switch2022.project.ddd.domain.model.typology;

import org.switch2022.project.ddd.domain.value_object.Name;

/**
 * Factory of Typology class.
 */

public interface ITypologyFactory {

    /**
     * This method creates a Typology object.
     *
     * @param typologyNumber f the Typology to create.
     * @param typologyName   is the name.
     * @return the typology created
     */
    Typology createTypology(int typologyNumber, Name typologyName);
}
