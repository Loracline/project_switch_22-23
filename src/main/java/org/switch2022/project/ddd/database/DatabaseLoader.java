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
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.UserStoryCreationDto;
import org.switch2022.project.ddd.infrastructure.jpa.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static java.time.Month.*;

/**
 * A Spring Boot component that loads initial data into the application's database.
 * Implements the CommandLineRunner interface, which allows it to be automatically
 * executed by Spring Boot when the application starts up.
 */

@Component
@Transactional
public class DatabaseLoader implements CommandLineRunner {

    @Autowired private IProjectJpaRepository projects;
    @Autowired private IBusinessSectorJpaRepository businessSectors;
    @Autowired private ITypologyJpaRepository typologies;
    @Autowired private ICustomerJpaRepository customers;
    @Autowired private IUserStoryJpaRepository userStories;
    @Autowired private ISprintJpaRepository sprints;
    @Autowired private IAccountJpaRepository accounts;
    @Autowired private IProfileJpaRepository profiles;
    @Autowired private IProjectResourceJpaRepository resources;
    @Autowired private UsService usService;


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
        final int BUSINESS_SECTOR_NUMBER_ONE = 1;
        final int BUSINESS_SECTOR_NUMBER_TWO = 2;
        this.businessSectors.save(businessSectorDomainDataAssembler.toData(businessSectorFactory.
                createBusinessSector(BUSINESS_SECTOR_NUMBER_ONE, new Name("it doesn't matter"))));
        this.businessSectors.save(businessSectorDomainDataAssembler.toData(businessSectorFactory.
                createBusinessSector(BUSINESS_SECTOR_NUMBER_TWO, new Name("Hospitality industry"))));

        // Project typologies
        TypologyFactory typologyFactory = new TypologyFactory();
        TypologyDomainDataAssembler typologyDomainDataAssembler = new TypologyDomainDataAssembler();
        final int TYPOLOGY_NUMBER_ONE = 1;
        final int TYPOLOGY_NUMBER_TWO = 2;
        this.typologies.save(typologyDomainDataAssembler.toData(typologyFactory.
                createTypology(TYPOLOGY_NUMBER_ONE, new Name("fixed cost"))));
        this.typologies.save(typologyDomainDataAssembler.toData(typologyFactory.
                createTypology(TYPOLOGY_NUMBER_TWO, new Name("Time and materials"))));

        // Customers
        CustomerFactory customerFactory = new CustomerFactory();
        CustomerDomainDataAssembler customerDomainDataAssembler = new CustomerDomainDataAssembler();
        final String CUSTOMER_SERRA_TAX_ID = "217746691";
        this.customers.save(customerDomainDataAssembler.toData(customerFactory.
                createCustomer(new TaxId(CUSTOMER_SERRA_TAX_ID), new Name("XPTO, SA"))));
        this.customers.save(customerDomainDataAssembler.toData(customerFactory.
                createCustomer(new TaxId("257578994"), new Name("XYZ, Lda"))));
        this.customers.save(customerDomainDataAssembler.toData(customerFactory.
                createCustomer(new TaxId("238419096"), new Name("Hell, LLC"))));

        // Projects
        FactoryProject factoryProject = new FactoryProject();
        ProjectDomainDataAssembler projectDomainDataAssembler = new ProjectDomainDataAssembler();

        final int TWENTY_TWENTY_TWO = 2022;
        final int TWO_THOUSAND_AND_TWENTY_THREE = 2023;
        final int ONE = 1;
        final int EIGHT = 8;
        final int TWELVE = 12;
        final int FIFTEEN = 15;
        final int TEN = 10;
        final int TWENTY = 20;
        final int THIRTY_ONE = 31;
        final int TWO_WEEKS = 2;
        final int THREE_WEEKS = 2;
        final int FOUR_WEEKS = 2;
        final int BUDGET_PROJECT_ONE = 150_000;
        final int BUDGET_PROJECT_TWO = 350_000;
        final int BUDGET_PROJECT_THREE = 750_000;
        final int PROJECT_ONE = 1;
        final int PROJECT_TWO = 2;
        final int PROJECT_THREE = 3;

