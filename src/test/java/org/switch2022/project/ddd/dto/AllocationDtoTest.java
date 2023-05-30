package org.switch2022.project.ddd.dto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class AllocationDtoTest {

    /**
     * Method AllocationDto().
     */
    @Test
    public void constructor_ShouldSetAttributes() {
        // Arrange
        int expectedProjectCode = 123;
        String expectedAccountEmail = "johndoe@example.com";
        String expectedAccountRole = "Developer";
        float expectedAccountCostPerHour = 50.0f;
        float expectedAccountPercentageOfAllocation = 0.8f;
        LocalDate expectedStartDate = LocalDate.of(2023, 6, 1);
        LocalDate expectedEndDate = LocalDate.of(2023, 6, 30);

        // Act
        AllocationDto allocation = new AllocationDto(expectedProjectCode, expectedAccountEmail,
                expectedAccountRole, expectedAccountCostPerHour, expectedAccountPercentageOfAllocation,
                expectedStartDate, expectedEndDate);

        // Assert
        Assertions.assertEquals(expectedProjectCode, allocation.projectCode);
        Assertions.assertEquals(expectedAccountEmail, allocation.accountEmail);
        Assertions.assertEquals(expectedAccountRole, allocation.accountRole);
        Assertions.assertEquals(expectedAccountCostPerHour, allocation.accountCostPerHour);
        Assertions.assertEquals(expectedAccountPercentageOfAllocation, allocation.accountPercentageOfAllocation);
        Assertions.assertEquals(expectedStartDate, allocation.startDate);
        Assertions.assertEquals(expectedEndDate, allocation.endDate);
    }
}
