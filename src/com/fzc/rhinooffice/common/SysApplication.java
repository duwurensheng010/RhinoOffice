package com.fzc.rhinooffice.common;

import java.util.LinkedList;
import java.util.List;

import com.fzc.rhinooffice.module.entity.Business;
import com.fzc.rhinooffice.module.entity.Email;
import com.fzc.rhinooffice.module.entity.Flow;
import com.fzc.rhinooffice.module.entity.News;
import com.fzc.rhinooffice.module.entity.Notify;
import com.fzc.rhinooffice.module.entity.User;
import com.lidroid.xutils.util.LogUtils;

import android.app.Activity;
import android.app.Application;

public class SysApplication extends Application {
	
	public static boolean isLogin = false;
	public static String a_sessid;
	private static List<Activity> activityList;
	public static User user;
	public static Email email;
	public static Notify notify;
	public static News news;
	public static Flow flow;
	public static Business business;

	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}
	

	// add Activity
	public static void addActivity(Activity activity) {
		activityList.add(activity);
	}

	public static void exit() {
		try {
			for (Activity activity : activityList) {
				if (activity != null)
					activity.finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	private void init() {
		LogUtils.customTagPrefix = "RhinoOffice"; // �������ʱ���� adb logcat ���
		LogUtils.allowI = true; // ��LogUtils.i(...) �� adb log ���
		activityList = new LinkedList<Activity>();
	}

}
