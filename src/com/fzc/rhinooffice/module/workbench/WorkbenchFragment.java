package com.fzc.rhinooffice.module.workbench;

import com.fzc.rhinooffice.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 工作台
 * @author chao.liu
 *
 */

public class WorkbenchFragment extends Fragment{
	
	private View view;
	
	@ViewInject(R.id.ll_email)
	private LinearLayout ll_email;
	
	@ViewInject(R.id.tv_email_unread)
	private TextView tv_email_unread;
	
	@ViewInject(R.id.tv_email_desc)
	private TextView tv_email_desc;
	
	@ViewInject(R.id.tv_email_time)
	private TextView tv_email_time;
	
	@ViewInject(R.id.ll_notice)
	private LinearLayout ll_notice;
	
	@ViewInject(R.id.tv_notice_unread)
	private TextView tv_notice_unread;
	
	@ViewInject(R.id.tv_notice_desc)
	private TextView tv_notice_desc;
	
	@ViewInject(R.id.tv_notice_time)
	private TextView tv_notice_time;
	
	@ViewInject(R.id.ll_tidings)
	private LinearLayout ll_tidings;
	
	@ViewInject(R.id.tv_tidings_unread)
	private TextView tv_tidings_unread;
	
	@ViewInject(R.id.tv_tidings_desc)
	private TextView tv_tidings_desc;
	
	@ViewInject(R.id.tv_tidings_time)
	private TextView tv_tidings_time;
	
	@ViewInject(R.id.ll_workflow)
	private LinearLayout ll_workflow;
	
	@ViewInject(R.id.tv_workflow_unread)
	private TextView tv_workflow_unread;
	
	@ViewInject(R.id.tv_workflow_desc)
	private TextView tv_workflow_desc;
	
	@ViewInject(R.id.tv_workflow_time)
	private TextView tv_workflow_time;
	
	
	private Intent mIntent;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("-------onCreateView---WorkbenchFragment----");
		view = inflater.inflate(R.layout.fragment_workbench, container, false); // ����fragment����
        ViewUtils.inject(this, view); //ע��view���¼�
        return view;
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	
	@OnClick(R.id.ll_email)
	private void checkEmail(View v){
		
		if(mIntent==null){
			mIntent = new Intent();
		}
		mIntent.setClass(getActivity(), MailListActivity.class);
		startActivity(mIntent);
		
	}
	
	@OnClick(R.id.ll_notice)
	private void checkNotice(View v){
		
	}
	
	@OnClick(R.id.ll_tidings)
	private void checkTidings(View v){
		
	}
	
	@OnClick(R.id.ll_workflow)
	private void checkWorkflow(View v){
		
	}
	
	
}
