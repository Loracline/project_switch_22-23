package org.switch2022.project.ddd.domain.model.typology;

import org.switch2022.project.ddd.domain.value_object.Name;

/**
 * Factory of Typology class.
 */

public interface IFactoryTypology {

    /**
     * This method creates a Typology object with no return.
     *
     * @param typologyNumber of the Typology to create.
     * @param typology       is the name.
     */

    Typology createTypology(int typologyNumber, Name typology);
}
