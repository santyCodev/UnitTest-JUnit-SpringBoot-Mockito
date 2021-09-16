package com.santicodev.tutorialunittest.controller;

import com.santicodev.tutorialunittest.models.Country;
import com.santicodev.tutorialunittest.models.CountryResponse;
import com.santicodev.tutorialunittest.repositories.CountryRepository;
import com.santicodev.tutorialunittest.util.DiferenciaEntreFechas;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IndependencyControllerTest {

    /*@Autowired
    CountryResponse countryResponse;
    @Autowired
    Optional<Country> country;*/
    @Autowired
    DiferenciaEntreFechas diferenciaEntreFechas = new DiferenciaEntreFechas();

    CountryRepository countryRepositoryMock = Mockito.mock(CountryRepository.class);

    IndependencyController independencyController
            = new IndependencyController(countryRepositoryMock, diferenciaEntreFechas);

    @BeforeEach
    void setUp() {
        System.out.println("Antes de la prueba");
        Country mockCountry = new Country();
        mockCountry.setIsoCode("DO");
        mockCountry.setCountryIdependenceDate("27/02/1844");
        mockCountry.setCountryId((long) 1);
        mockCountry.setCountryName("Republica Dominicana");
        mockCountry.setCountryCapital("Santo Domingo");

        Mockito.when(countryRepositoryMock.findCountryByIsoCode("DO")).thenReturn(mockCountry);
    }

    @Test
    void getCountryDetailsWithValidCountryCode() {
        ResponseEntity<CountryResponse> respuestaServicioTest;
        respuestaServicioTest = independencyController.getCountryDetails("DO");

        Assertions.assertEquals("Republica Dominicana", respuestaServicioTest.getBody().getCountryName());
    }

    @Test
    void getCountryDetailsWithInvalidCountryCode() {
        ResponseEntity<CountryResponse> respuestaServicioTest;
        respuestaServicioTest = independencyController.getCountryDetails("IT");

        Assertions.assertNull(respuestaServicioTest.getBody().getCountryName());
    }
}