package com.pzjd.travel.ecommerce.external.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class RapidApiConfig {

    @Value("${rapid.api.key}")
    private String key;
}
