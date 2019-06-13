package jp.co.isopra.entryandexit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.isopra.entryandexit.entity.Record;
import jp.co.isopra.entryandexit.repositories.RecordRepository;

@Controller
public class RecordController {

	@Autowired
	RecordRepository recordRepository;

	@RequestMapping("record")
	public String record() {
		return "record";
	}

	@RequestMapping("/recordDetail/regist/1")
	public ModelAndView recordRegist(
			@ModelAttribute("formModel") @Validated Record record,
			ModelAndView mav) {

			recordRepository.saveAndFlush(record);
			return new ModelAndView("redirect:/");
	}

}
