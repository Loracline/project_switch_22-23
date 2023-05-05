package org.switch2022.project.ddd.domain.model.typology;

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
    boolean add(Typology typology);

    /**
     * This method gets the size of the repository list
     *
     * @return the integer equivalent to the size of the list typologies
     */
    int getSize();
}
