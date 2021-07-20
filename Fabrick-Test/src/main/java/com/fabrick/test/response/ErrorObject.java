package com.fabrick.test.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorObject {

	private String code;
	private String description;
	private String params;
	
}