        Project projectOne = factoryProject.createProject(PROJECT_ONE, new Name("Dummy 01"),
                new Description("Just a dummy project"), new BusinessSectorId(1),
                new TaxId(CUSTOMER_SERRA_TAX_ID), new ProjectTypologyId(TYPOLOGY_NUMBER_ONE));
        projectInputData(ONE, EIGHT, THIRTY_ONE, TWO_WEEKS, BUDGET_PROJECT_ONE, projectOne, JANUARY);

        Project projectTwo = factoryProject.createProject(PROJECT_TWO, new Name("Dummy 02"),
                new Description("Just another dummy project"), new BusinessSectorId(1),
                new TaxId(CUSTOMER_SERRA_TAX_ID), new ProjectTypologyId(TYPOLOGY_NUMBER_ONE));
        projectInputData(THIRTY_ONE, TWELVE, THIRTY_ONE, FOUR_WEEKS, BUDGET_PROJECT_TWO, projectTwo, MAY);

        Project projectThree = factoryProject.createProject(PROJECT_THREE, new Name("Inevitable nightmare"),
                new Description("Doomed from the start"),
                new BusinessSectorId(BUSINESS_SECTOR_NUMBER_TWO),
                new TaxId(CUSTOMER_SERRA_TAX_ID), new ProjectTypologyId(TYPOLOGY_NUMBER_TWO));
        projectDataInsertion(TWO_THOUSAND_AND_TWENTY_THREE, TEN, FIFTEEN, TWENTY, THREE_WEEKS, BUDGET_PROJECT_THREE,
                projectThree, MARCH, SEPTEMBER);

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

        final String USER_STORY_ONE = "1";
        final String USER_STORY_TWO = "2";

        UserStory userStoryOne = factoryUserStory.createUserStory(new UsNumber(USER_STORY_ONE),
                new UsText("I want to be a iguana"),
                new Actor("Farmer"), new ArrayList<>(), new Code(PROJECT_ONE));
        userStoryOne.setStatus(Status.RUNNING);
        UserStory userStoryTwo = factoryUserStory.createUserStory(new UsNumber(USER_STORY_TWO),
                new UsText("I want to be like a hippo"),
                new Actor("Manuela"), new ArrayList<>(), new Code(PROJECT_ONE));
        userStoryTwo.setStatus(Status.RUNNING);
        UserStory userStoryThree = factoryUserStory.createUserStory(new UsNumber(USER_STORY_ONE),
                new UsText("I want to marry with Cristiana"),
                new Actor("Gaiato"), new ArrayList<>(), new Code(PROJECT_TWO));
        userStoryThree.setStatus(Status.RUNNING);
        UserStory userStoryFour = factoryUserStory.createUserStory(new UsNumber(USER_STORY_TWO),
                new UsText("I want to break free"),
                new Actor("Gervásio"), new ArrayList<>(), new Code(PROJECT_TWO));
        userStoryFour.setStatus(Status.RUNNING);
        UserStory userStoryFive = factoryUserStory.createUserStory(new UsNumber(USER_STORY_ONE),
                new UsText("I want to live forever"),
                new Actor("Anacleto"), new ArrayList<>(), new Code(PROJECT_THREE));
        UserStory userStorySix = factoryUserStory.createUserStory(new UsNumber(USER_STORY_ONE),
                new UsText("I want to be like superman"),
                new Actor("Josefina"), new ArrayList<>(), new Code(PROJECT_THREE));
        UserStory userStorySeven = factoryUserStory.createUserStory(new UsNumber(USER_STORY_TWO),
                new UsText("I want to have a gameboy"),
                new Actor("Bina"), new ArrayList<>(), new Code(PROJECT_TWO));

