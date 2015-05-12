package com.fzc.rhinooffice.module;


import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;

import com.fzc.rhinooffice.R;
import com.fzc.rhinooffice.common.utils.DBUtil;
import com.fzc.rhinooffice.module.entity.UserLogin;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

/**
 * 欢迎页
 * @author chao.liu
 *
 */

@ContentView(R.layout.activity_init)
public class InitActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtils.i("InitActivity begin");
		ViewUtils.inject(this);
		//setContentView(R.layout.activity_init);
		
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				UserLogin userLogin = DBUtil.findFirstUserLogin(InitActivity.this);
				if(userLogin==null){
					InitActivity.this.startActivity(new Intent(InitActivity.this,LoginActivity.class));
					InitActivity.this.finish();
				}else{
					InitActivity.this.startActivity(new Intent(InitActivity.this,HomeActivity.class));
					InitActivity.this.finish();
				}
				
				
			}
		}, 2500);
	}

	
}
