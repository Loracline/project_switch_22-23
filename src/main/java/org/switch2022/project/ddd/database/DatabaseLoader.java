package org.switch2022.project.ddd.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.*;
import org.switch2022.project.ddd.domain.model.account.AccountFactory;
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSectorFactory;
import org.switch2022.project.ddd.domain.model.customer.CustomerFactory;
import org.switch2022.project.ddd.domain.model.profile.ProfileFactory;
import org.switch2022.project.ddd.domain.model.project.FactoryProject;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResourceFactory;
import org.switch2022.project.ddd.domain.model.sprint.SprintFactory;
import org.switch2022.project.ddd.domain.model.typology.TypologyFactory;
import org.switch2022.project.ddd.domain.model.user_story.FactoryUserStory;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.infrastructure.jpa.*;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A Spring Boot component that loads initial data into the application's database.
 * Implements the CommandLineRunner interface, which allows it to be automatically
 * executed by Spring Boot when the application starts up.
 */

@Component
public class DatabaseLoader implements CommandLineRunner {


    private final IProjectJpaRepository projects;
    private final IBusinessSectorJpaRepository businessSectors;
    private final ITypologyJpaRepository typologies;
    private final ICustomerJpaRepository customers;
    private final IUserStoryJpaRepository userStories;
    private final ISprintJpaRepository sprints;
    private final IAccountJpaRepository accounts;
    private final IProfileJpaRepository profiles;
    private final IProjectResourceJpaRepository resources;

    @Autowired
    public DatabaseLoader(IProjectJpaRepository projects, IBusinessSectorJpaRepository businessSectors,
                          ITypologyJpaRepository typologies, ICustomerJpaRepository customers,
                          IUserStoryJpaRepository userStories, ISprintJpaRepository sprints,
                          IAccountJpaRepository accounts, IProfileJpaRepository profiles,
                          IProjectResourceJpaRepository resources) {
        this.projects = projects;
        this.businessSectors = businessSectors;
        this.typologies = typologies;
        this.customers = customers;
        this.userStories = userStories;
        this.sprints = sprints;
        this.accounts = accounts;
        this.profiles = profiles;
        this.resources = resources;
    }
    /**
     * Called by Spring Boot when the application starts up. Loads initial data into
     * the application's database using the autowired repositories.
     *
     * @param strings Command-line arguments (not used).
     * @throws Exception If there is an error loading the data.
     */


