package org.switch2022.project.ddd.datamodel_jpa.assemblers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.datamodel_jpa.ProjectResourceJpa;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceFactory;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.value_object.Period;
import org.switch2022.project.ddd.domain.value_object.Role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = ResourceDomainDataAssemblerTest.class)
class ResourceDomainDataAssemblerTest {
    @InjectMocks
    ResourceDomainDataAssembler assembler;
    @MockBean
    IProjectResourceFactory factory;

    /**
     * Method: toData()
     * Converts a ProjectResource domain instance to a ProjectResourceJpa data model instance.
     * When comparing two instances (expected and result) of ProjectResource, it should return True.
     */
    @Test
    void toData() {
        //ARRANGE
        ProjectResource resourceDouble = mock(ProjectResource.class);
        Period period = mock(Period.class);
        when(resourceDouble.getPeriod()).thenReturn(period);

        ProjectResourceJpa expected = new ProjectResourceJpa("PR01", "P01", "example@isep.ipp.pt", "TEAM MEMBER",
                "2023-06-01", "2023-06-05", 8.0f, 55.0f);

        when(resourceDouble.getProjectResourceId()).thenReturn("PR01");


        //ACT
        ProjectResourceJpa result = assembler.toData(resourceDouble);
        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Method: toDomain()
     * Converts a ProjectResourceJpa data model instance to a ProjectResource domain instance.
     * When comparing two instances (expected and result) of ProjectResourceJpa, it should return True.
     */
    @Test
    void toDomain() {
        //ARRANGE
        ProjectResourceJpa resourceJpaDouble = mock(ProjectResourceJpa.class);
        ProjectResource expected = mock(ProjectResource.class);

        when(resourceJpaDouble.getId()).thenReturn("PR01");
        when(resourceJpaDouble.getProjectCode()).thenReturn("P01");
        when(resourceJpaDouble.getAccountEmail()).thenReturn("example@isep.ipp.pt");
        when(resourceJpaDouble.getRole()).thenReturn("TEAM MEMBER");
        when(resourceJpaDouble.getStartDate()).thenReturn("2023-06-01");
        when(resourceJpaDouble.getEndDate()).thenReturn("2023-06-05");
        when(resourceJpaDouble.getCostPerHour()).thenReturn(8.0f);
        when(resourceJpaDouble.getPercentageOfAllocation()).thenReturn(55.0f);

        when(factory.createProjectResource(any(), any(), any(), any(), any(), any(), any())).thenReturn(expected);
        //ACT
        ProjectResource result = assembler.toDomain(resourceJpaDouble);
        //ASSERT
        assertEquals(expected, result);
    }
}