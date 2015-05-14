package com.fzc.rhinooffice.common.utils;

import com.fzc.rhinooffice.module.workbench.EmailDetailActivity;

import android.content.Context;
import android.widget.Toast;

public class StringUtil {
	
	public static boolean isEmpty(String str){
		if(str==null || "".equals(str)){
			return true;
		}
		return false;
	}
	
	public static void showToast(Context context,String content){
		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
	}
}
