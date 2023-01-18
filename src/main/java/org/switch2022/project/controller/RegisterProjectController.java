package org.switch2022.project.controller;

import org.switch2022.project.DTO.ProjectDTO;
import org.switch2022.project.model.Company;

public class RegisterProjectController {
  private Company company;
  private ProjectDTO dto;

  public RegisterProjectController(Company company) {
    this.company = company;
  }

  /*public boolean registerProject(String dto, String email){
    return company.registerProject(dto,email);
  }*/ //todo work to be done

}
