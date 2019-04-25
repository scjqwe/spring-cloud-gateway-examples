package com.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

/**
 * 
 * 动态网关监控<br>
 * 版权: Copyright (c) 2011-2019<br>
 * 
 * @author: 孙常军<br>
 * @date: 2019年4月25日<br>
 */
@Service
public class DynamicRouteService implements ApplicationEventPublisherAware {

	@Autowired
	private RouteDefinitionWriter routeDefinitionWriter;

	private ApplicationEventPublisher publisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}

	/**
	 * 发布更新事件
	 */
	private void notifyChanged() {
		this.publisher.publishEvent(new RefreshRoutesEvent(this));
	}

	/**
	 * 增加路由
	 *
	 */
	public String add(RouteDefinition definition) {
		routeDefinitionWriter.save(Mono.just(definition)).subscribe();
		notifyChanged();
		return "add success";
	}

	/**
	 * 更新路由
	 */
	public String update(RouteDefinition definition) {
		try {
			this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
		} catch (Exception e) {
			return "update fail,not find route  routeId: " + definition.getId();
		}

		try {
			routeDefinitionWriter.save(Mono.just(definition)).subscribe();
			notifyChanged();
			return "update success";
		} catch (Exception e) {
			return "update fail";
		}
	}

	/**
	 * 删除路由
	 *
	 */
	public String delete(String id) {
		try {
			this.routeDefinitionWriter.delete(Mono.just(id));
			notifyChanged();
			return "delete success";
		} catch (Exception e) {
			e.printStackTrace();
			return "delete fail";
		}
	}

}
