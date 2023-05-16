package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountStatusTest {

    /**
     * Method getAccountStatus
     */

    @Test
    void ensureAccountStatusIsRetrieved(){
        //Arrange
        String expected = "active";
        //Act
        String result = AccountStatus.ACTIVE.getAccountStatus();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Method sameValueAs()
     *<br>
     * Scenario 1: Verify that two instances of AccountStatus are equal if their values are the same.
     */

    @Test
    void ensureThatReturnsFalseIfTwoAccountStatusInstancesHaveDifferentValues(){
        //Arrange
        AccountStatus status = AccountStatus.ACTIVE;
        AccountStatus otherStatus = AccountStatus.ACTIVE;
        //Act
        boolean result = status.sameValueAs(otherStatus);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of AccountStatus are not equal if their values are the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoAccountStatusInstancesHaveDifferentValues() {
        //Arrange
        AccountStatus status = AccountStatus.ACTIVE;
        AccountStatus otherStatus = AccountStatus.INACTIVE;
        //Act
        boolean result = status.sameValueAs(otherStatus);
        //Arrange
        assertFalse(result);
    }
}