package org.switch2022.project.ddd.infrastructure;

import org.springframework.stereotype.Repository;
import org.switch2022.project.ddd.domain.model.typology.ITypologyRepository;
import org.switch2022.project.ddd.domain.model.typology.Typology;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository("typology_memory")
public class TypologyRepository implements ITypologyRepository {
    /**
     * Attributes
     */
    private final List<Typology> typologies = new ArrayList<>();

    /**
     * This method checks if an instance of typologyRepository is equal to another object.
     *
     * @param o object to compare with.
     * @return true if the two have the same attribute value, and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        TypologyRepository that = (TypologyRepository) o;
        return Objects.equals(typologies, that.typologies);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the
     * object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(typologies);
    }

    /**
     * This method adds a typology to typology repository  if the tupology does not exist.
     *
     * @param typology to be added to the repository.
     * @return true if the typology was added to typology repository and throw exception otherwise.
     */
    public boolean save(Typology typology) {
        if (typologies.contains(typology)) {
            throw new AlreadyExistsInRepoException("The typology already exists in the repository.");
        } else {
            typologies.add(typology);
            return true;
        }
    }

    /**
     * This method gets the size of the repository list
     *
     * @return the integer equivalent to the size of the list typologies
     */
    public int count() {
        return typologies.size();
    }

    /**
     * Retrieves the ID of a typology with the given name from the repository.
     *
     * @param typologyName the name of the project typology whose ID is being requested.
     * @return the ID of the project typology with the given name.
     * @throws NotFoundInRepoException if a business sector with the given name is not found in the repository.
     */

    @Override
    public String findTypologyIdByTypologyName(String typologyName) {
        String requestedProjectTypologyId = null;
        int i = 0;
        while (i < this.typologies.size()) {
            if (typologies.get(i).getTypologyName().contains(typologyName)) {
                String typologyIdWithLetters = typologies.get(i).getTypologyId();
                requestedProjectTypologyId = typologyIdWithLetters.replaceAll("[^0-9]", "");
                i = this.typologies.size();
            }
            i++;
        }

        if (requestedProjectTypologyId == null) {
            throw new NotFoundInRepoException("Typology with this name does not exist in the Repository.");
        }

        return requestedProjectTypologyId;
    }

    /**
     * This method returns a list of all typologies in the repository.
     *
     * @return an unmodifiable view of the typologies.
     */
    public List<Typology> findAll() {
        return Collections.unmodifiableList(typologies);
    }
}
