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
	public int flow_sl;

	// 打开工作流要返回的
	public int flow_run_id;

	// 打开工作流要返回的
	public int flow_prcs_id;

	// 打开工作流要返回的
	public int flow_prcs;
	
	public int flow_id;
	
	//工作流标题
	public String flow_subject;
	
	//工作流开始时间
	public String flow_prcs_time;

}
