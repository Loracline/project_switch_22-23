package org.switch2022.project.ddd.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.ddd.datamodel_jpa.ProjectJpa;
import org.switch2022.project.ddd.domain.model.project.Project;

import java.util.Collection;
import java.util.Optional;


public interface IProjectJpaRepository extends CrudRepository<ProjectJpa, String> {

    Optional<ProjectJpa> findByProjectCode(String projectCode);

    long count();

    boolean save(Project project);

    Iterable<ProjectJpa> findAll();

    Iterable<ProjectJpa> findAllByProjectCodeIn(Collection<String> projectCode);
}
