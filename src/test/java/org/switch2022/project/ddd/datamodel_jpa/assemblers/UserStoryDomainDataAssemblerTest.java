package org.switch2022.project.ddd.datamodel_jpa.assemblers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.datamodel_jpa.UserStoryJpa;
import org.switch2022.project.ddd.domain.model.user_story.IFactoryUserStory;
import org.switch2022.project.ddd.domain.model.user_story.UserStory;
import org.switch2022.project.ddd.domain.value_object.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UserStoryDomainDataAssembler.class)
public class UserStoryDomainDataAssemblerTest {
    @InjectMocks
    UserStoryDomainDataAssembler userStoryDomainDataAssembler;
    @MockBean
    IFactoryUserStory factoryUserStory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ensureConversionToDataModelIsSuccessful() {
        //ARRANGE
        UserStory userStory = mock(UserStory.class);
        when(userStory.getUsId()).thenReturn("us001");
        when(userStory.getAcceptanceCriteria()).thenReturn(Collections.singletonList("AC1"));
        when(userStory.getUsNumber()).thenReturn("1");
        when(userStory.getActor()).thenReturn("User");
        when(userStory.getUsText()).thenReturn("As a user, I want to login to the system.");
        when(userStory.getStatus()).thenReturn("Planned");
        when(userStory.getProjectCode()).thenReturn("p001");

        UserStoryJpa expected = new UserStoryJpa("us001", Collections.singletonList("AC1"), "1",
                "User", "As a user, I want to login to the system.",
                "Planned", "p001");

        //ACT
        UserStoryJpa result = userStoryDomainDataAssembler.toData(userStory);

        //ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureConversionToDomainModelIsSuccessful() {
        //ARRANGE
        UserStoryJpa userStoryJpa = new UserStoryJpa("us001", Collections.singletonList("AC1"),
                "us001", "User", "As a user, I want to login to the system.",
                "Planned", "p001");

        UsNumber expectedUserStoryNumber = new UsNumber("1");
        UsText expectedUserStoryText = new UsText("As a user, I want to login to the system.");
        Actor expectedActor = new Actor("User");
        List<AcceptanceCriteria> expectedAcceptanceCriteria = Collections.singletonList(
                new AcceptanceCriteria("AC1"));
        Code expectedProjectCode = new Code(1);

        UserStory expectedUserStory = mock(UserStory.class);
        when(factoryUserStory.createUserStory(expectedUserStoryNumber, expectedUserStoryText,
                expectedActor, expectedAcceptanceCriteria, expectedProjectCode)).thenReturn(expectedUserStory);

        //ACT
        UserStory result = userStoryDomainDataAssembler.toDomain(userStoryJpa);

        //ASSERT
        assertEquals(expectedUserStory, result);
        verify(factoryUserStory).createUserStory(expectedUserStoryNumber, expectedUserStoryText,
                expectedActor, expectedAcceptanceCriteria, expectedProjectCode
        );
    }
}
