package org.switch2022.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.factories.*;
import org.switch2022.project.utils.Effort;
import org.switch2022.project.utils.Period;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProjectTest {
    /**
     * BeforeEach and AfterEach execute common code before/after running the
     * tests below.
     */
    Project projectOne, projectTwo, projectThree;

    @BeforeEach
    void setUp() {
        projectOne = new Project("AA001", "Aptoide",
                new Customer("John", "228674498"),
                new ProjectTypology("Fixed cost"),
                new BusinessSector("Hunting"));
        projectTwo = new Project("AA002", "Aptoide",
                new Customer("John", "228674498"),
                new ProjectTypology("Fixed cost"),
                new BusinessSector("Hunting"));
        projectThree = new Project("AA001", "Aptoide",
                new Customer("John", "228674498"),
                new ProjectTypology("Fixed cost"),
                new BusinessSector("Hunting"));
    }

    @AfterEach
    void tearDown() {
        projectOne = null;
        projectTwo = null;
        projectThree = null;
    }

    /**
     * Test to ensure the object equals itself
     */
    @Test
    void ensureSameObjectEqualsItself() {
        // Arrange
        Project projectReference = projectOne;
        boolean expected = true;
        boolean result = projectOne.equals(projectReference);
        assertEquals(expected, result);
    }

    /**
     * Test to ensure that two objects are from different classes
     */
    @Test
    void ensureObjectsAreFromDifferentClasses() {
        // Arrange
        Object projectObject = new Object();
        boolean expected = false;
        boolean result = projectOne.equals(projectObject);
        assertEquals(expected, result);
    }

    /**
     * Test to ensure that the object to compare is equal to null
     */
    @Test
    void ensureObjectToCompareIsNull() {
        //Arrange
        Project other = null;
        boolean expected = false;

        //Act
        boolean result = projectOne.equals(other);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to ensure that two objects from the same class are different
     */
    @Test
    void ensureTwoProjectsAreNotEqual() {
        // Arrange
        boolean expected = false;
        boolean result = projectOne.equals(projectTwo);
        assertEquals(expected, result);
    }

    /**
     * Test to ensure that two objects from the same class are equal
     */
    @Test
    void ensureTwoProjectsAreEqual() {
        // Arrange
        Project project = new Project("AA001", "Aptoide", new Customer("john",
                "228674498"),
                new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));

        // Assert
        assertEquals(project, projectOne);
    }

    /**
     * Test to ensure that project code requested from a given project is retrieved
     */
    @Test
    void ensureProjectCodeIsEqual() {
        // Act
        String expected = "AA001";
        String result = projectOne.getProjectCode();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to ensure that project code requested from a given project is not retrieved
     */
    @Test
    void ensureProjectCodeIsNotEqual() {
        // Act
        String expected = "AA002";
        String result = projectOne.getProjectCode();

        // Assert
        assertNotEquals(expected, result);
    }

    /**
     * Test to ensure that project name requested from a given project is retrieved
     */
    @Test
    void ensureProjectNameIsEqual() {
        // Act
        String expected = "Aptoide";
        String result = projectOne.getProjectName();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to ensure that project name requested from a given project is not retrieved
     */
    @Test
    void ensureProjectNameIsNotEqual() {
        // Act
        String expected = "Aptoido";
        String result = projectOne.getProjectName();

        // Assert
        assertNotEquals(expected, result);
    }

    /**
     * Test to ensure that customer requested from a given project is retrieved
     */
    @Test
    void ensureCustomerIsEqual() {
        // Act
        Customer expected = new Customer("john", "228674498");
        Customer result = projectOne.getCustomer();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to ensure that customer requested from a given project is not retrieved
     */
    @Test
    void ensureCustomerIsNotEqual() {
        // Act
        Customer expected = new Customer("Johnny", null);
        Customer result = projectOne.getCustomer();

        // Assert
        assertNotEquals(expected, result);
    }

    /**
     * Test to ensure that status requested from a given project is retrieved
     */
    @Test
    void ensureStatusIsEqual() {
        // Act
        String expected = "planned";
        String result = projectOne.getProjectStatus();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to ensure that status requested from a given project is not retrieved
     */
    @Test
    void ensureStatusIsNotEqual() {
        // Act
        String expected = "finished";
        String result = projectOne.getProjectStatus();

        // Assert
        assertNotEquals(expected, result);
    }

    /**
     * Test to ensure that project typology requested from a given project is retrieved
     */
    @Test
    void ensureProjectTypologyIsEqual() {
        // Act
        ProjectTypology expected = new ProjectTypology("Fixed cost");
        ProjectTypology result = projectOne.getProjectTypology();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to ensure that project typology requested from a given project is not
     * retrieved
     */
    @Test
    void ensureProjectTypologyIsNotEqual() {
        // Act
        ProjectTypology expected = new ProjectTypology("Variable cost");
        ProjectTypology result = projectOne.getProjectTypology();

        // Assert
        assertNotEquals(expected, result);
    }

    /**
     * Test to ensure that business sector requested from a given project is retrieved
     */
    @Test
    void ensureBusinessSectorIsEqual() {
        // Act
        BusinessSector expected = new BusinessSector("Hunting");
        BusinessSector result = projectOne.getBusinessSector();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to ensure that business sector requested from a given project is not retrieved
     */
    @Test
    void ensureBusinessSectorIsNotEqual() {
        // Act
        BusinessSector expected = new BusinessSector("Fishing");
        BusinessSector result = projectOne.getBusinessSector();

        // Assert
        assertNotEquals(expected, result);
    }

    /**
     * Test to check the hashcode when objects are equal and unequal
     */
    @Test
    void testHashCode() {
        // Arrange
        Project objectOne = projectOne;
        Project objectTwo = projectTwo;
        Project objectThree = projectThree;

        // Assert
        // Check that equal objects have the same hash code
        assertEquals(objectOne.hashCode(), objectThree.hashCode());

        // Check that unequal objects have different hash codes
        assertNotEquals(objectOne.hashCode(), objectTwo.hashCode());
    }

    @Test
    void ensureThatProjectIsOpen() {
        //Arrange
        boolean expected = true;

        //Act
        boolean result = projectOne.isProjectOpen();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatProjectIsClosed() {
        //Arrange
        projectOne.setProjectStatus("CLOSED");
        boolean expected = false;

        //Act
        boolean result = projectOne.isProjectOpen();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test sprintDuration GET.
     */
    @Test
    public void testGetSprintDuration() {
        // Arrange
        Project project = projectThree;

        //Act
        int duration = project.getSprintDuration();

        // Assert
        assertEquals(0, duration);
    }

    /**
     * Test sprintDuration SET
     */
    @Test
    public void testSetSprintDurationIsValid() {
        // Arrange
        Project project = projectOne;

        // Assert
        assertTrue(project.setSprintDuration(3));
        assertEquals(3, project.getSprintDuration());
    }

    @Test
    public void testSetSprintDurationIsInvalid() {
        // Arrange
        Project project = projectOne;

        // Assert
        assertFalse(project.setSprintDuration(0));
        assertEquals(0, project.getSprintDuration());
    }

    @Test
    public void testSetSprintDurationIsInvalidIfGreaterThenFive() {
        // Arrange
        Project project = projectOne;

        //Assert
        assertFalse(project.setSprintDuration(5));
        assertEquals(0, project.getSprintDuration());
    }

    //ISOLATION TESTS

    /**
     * Scenario 1: Verifies if it's possible to create a valid instance of
     * {@link Project} with
     * isolated objects.
     * For this test, isolated objects of {@link Customer}, {@link BusinessSector} and
     * {@link ProjectTypology}
     * are created.
     * Then, an instance of {@link Project} is created with the isolated objects and
     * verify if
     * the created
     * instance is not null, indicating that it was created correctly.
     */
    @Test
    public void shouldCreateAValidProjectWithIsolation() {
        // Arrange
        Customer customerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);

        // Act
        Project project = new Project("A001", "Artemis", customerDouble,
                projectTypologyDouble,
                businessSectorDouble);

        // Assert
        assertNotNull(project);
    }

    /**
     * Scenario 2: Verifies if the method {@link Project#equals(Object)} returns {@code
     * true}
     * when compared to the same object.
     * For this test, isolated objects of {@link Customer}, {@link BusinessSector} and
     * {@link ProjectTypology} are created.
     * Then, an instance of {@link Project} is created with the isolated objects and
     * compared to
     * itself using the
     * {@link Project#equals(Object)} method.
     * Expected result:true, indicating that the comparison was successful.
     */
    @Test
    public void shouldReturnTrueEqualsWithSameObjectWithIsolation() {
        // Arrange
        Customer customerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);

        // Act
        Project project = new Project("A001", "Artemis", customerDouble,
                projectTypologyDouble,
                businessSectorDouble);

        boolean isEquals = project.equals(project);

        // Assert
        assertTrue(isEquals);
    }

    /**
     * Scenario 3: Verifies if the method {@link Project#equals(Object)} returns {@code
     * true}
     * when compared to the
     * same type of object and with the same parameters.
     * For this test, isolated objects of {@link Customer}, {@link BusinessSector} and
     * {@link ProjectTypology}
     * are created.
     * Then, two instances of {@link Project} are created with the isolated objects and
     * compared
     * using the
     * {@link Project#equals(Object)} method.
     * Expected result:true, indicating that the comparison was successful.
     */
    @Test
    public void shouldReturnTrueEqualsProjectsWithSameParameters() {
        // Arrange
        Customer customerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);

        // Act
        Project projectDouble = new Project("A001", "Artemis", customerDouble,
                projectTypologyDouble,
                businessSectorDouble);
        Project projectDoubleToCompare = new Project("A001", "Artemis", customerDouble,
                projectTypologyDouble, businessSectorDouble);

        boolean isEquals = projectDouble.equals(projectDoubleToCompare);

        // Assert
        assertTrue(isEquals);
    }

    /**
     * Scenario 4: Verifies if the method {@link Project#equals(Object)} returns {@code
     * false}
     * when compared to the
     * same type of object and with the different parameters.
     * For this test, isolated objects of {@link Customer}, {@link BusinessSector} and
     * {@link ProjectTypology}
     * are created.
     * Then, two instances of {@link Project} are created with the isolated objects and
     * compared
     * using the
     * {@link Project#equals(Object)} method.
     * Expected result:false, indicating that the comparison was successful.
     */
    @Test
    public void shouldReturnFalseEqualsProjectsNotWithSameParameters() {
        // Arrange
        Customer customerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);

        // Act
        Project projectDouble = new Project("A001", "Artemis", customerDouble,
                projectTypologyDouble,
                businessSectorDouble);
        Project projectDoubleToCompare = new Project("A002", "Artemis", customerDouble,
                projectTypologyDouble, businessSectorDouble);

        boolean isEquals = projectDouble.equals(projectDoubleToCompare);

        // Assert
        assertFalse(isEquals);
    }

    /**
     * Scenario 5: Verifies if the method {@link Project#equals(Object)} returns {@code
     * false}
     * when compared to the
     * different type of object.
     * For this test, isolated objects of {@link Customer}, {@link BusinessSector} and
     * {@link ProjectTypology}
     * are created.
     * Then, one instance of {@link Project} is created with the isolated objects and
     * compared to
     * an instance of the
     * {@link Object} with {@link Project#equals(Object)} method.
     * Expected result:false, indicating that the comparison was successful.
     */
    @Test
    public void shouldReturnFalseWithDifferentClasses() {
        // Arrange
        Customer customerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);

        // Act
        Project projectDouble = new Project("A001", "Artemis", customerDouble,
                projectTypologyDouble,
                businessSectorDouble);
        Object projectToCompare = new Object();

        boolean isEquals = projectDouble.equals(projectToCompare);

        // Assert
        assertFalse(isEquals);
    }

    /**
     * Scenario 1: Verifies that the estimateEffortUserStory() method of the Project class
     * can correctly estimate the effort for a user story
     * Expected result: true, indicating that the estimation was successful.
     */
    @Test
    void ensureEstimateEffortUserStorySuccessfully() {
        // Arrange
        IFactorySprintBacklog factorySprintBacklogDouble = new FactorySprintBacklog();
        IFactoryPeriod factoryPeriodDouble = new FactoryPeriod();
        IFactoryProductBacklog factoryProductBacklog = new FactoryProductBacklog();
        IFactoryUserStory factoryUserStory = new FactoryUserStory();
        Project project = new Project("AA001", "Aptoide",
                new Customer("John", "228674498"),
                new ProjectTypology("Fixed cost"),
                new BusinessSector("Hunting"),
                factoryProductBacklog, factoryUserStory);
        UserStoryDto reference = new UserStoryDto("US001", "I want to create a profile",
                "Planned");
        Effort effort = Effort.TWO;
        Sprint sprint = Sprint.createSprint(LocalDate.of(2023, 3, 9),
                3, "S1", factoryPeriodDouble, factorySprintBacklogDouble);
        project.addSprint(sprint);
        sprint.addUserStoryToSprintBacklog(new UserStory.UserStoryBuilder("US001").build());

        // Act
        boolean result = project.estimateEffortUserStory(reference, effort, "S1");

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verifies that the estimateEffortUserStory() method of the Project class
     * can't correctly estimate the effort for a user story
     * Expected result: false, indicating that the estimation was unsuccessful.
     */
    @Test
    void ensureEstimateEffortUserStoryUnsuccessfully() {
        // Arrange
        IFactorySprintBacklog factorySprintBacklogDouble = new FactorySprintBacklog();
        IFactoryPeriod factoryPeriodDouble = new FactoryPeriod();
        IFactoryProductBacklog factoryProductBacklog = new FactoryProductBacklog();
        IFactoryUserStory factoryUserStory = new FactoryUserStory();
        Project project = new Project("AA001", "Aptoide",
                new Customer("John", "228674498"),
                new ProjectTypology("Fixed cost"),
                new BusinessSector("Hunting"),
                factoryProductBacklog, factoryUserStory);
        UserStoryDto reference = new UserStoryDto("US001", "I want to create a profile",
                "Planned");
        Effort effort = Effort.TWO;
        Sprint sprint = Sprint.createSprint(LocalDate.of(2023, 3, 9),
                3, "S1", factoryPeriodDouble, factorySprintBacklogDouble);
        project.addSprint(sprint);
        sprint.addUserStoryToSprintBacklog(new UserStory.UserStoryBuilder("US001").build());

        // Act
        boolean result = project.estimateEffortUserStory(reference, effort, "S2");

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 1: Verifies that a Sprint can be successfully added to the project
     * Expected result: true, indicating that the Sprint was added to the project.
     */
    @Test
    void ensureThatSprintIsSuccessfullyAddedToTheProject() {
        // Arrange
        IFactorySprintBacklog factorySprintBacklogDouble = new FactorySprintBacklog();
        IFactoryPeriod factoryPeriodDouble = new FactoryPeriod();
        IFactoryProductBacklog factoryProductBacklog = new FactoryProductBacklog();
        IFactoryUserStory factoryUserStory = new FactoryUserStory();

        Project project = new Project("AA001", "Aptoide",
                new Customer("John", "228674498"),
                new ProjectTypology("Fixed cost"),
                new BusinessSector("Hunting"),
                factoryProductBacklog, factoryUserStory);

        Sprint sprint = Sprint.createSprint(LocalDate.of(2023, 3, 9),
                3, "S1", factoryPeriodDouble, factorySprintBacklogDouble);

        // Act
        boolean result = project.addSprint(sprint);

        // Assert
        assertTrue(result);

    }

    /**
     * Scenario 2: Verifies that a Sprint with the same number can't be added to the
     * project
     * Expected result: false, indicating that the Sprint was not added to the project.
     */
    @Test
    void ensureThatCannotAddSameSprintTwiceToProject() {
        // Arrange
        IFactorySprintBacklog factorySprintBacklogDouble = new FactorySprintBacklog();
        IFactoryPeriod factoryPeriodDouble = new FactoryPeriod();
        IFactoryProductBacklog factoryProductBacklog = new FactoryProductBacklog();
        IFactoryUserStory factoryUserStory = new FactoryUserStory();

        Project project = new Project("AA001", "Aptoide",
                new Customer("John", "228674498"),
                new ProjectTypology("Fixed cost"),
                new BusinessSector("Hunting"),
                factoryProductBacklog, factoryUserStory);

        Sprint sprint = Sprint.createSprint(LocalDate.of(2023, 3, 9),
                3, "S1", factoryPeriodDouble, factorySprintBacklogDouble);

        // Act
        boolean added = project.addSprint(sprint);
        assertTrue(added);
        boolean addedAgain = project.addSprint(sprint);

        // Assert
        assertFalse(addedAgain);
    }

    /**
     * METHOD getProductBacklog(). This method verifies if a copy of a Product Backlog
     * with list
     * of copies of user stories is correctly returned.
     * <p>
     * Scenario 1: Verifies that the method returns a product backlog successfully.
     */
    @Test
    void ensureThatProductBacklogIsSuccessfullyReturned() {
        //ARRANGE
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryProductBacklog factoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        Customer customerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);

        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);
        when(factoryProductBacklogDouble.createProductBacklog(factoryUserStoryDouble)).thenReturn(productBacklogDouble);

        ProductBacklog productBacklogCopyExpected = mock(ProductBacklog.class);
        when(productBacklogDouble.getProductBacklogCopy()).thenReturn(productBacklogCopyExpected);

        Project project = new Project("AP01", "Artemis", customerDouble,
                projectTypologyDouble,
                businessSectorDouble, factoryProductBacklogDouble,
                factoryUserStoryDouble);

        //ACT
        ProductBacklog result = project.getProductBacklog();

        //ASSERT
        assertEquals(productBacklogCopyExpected, result);
    }

    /**
     * Method getSprintByDate()
     * <br>
     * Scenario 1:
     */
    @Test
    void ensureThatReturnsAnOptionalWithASprint() {
        // Arrange
        Customer customerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);

        IFactoryProductBacklog factoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactorySprintBacklog factorySprintBacklogDouble =
                mock(FactorySprintBacklog.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);

        Period periodDouble = mock(Period.class);

        LocalDate startDate = LocalDate.of(2022, 12, 1);
        when(factoryPeriodDouble.createPeriod(any(LocalDate.class), anyInt())).thenReturn(periodDouble);
        when(periodDouble.isDateEqualOrGreaterThanStartDate(any(LocalDate.class))).thenReturn(true);
        when(periodDouble.isDateEqualOrLowerThanEndDate(any(LocalDate.class))).thenReturn(true);

        LocalDate dateOfInterest = LocalDate.of(2023, 1, 1);

        Project projectToTest = new Project("A001", "Artemis", customerDouble,
                projectTypologyDouble, businessSectorDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble);

        Sprint sprint = Sprint.createSprint(startDate, 3, "SP001",
                factoryPeriodDouble, factorySprintBacklogDouble);

        projectToTest.addSprint(sprint);

        Optional<Sprint> expected = Optional.of(sprint);

        // Act
        Optional<Sprint> sprintOptional = projectToTest.getSprintByDate(dateOfInterest);

        // Assert
        assertEquals(expected, sprintOptional);
    }
}