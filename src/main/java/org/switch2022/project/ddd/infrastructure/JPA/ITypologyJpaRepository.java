package org.switch2022.project.ddd.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.ddd.datamodel_jpa.TypologyJpa;


public interface ITypologyJpaRepository extends CrudRepository<TypologyJpa, String> {
    /**
     * This method returns the typology id in the repository that has a given typology name.
     *
     * @param typologyName the name of the typology to be found
     * @return the typology id with the given name.
     *
     */
    String findTypologyIdByTypologyName(String typologyName);
}
