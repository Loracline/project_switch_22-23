package org.switch2022.project.ddd.datamodel_jpa;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductBacklogJpaTest {

    @Test
    void ensureProductBacklogJpaIsCreated(){
        //Act
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa();

        //Assert
        assertNotNull(productBacklogJpa);
    }

    @Test
    void getProductBacklogId() {
        //Arrange
        List<String> userStories = new ArrayList<>();
        userStories.add("p001_us001");
        userStories.add("p001_us002");
        userStories.add("p001_us003");
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb",userStories);
        String expected =  "p001_pb";

        //Act
        String result = productBacklogJpa.getProductBacklogId();

        //Assert
        assertEquals(expected,result);

    }

    @Test
    void getUserStories() {
        //Arrange
        List<String> userStories = new ArrayList<>();
        userStories.add("p001_us001");
        userStories.add("p001_us002");
        userStories.add("p001_us003");
        ProductBacklogJpa productBacklogJpa = new ProductBacklogJpa("p001_pb",userStories);
        List<String> expected =  Arrays.asList("p001_us001","p001_us002", "p001_us003");

        //Act
        List<String> result =  productBacklogJpa.getUserStories();

        //Assert
        assertEquals(expected,result);
    }

}