package com.linzx.web.safetyfirst.push.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.linzx.web.safetyfirst.push.bean.User;

//127.0.0.1/api/account/...
@Path("/account")
public class AccountService {

	//127.0.0.1/api/account/login
	@GET
	@Path("/login")
	public String get() {
		return "you get the login.";
	}
	
	@POST
	@Path("/login")
	//指定请求与返回的响应体为json
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User post() {
		User user = new User();
		user.setName("妹纸");
		user.setSex(2);
		return user;
	}
}
