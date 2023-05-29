/*
package org.switch2022.project.ddd.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSectorFactory;
import org.switch2022.project.ddd.domain.model.customer.CustomerFactory;
import org.switch2022.project.ddd.domain.model.project.FactoryProject;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResourceFactory;
import org.switch2022.project.ddd.domain.model.sprint.SprintFactory;
import org.switch2022.project.ddd.domain.model.typology.TypologyFactory;
import org.switch2022.project.ddd.domain.model.user_story.FactoryUserStory;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.infrastructure.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;

*/
/**
 * JUnit test class for the {@link DatabaseLoader} component.
 * Uses Mockito for mocking dependencies and verifying that the correct data
 * is loaded into the database when the {@link DatabaseLoader} is executed.
 *//*


@ExtendWith(MockitoExtension.class)
public class DatabaseLoaderTest {

    @Mock
    private ProjectRepositoryJpa projects;
    @Mock
    private BusinessSectorJpaRepository businessSectors;
    @Mock
    private TypologyJpaRepository typologies;
    @Mock
    private CustomerJpaRepository customers;
    @Mock
    private UserStoryRepositoryJpa userStories;
    @Mock
    private SprintRepositoryJpa sprints;
    @Mock
    private ProfileJpaRepository profiles;
    @Mock
    private AccountJpaRepository accounts;
    @Mock
    private ProjectResourceJpaRepository resources;
    @InjectMocks
    private DatabaseLoader databaseLoader;

    */
/**
     * Verifies that the correct data is loaded into the database when the
     * {@link DatabaseLoader} is executed.
     *
     * @throws Exception If there is an error loading the data.
     *//*


    @Test
    public void ensureDataIsLoadedIntoDatabase() throws Exception {
        // Call the run method of the database loader
        databaseLoader.run();

        // Verify that the data was loaded into the database
        verify(businessSectors).save(new BusinessSectorFactory().createBusinessSector(1, new Name("farming")));
        verify(businessSectors).save(new BusinessSectorFactory().createBusinessSector(2, new Name("sports")));
        verify(businessSectors).save(new BusinessSectorFactory().createBusinessSector(3, new Name("fishing")));
        verify(typologies).save(new TypologyFactory().createTypology(1, new Name("fixed cost")));
        verify(typologies).save(new TypologyFactory().createTypology(2, new Name("fixed materials")));
        verify(customers).save(new CustomerFactory().createCustomer(new TaxId("1111222234"), new Name("Eufemia")));
        verify(customers).save(new CustomerFactory().createCustomer(new TaxId("1111222237"), new Name("Jussara")));
        verify(customers).save(new CustomerFactory().createCustomer(new TaxId("1111222235"), new Name("Caroline")));
        verify(projects).save(new FactoryProject().createProject(1, new Name("Project 1"),
                new Description("potato planting"), new BusinessSectorId(1),
                new TaxId("1111222234"), new ProjectTypologyId(1)));
        verify(userStories).save(new FactoryUserStory().createUserStory(new UsNumber("1"), new UsText("I want to have a farm"),
                new Actor("Farmer"), new ArrayList<>(), new Code(1)));
        verify(sprints).save(new SprintFactory().createSprint(new Code(1),
                new SprintId("1", "1"), new SprintNumber(1),
                new Period(LocalDate.now(), 2)));
        verify(resources).save(new ProjectResourceFactory().createProjectResource(new ProjectResourceId(1),
                new Code(3), new Email("oliveira@gmail.com"), Role.SCRUM_MASTER,
                new Period(LocalDate.now(), 2), new CostPerHour(19), new PercentageOfAllocation(25)));
        verify(resources).save(new ProjectResourceFactory().createProjectResource(new ProjectResourceId(2),
                new Code(2), new Email("oliveira@gmail.com"), Role.PRODUCT_OWNER,
                new Period(LocalDate.now(), 2), new CostPerHour(18), new PercentageOfAllocation(35)));
        verify(resources).save(new ProjectResourceFactory().createProjectResource(new ProjectResourceId(3),
                new Code(3), new Email("oliveira@gmail.com"), Role.SCRUM_MASTER,
                new Period(LocalDate.now(), 2), new CostPerHour(19), new PercentageOfAllocation(25)));
    }
}*/
