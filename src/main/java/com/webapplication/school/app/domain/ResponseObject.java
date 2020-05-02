package com.webapplication.school.app.domain;

public class ResponseObject {

	private String response;
	private String message;
	private String status;
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ResponseObject(String response, String message, String status) {
		super();
		this.response = response;
		this.message = message;
		this.status = status;
	}
	public ResponseObject() {
		super();
	}
	@Override
	public String toString() {
		return "ResponseObject [response=" + response + ", message=" + message + ", status=" + status + "]";
	}
	
	
}
