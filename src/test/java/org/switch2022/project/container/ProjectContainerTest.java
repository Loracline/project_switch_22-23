package org.switch2022.project.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.dto.ProjectCreationDto;
import org.switch2022.project.dto.ProjectDto;
import org.switch2022.project.dto.UserStoryCreationDto;
import org.switch2022.project.factories.*;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectTypology;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProjectContainerTest {

  /**
   * BeforeEach and AfterEach executes common code before/after running the
   * tests below.
   */

  Project projectOne;
  ProjectCreationDto projectOneDTO, projectTwoDTO;
  ProjectTypology typology;
  Customer customer;
  BusinessSector businessSector;
  ProjectTypologyContainer projectTypologyContainer;
  BusinessSectorContainer businessSectorContainer;
  ProjectContainer projectContainer;
  CustomerContainer customerContainer;
  Company company;
  IFactoryProject factoryProject;
  IFactoryUserStory factoryUserStory;
  IFactoryProductBacklog factoryProductBacklog;



  @BeforeEach
  void setUp() {
    projectOne = new Project("AA001", "Aptoide",
            new Customer("ISEP", "228674498"),
            new ProjectTypology("Fixed cost"),
            new BusinessSector("fishing"));

    projectTypologyContainer = new ProjectTypologyContainer();
    customerContainer = new CustomerContainer();
    businessSectorContainer = new BusinessSectorContainer();

    projectOneDTO = new ProjectCreationDto("AA001", "Aptoide",
            "ISEP", "228674498", "Fixed cost",
            "fishing");
    projectTwoDTO = new ProjectCreationDto("AA002", "Aptoide",
            "ISEP", "228674498", "Fixed cost",
            "fishing");
    factoryProductBacklog= new FactoryProductBacklog();
    factoryProject =  new FactoryProject();
    factoryUserStory= new FactoryUserStory();
    projectContainer = new ProjectContainer();
    projectContainer.registerProject(projectOneDTO, projectTypologyContainer, customerContainer,
            businessSectorContainer,factoryProductBacklog,factoryUserStory,factoryProject);



  }

  @AfterEach
  void tearDown() {
    projectOne = null;
    businessSectorContainer = null;
    businessSector = null;
    projectTypologyContainer = null;
    typology = null;
    projectContainer = null;
    projectOneDTO = null;
    customerContainer = null;
    customer = null;
    company = null;
  }

  /**
   * This test verifies that a new project is registered and added to the
   * container successfully.
   */
  @Test
  void ensureProjectIsRegisteredSuccessfully() {
    // Arrange
    boolean expected = true;

    // Act
    boolean result = projectContainer.registerProject(projectTwoDTO, projectTypologyContainer, customerContainer,
            businessSectorContainer,factoryProductBacklog,factoryUserStory,factoryProject);

    // Assert
    assertEquals(expected, result);
  }

  /**
   * This test verifies that a project is not registered because it already
   * exists in the container.
   */
  @Test
  void ensureProjectIsNotRegisteredBecauseItAlreadyExists() {
    // Arrange
    boolean expected = false;

    // Act
    boolean result = projectContainer.registerProject(projectOneDTO, projectTypologyContainer, customerContainer,
            businessSectorContainer,factoryProductBacklog,factoryUserStory,factoryProject);

    // Assert
    assertEquals(expected, result);
  }

  /**
   * METHOD createUserStory
   * Scenario1: this test creates an userStory in a given project
   * and return true
   */
  @Test
  void ensureUserStoryIsCreatedInAProject(){
    //Arrange
    UserStoryCreationDto userStoryCreationDtoDouble=mock(UserStoryCreationDto.class);
    ProjectDto projectDto= new ProjectDto("P001","Project1","ITV","Panned",
            "Fixed cost","Media");
    ProjectContainer projectContainerTest= new ProjectContainer();
    ProjectCreationDto projectCreationDtoDouble= mock(ProjectCreationDto.class);
    ProjectTypologyContainer projectTypologyContainerDouble=mock(ProjectTypologyContainer.class);
    CustomerContainer costumerContainerDouble= mock(CustomerContainer.class);
    BusinessSectorContainer businessSectorContainerDouble=mock(BusinessSectorContainer.class);
    IFactoryUserStory factoryUserStoryDouble= mock(FactoryUserStory.class);
    IFactoryProductBacklog factoryProductBacklogDouble= mock(FactoryProductBacklog.class);
    Project projectDouble = mock(Project.class);
    IFactoryProject factoryProjectDouble =  mock(FactoryProject.class);
    when (factoryProjectDouble.createProject(any(), any(), any(), any(), any(), any())).thenReturn(projectDouble);
    when(projectDouble.createUserStory(userStoryCreationDtoDouble)).thenReturn(true);
    when (projectDouble.hasProjectCode("P001")).thenReturn(true);
    projectContainerTest.registerProject(projectCreationDtoDouble,projectTypologyContainerDouble,
            costumerContainerDouble,businessSectorContainerDouble,factoryProductBacklogDouble,factoryUserStoryDouble,factoryProjectDouble);
    //Act
    boolean result= projectContainerTest.createUserStory(projectDto,userStoryCreationDtoDouble);
    //Assert
    assertTrue(result);
  }
  /**
   * Scenario2: this test doesn't create an userStory in a given project
   * due to project not be in the container
   */
  @Test
  void ensureUserStoryIsNotCreatedInAProject(){
    //Arrange
    UserStoryCreationDto userStoryCreationDtoDouble=mock(UserStoryCreationDto.class);
    ProjectDto projectDto= new ProjectDto("P001","Project1","ITV","Panned",
            "Fixed cost","Media");
    ProjectContainer projectContainerTest= new ProjectContainer();
    ProjectCreationDto projectCreationDtoDouble= mock(ProjectCreationDto.class);
    ProjectTypologyContainer projectTypologyContainerDouble=mock(ProjectTypologyContainer.class);
    CustomerContainer costumerContainerDouble= mock(CustomerContainer.class);
    BusinessSectorContainer businessSectorContainerDouble=mock(BusinessSectorContainer.class);
    IFactoryUserStory factoryUserStoryDouble= mock(FactoryUserStory.class);
    IFactoryProductBacklog factoryProductBacklogDouble= mock(FactoryProductBacklog.class);
    Project projectDouble = mock(Project.class);
    IFactoryProject factoryProjectDouble =  mock(FactoryProject.class);
    ProjectTypology projectTypologyDouble= mock(ProjectTypology.class);
    Customer costumerDouble=mock(Customer.class);
    BusinessSector businessSectorDouble=mock(BusinessSector.class);
    when (factoryProjectDouble.createProject(projectCreationDtoDouble,costumerDouble,projectTypologyDouble,businessSectorDouble,
            factoryProductBacklogDouble,factoryUserStoryDouble)).thenReturn(projectDouble);
    projectContainerTest.registerProject(projectCreationDtoDouble,projectTypologyContainerDouble,
            costumerContainerDouble,businessSectorContainerDouble,factoryProductBacklogDouble,factoryUserStoryDouble,factoryProjectDouble);
    when(projectDouble.createUserStory(userStoryCreationDtoDouble)).thenReturn(true);
    //Act
    boolean result= projectContainerTest.createUserStory(projectDto,userStoryCreationDtoDouble);
    //Assert
    assertFalse(result);
  }
  /**
   * Scenario3: this test doesn't create an userStory in a given project
   * due to userStory be repeated
   */
  @Test
  void ensureUserStoryIsNotCreatedInAProject_RepeatedUserStory(){
    //Arrange
    UserStoryCreationDto userStoryCreationDtoDouble=mock(UserStoryCreationDto.class);
    ProjectDto projectDto= new ProjectDto("P001","Project1","ITV","Panned",
            "Fixed cost","Media");
    ProjectContainer projectContainerTest= new ProjectContainer();
    ProjectCreationDto projectCreationDtoDouble= mock(ProjectCreationDto.class);
    ProjectTypologyContainer projectTypologyContainerDouble=mock(ProjectTypologyContainer.class);
    CustomerContainer costumerContainerDouble= mock(CustomerContainer.class);
    BusinessSectorContainer businessSectorContainerDouble=mock(BusinessSectorContainer.class);
    IFactoryUserStory factoryUserStoryDouble= mock(FactoryUserStory.class);
    IFactoryProductBacklog factoryProductBacklogDouble= mock(FactoryProductBacklog.class);
    Project projectDouble = mock(Project.class);
    IFactoryProject factoryProjectDouble =  mock(FactoryProject.class);
    ProjectTypology projectTypologyDouble= mock(ProjectTypology.class);
    Customer costumerDouble=mock(Customer.class);
    BusinessSector businessSectorDouble=mock(BusinessSector.class);
    when (factoryProjectDouble.createProject(projectCreationDtoDouble,costumerDouble,projectTypologyDouble,businessSectorDouble,
            factoryProductBacklogDouble,factoryUserStoryDouble)).thenReturn(projectDouble);
    projectContainerTest.registerProject(projectCreationDtoDouble,projectTypologyContainerDouble,
            costumerContainerDouble,businessSectorContainerDouble,factoryProductBacklogDouble,factoryUserStoryDouble,factoryProjectDouble);
    when(projectDouble.createUserStory(userStoryCreationDtoDouble)).thenReturn(false);
    //Act
    boolean result= projectContainerTest.createUserStory(projectDto,userStoryCreationDtoDouble);
    //Assert
    assertFalse(result);
  }
  /**
   * Scenario4: this test doesn't create an userStory in a given project
   * due to project be a null
   */
  @Test
  void ensureUserStoryIsNotCreatedInAProject_ProjectNull(){
    //Arrange
    UserStoryCreationDto userStoryCreationDtoDouble=mock(UserStoryCreationDto.class);
    ProjectDto projectDto= new ProjectDto("P001","Project1","ITV","Panned",
            "Fixed cost","Media");
    ProjectContainer projectContainerTest= new ProjectContainer();
    ProjectCreationDto projectCreationDtoDouble= mock(ProjectCreationDto.class);
    ProjectTypologyContainer projectTypologyContainerDouble=mock(ProjectTypologyContainer.class);
    CustomerContainer costumerContainerDouble= mock(CustomerContainer.class);
    BusinessSectorContainer businessSectorContainerDouble=mock(BusinessSectorContainer.class);
    IFactoryUserStory factoryUserStoryDouble= mock(FactoryUserStory.class);
    IFactoryProductBacklog factoryProductBacklogDouble= mock(FactoryProductBacklog.class);
    IFactoryProject factoryProjectDouble =  mock(FactoryProject.class);
    ProjectTypology projectTypologyDouble= mock(ProjectTypology.class);
    Customer costumerDouble=mock(Customer.class);
    BusinessSector businessSectorDouble=mock(BusinessSector.class);
    when (factoryProjectDouble.createProject(projectCreationDtoDouble,costumerDouble,projectTypologyDouble,businessSectorDouble,
            factoryProductBacklogDouble,factoryUserStoryDouble)).thenReturn(null);
    projectContainerTest.registerProject(projectCreationDtoDouble,projectTypologyContainerDouble,
            costumerContainerDouble,businessSectorContainerDouble,factoryProductBacklogDouble,factoryUserStoryDouble,factoryProjectDouble);
    //Act
    boolean result= projectContainerTest.createUserStory(projectDto,userStoryCreationDtoDouble);
    //Assert
    assertFalse(result);
  }
}