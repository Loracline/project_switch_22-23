package org.switch2022.project.ddd.datamodel_jpa;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a business sector entity in the Java Persistence API (JPA) data model.
 * This class is used to map business sector data to the corresponding database table.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "business-sectors")
public class BusinessSectorJpa {
    /**
     * Attributes
     */
    @Id
    private String idNumber;
    private String name;

    /**
     * Constructs a new instance of BusinessSectorJpa with the provided parameters.
     *
     * @param idNumber The identifier of the business sector.
     * @param name     The name of the business sector.
     */
    public BusinessSectorJpa(String idNumber, String name) {
        this.idNumber = idNumber;
        this.name = name;
    }
}
