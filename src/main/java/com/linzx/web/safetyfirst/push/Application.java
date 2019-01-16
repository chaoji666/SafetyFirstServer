package com.linzx.web.safetyfirst.push;

import java.util.logging.Logger;

import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.linzx.web.safetyfirst.push.provider.GsonProvider;
import com.linzx.web.safetyfirst.push.service.AccountService;
import com.linzx.web.safetyfirst.push.utils.Hib;

public class Application extends ResourceConfig{
	public Application() {
		//注册逻辑处理的包名
		packages(AccountService.class.getPackage().getName());
		
		//注册json解析器
//		register(JacksonJsonProvider.class);
		//替换解析器为Gson
		register(GsonProvider.class);
		new Hib();
		//注册日志打印输出
		register(Logger.class);
	}
}
