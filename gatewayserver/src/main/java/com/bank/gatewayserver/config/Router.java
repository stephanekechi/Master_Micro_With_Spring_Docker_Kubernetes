package com.bank.gatewayserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Configuration
@Slf4j
public class Router {

    //private static final String UTC_DATE_FORMAT = "dd-MMMM-yyyy HH:mm:ss";
    private static final String UTC_DATE_FORMAT = "E, dd MMM yyyy HH:mm:ss z";
    private static final String UTC_TIME_ZONE = "UTC";

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder) {

        return routeLocatorBuilder.routes()
                .route(pathStr -> pathStr
                        .path("/greatness/ACCOUNTS/**")
                        .filters(filter -> filter.rewritePath("/greatness/ACCOUNTS/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", getUtcDateStr()))
                        .uri("lb://ACCOUNTS")).
                route(pathStr -> pathStr
                        .path("/greatness/LOANS/**")
                        .filters(filter -> filter.rewritePath("greatness/LOANS/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", getUtcDateStr()))
                        .uri("lb://LOANS")).
                route(pathStr -> pathStr
                        .path("/greatness/CARDS/**")
                        .filters(filter -> filter.rewritePath("greatness/CARDS/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", getUtcDateStr()))
                        .uri("lb://CARDS")).build();

    }

    private String getUtcDateStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UTC_DATE_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(UTC_TIME_ZONE));

        return simpleDateFormat.format(new Date());
    }
}
