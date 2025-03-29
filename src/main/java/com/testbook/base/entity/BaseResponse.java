package com.testbook.base.entity;

import java.time.Instant;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<REQ extends BaseEntity<?>> {
	private ResponseMetadata metadata;
	private ErrorResponse error;
	private REQ responseBody;


	
	@Data
	@NoArgsConstructor
	public static class ResponseMetadata {
		private boolean success;
		private Instant timestamp;
		private Pagination pagination;
		private String correlationId;
        private String originService;
	}
	
	@Data
	@NoArgsConstructor
	public static class ErrorResponse {
		private String code;
		private String message;
		private Map<String, String> details;
	}

	@Data
	@NoArgsConstructor
	public static class Pagination {
		private int currentPage;
		private int pageSize;
		private long totalElements;
		private int totalPages;
		private boolean first;
		private boolean last;
	}
}