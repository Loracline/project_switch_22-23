package org.switch2022.project.ddd.datamodel_jpa.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.switch2022.project.ddd.datamodel_jpa.TypologyJpa;
import org.switch2022.project.ddd.domain.model.typology.IFactoryTypology;
import org.switch2022.project.ddd.domain.model.typology.Typology;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.utils.Utils;

/**
 * Assembler class responsible for converting typology instances to TypologyJpa instances
 * and vice versa.
 */
public class TypologyDomainDataAssembler {
    @SuppressWarnings("all")
    @Autowired
    IFactoryTypology factory;

    /**
     * Converts a typology instance to a TypologyJpa instance.
     *
     * @param typology The Typologogu instance to be converted.
     * @return The converted TypologyJpa instance.
     */
    public TypologyJpa toData(Typology typology) {
        return new TypologyJpa(typology.getTypologyId(),
                typology.getTypologyName());
    }

    /**
     * Converts a typologyJpa data model instance to a BusinessSector domain instance.
     *
     * @param typologyJpa The BusinessSectorJpa instance to be converted.
     * @return The converted BusinessSector instance.
     */
    public Typology toDomain(TypologyJpa typologyJpa) {
        int projectTypologyId = Utils.getIntFromAlphanumericString(typologyJpa.getTypologyId(),
                "PT");
        Name typologyName = new Name(typologyJpa.getTypologyName());

        return factory.createTypology(projectTypologyId, typologyName);
    }

}
