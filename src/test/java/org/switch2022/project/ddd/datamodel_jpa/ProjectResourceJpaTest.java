package org.switch2022.project.ddd.datamodel_jpa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProjectResourceJpaTest {

    /**
     * No Args Constructor
     * Scenario 01: Test to see if an instance of project resource is successfully created.
     */
    @Test
    void ensureThatInstanceOfProjectResourceJpaIsCreated() {
        //ARRANGE AND ACT
        ProjectResourceJpa resourceJpa = new ProjectResourceJpa();
        //ASSERT
        assertNotNull(resourceJpa);
    }

    /**
     * Method: getId()
     * Scenario 01: Test to get the id.
     */
    @Test
    void ensureThatTheIdIsReturned() {
        //ARRANGE
        ProjectResourceJpa resourceJpa = new ProjectResourceJpa("PR01", "P01", "example@isep.ipp.pt", "TEAM MEMBER",
                "2023-06-01", "2023-06-05", 8.0f, 55.0f);
        String expected = "PR01";
        //ACT
        String result = resourceJpa.getId();
        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Method: getProjectCode()
     * Scenario 01: Test to get the project code.
     */
    @Test
    void ensureThatProjectCodeIsReturned() {
        //ARRANGE
        ProjectResourceJpa resourceJpa = new ProjectResourceJpa("PR01", "P01", "example@isep.ipp.pt", "TEAM MEMBER",
                "2023-06-01", "2023-06-05", 8.0f, 55.0f);
        String expected = "P01";
        //ACT
        String result = resourceJpa.getProjectCode();
        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Method: getAccountEmail()
     * Scenario 01: Test to get the account email.
     */
    @Test
    void ensureThatAccountEmailIsReturned() {
        //ARRANGE
        ProjectResourceJpa resourceJpa = new ProjectResourceJpa("PR01", "P01", "example@isep.ipp.pt", "TEAM MEMBER",
                "2023-06-01", "2023-06-05", 8.0f, 55.0f);
        String expected = "example@isep.ipp.pt";
        //ACT
        String result = resourceJpa.getAccountEmail();
        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Method: getRole()
     * Scenario 01: Test to get the role.
     */
    @Test
    void ensureThatRoleIsReturned() {
        //ARRANGE
        ProjectResourceJpa resourceJpa = new ProjectResourceJpa("PR01", "P01", "example@isep.ipp.pt", "TEAM MEMBER",
                "2023-06-01", "2023-06-05", 8.0f, 55.0f);
        String expected = "TEAM MEMBER";
        //ACT
        String result = resourceJpa.getRole();
        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Method: getStartDate()
     * Scenario 01: Test to get the start date.
     */
    @Test
    void ensureThatStartDateIsReturned() {
        //ARRANGE
        ProjectResourceJpa resourceJpa = new ProjectResourceJpa("PR01", "P01", "example@isep.ipp.pt", "TEAM MEMBER",
                "2023-06-01", "2023-06-05", 8.0f, 55.0f);
        String expected = "2023-06-01";
        //ACT
        String result = resourceJpa.getStartDate();
        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Method: getEndDate()
     * Scenario 01: Test to get the end date.
     */
    @Test
    void ensureThatEndDateIsReturned() {
        //ARRANGE
        ProjectResourceJpa resourceJpa = new ProjectResourceJpa("PR01", "P01", "example@isep.ipp.pt", "TEAM MEMBER",
                "2023-06-01", "2023-06-05", 8.0f, 55.0f);
        String expected = "2023-06-05";
        //ACT
        String result = resourceJpa.getEndDate();
        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Method: getCostPerHour()
     * Scenario 01: Test to get the cost per hour.
     */
    @Test
    void ensureThatCostPerHourIsReturned() {
        //ARRANGE
        ProjectResourceJpa resourceJpa = new ProjectResourceJpa("PR01", "P01", "example@isep.ipp.pt", "TEAM MEMBER",
                "2023-06-01", "2023-06-05", 8.0f, 55.0f);
        float expected = 8.0f;
        //ACT
        float result = resourceJpa.getCostPerHour();
        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Method: getPercentageOfAllocation()
     * Scenario 01: Test to get the percentage of allocation.
     */
    @Test
    void ensureThatPercentageOfAllocationIsReturned() {
        //ARRANGE
        ProjectResourceJpa resourceJpa = new ProjectResourceJpa("PR01", "P01", "example@isep.ipp.pt", "TEAM MEMBER",
                "2023-06-01", "2023-06-05", 8.0f, 55.0f);
        float expected = 55.0f;
        //ACT
        float result = resourceJpa.getPercentageOfAllocation();
        //ASSERT
        assertEquals(expected, result);
    }


    /**
     * Method: equals()
     * Scenario 01: Test to ensure the object equals itself.
     */
    @SuppressWarnings("all")
    @Test
    void ensureThatProjectResourceEqualsItself() {
        //ARRANGE
        ProjectResourceJpa resourceJpa = new ProjectResourceJpa("PR01", "P01", "example@isep.ipp.pt", "TEAM MEMBER",
                "2023-06-01", "2023-06-05", 8.0f, 55.0f);
        ProjectResourceJpa sameResourceJpa = resourceJpa;
        //ACT
        boolean result = resourceJpa.equals(sameResourceJpa);
        //ASSERT
        assertTrue(result);
    }

    /**
     * Method: equals()
     * Scenario 02: Test to ensure that two different objects from the same class are different.
     */
    @Test
    void ensureThatTwoDifferentProjectResourcesAreNotEqual() {
        //ARRANGE
        ProjectResourceJpa resourceJpa = new ProjectResourceJpa("PR01", "P01", "example@isep.ipp.pt", "TEAM MEMBER",
                "2023-06-01", "2023-06-05", 8.0f, 55.0f);
        ProjectResourceJpa otherResourceJpa = new ProjectResourceJpa("PR02", "P02", "example2@isep.ipp.pt", "TEAM " +
                "MEMBER","2023-06-01", "2023-06-05", 8.0f, 55.0f);
        //ACT
        boolean result = resourceJpa.equals(otherResourceJpa);
        //ASSERT
        assertFalse(result);
    }

    @SuppressWarnings("all")
    /**
     * Method: equals()
     * Scenario 03: Test to ensure that two objects from different classes are different.
     */
    @Test
    void ensureThatProjectResourceIsNotEqualToOtherTypeOfObject() {
        //ARRANGE
        ProjectResourceJpa resourceJpa = new ProjectResourceJpa("PR01", "P01", "example@isep.ipp.pt", "TEAM MEMBER",
                "2023-06-01", "2023-06-05", 8.0f, 55.0f);
        String otherObject = "other object";
        //ACT
        boolean result = resourceJpa.equals(otherObject);
        //ASSERT
        assertFalse(result);
    }

    /**
     * Method: equals()
     * Scenario 04: Test to ensure that two equal objects from the same class are equal.
     */
    @Test
    void ensureTwoObjectsAreEqual() {
        //ARRANGE
        ProjectResourceJpa resourceJpa = new ProjectResourceJpa("PR01", "P01", "example@isep.ipp.pt", "TEAM MEMBER",
                "2023-06-01", "2023-06-05", 8.0f, 55.0f);
        ProjectResourceJpa otherResourceJpa = new ProjectResourceJpa("PR01", "P01", "example@isep.ipp.pt",
                "TEAM MEMBER",
                "2023-06-01", "2023-06-05", 8.0f, 55.0f);
        //ACT
        boolean result = resourceJpa.equals(otherResourceJpa);
        //ASSERT
        assertTrue(result);
    }

    /**
     * Method: equals()
     * Scenario 05: Test to ensure that one object doesn't equal null.
     */
    @SuppressWarnings("all")
    @Test
    void ensureThatObjectDoesNotEqualNull() {
        //ARRANGE
        ProjectResourceJpa resourceJpa = new ProjectResourceJpa("PR01", "P01", "example@isep.ipp.pt", "TEAM MEMBER",
                "2023-06-01", "2023-06-05", 8.0f, 55.0f);
        //ACT
        boolean result = resourceJpa.equals(null);
        //ASSERT
        assertFalse(result);
    }

    /**
     * Tests for hashCode()
     */
    @Test
    void ensureThatTwoProjectResourcesHaveSameHashCode() {
        //ARRANGE
        ProjectResourceJpa resourceJpa = new ProjectResourceJpa("PR01", "P01", "example@isep.ipp.pt", "TEAM MEMBER",
                "2023-06-01", "2023-06-05", 8.0f, 55.0f);
        ProjectResourceJpa otherResourceJpa = new ProjectResourceJpa("PR01", "P01", "example@isep.ipp.pt",
                "TEAM MEMBER",
                "2023-06-01", "2023-06-05", 8.0f, 55.0f);
        //ACT
        int resourceJpaHash = resourceJpa.hashCode();
        int otherResourceJpaHash = otherResourceJpa.hashCode();
        //ASSERT
        assertEquals(resourceJpaHash, otherResourceJpaHash);
    }

    @Test
    void ensureThatTwoProjectResourcesWithDifferentIdsHaveDifferentHashCodes() {
        //ARRANGE
        ProjectResourceJpa resourceJpa = new ProjectResourceJpa("PR01", "P01", "example@isep.ipp.pt", "TEAM MEMBER",
                "2023-06-01", "2023-06-05", 8.0f, 55.0f);
        ProjectResourceJpa otherResourceJpa = new ProjectResourceJpa("PR02", "P02", "example2@isep.ipp.pt", "TEAM " +
                "MEMBER","2023-06-01", "2023-06-05", 8.0f, 55.0f);
        //ACT
        int resourceJpaHash = resourceJpa.hashCode();
        int otherResourceJpaHash = otherResourceJpa.hashCode();
        //ASSERT
        assertNotEquals(resourceJpaHash, otherResourceJpaHash);
    }
}