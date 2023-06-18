package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.typology.ITypologyFactory;
import org.switch2022.project.ddd.domain.model.typology.ITypologyRepository;
import org.switch2022.project.ddd.domain.model.typology.Typology;
import org.switch2022.project.ddd.dto.TypologyDto;
import org.switch2022.project.ddd.dto.mapper.TypologyMapper;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = TypologyServiceTest.class
)
class TypologyServiceTest {
    @InjectMocks
    TypologyService typologyService;
    @MockBean
    ITypologyRepository typologyRepository;
    @MockBean
    ITypologyFactory factoryTypology;
    @MockBean
    TypologyMapper mapper;

    /**
     * Method: createTypology().
     * Scenario 01: The typology is created successfully.
     * Expected return: TRUE.
     */
    @DisplayName("Typology created successfully")
    @Test
    void ensureTypologyIsCreatedSuccessfully() {
        //Arrange

        when(typologyRepository.save(any())).thenReturn(true);
        //Act
        boolean result = typologyService.createTypology("typology01");

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: The typology is not created.
     * Expected return:  AlreadyExistsInRepoException with "The typology already exists in the
     * repository." .
     */
    @DisplayName("Typology not created")
    @Test
    void ensureTypologyIsCreatedInsuccessfully() {
        //Arrange
        String expected = "The typology already exists in the repository.";

        when(typologyRepository.save(any())).thenThrow(new AlreadyExistsInRepoException(expected));

        //Act
        AlreadyExistsInRepoException result =
                assertThrows(AlreadyExistsInRepoException.class,
                        () -> typologyService.createTypology("typology01"));

        //Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * Method: requestAllTypologies().
     * Scenario 01: returns a list of TypologyDto.
     * Expected return: a list of all TypologyDtos.
     */
    @DisplayName("list of all TypologyDtos")
    @Test
    void ensureRequestForTheListWithAllTypologies() {
        //Arrange
        List<TypologyDto> expected = new ArrayList<>();
        Typology typologyOne = mock(Typology.class);
        Typology typologyTwo = mock(Typology.class);
        TypologyDto typologyDto = mock(TypologyDto.class);
        TypologyDto typologyTwoDto = mock(TypologyDto.class);
        List<Typology> typologies = new ArrayList<>();

        typologies.add(typologyOne);
        typologies.add(typologyTwo);

        when(typologyRepository.findAll()).thenReturn(typologies);
        when(mapper.typologyToDto(typologyOne)).thenReturn(typologyDto);
        when(mapper.typologyToDto(typologyTwo)).thenReturn(typologyTwoDto);

        expected.add(typologyDto);
        expected.add(typologyTwoDto);

        //Act
        List<TypologyDto> result = typologyService.requestAllTypologies();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 02: returns and emtpy list because there are no typologies.
     */
    @DisplayName("list of all TypologyDtos is empty")
    @Test
    void ensureThatReturnsAnEmptyListBecauseThereAreNoTypologies() {
        //Arrange
        List<TypologyDto> expected = new ArrayList<>();
        List<Typology> typologies = new ArrayList<>();
        when(typologyRepository.findAll()).thenReturn(typologies);
        //Act
        List<TypologyDto> result = typologyService.requestAllTypologies();
        //Assert
        assertEquals(expected, result);
    }
    /**
     * Method: findTypologyByTypologyName().
     * Scenario 01: ensure that a TypologyDto is returned successfully.
     */
    @Test
    void ensureItReturnsATypologyDto() {
        // Arrange
        String typologyName = "typology name";
        Typology typology = mock(Typology.class);
        when(typologyRepository.findTypologyByTypologyName(typologyName)).thenReturn(typology);

        TypologyDto dtoDouble = mock(TypologyDto.class);
        when(mapper.typologyToDto(typology)).thenReturn(dtoDouble);

        // Act
        TypologyDto result = typologyService.getByTypologyName(typologyName);

        // Assert
        assertNotNull(result);
        assertEquals(dtoDouble, result);
    }
}