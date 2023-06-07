package org.switch2022.project.ddd.datamodel_jpa;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Represents a business sector entity in the Java Persistence API (JPA) data model.
 * This class is used to map business sector data to the corresponding database table.
 */
@NoArgsConstructor
@Entity
@Table(name = "business_sectors")
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

    public String getIdNumber() {
        return idNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        BusinessSectorJpa that = (BusinessSectorJpa) o;
        return Objects.equals(idNumber, that.idNumber) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNumber, name);
    }
}
