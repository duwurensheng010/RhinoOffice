package com.fzc.rhinooffice.module.workbench;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fzc.rhinooffice.R;
import com.fzc.rhinooffice.common.RemoteInvoke;
import com.fzc.rhinooffice.common.SysApplication;
import com.fzc.rhinooffice.common.utils.JsonUtil;
import com.fzc.rhinooffice.common.utils.StringUtil;
import com.fzc.rhinooffice.common.view.CustomProgress;
import com.fzc.rhinooffice.module.BaseActivity;
import com.fzc.rhinooffice.module.CommonAdapter;
import com.fzc.rhinooffice.module.InitActivity;
import com.fzc.rhinooffice.module.ViewHolder;
import com.fzc.rhinooffice.module.entity.AttachmentRecord;
import com.fzc.rhinooffice.module.entity.Email;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

@ContentView(R.layout.activity_emial_detail)
public class EmailDetailActivity extends BaseActivity {

	@ViewInject(R.id.iv_back)
	private ImageView iv_back;

	@ViewInject(R.id.tv_title)
	private TextView tv_title;

	@ViewInject(R.id.tv_filter)
	private TextView tv_filter;

	@ViewInject(R.id.tv_email_subject)
	private TextView tv_email_subject; // 邮件标题

	@ViewInject(R.id.tv_from_name)
	private TextView tv_from_name; // 发件人

	@ViewInject(R.id.tv_send_time)
	private TextView tv_send_time; // 发件人

	@ViewInject(R.id.ll_attachment_record)
	private LinearLayout ll_attachment_record; // 附件布局

	@ViewInject(R.id.gv_attachment)
	private GridView gv_attachment; // 附件列表

	@ViewInject(R.id.tv_email_content)
	private TextView tv_email_content; // 邮件内容

	private CustomProgress customProgress;
	
	private Intent mIntent;
	
	private Email email;
	
	private CommonAdapter<AttachmentRecord> commonAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		customProgress = CustomProgress.init(this,
				getResources().getString(R.string.loading), false, null);
		
		tv_filter.setVisibility(View.GONE);
		tv_title.setText("邮件详细信息");
		getEmailDetail();
	}
	
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			customProgress.dismiss();
			switch (msg.what) {
			case 3:
				// 获取邮件详情成功
				JSONObject jsonObject = (JSONObject) msg.obj;
				if (jsonObject != null) {
					try {

						if (!StringUtil.isEmpty(jsonObject.getString("record"))) {
							email = JsonUtil.analysis_email(jsonObject
									.getString("record"));
							//附件json数据
							String attach_str = jsonObject.getString(jsonObject.getJSONObject("record").getString("attachment_record"));
							if (StringUtil.isEmpty(attach_str)) {
								email.attachs = JsonUtil.analysis_attachment_record(attach_str);
							}
							initUI();
						}

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

				break;

			case -3:
				// 获取邮件详情失败
				String reason = (String) msg.obj;
				StringUtil.showToast(getApplicationContext(), reason);
				finish();
				break;
			}
		}

		
	};

	private void getEmailDetail() {
		mIntent = getIntent();
		customProgress.show();
		RemoteInvoke.email_detail(mHandler, mIntent.getIntExtra("email_id", 16));
	}
	
	private void initUI() {
		
		if(StringUtil.isEmpty(email.subject)){
			tv_email_subject.setText("邮件标题：未知标题");
		}else{
			tv_email_subject.setText("邮件标题："+email.subject);
		}
		
		if(StringUtil.isEmpty(email.from_name)){
			tv_from_name.setText("发件人：未知发件人");
		}else{
			tv_from_name.setText("发件人："+email.from_name);
		}
		
		if(StringUtil.isEmpty(email.content)){
			tv_email_content.setText("无");
		}else{
			tv_email_content.setText(email.content);
		}
		
		//附件
		if(email.attachs!=null && email.attachs.size()!=0){
			ll_attachment_record.setVisibility(View.VISIBLE);
			initAdapter();
		}else{
			ll_attachment_record.setVisibility(View.GONE);
		}
	}
	
	private void initAdapter(){
		if(commonAdapter==null){
			commonAdapter = new CommonAdapter<AttachmentRecord>(this, email.attachs,R.layout.attachment_record_item) {

				@Override
				public void convert(ViewHolder holder, AttachmentRecord item) {
					
					holder.setText(R.id.attach_name, item.name);
				}
			};
		}else{
			commonAdapter.notifyDataSetChanged();
		}
	}
	
	@OnClick(R.id.iv_back)
	private void goBack(View v){
		finish();
	}
	
}
