package org.switch2022.project.model.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.ProjectTypology;
import org.switch2022.project.model.container.ProjectTypologyContainer;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ProjectTypologyContainerTest {
    ProjectTypology projectTypologyOne, projectTypologyTwo;
    List<ProjectTypology> typologies;
    ProjectTypologyContainer projectTypologyContainerReference;
    @BeforeEach
    void setUp(){

        projectTypologyOne = new ProjectTypology("Fixed Cost");
        projectTypologyTwo= new ProjectTypology("Fixed time and materials");

        typologies = new ArrayList<>();
        typologies.add(projectTypologyOne);
        typologies.add(projectTypologyTwo);

        projectTypologyContainerReference= new ProjectTypologyContainer(typologies);
    }
    @AfterEach
    void tearDown() {
        projectTypologyOne = null;
        projectTypologyTwo= null;
        typologies.clear();
        projectTypologyContainerReference= null;
    }
    @Test
    void ensureProjectTypologyCreatedSuccessfully(){
        boolean expected= true;
        boolean result=projectTypologyContainerReference.createProjectTypology("Fixed new typology");
        assertEquals(expected, result);
    }
    @Test
    void ensureProjectTypologyCreatedUnsuccessfully(){
        boolean expected= false;
        boolean result=projectTypologyContainerReference.createProjectTypology("Fixed Cost");
        assertEquals(expected, result);
    }
    @Test
    void ensureProjectTypologyCreatedUnsuccessfully_CaseInsensitive(){
        boolean expected= false;
        boolean result=projectTypologyContainerReference.createProjectTypology("fixed cost");
        assertEquals(expected, result);
    }
    @Test
    void ensureProjectTypologyCreatedSuccessfully_CaseInsensitive(){
        boolean expected= true;
        boolean result=projectTypologyContainerReference.createProjectTypology("fIxEd new typology");
        assertEquals(expected, result);
    }
}