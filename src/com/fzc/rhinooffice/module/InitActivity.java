package com.fzc.rhinooffice.module;


import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;

import com.fzc.rhinooffice.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

/**
 * »¶Ó­Ò³
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
				
				InitActivity.this.startActivity(new Intent(InitActivity.this,HomeActivity.class));
				InitActivity.this.finish();
				
			}
		}, 2500);
	}

	
}
