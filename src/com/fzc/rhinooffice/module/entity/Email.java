package com.fzc.rhinooffice.module.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class Email implements Serializable{

	// 新邮件数量
	public String email_sl;

	// 邮件ID
	public String email_id;
	
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
	public String attachment_rows;
	
	//为1标记为新
	public String is_new;
	
	public AttachmentRecord[] attachment_record;		//附件

	public List<AttachmentRecord> attach_list;
}
