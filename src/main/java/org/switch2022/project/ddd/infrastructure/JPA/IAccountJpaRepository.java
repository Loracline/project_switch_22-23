package org.switch2022.project.ddd.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.ddd.datamodel_jpa.AccountJpa;

/**
 * Repository interface for managing AccountJpa entity.
 * Extends CrudRepository for basic CRUD operations.
 */
public interface IAccountJpaRepository extends CrudRepository<AccountJpa, String> {
}
