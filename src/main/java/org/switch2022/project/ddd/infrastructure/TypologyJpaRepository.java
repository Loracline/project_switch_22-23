package org.switch2022.project.ddd.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.switch2022.project.ddd.datamodel_jpa.TypologyJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.TypologyDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.typology.ITypologyRepository;
import org.switch2022.project.ddd.domain.model.typology.Typology;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;
import org.switch2022.project.ddd.infrastructure.jpa.ITypologyJpaRepository;

/**
 * Implementation of the {@link ITypologyRepository} interface that uses JPA to persist and retrieve tupology data.
 * This class interacts with the {@link ITypologyJpaRepository} for performing CRUD operations on {@link TypologyJpa}
 * entities.
 */
@Repository("typology_jpa")
public class TypologyJpaRepository implements ITypologyRepository{
    @SuppressWarnings("all")
    @Autowired
    ITypologyJpaRepository jpaRepository;

    @SuppressWarnings("all")
    @Autowired
    TypologyDomainDataAssembler assembler;

    public boolean save(Typology typology) {
        TypologyJpa typologyJpa= assembler.toData(typology);
        if (jpaRepository.existsById(typology.getTypologyId())) {
            throw new AlreadyExistsInRepoException("The typology already exists in the repository.");
        } else {
            jpaRepository.save(typologyJpa);
            return true;
        }
    }
    public int count(){
        return (int) jpaRepository.count();
    }

    public String findTypologyIdByTypologyName(String typologyName) {
        String requestedProjectTypologyId =
                jpaRepository.findTypologyIdByTypologyName(typologyName);
        if (requestedProjectTypologyId == null) {
            throw new NotFoundInRepoException("Typology with this name does not exist in the Repository.");
        }
        return requestedProjectTypologyId;
    }
}
