package com.fzc.rhinooffice.module.office;

import com.fzc.rhinooffice.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 办公
 * @author chao.liu
 *
 */

public class OfficeFragment extends Fragment {

	private View view;
	
	@ViewInject(R.id.rl_office_email)
	private RelativeLayout rl_office_email;
	
	@ViewInject(R.id.tv_email_unread)
	private TextView tv_email_unread;		//未读邮件
	
	@ViewInject(R.id.rl_office_notice)
	private RelativeLayout rl_office_notice;
	
	@ViewInject(R.id.tv_notice_unread)
	private TextView tv_notice_unread;		//未读通知
	
	@ViewInject(R.id.rl_office_tidings)
	private RelativeLayout rl_office_tidings;
	
	@ViewInject(R.id.tv_tidings_unread)
	private TextView tv_tidings_unread;		//未读新闻
	
	@ViewInject(R.id.rl_office_workflow)
	private RelativeLayout rl_office_workflow;
	
	@ViewInject(R.id.tv_office_workflow_unread)
	private TextView tv_office_workflow_unread;		//未读新闻
	
	@ViewInject(R.id.rl_office_personal_folders)
	private RelativeLayout rl_office_personal_folders;
	
	@ViewInject(R.id.tv_personal_folders_unread)
	private TextView tv_personal_folders_unread;		//未读新闻
	
	@ViewInject(R.id.rl_office_internal_sms)
	private RelativeLayout rl_office_internal_sms;
	
	@ViewInject(R.id.tv_internal_sms_unread)
	private TextView tv_internal_sms_unread;		//未读新闻
	
	@ViewInject(R.id.rl_office_positioning_sign)
	private RelativeLayout rl_office_positioning_sign;
	
	@ViewInject(R.id.tv_office_positioning_sign_unread)
	private TextView tv_office_positioning_sign_unread;		//未读新闻
	
	@ViewInject(R.id.rl_office_funding_schedule)
	private RelativeLayout rl_office_funding_schedule;
	
	@ViewInject(R.id.tv_office_funding_schedule_unread)
	private TextView tv_office_funding_schedule_unread;		//资金计划表
	
	/*@ViewInject(R.id.ll_workflow)
	private LinearLayout ll_workflow;		//工作流
	
	@ViewInject(R.id.ll_personal_folders)
	private LinearLayout ll_personal_folders;		//个人文件柜
	
	@ViewInject(R.id.ll_internal_sms)
	private LinearLayout ll_internal_sms;		//内部短信
	
	@ViewInject(R.id.ll_positioning_sign)
	private LinearLayout ll_positioning_sign;		//定位签到
	
	@ViewInject(R.id.ll_funding_schedule)
	private LinearLayout ll_funding_schedule;		//资金计划表
*/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_office, container, false); 
		ViewUtils.inject(this, view); 
		return view;
	}
	
	@OnClick(R.id.rl_office_email)
	private void checkEmail(View v){
		
	}
	
	@OnClick(R.id.rl_office_notice)
	private void checkNotice(View v){
		
	}
	
	@OnClick(R.id.rl_office_tidings)
	private void checkTidings(View v){
		
	}
	
	@OnClick(R.id.ll_workflow)
	private void checkWrokflow(View v){
		
	}
	
	
	
}
