package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Talk;
import com.example.demo.repository.TalkRepository;

@Controller
public class HomeController {

	@Autowired
	private final TalkRepository talkRepository;

	public HomeController(TalkRepository talkRepository) {
		this.talkRepository = talkRepository;
	}

	//	ホーム画面の表示
	@RequestMapping("/home")
	public String homeAcc() {
		return "home";
	}

	//	タイムラインページの表示
	@RequestMapping("/timeLine")
	public String getTimeline(@ModelAttribute Talk talk, Model model) {
		model.addAttribute("talks", talkRepository.findAll());
		return "timeLine";
	}

	/*	コメント登録とバリデーションチェック
		BindingResultはBeanValidationの機能(インターフェースらしい)	*/
	@PostMapping("/add")
	public String addComment(@Validated @ModelAttribute Talk talk, BindingResult result, Model model) {
		model.addAttribute("talks", talkRepository.findAll());
		if (result.hasErrors()) {
			return "timeLine";
		}
		talkRepository.save(talk);
		return "timeLine";
	}
}
