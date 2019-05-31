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

	@RequestMapping("record")
	public String record() {
		return "record";
	}

	@RequestMapping("/member")
	public String member() {
		return "member";
	}

	@RequestMapping("/memberFace")
	public String memberFace() {
		return "memberFace";
	}

	@RequestMapping("/memberEdit")
	public String memberEdit() {
		return "memberEdit";
	}

	@RequestMapping("/location")
	public String location() {
		return "location";
	}

	@RequestMapping("/locationNew")
	public String locationNew() {
		return "locationEdit";
	}

	@RequestMapping("/locationEdit")
	public String locationEdit() {
		return "locationEdit";
	}
}
