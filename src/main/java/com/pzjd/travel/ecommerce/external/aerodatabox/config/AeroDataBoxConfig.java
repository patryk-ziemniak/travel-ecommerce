package com.pzjd.travel.ecommerce.external.aerodatabox.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AeroDataBoxConfig {

    @Value("${aerodatabox.api.host}")
    private String host;
    @Value("${aerodatabox.api.endpoint}")
    private String endpoint;
}
