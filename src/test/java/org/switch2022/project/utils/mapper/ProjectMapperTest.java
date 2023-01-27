package org.switch2022.project.utils.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.controller.RegisterProjectController;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.dto.ProjectDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectMapperTest {

  /**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   */

  Project projectOne;
  ProjectDTO projectDTO;
  ProjectTypologyContainer projectTypologyContainer;
  ProjectTypology typology;
  List<Project> projects;
  BusinessSectorContainer businessSectorContainer;
  ProjectContainer projectContainer;
  CustomerContainer customerContainer;
  BusinessSector businessSector;
  Customer customer;
  Company company;



  @BeforeEach
  void setUp() {

    projectOne = new Project("AA001", "Aptoide", new Customer("ISEP","228674498"),
            new ProjectTypology("Fixed Cost"), new BusinessSector("fishing"));
    projects = new ArrayList<>();
    projects.add(projectOne);
    projectContainer = new ProjectContainer();

    projectTypologyContainer = new ProjectTypologyContainer();
    typology = new ProjectTypology("Fixed cost");

    customerContainer = new CustomerContainer();
    customer = new Customer("ISEP","228674498");

    businessSectorContainer = new BusinessSectorContainer();
    businessSector = new BusinessSector("fishing");

    projectDTO = new ProjectDTO("AA001", "Aptoide", "ISEP","228674498", "Fixed cost",
            "fishing");


  }

  @AfterEach
  void tearDown() {
    projectOne = null;
    businessSectorContainer = null;
    businessSector = null;
    projectTypologyContainer = null;
    typology = null;
    projects.clear();
    projectContainer = null;
    projectDTO = null;
    customerContainer = null;
    customer = null;
    company = null;
  }

  @Test
  void ensureThatAccountIsConvertedIntoAccountDTO() {
    // ARRANGE
    Project expected = projectOne;

    // ACT
    Project result = ProjectMapper.getProjectFromDTO(projectDTO,projectTypologyContainer,customerContainer,businessSectorContainer);

    // ASSERT
    assertEquals(expected, result);
  }
}
