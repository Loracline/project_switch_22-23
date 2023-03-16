package org.switch2022.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.dto.SprintCreationDto;
import org.switch2022.project.factories.*;
import org.switch2022.project.dto.UserStoryCreationDto;
import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.utils.Effort;
import org.switch2022.project.utils.Period;

import java.util.Optional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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
        IFactoryProductBacklog factoryProductBacklog = mock(FactoryProductBacklog.class);
        IFactoryUserStory factoryUserStory = mock(FactoryUserStory.class);
        Customer customerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        Sprint sprintDouble = mock(Sprint.class);
        IFactoryPeriod factoryPeriod = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklog = mock(FactorySprintBacklog.class);
        IFactorySprint factorySprint = mock(FactorySprint.class);

        Project project = new Project("AA001", "Aptoide", customerDouble, projectTypologyDouble,
                businessSectorDouble, factoryProductBacklog, factoryUserStory, factoryPeriod, factorySprintBacklog,
                factorySprint);
        UserStoryDto userStoryDto = mock(UserStoryDto.class);
        Effort effort = Effort.TWO;

        project.addSprint(sprintDouble);
        when(sprintDouble.isDateWithinPeriod(any())).thenReturn(true);
        when(sprintDouble.estimateEffortUserStory(userStoryDto, effort)).thenReturn(true);

        // Act
        boolean result = project.estimateEffortUserStory(userStoryDto, effort,
                LocalDate.of(2023, 3, 8));

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
        IFactoryProductBacklog factoryProductBacklog = mock(FactoryProductBacklog.class);
        IFactoryUserStory factoryUserStory = mock(FactoryUserStory.class);
        Customer customerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        Sprint sprintDouble = mock(Sprint.class);
        IFactoryPeriod factoryPeriod = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklog = mock(FactorySprintBacklog.class);
        IFactorySprint factorySprint = mock(FactorySprint.class);

        Project project = new Project("AA001", "Aptoide", customerDouble,
                projectTypologyDouble, businessSectorDouble, factoryProductBacklog,
                factoryUserStory, factoryPeriod, factorySprintBacklog, factorySprint);
        UserStoryDto userStoryDto = mock(UserStoryDto.class);

        Effort effort = Effort.TWO;
        project.addSprint(sprintDouble);
        when(sprintDouble.isDateWithinPeriod(any())).thenReturn(false);
        when(sprintDouble.estimateEffortUserStory(userStoryDto, effort)).thenReturn(false);

        // Act
        boolean result = project.estimateEffortUserStory(userStoryDto, effort,
                LocalDate.of(2023, 3, 8));

        // Assert
        assertFalse(result);
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
        IFactoryPeriod factoryPeriod = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklog = mock(FactorySprintBacklog.class);
        IFactorySprint factorySprint = mock(FactorySprint.class);

        Project project = new Project("AP01", "Artemis", customerDouble,
                projectTypologyDouble,
                businessSectorDouble, factoryProductBacklogDouble,
                factoryUserStoryDouble, factoryPeriod, factorySprintBacklog,
                factorySprint);

        //ACT
        ProductBacklog result = project.getProductBacklog();

        //ASSERT
        assertEquals(productBacklogCopyExpected, result);
    }

    /**
     * METHOD createUserStory
     * Scenario 1: Creates a userStory successfully with an empty list of sprints
     * return true
     */
    @Test
    public void ensureThatAnUserStoryIsCreatedSuccessfully_emptySprints() {
        //Arrange
        Customer customerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        IFactoryProductBacklog factoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        UserStoryCreationDto userStoryCreationDtoDouble = mock(UserStoryCreationDto.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);
        IFactoryPeriod factoryPeriod = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklog = mock(FactorySprintBacklog.class);
        IFactorySprint factorySprint = mock(FactorySprint.class);

        when(factoryProductBacklogDouble.createProductBacklog(factoryUserStoryDouble)).thenReturn(productBacklogDouble);
        Project projectToTest = new Project("A001", "Artemis", customerDouble,
                projectTypologyDouble, businessSectorDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryPeriod,
                factorySprintBacklog, factorySprint);

        when(productBacklogDouble.createUserStory(userStoryCreationDtoDouble)).thenReturn(true);

        //Act
        boolean result = projectToTest.createUserStory(userStoryCreationDtoDouble);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Creates a userStory unsuccessfully due to the productBacklog can't
     * create it
     * return false
     */
    @Test
    public void ensureThatAnUserStoryIsCreatedUnsuccessfully_ProductBacklog() {
        //Arrange
        Customer customerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        Sprint sprintDouble = mock(Sprint.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        IFactoryProductBacklog factoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        UserStoryCreationDto userStoryCreationDtoDouble = mock (UserStoryCreationDto.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);
        IFactoryPeriod factoryPeriod = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklog = mock(FactorySprintBacklog.class);
        IFactorySprint factorySprint = mock(FactorySprint.class);
        when(factoryProductBacklogDouble.createProductBacklog(factoryUserStoryDouble)).thenReturn(productBacklogDouble);

        Project projectToTest = new Project("A001", "Artemis", customerDouble,
                projectTypologyDouble, businessSectorDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryPeriod,
                factorySprintBacklog, factorySprint);
        projectToTest.addSprint(sprintDouble);
        when(sprintDouble.hasUserStory(any())).thenReturn(false);

        when(productBacklogDouble.createUserStory(userStoryCreationDtoDouble)).thenReturn(false);

        //Act
        boolean result = projectToTest.createUserStory(userStoryCreationDtoDouble);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: Creates a userStory successfully
     * return true
     */
    @Test
    public void ensureThatAnUserStoryIsCreatedSuccessfully() {
        //Arrange
        Customer customerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        IFactoryProductBacklog factoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        UserStoryCreationDto userStoryCreationDtoDouble = mock(UserStoryCreationDto.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);
        Sprint sprintDouble = mock(Sprint.class);
        IFactoryPeriod factoryPeriod = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklog = mock(FactorySprintBacklog.class);
        IFactorySprint factorySprint = mock(FactorySprint.class);
        when(factoryProductBacklogDouble.createProductBacklog(factoryUserStoryDouble)).thenReturn(productBacklogDouble);

        Project projectToTest = new Project("A001", "Artemis", customerDouble,
                projectTypologyDouble, businessSectorDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryPeriod,
                factorySprintBacklog, factorySprint);
        projectToTest.addSprint(sprintDouble);
        when(sprintDouble.hasUserStory(any())).thenReturn(false);
        when(productBacklogDouble.createUserStory(userStoryCreationDtoDouble)).thenReturn(true);

        //Act
        boolean result = projectToTest.createUserStory(userStoryCreationDtoDouble);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 4: Creates a userStory unsuccessfully due to userStoryCreationDto be null
     * return false
     */
    @Test
    public void ensureThatAnUserStoryIsCreatedUnsuccessfully_userStoryNull() {
        //Arrange
        Customer customerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        IFactoryProductBacklog factoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        UserStoryCreationDto userStoryCreationDto = null;
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);
        Sprint sprintDouble = mock(Sprint.class);
        IFactoryPeriod factoryPeriod = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklog = mock(FactorySprintBacklog.class);
        IFactorySprint factorySprint = mock(FactorySprint.class);

        when(factoryProductBacklogDouble.createProductBacklog(factoryUserStoryDouble)).thenReturn(productBacklogDouble);

        Project projectToTest = new Project("A001", "Artemis", customerDouble,
                projectTypologyDouble, businessSectorDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryPeriod,
                factorySprintBacklog, factorySprint);
        projectToTest.addSprint(sprintDouble);

        //Act
        boolean result = projectToTest.createUserStory(userStoryCreationDto);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 5¬: Creates a userStory unsuccessfully due to be already in a sprint
     * return false
     */
    @Test
    public void ensureThatAnUserStoryIsCreatedUnsuccessfully_userStoryRepeated() {
        //Arrange
        Customer customerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        IFactoryProductBacklog factoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        UserStoryCreationDto userStoryCreationDtoDouble = mock(UserStoryCreationDto.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);
        Sprint sprintDouble = mock(Sprint.class);
        IFactoryPeriod factoryPeriod = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklog = mock(FactorySprintBacklog.class);
        IFactorySprint factorySprint = mock(FactorySprint.class);

        when(factoryProductBacklogDouble.createProductBacklog(factoryUserStoryDouble)).thenReturn(productBacklogDouble);

        Project projectToTest = new Project("A001", "Artemis", customerDouble,
                projectTypologyDouble, businessSectorDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryPeriod,
                factorySprintBacklog, factorySprint);
        projectToTest.addSprint(sprintDouble);
        when(sprintDouble.hasUserStory(any())).thenReturn(true);

        //Act
        boolean result = projectToTest.createUserStory(userStoryCreationDtoDouble);

        //Assert
        assertFalse(result);
    }
    /**
     * Scenario 6: Creates a userStory unsuccessfully with an empty list of sprints
     * return false
     */
    @Test
    public void ensureThatAnUserStoryIsCreatedUnsuccessfully_emptySprints() {
        //Arrange
        Customer customerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        IFactoryProductBacklog factoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        UserStoryCreationDto userStoryCreationDtoDouble = mock(UserStoryCreationDto.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);
        IFactoryPeriod factoryPeriod = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklog = mock(FactorySprintBacklog.class);
        IFactorySprint factorySprint = mock(FactorySprint.class);

        when(factoryProductBacklogDouble.createProductBacklog(factoryUserStoryDouble)).thenReturn(productBacklogDouble);
        Project projectToTest = new Project("A001", "Artemis", customerDouble,
                projectTypologyDouble, businessSectorDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryPeriod,
                factorySprintBacklog, factorySprint);

        when(productBacklogDouble.createUserStory(userStoryCreationDtoDouble)).thenReturn(false);

        //Act
        boolean result = projectToTest.createUserStory(userStoryCreationDtoDouble);

        //Assert
        assertFalse(result);
    }


    /**
     * METHOD getSprintBacklogByDate().
     * returns a Sprint Backlog from Project which Period is within the given date.
     * <br>
     * Scenario 1: an Optional of Sprint Backlog is returned with a copy of the User
     * Stories of the Sprint occurring in the given date.
     */
    @Test
    void ensureThatReturnsAnOptionalWithASprintBacklog() {
        // Arrange
        // Creation of doubles for customer, business sector and project typology.
        Customer customerDouble = mock(Customer.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);

        // Creation of doubles of the Factories needed for testing.
        IFactoryProductBacklog factoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        IFactorySprintBacklog factorySprintBacklogDouble =
                mock(FactorySprintBacklog.class);
        IFactoryUserStory factoryUserStoryDouble = mock(FactoryUserStory.class);
        IFactoryPeriod factoryPeriodDouble = mock(FactoryPeriod.class);
        IFactoryPeriod factoryPeriod = mock(FactoryPeriod.class);
        IFactorySprintBacklog factorySprintBacklog = mock(FactorySprintBacklog.class);
        IFactorySprint factorySprint = mock(FactorySprint.class);

        // Creation of double of the period of the sprint, and determining its
        // behaviour in this test.
        Period periodDouble = mock(Period.class);
        when(factoryPeriodDouble.createPeriod(any(LocalDate.class), anyInt())).thenReturn(periodDouble);
        when(periodDouble.isDateEqualOrGreaterThanStartDate(any(LocalDate.class))).thenReturn(true);
        when(periodDouble.isDateEqualOrLowerThanEndDate(any(LocalDate.class))).thenReturn(true);

        // Creation of the project to test.
        Project projectToTest = new Project("A001", "Artemis", customerDouble,
                projectTypologyDouble, businessSectorDouble,
                factoryProductBacklogDouble, factoryUserStoryDouble, factoryPeriod,
                factorySprintBacklog, factorySprint);

        // Creation of a double of the Sprint Backlog of the sprint of interest.
        SprintBacklog sprintBacklogDouble = mock(SprintBacklog.class);
        when(factorySprintBacklogDouble.createSprintBacklog()).thenReturn(sprintBacklogDouble);

        // Creation of the sprint occurring during the date of interest, and addition
        // of it to the project to test.
        Sprint sprint = Sprint.createSprint(LocalDate.of(2022, 12, 1), 3, "SP001",
                factoryPeriodDouble, factorySprintBacklogDouble);
        projectToTest.addSprint(sprint);

        // Determining that the previous Sprint Backlog will return a list of copied User
        // Stories containing a double of User Story.
        UserStory userStoryDouble = mock(UserStory.class);
        List<UserStory> expectedList = new ArrayList<>();
        expectedList.add(userStoryDouble);
        when(sprintBacklogDouble.getUserStoriesCopy(factoryUserStoryDouble)).thenReturn(expectedList);

        // Creation of the expected Sprint Backlog containing the same double of User
        // Story used previously, and converting it to an object Optional, in case
        // there is no Sprint occurring in the given date (which is not the case tested
        // here).
        SprintBacklog sprintBacklogExpected = new SprintBacklog();
        sprintBacklogExpected.addUserStory(userStoryDouble);
        Optional<SprintBacklog> expected = Optional.of(sprintBacklogExpected);

        // Act
        Optional<SprintBacklog> sprintBacklogOptional =
                projectToTest.getSprintBacklogByDate(LocalDate.of(2023, 1, 1),
                        factoryUserStoryDouble);

        // Assert
        assertEquals(expected, sprintBacklogOptional);
    }

    /**
     * METHOD addUserStoryToSprintBacklog(String userStoryNumber, String sprintNumber)
     * verifies if a User Story is added to the Sprint Backlog and removed from the
     * Product Backlog.
     * <p>
     * Scenario 1: verifies that User Story is added to Sprint Backlog since the User
     * Story and the Sprint are not null objects.
     */
    @Test
    void addUserStoryIsSuccessfullyAddedToSprintBacklog() {
        //Arrange
        //Project creation
        Customer customerDouble = mock(Customer.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        IFactoryProductBacklog iFactoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        IFactoryUserStory iFactoryUserStory = mock(FactoryUserStory.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);
        when(iFactoryProductBacklogDouble.createProductBacklog(iFactoryUserStory)).thenReturn(productBacklogDouble);
        IFactoryPeriod iFactoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog iFactorySprintBacklogDouble =
                mock(FactorySprintBacklog.class);
        IFactorySprint iFactorySprintDouble = mock(FactorySprint.class);

        Project project = new Project("Proj01", "Project Switch", customerDouble,
                projectTypologyDouble,
                businessSectorDouble, iFactoryProductBacklogDouble, iFactoryUserStory,
                iFactoryPeriodDouble, iFactorySprintBacklogDouble, iFactorySprintDouble);

        //Sprint
        Sprint sprintDouble = mock(Sprint.class);
        project.addSprint(sprintDouble);

        //User story
        UserStory userStoryDouble = mock(UserStory.class);
        when(productBacklogDouble.getUserStoryByNumber(any())).
                thenReturn(Optional.ofNullable(userStoryDouble));

        when(sprintDouble.hasSprintNumber(any())).thenReturn(true);
        when(sprintDouble.addUserStoryToSprintBacklog(userStoryDouble)).thenReturn
                (true);
        when(productBacklogDouble.removeUserStory(userStoryDouble)).thenReturn(true);

        //Act
        boolean result = project.addUserStoryToSprintBacklog("US003", "S1");

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: verifies that User Story is not added to Sprint Backlog since the
     * Sprint is a null object.
     */
    @Test
    void addUserStoryIsNotAddedToSprintBacklogBecauseSprintIsNull() {
        //Arrange
        //Project creation
        Customer customerDouble = mock(Customer.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        IFactoryProductBacklog iFactoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        IFactoryUserStory iFactoryUserStory = mock(FactoryUserStory.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);
        when(iFactoryProductBacklogDouble.createProductBacklog(iFactoryUserStory)).thenReturn(productBacklogDouble);
        IFactoryPeriod iFactoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog iFactorySprintBacklogDouble =
                mock(FactorySprintBacklog.class);
        IFactorySprint iFactorySprintDouble = mock(FactorySprint.class);

        Project project = new Project("Proj01", "Project Switch", customerDouble,
                projectTypologyDouble,
                businessSectorDouble, iFactoryProductBacklogDouble, iFactoryUserStory,
                iFactoryPeriodDouble, iFactorySprintBacklogDouble, iFactorySprintDouble);

        //Sprint
        Sprint sprintDouble = mock(Sprint.class);
        project.addSprint(sprintDouble);

        when(sprintDouble.hasSprintNumber(any())).thenReturn(false);

        //User story
        UserStory userStoryDouble = mock(UserStory.class);
        when(productBacklogDouble.getUserStoryByNumber(any())).
                thenReturn(Optional.ofNullable(userStoryDouble));

        //Act
        boolean result = project.addUserStoryToSprintBacklog("US003", "S1");

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: verifies that User Story is not added to Sprint Backlog since the
     * User Story is a null object.
     */
    @Test
    void addUserStoryIsNotAddedToSprintBacklogBecauseUserStoryIsNull() {
        //Arrange
        //Project creation
        Customer customerDouble = mock(Customer.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        IFactoryProductBacklog iFactoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        IFactoryUserStory iFactoryUserStory = mock(FactoryUserStory.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);
        when(iFactoryProductBacklogDouble.createProductBacklog(iFactoryUserStory)).thenReturn(productBacklogDouble);
        IFactoryPeriod iFactoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog iFactorySprintBacklogDouble =
                mock(FactorySprintBacklog.class);
        IFactorySprint iFactorySprintDouble = mock(FactorySprint.class);

        Project project = new Project("Proj01", "Project Switch", customerDouble,
                projectTypologyDouble,
                businessSectorDouble, iFactoryProductBacklogDouble, iFactoryUserStory,
                iFactoryPeriodDouble, iFactorySprintBacklogDouble, iFactorySprintDouble);

        //Sprint
        Sprint sprintDouble = mock(Sprint.class);
        project.addSprint(sprintDouble);

        //User story
        UserStory userStoryDouble = mock(UserStory.class);
        when(userStoryDouble.hasUserStoryNumber(any())).thenReturn(false);

        when(sprintDouble.hasSprintNumber(any())).thenReturn(true);

        //Act
        boolean result = project.addUserStoryToSprintBacklog("US003", "S1");

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD addSprint(sprint)
     * adds a Sprint to the list of Sprints of the Project
     * <p>
     * Scenario 1: verify if a Sprint is added to the list os Sprints if it is not
     * already there. Should return TRUE.
     */
    @Test
    void ensureThatSprintIsSuccessfullyAddedToSprintsList() {
        //Arrange
        Customer customerDouble = mock(Customer.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        IFactoryProductBacklog iFactoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        IFactoryUserStory iFactoryUserStory = mock(FactoryUserStory.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);
        when(iFactoryProductBacklogDouble.createProductBacklog(iFactoryUserStory)).thenReturn(productBacklogDouble);
        IFactoryPeriod iFactoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog iFactorySprintBacklogDouble =
                mock(FactorySprintBacklog.class);
        IFactorySprint iFactorySprintDouble = mock(FactorySprint.class);

        Project project = new Project("Proj01", "Project Switch", customerDouble,
                projectTypologyDouble,
                businessSectorDouble, iFactoryProductBacklogDouble, iFactoryUserStory,
                iFactoryPeriodDouble, iFactorySprintBacklogDouble, iFactorySprintDouble);

        Sprint sprintDouble = mock(Sprint.class);
        Sprint sprintDoubleOther = mock(Sprint.class);
        project.addSprint(sprintDoubleOther);

        //Act
        boolean result = project.addSprint(sprintDouble);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: verify if a Sprint is added to the list os Sprints because the list
     * is empty. Should return FALSE.
     */
    @Test
    void ensureSprintIsSuccessfullyAddedToSprintsListBecauseListIsEmpty() {
        //Arrange
        Customer customerDouble = mock(Customer.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        IFactoryProductBacklog iFactoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        IFactoryUserStory iFactoryUserStory = mock(FactoryUserStory.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);
        when(iFactoryProductBacklogDouble.createProductBacklog(iFactoryUserStory)).thenReturn(productBacklogDouble);
        IFactoryPeriod iFactoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog iFactorySprintBacklogDouble =
                mock(FactorySprintBacklog.class);
        IFactorySprint iFactorySprintDouble = mock(FactorySprint.class);

        Project project = new Project("Proj01", "Project Switch", customerDouble,
                projectTypologyDouble,
                businessSectorDouble, iFactoryProductBacklogDouble, iFactoryUserStory,
                iFactoryPeriodDouble, iFactorySprintBacklogDouble, iFactorySprintDouble);

        Sprint sprintDouble = mock(Sprint.class);

        //Act
        boolean result = project.addSprint(sprintDouble);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 3: verify if a Sprint is not added to the list os Sprints because it is
     * already there. Should return FALSE.
     */
    @Test
    void ensureSprintIsNotAddedToSprintsListBecauseItAlreadyExists() {
        //Arrange
        Customer customerDouble = mock(Customer.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        IFactoryProductBacklog iFactoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        IFactoryUserStory iFactoryUserStory = mock(FactoryUserStory.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);
        when(iFactoryProductBacklogDouble.createProductBacklog(iFactoryUserStory)).thenReturn(productBacklogDouble);
        IFactoryPeriod iFactoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog iFactorySprintBacklogDouble =
                mock(FactorySprintBacklog.class);
        IFactorySprint iFactorySprintDouble = mock(FactorySprint.class);

        Project project = new Project("Proj01", "Project Switch", customerDouble,
                projectTypologyDouble,
                businessSectorDouble, iFactoryProductBacklogDouble, iFactoryUserStory,
                iFactoryPeriodDouble, iFactorySprintBacklogDouble, iFactorySprintDouble);

        Sprint sprintDouble = mock(Sprint.class);
        project.addSprint(sprintDouble);

        //Act
        boolean result = project.addSprint(sprintDouble);

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD createSprint(sprint)
     * Scenario 1: verify if a Sprint is created and added to the list os Sprints because Sprint List is empty.
     * should return true.
     */
    @Test
    void ensureSprintIsCreatedAndAddedToTheList_EmptyList() {
        //Arrange
        LocalDate startDate = mock(LocalDate.class);
        Customer customerDouble = mock(Customer.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        IFactoryProductBacklog iFactoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        IFactoryUserStory iFactoryUserStory = mock(FactoryUserStory.class);
        IFactoryPeriod iFactoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog iFactorySprintBacklogDouble =
                mock(FactorySprintBacklog.class);
        IFactorySprint iFactorySprintDouble = mock(FactorySprint.class);
        Project project = new Project("Proj01", "Project Switch", customerDouble,
                projectTypologyDouble,
                businessSectorDouble, iFactoryProductBacklogDouble, iFactoryUserStory,
                iFactoryPeriodDouble, iFactorySprintBacklogDouble, iFactorySprintDouble);
        SprintCreationDto sprintCreationDtoDouble = mock(SprintCreationDto.class);
        //ACT
        boolean result = project.createSprint(sprintCreationDtoDouble);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify if a Sprint is created but not added to the list of Sprints because Period not valid.
     * it should return false.
     */
    @Test
    void ensureSprintIsCreatedButNotAddedToTheList() {
        //Arrange
        LocalDate startDate = mock(LocalDate.class);
        Customer customerDouble = mock(Customer.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        IFactoryProductBacklog iFactoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        IFactoryUserStory iFactoryUserStory = mock(FactoryUserStory.class);
        IFactoryPeriod iFactoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog iFactorySprintBacklogDouble =
                mock(FactorySprintBacklog.class);
        IFactorySprint iFactorySprintDouble = mock(FactorySprint.class);
        Sprint sprintDouble = mock(Sprint.class);
        Sprint sprintDoubleTwo = mock(Sprint.class);
        when(iFactorySprintDouble.createSprint(startDate, 3, "S001", iFactoryPeriodDouble,
                iFactorySprintBacklogDouble)).thenReturn(sprintDouble);
        Project project = new Project("Proj01", "Project Switch", customerDouble,
                projectTypologyDouble,
                businessSectorDouble, iFactoryProductBacklogDouble, iFactoryUserStory,
                iFactoryPeriodDouble, iFactorySprintBacklogDouble, iFactorySprintDouble);
        SprintCreationDto sprintCreationDtoDouble = mock(SprintCreationDto.class);
        project.addSprint(sprintDoubleTwo);
        when(sprintDoubleTwo.isPeriodNotOverlapping(any())).thenReturn(false);
        //ACT
        boolean result = project.createSprint(sprintCreationDtoDouble);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: Verify if a Sprint is created and added to the list of Sprints.
     * it should return true.
     */
    @Test
    void ensureSprintIsCreatedAndAddedToTheList() {
        //Arrange
        LocalDate startDate = mock(LocalDate.class);
        Customer customerDouble = mock(Customer.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        IFactoryProductBacklog iFactoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        IFactoryUserStory iFactoryUserStory = mock(FactoryUserStory.class);
        IFactoryPeriod iFactoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog iFactorySprintBacklogDouble =
                mock(FactorySprintBacklog.class);
        IFactorySprint iFactorySprintDouble = mock(FactorySprint.class);
        Sprint sprintDouble = mock(Sprint.class);
        Sprint sprintDoubleTwo = mock(Sprint.class);
        when(iFactorySprintDouble.createSprint(startDate, 3, "S001", iFactoryPeriodDouble,
                iFactorySprintBacklogDouble)).thenReturn(sprintDouble);
        Project project = new Project("Proj01", "Project Switch", customerDouble,
                projectTypologyDouble,
                businessSectorDouble, iFactoryProductBacklogDouble, iFactoryUserStory,
                iFactoryPeriodDouble, iFactorySprintBacklogDouble, iFactorySprintDouble);
        SprintCreationDto sprintCreationDtoDouble = mock(SprintCreationDto.class);
        project.addSprint(sprintDoubleTwo);
        when(sprintDoubleTwo.isPeriodNotOverlapping(any())).thenReturn(true);
        //ACT
        boolean result = project.createSprint(sprintCreationDtoDouble);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 4: Verify if a Sprint is created and not added to the list of Sprints because Period is not valid and
     * Sprint is already in the Sprint List.
     * it should return false.
     */
    @Test
    void ensureSprintIsCreatedButNotAddedToTheList_PeriodNotValidAndSprintAlreadyInTheList() {
        //Arrange
        LocalDate startDate = mock(LocalDate.class);
        Customer customerDouble = mock(Customer.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        IFactoryProductBacklog iFactoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        IFactoryUserStory iFactoryUserStory = mock(FactoryUserStory.class);
        IFactoryPeriod iFactoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog iFactorySprintBacklogDouble =
                mock(FactorySprintBacklog.class);
        IFactorySprint iFactorySprintDouble = mock(FactorySprint.class);
        Sprint sprintDouble = mock(Sprint.class);
        when(iFactorySprintDouble.createSprint(startDate, 3, "S001", iFactoryPeriodDouble,
                iFactorySprintBacklogDouble)).thenReturn(sprintDouble);
        Project project = new Project("Proj01", "Project Switch", customerDouble,
                projectTypologyDouble,
                businessSectorDouble, iFactoryProductBacklogDouble, iFactoryUserStory,
                iFactoryPeriodDouble, iFactorySprintBacklogDouble, iFactorySprintDouble);
        SprintCreationDto sprintCreationDtoDouble = mock(SprintCreationDto.class);
        project.addSprint(sprintDouble);
        when(sprintDouble.isPeriodNotOverlapping(any())).thenReturn(false);
        //ACT
        boolean result = project.createSprint(sprintCreationDtoDouble);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: Verify if a Sprint is created but not added to the List because it already exists there.
     * it should return false.
     */
    @Test
    void ensureSprintIsCreatedAndNotAddedToTheListBecauseItAlreadyExists() {
        //Arrange
        LocalDate startDate = mock(LocalDate.class);
        Customer customerDouble = mock(Customer.class);
        ProjectTypology projectTypologyDouble = mock(ProjectTypology.class);
        BusinessSector businessSectorDouble = mock(BusinessSector.class);
        IFactoryProductBacklog iFactoryProductBacklogDouble =
                mock(FactoryProductBacklog.class);
        IFactoryUserStory iFactoryUserStory = mock(FactoryUserStory.class);
        IFactoryPeriod iFactoryPeriodDouble = mock(FactoryPeriod.class);
        IFactorySprintBacklog iFactorySprintBacklogDouble =
                mock(FactorySprintBacklog.class);
        IFactorySprint iFactorySprintDouble = mock(FactorySprint.class);
        Sprint sprintDoubleTwo = mock(Sprint.class);
        when(iFactorySprintDouble.createSprint(startDate, 3, "S001", iFactoryPeriodDouble,
                iFactorySprintBacklogDouble)).thenReturn(sprintDoubleTwo);
        Project project = new Project("Proj01", "Project Switch", customerDouble,
                projectTypologyDouble,
                businessSectorDouble, iFactoryProductBacklogDouble, iFactoryUserStory,
                iFactoryPeriodDouble, iFactorySprintBacklogDouble, iFactorySprintDouble);
        SprintCreationDto sprintCreationDtoDouble = mock(SprintCreationDto.class);
        project.addSprint(sprintDoubleTwo);
        when(sprintCreationDtoDouble.getSprintNumber()).thenReturn("S001");
        when(sprintDoubleTwo.isPeriodNotOverlapping(any())).thenReturn(false);
        //ACT
        boolean result = project.createSprint(sprintCreationDtoDouble);
        //Assert
        assertFalse(result);
    }

}