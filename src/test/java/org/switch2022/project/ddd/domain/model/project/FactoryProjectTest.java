package org.switch2022.project.ddd.domain.model.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.switch2022.project.ddd.domain.value_object.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class FactoryProjectTest {

    private IFactoryProject factory;

    @Mock
    private Name nameDouble;

    @Mock
    private Description descriptionDouble;

    @Mock
    private BusinessSectorId businessSectorIdDouble;

    @Mock
    private TaxId taxIdDouble;

    @Mock
    private ProjectTypologyId projectTypologyIdDouble;

    @BeforeEach
    public void setup() {
        factory = new FactoryProject();
    }

    @DisplayName("Project created")
    @Test
    void ensureProjectIsCreatedSuccessfully() {
        // Arrange
        Project expected = new Project(1, nameDouble, descriptionDouble, businessSectorIdDouble,
                taxIdDouble, projectTypologyIdDouble);

        // Act
        Project result = factory.createProject(1, nameDouble, descriptionDouble, businessSectorIdDouble,
                taxIdDouble, projectTypologyIdDouble);

        // Assert
        assertEquals(expected.getProjectName(), result.getProjectName());
        assertEquals(expected.getDescription(), result.getDescription());
        assertEquals(expected.getBusinessSectorId(), result.getBusinessSectorId());
        assertEquals(expected.getCustomerTaxId(), result.getCustomerTaxId());
        assertEquals(expected.getProjectTypologyId(), result.getProjectTypologyId());
    }
}