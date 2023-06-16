package org.switch2022.project.ddd.integrationTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.switch2022.project.ddd.dto.ProjectCreationDto;
import org.switch2022.project.ddd.dto.ProjectDto;
import org.switch2022.project.ddd.dto.UserStoryDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ProjectIT {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ensureProjectsAreCreatedAndReturnOk() throws Exception {
        //Arrange
        ProjectCreationDto newProject = new ProjectCreationDto("BetaProject",
                "I want to create a project",
                "bs001", "217746691", "pt001");
        ProjectDto newProjectCreated = new ProjectDto("p004", "BetaProject", "xpto, sa",
                "planned", "", "");
        ProjectDto projectOne = new ProjectDto("p001", "dummy 01", "xpto, sa", "closed", "2022-03-01", "2022-07-31");
        ProjectDto projectTwo = new ProjectDto("p002", "dummy 02", "xpto, sa", "closed", "2022-05-31", "2023-04-29");
        ProjectDto projectThree = new ProjectDto("p003", "inevitable nightmare", "xpto, sa", "inception", "2023-03-10",
                "2023-09-20");
        List<ProjectDto> projects = new ArrayList<>();
        projects.add(projectOne);
        projects.add(projectTwo);
        projects.add(projectThree);
        projects.add(newProjectCreated);
        UserStoryDto usdto = new UserStoryDto("us002", "i want to have a gameboy"
                , "planned");
        List<UserStoryDto> userStories = new ArrayList<>();
        userStories.add(usdto);

        // First call: Ensure that this project does not exist in DB yet.
        // GET projects/p004
        // Act - ONE

        MvcResult firstResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/projects/" + "p004")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String firstResultContent = firstResult.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(firstResultContent);
        // Assert - ONE

        assertNotNull(firstResultContent);
        assertEquals(404, jsonNode.get("statusCode").asInt());

        // Act - TWO
        MvcResult secondResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/projects")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newProject))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String secondResultContent = secondResult.getResponse().getContentAsString();

        // Assert - TWO
        assertNotNull(secondResultContent);
        assertEquals("p004", secondResultContent);

        // Third call: Confirm that this new project now exists in the DB.
        // Act - THREE
        MvcResult thirdResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/projects/p004")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String thirdResultContent = thirdResult.getResponse().getContentAsString();

        // Assert - THREE
        assertNotNull(thirdResultContent);
        assertEquals(objectMapper.writeValueAsString(newProjectCreated), thirdResultContent);

        //Forth call: confirm that the new project is in the list of all projects
        //Act = FOUR
        MvcResult forthResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/projects")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String forthResultContent = forthResult.getResponse().getContentAsString();

        // Assert - FOUR
        assertNotNull(forthResultContent);
        assertEquals(objectMapper.writeValueAsString(projects), forthResultContent);

        //Fifth call: verify that the productBacklog can be retrieved
        //Act FIVE

        MvcResult fifthResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/projects/p002/productBacklog")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String fifthResultContent = fifthResult.getResponse().getContentAsString();

        // Assert - FOUR
        assertNotNull(fifthResultContent);
        assertEquals(objectMapper.writeValueAsString(userStories), fifthResultContent);
    }
}

