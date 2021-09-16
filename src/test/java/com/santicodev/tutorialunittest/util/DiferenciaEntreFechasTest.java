package com.santicodev.tutorialunittest.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiferenciaEntreFechasTest {

    @Autowired
    DiferenciaEntreFechas diferenciaEntreFechas;

    @Test
    void calculateYearsOfIndependency() {
        LocalDate today = LocalDate.now();
        String fechaIndependencia = "27/02/1844";

        Period resultado = diferenciaEntreFechas.calculateYearsOfIndependency(fechaIndependencia);

        Assertions.assertEquals(20, resultado.getDays());
        Assertions.assertEquals(6, resultado.getMonths());
        Assertions.assertEquals(177, resultado.getYears());
    }
}