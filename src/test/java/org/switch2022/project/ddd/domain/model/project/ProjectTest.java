package org.switch2022.project.ddd.domain.model.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

    ProductBacklog productBacklog;


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

        productBacklog = mock(ProductBacklog.class);


        //Project
        projectOne = new Project(1, projectName, description, businessSectorId, customerTaxId, projectTypologyId,
                productBacklog);
        projectTwo = new Project(2, projectName, description, businessSectorId, customerTaxId, projectTypologyId,
                productBacklog);
        projectThree = new Project(3, projectName, description, businessSectorId, customerTaxId, projectTypologyId,
                productBacklog);
        projectFour = new Project(1, projectName, description, businessSectorId, customerTaxId, projectTypologyId,
                productBacklog);
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
                projectTypologyId, productBacklog);
        Project projectTwo = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId, productBacklog);

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
                projectTypologyId, productBacklog);
        Project projectTwo = new Project(2, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId, productBacklog);

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
                projectTypologyId, productBacklog);
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
                projectTypologyId, productBacklog);

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
                projectTypologyId, productBacklog);


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
                projectTypologyId, productBacklog);
        Project projectDoubleToCompare = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId, productBacklog);

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
                projectTypologyId, productBacklog);
        Project projectDoubleToCompare = new Project(2, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId, productBacklog);

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
                projectTypologyId, productBacklog);
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
                projectTypologyId, productBacklog);

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
        Project projectTwo = new Project(2, projectName, description, businessSectorId, customerTaxId, projectTypologyId,
                productBacklog);

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
                projectTypologyId, productBacklog);

        IFactoryProductBacklog iFactoryProductBacklogDouble = mock(IFactoryProductBacklog.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);

        when(iFactoryProductBacklogDouble.createProductBacklog(any())).thenReturn(productBacklogDouble);
        projectOne.setProductBacklog(iFactoryProductBacklogDouble);

        int priority = 0;
        UsId usId = mock(UsId.class);

        when(productBacklogDouble.addUserStory(priority, usId)).thenReturn(true);

        //Act
        boolean result = projectOne.addUserStory(priority, usId);

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
                projectTypologyId, productBacklog);

        IFactoryProductBacklog iFactoryProductBacklogDouble = mock(IFactoryProductBacklog.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);

        when(iFactoryProductBacklogDouble.createProductBacklog(any())).thenReturn(productBacklogDouble);
        projectOne.setProductBacklog(iFactoryProductBacklogDouble);

        int priority = 0;
        UsId usId = mock(UsId.class);

        when(productBacklogDouble.addUserStory(priority, usId)).thenReturn(false);

        //Act
        boolean result = projectOne.addUserStory(priority, usId);

        //Assert
        assertFalse(result);
    }

    /**
     * Method: getProductBacklog()
     * Scenario 1: This test verifies that the getProductBacklog() method in the Project class returns a list
     * of User Stories in the Product Backlog.
     * It sets up a mock object for the IFactoryProductBacklog interface and a mock object for the
     * ProductBacklog class to simulate a Product Backlog with two User Stories. It then sets up the expected
     * User Story list with the same two User Stories.
     * The method under test is then called to get the list of User Stories in the Product Backlog. The actual
     * list is compared to the expected list to ensure that they are equal. If they are, the test passes.
     */
    @Test
    void ensureGetProductBacklogReturnsListOfUserStories() {
        // Arrange
        IFactoryProductBacklog iFactoryProductBacklogDouble = mock(IFactoryProductBacklog.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);

        List<UsId> expectedUserStories = new ArrayList<>();
        expectedUserStories.add(new UsId("P001", "P1US001"));
        expectedUserStories.add(new UsId("P001", "P1US002"));

        when(iFactoryProductBacklogDouble.createProductBacklog(any())).thenReturn(productBacklogDouble);
        when(productBacklogDouble.getUserStories()).thenReturn(expectedUserStories);

        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId, productBacklog);
        project.setProductBacklog(iFactoryProductBacklogDouble);

        // Act
        List<UsId> result = project.getProductBacklog();

        // Assert
        assertEquals(expectedUserStories, result);
    }

    /**
     * Scenario 2: This test verifies that the getProductBacklog() method in the Project class returns an empty list
     * when there are no User Stories in the Product Backlog.
     * It sets up a mock object for the IFactoryProductBacklog interface and a mock object for the
     * ProductBacklog class to simulate an empty Product Backlog. It then sets up an empty expected User Story list.
     * The method under test is then called to get the list of User Stories in the Product Backlog. The actual
     * list is compared to the expected empty list to ensure that they are equal. If they are, the test passes.
     */
    @Test
    void ensureGetProductBacklogReturnsEmptyListWhenProductBacklogIsEmpty() {
        // Arrange
        IFactoryProductBacklog iFactoryProductBacklogDouble = mock(IFactoryProductBacklog.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);

        List<UsId> expectedUserStories = new ArrayList<>();

        when(iFactoryProductBacklogDouble.createProductBacklog(any())).thenReturn(productBacklogDouble);
        when(productBacklogDouble.getUserStories()).thenReturn(new ArrayList<>());

        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId, productBacklog);
        project.setProductBacklog(iFactoryProductBacklogDouble);

        // Act
        List<UsId> result = project.getProductBacklog();

        // Assert
        assertEquals(expectedUserStories, result);
    }

    /**
     * Scenario 3: This test verifies that the getProductBacklog() method in the Project class returns a list
     * of User Stories in the order they were added to the Product Backlog.
     * It sets up a mock object for the IFactoryProductBacklog interface and a mock object for the
     * ProductBacklog class to simulate a Product Backlog with three User Stories added in a specific order.
     * It then sets up the expected User Story list with the same three User Stories in the order they were added.
     * The method under test is then called to get the list of User Stories in the Product Backlog. The actual
     * list is compared to the expected list to ensure that they are equal and in the same order. If they are,
     * the test passes.
     */
    @Test
    void ensureGetProductBacklogReturnsListOfUserStoriesInCorrectOrder() {
        // Arrange
        IFactoryProductBacklog iFactoryProductBacklogDouble = mock(IFactoryProductBacklog.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);

        List<UsId> expectedUserStories = new ArrayList<>();
        expectedUserStories.add(new UsId("P001", "P1US001"));
        expectedUserStories.add(new UsId("P001", "P1US003"));
        expectedUserStories.add(new UsId("P001", "P1US002"));

        when(iFactoryProductBacklogDouble.createProductBacklog(any())).thenReturn(productBacklogDouble);
        when(productBacklogDouble.getUserStories()).thenReturn(new ArrayList<>() {{
            add(new UsId("P001", "P1US001"));
            add(new UsId("P001", "P1US003"));
            add(new UsId("P001", "P1US002"));
        }});

        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId, productBacklog);
        project.setProductBacklog(iFactoryProductBacklogDouble);

        // Act
        List<UsId> result = project.getProductBacklog();

        // Assert
        assertEquals(expectedUserStories, result);
    }

    /**
     * Scenario 4: Test the behavior of the getProductBacklog() method in the Project class when the Product Backlog is null.
     * It sets up a mock object for the IFactoryProductBacklog interface to simulate a null Product Backlog.
     * The method under test is then called to get the list of User Stories in the Product Backlog.
     * An exception is expected to be thrown since there is no Product Backlog to get User Stories from.
     */
    @Test
    void ensureGetProductBacklogThrowsExceptionWhenProductBacklogIsNull() {
        // Arrange
        IFactoryProductBacklog iFactoryProductBacklogDouble = mock(IFactoryProductBacklog.class);
        when(iFactoryProductBacklogDouble.createProductBacklog(any())).thenReturn(null);

        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId, productBacklog);
        project.setProductBacklog(iFactoryProductBacklogDouble);

        // Act and Assert
        assertThrows(NullPointerException.class, project::getProductBacklog);
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
        int priority = 0;
        IFactoryProductBacklog productBacklog = new FactoryProductBacklog();
        productBacklog.createProductBacklog("1");
        projectOne.setProductBacklog(productBacklog);
        // Act
        boolean result = projectOne.addUserStory(priority, usId);

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
        int priority = 0;
        IFactoryProductBacklog productBacklog = new FactoryProductBacklog();
        productBacklog.createProductBacklog("1");
        projectOne.setProductBacklog(productBacklog);
        // Act
        projectOne.addUserStory(priority, usId);
        boolean result = projectOne.addUserStory(priority, usId);

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
     * setBusinessSector()
     * <p>
     * Scenario 1: Sets the business sector ID attribute of the project.
     * <p>
     * Expected result: true.
     */

    @Test
    void ensureBusinessSectorIdIsSet() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        BusinessSectorId businessSectorId = new BusinessSectorId(1);

        // Act
        projectOne.setBusinessSector(businessSectorId);
        Field field = projectOne.getClass().getDeclaredField("businessSectorId");
        field.setAccessible(true);
        BusinessSectorId actualBusinessSectorId = (BusinessSectorId) field.get(projectOne);

        // Assert
        assertEquals(businessSectorId, actualBusinessSectorId);
    }

    /**
     * Scenario 2: Fails to set the business sector ID attribute of the project, ID is null.
     * <p>
     * Expected result: business sector ID not set.
     */

    @Test
    void ensureBusinessSectorIdIsNotSetBecauseItIsNull() throws NoSuchFieldException, IllegalAccessException {
        // Act
        projectOne.setBusinessSector(null);
        Field field = projectOne.getClass().getDeclaredField("businessSectorId");
        field.setAccessible(true);
        BusinessSectorId actualBusinessSectorId = (BusinessSectorId) field.get(projectOne);

        // Assert
        assertNull(actualBusinessSectorId);
    }

    /**
     * setTypology()
     * <p>
     * Scenario 1: Sets the project typology ID attribute of the project.
     * <p>
     * Expected result: true.
     */

    @Test
    void ensureTypologyIdIsSet() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        ProjectTypologyId projectTypologyId = new ProjectTypologyId(1);

        // Act
        projectOne.setTypology(projectTypologyId);
        Field field = projectOne.getClass().getDeclaredField("projectTypologyId");
        field.setAccessible(true);
        ProjectTypologyId actualProjectTypologyId = (ProjectTypologyId) field.get(projectOne);

        // Assert
        assertEquals(projectTypologyId, actualProjectTypologyId);
    }

    /**
     * Scenario 2: Fails to set the project typology ID attribute of the project, ID is null.
     * <p>
     * Expected result: project typology ID not set.
     */

    @Test
    void ensureTypologyIdIsNotSetBecauseItIsNull() throws NoSuchFieldException, IllegalAccessException {
        // Act
        projectOne.setTypology(null);
        Field field = projectOne.getClass().getDeclaredField("projectTypologyId");
        field.setAccessible(true);
        ProjectTypologyId actualProjectTypologyId = (ProjectTypologyId) field.get(projectOne);

        // Assert
        assertNull(actualProjectTypologyId);
    }

    /**
     * setSprintDuration()
     * <p>
     * Scenario 1: Sets the sprint duration of the project.
     * <p>
     * Expected result: true.
     */

    @Test
    void ensureSprintDurationIsSet() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        int sprintDuration = 2;
        projectOne.setProjectStatus(ProjectStatus.INCEPTION);

        // Act
        projectOne.setSprintDuration(sprintDuration);

        Field field = projectOne.getClass().getDeclaredField("sprintDuration");
        field.setAccessible(true);
        SprintDuration actualSprintDuration = (SprintDuration) field.get(projectOne);

        Field durationField = actualSprintDuration.getClass().getDeclaredField("duration");
        durationField.setAccessible(true);
        int actualDuration = (int) durationField.get(actualSprintDuration);

        // Assert
        assertEquals(sprintDuration, actualDuration);
    }

    /**
     * Scenario 2: Fails to set the sprint duration of the project because the sprint duration
     * is higher than the established maximum limit.
     * <p>
     * Expected result: sprint duration not set, exception is thrown.
     */

    @Test
    void ensureSprintDurationIsNotSetAboveTheLimit() {
        // Arrange
        projectOne.setProjectStatus(ProjectStatus.INCEPTION);
        Exception exception = assertThrows(Exception.class, () ->
                projectOne.setSprintDuration(5));
        String expected = "The sprint duration must be between 1 and 4";

        //Act
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Fails to set the sprint duration of the project because the sprint duration
     * is lower than the established minimum limit.
     * <p>
     * Expected result: sprint duration not set, exception is thrown.
     */

    @Test
    void ensureSprintDurationIsNotSetBellowTheLimit() {
        // Arrange
        projectOne.setProjectStatus(ProjectStatus.INCEPTION);
        Exception exception = assertThrows(Exception.class, () ->
                projectOne.setSprintDuration(0));
        String expected = "The sprint duration must be between 1 and 4";

        //Act
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Integration Tests
     * method: getProjectCode
     * scenario 1: get Project code with success
     */

    @Test
    void ensureProjectCodeIsRetrievedSuccessfully() throws NoSuchFieldException, IllegalAccessException {
        //Arrange
        Code projectCode = new Code(1);
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId, productBacklog);
        Field field = project.getClass().getDeclaredField("projectCode");
        field.setAccessible(true);

        //Act
        Code actualCode = (Code) field.get(project);

        //Assert
        assertEquals(projectCode, actualCode);
    }

    /**
     * Integration Tests
     * method: setName
     * scenario 1: set name with success
     */
    @Test
    void ensureSetNameIsSuccessfullyChanged() throws NoSuchFieldException, IllegalAccessException {
        //Arrange
        Name name = new Name("Alpha");
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId, productBacklog);
        Field field = project.getClass().getDeclaredField("projectName");
        field.setAccessible(true);
        project.setName("Alpha");

        //Act
        Name actualName = (Name) field.get(project);

        //Assert
        assertEquals(name, actualName);
    }

    /**
     * scenario 2: set name was unsuccessfully due to String be empty
     */
    @Test
    void ensureSetNameIsUnsuccessfullyChanged_Empty() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId, productBacklog);


        Exception exception = assertThrows(Exception.class, () ->
                project.setName(""));
        String expected = "The name must not be empty";
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 3: set name was unsuccessfully due to String be null
     */
    @Test
    void ensureSetNameIsUnsuccessfullyChanged_Null() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId, productBacklog);


        Exception exception = assertThrows(Exception.class, () ->
                project.setName(null));
        String expected = "The name must not be null";
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 4: set name was unsuccessfully due to String be blank
     */
    @Test
    void ensureSetNameIsUnsuccessfullyChanged_Blank() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId, productBacklog);


        Exception exception = assertThrows(Exception.class, () ->
                project.setName(" "));
        String expected = "The name must not be blank";
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Integration Tests
     * method: setDescription
     * scenario 1: set description with success
     */
    @Test
    void ensureSetDescriptionIsSuccessfullyChanged() throws NoSuchFieldException, IllegalAccessException {
        //Arrange
        Description description = new Description("Cool project");
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId, productBacklog);
        Field field = project.getClass().getDeclaredField("description");
        field.setAccessible(true);
        project.setDescription("Cool project");

        //Act
        Description actualDescription = (Description) field.get(project);

        //Assert
        assertEquals(description, actualDescription);
    }

    /**
     * scenario 2: set description was unsuccessfully due to String be empty
     */
    @Test
    void ensureSetDescriptionIsSuccessfullyChanged_Empty() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId, productBacklog);


        Exception exception = assertThrows(Exception.class, () ->
                project.setDescription(""));
        String expected = "The project description must not be empty";
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 3: set description was unsuccessfully due to String be null
     */
    @Test
    void ensureSetDescriptionIsSuccessfullyChanged_Null() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId, productBacklog);


        Exception exception = assertThrows(Exception.class, () ->
                project.setDescription(null));
        String expected = "The project description must not be null";
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 4: set description was unsuccessfully due to String be blank
     */
    @Test
    void ensureSetDescriptionIsSuccessfullyChanged_Blank() {
        //Arrange
        Project project = new Project(1, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId, productBacklog);

        Exception exception = assertThrows(Exception.class, () ->
                project.setDescription(" "));
        String expected = "The project description must not be blank";
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
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
                projectTypologyId, productBacklog);

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
                projectTypologyId, productBacklog);
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
                projectTypologyId, productBacklog);
        FactoryProductBacklog factoryProductBacklog = new FactoryProductBacklog();
        project.setProductBacklog(factoryProductBacklog);
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
                projectTypologyId, productBacklog);
        FactoryProductBacklog factoryProductBacklog = new FactoryProductBacklog();
        project.setProductBacklog(factoryProductBacklog);
        //Act
        List<UsId> result = project.getProductBacklog();
        //Assert
        assertEquals(expected, result);
    }
}