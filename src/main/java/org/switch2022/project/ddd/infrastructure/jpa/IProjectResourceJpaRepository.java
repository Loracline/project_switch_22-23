package org.switch2022.project.ddd.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.ddd.datamodel_jpa.ProjectResourceJpa;

import java.util.List;

public interface IProjectResourceJpaRepository extends CrudRepository<ProjectResourceJpa, String> {

    /**
     * This method retrieves a list of ProjectResourceJpa with a given email as attribute.
     *
     * @param email the value object email that represents the desired projectResourceJpa.
     * @return a list of ProjectResourceJpa with a given email as attribute.
     */
    List<ProjectResourceJpa> findByAccountEmail(String email);

    /**
     * This method retrieves a list of ProjectResourceJpa with a given code as attribute.
     *
     * @param code the value object code that represents the desired projectResourceJpa.
     * @return a list of ProjectResourceJpa with a given code as attribute.
     */
    List<ProjectResourceJpa> findByProjectCode(String code);
}

