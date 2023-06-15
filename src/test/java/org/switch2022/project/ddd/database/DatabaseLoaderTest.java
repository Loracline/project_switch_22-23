package org.switch2022.project.ddd.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.switch2022.project.ddd.application.AddUserStoryToSprintBacklogService;
import org.switch2022.project.ddd.application.UsService;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.*;
import org.switch2022.project.ddd.domain.model.account.Account;
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
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.infrastructure.jpa.*;

import java.math.BigDecimal;
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
    @Mock
    private UsService usService;
    @Mock
    private AddUserStoryToSprintBacklogService addService;
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

        // Customers
        CustomerFactory customerFactory = new CustomerFactory();
        CustomerDomainDataAssembler customerDomainDataAssembler = new CustomerDomainDataAssembler();
        verify(customers).save(customerDomainDataAssembler.toData(customerFactory.
                createCustomer(new TaxId("217746691"), new Name("XPTO, SA"))));


        // Business sectors
        BusinessSectorDomainDataAssembler businessSectorDomainDataAssembler = new BusinessSectorDomainDataAssembler();
        BusinessSectorFactory businessSectorFactory = new BusinessSectorFactory();
        verify(businessSectors).save(businessSectorDomainDataAssembler.toData(businessSectorFactory.
                createBusinessSector(1, new Name("it doesn't matter"))));

        // Project typologies
        TypologyFactory typologyFactory = new TypologyFactory();
        TypologyDomainDataAssembler typologyDomainDataAssembler = new TypologyDomainDataAssembler();
        verify(typologies).save(typologyDomainDataAssembler.toData(typologyFactory.
                createTypology(1, new Name("fixed cost"))));

        // Projects
        FactoryProject factoryProject = new FactoryProject();
        ProjectDomainDataAssembler projectDomainDataAssembler = new ProjectDomainDataAssembler();
        Project projectOne = factoryProject.createProject(1, new Name("Dummy 01"),
                new Description("Just a dummy project"), new BusinessSectorId(1),
                new TaxId("217746691"), new ProjectTypologyId(1));
        projectOne.setProjectStatus(ProjectStatus.INCEPTION);
        projectOne.setPeriod(LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 7, 31));
        projectOne.setSprintDuration(2);
        projectOne.isNumberOfPlannedSprintsDefined(new NumberOfPlannedSprints(8));
        projectOne.isBudgetAssigned(new Budget(new BigDecimal(150000)));
        projectOne.setProjectStatus(ProjectStatus.CLOSED);
        projectOne.setProjectHistory(BigDecimal.valueOf(150_000),
                new NumberOfPlannedSprints(8), 2,
                LocalDate.of(2022, 3, 1),
                LocalDate.of(2022, 7, 31));
        verify(projects).save(projectDomainDataAssembler.toData(projectOne));


        // User Stories
        FactoryUserStory factoryUserStory = new FactoryUserStory();
        UserStoryDomainDataAssembler userStoryDomainDataAssembler = new UserStoryDomainDataAssembler();

        UserStory userStory = factoryUserStory.createUserStory(new UsNumber("1"), new UsText("I want to be a iguana"),
                new Actor("Farmer"), new ArrayList<>(), new Code(1));
        userStory.changeStatus(Status.RUNNING);
        verify(userStories).save(userStoryDomainDataAssembler.toData(userStory));

        // Sprints
        SprintFactory sprintFactory = new SprintFactory();
        SprintDomainDataAssembler sprintDomainDataAssembler = new SprintDomainDataAssembler();
        verify(sprints).save(sprintDomainDataAssembler.toData(sprintFactory.
                createSprint(new Code(1),
                        new SprintId("p001", "s001"), new SprintNumber(1),
                        new Period(LocalDate.of(2022, 3, 22), 2))));

        // Profiles
        ProfileFactory profileFactory = new ProfileFactory();
        ProfileDomainDataAssembler profileDomainDataAssembler = new ProfileDomainDataAssembler();
        verify(profiles).save(profileDomainDataAssembler.toData(profileFactory.
                createProfile(new Name("Administrator"), 3)));

        // Accounts
        AccountFactory accountFactory = new AccountFactory();
        AccountDomainDataAssembler accountDomainDataAssembler = new AccountDomainDataAssembler();
        Account accountOne = accountFactory.create(new Name("João Silva"),
                new Email("js@mymail.com"), new PhoneNumber(915879652), null);
        accountOne.changeProfile(new ProfileId(3));
        verify(accounts).save(accountDomainDataAssembler.toData(accountOne));

        // Project resources
        ProjectResourceFactory projectResourceFactory = new ProjectResourceFactory();
        ResourceDomainDataAssembler resourceDomainDataAssembler = new ResourceDomainDataAssembler();
        verify(resources).save(resourceDomainDataAssembler.toData(projectResourceFactory.
                createProjectResource(new ProjectResourceId(1),
                        new Code(1), new Email("tc@gmail.com"), Role.PROJECT_MANAGER,
                        new Period(LocalDate.of(2022, 1, 2), LocalDate.
                                of(2022, 7, 31)), new CostPerHour(35), new PercentageOfAllocation(20))));
    }
}
