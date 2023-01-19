package org.switch2022.project.container;

import org.switch2022.project.model.BusinessSector;

import java.util.List;

/**
 * Class BusinessSectorContainer is built to allow access to class BusinessSector.
 */
public class BusinessSectorContainer {
  /**
   * BussinessSectorContainer contains business sectors
   */
  private List<BusinessSector> businessSectors;

  public BusinessSectorContainer(List<BusinessSector> businessSectors) {
    this.businessSectors = businessSectors;
  }

  /**
   * This method validates if businessSector exits
   *
   * @param businessSector one must check
   * @return true if businessSector exists in businessSectors
   */
  private boolean doesBusinessSectorExist(BusinessSector businessSector) {
    return this.businessSectors.contains(businessSector);
  }
  /**
   * This method creates businessSector  and adds it to businessSectors
   *
   * @param businessSectorName
   * @return true if businessSector is added to list
   */

  public boolean createBusinessSector(String businessSectorName){
    BusinessSector businessSector= new BusinessSector(businessSectorName);
    boolean isAddedToList= false;
    if(!doesBusinessSectorExist(businessSector)){
      businessSectors.add(businessSector);
      isAddedToList= true;
    }
    return isAddedToList;
  }

  /**
   * This method returns a list of business sectors
   *
   * @return list
   */
  public List<BusinessSector> getBusinessSectors() {
    return businessSectors;
  }

  /**
   * This method returns a business sector from the list of business sectors
   *
   * @param businessSector
   * @return business sector
   */

  public BusinessSector getBusinessSector(String businessSector) {
    BusinessSector requestedBusinessSector = null;
    for (int i = 0; i < businessSectors.size(); i++) {
      if (businessSectors.get(i).equals(businessSector)) {
        requestedBusinessSector = businessSectors.get(i);
        break;
      }
    }
    return requestedBusinessSector;
  }
}
