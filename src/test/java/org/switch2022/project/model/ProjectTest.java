package org.switch2022.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.factories.*;
import org.switch2022.project.utils.Effort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.switch2022.project.model.UserStory.createUserStory;

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

    /**
     * Tests the ensureEffortIsTheSameForTwoDifferentUserStories() method of the
     * SprintTest class with
     * two different UserStory objects and the same Effort value.
     * + Expected result: the effort of both UserStories should be equal to the
     * specified Effort value.
     */
    @Test
    void ensureEffortIsTheSameForTwoDifferentUserStories() {
        // Arrange
        UserStory userStoryOne = createUserStory("US001", "Manager",
                "I want to create a profile");
        UserStory userStoryTwo = createUserStory("US002", "Manager",
                "I want to create a project");

        // Act
        userStoryOne.setEffort(Effort.TWO);
        userStoryTwo.setEffort(Effort.TWO);

        // Assert
        assertEquals(userStoryOne.getEffort(), userStoryTwo.getEffort());
    }


    //ISOLATION TESTS

    /**
     * Scenario 1: Verifies if it's possible to create a valid instance of
     * {@link Project} with isolated objects.
     * For this test, isolated objects of {@link Customer}, {@link BusinessSector} and
     * {@link ProjectTypology}
     * are created.
     * Then, an instance of {@link Project} is created with the isolated objects and
     * verify if the created
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
                projectTypologyDouble, businessSectorDouble);

        // Assert
        assertNotNull(project);
    }

    /**
     * Scenario 2: Verifies if the method {@link Project#equals(Object)} returns {@code
     * true} when compared to the same object.
     * For this test, isolated objects of {@link Customer}, {@link BusinessSector} and
     * {@link ProjectTypology} are created.
     * Then, an instance of {@link Project} is created with the isolated objects and
     * compared to itself using the
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
                projectTypologyDouble, businessSectorDouble);

        boolean isEquals = project.equals(project);

        // Assert
        assertTrue(isEquals);
    }

    /**
     * Scenario 3: Verifies if the method {@link Project#equals(Object)} returns {@code
     * true} when compared to the
     * same type of object and with the same parameters.
     * For this test, isolated objects of {@link Customer}, {@link BusinessSector} and
     * {@link ProjectTypology}
     * are created.
     * Then, two instances of {@link Project} are created with the isolated objects and
     * compared using the
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
     * false} when compared to the
     * same type of object and with the different parameters.
     * For this test, isolated objects of {@link Customer}, {@link BusinessSector} and
     * {@link ProjectTypology}
     * are created.
     * Then, two instances of {@link Project} are created with the isolated objects and
     * compared using the
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
     * false} when compared to the
     * different type of object.
     * For this test, isolated objects of {@link Customer}, {@link BusinessSector} and
     * {@link ProjectTypology}
     * are created.
     * Then, one instance of {@link Project} is created with the isolated objects and
     * compared to an instance of the
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

        Project project = new Project("Proj01", "Project Switch", customerDouble,
                projectTypologyDouble,
                businessSectorDouble, iFactoryProductBacklogDouble, iFactoryUserStory);

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

        Project project = new Project("Proj01", "Project Switch", customerDouble,
                projectTypologyDouble,
                businessSectorDouble, iFactoryProductBacklogDouble, iFactoryUserStory);

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

        Project project = new Project("Proj01", "Project Switch", customerDouble,
                projectTypologyDouble,
                businessSectorDouble, iFactoryProductBacklogDouble, iFactoryUserStory);

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

        Project project = new Project("Proj01", "Project Switch", customerDouble,
                projectTypologyDouble,
                businessSectorDouble, iFactoryProductBacklogDouble, iFactoryUserStory);

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

        Project project = new Project("Proj01", "Project Switch", customerDouble,
                projectTypologyDouble,
                businessSectorDouble, iFactoryProductBacklogDouble, iFactoryUserStory);

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

        Project project = new Project("Proj01", "Project Switch", customerDouble,
                projectTypologyDouble,
                businessSectorDouble, iFactoryProductBacklogDouble, iFactoryUserStory);

        Sprint sprintDouble = mock(Sprint.class);
        project.addSprint(sprintDouble);

        //Act
        boolean result = project.addSprint(sprintDouble);

        //Assert
        assertFalse(result);
    }
}