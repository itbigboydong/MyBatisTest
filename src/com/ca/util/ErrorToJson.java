package com.ca.util;

import java.io.IOException;
import java.util.Properties;

public class ErrorToJson {

	private static ErrorToJson errorToJson = new ErrorToJson();

	private static Properties properties = new Properties();

	private ErrorToJson() {

	}

	public static ErrorToJson getErrorToJson(String path) throws IOException {

		properties.load(ErrorToJson.class.getResourceAsStream(path));

		return errorToJson;

	}

	public String errorJsonMessage(String errorCode) {

		String result = properties.getProperty(errorCode);

		return "{\"code\":\"" + errorCode + "\",\"dis\":\"" + result + "\"}";

	}

}
