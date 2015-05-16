package com.fzc.rhinooffice.module.entity;

import java.io.Serializable;

public class News implements Serializable {

	// 当天新闻数量
	public String news_sl;

	// 新闻ID
	public String news_id;

	// 新闻标题
	public String subject;

	// 发布时间
	public String news_time;

	// 发布人
	public String from_name;

	//新闻类型
	public String type_name;

	// 内容
	public String content;

}
