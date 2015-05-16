package com.fzc.rhinooffice.module.workbench;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fzc.rhinooffice.R;
import com.fzc.rhinooffice.common.RemoteInvoke;
import com.fzc.rhinooffice.common.utils.JsonUtil;
import com.fzc.rhinooffice.common.utils.StringUtil;
import com.fzc.rhinooffice.common.view.CustomProgress;
import com.fzc.rhinooffice.module.BaseActivity;
import com.fzc.rhinooffice.module.entity.News;
import com.fzc.rhinooffice.module.entity.Notify;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

@ContentView(R.layout.activity_notify_detail)
public class NewsDetailActivity extends BaseActivity {
	@ViewInject(R.id.iv_back)
	private ImageView iv_back;

	@ViewInject(R.id.tv_title)
	private TextView tv_title;

	@ViewInject(R.id.tv_filter)
	private TextView tv_filter;
	
	@ViewInject(R.id.tv_subject)
	private TextView tv_subject;	//标题
	
	@ViewInject(R.id.tv_begin_date)
	private TextView tv_begin_date;		//发布时间
	
	@ViewInject(R.id.tv_type_name)
	private TextView tv_type_name;		//新闻类型
	
	@ViewInject(R.id.tv_from_name)
	private TextView tv_from_name;		//新闻来源
	
	@ViewInject(R.id.tv_content)
	private TextView tv_content;		//新闻内容
	
	private CustomProgress customProgress;
	
	private News news;
	
	private Intent mIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtils.i("NotifyDetailActivity begin!");
		ViewUtils.inject(this);
		customProgress = CustomProgress.init(this,
				getResources().getString(R.string.loading), false, null);
		tv_filter.setVisibility(View.GONE);
		tv_title.setText("新闻详情");
		getNewsDetail();
	}

	private void getNewsDetail() {
		mIntent = getIntent();
		customProgress.show();
		RemoteInvoke.news_detail(mHandler, mIntent.getStringExtra("news_id"));
		//RemoteInvoke.news_detail(mHandler, mIntent.getIntExtra("news_id", 7));
	}
	
	private Handler mHandler = new Handler(){
		
		@Override
		public void handleMessage(android.os.Message msg) {
			customProgress.dismiss();
			switch (msg.what) {
			case 5:
				// 获取邮件详情成功
				JSONObject jsonObject = (JSONObject) msg.obj;
				if (jsonObject != null) {
					try {

						if (!StringUtil.isEmpty(jsonObject.getString("record"))) {
							news = JsonUtil.analysis_news(StringUtil.StringToJsonReader(jsonObject
									.getString("record")));
							initUI();
						}

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

				break;

			case -5:
				// 获取邮件详情失败
				String reason = (String) msg.obj;
				StringUtil.showToast(getApplicationContext(), reason);
				finish();
				break;
			}
		};
	};

	protected void initUI() {
		if(StringUtil.isEmpty(news.subject)){
			tv_subject.setText("未知标题");
		}else{
			tv_subject.setText(news.subject);
		}
		
		if(StringUtil.isEmpty(news.news_time)){
			tv_begin_date.setText("时间：未知");
		}else{
			tv_begin_date.setText("时间："+news.news_time);
		}
		
		if(StringUtil.isEmpty(news.type_name)){
			tv_type_name.setText("公告类型：未知");
		}else{
			tv_type_name.setText("公告类型："+news.type_name);
		}
		
		if(StringUtil.isEmpty(news.from_name)){
			tv_from_name.setText("发布人：未知");
		}else{
			tv_from_name.setText("发布人："+news.from_name);
		}
		
		if(StringUtil.isEmpty(news.content)){
			tv_content.setText("无");
		}else{
			tv_content.setText("\u3000\u3000"+news.content);
		}
		
	}
	
	@OnClick(R.id.iv_back)
	private void goBack(View v){
		finish();
	}
}
