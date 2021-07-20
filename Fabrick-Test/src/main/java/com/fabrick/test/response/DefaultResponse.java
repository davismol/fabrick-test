package com.fabrick.test.response;

import java.util.List;

import lombok.Data;

@Data
public abstract class DefaultResponse {

	protected String status;
	protected List<ErrorObject> errors;

}
