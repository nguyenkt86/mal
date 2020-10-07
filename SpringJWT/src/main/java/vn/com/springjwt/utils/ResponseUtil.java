package vn.com.springjwt.utils;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;

import vn.com.springjwt.model.MessageInfo;
import vn.com.springjwt.model.ResponseModel;

public class ResponseUtil {

	public static ResponseEntity<ResponseModel> getSuccess(Object data) {
		ResponseModel responseModel = new ResponseModel(true, null, data);

		return ResponseEntity.ok().body(responseModel);
	}

	public static ResponseEntity<ResponseModel> getError(HttpStatus status, String code, String message) {
		Object finalMessage = null;

		if (message != null && !message.isEmpty()) {
			if (JsonUtil.isJsonValid(message))
				finalMessage = new Gson().fromJson(message, Object.class);
			else
				finalMessage = message;
		} else
			finalMessage = "Have some error!";

		MessageInfo messageInfo = new MessageInfo(code, finalMessage);
		ResponseModel responseModel = new ResponseModel(false, Collections.singletonList(messageInfo), null);

		return ResponseEntity.ok().body(responseModel);
	}

	public static ResponseEntity<ResponseModel> getNormal(boolean success, HttpStatus status, String code,
			String message, Object data) {
		MessageInfo messageInfo = new MessageInfo(code, message);
		ResponseModel responseModel = new ResponseModel(success, Collections.singletonList(messageInfo), data);

		return ResponseEntity.status(status).body(responseModel);
	}
}
