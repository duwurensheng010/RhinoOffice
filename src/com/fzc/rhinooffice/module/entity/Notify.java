package com.fzc.rhinooffice.module.entity;

import java.io.Serializable;

/**
 * 通知类
 * 
 * @author Administrator
 * 
 */

public class Notify implements Serializable{

	// 新通知数量
	public int notify_sl;

	// 通知ID
	public int notify_id;

	//通知标题
	public String notify_subject;

	//发布时间
	public String notify_time;
}
