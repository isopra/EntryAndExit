package jp.co.isopra.entryandexit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecordController {

	@RequestMapping("record")
	public String record() {
		return "record";
	}
}
