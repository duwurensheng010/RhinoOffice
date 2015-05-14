package com.fzc.rhinooffice.module.entity;

import java.io.Serializable;
import java.util.List;

public class Email implements Serializable{

	// 新邮件数量
	public int email_sl;

	// 邮件ID
	public int email_id;
	
	//发件人ID
	public String from_id;
	
	// 发件人姓名
	public String from_name;

	// 邮件标题
	public String subject;
	
	//邮件内容
	public String content;

	// 发件时间
	public String send_time;
	
	//附件数量
	public int attachment_rows;
	
	//为1标记为新
	public int is_new;
	
	public List<AttachmentRecord> attachs;		//附件

}
