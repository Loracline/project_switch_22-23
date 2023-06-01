package org.switch2022.project.ddd.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.application.UsService;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.*;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.AccountFactory;
import org.switch2022.project.ddd.domain.model.business_sector.BusinessSectorFactory;
import org.switch2022.project.ddd.domain.model.customer.CustomerFactory;
import org.switch2022.project.ddd.domain.model.profile.ProfileFactory;
import org.switch2022.project.ddd.domain.model.project.FactoryProject;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResourceFactory;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.domain.model.sprint.SprintFactory;
import org.switch2022.project.ddd.domain.model.typology.TypologyFactory;
import org.switch2022.project.ddd.domain.model.user_story.FactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.IFactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;
import org.switch2022.project.ddd.infrastructure.jpa.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A Spring Boot component that loads initial data into the application's database.
 * Implements the CommandLineRunner interface, which allows it to be automatically
 * executed by Spring Boot when the application starts up.
 */

@Component
@Transactional
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
    private final UsService usService;

    @Autowired
    public DatabaseLoader(IProjectJpaRepository projects, IBusinessSectorJpaRepository businessSectors,
                          ITypologyJpaRepository typologies, ICustomerJpaRepository customers,
                          IUserStoryJpaRepository userStories, ISprintJpaRepository sprints,
                          IAccountJpaRepository accounts, IProfileJpaRepository profiles,
                          IProjectResourceJpaRepository resources,
                          UsService usService) {
        this.projects = projects;
        this.businessSectors = businessSectors;
        this.typologies = typologies;
        this.customers = customers;
        this.userStories = userStories;
        this.sprints = sprints;
        this.accounts = accounts;
        this.profiles = profiles;
        this.resources = resources;
        this.usService = usService;
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

        this.businessSectors.save(businessSectorDomainDataAssembler.toData(businessSectorFactory.
                createBusinessSector(1, new Name("it doesn't matter"))));
        this.businessSectors.save(businessSectorDomainDataAssembler.toData(businessSectorFactory.
                createBusinessSector(2, new Name("Hospitality industry"))));

        // Project typologies
        TypologyFactory typologyFactory = new TypologyFactory();
        TypologyDomainDataAssembler typologyDomainDataAssembler = new TypologyDomainDataAssembler();

        this.typologies.save(typologyDomainDataAssembler.toData(typologyFactory.
                createTypology(1, new Name("fixed cost"))));
        this.typologies.save(typologyDomainDataAssembler.toData(typologyFactory.
                createTypology(2, new Name("Time and materials"))));

        // Customers
        CustomerFactory customerFactory = new CustomerFactory();
        CustomerDomainDataAssembler customerDomainDataAssembler = new CustomerDomainDataAssembler();

        this.customers.save(customerDomainDataAssembler.toData(customerFactory.
                createCustomer(new TaxId("217746691"), new Name("XPTO, SA"))));
        this.customers.save(customerDomainDataAssembler.toData(customerFactory.
                createCustomer(new TaxId("257578994"), new Name("XYZ, Lda"))));
        this.customers.save(customerDomainDataAssembler.toData(customerFactory.
                createCustomer(new TaxId("238419096"), new Name("Hell, LLC"))));

        // Projects
        FactoryProject factoryProject = new FactoryProject();
        ProjectDomainDataAssembler projectDomainDataAssembler = new ProjectDomainDataAssembler();

        Project projectOne = factoryProject.createProject(1, new Name("Dummy 01"),
                new Description("Just a dummy project"), new BusinessSectorId(1),
                new TaxId("217746691"), new ProjectTypologyId(1));
        projectOne.setProjectStatus(ProjectStatus.INCEPTION);
        projectOne.setPeriod(LocalDate.of(2022, 1, 3),
                LocalDate.of(2022, 7, 31));
        projectOne.setSprintDuration(2);
        projectOne.isNumberOfPlannedSprintsDefined(new NumberOfPlannedSprints(8));
        projectOne.isBudgetAssigned(new Budget(new BigDecimal(150000)));

        Project projectTwo = factoryProject.createProject(2, new Name("Dummy 02"),
                new Description("Just another dummy project"), new BusinessSectorId(1),
                new TaxId("217746691"), new ProjectTypologyId(1));
        projectTwo.setProjectStatus(ProjectStatus.INCEPTION);
        projectTwo.setPeriod(LocalDate.of(2022, 5, 31),
                LocalDate.of(2022, 7, 31));
        projectTwo.setSprintDuration(4);
        projectTwo.isNumberOfPlannedSprintsDefined(new NumberOfPlannedSprints(12));
        projectTwo.isBudgetAssigned(new Budget(new BigDecimal(350000)));

        Project projectThree = factoryProject.createProject(3, new Name("Inevitable nightmare"),
                new Description("Doomed from the start"), new BusinessSectorId(2),
                new TaxId("217746691"), new ProjectTypologyId(2));
        projectThree.setProjectStatus(ProjectStatus.INCEPTION);
        projectThree.setPeriod(LocalDate.of(2023, 3, 10),
                LocalDate.of(2023, 9, 20));
        projectThree.setSprintDuration(3);
        projectThree.isNumberOfPlannedSprintsDefined(new NumberOfPlannedSprints(15));
        projectThree.isBudgetAssigned(new Budget(new BigDecimal(750000)));

        this.projects.save(projectDomainDataAssembler.toData(projectOne));
        this.projects.save(projectDomainDataAssembler.toData(projectTwo));
        this.projects.save(projectDomainDataAssembler.toData(projectThree));

        List<String> acceptanceCriteria = new ArrayList<>();
        usService.createUs(new UserStoryCreationDto("P001", "1",
                "I want to be a iguana", "Farmer", acceptanceCriteria, 0));
        usService.createUs(new UserStoryCreationDto("P001", "2",
                "I want to be like a hippo", "Manuela", acceptanceCriteria, 0));
        usService.createUs(new UserStoryCreationDto("P002", "1",
                "I want to marry with Cristiana", "Gaiato", acceptanceCriteria, 0));
        usService.createUs(new UserStoryCreationDto("P002", "2",
                "I want to break free", "Gervásio", acceptanceCriteria, 0));
        usService.createUs(new UserStoryCreationDto("P003", "1",
                "I want to live forever", "Anacleto", acceptanceCriteria, 0));
        usService.createUs(new UserStoryCreationDto("P003", "2",
                "I want pantallones", "Josefina", acceptanceCriteria, 0));

        // User Stories
        FactoryUserStory factoryUserStory = new FactoryUserStory();
        UserStoryDomainDataAssembler userStoryDomainDataAssembler = new UserStoryDomainDataAssembler();
        UserStory userStoryOne = factoryUserStory.createUserStory(new UsNumber("1"),
                new UsText("I want to be a iguana"),
                new Actor("Farmer"), new ArrayList<>(), new Code(1));
        userStoryOne.setStatus(Status.RUNNING);
        UserStory userStoryTwo = factoryUserStory.createUserStory(new UsNumber("2"),
                new UsText("I want to be like a hippo"),
                new Actor("Manuela"), new ArrayList<>(), new Code(1));
        userStoryTwo.setStatus(Status.RUNNING);
        UserStory userStoryThree = factoryUserStory.createUserStory(new UsNumber("1"),
                new UsText("I want to marry with Cristiana"),
                new Actor("Gaiato"), new ArrayList<>(), new Code(2));
        userStoryThree.setStatus(Status.RUNNING);
        UserStory userStoryFour = factoryUserStory.createUserStory(new UsNumber("2"),
                new UsText("I want to break free"),
                new Actor("Gervásio"), new ArrayList<>(), new Code(2));
        userStoryFour.setStatus(Status.RUNNING);
        UserStory userStoryFive = factoryUserStory.createUserStory(new UsNumber("1"),
                new UsText("I want to live forever"),
                new Actor("Anacleto"), new ArrayList<>(), new Code(3));
        UserStory userStorySix = factoryUserStory.createUserStory(new UsNumber("3"),
                new UsText("I want pantallones"),
                new Actor("Josefina"), new ArrayList<>(), new Code(1));
        UserStory userStorySeven = factoryUserStory.createUserStory(new UsNumber("7"),
                new UsText("I want to have a gameboy"),
                new Actor("Bina"), new ArrayList<>(), new Code(2));
        UserStory userStoryEight = factoryUserStory.createUserStory(new UsNumber("3"),
                new UsText("I want to drink wine"),
                new Actor("Ben"), new ArrayList<>(), new Code(1));
        UserStory userStoryNine = factoryUserStory.createUserStory(new UsNumber("3"),
                new UsText("I want to smoke"),
                new Actor("John"), new ArrayList<>(), new Code(3));

        this.userStories.save(userStoryDomainDataAssembler.toData(userStoryOne));
        this.userStories.save(userStoryDomainDataAssembler.toData(userStoryTwo));
        this.userStories.save(userStoryDomainDataAssembler.toData(userStoryThree));
        this.userStories.save(userStoryDomainDataAssembler.toData(userStoryFour));
        this.userStories.save(userStoryDomainDataAssembler.toData(userStoryFive));
        this.userStories.save(userStoryDomainDataAssembler.toData(userStorySix));
        this.userStories.save(userStoryDomainDataAssembler.toData(userStorySeven));
        this.userStories.save(userStoryDomainDataAssembler.toData(userStoryEight));
        this.userStories.save(userStoryDomainDataAssembler.toData(userStoryNine));

        // Sprints
        SprintFactory sprintFactory = new SprintFactory();
        SprintDomainDataAssembler sprintDomainDataAssembler = new SprintDomainDataAssembler();

        Sprint sprintOne = sprintFactory.createSprint(new Code(1),
                new SprintId("p001", "s001"), new SprintNumber(1),
                new Period(LocalDate.of(2022, 3, 22), 2));
        Sprint sprintTwo = sprintFactory.createSprint(new Code(1),
                new SprintId("p001", "s002"), new SprintNumber(2),
                new Period(LocalDate.of(2022, 4, 5), 2));
        Sprint sprintThree = sprintFactory.createSprint(new Code(1),
                new SprintId("p001", "s003"), new SprintNumber(3),
                new Period(LocalDate.of(2022, 4, 26), 2));
        Sprint sprintFour = sprintFactory.createSprint(new Code(1),
                new SprintId("p001", "s004"), new SprintNumber(4),
                new Period(LocalDate.of(2022, 5, 10), 2));
        Sprint sprintFive = sprintFactory.createSprint(new Code(1),
                new SprintId("p001", "s005"), new SprintNumber(5),
                new Period(LocalDate.of(2022, 5, 24), 2));
        Sprint sprintSix = sprintFactory.createSprint(new Code(1),
                new SprintId("p001", "s006"), new SprintNumber(6),
                new Period(LocalDate.of(2022, 6, 7), 2));
        Sprint sprintSeven = sprintFactory.createSprint(new Code(1),
                new SprintId("p001", "s007"), new SprintNumber(7),
                new Period(LocalDate.of(2022, 6, 21), 2));
        Sprint sprintEight = sprintFactory.createSprint(new Code(1),
                new SprintId("p001", "s008"), new SprintNumber(8),
                new Period(LocalDate.of(2022, 7, 19), 2));
        Sprint sprintNine = sprintFactory.createSprint(new Code(2),
                new SprintId("p002", "s001"), new SprintNumber(9),
                new Period(LocalDate.of(2022, 6, 7), 2));
        Sprint sprintTen = sprintFactory.createSprint(new Code(2),
                new SprintId("p002", "s002"), new SprintNumber(10),
                new Period(LocalDate.of(2022, 7, 5), 2));
        Sprint sprintEleven = sprintFactory.createSprint(new Code(2),
                new SprintId("p002", "s003"), new SprintNumber(11),
                new Period(LocalDate.of(2022, 8, 2), 2));
        Sprint sprintTwelve = sprintFactory.createSprint(new Code(2),
                new SprintId("p002", "s004"), new SprintNumber(12),
                new Period(LocalDate.of(2022, 8, 30), 2));
        Sprint sprintThirteen = sprintFactory.createSprint(new Code(2),
                new SprintId("p002", "s005"), new SprintNumber(13),
                new Period(LocalDate.of(2022, 9, 27), 2));
        Sprint sprintFourteen = sprintFactory.createSprint(new Code(2),
                new SprintId("p002", "s006"), new SprintNumber(14),
                new Period(LocalDate.of(2022, 10, 25), 2));
        Sprint sprintFifteen = sprintFactory.createSprint(new Code(2),
                new SprintId("p002", "s007"), new SprintNumber(15),
                new Period(LocalDate.of(2022, 11, 22), 2));
        Sprint sprintSixteen = sprintFactory.createSprint(new Code(2),
                new SprintId("p002", "s008"), new SprintNumber(16),
                new Period(LocalDate.of(2022, 12, 13), 2));
        Sprint sprintSeventeen = sprintFactory.createSprint(new Code(2),
                new SprintId("p002", "s009"), new SprintNumber(17),
                new Period(LocalDate.of(2022, 1, 10), 2));
        Sprint sprintEighteen = sprintFactory.createSprint(new Code(2),
                new SprintId("p002", "s010"), new SprintNumber(18),
                new Period(LocalDate.of(2022, 2, 7), 2));
        Sprint sprintNineteen = sprintFactory.createSprint(new Code(2),
                new SprintId("p002", "s011"), new SprintNumber(19),
                new Period(LocalDate.of(2022, 3, 7), 2));
        Sprint sprintTwenty = sprintFactory.createSprint(new Code(2),
                new SprintId("p002", "s012"), new SprintNumber(20),
                new Period(LocalDate.of(2022, 4, 4), 2));

        this.sprints.save(sprintDomainDataAssembler.toData(sprintOne));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintTwo));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintThree));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintFour));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintFive));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintSix));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintSeven));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintEight));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintNine));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintTen));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintEleven));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintTwelve));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintThirteen));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintFourteen));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintFifteen));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintSixteen));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintSeventeen));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintEighteen));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintNineteen));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintTwenty));

        // Profiles
        ProfileFactory profileFactory = new ProfileFactory();
        ProfileDomainDataAssembler profileDomainDataAssembler = new ProfileDomainDataAssembler();

        this.profiles.save(profileDomainDataAssembler.toData(profileFactory.
                createProfile(new Name("User"), 1)));
        this.profiles.save(profileDomainDataAssembler.toData(profileFactory.
                createProfile(new Name("Manager"), 2)));
        this.profiles.save(profileDomainDataAssembler.toData(profileFactory.
                createProfile(new Name("Administrator"), 3)));

        // Accounts
        AccountFactory accountFactory = new AccountFactory();
        AccountDomainDataAssembler accountDomainDataAssembler = new AccountDomainDataAssembler();

        Account accountOne = accountFactory.create(new Name("João Silva"),
                new Email("js@mymail.com"), new PhoneNumber(915879652), null);
        accountOne.changeProfile(new ProfileId(3));

        Account accountTwo = accountFactory.create(new Name("Manel Costa"),
                new Email("ms@mymail.com"), new PhoneNumber(263650520), null);
        Account accountThree = accountFactory.create(new Name("Xico Ferreira"),
                new Email("xf@gmail.com"), new PhoneNumber(263650532), null);
        Account accountFour = accountFactory.create(new Name("Tiago Cancado"),
                new Email("tc@mymail.com"), new PhoneNumber(263650345), null);
        Account accountFive = accountFactory.create(new Name("Urbino das Urzes"),
                new Email("udu@mymail.com"), new PhoneNumber(962547891), null);
        Account accountSix = accountFactory.create(new Name("Ze da Esquina"),
                new Email("ze@mymail.com"), new PhoneNumber(212349016), null);
        accountSix.changeProfile(new ProfileId(2));

        Account accountSeven = accountFactory.create(new Name("Nel Moleiro"),
                new Email("nel.m@mymail.com"), new PhoneNumber(930123456), null);
        Account accountEight = accountFactory.create(new Name("Zé do Bento"),
                new Email("zb@mymail.com"), new PhoneNumber(921458791), null);
        Account accountNine = accountFactory.create(new Name("Tó Farrulo"),
                new Email("to.f@mymail.com"), new PhoneNumber(921458795), null);
        Account accountTen = accountFactory.create(new Name("Tino das Crzes"),
                new Email("tdc@mymail.com"), new PhoneNumber(921458799), null);
        Account accountEleven = accountFactory.create(new Name("Quim Barrreiros"),
                new Email("qb@mymail.com"), new PhoneNumber(921458803), null);
        Account accountTwelve = accountFactory.create(new Name("Tiago Geringonca"),
                new Email("tg@mymail.com"), new PhoneNumber(921458807), null);
        accountTwelve.changeProfile(new ProfileId(2));

        Account accountThirteen = accountFactory.create(new Name("Zé Manel"),
                new Email("zm@mymail.com"), new PhoneNumber(921458811), null);
        Account accountFourteen = accountFactory.create(new Name("Antonio Silva"),
                new Email("as@mymail.com"), new PhoneNumber(921458815), null);

        this.accounts.save(accountDomainDataAssembler.toData(accountOne));
        this.accounts.save(accountDomainDataAssembler.toData(accountTwo));
        this.accounts.save(accountDomainDataAssembler.toData(accountThree));
        this.accounts.save(accountDomainDataAssembler.toData(accountFour));
        this.accounts.save(accountDomainDataAssembler.toData(accountFive));
        this.accounts.save(accountDomainDataAssembler.toData(accountSix));
        this.accounts.save(accountDomainDataAssembler.toData(accountSeven));
        this.accounts.save(accountDomainDataAssembler.toData(accountEight));
        this.accounts.save(accountDomainDataAssembler.toData(accountNine));
        this.accounts.save(accountDomainDataAssembler.toData(accountTen));
        this.accounts.save(accountDomainDataAssembler.toData(accountEleven));
        this.accounts.save(accountDomainDataAssembler.toData(accountTwelve));
        this.accounts.save(accountDomainDataAssembler.toData(accountThirteen));
        this.accounts.save(accountDomainDataAssembler.toData(accountFourteen));

        // Project resources
        ProjectResourceFactory projectResourceFactory = new ProjectResourceFactory();
        ResourceDomainDataAssembler resourceDomainDataAssembler = new ResourceDomainDataAssembler();

        ProjectResource projectResourceOne = projectResourceFactory.createProjectResource(new ProjectResourceId(1),
                new Code(1), new Email("tc@gmail.com"), Role.PROJECT_MANAGER,
                new Period(LocalDate.of(2022, 1, 2), LocalDate.of
                        (2022, 7, 31)), new CostPerHour(35), new PercentageOfAllocation(20));
        ProjectResource projectResourceTwo = projectResourceFactory.createProjectResource(new ProjectResourceId(2),
                new Code(1), new Email("js@gmail.com"), Role.PRODUCT_OWNER,
                new Period(LocalDate.of(2022, 1, 3), LocalDate.of
                        (2022, 7, 31)), new CostPerHour(25), new PercentageOfAllocation(20));
        ProjectResource projectResourceThree = projectResourceFactory.createProjectResource(new ProjectResourceId(3),
                new Code(1), new Email("ms@gmail.com"), Role.SCRUM_MASTER,
                new Period(LocalDate.of(2022, 1, 4), LocalDate.of
                        (2022, 7, 31)), new CostPerHour(25), new PercentageOfAllocation(30));
        ProjectResource projectResourceFour = projectResourceFactory.createProjectResource(new ProjectResourceId(4),
                new Code(1), new Email("xf@gmail.com"), Role.TEAM_MEMBER,
                new Period(LocalDate.of(2022, 1, 5), LocalDate.of
                        (2022, 7, 31)), new CostPerHour(20), new PercentageOfAllocation(100));
        ProjectResource projectResourceFive = projectResourceFactory.createProjectResource(new ProjectResourceId(5),
                new Code(1), new Email("nel.m@gmail.com"), Role.TEAM_MEMBER,
                new Period(LocalDate.of(2022, 1, 7), LocalDate.of
                        (2022, 7, 31)), new CostPerHour(20), new PercentageOfAllocation(100));
        ProjectResource projectResourceSix = projectResourceFactory.createProjectResource(new ProjectResourceId(6),
                new Code(1), new Email("zb@gmail.com"), Role.TEAM_MEMBER,
                new Period(LocalDate.of(2022, 1, 8), LocalDate.of(2022, 7,
                        20)), new CostPerHour(20), new PercentageOfAllocation(100));
        ProjectResource projectResourceSeven = projectResourceFactory.createProjectResource(new ProjectResourceId(7),
                new Code(1), new Email("to.f@gmail.com"), Role.TEAM_MEMBER,
                new Period(LocalDate.of(2022, 1, 8), LocalDate.of
                        (2022, 7, 20)), new CostPerHour(20), new PercentageOfAllocation(100));
        ProjectResource projectResourceEight = projectResourceFactory.createProjectResource(new ProjectResourceId(8),
                new Code(1), new Email("tdc@gmail.com"), Role.TEAM_MEMBER,
                new Period(LocalDate.of(2022, 1, 10), LocalDate.of
                        (2022, 7, 20)), new CostPerHour(20), new PercentageOfAllocation(100));
        ProjectResource projectResourceNine = projectResourceFactory.createProjectResource(new ProjectResourceId(9),
                new Code(2), new Email("qb@gmail.com"), Role.PROJECT_MANAGER,
                new Period(LocalDate.of(2022, 5, 15), LocalDate.of
                        (2022, 7, 30)), new CostPerHour(42), new PercentageOfAllocation(20));
        ProjectResource projectResourceTen = projectResourceFactory.createProjectResource(new ProjectResourceId(10),
                new Code(2), new Email("tg@gmail.com"), Role.PRODUCT_OWNER,
                new Period(LocalDate.of(2022, 5, 15), LocalDate.of
                        (2022, 7, 30)), new CostPerHour(30), new PercentageOfAllocation(20));
        ProjectResource projectResourceEleven = projectResourceFactory.createProjectResource(new ProjectResourceId(11),
                new Code(2), new Email("zm@gmail.com"), Role.TEAM_MEMBER,
                new Period(LocalDate.of(2022, 5, 31), LocalDate.of
                        (2022, 7, 30)), new CostPerHour(20), new PercentageOfAllocation(100));
        ProjectResource projectResourceTwelve = projectResourceFactory.createProjectResource(new ProjectResourceId(12),
                new Code(2), new Email("as@gmail.com"), Role.TEAM_MEMBER,
                new Period(LocalDate.of(2022, 5, 31), LocalDate.of
                        (2022, 7, 30)), new CostPerHour(18), new PercentageOfAllocation(100));

        this.resources.save(resourceDomainDataAssembler.toData(projectResourceOne));
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceTwo));
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceThree));
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceFour));
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceFive));
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceSix));
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceSeven));
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceEight));
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceNine));
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceTen));
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceEleven));
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceTwelve));
    }
}
