package com.fzc.rhinooffice.module.workbench;

import com.fzc.rhinooffice.R;
import com.fzc.rhinooffice.common.SysApplication;
import com.fzc.rhinooffice.module.entity.Business;
import com.fzc.rhinooffice.module.entity.Email;
import com.fzc.rhinooffice.module.entity.Flow;
import com.fzc.rhinooffice.module.entity.News;
import com.fzc.rhinooffice.module.entity.Notify;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
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
 * 
 * @author chao.liu
 * 
 */

public class WorkbenchFragment extends Fragment {

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
	
	@ViewInject(R.id.tv_marketing_account)
	private TextView tv_marketing_account;	//当日营销额
	
	@ViewInject(R.id.tv_station)
	private TextView tv_station;	//销售台数
	
	@ViewInject(R.id.tv_month_completion_rate)
	private TextView tv_month_completion_rate;	//任务完成率

	private Intent mIntent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LogUtils.e("WorkbenchFragment view created");
		view = inflater.inflate(R.layout.fragment_workbench, container, false);
		ViewUtils.inject(this, view); // 注入fragment布局
		return view;
	}

	@OnClick(R.id.ll_email)
	private void checkEmail(View v) {

		if (mIntent == null) {
			mIntent = new Intent();
		}
		mIntent.setClass(getActivity(), MailListActivity.class);
		startActivity(mIntent);

	}

	@OnClick(R.id.ll_notice)
	private void checkNotice(View v) {

	}

	@OnClick(R.id.ll_tidings)
	private void checkTidings(View v) {

	}

	@OnClick(R.id.ll_workflow)
	private void checkWorkflow(View v) {

	}

	public void initUI() {
		Email email = SysApplication.email;

		if (email.email_sl > 0) {
			tv_email_unread.setVisibility(View.VISIBLE);
			tv_email_unread.setText(email.email_sl + "");
		} else {
			tv_email_unread.setVisibility(View.GONE);
		}

		if (email.email_subject == null || "".equals(email.email_subject)) {
			tv_email_desc.setText("未知");
		} else {
			tv_email_desc.setText(email.email_subject);
		}

		if (email.email_time == null || "".equals(email.email_time)) {
			tv_email_time.setText("未知");
		} else {
			tv_email_time.setText(email.email_time);
		}

		Notify notify = SysApplication.notify;

		if (notify.notify_sl > 0) {
			tv_notice_unread.setVisibility(View.VISIBLE);
			tv_notice_unread.setText(notify.notify_sl + "");
		} else {
			tv_notice_unread.setVisibility(View.GONE);
		}

		if (notify.notify_subject == null || "".equals(notify.notify_subject)) {
			tv_notice_desc.setText("未知");
		} else {
			tv_notice_desc.setText(notify.notify_subject);
		}

		if (notify.notify_time == null || "".equals(notify.notify_time)) {
			tv_notice_time.setText("未知");
		} else {
			tv_notice_time.setText(notify.notify_time);
		}

		News news = SysApplication.news;

		if (news.news_sl > 0) {
			tv_tidings_unread.setVisibility(View.VISIBLE);
			tv_tidings_unread.setText(news.news_sl + "");
		} else {
			tv_tidings_unread.setVisibility(View.GONE);
		}

		if (news.news_subject == null || "".equals(news.news_subject)) {
			tv_tidings_desc.setText("未知");
		} else {
			tv_tidings_desc.setText(news.news_subject);
		}

		if (news.news_time == null || "".equals(news.news_time)) {
			tv_tidings_time.setText("未知");
		} else {
			tv_tidings_time.setText(news.news_time);
		}

		Flow flow = SysApplication.flow;
		if (flow.flow_sl > 0) {
			tv_workflow_unread.setVisibility(View.VISIBLE);
			tv_workflow_unread.setText(flow.flow_sl + "");
		} else {
			tv_workflow_unread.setVisibility(View.GONE);
		}

		if (flow.flow_subject == null || "".equals(flow.flow_subject)) {
			tv_workflow_desc.setText("未知");
		} else {
			tv_workflow_desc.setText(flow.flow_subject);
		}

		if (flow.flow_prcs_time == null || "".equals(flow.flow_prcs_time)) {
			tv_workflow_time.setText("未知");
		} else {
			tv_workflow_time.setText(flow.flow_prcs_time);
		}
		
		Business business = SysApplication.business;
		tv_marketing_account.setText(business.dryxe/10000+"万");
		tv_station.setText(business.xsts+"台");
		tv_month_completion_rate.setText(business.rwydwcl*100+"%");

	}

}
