package org.switch2022.project.ddd.datamodel_jpa.assemblers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.datamodel_jpa.TypologyJpa;
import org.switch2022.project.ddd.domain.model.typology.ITypologyFactory;
import org.switch2022.project.ddd.domain.model.typology.Typology;
import org.switch2022.project.ddd.domain.value_object.Name;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(
        classes =
                org.switch2022.project.ddd.datamodel_jpa.assemblers.TypologyDomainDataAssembler.class)
class TypologyDomainDataAssemblerTest {

    @InjectMocks
    TypologyDomainDataAssembler assembler;
    @MockBean
    ITypologyFactory factory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("ToData test")
    @Test
    void ensureTypologyJpaIsRetrievedFromTypology() {
        // Arrange
        Typology typology = mock(Typology.class);
        when(typology.getTypologyId()).thenReturn("PT003");
        when(typology.getTypologyName()).thenReturn("Gaming");
        TypologyJpa expected = new TypologyJpa("PT003", "Gaming");

        // Act
        TypologyJpa result = assembler.toData(typology);

        // Assert
        assertEquals(expected, result);
    }


    @DisplayName("ToDomain test")
    @Test
    void ensureTypologyIsRetrievedFromTypologyJpa() {
        // Arrange
        TypologyJpa typologyJpa = new TypologyJpa("PT003", "Gaming");
        Typology expected = mock(Typology.class);
        when(factory.createTypology(3, new Name("Gaming"))).thenReturn(expected);

        // Act
        Typology result = assembler.toDomain(typologyJpa);

        // Assert
        assertEquals(expected, result);
    }
}