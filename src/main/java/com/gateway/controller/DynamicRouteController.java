package com.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	public String add(@Validated @RequestBody RouteDefinition definition) {
		return dynamicRouteService.add(definition);
	}

	@RequestMapping("/update")
	public String update(@Validated @RequestBody RouteDefinition definition) {
		return dynamicRouteService.update(definition);
	}

	@RequestMapping("/delete")
	public String delete(String id) {
		return dynamicRouteService.delete(id);
	}

}
