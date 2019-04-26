package com.gateway.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ContextPathCompositeHandler;
import org.springframework.http.server.reactive.HttpHandler;

/**
 * 
 * Netty服务器无法自动识别context-path<br>
 * 版权: Copyright (c) 2011-2019<br>
 * 公司: 活力天汇<br>
 * 
 * @author: 孙常军<br>
 * @date: 2019年4月26日<br>
 */
@Configuration
public class NettyReactiveWebServerConfig {

	@Bean
	public NettyReactiveWebServerFactory nettyReactiveWebServerFactory(@Value("${server.servlet.context-path}") String contextPath) {
		return new NettyReactiveWebServerFactory() {
			@Override
			public WebServer getWebServer(HttpHandler httpHandler) {
				Map<String, HttpHandler> handlerMap = new HashMap<>();
				handlerMap.put(contextPath, httpHandler);
				return super.getWebServer(new ContextPathCompositeHandler(handlerMap));
			}
		};
	}

}