    @Override
    public void run(String... strings) throws Exception {
        // Business sectors
        BusinessSectorFactory businessSectorFactory = new BusinessSectorFactory();
        BusinessSectorDomainDataAssembler businessSectorDomainDataAssembler = new BusinessSectorDomainDataAssembler();
        this.businessSectors.save(businessSectorDomainDataAssembler.toData(businessSectorFactory.createBusinessSector(1, new Name("farming"))));
        this.businessSectors.save(businessSectorDomainDataAssembler.toData(businessSectorFactory.createBusinessSector(2, new Name("sports"))));
        this.businessSectors.save(businessSectorDomainDataAssembler.toData(businessSectorFactory.createBusinessSector(3, new Name("fishing"))));
        // Project typologies
        TypologyFactory typologyFactory = new TypologyFactory();
        TypologyDomainDataAssembler typologyDomainDataAssembler = new TypologyDomainDataAssembler();
        this.typologies.save(typologyDomainDataAssembler.toData(typologyFactory.createTypology(1, new Name("fixed cost"))));
        this.typologies.save(typologyDomainDataAssembler.toData(typologyFactory.createTypology(2, new Name("fixed materials"))));

        // Customers
        CustomerFactory customerFactory = new CustomerFactory();
        CustomerDomainDataAssembler customerDomainDataAssembler = new CustomerDomainDataAssembler();
        this.customers.save(customerDomainDataAssembler.toData(customerFactory.createCustomer(new TaxId("217746691"), new Name("Catarina"))));
        this.customers.save(customerDomainDataAssembler.toData(customerFactory.createCustomer(new TaxId("257578994"), new Name("Jussara"))));
        this.customers.save(customerDomainDataAssembler.toData(customerFactory.createCustomer(new TaxId("238419096"), new Name("Caroline"))));
        // Projects
        FactoryProject factoryProject = new FactoryProject();
        ProjectDomainDataAssembler projectDomainDataAssembler = new ProjectDomainDataAssembler();
        this.projects.save(projectDomainDataAssembler.toData(factoryProject.createProject(1, new Name("Project 1"),
                new Description("potato planting"), new BusinessSectorId(1),
                new TaxId("1111222234"), new ProjectTypologyId(1))));
        this.projects.save(projectDomainDataAssembler.toData(factoryProject.createProject(2, new Name("Project 2"),
                new Description("athlete maintenance"), new BusinessSectorId(2),
                new TaxId("1111222237"), new ProjectTypologyId(1))));
        this.projects.save(projectDomainDataAssembler.toData(factoryProject.createProject(3, new Name("Project 3"),
                new Description("codfish fishing"), new BusinessSectorId(3),
                new TaxId("1111222235"), new ProjectTypologyId(2))));
        // User Stories
        FactoryUserStory factoryUserStory = new FactoryUserStory();
        UserStoryDomainDataAssembler userStoryDomainDataAssembler = new UserStoryDomainDataAssembler();
        this.userStories.save(userStoryDomainDataAssembler.toData(factoryUserStory.createUserStory(new UsNumber("1"), new UsText("I want to have a farm"),
                new Actor("Farmer"), new ArrayList<>(), new Code(1))));
        this.userStories.save(userStoryDomainDataAssembler.toData(factoryUserStory.createUserStory(new UsNumber("2"), new UsText("I want to create chickens"),
                new Actor("Farmer"), new ArrayList<>(), new Code(2))));
        this.userStories.save(userStoryDomainDataAssembler.toData(factoryUserStory.createUserStory(new UsNumber("3"), new UsText("I want to plant eggplants"),
                new Actor("Farmer"), new ArrayList<>(), new Code(3))));
        // Sprints
        SprintFactory sprintFactory = new SprintFactory();
        SprintDomainDataAssembler sprintDomainDataAssembler = new SprintDomainDataAssembler();
        this.sprints.save(sprintDomainDataAssembler.toData(sprintFactory.createSprint(new Code(1),
                new SprintId("1", "1"), new SprintNumber(1),
                new Period(LocalDate.now(), 2))));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintFactory.createSprint(new Code(2),
                new SprintId("2", "1"), new SprintNumber(1),
                new Period(LocalDate.now(), 2))));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintFactory.createSprint(new Code(3),
                new SprintId("3", "1"), new SprintNumber(1),
                new Period(LocalDate.now(), 2))));
        // Profiles
        ProfileFactory profileFactory = new ProfileFactory();
        ProfileDomainDataAssembler profileDomainDataAssembler = new ProfileDomainDataAssembler();
        this.profiles.save(profileDomainDataAssembler.toData(profileFactory.createProfile(new Name("User"), 1)));
        this.profiles.save(profileDomainDataAssembler.toData(profileFactory.createProfile(new Name("Manager"), 2)));
        this.profiles.save(profileDomainDataAssembler.toData(profileFactory.createProfile(new Name("Administrator"), 3)));
        // Accounts
        AccountFactory accountFactory = new AccountFactory();
        AccountDomainDataAssembler accountDomainDataAssembler = new AccountDomainDataAssembler();
        BufferedImage defaultImage = new BufferedImage(5, 5, BufferedImage.TYPE_3BYTE_BGR);
        this.accounts.save(accountDomainDataAssembler.toData(accountFactory.create(new Name("Miguel"), new Email("oliveira@gmail.com"),
                new PhoneNumber(964454321), null)));
        this.accounts.save(accountDomainDataAssembler.toData(accountFactory.create(new Name("Joana"), new Email("marques@gmail.com"),
                new PhoneNumber(964454322), null)));
        this.accounts.save(accountDomainDataAssembler.toData(accountFactory.create(new Name("Paulo"), new Email("ribeiro@gmail.com"),
                new PhoneNumber(964454323), null)));
        // Project resources
        ProjectResourceFactory projectResourceFactory = new ProjectResourceFactory();
        ResourceDomainDataAssembler resourceDomainDataAssembler = new ResourceDomainDataAssembler();
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceFactory.createProjectResource(new ProjectResourceId(1),
                new Code(1), new Email("oliveira@gmail.com"), Role.TEAM_MEMBER,
                new Period(LocalDate.now(), 2), new CostPerHour(15), new PercentageOfAllocation(75))));
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceFactory.createProjectResource(new ProjectResourceId(2),
                new Code(2), new Email("oliveira@gmail.com"), Role.PRODUCT_OWNER,
                new Period(LocalDate.now(), 2), new CostPerHour(18), new PercentageOfAllocation(35))));
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceFactory.createProjectResource(new ProjectResourceId(3),
                new Code(3), new Email("oliveira@gmail.com"), Role.SCRUM_MASTER,
                new Period(LocalDate.now(), 2), new CostPerHour(19), new PercentageOfAllocation(25))));
    }
}
