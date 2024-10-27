package com.example.ecommerce.configuration;

import com.mercadopago.MercadoPagoConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MPConfiguration {
    public MPConfiguration() {
        String accessToken = "APP_USR-2564266601012376-102112-4ff3b3438bf4713514364cbbdc8ed09e-2049869592"; // Reemplaza con tu access token de producción
        MercadoPagoConfig.setAccessToken(accessToken);
    }

}

