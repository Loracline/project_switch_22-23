package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSector;
import org.switch2022.project.ddd.domain.model.business_sector.IBusinessSectorFactory;
import org.switch2022.project.ddd.domain.model.business_sector.IBusinessSectorRepository;
import org.switch2022.project.ddd.domain.value_object.Name;


@Service
public class BusinessSectorService {
    @SuppressWarnings("all")
    @Qualifier("businessSector_jpa")
    @Autowired
    private IBusinessSectorRepository businessSectorRepository;
    @SuppressWarnings("all")
    @Autowired
    private IBusinessSectorFactory businessSectorFactory;


    
    /**
     * This method receives a name as a String and creates a new business sector with that name.
     *
     * @param name that represents a project typology.
     * @return TRUE if the typology is created and added to the typology repository successfully, and throws an
     * AlreadyExistsInRepoException otherwise.
     */
    public boolean createBusinessSector(String name) {
        Name businessSectorName = new Name(name);
        int businessSectorNumber = calculateNextBusinessSectorNumber();
        BusinessSector businessSector = businessSectorFactory.createBusinessSector(businessSectorNumber,
                businessSectorName);
        return businessSectorRepository.save(businessSector);
    }

    /**
     * This method calculates the next business sector number
     *
     * @return the number of business sectors already contained in the list (equivalent to the
     * size of the list) plus
     * one, which logically equals the next number.
     */
    private int calculateNextBusinessSectorNumber() {
        return businessSectorRepository.count() + 1;
    }
}
