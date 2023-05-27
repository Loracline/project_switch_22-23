package org.switch2022.project.ddd.domain.model.typology;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.Name;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class FactoryTypologyTest {

    /**
     * Method createTypology(int typologyNumber, Name typology)
     * Scenario 1: Verify if a Typology object is created through the IFactoryTypology instance
     * and equal to a Typology object from a Typology instance.
     * <p>
     * Expected result: Both Typology objects are equal.
     */

    @Test
    void createProjectTypology() {
        // Arrange
        Name typologyNameDouble = mock(Name.class);
        ITypologyFactory factoryTypologyI = new TypologyFactory();

        // Act
        Typology typology = factoryTypologyI.createTypology(8, typologyNameDouble);
        Typology typologyToCompare = new Typology(8, typologyNameDouble);

        // Assert
        assertEquals(typology, typologyToCompare);
    }
}