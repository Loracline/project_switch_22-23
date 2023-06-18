package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.typology.ITypologyFactory;
import org.switch2022.project.ddd.domain.model.typology.ITypologyRepository;
import org.switch2022.project.ddd.domain.model.typology.Typology;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.dto.TypologyDto;
import org.switch2022.project.ddd.dto.mapper.TypologyMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypologyService {
    @Autowired
    @Qualifier("typology_jpa")
    ITypologyRepository typologyRepository;
    @Autowired
    ITypologyFactory factoryTypology;

    @Autowired
    TypologyMapper typologyMapper;

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

    /**
     * Requests a list of all projects
     *
     * @return a list of all projectsDto.
     */
    public List<TypologyDto> requestAllTypologies() {
        List<TypologyDto> typologiesDto = new ArrayList<>();
        List<Typology> typologies = typologyRepository.findAll();
        for (Typology typology : typologies) {
            typologiesDto.add(typologyMapper.typologyToDto(typology));
        }
        return typologiesDto;
    }

    /**
     * Retrieves a typology DTO (Data Transfer Object) based on the provided typology
     * name.
     *
     * @param typologyName Typology name of the typology to be retrieved.
     * @return TypologyDTO based on the Typology provided by the repository.
     */
    public TypologyDto getByTypologyName(String typologyName) {
        Typology typology = typologyRepository.findTypologyByTypologyName(typologyName);
        return typologyMapper.typologyToDto(typology);
    }
}
