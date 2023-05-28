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

import java.util.Optional;

/**
 * Implementation of the {@link ITypologyRepository} interface that uses JPA to persist and retrieve tupology data.
 * This class interacts with the {@link ITypologyJpaRepository} for performing CRUD operations on {@link TypologyJpa}
 * entities.
 */
@Repository("typology_jpa")
public class TypologyJpaRepository implements ITypologyRepository {
    @SuppressWarnings("all")
    @Autowired
    ITypologyJpaRepository crudRepository;

    @SuppressWarnings("all")
    @Autowired
    TypologyDomainDataAssembler assembler;

    /**
     * Saves a {@link Typology} object in the repository.
     * If the typology's id already exists in the repository, an {@link AlreadyExistsInRepoException} is thrown.
     *
     * @param typology The typology to be saved.
     * @return {@code true} if the typology was successfully saved, {@code false} otherwise.
     * @throws AlreadyExistsInRepoException if the typology's id already exists in the repository.
     */
    @Override
    public boolean save(Typology typology) {
        TypologyJpa typologyJpa = assembler.toData(typology);
        if (crudRepository.existsById(typology.getTypologyId())) {
            throw new AlreadyExistsInRepoException("The typology already exists in the repository.");
        } else {
            crudRepository.save(typologyJpa);
            return true;
        }
    }

    /**
     * Retrieves the size of the repository list.
     *
     * @return The number of elements in the repository.
     */
    @Override
    public int count() {
        return (int) crudRepository.count();
    }

    /**
     * Retrieves the ID of a typology with the given name from the repository.
     *
     * @param typologyName the name of the typology whose ID is being requested.
     * @return the ID of the typology with the given name.
     * @throws NotFoundInRepoException if a typology the given name is not found in the repository.
     */
    public String findTypologyIdByTypologyName(String typologyName) {
        Optional<TypologyJpa> typology =
                crudRepository.findByTypologyName(typologyName);
        String typologyId;
        if (typology.isPresent()) {
            typologyId = typology.get().getTypologyId();
        } else {
            throw new NotFoundInRepoException("Typology with this name does not exist in the Repository.");
        }
        return typologyId;
    }
}
