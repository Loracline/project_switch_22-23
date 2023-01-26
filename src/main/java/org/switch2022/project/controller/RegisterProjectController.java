package org.switch2022.project.controller;

import org.switch2022.project.model.Company;
import org.switch2022.project.model.Project;
import org.switch2022.project.utils.dto.ProjectDTO;
import org.switch2022.project.utils.mapper.ProjectMapper;

/**
 * Class RegisterProjectController is built to register projects.
 */

public class RegisterProjectController {
  private final Company company;

  /**
   * RegisterProjectController constructor
   *
   * @param company
   */
  public RegisterProjectController(Company company) {
    this.company = company;
  }

  /**
   * This method will call the method registerProject in Company
   *
   * @param dto
   * @param email
   * @return true if the project is registered
   */
  public boolean registerProject(Project dto, String email) {
    return company.validateManager(email) && company.registerProject(ProjectMapper.getDTOFromProject(dto));
  }
}

