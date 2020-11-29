package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Talk;
import com.example.demo.repository.TalkRepository;

@Controller
public class HomeController {

	private final TalkRepository talkRepository;
	//ラジオボタンの変数宣言
	private String radioName;

	public HomeController(TalkRepository talkRepository) {
		this.talkRepository = talkRepository;
	}

	public String getRadioName() {
		return radioName;
	}

	public void setRadioName(String radioName) {
		this.radioName = radioName;
	}

	//	ホーム画面の表示
	@RequestMapping("/home")
	public String homeAcc() {
		return "home";
	}

	//ラジタボタンの生成
	private Map<String, String> getRadioItems() {
		Map<String, String> selectMap = new LinkedHashMap<String, String>();
		/* 第1引数は値
		 * 第2引数はHTMLで表示される選択肢 */
		selectMap.put("あかまる", "あかまる");
		selectMap.put("あおまる", "あおまる");
		selectMap.put("みどりまる", "みどりまる");
		selectMap.put("きまる", "きまる");
		selectMap.put("くろまる", "くろまる");
		selectMap.put("しろまる", "しろまる");
		return selectMap;
	}

	//	タイムラインページの表示
	@GetMapping("/timeLine")
	public String radioTimeLine(@ModelAttribute Talk talk, Model model) {
		model.addAttribute("talks", talkRepository.findAll());
		model.addAttribute("radioItems", getRadioItems());
		return "timeLine";
	}

	/*	コメント登録とバリデーションチェック
		BindingResultはBeanValidationの機能(インターフェースらしい)	*/
	@PostMapping("/add")
	public String addComment(@Validated @ModelAttribute Talk talk, BindingResult result, Model model) {
		model.addAttribute("talks", talkRepository.findAll());
		if (result.hasErrors()) {
			return "redirect:/timeLine";
		}
		//コメントの登録
		talkRepository.save(talk);
		return "redirect:/timeLine";
	}
}
