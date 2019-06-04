package jp.co.isopra.entryandexit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HeloController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/faceCapture")
	public String faceCapture() {
		return "faceCapture";
	}

	@RequestMapping("/recordDetail")
	public String recordDetail() {
		return "recordDetail";
	}

}