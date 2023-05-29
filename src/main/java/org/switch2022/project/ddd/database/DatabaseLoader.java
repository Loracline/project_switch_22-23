/*
package org.switch2022.project.ddd.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
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
import org.switch2022.project.ddd.infrastructure.*;

import java.time.LocalDate;
import java.util.ArrayList;

*/
/**
 * A Spring Boot component that loads initial data into the application's database.
 * Implements the CommandLineRunner interface, which allows it to be automatically
 * executed by Spring Boot when the application starts up.
 *//*


@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private ProjectRepositoryJpa projects;
    @Autowired
    private BusinessSectorJpaRepository businessSectors;
    @Autowired
    private TypologyJpaRepository typologies;
    @Autowired
    private CustomerJpaRepository customers;
    @Autowired
    private UserStoryRepositoryJpa userStories;
    @Autowired
    private SprintRepositoryJpa sprints;
    @Autowired
    private AccountJpaRepository accounts;
    @Autowired
    private ProfileJpaRepository profiles;
    @Autowired
    private ProjectResourceJpaRepository resources;

    */
/**
     * Called by Spring Boot when the application starts up. Loads initial data into
     * the application's database using the autowired repositories.
     *
     * @param strings Command-line arguments (not used).
     * @throws Exception If there is an error loading the data.
     *//*


    @Override
    public void run(String... strings) throws Exception {
        // Business sectors
        BusinessSectorFactory businessSectorFactory = new BusinessSectorFactory();
        this.businessSectors.save(businessSectorFactory.createBusinessSector(1, new Name("farming")));
        this.businessSectors.save(businessSectorFactory.createBusinessSector(2, new Name("sports")));
        this.businessSectors.save(businessSectorFactory.createBusinessSector(3, new Name("fishing")));

        // Project typologies
        TypologyFactory typologyFactory = new TypologyFactory();
        this.typologies.save(typologyFactory.createTypology(1,new Name("fixed cost")));
        this.typologies.save(typologyFactory.createTypology(2,new Name("fixed materials")));

        // Customers
        CustomerFactory customerFactory = new CustomerFactory();
        this.customers.save(customerFactory.createCustomer(new TaxId("1111222234"),new Name("Catarina")));
        this.customers.save(customerFactory.createCustomer(new TaxId("1111222237"),new Name("Jussara")));
        this.customers.save(customerFactory.createCustomer(new TaxId("1111222235"),new Name("Caroline")));

        // Projects
        FactoryProject factoryProject = new FactoryProject();
        this.projects.save(factoryProject.createProject(1,new Name("Project 1"),
                new Description("potato planting"),new BusinessSectorId(1),
                new TaxId("1111222234"),new ProjectTypologyId(1)));
        this.projects.save(factoryProject.createProject(2,new Name("Project 2"),
                new Description("athlete maintenance"),new BusinessSectorId(2),
                new TaxId("1111222237"),new ProjectTypologyId(1)));
        this.projects.save(factoryProject.createProject(3,new Name("Project 3"),
                new Description("codfish fishing"),new BusinessSectorId(3),
                new TaxId("1111222235"),new ProjectTypologyId(2)));

        // User Stories
        FactoryUserStory factoryUserStory = new FactoryUserStory();
        this.userStories.save(factoryUserStory.createUserStory(new UsNumber("1"),new UsText("I want to have a farm"),
                new Actor("Farmer"), new ArrayList<>(), new Code(1)));
        this.userStories.save(factoryUserStory.createUserStory(new UsNumber("2"),new UsText("I want to create chickens"),
                new Actor("Farmer"), new ArrayList<>(), new Code(2)));
        this.userStories.save(factoryUserStory.createUserStory(new UsNumber("3"),new UsText("I want to plant eggplants"),
                new Actor("Farmer"), new ArrayList<>(), new Code(3)));

        // Sprints
        SprintFactory sprintFactory = new SprintFactory();
        this.sprints.save(sprintFactory.createSprint(new Code(1),
                new SprintId("1","1"), new SprintNumber(1),
                new Period(LocalDate.now(),2)));
        this.sprints.save(sprintFactory.createSprint(new Code(2),
                new SprintId("2","1"), new SprintNumber(1),
                new Period(LocalDate.now(),2)));
        this.sprints.save(sprintFactory.createSprint(new Code(3),
                new SprintId("3","1"), new SprintNumber(1),
                new Period(LocalDate.now(),2)));

        // Profiles
        ProfileFactory profileFactory = new ProfileFactory();
        this.profiles.save(profileFactory.createProfile(new Name("Administrator"),1));
        this.profiles.save(profileFactory.createProfile(new Name("Manager"),2));
        this.profiles.save(profileFactory.createProfile(new Name("User"),3));

        // Accounts
        AccountFactory accountFactory = new AccountFactory();
        this.accounts.save(accountFactory.create(new Name("Miguel"),new Email("oliveira@gmail.com"),
                new PhoneNumber(964454321),new Photo(null)));
        this.accounts.save(accountFactory.create(new Name("Joana"),new Email("marques@gmail.com"),
                new PhoneNumber(964454322),new Photo(null)));
        this.accounts.save(accountFactory.create(new Name("Paulo"),new Email("ribeiro@gmail.com"),
                new PhoneNumber(964454323),new Photo(null)));

        // Project resources
        ProjectResourceFactory projectResourceFactory = new ProjectResourceFactory();
        this.resources.save(projectResourceFactory.createProjectResource(new ProjectResourceId(1),
                new Code(1),new Email("oliveira@gmail.com"),Role.TEAM_MEMBER,
                new Period(LocalDate.now(),2),new CostPerHour(15),new PercentageOfAllocation(75)));
        this.resources.save(projectResourceFactory.createProjectResource(new ProjectResourceId(2),
                new Code(2),new Email("oliveira@gmail.com"),Role.PRODUCT_OWNER,
                new Period(LocalDate.now(),2),new CostPerHour(18),new PercentageOfAllocation(35)));
        this.resources.save(projectResourceFactory.createProjectResource(new ProjectResourceId(3),
                new Code(3),new Email("oliveira@gmail.com"),Role.SCRUM_MASTER,
                new Period(LocalDate.now(),2),new CostPerHour(19),new PercentageOfAllocation(25)));
    }
}
*/
