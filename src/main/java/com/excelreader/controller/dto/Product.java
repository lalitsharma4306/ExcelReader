package com.excelreader.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private String context;
    private String title;
    private String type;
    private String sku;
    private String name;
    private String image1;
    private String itemCondition;
    private String brand;
    private Manufacturer manufacturer;
    private AggregateRating aggregateRating;
//    private String description;
    private Offer offers;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Manufacturer {
        private String type;
        private String name;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AggregateRating {
        private String type;
        private double ratingValue;
        private int ratingCount;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Offer {
        private String type;
        private String availability;
        private String priceCurrency;
        private double price;
        private String url;
    }
}

