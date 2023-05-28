package org.switch2022.project.ddd.domain.model.typology;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.value_object.Name;

/**
 * Class FactoryTypology implements IFactoryTypology in order to create
 * a Typology object.
 */
@Component
public class TypologyFactory implements ITypologyFactory {

    /**
     * This method creates a projectTypology object.
     *
     * @param typologyNumber is an attribute of Typology.
     * @param typologyName   is an attribute of Typology.
     * @return a new object Typology.
     */
    @Override
    public Typology createTypology(int typologyNumber, Name typologyName) {
        return new Typology(typologyNumber, typologyName);
    }
}
