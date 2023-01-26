package org.switch2022.project.utils.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;

import org.switch2022.project.utils.dto.ProjectDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectListMapperTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */
    Project  projectOne, projectTwo;
    ProjectDTO projDTOOne, projDTOTwo;
    List<ProjectDTO> projsDTOOne;
    List<Project> projects;
    ProjectTypology projectTypologyOne;
    BusinessSector businessSectorOne;
    Customer customerOne;

    @BeforeEach
    void setUp() {

        projectTypologyOne = new ProjectTypology("Fixed Cost");
        customerOne = new Customer("ISEP", "222333444");
        businessSectorOne = new BusinessSector("fishing");

        // Projects created.
        projectOne = new Project("1A", "Mobile Software", customerOne, projectTypologyOne, businessSectorOne );
        projectTwo = new Project("2B", "Software Development Management",customerOne, projectTypologyOne,businessSectorOne);

        //ProjectsDTO
        projDTOOne = ProjectMapper.getDTOFromProject(projectOne);
        projDTOTwo = ProjectMapper.getDTOFromProject(projectTwo);

        projects = new ArrayList<>();
        projects.add(projectOne);
        projects.add(projectTwo);

        projsDTOOne = new ArrayList<>();
        projsDTOOne.add(projDTOOne);
        projsDTOOne.add(projDTOTwo);
    }

    @AfterEach
    void tearDown() {
        customerOne = null;
        projectTypologyOne = null;
        businessSectorOne = null;
        projectOne = null;
        projectTwo = null;
        projDTOOne = null;
        projDTOTwo = null;
        projsDTOOne.clear();
        projects.clear();
    }

    /**
     * getListDTOFromProjects(List<Project> projects)
     */

    @Test
    void ensureThatProjectListIsConvertedIntoProjectListDTO() {
        // ARRANGE
        List<ProjectDTO> expected = projsDTOOne;
        //ACT
        List<ProjectDTO> result = ProjectListMapper.projectsToDTO(projects);
        // ASSERT
        assertEquals(expected, result);
    }

}