package vn.com.springjwt.model;
import java.util.List;

import org.springframework.lang.NonNull;
public class ResponseModel {
	@NonNull
	private boolean success;
	private List<MessageInfo> message;
	private Object data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<MessageInfo> getMessage() {
		return message;
	}

	public void setMessage(List<MessageInfo> message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ResponseModel() {
		super();
	}

	public ResponseModel(boolean success, List<MessageInfo> message, Object data) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
	}
}
