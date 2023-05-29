package org.switch2022.project.ddd.dto.mapper;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSector;
import org.switch2022.project.ddd.dto.BusinessSectorDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class BusinessSectorMapper {
    /**
     * This method converts a business sector into a Dto.
     *
     * @param businessSector one must convert.
     * @return Dto carrying data.
     */

    public BusinessSectorDto businessSectorToDto(BusinessSector businessSector) {
        return new BusinessSectorDto(businessSector.getBusinessSectorName());
    }

    /**
     * This method converts a list of business sectors into a list of businessSectorDtos.
     *
     * @param businessSectors list one must convert in Dto
     * @return a list of accountDtos.
     */
    public List<BusinessSectorDto> listBusinessSectorsToDto(List<BusinessSector> businessSectors) {
        List<BusinessSectorDto> businessSectorDtos = new ArrayList<>();
        for (int i = 0; i < businessSectors.size(); i++) {
            BusinessSectorDto accountDTO = businessSectorToDto(businessSectors.get(i));
            businessSectorDtos.add(accountDTO);
        }
        return businessSectorDtos;
    }
}
