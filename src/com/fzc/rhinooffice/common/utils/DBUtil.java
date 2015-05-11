package com.fzc.rhinooffice.common.utils;

import android.content.Context;

import com.fzc.rhinooffice.module.entity.UserLogin;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

public class DBUtil {
	
	public static boolean saveUser(Context context,UserLogin userLogin){
		
		if(findUserLogin(context,userLogin)){
			return true;
		}
		
		DbUtils db = DbUtils.create(context);
		try {
			db.save(userLogin);
		} catch (DbException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean findUserLogin(Context context,UserLogin userLogin){
		DbUtils db = DbUtils.create(context);
		UserLogin temp = null;
		try {
			temp = db.findFirst(Selector.from(UserLogin.class).where("username", "=",userLogin.username));
		} catch (DbException e) {
			e.printStackTrace();
			return false;
		}
		if(temp==null){
			return false; 
		}else{
			return true;
		}
	}
	
	public static UserLogin findFirstUserLogin(Context context){
		DbUtils db = DbUtils.create(context);
		UserLogin userLogin = null;
		try {
			userLogin = db.findFirst(Selector.from(UserLogin.class).where("username", "!=",null));
		} catch (DbException e) {
			e.printStackTrace();
			return null;
		}
		return userLogin;
	}
	
}
