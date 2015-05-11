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
 * �칫ҳ��
 * @author chao.liu
 *
 */

public class OfficeFragment extends Fragment {

	private View view;
	
	@ViewInject(R.id.rl_office_email)
	private RelativeLayout rl_office_email;
	
	@ViewInject(R.id.tv_email_unread)
	private TextView tv_email_unread;		//δ���ڲ��ʼ�
	
	@ViewInject(R.id.rl_office_notice)
	private RelativeLayout rl_office_notice;
	
	@ViewInject(R.id.tv_notice_unread)
	private TextView tv_notice_unread;		//δ������֪ͨ
	
	@ViewInject(R.id.rl_office_tidings)
	private RelativeLayout rl_office_tidings;
	
	@ViewInject(R.id.tv_tidings_unread)
	private TextView tv_tidings_unread;		//δ������
	
	@ViewInject(R.id.ll_workflow)
	private LinearLayout ll_workflow;		//������
	
	@ViewInject(R.id.ll_personal_folders)
	private LinearLayout ll_personal_folders;		//�����ļ���
	
	@ViewInject(R.id.ll_internal_sms)
	private LinearLayout ll_internal_sms;		//�ڲ�����
	
	@ViewInject(R.id.ll_positioning_sign)
	private LinearLayout ll_positioning_sign;		//��λǩ��
	
	@ViewInject(R.id.ll_funding_schedule)
	private LinearLayout ll_funding_schedule;		//�ʽ�ƻ���

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_office, container, false); // ����fragment����
		ViewUtils.inject(this, view); // ע��view���¼�
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