        this.userStories.save(userStoryDomainDataAssembler.toData(userStoryOne));
        this.userStories.save(userStoryDomainDataAssembler.toData(userStoryTwo));
        this.userStories.save(userStoryDomainDataAssembler.toData(userStoryThree));
        this.userStories.save(userStoryDomainDataAssembler.toData(userStoryFour));
        this.userStories.save(userStoryDomainDataAssembler.toData(userStoryFive));
        this.userStories.save(userStoryDomainDataAssembler.toData(userStorySix));
        this.userStories.save(userStoryDomainDataAssembler.toData(userStorySeven));

        // Sprints
        SprintFactory sprintFactory = new SprintFactory();
        SprintDomainDataAssembler sprintDomainDataAssembler = new SprintDomainDataAssembler();

        final int SPRINT_NUMBER_ONE = 1;
        final int SPRINT_NUMBER_TWO = 2;
        final int SPRINT_NUMBER_THREE = 3;
        final int SPRINT_NUMBER_FOUR = 4;
        final int SPRINT_NUMBER_FIVE = 5;
        final int SPRINT_NUMBER_SIX = 6;
        final int SPRINT_NUMBER_SEVEN = 7;
        final int SPRINT_NUMBER_EIGHT = 8;
        final int SPRINT_NUMBER_NINE = 9;
        final int SPRINT_NUMBER_TEN = 1;
        final int SPRINT_NUMBER_ELEVEN = 11;
        final int SPRINT_NUMBER_TWELVE = 12;
        final int SPRINT_NUMBER_THIRTEEN = 13;
        final int SPRINT_NUMBER_FOURTEEN = 14;
        final int SPRINT_NUMBER_FIFTEEN = 15;
        final int SPRINT_NUMBER_SIXTEEN = 16;
        final int SPRINT_NUMBER_SEVENTEEN = 17;
        final int SPRINT_NUMBER_EIGHTEEN = 18;
        final int SPRINT_NUMBER_NINETEEN = 19;
        final int SPRINT_NUMBER_TWENTY = 20;
        final int SPRINT_DURATION = 2;

        final int TWO = 2;
        final int FOUR = 4;
        final int FIVE = 5;
        final int SEVEN = 7;
        final int THIRTEEN = 13;
        final int NINETEEN = 19;
        final int TWENTY_ONE = 21;
        final int TWENTY_TWO = 22;
        final int TWENTY_FOUR = 24;
        final int TWENTY_FIVE = 25;
        final int TWENTY_SIX = 26;
        final int TWENTY_SEVEN = 27;
        final int THIRTY = 30;

