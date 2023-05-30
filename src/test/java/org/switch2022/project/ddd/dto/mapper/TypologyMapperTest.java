package org.switch2022.project.ddd.dto.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.typology.Typology;
import org.switch2022.project.ddd.dto.TypologyDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TypologyMapperTest {

    /**
     * Method TypologyDto(typology).
     * Scenario 1: it should return a dto from the Typology given.
     * Expected return: a typologyDto.
     */

    @Test
    void ensureThatItReturnsATypologyDto() {
        //Arrange
        TypologyMapper typologyMapper = new TypologyMapper();
        Typology typology = mock(Typology.class);
        String typologyId = "PT001";
        String typologyName = "typology one";

        when(typology.getTypologyId()).thenReturn(typologyId);
        when(typology.getTypologyName()).thenReturn(typologyName);

        TypologyDto expected = new TypologyDto(typologyId, typologyName);
        //Act
        TypologyDto  result = typologyMapper.typologyToDto(typology);
        //Assert
        assertEquals(expected, result);
    }

}