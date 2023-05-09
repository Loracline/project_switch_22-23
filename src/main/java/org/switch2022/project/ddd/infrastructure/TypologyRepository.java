package org.switch2022.project.ddd.infrastructure;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.model.typology.ITypologyRepository;
import org.switch2022.project.ddd.domain.model.typology.Typology;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
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
    public boolean add(Typology typology) {
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
    public int getSize() {
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
    public String getTypologyIdByName(String typologyName) {
        String requestedProjectTypologyId = null;
        int i = 0;
        while (i < this.typologies.size()) {
            if (typologies.get(i).getTypologyName().contains(typologyName)) {
                requestedProjectTypologyId = typologies.get(i).getTypologyId();
                i = this.typologies.size();
            }
            i++;
        }

        if (requestedProjectTypologyId == null) {
            throw new NotFoundInRepoException("Typology with this name does not exist in the Repository.");
        }

        return requestedProjectTypologyId;
    }
}
