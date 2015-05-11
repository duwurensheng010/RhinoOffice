package com.fzc.rhinooffice.module;

import com.fzc.rhinooffice.common.SysApplication;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SysApplication.addActivity(this);
	}
}
