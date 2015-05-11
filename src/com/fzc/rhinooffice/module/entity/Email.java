package com.fzc.rhinooffice.module.entity;

import java.io.Serializable;

public class Email implements Serializable{

	// 新邮件数量
	public int email_sl;

	// 邮件ID
	public int email_id;

	// 发件人
	public String email_from_id;

	// 邮件标题
	public String email_subject;

	// 发件时间
	public String email_time;

}
