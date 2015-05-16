package com.fzc.rhinooffice.module.workbench;

import com.fzc.rhinooffice.R;
import com.fzc.rhinooffice.common.SysApplication;
import com.fzc.rhinooffice.common.utils.StringUtil;
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

	//邮件列表
	@OnClick(R.id.ll_email)
	private void checkEmail(View v) {
		if(SysApplication.email==null || SysApplication.email.email_sl==null || "0".equals(SysApplication.email.email_sl)){
			StringUtil.showToast(getActivity(), "无未读邮件！");
			return;
		}
		if (mIntent == null) {
			mIntent = new Intent();
		}
		mIntent.putExtra("email_id", SysApplication.email.email_id);
		mIntent.setClass(getActivity(), EmailDetailActivity.class);
		startActivity(mIntent);

	}
	
	//公告通知
	@OnClick(R.id.ll_notice)
	private void checkNoticfy(View v) {
		
		if(SysApplication.notify==null || SysApplication.notify.notify_sl==null || "0".equals(SysApplication.notify.notify_sl)){
			StringUtil.showToast(getActivity(), "无未读公告信息！");
			return;
		}
		
		if (mIntent == null) {
			mIntent = new Intent();
		}
		mIntent.putExtra("notify_id", SysApplication.notify.notify_id);
		mIntent.setClass(getActivity(), NotifyDetailActivity.class);
		startActivity(mIntent);
	}
	
	//新闻
	@OnClick(R.id.ll_tidings)
	private void checkTidings(View v) {
		if(SysApplication.news==null || SysApplication.news.news_sl==null || "0".equals(SysApplication.news.news_sl)){
			StringUtil.showToast(getActivity(), "无未读新闻信息！");
			return;
		}
		
		if (mIntent == null) {
			mIntent = new Intent();
		}
		mIntent.putExtra("news_id", SysApplication.news.news_id);
		mIntent.setClass(getActivity(), NewsDetailActivity.class);
		startActivity(mIntent);
	}
	
	//工作流
	@OnClick(R.id.ll_workflow)
	private void checkWorkflow(View v) {
		if (mIntent == null) {
			mIntent = new Intent();
		}
		mIntent.setClass(getActivity(), NotifyListActivity.class);
		startActivity(mIntent);
	}

	public void initUI() {
		Email email = SysApplication.email;

		if (!"0".equals(email.email_sl)) {
			tv_email_unread.setVisibility(View.VISIBLE);
			tv_email_unread.setText(email.email_sl);
		} else {
			tv_email_unread.setVisibility(View.GONE);
		}

		if (StringUtil.isEmpty(email.subject)) {
			tv_email_desc.setText("未知");
		} else {
			tv_email_desc.setText(email.subject);
		}

		if (StringUtil.isEmpty(email.send_time)) {
			tv_email_time.setText("未知");
		} else {
			tv_email_time.setText(email.send_time);
		}

		Notify notify = SysApplication.notify;

		if (!"0".equals(notify.notify_sl)) {
			tv_notice_unread.setVisibility(View.VISIBLE);
			tv_notice_unread.setText(notify.notify_sl);
		} else {
			tv_notice_unread.setVisibility(View.GONE);
		}

		if (StringUtil.isEmpty(notify.subject)) {
			tv_notice_desc.setText("未知");
		} else {
			tv_notice_desc.setText(notify.subject);
		}

		if (StringUtil.isEmpty(notify.begin_date)) {
			tv_notice_time.setText("未知");
		} else {
			tv_notice_time.setText(notify.begin_date);
		}

		News news = SysApplication.news;

		if (!"0".equals(news.news_sl)) {
			tv_tidings_unread.setVisibility(View.VISIBLE);
			tv_tidings_unread.setText(news.news_sl);
		} else {
			tv_tidings_unread.setVisibility(View.GONE);
		}

		if (StringUtil.isEmpty(news.subject)) {
			tv_tidings_desc.setText("未知");
		} else {
			tv_tidings_desc.setText(news.subject);
		}

		if (StringUtil.isEmpty(news.news_time)) {
			tv_tidings_time.setText("未知");
		} else {
			tv_tidings_time.setText(news.news_time);
		}

		Flow flow = SysApplication.flow;
		if (!"0".equals(flow.flow_sl)) {
			tv_workflow_unread.setVisibility(View.VISIBLE);
			tv_workflow_unread.setText(flow.flow_sl + "");
		} else {
			tv_workflow_unread.setVisibility(View.GONE);
		}

		if (StringUtil.isEmpty(flow.subject)) {
			tv_workflow_desc.setText("未知");
		} else {
			tv_workflow_desc.setText(flow.subject);
		}

		if (StringUtil.isEmpty(flow.prcs_time)) {
			tv_workflow_time.setText("未知");
		} else {
			tv_workflow_time.setText(flow.prcs_time);
		}
		
		Business business = SysApplication.business;
		tv_marketing_account.setText((Integer.parseInt(business.dryxe)/10000)+"万");
		tv_station.setText(business.xsts+"台");
		tv_month_completion_rate.setText(Float.parseFloat(business.rwydwcl)*100+"%");

	}

}
