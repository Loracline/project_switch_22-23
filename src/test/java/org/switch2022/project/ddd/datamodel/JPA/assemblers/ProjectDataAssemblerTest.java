package org.switch2022.project.ddd.datamodel.JPA.assemblers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.datamodel.JPA.ProductBacklogJpa;
import org.switch2022.project.ddd.datamodel.JPA.ProjectJpa;
import org.switch2022.project.ddd.domain.model.project.IFactoryProject;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.value_object.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        classes = ProjectDomainDataAssembler.class)
class ProjectDataAssemblerTest {
    @InjectMocks
    ProjectDomainDataAssembler projectDataAssembler;
    @MockBean
    IFactoryProject factoryProject;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    /**
     * Method:toData
     */

    @Test
    void ensureThatProjectJpaIsCreatedSuccessfully() {
        //Arrange
        List<String> userStories = new ArrayList<>();
        userStories.add("p001_us001");
        userStories.add("p001_us002");
        UsId usIdDouble = mock(UsId.class);
        UsId usIdDoubleTwo = mock(UsId.class);
        when(usIdDouble.getUserStoryId()).thenReturn("p001_us001");
        when(usIdDoubleTwo.getUserStoryId()).thenReturn("p001_us002");

        List<UsId> userStoriesId = new ArrayList<>();
        userStoriesId.add(usIdDouble);
        userStoriesId.add(usIdDoubleTwo);

        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb001", userStories);
        ProjectJpa expected = new ProjectJpa("P001", 300.0, "ISEP", "Project",
                "planned", 3, "2021-02-10", "2030-03-09", 2,
                "bs001", "2576542324", "pt001", productBacklogJpa);
        Project projectDouble = mock(Project.class);
        when(projectDouble.getProjectCode()).thenReturn("P001");
        when(projectDouble.getBudget()).thenReturn(300.0);
        when(projectDouble.getProjectName()).thenReturn("ISEP");
        when(projectDouble.getDescription()).thenReturn("Project");
        when(projectDouble.getProjectStatus()).thenReturn("planned");
        when(projectDouble.getPlannedSprints()).thenReturn(3);
        when(projectDouble.getStartDate()).thenReturn("2021-02-10");
        when(projectDouble.getEndDate()).thenReturn("2030-03-09");
        Optional<SprintDuration> optionalSprintDuration = Optional.of(new SprintDuration(2));
        when(projectDouble.getSprintDuration()).thenReturn(optionalSprintDuration);
        when(projectDouble.getBusinessSectorId()).thenReturn("bs001");
        when(projectDouble.getCustomerTaxID()).thenReturn("2576542324");
        when(projectDouble.getProjectTypologyId()).thenReturn("pt001");
        when(projectDouble.getProductBacklog()).thenReturn(userStoriesId);
        when(projectDouble.getProductBacklogId()).thenReturn("p001_pb001");

        //Act
        ProjectJpa result = projectDataAssembler.toData(projectDouble);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method:toData
     * scenario: sprint duration is a null
     */

    @Test
    void ensureThatProjectJpaIsCreatedSuccessfully_sprintDurationNull() {
        //Arrange
        List<String> userStories = new ArrayList<>();
        userStories.add("p001_us001");
        userStories.add("p001_us002");
        UsId usIdDouble = mock(UsId.class);
        UsId usIdDoubleTwo = mock(UsId.class);
        when(usIdDouble.getUserStoryId()).thenReturn("p001_us001");
        when(usIdDoubleTwo.getUserStoryId()).thenReturn("p001_us002");

        List<UsId> userStoriesId = new ArrayList<>();
        userStoriesId.add(usIdDouble);
        userStoriesId.add(usIdDoubleTwo);

        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb001", userStories);
        ProjectJpa expected = new ProjectJpa("P001", 300.0, "ISEP", "Project",
                "planned", 3, "2021-02-10", "2030-03-09", 0,
                "bs001", "2576542324", "pt001", productBacklogJpa);
        Project projectDouble = mock(Project.class);
        when(projectDouble.getProjectCode()).thenReturn("P001");
        when(projectDouble.getBudget()).thenReturn(300.0);
        when(projectDouble.getProjectName()).thenReturn("ISEP");
        when(projectDouble.getDescription()).thenReturn("Project");
        when(projectDouble.getProjectStatus()).thenReturn("planned");
        when(projectDouble.getPlannedSprints()).thenReturn(3);
        when(projectDouble.getStartDate()).thenReturn("2021-02-10");
        when(projectDouble.getEndDate()).thenReturn("2030-03-09");
        Optional<SprintDuration> optionalSprintDuration = Optional.empty();
        when(projectDouble.getSprintDuration()).thenReturn(optionalSprintDuration);
        when(projectDouble.getBusinessSectorId()).thenReturn("bs001");
        when(projectDouble.getCustomerTaxID()).thenReturn("2576542324");
        when(projectDouble.getProjectTypologyId()).thenReturn("pt001");
        when(projectDouble.getProductBacklog()).thenReturn(userStoriesId);
        when(projectDouble.getProductBacklogId()).thenReturn("p001_pb001");

        //Act
        ProjectJpa result = projectDataAssembler.toData(projectDouble);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method:toDomain
     */
    @Test
    void ensureThatProjectIsCreatedSuccessfully() {
        //Arrang
        Project expected = mock(Project.class);
        Name nameDouble = new Name("ISEP");
        Description descriptionDouble = new Description("Project");
        BusinessSectorId businessSectorIdDouble = new BusinessSectorId(1);
        TaxId taxIdDouble = new TaxId("2576542324");
        ProjectTypologyId projectTypologyIdDouble = new ProjectTypologyId(1);
        when(factoryProject.createProject(1, nameDouble, descriptionDouble, businessSectorIdDouble, taxIdDouble,
                projectTypologyIdDouble)).thenReturn(expected);
        expected.setProjectStatus(ProjectStatus.INCEPTION);
        expected.isBudgetAssigned(new Budget(BigDecimal.valueOf(300)));
        expected.isNumberOfPlannedSprintsDefined(new NumberOfPlannedSprints(3));
        expected.setSprintDuration(3);
        expected.isPeriodAssigned(new Period(LocalDate.parse("2021-02-10"), LocalDate.parse("2030-03-09")));
        expected.addUserStory(0, new UsId("P001", "us001"));
        expected.addUserStory(0, new UsId("P001", "us002"));
        List<String> userStories = new ArrayList<>();
        userStories.add("p001_us001");
        userStories.add("p001_us002");
        ProductBacklogJpa productBacklogJpa = mock(ProductBacklogJpa.class);
        ProjectJpa projectJpa = mock(ProjectJpa.class);
        when(projectJpa.getProjectName()).thenReturn("ISEP");
        when(projectJpa.getDescription()).thenReturn("Project");
        when(projectJpa.getProjectCode()).thenReturn("P001");
        when(projectJpa.getBusinessSectorId()).thenReturn("bs001");
        when(projectJpa.getCustomerTaxId()).thenReturn("2576542324");
        when(projectJpa.getProjectTypologyId()).thenReturn("pt001");
        when(projectJpa.getBudget()).thenReturn(300.0);
        when(projectJpa.getProjectStatus()).thenReturn("INCEPTION");
        when(projectJpa.getNumberOfPlannedSprints()).thenReturn(3);
        when(projectJpa.getStartDate()).thenReturn("2021-02-10");
        when(projectJpa.getEndDate()).thenReturn("2030-03-09");
        when(projectJpa.getSprintDuration()).thenReturn(3);
        when(projectJpa.getProductBacklog()).thenReturn(productBacklogJpa);
        when(productBacklogJpa.getUserStories()).thenReturn(userStories);
        when(productBacklogJpa.getProductBacklogId()).thenReturn("p001_pb001");



        //Act
        Project result = projectDataAssembler.toDomain(projectJpa);
        //Assert
        assertEquals(expected, result);
    }

}