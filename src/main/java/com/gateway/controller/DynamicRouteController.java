package com.gateway.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gateway.service.DynamicRouteService;

/**
 * 
 * 动态路由规则控制器<br>
 * 版权: Copyright (c) 2011-2019<br>
 * 
 * @author: 孙常军<br>
 * @date: 2019年4月25日<br>
 */
@RestController
@RequestMapping("/route")
public class DynamicRouteController {

	@Autowired
	private DynamicRouteService dynamicRouteService;

	@RequestMapping("/add")
	public String add(@RequestBody RouteDefinition definition) {
		if (StringUtils.isBlank(definition.getId())) {
			return "fail";
		}
		return dynamicRouteService.add(definition);
	}

	@RequestMapping("/delete")
	public String delete(String id) {
		if (StringUtils.isBlank(id)) {
			return "fail";
		}
		return dynamicRouteService.delete(id);
	}

	@RequestMapping("/update")
	public String update(@RequestBody RouteDefinition definition) {
		if (StringUtils.isBlank(definition.getId())) {
			return "fail";
		}
		return dynamicRouteService.update(definition);
	}

}
