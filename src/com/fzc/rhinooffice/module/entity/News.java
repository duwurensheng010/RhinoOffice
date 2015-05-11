package com.fzc.rhinooffice.module.entity;

import java.io.Serializable;

public class News implements Serializable{
	
	// 当天新闻数量
	public int news_sl;

	// 新闻ID
	public int news_id;

	// 新闻标题
	public String news_subject;

	// 发布时间
	public String news_time;

}
