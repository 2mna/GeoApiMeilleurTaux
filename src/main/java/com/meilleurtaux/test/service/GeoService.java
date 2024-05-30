package com.meilleurtaux.test.service;

import com.meilleurtaux.test.exception.ExternalServiceException;
import com.meilleurtaux.test.exception.InvalidPostalCodeException;
import com.meilleurtaux.test.exception.TownNotFoundException;
import com.meilleurtaux.test.record.Town;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;


@Service
public class GeoService implements IGeoService {

    @Value("${geo.api.link}")
    private String geoApiUrl;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public List<Town> getTownsByPostalCode(String postalCode) {
        if (StringUtils.isBlank(postalCode) || !postalCode.matches("\\d+")) {
            throw new InvalidPostalCodeException("Invalid postal code: " + postalCode);
        }
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(geoApiUrl)
                .queryParam("codePostal", postalCode)
                .queryParam("fields", "nom,population");

        try {
            Town[] towns = restTemplate.getForObject(uriBuilder.toUriString(), Town[].class);
            if (towns == null || towns.length == 0) {
                throw new TownNotFoundException("No town found for postal code: " + postalCode);
            }
            return Arrays.asList(towns);
        } catch (RestClientException e) {
            throw new ExternalServiceException("Error occurred while calling external service", e);
        }
    }
}



