package org.switch2022.project.controller;

import org.switch2022.project.DTO.ProjectDTO;
import org.switch2022.project.mapper.ProjectMapper;
import org.switch2022.project.model.Company;

/**
 * Class RegisterProjectController is built to register projects.
 */

public class RegisterProjectController {
  private Company company;
  private ProjectMapper dto;

  /**
   * RegisterProjectController constructor
   * @param company
   */
  public RegisterProjectController(Company company) {
    this.company = company;
    this.dto = dto;
  }

  /**
   * This method will call the method registerProject in Company
   * @param dto
   * @param email
   * @return true if the project is registered
   */
 /* public boolean registerProject(ProjectDTO dto, String email){
    return company.registerProject(dto,email);
  }
*/
}
