package vn.com.springjwt.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {

	public static boolean isJsonValid(String jsonNeedCheck) {
		try {
			new JSONObject(jsonNeedCheck);
		} catch (JSONException ex) {
			try {
				new JSONArray(jsonNeedCheck);
			} catch (JSONException e2) {
				return false;
			}
		}
		return true;
	}
}
