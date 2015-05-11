package com.fzc.rhinooffice.common.utils;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.fzc.rhinooffice.module.entity.Business;
import com.fzc.rhinooffice.module.entity.Email;
import com.fzc.rhinooffice.module.entity.Flow;
import com.fzc.rhinooffice.module.entity.News;
import com.fzc.rhinooffice.module.entity.Notify;
import com.fzc.rhinooffice.module.entity.User;

public class JsonUtil {

	// 用户信息
	public static User analysis_user(String json) {
		User user = new User();

		try {
			JSONObject jsonObject = new JSONObject(json);
			user.A_USER_NAME = jsonObject.getString("A_USER_NAME");
			user.a_sessid = jsonObject.getString("a_sessid");
			String arrs = jsonObject.getString("A_MENU_STR");
			String[] temp = arrs.split(",");

			user.A_MENU_STR = new int[temp.length + 1];
			for (int i = 0; i < temp.length; i++) {
				user.A_MENU_STR[i] = Integer.parseInt(temp[i]);
			}
			jsonObject = null;
			temp = null;
		} catch (JSONException e1) {
			e1.printStackTrace();
			return null;
		}

		/*
		 * try { ObjectMapper objectMapper = new ObjectMapper(); user =
		 * objectMapper.readValue(json, User.class); } catch (JsonParseException
		 * e) { e.printStackTrace(); return null; } catch (JsonMappingException
		 * e) { e.printStackTrace(); return null; } catch (IOException e) {
		 * e.printStackTrace(); return null; }
		 */

		return user;
	}

	// 用户信息
	public static Email analysis_email(String json) {
		Email email = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			email = objectMapper.readValue(json, Email.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return email;
	}

	//通知信息
	public static Notify analysis_notify(String json) {
		Notify notify = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			notify = objectMapper.readValue(json, Notify.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return notify;
	}
	
	public static News analysis_news(String json) {
		System.out.println("--analysis_news--->>"+json);
		News news = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			news = objectMapper.readValue(json, News.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return news;
	}
	
	public static Flow analysis_flow(String json) {
		System.out.println("--analysis_news--->>"+json);
		Flow flow = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			flow = objectMapper.readValue(json, Flow.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return flow;
	}
	
	public static Business analysis_business(String json) {
		System.out.println("--Business--->>"+json);
		Business business = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			business = objectMapper.readValue(json, Business.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return business;
	}

}
