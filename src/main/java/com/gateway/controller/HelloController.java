package com.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 控制器<br>
 * 版权: Copyright (c) 2011-2019<br>
 * 
 * @author: 孙常军<br>
 * @date: 2019年4月25日<br>
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

	@RequestMapping("/1")
	public String hello1() {
		return "1";
	}

	@RequestMapping("/2")
	public String hello2() {
		return "2";
	}

	@RequestMapping("/common")
	public String common() {
		return "common";
	}

}
