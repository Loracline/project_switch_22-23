package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.switch2022.project.ddd.domain.model.typology.IFactoryTypology;
import org.switch2022.project.ddd.domain.model.typology.ITypologyRepository;
import org.switch2022.project.ddd.domain.model.typology.Typology;
import org.switch2022.project.ddd.domain.value_object.Name;


@Service
public class TypologyService {
    @Autowired
    private ITypologyRepository typologyRepository;
    @Autowired
    private IFactoryTypology factoryTypology;

    /**
     * Constructor.
     */
    public TypologyService() {
    }

    /**
     * This method receives a String name and to convert in of objects of type Name.
     *
     * @param name that represents a String
     * @return TRUE if the typology is created and added to the typology repository successfully.
     */
    public boolean createTypology(String name) {
        Name typologyName = new Name(name);
        int typologyNumber = calculateNextTypologyNumber();
        Typology typology = factoryTypology.createTypology(typologyNumber, typologyName);
        return typologyRepository.add(typology);
    }

    /**
     * This method calculates the next typology number
     *
     * @return the number of typologies already contained in the list (equivalent to the size of the list) plus one.
     * Which logically equals the next number.
     */
    private int calculateNextTypologyNumber() {
        return typologyRepository.getSize() + 1;
    }
}
