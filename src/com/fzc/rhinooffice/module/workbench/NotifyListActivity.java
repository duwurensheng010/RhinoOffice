package com.fzc.rhinooffice.module.workbench;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.fzc.rhinooffice.R;
import com.fzc.rhinooffice.common.RemoteInvoke;
import com.fzc.rhinooffice.common.utils.JsonUtil;
import com.fzc.rhinooffice.common.view.CustomProgress;
import com.fzc.rhinooffice.common.view.HScrollView;
import com.fzc.rhinooffice.common.view.InterceptScrollContainer;
import com.fzc.rhinooffice.common.view.HScrollView.OnScrollChangedListener;
import com.fzc.rhinooffice.module.BaseActivity;
import com.fzc.rhinooffice.module.CommonAdapter;
import com.fzc.rhinooffice.module.ViewHolder;
import com.fzc.rhinooffice.module.entity.Email;
import com.fzc.rhinooffice.module.entity.Notify;
import com.fzc.rhinooffice.module.workbench.EmailListActivity.ListViewAndHeadViewTouchLinstener;
import com.fzc.rhinooffice.module.workbench.EmailListActivity.OnScrollChangedListenerImp;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;

@ContentView(R.layout.activity_mail_list)
public class NotifyListActivity extends BaseActivity implements OnItemClickListener{
	
	@ViewInject(R.id.iv_back)
	private ImageView iv_back;

	@ViewInject(R.id.tv_title)
	private TextView tv_title;

	@ViewInject(R.id.iv_filter)
	private TextView iv_filter;

	// 表格标题
	@ViewInject(R.id.form_head)
	private InterceptScrollContainer isc_head;

	@ViewInject(R.id.tv_01)
	private TextView tv_01; // 序号

	@ViewInject(R.id.tv_02)
	private TextView tv_02; // 标题

	@ViewInject(R.id.tv_03)
	private TextView tv_03; // 通知类型

	@ViewInject(R.id.tv_04)
	private TextView tv_04; // 发件时间

	@ViewInject(R.id.horizontal_ScrollView)
	private HScrollView horizontal_ScrollView;

	@ViewInject(R.id.lv_mails)
	private ListView lv_notify;

	private CommonAdapter<Notify> commonAdapter;

	private List<Notify> notifyList;

	private CustomProgress customProgress;
	
	private Intent mIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initUI();
		getNotifys();
		//initAdapter();
	}

	private void initUI() {
		isc_head.setFocusable(true);
		isc_head.setClickable(true);
		isc_head.setBackgroundColor(Color.parseColor("#b2d235"));
		isc_head.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());
		lv_notify.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());
		
		tv_title.setText("公告通知");
		tv_01.setText(R.string.sequence);
		tv_02.setText(R.string.subject);
		tv_03.setText(R.string.notify_type);
		tv_04.setText(R.string.send_time);
		
		lv_notify.setOnItemClickListener(this);
		
	}

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			customProgress.dismiss();
			switch (msg.what) {
			case 2:
				JSONObject jsonObject = (JSONObject) msg.obj;
				if (jsonObject != null) {
					try {
						if (jsonObject.getString("record") != null
								&& !"".equals(jsonObject.getString("record"))) {
							notifyList = JsonUtil.analysis_notifys(jsonObject
									.getString("record"));
							if (notifyList != null && notifyList.size() != 0) {
								initAdapter();
							} else {
								Toast.makeText(NotifyListActivity.this, "暂无可读通知",
										Toast.LENGTH_SHORT).show();
							}

						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					Toast.makeText(NotifyListActivity.this, "未知错误",
							Toast.LENGTH_SHORT).show();
				}

				break;

			case -2:
				Toast.makeText(NotifyListActivity.this, "通知列表加载失败！",
						Toast.LENGTH_SHORT).show();
				break;
			}
		};
	};

	private void getNotifys() {
		customProgress = CustomProgress.init(this,
				getResources().getString(R.string.loading), false, null);
		customProgress.show();
		RemoteInvoke.notify(mHandler, "", 1);
	}

	class ListViewAndHeadViewTouchLinstener implements View.OnTouchListener {

		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			// 当在列头 和 listView控件上touch时，将这个touch的事件分发给 ScrollView
			horizontal_ScrollView.onTouchEvent(arg1);
			return false;
		}
	}

	private void initAdapter() {
		/*notifyList = new ArrayList<Notify>();
		notifyList.add(new Notify());
		notifyList.add(new Notify());
		notifyList.add(new Notify());
		notifyList.add(new Notify());*/
		if (commonAdapter == null) {
			commonAdapter = new CommonAdapter<Notify>(this, notifyList,R.layout.mail_list_item) {
					
						@Override
						public void convert(ViewHolder holder,Notify item) {
								
							holder.setText(R.id.tv_01,holder.getPosition()+1+"");
							holder.setText(R.id.tv_02,item.subject);
							holder.setText(R.id.tv_03,item.from_name);
							holder.setText(R.id.tv_04,item.begin_date);
							horizontal_ScrollView
							.AddOnScrollChangedListener(new OnScrollChangedListenerImp(
									holder.getView(R.id.horizontal_ScrollView)));
						}

				
			};
			lv_notify.setAdapter(commonAdapter);
		}else{
			commonAdapter.notifyDataSetChanged();
		}

		

	}

	@OnClick(R.id.iv_back)
	private void OpenDL(View v) {
		finish();
	}

	
	class OnScrollChangedListenerImp implements OnScrollChangedListener {
		HScrollView mScrollViewArg;

		public OnScrollChangedListenerImp(View view) {
			if(view instanceof HScrollView){
				mScrollViewArg = (HScrollView) view;
			}
			
		}

		@Override
		public void onScrollChanged(int l, int t, int oldl, int oldt) {
			mScrollViewArg.smoothScrollTo(l, t);
		}
	};
	
	@OnItemClick(R.id.lv_mails)
	private void emailClick(View v){
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		mIntent = new Intent(this,NotifyDetailActivity.class);
		mIntent.putExtra("notify_id", notifyList.get(position).notify_id);
		mIntent.putExtra("notify_id", 7);	//测试
		startActivity(mIntent);
	}
	
}
