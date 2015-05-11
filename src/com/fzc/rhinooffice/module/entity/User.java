package com.fzc.rhinooffice.module.entity;

import java.io.Serializable;

/**
 * 用户信息
 * @author liuchao
 *
 */

public class User{
	
	//用户名
	public String A_USER_NAME;
	//密码
	public String pwd;
	//需返回的登录标记
	public String a_sessid;
	//有权限的菜单列表
	public int[] A_MENU_STR;
}
