package com.fzc.rhinooffice.module.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 * @author liuchao
 *
 */

public class User{
	//商城网址
	public String mallurl;
	//用户ID/登录的帐号
	public String userid;
	//用户名
	public String a_user_name;
	//密码
	public String pwd;
	//需返回的登录标记
	public String a_sessid;
	//有权限的菜单列表
	public List<String> a_menu_str;
}
