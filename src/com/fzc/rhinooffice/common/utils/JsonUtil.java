package com.fzc.rhinooffice.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.fzc.rhinooffice.module.entity.AttachmentRecord;
import com.fzc.rhinooffice.module.entity.Business;
import com.fzc.rhinooffice.module.entity.Email;
import com.fzc.rhinooffice.module.entity.Flow;
import com.fzc.rhinooffice.module.entity.News;
import com.fzc.rhinooffice.module.entity.Notify;
import com.fzc.rhinooffice.module.entity.User;

public class JsonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();

	// 用户信息
	public static User analysis_user(String json) {
		User user = new User();

		try {
			JSONObject jsonObject = new JSONObject(json);
			user.a_user_name = jsonObject.getString("a_user_name");
			user.a_sessid = jsonObject.getString("a_sessid");
			String arrs = jsonObject.getString("A_MENU_STR");
			String[] temp = arrs.split(",");

			user.a_menu_str = new int[temp.length + 1];
			for (int i = 0; i < temp.length; i++) {
				user.a_menu_str[i] = Integer.parseInt(temp[i]);
			}
			jsonObject = null;
			temp = null;
		} catch (JSONException e1) {
			e1.printStackTrace();
			return user;
		}

		return user;
	}

	// 用户信息
	@SuppressWarnings("finally")
	public static List<Email> analysis_emails(String json) {
		List<Email> emailList = null;
		Email[] emailArr;
		try {
			emailArr = objectMapper.readValue(json, Email[].class);
			emailList = Arrays.asList(emailArr);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return emailList;
		}

	}

	// 用户信息
	public static Email analysis_email(String json) {
		Email email = null;
		Email defaultEmail = new Email();
		try {
			email = objectMapper.readValue(json, Email.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
			return defaultEmail;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return defaultEmail;
		} catch (IOException e) {
			e.printStackTrace();
			return defaultEmail;
		}

		return email;
	}

	// 附件集合
	@SuppressWarnings("finally")
	public static List<AttachmentRecord> analysis_attachment_record(String json) {
		List<AttachmentRecord> attachList = null;
		AttachmentRecord[] attachArr;
		try {
			attachArr = objectMapper.readValue(json, AttachmentRecord[].class);
			attachList = Arrays.asList(attachArr);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return attachList;
		}

	}

	// 通知信息
	public static Notify analysis_notify(String json) {
		Notify notify = null;
		Notify defaultNotify = new Notify();
		try {
			notify = objectMapper.readValue(json, Notify.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
			return defaultNotify;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return defaultNotify;
		} catch (IOException e) {
			e.printStackTrace();
			return defaultNotify;
		}

		return notify;
	}

	// 通知列表
	@SuppressWarnings("finally")
	public static List<Notify> analysis_notifys(String json) {
		List<Notify> notifyList = null;
		Notify[] notifyArr;
		try {
			notifyArr = objectMapper.readValue(json, Notify[].class);
			notifyList = Arrays.asList(notifyArr);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return notifyList;
		}

	}

	public static News analysis_news(String json) {
		News news = null;
		News defaultNews = new News();
		try {
			news = objectMapper.readValue(json, News.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
			return defaultNews;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return defaultNews;
		} catch (IOException e) {
			e.printStackTrace();
			return defaultNews;
		}

		return news;
	}

	// 解析工作流类
	public static Flow analysis_flow(String json) {
		Flow flow = null;
		Flow defaultFlow = new Flow();
		try {
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

	// 解析业务类
	public static Business analysis_business(String json) {
		Business business = null;
		Business defaultBusiness = new Business();
		try {
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
