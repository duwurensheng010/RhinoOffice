package com.fzc.rhinooffice.module;

import java.io.StringReader;

import org.json.JSONException;
import org.json.JSONObject;

import com.fzc.rhinooffice.R;
import com.fzc.rhinooffice.common.RemoteInvoke;
import com.fzc.rhinooffice.common.SysApplication;
import com.fzc.rhinooffice.common.utils.DBUtil;
import com.fzc.rhinooffice.common.utils.JsonUtil;
import com.fzc.rhinooffice.common.utils.StringUtil;
import com.fzc.rhinooffice.common.view.CustomProgress;
import com.fzc.rhinooffice.common.view.DragLayout;
import com.fzc.rhinooffice.common.view.MyRelativeLayout;
import com.fzc.rhinooffice.common.view.DragLayout.DragListener;
import com.fzc.rhinooffice.module.business.BusinessFragment;
import com.fzc.rhinooffice.module.entity.Business;
import com.fzc.rhinooffice.module.entity.Email;
import com.fzc.rhinooffice.module.entity.Flow;
import com.fzc.rhinooffice.module.entity.News;
import com.fzc.rhinooffice.module.entity.Notify;
import com.fzc.rhinooffice.module.entity.User;
import com.fzc.rhinooffice.module.entity.UserLogin;
import com.fzc.rhinooffice.module.mall.MallFragment;
import com.fzc.rhinooffice.module.news.NewsFragment;
import com.fzc.rhinooffice.module.office.OfficeFragment;
import com.fzc.rhinooffice.module.workbench.WorkbenchFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nineoldandroids.view.ViewHelper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.JsonReader;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * 主界面
 * 
 * @author chao.liu
 * 
 */

