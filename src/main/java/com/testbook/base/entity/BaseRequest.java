package com.testbook.base.entity;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public  class BaseRequest<REQ extends BaseEntity<?>>  {
    @NonNull
    private RequestMetadata metadata;
    
    @NonNull
    private REQ requestBody;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestMetadata {
        private String correlationId;
        private String originService;
        private Instant timestamp;
    }
}