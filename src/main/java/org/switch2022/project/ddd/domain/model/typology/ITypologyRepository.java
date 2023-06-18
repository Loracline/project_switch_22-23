package org.switch2022.project.ddd.domain.model.typology;

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
     */

    String findTypologyIdByTypologyName(String typologyName);


    /**
     * Retrieves a Typology from the database based on the provided typology name.
     @param typologyName the name of the typology to search for.
     @return a Typology object that matches the provided typology name, or null if no match is found.
     */
    Typology findTypologyByTypologyName(String typologyName);

    /**
     * Retrieves all typologies from the database.
     *
     * @return a List containing all Typology objects found in the database.
     */
    List<Typology> findAll();
}