        Sprint sprintOne = sprintFactory.createSprint(new Code(PROJECT_ONE),
                new SprintId("p001", "s001"), new SprintNumber(SPRINT_NUMBER_ONE),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, MARCH, TWENTY_TWO), SPRINT_DURATION));
        Sprint sprintTwo = sprintFactory.createSprint(new Code(PROJECT_ONE),
                new SprintId("p001", "s002"), new SprintNumber(SPRINT_NUMBER_TWO),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, APRIL, FIVE), SPRINT_DURATION));
        Sprint sprintThree = sprintFactory.createSprint(new Code(PROJECT_ONE),
                new SprintId("p001", "s003"), new SprintNumber(SPRINT_NUMBER_THREE),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, APRIL, TWENTY_SIX), SPRINT_DURATION));
        Sprint sprintFour = sprintFactory.createSprint(new Code(PROJECT_ONE),
                new SprintId("p001", "s004"), new SprintNumber(SPRINT_NUMBER_FOUR),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, MAY, TEN), SPRINT_DURATION));
        Sprint sprintFive = sprintFactory.createSprint(new Code(PROJECT_ONE),
                new SprintId("p001", "s005"), new SprintNumber(SPRINT_NUMBER_FIVE),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, MAY, TWENTY_FOUR), SPRINT_DURATION));
        Sprint sprintSix = sprintFactory.createSprint(new Code(PROJECT_ONE),
                new SprintId("p001", "s006"), new SprintNumber(SPRINT_NUMBER_SIX),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, JUNE, SEVEN), SPRINT_DURATION));
        Sprint sprintSeven = sprintFactory.createSprint(new Code(PROJECT_ONE),
                new SprintId("p001", "s007"), new SprintNumber(SPRINT_NUMBER_SEVEN),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, JUNE, TWENTY_ONE), SPRINT_DURATION));
        Sprint sprintEight = sprintFactory.createSprint(new Code(PROJECT_ONE),
                new SprintId("p001", "s008"), new SprintNumber(SPRINT_NUMBER_EIGHT),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, JULY, NINETEEN), SPRINT_DURATION));
        Sprint sprintNine = sprintFactory.createSprint(new Code(PROJECT_TWO),
                new SprintId("p002", "s001"), new SprintNumber(SPRINT_NUMBER_NINE),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, JUNE, SEVEN), SPRINT_DURATION));
        Sprint sprintTen = sprintFactory.createSprint(new Code(PROJECT_TWO),
                new SprintId("p002", "s002"), new SprintNumber(SPRINT_NUMBER_TEN),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, JULY, FIVE), SPRINT_DURATION));
        Sprint sprintEleven = sprintFactory.createSprint(new Code(PROJECT_TWO),
                new SprintId("p002", "s003"), new SprintNumber(SPRINT_NUMBER_ELEVEN),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, AUGUST, TWO), SPRINT_DURATION));
        Sprint sprintTwelve = sprintFactory.createSprint(new Code(PROJECT_TWO),
                new SprintId("p002", "s004"), new SprintNumber(SPRINT_NUMBER_TWELVE),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, AUGUST, THIRTY), SPRINT_DURATION));
        Sprint sprintThirteen = sprintFactory.createSprint(new Code(PROJECT_TWO),
                new SprintId("p002", "s005"), new SprintNumber(SPRINT_NUMBER_THIRTEEN),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, SEPTEMBER, TWENTY_SEVEN), SPRINT_DURATION));
        Sprint sprintFourteen = sprintFactory.createSprint(new Code(PROJECT_TWO),
                new SprintId("p002", "s006"), new SprintNumber(SPRINT_NUMBER_FOURTEEN),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, OCTOBER, TWENTY_FIVE), SPRINT_DURATION));
        Sprint sprintFifteen = sprintFactory.createSprint(new Code(PROJECT_TWO),
                new SprintId("p002", "s007"), new SprintNumber(SPRINT_NUMBER_FIFTEEN),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, NOVEMBER, TWENTY_TWO), SPRINT_DURATION));
        Sprint sprintSixteen = sprintFactory.createSprint(new Code(PROJECT_TWO),
                new SprintId("p002", "s008"), new SprintNumber(SPRINT_NUMBER_SIXTEEN),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, DECEMBER, THIRTEEN), SPRINT_DURATION));
        Sprint sprintSeventeen = sprintFactory.createSprint(new Code(PROJECT_TWO),
                new SprintId("p002", "s009"), new SprintNumber(SPRINT_NUMBER_SEVENTEEN),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, JANUARY, TEN), SPRINT_DURATION));
        Sprint sprintEighteen = sprintFactory.createSprint(new Code(PROJECT_TWO),
                new SprintId("p002", "s010"), new SprintNumber(SPRINT_NUMBER_EIGHTEEN),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, FEBRUARY, SEVEN), SPRINT_DURATION));
        Sprint sprintNineteen = sprintFactory.createSprint(new Code(PROJECT_TWO),
                new SprintId("p002", "s011"), new SprintNumber(SPRINT_NUMBER_NINETEEN),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, MARCH, SEVEN), SPRINT_DURATION));
        Sprint sprintTwenty = sprintFactory.createSprint(new Code(PROJECT_TWO),
                new SprintId("p002", "s012"), new SprintNumber(SPRINT_NUMBER_TWENTY),
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, APRIL, FOUR), SPRINT_DURATION));

        saveSprintsAuxiliary(sprintDomainDataAssembler, sprintOne, sprintTwo, sprintThree, sprintFour, sprintFive,
                sprintSix, sprintSeven, sprintEight, sprintNine, sprintTen);
        saveSprintsAuxiliary(sprintDomainDataAssembler, sprintEleven, sprintTwelve, sprintThirteen, sprintFourteen,
                sprintFifteen, sprintSixteen, sprintSeventeen, sprintEighteen, sprintNineteen, sprintTwenty);

        // Profiles
        ProfileFactory profileFactory = new ProfileFactory();
        ProfileDomainDataAssembler profileDomainDataAssembler = new ProfileDomainDataAssembler();

        final int PROFILE_ONE = 1;
        final int PROFILE_TWO = 2;
        final int PROFILE_THREE = 3;

        this.profiles.save(profileDomainDataAssembler.toData(profileFactory.
                createProfile(new Name("User"), PROFILE_ONE)));
        this.profiles.save(profileDomainDataAssembler.toData(profileFactory.
                createProfile(new Name("Manager"), PROFILE_TWO)));
        this.profiles.save(profileDomainDataAssembler.toData(profileFactory.
                createProfile(new Name("Administrator"), PROFILE_THREE)));

        // Accounts
        AccountFactory accountFactory = new AccountFactory();
        AccountDomainDataAssembler accountDomainDataAssembler = new AccountDomainDataAssembler();

        final int PHONE_NUMBER_SILVA = 915_879_652;
        final int PHONE_NUMBER_COSTA = 263_650_520;
        final int PHONE_NUMBER_FERREIRA = 915_796_520;
        final int PHONE_NUMBER_CANCADO = 263_650_345;
        final int PHONE_NUMBER_URZES = 962_547_891;
        final int PHONE_NUMBER_ESQUINA = 212_349_016;
        final int PHONE_NUMBER_MOLEIRO = 930_123_456;
        final int PHONE_NUMBER_BENTO = 921_458_791;
        final int PHONE_NUMBER_FARRULO = 921_458_795;
        final int PHONE_NUMBER_CRUZES = 921_458_799;
        final int PHONE_NUMBER_BARREIROS = 921_458_803;
        final int PHONE_NUMBER_GERINGONCA = 921_458_807;
        final int PHONE_NUMBER_MANEL = 921_458_811;
        final int PHONE_NUMBER_SILVA_A = 921_458_815;



        Account accountOne = accountFactory.create(new Name("João Silva"),
                new Email("js@mymail.com"), new PhoneNumber(PHONE_NUMBER_SILVA), null);
        accountOne.changeProfile(new ProfileId(PROFILE_THREE));

        Account accountTwo = accountFactory.create(new Name("Manel Costa"),
                new Email("ms@mymail.com"), new PhoneNumber(PHONE_NUMBER_COSTA), null);
        Account accountThree = accountFactory.create(new Name("Xico Ferreira"),
                new Email("xf@gmail.com"), new PhoneNumber(PHONE_NUMBER_FERREIRA), null);
        Account accountFour = accountFactory.create(new Name("Tiago Cancado"),
                new Email("tc@mymail.com"), new PhoneNumber(PHONE_NUMBER_CANCADO), null);
        Account accountFive = accountFactory.create(new Name("Urbino das Urzes"),
                new Email("udu@mymail.com"), new PhoneNumber(PHONE_NUMBER_URZES), null);
        Account accountSix = accountFactory.create(new Name("Ze da Esquina"),
                new Email("ze@mymail.com"), new PhoneNumber(PHONE_NUMBER_ESQUINA), null);
        accountSix.changeProfile(new ProfileId(PROFILE_TWO));

        Account accountSeven = accountFactory.create(new Name("Nel Moleiro"),
                new Email("nel.m@mymail.com"), new PhoneNumber(PHONE_NUMBER_MOLEIRO), null);
        Account accountEight = accountFactory.create(new Name("Zé do Bento"),
                new Email("zb@mymail.com"), new PhoneNumber(PHONE_NUMBER_BENTO), null);
        Account accountNine = accountFactory.create(new Name("Tó Farrulo"),
                new Email("to.f@mymail.com"), new PhoneNumber(PHONE_NUMBER_FARRULO), null);
        Account accountTen = accountFactory.create(new Name("Tino das Cruzes"),
                new Email("tdc@mymail.com"), new PhoneNumber(PHONE_NUMBER_CRUZES), null);
        Account accountEleven = accountFactory.create(new Name("Quim Barrreiros"),
                new Email("qb@mymail.com"), new PhoneNumber(PHONE_NUMBER_BARREIROS), null);
        Account accountTwelve = accountFactory.create(new Name("Tiago Geringonca"),
                new Email("tg@mymail.com"), new PhoneNumber(PHONE_NUMBER_GERINGONCA), null);
        accountTwelve.changeProfile(new ProfileId(PROFILE_TWO));

        Account accountThirteen = accountFactory.create(new Name("Zé Manel"),
                new Email("zm@mymail.com"), new PhoneNumber(PHONE_NUMBER_MANEL), null);
        Account accountFourteen = accountFactory.create(new Name("Antonio Silva"),
                new Email("as@mymail.com"), new PhoneNumber(PHONE_NUMBER_SILVA_A), null);

        saveAccounts(accountDomainDataAssembler, accountOne, accountTwo, accountThree, accountFour, accountFive,
                accountSix, accountSeven);
        saveAccounts(accountDomainDataAssembler, accountEight, accountNine, accountTen, accountEleven, accountTwelve,
                accountThirteen, accountFourteen);

        // Project resources
        ProjectResourceFactory projectResourceFactory = new ProjectResourceFactory();
        ResourceDomainDataAssembler resourceDomainDataAssembler = new ResourceDomainDataAssembler();

        final int THREE = 3;
        final int EIGHTEEN = 18;
        final int THIRTY_FIVE = 35;
        final int FORTY_TWO = 42;
        final int ONE_HUNDRED = 100;

        final int RESOURCE_ONE = 1;
        final int RESOURCE_TWO = 2;
        final int RESOURCE_THREE = 3;
        final int RESOURCE_FOUR = 4;
        final int RESOURCE_FIVE = 5;
        final int RESOURCE_SIX = 6;
        final int RESOURCE_SEVEN = 7;
        final int RESOURCE_EIGHT = 8;
        final int RESOURCE_NINE = 9;
        final int RESOURCE_TEN = 10;
        final int RESOURCE_ELEVEN = 11;
        final int RESOURCE_TWELVE = 12;

        ProjectResource projectResourceOne = projectResourceFactory.createProjectResource
                (new ProjectResourceId(RESOURCE_ONE),
                new Code(PROJECT_ONE), new Email("tc@gmail.com"), Role.PROJECT_MANAGER,
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, JANUARY, TWO), LocalDate.of
                        (TWENTY_TWENTY_TWO, JULY, THIRTY_ONE)), new CostPerHour(THIRTY_FIVE),
                        new PercentageOfAllocation(TWENTY));
        ProjectResource projectResourceTwo = projectResourceFactory.createProjectResource
                (new ProjectResourceId(RESOURCE_TWO),
                new Code(PROJECT_ONE), new Email("js@gmail.com"), Role.PRODUCT_OWNER,
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, JANUARY, THREE), LocalDate.of
                        (TWENTY_TWENTY_TWO, JULY, THIRTY_ONE)), new CostPerHour(TWENTY_FIVE),
                        new PercentageOfAllocation(TWENTY));
        ProjectResource projectResourceThree = projectResourceFactory.createProjectResource
                (new ProjectResourceId(RESOURCE_THREE),
                new Code(PROJECT_ONE), new Email("ms@gmail.com"), Role.SCRUM_MASTER,
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, JANUARY, FOUR), LocalDate.of
                        (TWENTY_TWENTY_TWO, JULY, THIRTY_ONE)), new CostPerHour(TWENTY_FIVE),
                        new PercentageOfAllocation(THIRTY));
        ProjectResource projectResourceFour = projectResourceFactory.createProjectResource
                (new ProjectResourceId(RESOURCE_FOUR),
                new Code(PROJECT_ONE), new Email("xf@gmail.com"), Role.TEAM_MEMBER,
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, JANUARY, FIVE), LocalDate.of
                        (TWENTY_TWENTY_TWO, JULY, THIRTY_ONE)), new CostPerHour(TWENTY),
                        new PercentageOfAllocation(ONE_HUNDRED));
        ProjectResource projectResourceFive = projectResourceFactory.createProjectResource
                (new ProjectResourceId(RESOURCE_FIVE),
                new Code(PROJECT_ONE), new Email("nel.m@gmail.com"), Role.TEAM_MEMBER,
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, JANUARY, SEVEN), LocalDate.of
                        (TWENTY_TWENTY_TWO, JULY, THIRTY_ONE)), new CostPerHour(TWENTY),
                        new PercentageOfAllocation(ONE_HUNDRED));
        ProjectResource projectResourceSix = projectResourceFactory.createProjectResource
                (new ProjectResourceId(RESOURCE_SIX),
                new Code(PROJECT_ONE), new Email("zb@gmail.com"), Role.TEAM_MEMBER,
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, JANUARY, EIGHT), LocalDate.of(TWENTY_TWENTY_TWO, JULY,
                        TWENTY)), new CostPerHour(TWENTY), new PercentageOfAllocation(ONE_HUNDRED));
        ProjectResource projectResourceSeven = projectResourceFactory.createProjectResource
                (new ProjectResourceId(RESOURCE_SEVEN),
                new Code(PROJECT_ONE), new Email("to.f@gmail.com"), Role.TEAM_MEMBER,
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, JANUARY, EIGHT), LocalDate.of
                        (TWENTY_TWENTY_TWO, JULY, TWENTY)), new CostPerHour(TWENTY),
                        new PercentageOfAllocation(ONE_HUNDRED));
        ProjectResource projectResourceEight = projectResourceFactory.createProjectResource
                (new ProjectResourceId(RESOURCE_EIGHT),
                new Code(PROJECT_ONE), new Email("tdc@gmail.com"), Role.TEAM_MEMBER,
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, JANUARY, TEN), LocalDate.of
                        (TWENTY_TWENTY_TWO, JULY, TWENTY)), new CostPerHour(TWENTY),
                        new PercentageOfAllocation(ONE_HUNDRED));
        ProjectResource projectResourceNine = projectResourceFactory.createProjectResource
                (new ProjectResourceId(RESOURCE_NINE),
                new Code(PROJECT_TWO), new Email("qb@gmail.com"), Role.PROJECT_MANAGER,
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, MAY, FIFTEEN), LocalDate.of
                        (TWENTY_TWENTY_TWO, JULY, THIRTY)), new CostPerHour(FORTY_TWO),
                        new PercentageOfAllocation(TWENTY));
        ProjectResource projectResourceTen = projectResourceFactory.createProjectResource
                (new ProjectResourceId(RESOURCE_TEN),
                new Code(PROJECT_TWO), new Email("tg@gmail.com"), Role.PRODUCT_OWNER,
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, MAY, FIFTEEN), LocalDate.of
                        (TWENTY_TWENTY_TWO, JULY, THIRTY)), new CostPerHour(THIRTY), new
                        PercentageOfAllocation(TWENTY));
        ProjectResource projectResourceEleven = projectResourceFactory.createProjectResource
                (new ProjectResourceId(RESOURCE_ELEVEN),
                new Code(PROJECT_TWO), new Email("zm@gmail.com"), Role.TEAM_MEMBER,
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, MAY, THIRTY_ONE), LocalDate.of
                        (TWENTY_TWENTY_TWO, JULY, THIRTY)), new CostPerHour(TWENTY), new

                        PercentageOfAllocation(ONE_HUNDRED));
        ProjectResource projectResourceTwelve = projectResourceFactory.createProjectResource
                (new ProjectResourceId(RESOURCE_TWELVE),
                new Code(PROJECT_TWO), new Email("as@gmail.com"), Role.TEAM_MEMBER,
                new Period(LocalDate.of(TWENTY_TWENTY_TWO, MAY, THIRTY_ONE), LocalDate.of
                        (TWENTY_TWENTY_TWO, JULY, THIRTY)), new CostPerHour(EIGHTEEN),
                        new PercentageOfAllocation(ONE_HUNDRED));

        saveResources(resourceDomainDataAssembler, projectResourceOne, projectResourceTwo, projectResourceThree,
                projectResourceFour, projectResourceFive, projectResourceSix);
        saveResources(resourceDomainDataAssembler, projectResourceSeven, projectResourceEight, projectResourceNine,
                projectResourceTen, projectResourceEleven, projectResourceTwelve);
    }

    private void saveResources(ResourceDomainDataAssembler resourceDomainDataAssembler,
                               ProjectResource projectResourceOne, ProjectResource projectResourceTwo,
                               ProjectResource projectResourceThree, ProjectResource projectResourceFour,
                               ProjectResource projectResourceFive, ProjectResource projectResourceSix) {
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceOne));
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceTwo));
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceThree));
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceFour));
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceFive));
        this.resources.save(resourceDomainDataAssembler.toData(projectResourceSix));
    }

    private void saveAccounts(AccountDomainDataAssembler accountDomainDataAssembler, Account accountOne,
                              Account accountTwo, Account accountThree, Account accountFour, Account accountFive,
                              Account accountSix, Account accountSeven) {
        this.accounts.save(accountDomainDataAssembler.toData(accountOne));
        this.accounts.save(accountDomainDataAssembler.toData(accountTwo));
        this.accounts.save(accountDomainDataAssembler.toData(accountThree));
        this.accounts.save(accountDomainDataAssembler.toData(accountFour));
        this.accounts.save(accountDomainDataAssembler.toData(accountFive));
        this.accounts.save(accountDomainDataAssembler.toData(accountSix));
        this.accounts.save(accountDomainDataAssembler.toData(accountSeven));
    }

    private void saveSprintsAuxiliary(SprintDomainDataAssembler sprintDomainDataAssembler, Sprint sprintOne,
                                      Sprint sprintTwo, Sprint sprintThree, Sprint sprintFour, Sprint sprintFive,
                                      Sprint sprintSix, Sprint sprintSeven, Sprint sprintEight, Sprint sprintNine,
                                      Sprint sprintTen) {
        saveSprints(sprintDomainDataAssembler, sprintOne, sprintTwo, sprintThree, sprintFour, sprintFive);
        saveSprints(sprintDomainDataAssembler, sprintSix, sprintSeven, sprintEight, sprintNine, sprintTen);
    }

    private void saveSprints(SprintDomainDataAssembler sprintDomainDataAssembler, Sprint sprintOne, Sprint sprintTwo,
                             Sprint sprintThree, Sprint sprintFour, Sprint sprintFive) {
        this.sprints.save(sprintDomainDataAssembler.toData(sprintOne));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintTwo));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintThree));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintFour));
        this.sprints.save(sprintDomainDataAssembler.toData(sprintFive));
    }

    private static void projectInputData(int one, int eight, int thirtyOne, int twoWeeks, int budgetProjectOne,
                                  Project project, Month january) {
        final int TWENTY_TWENTY_TWO = 2022;
        projectDataInsertion(TWENTY_TWENTY_TWO, one, eight, thirtyOne, twoWeeks, budgetProjectOne, project,
                january, JULY);
    }

    private static void projectDataInsertion(int year, int startDay, int numberOfSprints, int endDay,
                                             int sprintDuration, int budget, Project project, Month startMonth,
                                             Month endMonth) {
        project.setProjectStatus(ProjectStatus.INCEPTION);
        project.setPeriod(LocalDate.of(year, startMonth, startDay),
                LocalDate.of(year, endMonth, endDay));
        project.setSprintDuration(sprintDuration);
        project.isNumberOfPlannedSprintsDefined(new NumberOfPlannedSprints(numberOfSprints));
        project.isBudgetAssigned(new Budget(new BigDecimal(budget)));
    }
}
