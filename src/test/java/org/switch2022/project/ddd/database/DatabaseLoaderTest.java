package org.switch2022.project.ddd.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.*;
import org.switch2022.project.ddd.domain.model.account.AccountFactory;
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSectorFactory;
import org.switch2022.project.ddd.domain.model.customer.CustomerFactory;
import org.switch2022.project.ddd.domain.model.profile.ProfileFactory;
import org.switch2022.project.ddd.domain.model.project.FactoryProject;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResourceFactory;
import org.switch2022.project.ddd.domain.model.sprint.SprintFactory;
import org.switch2022.project.ddd.domain.model.typology.TypologyFactory;
import org.switch2022.project.ddd.domain.model.user_story.FactoryUserStory;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.infrastructure.jpa.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;

/**
 * JUnit test class for the {@link DatabaseLoader} component.
 * Uses Mockito for mocking dependencies and verifying that the correct data
 * is loaded into the database when the {@link DatabaseLoader} is executed.
 */


@ExtendWith(MockitoExtension.class)
public class DatabaseLoaderTest {

    @Mock
    private IProjectJpaRepository projects;
    @Mock
    private IBusinessSectorJpaRepository businessSectors;
    @Mock
    private ITypologyJpaRepository typologies;
    @Mock
    private ICustomerJpaRepository customers;
    @Mock
    private IUserStoryJpaRepository userStories;
    @Mock
    private ISprintJpaRepository sprints;
    @Mock
    private IProfileJpaRepository profiles;
    @Mock
    private IAccountJpaRepository accounts;
    @Mock
    private IProjectResourceJpaRepository resources;
    @InjectMocks
    private DatabaseLoader databaseLoader;

/**
     * Verifies that the correct data is loaded into the database when the
     * {@link DatabaseLoader} is executed.
     *
     * @throws Exception If there is an error loading the data.
     */


    @Test
    public void ensureDataIsLoadedIntoDatabase() throws Exception {
        // Call the run method of the database loader
        databaseLoader.run();

        // Verify that the data was loaded into the database
        // Business sectors
        BusinessSectorDomainDataAssembler businessSectorDomainDataAssembler = new BusinessSectorDomainDataAssembler();
        BusinessSectorFactory businessSectorFactory = new BusinessSectorFactory();
        verify(businessSectors).save(businessSectorDomainDataAssembler.toData(businessSectorFactory.createBusinessSector(1, new Name("farming"))));

        // Project typologies
        TypologyFactory typologyFactory = new TypologyFactory();
        TypologyDomainDataAssembler typologyDomainDataAssembler = new TypologyDomainDataAssembler();
        verify(typologies).save(typologyDomainDataAssembler.toData(typologyFactory.createTypology(1, new Name("fixed cost"))));

        // Customers
        CustomerFactory customerFactory = new CustomerFactory();
        CustomerDomainDataAssembler customerDomainDataAssembler = new CustomerDomainDataAssembler();
        //verify(customers).save(customerDomainDataAssembler.toData(customerFactory.createCustomer(new TaxId("217746691"), new Name("Catarina"))));

        // Projects
        FactoryProject factoryProject = new FactoryProject();
        ProjectDomainDataAssembler projectDomainDataAssembler = new ProjectDomainDataAssembler();
        Project project1 = factoryProject.createProject(1, new Name("Project 1"),
                new Description("potato planting"), new BusinessSectorId(1),
                new TaxId("217746691"), new ProjectTypologyId(1));
        project1.setProjectStatus(ProjectStatus.INCEPTION);
        project1.setPeriod(LocalDate.of(2022,4,12), LocalDate.of(2024, 4, 12));
        project1.setSprintDuration(2);
        verify(projects).save(projectDomainDataAssembler.toData(project1));

        // User Stories
        FactoryUserStory factoryUserStory = new FactoryUserStory();
        UserStoryDomainDataAssembler userStoryDomainDataAssembler = new UserStoryDomainDataAssembler();
        verify(userStories).save(userStoryDomainDataAssembler.toData(factoryUserStory.createUserStory(new UsNumber("1"), new UsText("I want to have a farm"),
                new Actor("Farmer"), new ArrayList<>(), new Code(1))));

         // Sprints
        SprintFactory sprintFactory = new SprintFactory();
        SprintDomainDataAssembler sprintDomainDataAssembler = new SprintDomainDataAssembler();
        verify(sprints).save(sprintDomainDataAssembler.toData(sprintFactory.createSprint(new Code(1),
                new SprintId("p001", "s001"), new SprintNumber(1),
                new Period(LocalDate.now(), 2))));

        // Profiles
        ProfileFactory profileFactory = new ProfileFactory();
        ProfileDomainDataAssembler profileDomainDataAssembler = new ProfileDomainDataAssembler();
        verify(profiles).save(profileDomainDataAssembler.toData(profileFactory.createProfile(new Name("Administrator"), 3)));

        // Accounts
        AccountFactory accountFactory = new AccountFactory();
        AccountDomainDataAssembler accountDomainDataAssembler = new AccountDomainDataAssembler();
        this.accounts.save(accountDomainDataAssembler.toData(accountFactory.create(new Name("Miguel"), new Email("oliveira@gmail.com"),
                new PhoneNumber(964454321), null)));

        // Project resources
        ProjectResourceFactory projectResourceFactory = new ProjectResourceFactory();
        ResourceDomainDataAssembler resourceDomainDataAssembler = new ResourceDomainDataAssembler();
        verify(resources).save(resourceDomainDataAssembler.toData(projectResourceFactory.createProjectResource(new ProjectResourceId(1),
                new Code(1), new Email("oliveira@gmail.com"), Role.TEAM_MEMBER,
                new Period(LocalDate.now(), 2), new CostPerHour(15), new PercentageOfAllocation(75))));
    }
}
