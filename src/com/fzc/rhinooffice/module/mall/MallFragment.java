package com.fzc.rhinooffice.module.mall;

import com.fzc.rhinooffice.R;
import com.lidroid.xutils.ViewUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * �̳ǽ���
 * @author chao.liu
 *
 */

public class MallFragment extends Fragment {

	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_mall, container, false); // ����fragment����
		ViewUtils.inject(this, view); // ע��view���¼�
		return view;
	}
}