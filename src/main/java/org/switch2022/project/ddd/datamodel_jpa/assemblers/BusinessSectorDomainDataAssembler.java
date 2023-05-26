package org.switch2022.project.ddd.datamodel_jpa.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.datamodel_jpa.BusinessSectorJpa;
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSector;
import org.switch2022.project.ddd.domain.model.business_sector.IBusinessSectorFactory;
import org.switch2022.project.ddd.domain.value_object.Name;


/**
 * Assembles data between the domain model and JPA data model for the BusinessSector entity.
 * This class is responsible for converting BusinessSector objects to BusinessSectorJpa objects and vice versa.
 */
@Service
public class BusinessSectorDomainDataAssembler {

    @SuppressWarnings("all")
    @Autowired
    private IBusinessSectorFactory factory;

    /**
     * Converts a BusinessSector domain instance to a BusinessSectorJpa data model instance.
     *
     * @param businessSector The BusinessSector instance to be converted.
     * @return The converted BusinessSectorJpa instance.
     */
    public BusinessSectorJpa toData(BusinessSector businessSector){
        return new BusinessSectorJpa(businessSector.getBusinessSectorId(),
                businessSector.getBusinessSectorName());
    }

    /**
     * Converts a BusinessSectorJpa data model instance to a BusinessSector domain instance.
     *
     * @param businessSectorJpa The BusinessSectorJpa instance to be converted.
     * @return The converted BusinessSector instance.
     */

    public BusinessSector toDomain(BusinessSectorJpa businessSectorJpa) {
        Number businessSectorId = Integer.parseInt(businessSectorJpa.getIdNumber());
        Name businessSectorName = new Name(businessSectorJpa.getName());
        return factory.createBusinessSector(businessSectorId, businessSectorName);
    }
}
