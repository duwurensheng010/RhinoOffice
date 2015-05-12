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
	
	public static void login(final Handler mHandler,String username,String pwd){
		LogUtils.e("username="+username+"-----pwd=="+pwd);
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("userid", username);
			jsonObject.put("passwd", pwd);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		params.addBodyParameter("appkey", AppConfig.APPKEY);
		params.addBodyParameter("appsecret", AppConfig.APPSECRET);
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
	
}
