package com.fzc.rhinooffice.module.workbench;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fzc.rhinooffice.R;
import com.fzc.rhinooffice.common.view.HScrollView;
import com.fzc.rhinooffice.common.view.HScrollView.OnScrollChangedListener;
import com.fzc.rhinooffice.common.view.InterceptScrollContainer;
import com.fzc.rhinooffice.module.BaseActivity;
import com.fzc.rhinooffice.module.entity.Email;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_mail_list)
public class MailListActivity extends BaseActivity {
	
	@ViewInject(R.id.iv_back)
	private ImageView iv_back;
	
	@ViewInject(R.id.tv_title)
	private TextView tv_title;
	
	@ViewInject(R.id.iv_filter)
	private TextView iv_filter;
	
	//表格标题
	@ViewInject(R.id.form_head)
	private InterceptScrollContainer isc_head;
	
	@ViewInject(R.id.tv_01)
	private TextView tv_01;		//序号
	
	@ViewInject(R.id.tv_02)
	private TextView tv_02;		//标题
	
	@ViewInject(R.id.tv_03)
	private TextView tv_03;		//发件人
	
	@ViewInject(R.id.tv_04)
	private TextView tv_04;		//发件时间
	
	@ViewInject(R.id.horizontal_ScrollView)
	private HScrollView horizontal_ScrollView;
	
	@ViewInject(R.id.lv_mails)
	private ListView lv_mails;
	
	private MainListAdapter mailListAdapter;
	
	private List<Email> emailList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initUI();
	}
	
	private void initUI(){
		isc_head.setFocusable(true);
		isc_head.setClickable(true);
		isc_head.setBackgroundColor(Color.parseColor("#b2d235"));
		isc_head.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());
		lv_mails.setOnTouchListener(new ListViewAndHeadViewTouchLinstener());
		
		tv_01.setText(R.string.sequence);
		tv_02.setText(R.string.subject);
		tv_03.setText(R.string.from);
		tv_04.setText(R.string.send_time);
		
		mailListAdapter = new MainListAdapter(this);
		lv_mails.setAdapter(mailListAdapter);
	}
	
	
	class ListViewAndHeadViewTouchLinstener implements View.OnTouchListener {

		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			//当在列头 和 listView控件上touch时，将这个touch的事件分发给 ScrollView
			horizontal_ScrollView.onTouchEvent(arg1);
			return false;
		}
	}
	
	public class MainListAdapter extends BaseAdapter{
		
		LayoutInflater mInflater;

		public MainListAdapter(Context context) {
			super();
			mInflater = LayoutInflater.from(context);

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 100;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parentView) {
			ViewHolder holder = null;
			if (convertView == null) {
				synchronized (MailListActivity.this) {
					convertView = mInflater.inflate(R.layout.mail_list_item, null);
					holder = new ViewHolder();

					holder.hScrollView = (HScrollView) convertView.findViewById(R.id.horizontal_ScrollView);
					holder.tv_01 = (TextView) convertView
							.findViewById(R.id.tv_01);
					holder.tv_02 = (TextView) convertView
							.findViewById(R.id.tv_02);
					holder.tv_03 = (TextView) convertView
							.findViewById(R.id.tv_03);
					holder.tv_04 = (TextView) convertView
							.findViewById(R.id.tv_04);

					
					horizontal_ScrollView
							.AddOnScrollChangedListener(new OnScrollChangedListenerImp(
									holder.hScrollView));
						
					convertView.setTag(holder);
				}
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tv_01.setText(position + "" + 1);
			holder.tv_02.setText(position + "" + 2);
			holder.tv_03.setText(position + "" + 3);
			holder.tv_04.setText(position + "" + 4);

			return convertView;
		}

		class OnScrollChangedListenerImp implements OnScrollChangedListener {
			HScrollView mScrollViewArg;

			public OnScrollChangedListenerImp(HScrollView scrollViewar) {
				mScrollViewArg = scrollViewar;
			}

			@Override
			public void onScrollChanged(int l, int t, int oldl, int oldt) {
				mScrollViewArg.smoothScrollTo(l, t);
			}
		};

		class ViewHolder {
			TextView tv_01;
			TextView tv_02;
			TextView tv_03;
			TextView tv_04;
			HScrollView hScrollView;
		}
	}
	
}
