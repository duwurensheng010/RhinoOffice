package com.fzc.rhinooffice.common.utils;


import java.io.StringReader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.JsonReader;
import android.widget.Toast;

@SuppressLint("NewApi")
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
	
	public static JsonReader StringToJsonReader(String str){
		return new JsonReader(new StringReader(str));
	}
	
	
}
