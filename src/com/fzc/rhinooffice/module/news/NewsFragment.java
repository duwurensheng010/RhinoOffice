package com.fzc.rhinooffice.module.news;

import com.fzc.rhinooffice.R;
import com.lidroid.xutils.ViewUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ��Ϣҳ��
 * @author chao.liu
 *
 */

public class NewsFragment extends Fragment {

	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_news, container, false); // ����fragment����
		ViewUtils.inject(this, view); // ע��view���¼�
		return view;
	}
}