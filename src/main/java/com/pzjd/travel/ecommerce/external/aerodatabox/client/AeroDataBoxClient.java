package com.pzjd.travel.ecommerce.external.aerodatabox.client;

import com.pzjd.travel.ecommerce.dto.external.aerodatabox.AirportDto;
import com.pzjd.travel.ecommerce.dto.external.aerodatabox.AirportListWrapperDto;
import com.pzjd.travel.ecommerce.external.aerodatabox.config.AeroDataBoxConfig;
import com.pzjd.travel.ecommerce.external.config.RapidApiConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AeroDataBoxClient {

    private final RestTemplate restTemplate;
    private final RapidApiConfig rapidApiConfig;
    private final AeroDataBoxConfig aeroDataBoxConfig;

    public List<AirportDto> getAirportByText(final String text) {
        URI url = UriComponentsBuilder.fromHttpUrl(aeroDataBoxConfig.getEndpoint() + "airports/search/term")
                .queryParam("q", text)
                .queryParam("withFlightInfoOnly", "true")
                .queryParam("fields", "name,id")
                .build().encode().toUri();
        HttpEntity<String> entity = createHttpRequestHeaders();
        try {
            ResponseEntity<AirportListWrapperDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, AirportListWrapperDto.class);
            if (response.getBody() != null) {
                return response.getBody().getItems();
            } else {
                return Collections.emptyList();
            }
        } catch (RestClientException e) {
            return Collections.emptyList();
        }
    }

    private HttpEntity<String> createHttpRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", aeroDataBoxConfig.getHost());
        headers.set("x-rapidapi-key", rapidApiConfig.getKey());
        return new HttpEntity<>(headers);
    }
}
