package org.switch2022.project.ddd.infrastructure.JPA;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.ddd.datamodel_jpa.ProjectResourceJpa;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Email;

import java.util.List;

public interface IProjectResourceJpaRepository extends CrudRepository<ProjectResourceJpa, String> {

    /**
     * This method retrieves a list of ProjectResourceJpa with a given email as attribute.
     *
     * @param email the value object email that represents the desired projectResourceJpa.
     * @return a list of ProjectResourceJpa with a given email as attribute.
     */
    List<ProjectResourceJpa> findAllByAccountEmail(Email email);

    /**
     * This method retrieves a list of ProjectResourceJpa with a given code as attribute.
     *
     * @param code the value object code that represents the desired projectResourceJpa.
     * @return a list of ProjectResourceJpa with a given code as attribute.
     */
    List<ProjectResourceJpa> findAllByProjectCode(Code code);
}

