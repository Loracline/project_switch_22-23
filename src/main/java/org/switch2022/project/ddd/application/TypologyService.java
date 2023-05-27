package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.switch2022.project.ddd.domain.model.typology.ITypologyFactory;
import org.switch2022.project.ddd.domain.model.typology.ITypologyRepository;
import org.switch2022.project.ddd.domain.model.typology.Typology;
import org.switch2022.project.ddd.domain.value_object.Name;


@Service
public class TypologyService {
    @Autowired
    ITypologyRepository typologyRepository;
    @Qualifier("typology_jpa")
    @Autowired
    ITypologyFactory factoryTypology;

    /**
     * This method receives a String name and to convert in of objects of type Name.
     *
     * @param name that represents a String
     * @return TRUE if the typology is created and added to the typology repository successfully.
     */
    public boolean createTypology(String name) {
        Name typologyName = new Name(name);
        int typologyNumber = Math.addExact(typologyRepository.count(), 1);
        Typology typology = factoryTypology.createTypology(typologyNumber, typologyName);
        return typologyRepository.save(typology);
    }
}
