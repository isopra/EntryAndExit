package jp.co.isopra.entryandexit.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.isopra.entryandexit.entity.Member;
import jp.co.isopra.entryandexit.entity.Record;
import jp.co.isopra.entryandexit.repositories.MemberRepository;
import jp.co.isopra.entryandexit.repositories.RecordRepository;

@Controller
public class RecordController {

	@Autowired
	RecordRepository recordRepository;

	@Autowired
	MemberRepository memberRepository;


	@RequestMapping("/record")
	public ModelAndView record(ModelAndView mav,
			@ModelAttribute Record record,
			BindingResult result1,
			@ModelAttribute Member member,
			BindingResult result2) {
		mav.setViewName("record");
		Iterable<Record> recordT = recordRepository.findAll();
		Iterable<Member> memberT = memberRepository.findAll();
		mav.addObject("recordT",recordT);
		mav.addObject("memberT",memberT);
		if(result1.hasErrors() || result2.hasErrors()) {
			for(ObjectError error:result1.getAllErrors()) {
				System.out.println(error.getDefaultMessage());
			}
			for(ObjectError error:result2.getAllErrors()) {
				System.out.println(error.getDefaultMessage());
			}
		}
		return mav;
	}

	@RequestMapping("/recordDetail/regist/1")
	public ModelAndView recordRegist(
			@ModelAttribute("formModel") @Validated Record record,
			ModelAndView mav) {

			recordRepository.saveAndFlush(record);
			return new ModelAndView("redirect:/");
	}

}
