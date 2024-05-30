package com.meilleurtaux.test.service;


import com.meilleurtaux.test.exception.ExternalServiceException;
import com.meilleurtaux.test.exception.InvalidPostalCodeException;
import com.meilleurtaux.test.exception.TownNotFoundException;
import com.meilleurtaux.test.record.Town;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GeoServiceTest {

    @Value("${geo.api.link}")
    private String geoApiUrl;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GeoService geoService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(geoService, "geoApiUrl", geoApiUrl);
    }

    @Test
    void testGetTownsByPostalCodeSuccess() {

        String postalCode = "75000";
        Town[] communes = {new Town("Paris", 2140526)};
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(geoApiUrl)
                .queryParam("codePostal", postalCode)
                .queryParam("fields", "nom,population");

        when(restTemplate.getForObject(uriBuilder.toUriString(), Town[].class)).thenReturn(communes);

        // Act
        List<Town> result = geoService.getTownsByPostalCode(postalCode);

        // Assert
        assertEquals(1, result.size());
        assertEquals("Paris", result.get(0).name());
        assertEquals(2140526, result.get(0).population());
    }

    @Test
    void testGetTownByPostalCodeNotFound() {

        String postalCode = "99999";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(geoApiUrl)
                .queryParam("codePostal", postalCode)
                .queryParam("fields", "nom,population");

        when(restTemplate.getForObject(uriBuilder.toUriString(), Town[].class)).thenReturn(new Town[0]);

        // Act & Assert
        assertThrows(TownNotFoundException.class, () -> {
            geoService.getTownsByPostalCode(postalCode);
        });
    }

    @Test
    void testGetTownByInvalidPostalCode() {

        String postalCode = "INVALID";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(geoApiUrl)
                .queryParam("codePostal", postalCode)
                .queryParam("fields", "nom,population");

        when(restTemplate.getForObject(uriBuilder.toUriString(), Town[].class)).thenReturn(new Town[0]);

        // Act & Assert
        assertThrows(InvalidPostalCodeException.class, () -> {
            geoService.getTownsByPostalCode(postalCode);
        });
    }

    @Test
    void testGetTownByPostalCodeExternalServiceError() {

        String postalCode = "75000";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(geoApiUrl)
                .queryParam("codePostal", postalCode)
                .queryParam("fields", "nom,population");

        when(restTemplate.getForObject(uriBuilder.toUriString(), Town[].class))
                .thenThrow(new RestClientException("External service error"));

        // Act & Assert
        ExternalServiceException exception = assertThrows(ExternalServiceException.class, () -> {
            geoService.getTownsByPostalCode(postalCode);
        });

        assertEquals("Error occurred while calling external service", exception.getMessage());
    }
}
