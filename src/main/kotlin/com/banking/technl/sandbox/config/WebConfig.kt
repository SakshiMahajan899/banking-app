package com.banking.technl.sandbox.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**") // Allow all endpoints
                    .allowedOrigins("http://localhost:8082") // Allow frontend origin
                    .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
                    .allowedHeaders("*") // Allow all headers
                    .allowCredentials(true) // Allow cookies or credentials
            }
        }
    }
}
