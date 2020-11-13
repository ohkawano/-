package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	//	ホーム画面の表示
	@RequestMapping("/home")
	public String homeAcc() {
		return "home";
	}

	@RequestMapping("/maruComment")
	public String maruComment() {
		return "maruComment";
	}

}
