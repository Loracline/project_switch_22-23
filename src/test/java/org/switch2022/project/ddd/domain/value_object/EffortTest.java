package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EffortTest {

    @Test
    void ensureSameValueAs_EqualValues() {
        assertTrue(Effort.ONE.sameValueAs(Effort.ONE));
    }

    @Test
    void ensureSameValueAs_DifferentValues() {
        assertFalse(Effort.ONE.sameValueAs(Effort.TWO));
    }
}