package org.switch2022.project.ddd.datamodel_jpa.assemblers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.datamodel_jpa.BusinessSectorJpa;
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSector;
import org.switch2022.project.ddd.domain.model.business_sector.IBusinessSectorFactory;
import org.switch2022.project.ddd.domain.value_object.Name;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest(
        classes = BusinessSectorDomainDataAssembler.class)
class BusinessSectorDomainDataAssemblerTest {
    @InjectMocks
    BusinessSectorDomainDataAssembler businessSectorDomainDataAssembler;
    @MockBean
    IBusinessSectorFactory factory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void toData() {
        //Arrange
        BusinessSectorJpa expected = new BusinessSectorJpa("bs001", "IT");
        BusinessSector businessSector = mock(BusinessSector.class);
        when(factory.createBusinessSector(1, new Name("IT"))).thenReturn(businessSector);

        when(businessSector.getBusinessSectorId()).thenReturn("bs001");
        when(businessSector.getBusinessSectorName()).thenReturn("IT");

        //Act
        BusinessSectorJpa result = businessSectorDomainDataAssembler.toData(businessSector);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void toDomain() {
        //Arrange
        BusinessSectorJpa businessSectorJpa = new BusinessSectorJpa("bs001", "IT");
        BusinessSector expected = mock(BusinessSector.class);
        when(factory.createBusinessSector(1, new Name("IT"))).thenReturn(expected);

        when(expected.getBusinessSectorName()).thenReturn("IT");
        when(expected.getBusinessSectorId()).thenReturn("bs001");
        //Act
        BusinessSector result = businessSectorDomainDataAssembler.toDomain(businessSectorJpa);

        //Assert
        assertEquals(expected, result);
    }

}