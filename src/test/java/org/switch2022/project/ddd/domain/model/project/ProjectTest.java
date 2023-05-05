package org.switch2022.project.ddd.domain.model.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectTest {
    /**
     * BeforeEach and AfterEach execute common code before/after running the
     * tests below.
     */

    Code projectCodeOne;
    Project projectOne, projectTwo, projectThree, projectFour;
    Name projectName;
    Description description;
    BusinessSectorId businessSectorId;
    TaxId customerTaxId;
    ProjectTypologyId projectTypologyId;


    @BeforeEach
    void setUp() {

        //Code
        projectCodeOne = new Code(1);

        //Name
        projectName = mock(Name.class);

        //Description
        description = mock(Description.class);

        //Business Sector
        businessSectorId = mock(BusinessSectorId.class);

        //Customer
        customerTaxId = mock(TaxId.class);

        //Project Typology
        projectTypologyId = mock(ProjectTypologyId.class);


        //Project
        projectOne = new Project(1, projectName, description, businessSectorId, customerTaxId, projectTypologyId);
        projectTwo = new Project(2, projectName, description, businessSectorId, customerTaxId, projectTypologyId);
        projectThree = new Project(3, projectName, description, businessSectorId, customerTaxId, projectTypologyId);
        projectFour = new Project(1, projectName, description, businessSectorId, customerTaxId, projectTypologyId);
    }

    @AfterEach
    void tearDown() {
        projectCodeOne = null;
        projectOne = null;
        projectTwo = null;
        projectThree = null;
        projectFour = null;
    }

    /**
     * Method: equals()
     * Scenario 01: Test to ensure the object equals itself
     */
    @Test
    void ensureSameObjectEqualsItself() {
        // Arrange
        Project projectReference = projectOne;
        boolean expected = true;

        //Act
        boolean result = projectOne.equals(projectReference);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 02:Test to ensure that two objects are from different classes
     */
    @Test
    void ensureObjectsAreFromDifferentClasses() {
        // Arrange
        Object projectObject = new Object();
        boolean expected = false;

        //Act
        boolean result = projectOne.equals(projectObject);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 03: Test to ensure that the object to compare is equal to null
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
     * Method: equals()
     * Scenario 04: Test to ensure that two objects from the same class are different
     */
    @Test
    void ensureTwoProjectsAreNotEqual() {
        // Arrange
        boolean expected = false;
        //Act
        boolean result = projectOne.equals(projectTwo);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method:hashCode()
     * Scenario 01:Two objects with the different code and different hash codes are two different objects
     */
    @Test
    void ensureNoTwoProjectsHaveTheSameHashCode() {

        //Assert
        assertNotEquals(projectOne.hashCode(), projectTwo.hashCode());
    }

    /**
     * Method: sameIdentityAs()
     * Scenario 01: make sure the two objects are the same by having the same attributes.
     * Expected result: true.
     */
    @Test
    void ensureTheTwoObjectsAreTheSameByHavingTheSameAttributes() {
        //Arrange
        Project projectOne = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        Project projectTwo = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);

        //Act
        boolean result = projectOne.sameIdentityAs(projectTwo);

        //Assert
        assertTrue(result);
    }

    /**
     * Method: sameIdentityAs()
     * Scenario 02: make sure the two objects are not the same because they have different attributes.
     * Expected result: false.
     */
    @Test
    void ensureTheTwoObjectsAreNotTheSameBecauseTheyHaveDifferentAttributes() {
        //Arrange
        Project projectOne = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        Project projectTwo = new Project(2, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);

        //Act
        boolean result = projectOne.sameIdentityAs(projectTwo);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: Verify that two Projects are not the same because one of them is null.
     */
    @Test
    void ensureTheTwoObjectsAreNotTheSameBecauseOneOfThemIsNull() {
        //Arrange
        Project projectOne = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        Project projectTwo = null;

        //Act
        boolean result = projectOne.sameIdentityAs(projectTwo);

        //Assert
        assertFalse(result);
    }

    /**
     * Method: getProjectCode().
     * Scenario 01: test to ensure that project code requested from a given project is retrieved.
     */
    @Test
    public void ensureProjectCodeIsEqual() {
        //Arrange
        String expected = projectCodeOne.getCode();

        //Act
        String result = projectOne.getProjectCode();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: getProjectCode().
     * Scenario 02: test to ensure that the requested project code is not the same as another project.
     */
    @Test
    public void ensureProjectCodeIsNotEqual() {
        //Arrange
        String expected = projectCodeOne.getCode();

        //Act
        String result = projectTwo.getProjectCode();

        //Assert
        assertNotEquals(expected, result);
    }

    /**
     * Method: hasProjectCode()
     * Scenario 01: make sure they have the same code
     */
    @Test
    public void makeSureTheyHaveTheSameCode() {
        //Arrange
        Code codeOne = projectCodeOne;
        //Act
        boolean result = projectOne.hasProjectCode(codeOne);
        //Assert
        assertTrue(result);

    }

    /**
     * Method: hasProjectCode()
     * Scenario 02: make sure they have different codes
     */
    @Test
    public void makeSureTheyHaveDifferentCodes() {
        //Arrange
        Code codeOne = projectCodeOne;
        //Act
        boolean result = projectTwo.hasProjectCode(codeOne);
        //Assert
        assertFalse(result);

    }


    //ISOLATION TESTS

    /**
     * Scenario 01: Verifies if it's possible to create a valid instance of
     * Project with
     * isolated objects.
     */
    @Test
    public void shouldCreateAValidProjectWithIsolation() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);

        // Assert
        assertNotNull(project);
    }

    /**
     * Method: equals()
     * Scenario 02: Verifies if the method equals() returns
     * true, when compared to the same object.
     */
    @Test
    public void shouldReturnTrueEqualsWithSameObjectWithIsolation() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);


        // Act
        boolean isEquals = project.equals(project);

        // Assert
        assertTrue(isEquals);
    }

    /**
     * Method: equals()
     * Scenario 03: Verifies if the method equals() returns
     * true, when compared to the
     * same type of object and with the same parameters.
     */
    @Test
    public void shouldReturnTrueEqualsProjectsWithSameParameters() {
        // Arrange
        Project projectDouble = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        Project projectDoubleToCompare = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);

        //Act
        boolean isEquals = projectDouble.equals(projectDoubleToCompare);

        // Assert
        assertTrue(isEquals);
    }

    /**
     * Method: equals()
     * Scenario 04: Verifies if the method equals() returns
     * false, when compared to the
     * same type of object and with the different parameters.
     */
    @Test
    public void shouldReturnFalseEqualsProjectsNotWithSameParameters() {
        // Arrange
        Project projectDouble = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        Project projectDoubleToCompare = new Project(2, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);

        // Act
        boolean isEquals = projectDouble.equals(projectDoubleToCompare);

        // Assert
        assertFalse(isEquals);
    }

    /**
     * Method: equals()
     * Scenario 05: Verifies if the method equals() returns
     * false, when compared to the
     * different type of object.
     */
    @Test
    public void shouldReturnFalseWithDifferentClasses() {
        // Arrange
        Project projectDouble = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        Object projectToCompare = new Object();

        // Act
        boolean isEquals = projectDouble.equals(projectToCompare);

        // Assert
        assertFalse(isEquals);
    }

    /**
     * Method: getProjectCode().
     * Scenario 01: test to ensure that project code requested from a given project is retrieved.
     */
    @Test
    public void ensureProjectCodeIsEqual_IsolationTest() {
        //Arrange
        Code projectCodeOne = mock(Code.class);
        when(projectCodeOne.getCode()).thenReturn("P001");

        Project projectOne = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);

        //Act
        String result = projectOne.getProjectCode();

        //Assert
        assertEquals("p001", result);
    }

    /**
     * Method: getProjectCode().
     * Scenario 02: test to ensure that the requested project code is not the same as another project.
     */
    @Test
    public void ensureProjectCodeIsNotEqual_IsolationTest() {
        //Arrange
        //Code
        Code projectCodeOne = mock(Code.class);
        when(projectCodeOne.getCode()).thenReturn("P001");
        //Project
        Project projectTwo = new Project(2, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);

        //Act
        String result = projectTwo.getProjectCode();

        //Assert
        assertNotEquals("P001", result);
    }

    /**
     * Method: addUserStory()
     * Scenario 01: verify if a User Story is added to Product Backlog.
     * Expected result: true.
     */
    @Test
    void ensureThatUserStoryIsSuccessfullyAdded() {
        //Arrange
        Project projectOne = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        UsId usIdDouble = mock(UsId.class);
        UsId usId = mock(UsId.class);
        projectOne.addUserStory(0, usId);
        int priority = 0;

        //Act
        boolean result = projectOne.addUserStory(priority, usIdDouble);

        //Assert
        assertTrue(result);
    }

    /**
     * Method: addUserStory()
     * Scenario 02: verify if a User Story is not added to Product Backlog.
     * Expected result: false.
     */
    @Test
    void ensureThatUserStoryIsUnsuccessfullyAdded() {
        //Arrange
        Project projectOne = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);

        UsId usId = mock(UsId.class);
        projectOne.addUserStory(0, usId);

        int priority = 0;
        //Act
        boolean result = projectOne.addUserStory(priority, usId);

        //Assert
        assertFalse(result);
    }

    /**
     * Method: getProductBacklog()
     * Scenario 1: This test verifies that the getProductBacklog() method in the Project class returns a list
     * of User Stories in the Product Backlog.
     * The method under test is then called to get the list of User Stories in the Product Backlog. The actual
     * list is compared to the expected list to ensure that they are equal. If they are, the test passes.
     */
    @Test
    void ensureGetProductBacklogReturnsListOfUserStories() {
        // Arrange
        UsId usId = mock(UsId.class);
        UsId usIdTwo = mock(UsId.class);
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        project.addUserStory(0, usId);
        project.addUserStory(1, usIdTwo);

        List<UsId> expectedUserStories = new ArrayList<>();
        expectedUserStories.add(usId);
        expectedUserStories.add(usIdTwo);

        // Act
        List<UsId> result = project.getProductBacklog();

        // Assert
        assertEquals(expectedUserStories, result);
    }

    /**
     * Scenario 2: This test verifies that the getProductBacklog() method in the Project class returns an empty list
     * when there are no User Stories in the Product Backlog.
     * The actual list is compared to the expected empty list to ensure that they are equal. If they are,
     * the test passes.
     */
    @Test
    void ensureGetProductBacklogReturnsEmptyListWhenProductBacklogIsEmpty() {
        // Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);

        List<UsId> expectedUserStories = new ArrayList<>();

        // Act
        List<UsId> result = project.getProductBacklog();

        // Assert
        assertEquals(expectedUserStories, result);
    }

    /**
     * Method: addUserStory()
     * <p>
     * Scenario 01: Add a new User Story to the userStories list if the User Story doesn't already exist.
     * <p>
     * Expected result: true.
     */

    @Test
    void ensureUserStoryIsAddedToTheProductBacklog() {
        // Arrange
        UsId usId = new UsId("1", "us001");
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);

        // Act
        boolean result = project.addUserStory(0, usId);

        // Assert
        assertTrue(result);
    }

    /**
     * Method: addUserStory()
     * <p>
     * Scenario 02: Fail to add a new User Story to the userStories list but the User Story already exists.
     * <p>
     * Expected result: false.
     */

    @Test
    void ensureUserStoryIsNotAddedToTheProductBacklog() {
        // Arrange
        UsId usId = new UsId("1", "us001");
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        project.addUserStory(0, usId);

        // Act
        boolean result = project.addUserStory(0, usId);
        // Assert
        assertFalse(result);
    }

    /**
     * Method: sameIdentityAs()
     * <p>
     * Scenario 1: Check if two instances of Project are equal by comparing the value of the attribute project code.
     * <p>
     * Expected result: true.
     */

    @Test
    void ensureTwoProjectsHaveTheSameIdentity() {
        // Act
        boolean isEquals = projectOne.sameIdentityAs(projectFour);

        // Assert
        assertTrue(isEquals);
    }

    /**
     * Method: sameIdentityAs()
     * <p>
     * Scenario 2: Check if two instances of Project are not equal by comparing the value of the attribute project code.
     * <p>
     * Expected result: false.
     */

    @Test
    void ensureTwoProjectsDoNotHaveTheSameIdentity() {
        // Act
        boolean isEquals = projectOne.sameIdentityAs(projectTwo);

        // Assert
        assertFalse(isEquals);
    }

    /**
     * METHOD: isPeriodAssigned
     * scenario 1: return true if period is assigned
     */
    @Test
    void ensureThatPeriodIsAssigned() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        Period periodDouble = mock(Period.class);
        ProjectStatus projectStatus = ProjectStatus.INCEPTION;
        project.setProjectStatus(projectStatus);
        //Act
        boolean result = project.isPeriodAssigned(periodDouble);
        //Assert
        assertTrue(result);
    }

    /**
     * scenario 2: return false if period is not assigned
     */
    @Test
    void ensureThatPeriodIsNotAssigned() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        Period periodDouble = mock(Period.class);
        //Act
        boolean result = project.isPeriodAssigned(periodDouble);
        //Assert
        assertFalse(result);
    }

    /**
     * METHOD: getProjectStatus
     * scenario 1: get Project status with success
     */
    @Test
    void ensureProjectStatusIsRetrievedSuccessfully() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        String expected = "planned";

        //Act
        String result = project.getProjectStatus();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD: getStartDate
     * scenario 1: get Project start date with success
     */
    @Test
    void ensureProjectStartDateIsRetrievedSuccessfully() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        Period periodDouble = mock(Period.class);
        ProjectStatus projectStatus = ProjectStatus.INCEPTION;
        project.setProjectStatus(projectStatus);
        project.isPeriodAssigned(periodDouble);
        String expected = "2023.01.20";
        when(periodDouble.getStartDate()).thenReturn(expected);
        //Act
        String result = project.getStartDate();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 2: get ProjectStartDate if period is null
     */

    @Test
    void ensureProjectStartDateIsRetrievedSuccessfullyWithPeriodNull() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        String expected = "";
        //Act
        String result = project.getStartDate();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD: getEndDate
     * scenario 1: get Project end date with success
     */
    @Test
    void ensureProjectEndDateIsRetrievedSuccessfully() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        Period periodDouble = mock(Period.class);
        ProjectStatus projectStatus = ProjectStatus.INCEPTION;
        project.setProjectStatus(projectStatus);
        project.isPeriodAssigned(periodDouble);
        String expected = "2023.01.04";
        when(periodDouble.getEndDate()).thenReturn(expected);
        //Act
        String result = project.getEndDate();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 2: get ProjectStartDate if period is null
     */

    @Test
    void ensureProjectEndDateIsRetrievedSuccessfullyWithPeriodNull() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        String expected = "";
        //Act
        String result = project.getEndDate();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Integration Tests
     * METHOD: getStartDate
     * scenario 1: get Project start date with success
     */
    @Test
    void ensureProjectStartDateIsRetrievedSuccessfully_IT() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        Period periodDouble = new Period(LocalDate.of(2023, 1, 21),
                LocalDate.of(2024, 1, 21));
        ProjectStatus projectStatus = ProjectStatus.INCEPTION;
        project.setProjectStatus(projectStatus);
        project.isPeriodAssigned(periodDouble);
        String expected = periodDouble.getStartDate();
        //Act
        String result = project.getStartDate();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Integration Tests
     * METHOD: getEndDate
     * scenario 1: get Project end date with success
     */
    @Test
    void ensureProjectEndDateIsRetrievedSuccessfully_IT() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        Period periodDouble = new Period(LocalDate.of(2023, 1, 21),
                LocalDate.of(2024, 1, 21));
        ProjectStatus projectStatus = ProjectStatus.INCEPTION;
        project.setProjectStatus(projectStatus);
        project.isPeriodAssigned(periodDouble);
        String expected = periodDouble.getEndDate();
        //Act
        String result = project.getEndDate();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Integration Tests
     * METHOD: isPeriodAssigned
     * scenario 1: return true if period is assigned
     */
    @Test
    void ensureThatPeriodIsAssigned_IT() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        Period periodDouble = new Period(LocalDate.of(2023, 1, 21),
                LocalDate.of(2024, 1, 21));
        ProjectStatus projectStatus = ProjectStatus.INCEPTION;
        project.setProjectStatus(projectStatus);
        //Act
        boolean result = project.isPeriodAssigned(periodDouble);
        //Assert
        assertTrue(result);
    }

    /**
     * scenario 2: return false if period is not assigned
     */
    @Test
    void ensureThatPeriodIsNotAssigned_IT() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        Period periodDouble = new Period(LocalDate.of(2023, 1, 21),
                LocalDate.of(2024, 1, 21));
        //Act
        boolean result = project.isPeriodAssigned(periodDouble);
        //Assert
        assertFalse(result);
    }

    /**
     * Integration Tests
     * method: getProjectCode
     * scenario 1: get Project code with success
     */

    @Test
    void ensureProjectCodeIsRetrievedSuccessfully() {
        //Arrange
        Code projectCode = new Code(1);
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        //Act
        String actualCode = project.getProjectCode();

        //Assert
        assertEquals(projectCode.getCode(), actualCode);
    }

    /**
     * Integration Tests
     * method: hasProjectCode
     * scenario 1: return true
     */
    @Test
    void ensureThatProjectCodeMatch() {
        //Arrange
        Code projectCode = new Code(1);
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);

        //Act
        boolean result = project.hasProjectCode(projectCode);
        //Assert
        assertTrue(result);
    }

    /**
     * scenario 2: return false
     */
    @Test
    void ensureThatProjectCodeNotMatch() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        Code newProjectCode = new Code(2);
        //Act
        boolean result = project.hasProjectCode(newProjectCode);
        //Assert
        assertFalse(result);
    }

    /**
     * Integration Tests
     * method: getProductBacklog
     * scenario 1: Retrieved product Backlog with success
     */
    @Test
    void ensureThatProductBacklogIsRetrieved() {
        //Arrange
        UsId usIdOne = new UsId("p001", "us001");
        UsId usIdTwo = new UsId("p001", "us002");
        List<UsId> expected = new ArrayList<>();
        expected.add(usIdOne);
        expected.add(usIdTwo);
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        project.addUserStory(0, usIdOne);
        project.addUserStory(1, usIdTwo);

        //Act
        List<UsId> result = project.getProductBacklog();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 2: Retrieved product Backlog as an empty list
     */
    @Test
    void ensureThatProductBacklogIsRetrieved_EmptyList() {
        //Arrange
        List<UsId> expected = new ArrayList<>();
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        //Act
        List<UsId> result = project.getProductBacklog();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD: getCustomerTaxId
     * scenario 1: a string of customerTaxID is retrieved
     */
    @Test
    void ensureThatCustomerTaxIDIsRetrieved() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId);
        TaxId expected = customerTaxId;
        //Act
        TaxId result = project.getCustomerTaxId();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * INTEGRATION TEST
     * METHOD: getCustomerTaxId
     * scenario 1: a string of customerTaxID is retrieved
     */
    @Test
    void ensureThatCustomerTaxIDIsRetrieved_IT() {
        //Arrange
        TaxId expected = new TaxId("202020200", "Portugal");
        Project project = new Project(1, projectName, description, businessSectorId, expected,
                projectTypologyId);
        //Act
        TaxId result = project.getCustomerTaxId();
        //Assert
        assertEquals(expected, result);
    }
}