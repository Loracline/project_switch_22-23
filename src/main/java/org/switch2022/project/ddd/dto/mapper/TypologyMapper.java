package org.switch2022.project.ddd.dto.mapper;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.model.typology.Typology;
import org.switch2022.project.ddd.dto.TypologyDto;

@Component
public class TypologyMapper {

    /**
     * This method converts a typology into a Dto.
     *
     * @param typology one must convert.
     * @return Dto carrying data.
     */

    public TypologyDto typologyToDto(Typology typology) {
        return new TypologyDto(typology.getTypologyName());
    }
}
