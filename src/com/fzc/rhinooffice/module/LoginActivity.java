package com.fzc.rhinooffice.module;

import org.json.JSONException;
import org.json.JSONObject;

import com.fzc.rhinooffice.R;
import com.fzc.rhinooffice.common.RemoteInvoke;
import com.fzc.rhinooffice.common.SysApplication;
import com.fzc.rhinooffice.common.utils.DBUtil;
import com.fzc.rhinooffice.common.utils.JsonUtil;
import com.fzc.rhinooffice.common.utils.StringUtil;
import com.fzc.rhinooffice.common.view.CustomProgress;
import com.fzc.rhinooffice.config.AppConfig;
import com.fzc.rhinooffice.module.entity.Business;
import com.fzc.rhinooffice.module.entity.Email;
import com.fzc.rhinooffice.module.entity.Flow;
import com.fzc.rhinooffice.module.entity.News;
import com.fzc.rhinooffice.module.entity.Notify;
import com.fzc.rhinooffice.module.entity.User;
import com.fzc.rhinooffice.module.entity.UserLogin;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.JsonToken;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

	@ViewInject(R.id.edt_username_input)
	private EditText edt_username_input;

	@ViewInject(R.id.edt_pwd_input)
	private EditText edt_pwd_input;

	@ViewInject(R.id.btn_login_now)
	private Button btn_login_now;

	@ViewInject(R.id.tv_forget_pwd)
	private TextView tv_forget_pwd;

	@ViewInject(R.id.tv_register_now)
	private TextView tv_register_now;

	private HttpUtils http = new HttpUtils();
	
	private Intent mIntent;
	
	private CustomProgress customProgress;
	
	private String username;
	private String pwd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		LogUtils.i("LoginActivity begin");
		customProgress = CustomProgress.init(this, getResources().getString(R.string.loading), false, null);
		
	}

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			customProgress.dismiss();
			switch (msg.what) {
			case 1:
				//登录成功
				JSONObject jsonObject = (JSONObject) msg.obj;
				String result = null;
				try {
					result = jsonObject.getString("reason");
					SysApplication.user = JsonUtil.analysis_user(jsonObject.getString("login"));
					SysApplication.email = JsonUtil.analysis_email(jsonObject.getString("email"));
					SysApplication.notify = JsonUtil.analysis_notify(jsonObject.getString("notify"));
					SysApplication.news = JsonUtil.analysis_news(jsonObject.getString("news"));
					SysApplication.flow = JsonUtil.analysis_flow(jsonObject.getString("flow"));
					SysApplication.business = JsonUtil.analysis_business(jsonObject.getString("busi"));
					
					LogUtils.i(SysApplication.a_sessid +" login success!");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				//将登录信息保存到数据库中
				UserLogin userLogin = new UserLogin();
				userLogin.username = username;
				userLogin.pwd = pwd;
				DBUtil.saveUser(LoginActivity.this, userLogin);
				
				mIntent = new Intent(LoginActivity.this,HomeActivity.class);
				startActivity(mIntent);
				
				finish();
				SysApplication.isLogin = true;
				StringUtil.showToast(getApplicationContext(), result);
				break;

			case -1:
				//登录失败
				StringUtil.showToast(getApplicationContext(), msg.obj.toString());
				break;
			}
		};
	};

	@OnClick(R.id.btn_login_now)
	private void loginNow(View v) {
		username = edt_username_input.getText() + "";
		pwd = edt_pwd_input.getText() + "";
		if (username.isEmpty()) {
			StringUtil.showToast(getApplicationContext(), getResources().getString(R.string.username_not_null));
			edt_username_input.findFocus();
			return;
		}

		if (pwd.isEmpty()) {
			StringUtil.showToast(getApplicationContext(), getResources().getString(R.string.pwd_not_null));
			edt_pwd_input.findFocus();
			return;
		}

		//登录
		customProgress.setMessage(getResources().getString(R.string.logining));
		customProgress.show();
		RemoteInvoke.login(mHandler, username,pwd);
		
	}

	
	
	// 忘记密码
	@OnClick(R.id.tv_forget_pwd)
	private void forgetPwd(View v) {
		LogUtils.i("forget password");
	}

	//立即注册
	@OnClick(R.id.tv_register_now)
	private void registerNow(View v) {
		LogUtils.i("register now");
	}

}
