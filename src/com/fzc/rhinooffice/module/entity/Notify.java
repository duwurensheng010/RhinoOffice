package com.fzc.rhinooffice.module.entity;

import java.io.Serializable;

/**
 * 通知类
 * 
 * @author Administrator
 * 
 */

public class Notify implements Serializable {

	// 新通知数量
	public int notify_sl;

	// 记录ID，不显示，打开明细需提供
	public int notify_id;

	// 通知标题
	public String subject;

	// 发布时间
	public String begin_date;

	// 通知类型
	public String type_name;

	// 发布人
	public String from_name;

	// 发布人ID
	public String from_id;

	// 为1标记为新
	public String is_new;
	
	//内容
	public String content;
}
