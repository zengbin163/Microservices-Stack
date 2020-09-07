package com.chihuo.food.infrastructure.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {

	private ResponseStatus status;
	private Object data;
	private String errorMsg;
	private Integer errorCode;
	
	public static Response ok() {
		return Response.builder().status(ResponseStatus.SUCCESS).build();
	}

	public static Response ok(Object data) {
		return Response.builder().status(ResponseStatus.SUCCESS).data(data).build();
	}
	
	public static Response failed(String errormsg) {
		return Response.builder().errorMsg(errormsg).build();
	}

	public static Response failed(ResponseStatus status, String errormsg) {
		return Response.builder().status(status).errorCode(status.getCode()).errorMsg(errormsg).build();
	}
	
}
