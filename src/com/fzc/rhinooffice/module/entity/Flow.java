package com.fzc.rhinooffice.module.entity;

import java.io.Serializable;

/**
 * 工作流类
 * 
 * @author Administrator
 * 
 */
public class Flow implements Serializable{

	//待办工作流数量
	public String flow_sl;

	// 打开工作流要返回的
	public String run_id;

	// 打开工作流要返回的
	public String prcs_id;

	// 打开工作流要返回的
	public String flow_prcs;
	
	public String flow_id;
	
	//工作流标题
	public String subject;
	
	//工作流开始时间
	public String prcs_time;

}
