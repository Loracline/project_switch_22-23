package org.switch2022.project.ddd.domain.model.typology;

import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.util.List;

/**
 * Interface for a repository of typologies.
 */
public interface ITypologyRepository {
    /**
     * This method adds a new typology to the repository of typologies.
     *
     * @param typology to be added to the repository.
     * @return true if the typology is added and throw exception otherwise.
     */
    boolean save(Typology typology);

    /**
     * This method gets the size of the repository list
     *
     * @return the integer equivalent to the size of the list typologies
     */
    int count();

    /**
     * Retrieves the ID of a typology with the given name from the repository.
     *
     * @param typologyName the name of the project typology whose ID is being requested.
     * @return the ID of the project typology with the given name.
     * @throws NotFoundInRepoException if a business sector with the given name is not found in the repository.
     */

    String findTypologyIdByTypologyName(String typologyName);

    List<Typology> findAll();
}