@SuppressLint("NewApi")
@ContentView(R.layout.reddotface_view)
public class HomeActivity extends FragmentActivity implements
		OnCheckedChangeListener {

	//侧滑布局
	@ViewInject(R.id.dl)
	private DragLayout dl;

	@ViewInject(R.id.iv_sliding_user_icon)
	private ImageView iv_sliding_user_icon;

	@ViewInject(R.id.tv_user_name)
	private TextView tv_user_name;

	@ViewInject(R.id.tv_user_desc)
	private TextView tv_user_desc;

	@ViewInject(R.id.ll_personal_settings)
	private LinearLayout ll_personal_settings;

	@ViewInject(R.id.ll_change_pwd)
	private LinearLayout ll_change_pwd;

	@ViewInject(R.id.ll_version_update)
	private LinearLayout ll_version_update;

	@ViewInject(R.id.ll_exit)
	private LinearLayout ll_operator_information;

	//主页布局
	private WorkbenchFragment workbenchFragment;

	private NewsFragment newsFragment;

	private OfficeFragment officeFragment;

	private BusinessFragment businessFragment;

	private MallFragment mallFragment;
	private FragmentTransaction tran;
	private FragmentManager fragmentManager;

	@ViewInject(R.id.rl_main_view)
	private MyRelativeLayout rl_main_view;

	@ViewInject(R.id.ll_sliding)
	private LinearLayout ll_sliding;

	@ViewInject(R.id.iv_sliding)
	private ImageView iv_sliding;

	@ViewInject(R.id.iv_user_icon)
	private ImageView iv_user_icon;

	@ViewInject(R.id.tv_registration)
	private TextView tv_registration;

	@ViewInject(R.id.container)
	private LinearLayout container;

	@ViewInject(R.id.rg_radioBtns)
	private RadioGroup rg_radioBtns;

	@ViewInject(R.id.rb_workbench)
	private RadioButton rb_workbench;

	@ViewInject(R.id.rb_news)
	private RadioButton rb_news;

	@ViewInject(R.id.rb_office)
	private RadioButton rb_office;

	@ViewInject(R.id.rb_business)
	private RadioButton rb_business;

	@ViewInject(R.id.rb_mall)
	private RadioButton rb_mall;

	private long firstTime = 0;
	
	private Intent mIntent;
	
	private CustomProgress customProgress;
	
	private Handler mHandler = new Handler(){
		
		public void handleMessage(Message msg) {
			customProgress.dismiss();
			switch (msg.what) {
			case 1:
				//登录成功
				JSONObject jsonObject = (JSONObject) msg.obj;
				String result = null;
				try {
					result = jsonObject.getString("reason");
					
					SysApplication.user = JsonUtil.analysis_user(StringUtil.StringToJsonReader(jsonObject.optString("login")));
					SysApplication.email = JsonUtil.analysis_email(StringUtil.StringToJsonReader(jsonObject.optString("email")));
					SysApplication.notify = JsonUtil.analysis_notify(StringUtil.StringToJsonReader(jsonObject.optString("notify")));
					SysApplication.news = JsonUtil.analysis_news(StringUtil.StringToJsonReader(jsonObject.optString("news")));
					SysApplication.flow = JsonUtil.analysis_flow(StringUtil.StringToJsonReader(jsonObject.optString("flow")));
					SysApplication.business = JsonUtil.analysis_business(StringUtil.StringToJsonReader(jsonObject.optString("busi")));
					
					LogUtils.i(SysApplication.a_sessid +" login success!");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				SysApplication.isLogin = true;
				StringUtil.showToast(getApplicationContext(), result);
				initUI();
				break;

			case -1:
				//登录失败
				StringUtil.showToast(getApplicationContext(), msg.obj.toString());
				break;
			}
		}

		
	};
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		LogUtils.i("HomeActivity begin");
		ViewUtils.inject(this);
		initDragLayout();
		fragmentManager = getSupportFragmentManager();
		listener();
		
		initUser();
	}
	
	
	
	private void initDragLayout() {
		dl.setDragListener(new DragListener() {
			@Override
			public void onOpen() {
				// lv.smoothScrollToPosition(new Random().nextInt(30));
			}

			@Override
			public void onClose() {
				shake();
			}

			@Override
			public void onDrag(float percent) {
				ViewHelper.setAlpha(ll_sliding, 1 - percent);
			}
		});
	}
	

	private void listener() {

		rb_workbench.setOnCheckedChangeListener(this);
		rb_news.setOnCheckedChangeListener(this);
		rb_office.setOnCheckedChangeListener(this);
		rb_business.setOnCheckedChangeListener(this);
		rb_mall.setOnCheckedChangeListener(this);
		rg_radioBtns.check(R.id.rb_workbench);

	}
	
	//读取数据库中的用户信息，自动登录
	private void initUser(){
		LogUtils.e("homeActivity  initUser");
		customProgress = CustomProgress.init(this, getResources().getString(R.string.loading), false, null);
		UserLogin userLogin = DBUtil.findFirstUserLogin(this);
		if(userLogin!=null){
			customProgress.show();
			RemoteInvoke.login(mHandler, userLogin.username, userLogin.pwd);
		}
	}
	
	//登录成功后初始化显示信息
	private void initUI() {
		//System.out.println("------homeActivity---initUI---");
		tv_user_name.setText(SysApplication.user.a_user_name);
		workbenchFragment.initUI();
		
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		initUI();
	}
	
	@Override
	public void onCheckedChanged(CompoundButton button, boolean isChecked) {
		if (isChecked) {
			tran = fragmentManager.beginTransaction();
			hideFragments(tran);
			switch (button.getId()) {
			case R.id.rb_workbench:
				if (workbenchFragment == null) {
					workbenchFragment = new WorkbenchFragment();
					tran.add(R.id.container, workbenchFragment);
				} else {
					tran.show(workbenchFragment);
				}
				tv_registration.setVisibility(View.VISIBLE);
				break;

			case R.id.rb_news:
				if (newsFragment == null) {
					newsFragment = new NewsFragment();
					tran.add(R.id.container, newsFragment);
				} else {
					tran.show(newsFragment);
				}
				tv_registration.setVisibility(View.GONE);
				break;
			case R.id.rb_office:
				if (officeFragment == null) {
					officeFragment = new OfficeFragment();
					tran.add(R.id.container, officeFragment);
				} else {
					tran.show(officeFragment);
				}
				tv_registration.setVisibility(View.GONE);
				break;
			case R.id.rb_business:
				if (businessFragment == null) {
					businessFragment = new BusinessFragment();
					tran.add(R.id.container, businessFragment);
				} else {
					tran.show(businessFragment);
				}
				tv_registration.setVisibility(View.GONE);
				break;

			case R.id.rb_mall:
				if (mallFragment == null) {
					mallFragment = new MallFragment();
					tran.add(R.id.container, mallFragment);
				} else {
					tran.show(mallFragment);
				}
				tv_registration.setVisibility(View.GONE);
				break;
			}
			tran.commit();
		}

	}

	@OnClick(R.id.ll_sliding)
	private void OpenDL(View v) {
		dl.open();
	}

	@OnClick(R.id.tv_registration)
	private void registration(View v) {
		LogUtils.i("---签到----");
		
	}

	@OnClick(R.id.ll_personal_settings)
	private void Setting(View v) {
		//个人设置
	}

	@OnClick(R.id.ll_change_pwd)
	private void changePwd(View v) {
		// 修改密码
	}

	@OnClick(R.id.ll_version_update)
	private void versionUpdate(View v) {
		// 版本更新
	}

	@OnClick(R.id.ll_exit)
	private void exit(View v) {
		// 退出
		UserLogin userLogin = DBUtil.findFirstUserLogin(this);
		if(userLogin!=null){
			DBUtil.deleteUser(this, userLogin);
			mIntent = new Intent(this,LoginActivity.class);
			startActivity(mIntent);
			finish();
		}else{
			StringUtil.showToast(this, "您尚未登录！");
		}
		
	}

	private void shake() {
		iv_sliding.startAnimation(AnimationUtils.loadAnimation(this,
				R.anim.shake));
	}

	/**
	 * 隐藏fragment
	 * 
	 * @param transaction
	 *            
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (workbenchFragment != null) {
			transaction.hide(workbenchFragment);
		}
		if (newsFragment != null) {
			transaction.hide(newsFragment);
		}
		if (officeFragment != null) {
			transaction.hide(officeFragment);
		}
		if (businessFragment != null) {
			transaction.hide(businessFragment);
		}
		if (mallFragment != null) {
			transaction.hide(mallFragment);
		}
	}

	// back键监听，双击关闭应用
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		long secondTime = System.currentTimeMillis();
		if ((secondTime - firstTime) > 2000) {
			Toast.makeText(this, R.string.app_exit_tip, Toast.LENGTH_SHORT)
					.show();
			firstTime = secondTime;
		} else {
			SysApplication.exit();
			// System.exit(0);
			finish();
		}
		return true;
		// return super.onKeyDown(keyCode, event);
	}

	
}
