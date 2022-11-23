package com.bank.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class Router {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder) {

        return routeLocatorBuilder.routes()
                .route(pathStr -> pathStr
                        .path("/greatness/ACCOUNTS/**")
                        .filters(filter -> filter.rewritePath("/greatness/ACCOUNTS/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", new Date().toString()))
                        .uri("lb://ACCOUNTS")).
                route(pathStr -> pathStr
                        .path("/greatness/LOANS/**")
                        .filters(filter -> filter.rewritePath("greatness/LOANS/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", new Date().toString()))
                        .uri("lb://LOANS")).
                route(pathStr -> pathStr
                        .path("/greatness/CARDS/**")
                        .filters(filter -> filter.rewritePath("greatness/CARDS/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", new Date().toString()))
                        .uri("lb://CARDS")).build();

    }
}
