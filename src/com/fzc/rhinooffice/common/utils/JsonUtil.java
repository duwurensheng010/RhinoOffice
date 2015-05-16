package com.fzc.rhinooffice.common.utils;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.exc.UnrecognizedPropertyException;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.util.JsonReader;

import com.fzc.rhinooffice.module.entity.AttachmentRecord;
import com.fzc.rhinooffice.module.entity.Business;
import com.fzc.rhinooffice.module.entity.Email;
import com.fzc.rhinooffice.module.entity.Flow;
import com.fzc.rhinooffice.module.entity.News;
import com.fzc.rhinooffice.module.entity.Notify;
import com.fzc.rhinooffice.module.entity.User;

@SuppressLint("NewApi")
public class JsonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();

	// 用户信息
	/*public static User analysis_user(String json) {
		User user = new User();

		try {
			JSONObject jsonObject = new JSONObject(json);
			user.a_user_name = jsonObject.getString("a_user_name");
			user.a_sessid = jsonObject.getString("a_sessid");
			String arrs = jsonObject.getString("a_menu_str");
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
	}*/
	
	public static User analysis_user(JsonReader reader) {
		User user = new User();

		try {
			reader.beginObject();
			while(reader.hasNext()){
				String key = reader.nextName();
				if("mallurl".equals(key)){
					user.mallurl = reader.nextString();
				}else if("userid".equals(key)){
					user.userid = reader.nextString();
				}else if("a_user_name".equals(key)){
					user.a_user_name = reader.nextString();
				}else if("a_sessid".equals(key)){
					user.a_sessid = reader.nextString();
				}else if("a_menu_str".equals(key)){
					List<String> list = new ArrayList<String>();
					String a_menu_str = reader.nextString();
					if(!StringUtil.isEmpty(a_menu_str)){
						String[] arr = a_menu_str.split(",");
						for(String s:arr){
							list.add(s);
						}
					}
					user.a_menu_str = list;
					list=null;
				}
			}
			reader.endObject();
		} catch (Exception e) {
			e.printStackTrace();
			return user;
		}

		return user;
	}
	
	// 用户信息
	@SuppressWarnings("finally")
	/*public static List<Email> analysis_emails(String json) {
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

	}*/
	
	public static List<Email> analysis_emails(JsonReader reader) {
		List<Email> emailList = new ArrayList<Email>();
		try {
			reader.beginArray();
			while(reader.hasNext()){
				Email email = analysis_email(reader);
				emailList.add(email);
			}
			reader.endArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return emailList;
		}

	}

	
	// 用户信息
	/*
	 * public static Email analysis_email(String json) {
	 * 
	 * Email email = null; Email defaultEmail = new Email(); try { //
	 * objectMapper
	 * .configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, //
	 * true); // objectMapper.enableDefaultTyping(); email =
	 * objectMapper.readValue(json, Email.class); } catch (JsonParseException e)
	 * { e.printStackTrace(); return defaultEmail; } catch (JsonMappingException
	 * e) { e.printStackTrace(); return defaultEmail; } catch (IOException e) {
	 * e.printStackTrace(); return defaultEmail; }
	 * 
	 * return email; }
	 */

	// 用户信息
	public static Email analysis_email(JsonReader reader) {

		Email email = new Email();
		try {
			//JsonReader reader = new JsonReader(new StringReader(json));
			reader.beginObject();
			while (reader.hasNext()) {
				String key = reader.nextName();
				if ("email_sl".equals(key)) {
					email.email_sl = reader.nextString();
				}
				
				if ("email_id".equals(key)) {
					email.email_id = reader.nextString();
				}
				if ("from_id".equals(key)) {
					email.from_id = reader.nextString();
				}
				if ("from_name".equals(key)) {
					email.from_name = reader.nextString();
				}
				if ("subject".equals(key)) {
					email.subject = reader.nextString();
				}
				if ("content".equals(key)) {
					email.content = reader.nextString();
				}
				if ("send_time".equals(key)) {
					email.send_time = reader.nextString();
				}
				if ("attachment_rows".equals(key)) {
					email.attachment_rows = reader.nextString();
				}
				if ("is_new".equals(key)) {
					email.is_new = reader.nextString();
				}
				if ("attachment_record".equals(key)) {
					
					
					//if(!StringUtil.isEmpty(reader.nextString())){
						//JsonReader readerArr = new JsonReader(new StringReader(reader.nextString()));
						List<AttachmentRecord> attach_list = new ArrayList<AttachmentRecord>();
						reader.beginArray();
						while(reader.hasNext()){
							AttachmentRecord attach = new AttachmentRecord();
							reader.beginObject();
							while(reader.hasNext()){
								String key2 = reader.nextName();
								if("url".equals(key2)){
									attach.url = reader.nextString();
								}
								if("name".equals(key2)){
									attach.name = reader.nextString();
								}
							}
							attach_list.add(attach);
							attach=null;
							reader.endObject();
						}
						reader.endArray();
						email.attach_list = attach_list;
					//}
				}
				
			}
			reader.endObject();

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return email;
		}

		
	}
	
	
	// 附件集合
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
	public static Notify analysis_notify(JsonReader reader) {
		Notify notify = new Notify();;
		try {
			reader.beginObject();
			while(reader.hasNext()){
				String key = reader.nextName();
				if("notify_sl".equals(key)){
					notify.notify_sl = reader.nextString();
				}else if("notify_id".equals(key)){
					notify.notify_id = reader.nextString();
				}else if("subject".equals(key)){
					notify.subject = reader.nextString();
				}else if("begin_date".equals(key)){
					notify.begin_date = reader.nextString();
				}else if("type_name".equals(key)){
					notify.type_name = reader.nextString();
				}else if("from_name".equals(key)){
					notify.from_name = reader.nextString();
				}else if("from_id".equals(key)){
					notify.from_id = reader.nextString();
				}else if("is_new".equals(key)){
					notify.is_new = reader.nextString();
				}else if("content".equals(key)){
					notify.content = reader.nextString();
				}
			}
			reader.endObject();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return notify;
	}

	// 通知列表
	@SuppressWarnings("finally")
	public static List<Notify> analysis_notifys(JsonReader reader) {
		List<Notify> notifyList = new ArrayList<Notify>();
		try {
			reader.beginArray();
			while(reader.hasNext()){
				Notify notify = analysis_notify(reader);
				notifyList.add(notify);
			}
			reader.endArray();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return notifyList;
		}

	}

	public static News analysis_news(JsonReader reader) {
		News news = new News();;
		try {
			reader.beginObject();
			while(reader.hasNext()){
				String key = reader.nextName();
				if("news_sl".equals(key)){
					news.news_sl = reader.nextString();
				}else if("news_id".equals(key)){
					news.news_id = reader.nextString();
				}else if("subject".equals(key)){
					news.subject = reader.nextString();
				}else if("news_time".equals(key)){
					news.news_time = reader.nextString();
				}else if("from_name".equals(key)){
					news.from_name = reader.nextString();
				}else if("type_name".equals(key)){
					news.type_name = reader.nextString();
				}else if("content".equals(key)){
					news.content = reader.nextString();
				}
			}
			reader.endObject();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return news;
	}

	// 解析工作流类
	public static Flow analysis_flow(JsonReader reader) {
		Flow flow = new Flow();
		try {
			reader.beginObject();
			while(reader.hasNext()){
				String key = reader.nextName();
				if("flow_sl".equals(key)){
					flow.flow_sl = reader.nextString();
				}else if("run_id".equals(key)){
					flow.run_id = reader.nextString();
				}else if("prcs_id".equals(key)){
					flow.prcs_id = reader.nextString();
				}else if("flow_prcs".equals(key)){
					flow.flow_prcs = reader.nextString();
				}else if("flow_id".equals(key)){
					flow.flow_id = reader.nextString();
				}else if("subject".equals(key)){
					flow.subject = reader.nextString();
				}else if("prcs_time".equals(key)){
					flow.prcs_time = reader.nextString();
				}
			
			}
			reader.endObject();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flow;
	}

	// 解析业务类
	public static Business analysis_business(JsonReader reader) {
		Business business = new Business();
		try {
			reader.beginObject();
			while(reader.hasNext()){
				String key = reader.nextName();
				if("dryxe".equals(key)){
					business.dryxe = reader.nextString();
				}else if("xsts".equals(key)){
					business.xsts = reader.nextString();
				}else if("rwydwcl".equals(key)){
					business.rwydwcl = reader.nextString();
				}
			
			}
			reader.endObject();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return business;
	}

}
