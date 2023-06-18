package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryStatusDtoTest {

    @Test
    void ensureUserStoryStatusDtoIsCreated(){
        //Act

        UserStoryStatusDto userStoryStatusDto =
                new UserStoryStatusDto("us001", "p001", "CLOSED");

        //Assert
        assertNotNull(userStoryStatusDto);
    }

}