package org.switch2022.project.ddd.domain.model.business_sector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.switch2022.project.ddd.domain.value_object.Name;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BusinessSectorFactoryTest {

    private IBusinessSectorFactory factory;

    @Mock
    private Name nameDouble;

    @BeforeEach
    public void setup() {
        factory = new BusinessSectorFactory();
    }

    @DisplayName("Business sector created")
    @Test
    void ensureBusinessSectorIsCreatedSuccessfully() {
        // Arrange
        BusinessSector expected = new BusinessSector(1, nameDouble);

        // Act
        BusinessSector result = factory.createBusinessSector(1, nameDouble);

        // Assert
        assertEquals(expected.getBusinessSectorId(), result.getBusinessSectorId());
        assertEquals(expected.getBusinessSectorName(), result.getBusinessSectorName());
    }
}