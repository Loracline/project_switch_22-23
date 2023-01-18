package org.switch2022.project.model;

import java.util.Objects;

public class BusinessSector {
    private String businessSectorName;

    public BusinessSector(String businessSectorName) {
        this.businessSectorName = businessSectorName.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessSector)) return false;
        BusinessSector that = (BusinessSector) o;
        return Objects.equals(businessSectorName, that.businessSectorName.toLowerCase());
    }
}
