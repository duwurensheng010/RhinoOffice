package com.fzc.rhinooffice.common;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.fzc.rhinooffice.config.AppConfig;
import com.fzc.rhinooffice.module.entity.UserLogin;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;

public class RemoteInvoke {
	
	private static RequestParams getRequestHead(Boolean isLogin){
		RequestParams params = new RequestParams();
		params.addBodyParameter("appkey", AppConfig.APPKEY);
		params.addBodyParameter("appsecret", AppConfig.APPSECRET);
		params.addBodyParameter("userid", AppConfig.userid);
		if(!isLogin){
			params.addBodyParameter("a_sessid", SysApplication.user.a_sessid);
		}
		
		return params;
	}
	
	public static void login(final Handler mHandler,String username,String pwd){
		
		HttpUtils http = new HttpUtils();
		RequestParams params = getRequestHead(true);
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("userid", username);
			jsonObject.put("passwd", pwd);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		params.addBodyParameter("data", jsonObject.toString());
		http.configSoTimeout(AppConfig.HTTP_TIMEOUT);	//设置请求超时
		http.send(HttpRequest.HttpMethod.POST,
				"http://www.gzlxsoft.com:899/app/log/", params,
				new RequestCallBack<String>() {
			
					Message msg = mHandler.obtainMessage();
					@Override
					public void onFailure(HttpException error, String result) {
						msg.what = -1;
						msg.obj = result;
						mHandler.sendMessage(msg);
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {

						if (responseInfo != null) {
							JSONObject jsonObject = null;
							try {
								jsonObject = new JSONObject(responseInfo.result);
								if((jsonObject.getInt("state"))==0){
									msg.what = -1;
									msg.obj = jsonObject.getString("reason");
								}else{
									msg.what = 1;
									msg.obj = jsonObject;
								}
							} catch (JSONException e) {
								jsonObject = new JSONObject();
								e.printStackTrace();
							}finally{
								mHandler.sendMessage(msg);
							}
							
						}else{
							msg.what = -1;
							msg.obj = "登录失败！";
							mHandler.sendMessage(msg);
						} 

					}
				});
	}
	
	public static void email(final Handler mHandler,String data1,String data2,String subject,int page){
		LogUtils.i("data1-->>"+data1+"--data2-->>"+data2+"--subject-->>"+subject+"--page-->>"+page);
		HttpUtils http = new HttpUtils();
		RequestParams params = getRequestHead(false);
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("data1", data1);
			jsonObject.put("data2", data2);
			jsonObject.put("subject", subject);
			jsonObject.put("page", page);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		params.addBodyParameter("data", jsonObject.toString());
		http.configSoTimeout(AppConfig.HTTP_TIMEOUT);	//设置请求超时
		http.send(HttpRequest.HttpMethod.POST,
				"http://www.gzlxsoft.com:899/app/email/", params,
				new RequestCallBack<String>() {
			
					Message msg = mHandler.obtainMessage();
					
					@Override
					public void onFailure(HttpException error, String result) {
						msg.what = -2;
						msg.obj = result;
						mHandler.sendMessage(msg);
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						System.out.println("------email----responseInfo------->>"+responseInfo.result);
						if (responseInfo != null) {
							JSONObject jsonObject = null;
							try {
								jsonObject = new JSONObject(responseInfo.result);
								if((jsonObject.getInt("state"))==0){
									msg.what = -2;
									msg.obj = jsonObject.getString("reason");
								}else{
									msg.what = 2;
									msg.obj = jsonObject;
								}
							} catch (JSONException e) {
								jsonObject = new JSONObject();
								e.printStackTrace();
							}finally{
								mHandler.sendMessage(msg);
							}
							
						}else{
							msg.what = -2;
							msg.obj = "获取邮件列表失败！";
							mHandler.sendMessage(msg);
						} 

					}
				});
	}
	
	public static void email_detail(final Handler mHandler,int email_id){
		LogUtils.i("email_detail::email_id-->>"+email_id);
		HttpUtils http = new HttpUtils();
		RequestParams params = getRequestHead(false);
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("email_id", email_id);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		params.addBodyParameter("data", jsonObject.toString());
		http.configSoTimeout(AppConfig.HTTP_TIMEOUT);	//设置请求超时
		http.send(HttpRequest.HttpMethod.POST,
				"http://www.gzlxsoft.com:899/app/email/detail/", params,
				new RequestCallBack<String>() {
			
					Message msg = mHandler.obtainMessage();
					@Override
					public void onFailure(HttpException error, String result) {
						msg.what = -3;
						msg.obj = result;
						mHandler.sendMessage(msg);
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {

						if (responseInfo != null) {
							LogUtils.i("email_detail--->>"+responseInfo.result);
							JSONObject jsonObject = null;
							try {
								jsonObject = new JSONObject(responseInfo.result);
								if((jsonObject.getInt("state"))==0){
									msg.what = -3;
									msg.obj = jsonObject.getString("reason");
								}else{
									msg.what = 3;
									msg.obj = jsonObject;
								}
							} catch (JSONException e) {
								jsonObject = new JSONObject();
								e.printStackTrace();
							}finally{
								mHandler.sendMessage(msg);
							}
							
						}else{
							msg.what = -3;
							msg.obj = "获取邮件详情失败！";
							mHandler.sendMessage(msg);
						} 

					}
				});
	}
	
	public static void notify(final Handler mHandler,String subject,int page){
		LogUtils.i("subject-->>"+"--page-->>"+page);
		HttpUtils http = new HttpUtils();
		RequestParams params = getRequestHead(false);
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("subject", subject);
			jsonObject.put("page", page);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		params.addBodyParameter("data", jsonObject.toString());
		http.configSoTimeout(AppConfig.HTTP_TIMEOUT);	//设置请求超时
		http.send(HttpRequest.HttpMethod.POST,
				"http://www.gzlxsoft.com:899/app/notify/", params,
				new RequestCallBack<String>() {
			
					Message msg = mHandler.obtainMessage();
					
					@Override
					public void onFailure(HttpException error, String result) {
						msg.what = -2;
						msg.obj = result;
						mHandler.sendMessage(msg);
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						
						if (responseInfo != null) {
							LogUtils.i("notify-list->>"+responseInfo.result);
							JSONObject jsonObject = null;
							try {
								jsonObject = new JSONObject(responseInfo.result);
								if((jsonObject.getInt("state"))==0){
									msg.what = -2;
									msg.obj = jsonObject.getString("reason");
								}else{
									msg.what = 2;
									msg.obj = jsonObject;
								}
							} catch (JSONException e) {
								jsonObject = new JSONObject();
								e.printStackTrace();
							}finally{
								mHandler.sendMessage(msg);
							}
							
						}else{
							msg.what = -2;
							msg.obj = "获取邮件列表失败！";
							mHandler.sendMessage(msg);
						} 

					}
				});
	}
	
	public static void notify_detail(final Handler mHandler,int notify_id){
		LogUtils.i("notify_detail--notify_id-->>"+notify_id);
		HttpUtils http = new HttpUtils();
		RequestParams params = getRequestHead(false);
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("notify_id", notify_id);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		params.addBodyParameter("data", jsonObject.toString());
		http.configSoTimeout(AppConfig.HTTP_TIMEOUT);	//设置请求超时
		http.send(HttpRequest.HttpMethod.POST,
				"http://www.gzlxsoft.com:899/app/notify/detail/", params,
				new RequestCallBack<String>() {
			
					Message msg = mHandler.obtainMessage();
					
					@Override
					public void onFailure(HttpException error, String result) {
						msg.what = -4;
						msg.obj = result;
						mHandler.sendMessage(msg);
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						
						if (responseInfo != null) {
							LogUtils.i("notify_detail->>"+responseInfo.result);
							JSONObject jsonObject = null;
							try {
								jsonObject = new JSONObject(responseInfo.result);
								if((jsonObject.getInt("state"))==0){
									msg.what = -4;
									msg.obj = jsonObject.getString("reason");
								}else{
									msg.what = 4;
									msg.obj = jsonObject;
								}
							} catch (JSONException e) {
								jsonObject = new JSONObject();
								e.printStackTrace();
							}finally{
								mHandler.sendMessage(msg);
							}
							
						}else{
							msg.what = -4;
							msg.obj = "获取公告详情失败！";
							mHandler.sendMessage(msg);
						} 

					}
				});
	}
	
}
